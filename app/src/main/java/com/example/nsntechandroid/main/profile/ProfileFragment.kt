package com.example.nsntechandroid.main.profile

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nsntechandroid.R
import com.example.nsntechandroid.auth.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnLogout.setOnClickListener{
            keluar()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private fun keluar() {
        auth.signOut()
        val i = Intent(activity, LoginActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

//    fun alertsignout() {
//        val alertDialog2: AlertDialog.Builder = Builder(
//            activity
//        )
//
//        // Setting Dialog Title
//        alertDialog2.setTitle("Confirm SignOut")
//
//        // Setting Dialog Message
//        alertDialog2.setMessage("Are you sure you want to Signout?")
//
//        // Setting Positive "Yes" Btn
//        alertDialog2.setPositiveButton("YES",
//            DialogInterface.OnClickListener { dialog, which -> // Write your code here to execute after dialog
//                auth.signOut()
//                val i = Intent(
//                    activity,
//                    NewActivity::class.java
//                )
//                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
//                        Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(i)
//            })
//
//        // Setting Negative "NO" Btn
//        alertDialog2.setNegativeButton("NO",
//            DialogInterface.OnClickListener { dialog, which -> // Write your code here to execute after dialog
//                Toast.makeText(
//                    ApplicationProvider.getApplicationContext(),
//                    "You clicked on NO", Toast.LENGTH_SHORT
//                )
//                    .show()
//                dialog.cancel()
//            })
//
//        // Showing Alert Dialog
//        alertDialog2.show()
//    }
}