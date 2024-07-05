package com.desenvolvimentomobile.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CadastroActivity : AppCompatActivity() {
    private lateinit var bancoDeDadosHelper: BancoDeDadosHelper
    private lateinit var etNome: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        bancoDeDadosHelper = BancoDeDadosHelper(this)
        etNome = findViewById(R.id.etNome)
        etEmail = findViewById(R.id.etEmail)
        btnSalvar = findViewById(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            val nome = etNome.text.toString()
            val email = etEmail.text.toString()
            if (nome.isNotEmpty() && email.isNotEmpty()) {
                val resultado = bancoDeDadosHelper.adicionarCadastro(nome, email)
                if (resultado > 0) {
                    Toast.makeText(this, "Cadastro salvo com sucesso", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ListaCadastrosActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao salvar cadastro", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
