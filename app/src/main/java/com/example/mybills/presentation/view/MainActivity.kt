package com.example.mybills.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.example.mybills.R
import com.example.mybills.databinding.ActivityMainBinding


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

        biding.floatingButtonAdd.setOnClickListener{
            onAddButtonCliked()


        }
    }

    private fun onAddButtonCliked() {
        setAnimation(click)
        setVisibility(click)
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