package com.uva.calculadoranotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var a1: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notaFinalBTN.visibility = View.GONE
        notaFinal.visibility = View.GONE
        calcularA1BTN.visibility = View.VISIBLE
        refresh.visibility = View.GONE


        setListeners()
    }

    private fun setListeners() {
        ava1?.doAfterTextChanged {text ->

        }

        ava2?.doAfterTextChanged {text ->

        }

        a2?.doAfterTextChanged {text ->

        }

        a3?.doAfterTextChanged {text ->

        }

        refresh?.setOnClickListener {
            refresh()
        }

       calcularA1BTN?.setOnClickListener {
            calcularMediaA1(ava1.text.toString(), ava2.text.toString())
        }

       notaFinalBTN?.setOnClickListener {
            calcularMediaFinal(a1, a2.text.toString(), a3.text.toString())
       }

    }

    private fun calcularMediaA1(ava1: String, ava2: String) {
        val ava1 = ava1.toFloatOrNull()
        val ava2 = ava2.toFloatOrNull()
        if(ava1 != null && ava2 != null) {
            val mediaA1 = (ava1 + ava2) / 2
            a1 = mediaA1.toString()
            mA1.text = "A1: %.1f".format(mediaA1)
            notaFinalBTN.visibility = View.VISIBLE
            calcularA1BTN.visibility = View.GONE
            mA1.visibility = View.VISIBLE
            notaFinal.visibility = View.GONE
            refresh.visibility = View.VISIBLE
        }

    }

    private fun calcularMediaFinal(a1: String, a2: String, a3: String) {
        val a1 = a1.toFloatOrNull()
        val a2 = a2.toFloatOrNull()
        val a3 = a3.toFloatOrNull()
        if (a1 != null && a2 != null && a3 != null) {
            val mediaFinal = (4 * a1 + 6 * (if (a2 > a3) a2 else a3)) / 10
            notaFinal.text = "Nota Final: %.1f".format(mediaFinal)
        }
        notaFinalBTN.visibility = View.GONE
        notaFinal.visibility = View.VISIBLE
        calcularA1BTN.visibility = View.VISIBLE
        mA1.visibility = View.GONE
    }

    private fun refresh() {
        ava1.clear()
        ava2.clear()
        a2.clear()
        a3.clear()
        notaFinal.text = "Nota Final"
        notaFinal.visibility = View.VISIBLE
        notaFinalBTN.visibility = View.GONE
        calcularA1BTN.visibility = View.VISIBLE
        mA1.visibility = View.GONE
        refresh.visibility = View.GONE
    }

    private fun EditText.clear() {
        text.clear()
    }

}

