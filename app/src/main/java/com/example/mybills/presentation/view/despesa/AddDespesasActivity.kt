package com.example.mybills.presentation.view.despesa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mybills.DataAplication
import com.example.mybills.databinding.ActivityAddDespesasBinding
import com.example.mybills.domain.Despesa
import com.example.mybills.presentation.view.main.MainActivity
import com.example.mybills.presentation.view.despesa.viewModel.AddDespesaViewModel
import com.example.mybills.presentation.view.despesa.viewModel.DespesaViewModelFactory
import java.text.NumberFormat
import java.util.*

class AddDespesasActivity : AppCompatActivity() {

    private val biding by lazy { ActivityAddDespesasBinding.inflate(layoutInflater) }


    private val viewModel by lazy {
        ViewModelProvider(this, DespesaViewModelFactory((application as DataAplication).despesaRepositori))
            .get(AddDespesaViewModel::class.java)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)
        loadClicks()
    }



    private fun loadClicks() {

        biding.btnConfirmar.setOnClickListener{
            if (validation()){
                val value = biding.etValorDespesa.text?.replace("""[R$,.]""".toRegex(), "")?.trim()!!.toDouble()
                val despesa = Despesa(
                    valor = value,
                    decricao = biding.txtDescription.text.toString(),
                    data = biding.txtData.text.toString(),
                    pago = biding.switchReceita.isChecked
                )

            viewModel.insert(despesa)

                Toast.makeText(this, "Despesa Salva com sucesso", Toast.LENGTH_SHORT).show()
                Log.d("diegoLog","${despesa}")
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
        biding.closeBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun validation(): Boolean {
        var validation = true
        val erroDescription = "Campo Obrigatorio"
        if (biding.etValorDespesa.text.isNullOrEmpty()){
            validation = false
            biding.etValorDespesa.error = erroDescription
        }else if (biding.txtDescription.text.isNullOrEmpty()){
            biding.txtDescription.error = erroDescription
            validation = false
        }else if (biding.txtData.text.isNullOrEmpty()){
            biding.txtData.error = erroDescription
            validation = false
        }
        return validation
    }

    override fun onResume() {
        super.onResume()
        biding.etValorDespesa.addTextChangedListener(object : TextWatcher {
            private  var dinheiro =""

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != dinheiro) {
                    biding.etValorDespesa.removeTextChangedListener(this)

                    val cleanString: String = s!!.replace("""[R$,.]""".toRegex(), "").trim()

                    val parsed = cleanString.toDouble()

                    val formatted = NumberFormat.getCurrencyInstance().format((parsed/100))

                    dinheiro = formatted

                    biding.etValorDespesa.setText(formatted)
                    biding.etValorDespesa.setSelection(formatted.length)
                    biding.etValorDespesa.addTextChangedListener(this)
                }
            }
        })
        biding.txtData.addTextChangedListener(object : TextWatcher {
            private var current = ""
            private val ddmmyyyy = "DDMMAAAA"
            private val cal = Calendar.getInstance()
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString() != current) {
                    var clean = s.toString().replace("[^\\d.]".toRegex(), "")
                    val cleanC = current.replace("[^\\d.]".toRegex(), "")
                    val cl = clean.length
                    var sel = cl
                    var i = 2
                    while (i <= cl && i < 6) {
                        sel++
                        i += 2
                    }

                    if (clean == cleanC) sel--
                    if (clean.length < 8) {
                        clean += ddmmyyyy.substring(clean.length)
                    } else {

                        var day = clean.substring(0, 2).toInt()
                        var mon = clean.substring(2, 4).toInt()
                        var year = clean.substring(4, 8).toInt()
                        if (mon > 12) mon = 12
                        cal[Calendar.MONTH] = mon - 1
                        year = if (year < 1900) 1900 else if (year > 2100) 2100 else year
                        cal[Calendar.YEAR] = year

                        day = if (day > cal.getActualMaximum(Calendar.DATE)) cal.getActualMaximum(
                            Calendar.DATE
                        ) else day
                        clean = String.format("%02d%02d%02d", day, mon, year)
                    }
                    clean = String.format(
                        "%s/%s/%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8)
                    )
                    sel = if (sel < 0) 0 else sel
                    current = clean
                    biding.txtData.setText(current)
                    biding.txtData.setSelection(if (sel < current.length) sel else current.length)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
    }

}