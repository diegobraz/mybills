package com.example.mybills.presentation.view.main


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mybills.DataAplication
import com.example.mybills.R
import com.example.mybills.databinding.ActivityMainBinding
import com.example.mybills.domain.Despesa
import com.example.mybills.domain.Receita
import com.example.mybills.presentation.view.despesa.AddDespesasActivity
import com.example.mybills.presentation.view.despesa.DespesasListaActivity
import com.example.mybills.presentation.view.despesa.EditDespesaActivity
import com.example.mybills.presentation.view.despesa.adapter.DespesaAdapter
import com.example.mybills.presentation.view.main.viewModel.MainViewModel
import com.example.mybills.presentation.view.main.viewModel.MainViewModelFactory
import com.example.mybills.presentation.view.receitas.AddReceitaActivity
import com.example.mybills.presentation.view.receitas.EditReceitaActivity
import com.example.mybills.presentation.view.receitas.ReceitasListaActivity
import com.example.mybills.presentation.view.receitas.adapter.ReceitasAdapter
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    private val biding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var click = false
    private var valorTota = 0.0

    private val rotateAnimation by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.rotate_open_anim
        )
    }
    private val rotateclose by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val rotateToBottom by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim) }
    private val rotateFromBottom by lazy {
        AnimationUtils.loadAnimation(
            this,
            R.anim.from_bottom_anim
        )
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this, MainViewModelFactory(
                (application as DataAplication).despesaRepositori,
                (application as DataAplication).receitaRepositori
            )
        )
            .get(MainViewModel::class.java)
    }

    private val receitaAdapter by lazy {
        ReceitasAdapter(
            onClickDelete = { id ->
                viewModel.deleteReceita(id)
            },
            onclickUpdadte = { receita ->
                onCreateUpdateReceita(receita)
            }
        )
    }

    private val despesAdapter by lazy {
        DespesaAdapter(
            onClickDelete = { id ->
                viewModel.deleteDespesa(id)
            },
            onclickUpdadte = { despesa ->
                onCreateUpdateDespesa(despesa)
            }
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        biding.receitaCards.adapter = receitaAdapter
        biding.despesaCards.adapter = despesAdapter

        loadClicks()
        loadValues()
        loadAdpter()
    }

    private fun loadAdpter() {
        viewModel.getAllReceita().observe(this, { receitaList ->
            receitaAdapter.submitList(receitaList)
        })

        viewModel.getAllDespesa().observe(this, { despesaList ->
            despesAdapter.submitList(despesaList)
        })


    }

    private fun onCreateUpdateReceita(receita: Receita) {

        startActivity(
            Intent(
                this,
                EditReceitaActivity::class.java
            ).apply {
                putExtra("receita", receita)
            }
        )


    }

    private fun onCreateUpdateDespesa(despesa: Despesa) {
        startActivity(
            Intent(
                this,
                EditDespesaActivity::class.java
            ).apply {
                putExtra("despesa", despesa)
            }
        )


    }


    private fun loadValues() {
        viewModel.getSumReceita().observe(this, { receitaSum ->
            valorTota += receitaSum ?: 0.0
            biding.receitaValue.setText(
                NumberFormat.getCurrencyInstance().format(((receitaSum ?: 0.0) / 100))
            )
            biding.valorConta.setText(NumberFormat.getCurrencyInstance().format((valorTota / 100)))

        })

        viewModel.getSumDespesa().observe(this, { sumDespesa ->

            biding.despesasValue.setText(
                NumberFormat.getCurrencyInstance().format(((sumDespesa ?: 0.0) / 100))
            )
            valorTota -= sumDespesa ?: 0.0
            biding.valorConta.setText(NumberFormat.getCurrencyInstance().format((valorTota / 100)))
        })

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
            val intent = Intent(this@MainActivity, DespesasListaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onAddButtonCliked() {
        setAnimation(click)
        setVisibility(click)
        setClickable(click)
        click = !click
    }


    private fun setAnimation(clicked: Boolean) {

        if (!clicked) {
            biding.floatingButtonAdd.startAnimation(rotateAnimation)
            biding.floatingDespesasButton.startAnimation(rotateFromBottom)
            biding.floatingReceitasButton.startAnimation(rotateFromBottom)
        } else {
            biding.floatingButtonAdd.startAnimation(rotateclose)
            biding.floatingDespesasButton.startAnimation(rotateToBottom)
            biding.floatingReceitasButton.startAnimation(rotateToBottom)

        }

    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            biding.floatingDespesasButton.visibility = View.VISIBLE
            biding.floatingReceitasButton.visibility = View.VISIBLE
        } else {
            biding.floatingDespesasButton.visibility = View.INVISIBLE
            biding.floatingReceitasButton.visibility = View.INVISIBLE
        }
    }

    private fun setClickable(clicked: Boolean) {
        if (!clicked) {
            biding.floatingDespesasButton.isClickable = true
            biding.floatingReceitasButton.isClickable = true

        } else {
            biding.floatingDespesasButton.isClickable = false
            biding.floatingReceitasButton.isClickable = false
        }
    }


}