package com.desenvolvimentomobile.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AtualizarActivity : AppCompatActivity() {
    private lateinit var bancoDeDadosHelper: BancoDeDadosHelper
    private lateinit var etId: EditText
    private lateinit var etNome: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnBuscarCadastro: Button
    private lateinit var btnAtualizarCadastro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atualizar)

        bancoDeDadosHelper = BancoDeDadosHelper(this)
        etId = findViewById(R.id.etIdAtualizar)
        etNome = findViewById(R.id.etNomeAtualizar)
        etEmail = findViewById(R.id.etEmailAtualizar)
        btnBuscarCadastro = findViewById(R.id.btnBuscarCadastro)
        btnAtualizarCadastro = findViewById(R.id.btnAtualizarCadastro)

        btnBuscarCadastro.setOnClickListener {
            val cadastroId = etId.text.toString().toIntOrNull()
            if (cadastroId != null) {
                val cadastro = bancoDeDadosHelper.buscarCadastroPorId(cadastroId)
                if (cadastro != null) {
                    etNome.setText(cadastro.nome)
                    etEmail.setText(cadastro.email)
                } else {
                    Toast.makeText(this, "Cadastro não encontrado", Toast.LENGTH_SHORT).show()
                    limparCampos()
                }
            } else {
                Toast.makeText(this, "Insira um ID válido", Toast.LENGTH_SHORT).show()
                limparCampos()
            }
        }

        btnAtualizarCadastro.setOnClickListener {
            val cadastroId = etId.text.toString().toIntOrNull()
            val nome = etNome.text.toString()
            val email = etEmail.text.toString()

            if (cadastroId != null && nome.isNotEmpty() && email.isNotEmpty()) {
                val resultado = bancoDeDadosHelper.atualizarCadastro(cadastroId, nome, email)
                if (resultado > 0) {
                    Toast.makeText(this, "Cadastro atualizado com sucesso", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ListaCadastrosActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao atualizar cadastro", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun limparCampos() {
        etNome.text.clear()
        etEmail.text.clear()
    }
}
