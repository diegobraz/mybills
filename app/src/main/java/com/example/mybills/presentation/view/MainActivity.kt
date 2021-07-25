package com.example.mybills.presentation.view


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mybills.R
import com.example.mybills.databinding.ActivityMainBinding
import com.example.mybills.presentation.view.despesa.AddDespesasActivity
import com.example.mybills.presentation.view.receitas.AddReceitaActivity
import com.example.mybills.presentation.view.receitas.ReceitasListaActivity


class MainActivity : AppCompatActivity() {

    private val biding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private  var click = false

    private val rotateAnimation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateclose by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val rotateToBottom by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim )}
    private val rotateFromBottom by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim )}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        loadClicks()
//        loadValues()
    }

    private fun loadValues() {
        TODO("Not yet implemented")
    }

    private fun loadClicks() {

        biding.floatingButtonAdd.setOnClickListener {
            onAddButtonCliked()
        }
        biding.floatingReceitasButton.setOnClickListener {
            val intent = Intent(this, AddReceitaActivity::class.java)
            startActivity(intent)

        }

        biding.floatingDespesasButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddDespesasActivity::class.java)
            startActivity(intent)

        }

        biding.receitaValue.setOnClickListener {
            val intent = Intent(this@MainActivity, ReceitasListaActivity::class.java)
            startActivity(intent)
        }

        biding.despesasValue.setOnClickListener {
            Toast.makeText(this@MainActivity, "Deu na despesas", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onAddButtonCliked() {
        setAnimation(click)
        setVisibility(click)
        setClickable(click)
        click = !click
    }


    private fun setAnimation(clicked: Boolean) {

        if (!clicked){
            biding.floatingButtonAdd.startAnimation(rotateAnimation)
            biding.floatingDespesasButton.startAnimation(rotateFromBottom)
            biding.floatingReceitasButton.startAnimation(rotateFromBottom)
        }else{
            biding.floatingButtonAdd.startAnimation(rotateclose)
            biding.floatingDespesasButton.startAnimation(rotateToBottom)
            biding.floatingReceitasButton.startAnimation(rotateToBottom)

        }

    }

    private fun setVisibility(clicked:Boolean) {
        if (!clicked){
            biding.floatingDespesasButton.visibility = View.VISIBLE
            biding.floatingReceitasButton.visibility = View.VISIBLE
        }else{
            biding.floatingDespesasButton.visibility = View.INVISIBLE
            biding.floatingReceitasButton.visibility = View.INVISIBLE
        }
    }

    private fun setClickable(clicked: Boolean){
        if (!clicked){
            biding.floatingDespesasButton.isClickable = true
            biding.floatingReceitasButton.isClickable = true

        }else{
            biding.floatingDespesasButton.isClickable = false
            biding.floatingReceitasButton.isClickable = false
        }
    }


}