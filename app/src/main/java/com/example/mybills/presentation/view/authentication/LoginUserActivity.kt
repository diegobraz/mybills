package com.example.mybills.presentation.view.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mybills.databinding.ActivityLoginUserBinding
import com.example.mybills.presentation.view.main.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginUserActivity : AppCompatActivity() {

    private val biding by lazy { ActivityLoginUserBinding.inflate(layoutInflater) }
    private var auth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(biding.root)
        loadMethode()
    }

    private fun loadMethode() {

        biding.registrarUser.setOnClickListener {
            val intent = Intent(this@LoginUserActivity, RegistratioActivity::class.java)
            startActivity(intent)
        }

        biding.btnConfirmar.setOnClickListener {
           if (validation()) {
               val email: String = biding.txtEmail.text.toString().trim { it <= ' ' }
               val password: String = biding.txtPassword.text.toString().trim { it <= ' ' }

               FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                   .addOnCompleteListener(
                       OnCompleteListener<AuthResult> { task ->
                           if (task.isSuccessful) {
                               val FirebaseUser: FirebaseUser = task.result!!.user!!

                               val intent = Intent(this@LoginUserActivity, MainActivity::class.java)
                               intent.flags =
                                   Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                               intent.putExtra("user_id", FirebaseUser.uid)
                               intent.putExtra("email_id", email)
                               startActivity(intent)
                               finish()
                           } else {
                               Toast.makeText(
                                   this@LoginUserActivity,
                                   task.exception?.message.toString(),
                                   Toast.LENGTH_SHORT
                               ).show()
                           }
                       }
                   )

           }else{
               Toast.makeText(this@LoginUserActivity, "Email ou senha invalida", Toast.LENGTH_SHORT).show()
           }



        }

    }
    private fun validation(): Boolean {
        var validation = true
        if ( biding.txtEmail.text.isNullOrEmpty()){
            validation = false
        }
        else if ( biding.txtPassword.text.isNullOrEmpty()){
           validation = false
        }

        return validation

    }
}