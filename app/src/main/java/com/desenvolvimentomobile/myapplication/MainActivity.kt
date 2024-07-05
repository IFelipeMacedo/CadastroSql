package com.desenvolvimentomobile.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdicionar: Button = findViewById(R.id.btnAdicionar)
        val btnVisualizar: Button = findViewById(R.id.btnVisualizar)
        val btnDeletar: Button = findViewById(R.id.btnDeletar)
        val btnAtualizar: Button = findViewById(R.id.btnAtualizar)

        btnAdicionar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        btnVisualizar.setOnClickListener {
            val intent = Intent(this, ListaCadastrosActivity::class.java)
            startActivity(intent)
        }

        btnDeletar.setOnClickListener {
            val intent = Intent(this, DeletarActivity::class.java)
            startActivity(intent)
        }

        btnAtualizar.setOnClickListener {
            val intent = Intent(this, AtualizarActivity::class.java)
            startActivity(intent)
        }
    }
}
