package com.example.mybills.presentation.view.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mybills.databinding.ActivityRegistratioBinding
import com.example.mybills.presentation.view.main.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class RegistratioActivity : AppCompatActivity() {
    private val biding by lazy { ActivityRegistratioBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        loadListener()
    }
    private fun loadListener() {
        biding.closeBtn.setOnClickListener {
           onBackPressed()
        }

        biding.btnRegistre.setOnClickListener{
             if (validationRegistre()){

                 val email: String = biding.txtEmailRegistre.text.toString().trim { it <= ' ' }
                 val password: String = biding.txtPassword.text.toString().trim { it <= ' ' }


                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(
                        OnCompleteListener<AuthResult>{ task ->
                            if (task.isSuccessful){
                                val FirebaseUser : FirebaseUser = task.result!!.user!!
                                Toast.makeText(this@RegistratioActivity,
                                    "Registrado com sucesso",
                                    Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@RegistratioActivity, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id",FirebaseUser.uid)
                                intent.putExtra("email_id",email)
                                startActivity(intent)
                                finish()
                            }else{
                                Toast.makeText(this@RegistratioActivity, task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    )

             }
           }
        }

    private fun validationRegistre():Boolean {
        var validaForma = true
        if (biding.txtEmailRegistre.text.isNullOrEmpty()){
            validaForma = false
            biding.txtEmailRegistre.error = "email obrigatorio"
        }
        else  if (biding.txtPassword.text.isNullOrEmpty()){
            validaForma = false
            biding.passwordRegistre.error = "password obrigatorio"
        }
    return  validaForma
    }

}