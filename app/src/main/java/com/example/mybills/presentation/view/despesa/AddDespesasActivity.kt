package com.example.mybills.presentation.view.despesa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.mybills.databinding.ActivityAddDespesasBinding
import com.example.mybills.domain.Despesa
import com.example.mybills.presentation.view.MainActivity
import com.example.mybills.presentation.view.despesa.viewModel.AddDespesaViewModel
import java.text.NumberFormat
import javax.inject.Inject

class AddDespesasActivity : AppCompatActivity() {

    private val biding by lazy { ActivityAddDespesasBinding.inflate(layoutInflater) }

    @Inject
    lateinit var viewModel: AddDespesaViewModel
    
    
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
                    data = biding.txtData.toString(),
                    pago = biding.switchReceita.isChecked
                )
            viewModel.insert(despesa)
                Toast.makeText(this, "Despesa Salva com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
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
    }

}