package com.uva.calculadoranotas

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uva.calculadoranotas.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        hideComponents()
        setListeners()
    }

    private fun hideComponents() {
        binding.btnCalculaMediaFinal.visibility = View.GONE
        binding.resultadoFinal.visibility = View.GONE
        binding.notaFinal.visibility = View.GONE
        binding.refresh.visibility = View.GONE
        binding.notasSegundaEtapa.visibility = View.GONE
    }
    private fun showComponents(){
        binding.btnCalculaMediaFinal.visibility = View.VISIBLE
        binding.notaA1.visibility = View.VISIBLE
        binding.notaFinal.visibility = View.VISIBLE
        binding.refresh.visibility = View.VISIBLE
        binding.notasSegundaEtapa.visibility = View.VISIBLE
    }
    private fun showResults(){
        binding.btnCalculaMediaFinal.visibility = View.GONE
        binding.notaFinal.visibility = View.VISIBLE
        binding.resultadoFinal.visibility = View.VISIBLE
    }

    private fun setListeners() {
        binding.refresh.setOnClickListener {
            clear()
        }

        binding.btnCalculaA1.setOnClickListener {
            if(isEmpty(binding.ava1) || isEmpty(binding.ava2)){
                Toast.makeText(this@MainActivity, "Notas inválidas! Favor preencher corretamente", Toast.LENGTH_LONG).show()
            }else{
                calcularMediaA1(
                    binding.ava1.text.toString().toFloat(),
                    binding.ava2.text.toString().toFloat()
                )
            }
        }

        binding.btnCalculaMediaFinal.setOnClickListener {
            if(isEmpty(binding.notaA1) || isEmpty(binding.notaA2) || isEmpty(binding.notaA3)){
                Toast.makeText(this@MainActivity, "Notas inválidas! Favor preencher corretamente", Toast.LENGTH_LONG).show()
            }else{
                calcularMediaFinal(
                    binding.notaA1.text.toString().toFloat(),
                    binding.notaA2.text.toString().toFloat(),
                    binding.notaA3.text.toString().toFloat()
                )
            }
        }
    }

    private fun isEmpty(etText: EditText): Boolean {
        return etText.text.toString().trim().isEmpty()
    }
    private fun isEmpty(etText: TextView): Boolean {
        return etText.text.toString().trim().isEmpty()
    }

    private fun calcularMediaA1(ava1: Float, ava2: Float) {
        val mediaA1 = (ava1 + ava2) / 2
        binding.notaA1.text = "%.1f".format(mediaA1)
        binding.btnCalculaA1.isEnabled = false
        showComponents()
    }

    private fun calcularMediaFinal(a1: Float, a2: Float, a3: Float) {
        val mediaFinal = (4 * a1 + 6 * (if (a2 > a3) a2 else a3)) / 10
        if(mediaFinal < 6 || (mediaFinal > 10 && mediaFinal < 60)){
            binding.resultadoTxt.text = "Reprovado"
        }
        binding.notaFinal.text = mediaFinal.roundToOneDecimalPlace().toString()
        showResults()
    }

    private fun clear() {
        binding.ava1.clear()
        binding.ava2.clear()
        binding.notaA2.clear()
        binding.notaA3.clear()

        hideComponents()
        binding.btnCalculaA1.isEnabled = true
    }

    private fun EditText.clear() {
        text.clear()
    }

    private fun Float.roundToOneDecimalPlace(): Float {
        val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH)).apply {
            roundingMode = RoundingMode.HALF_UP
        }
        return df.format(this).toFloat()
    }
}

