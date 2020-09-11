package com.example.nsntechandroid.auth


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nsntechandroid.R
import com.example.nsntechandroid.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        // Configure Google Sign In
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        gsc = GoogleSignIn.getClient(this, gso)

        btnRegister.setOnClickListener {
            onBackPressed()
        }


        btnLoginWithGoogle.setOnClickListener {
            loginWithGoogle()
        }

        btnLogin.setOnClickListener {
            formValidation {  email, password -> loginWitEmail( email, password)
            }
        }
    }

    private fun formValidation(success: (email: String, password: String) -> Unit) {
        var isValid = true
        val email = emailTextField.editText?.text.toString()
        val password = passwordTextField.editText?.text.toString()

        if (email.isEmpty()) {
            emailTextField.error = "Email cannot be empty"
            isValid = false
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailTextField.error = "Invalid email address"
                isValid = false
            } else {
                emailTextField.error = ""
            }
        }

        if (password.isEmpty()) {
            passwordTextField.error = "Password cannot be empty"
            isValid = false
        } else {
            if (password.length < 8) {
                passwordTextField.error = "Minimum password length is 8 characters"
                isValid = false
            } else {
                passwordTextField.error = ""
            }
        }

        if (isValid) {
            success(email,password)
        }
    }

    private fun loginWitEmail(email:String,password:String){
        performLoginLoading(true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    performLoginLoading(false)
                    navigationToMain()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed. ${task.exception?.localizedMessage}",
                        Toast.LENGTH_SHORT).show()
                    performLoginLoading(false)
                }
            }
    }

    private fun loginWithGoogle() {
        val signInIntent = gsc.signInIntent
        startActivityForResult(signInIntent, RC_LOGIN)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    navigationToMain()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed. ${task.exception?.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                    performLoginLoading(false)
                }

            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_LOGIN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun performLoginLoading(isLoading: Boolean) {
        if (isLoading) {
            btnLogin.visibility = View.INVISIBLE
            loginLoading.visibility = View.VISIBLE
            btnLogin.isEnabled = false
        } else {
            btnLogin.visibility = View.VISIBLE
            loginLoading.visibility = View.INVISIBLE
            btnLogin.isEnabled = true
        }
    }

    private fun navigationToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    companion object {
        private const val TAG = "LoginActivity"
        private const val RC_LOGIN = 986
    }
}