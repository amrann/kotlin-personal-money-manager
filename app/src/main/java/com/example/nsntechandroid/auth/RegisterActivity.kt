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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

//    private lateinit var auth: FirebaseAuth
//    private lateinit var gso: GoogleSignInOptions
//    private lateinit var gsc: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        auth = Firebase.auth
//        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        gsc = GoogleSignIn.getClient(this, gso)
//
//        btnLogin.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }
//
//        btnRegisterWithGoogle.setOnClickListener {
//            registerWithGoogle()
//        }
//
//        btnRegister.setOnClickListener {
//            formValidasi { nama, email, password -> register(nama, email, password)
//            }
//        }
    }

//    // Validasi form
//    private fun formValidasi(success: (nama: String, email: String, password: String) -> Unit) {
//        var isValid = true
//        val nama = namaTextField.editText?.text.toString()
//        val email = emailTextField.editText?.text.toString()
//        val password = passwordTextField.editText?.text.toString()
//
//        if (nama.isEmpty()) {
//            namaTextField.error = "Nama tidak bisa kosong"
//            isValid = false
//        }
//        if (email.isEmpty()) {
//            emailTextField.error = "Email tidak bisa kosong"
//            isValid = false
//        }
//        if(email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            emailTextField.error = "Alamat email tidak valid"
//        }
//        if (password.isEmpty()) {
//            passwordTextField.error = "Password tidak bisa kosong"
//            isValid = false
//        }
//        if (password.isNotEmpty() && password.length < 8) {
//            passwordTextField.error = "Minimum password 8 karakter"
//            isValid = false
//        }
//        if (isValid) success(nama, password)
//    }
//
//    private fun register(nama: String, email: String, password: String) {
//        performRegisterLoading(true)
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    saveToDatabase(nama, email)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        baseContext, "Authentication failed. ${task.exception?.localizedMessage}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    performRegisterLoading(false)
//                }
//            }
//    }
//
//    private fun registerWithGoogle() {
//        val signInIntent = gsc.signInIntent
//        startActivityForResult(signInIntent, RC_REGISTER)
//    }
//
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithCredential:success")
//                    val user = auth.currentUser
//                    val nama = user?.displayName ?: ""
//                    val email = user?.email ?: ""
//                    saveToDatabase(nama, email)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    Toast.makeText(
//                        baseContext, "Authentication failed. ${task.exception?.localizedMessage}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    performRegisterLoading(false)
//                }
//
//            }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_REGISTER) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                val account = task.getResult(ApiException::class.java)!!
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
//                firebaseAuthWithGoogle(account.idToken!!)
//            } catch (e: ApiException) {
//                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e)
//            }
//        }
//    }
//
//    private fun saveToDatabase(nama: String, email: String) {
//        //mendapatkan id dari user saat register
//        val uid = auth.uid
//        //menginisiasi instance database
//        val database = Firebase.database
//        //menambahkan referensi
//        val myRef = database.getReference("users/$uid")
//
//        val userModel = HashMap<String, String>()
//        userModel["name"] = nama
//        userModel["email"] = email
//
//        myRef.setValue(userModel)
//        performRegisterLoading(false)
//        navigationToMain()
//    }
//
//    private fun performRegisterLoading(isLoading: Boolean) {
//        if (isLoading) {
//            btnRegister.visibility = View.INVISIBLE
//            registerProgress.visibility = View.VISIBLE
//            btnRegister.isEnabled = false
//        } else {
//            btnRegister.visibility = View.VISIBLE
//            registerProgress.visibility = View.INVISIBLE
//            btnRegister.isEnabled = true
//        }
//    }
//
//    private fun navigationToMain() {
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
//    }
//
//    companion object {
//        private const val TAG = "RegisterActivity"
//        private const val RC_REGISTER = 987
//    }
}