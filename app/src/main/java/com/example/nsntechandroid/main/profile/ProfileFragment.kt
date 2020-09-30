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
//            keluar()
            val builder = androidx.appcompat.app.AlertDialog.Builder(requireActivity())
            builder.setTitle("Logout")
                .setMessage("Apakah Anda Yakin Ingin Keluar?")
                .setNegativeButton("Tidak", {dialogInterface, i -> dialogInterface.dismiss()  })
                .setPositiveButton("Ya", {dialogInterface, i -> keluar()  })
            val dialog = builder.create()
            dialog.show()
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
}