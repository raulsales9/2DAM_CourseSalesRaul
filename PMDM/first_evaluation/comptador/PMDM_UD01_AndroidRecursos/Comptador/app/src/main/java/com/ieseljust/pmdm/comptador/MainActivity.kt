package com.ieseljust.pmdm.comptador

import com.ieseljust.pmdm.comptador.datebinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var comptador=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializa la variable de View Binding utilizando inflate
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Referencia al TextView a través de View Binding
        val textViewContador = binding.textViewComptador
        //per comprobar les fases
        override fun onStart(){
            super.onStart()
            Log.d(TAG, "Al metode onStart()")
        }
        if(savedInstanceState != null){
            comptador = savedInstanceState.getInt("comptador")
        }
        // Inicialitzem el TextView amb el comptador a 0
        textViewContador.text = comptador.toString();

        // Referencia al botón
        val btAdd = findViewById<Button>(R.id.btAdd)
        val btResta = findViewById<Button>(R.id.btResta);
        val btReset = findViewById<Button>(R.id.btReset);


        btAdd.setOnClickListener {
            comptador++
            textViewContador.setText(comptador.toString())
        }

        btReset.setOnClickListener {
            comptador = 0
            textViewContador.setText(comptador.toString())
        }

        btResta.setOnClickListener {
            if (comptador > 0){
                comptador--
                textViewContador.setText(comptador.toString())
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("comptador", comptador);
        super.onSaveInstanceState(outState,)
    }


}