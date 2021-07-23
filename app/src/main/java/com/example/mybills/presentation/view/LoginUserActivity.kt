package com.example.mybills.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mybills.databinding.ActivityLoginUserBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase

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
            val intent = Intent(this,RegistratioActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        biding.btnConfirmar.setOnClickListener {

            val email: String = biding.txtEmail.text.toString().trim { it <= ' ' }
            val password: String = biding.txtPassword.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult>{ task ->
                        if (task.isSuccessful){
                            val FirebaseUser : FirebaseUser = task.result!!.user!!
//                            Toast.makeText(this,
//                                "Registrado com sucesso",
//                                Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id",FirebaseUser.uid)
                            intent.putExtra("email_id",email)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                )


            



        }

    }
}