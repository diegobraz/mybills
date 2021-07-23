package com.example.mybills.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybills.databinding.ActivityLoginUserBinding

class LoginUserActivity : AppCompatActivity() {

    private val biding by lazy { ActivityLoginUserBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(biding.root)
    }
}