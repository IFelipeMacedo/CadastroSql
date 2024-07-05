package com.desenvolvimentomobile.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class DeletarActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: CadastroAdapter
    private lateinit var dbHelper: BancoDeDadosHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deletar)

        listView = findViewById(R.id.listaCadastros)
        dbHelper = BancoDeDadosHelper(this)

        val cadastros = dbHelper.listarCadastros()
        adapter = CadastroAdapter(this, cadastros)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val cadastro = adapter.getItem(position)

            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Tem certeza que deseja deletar este cadastro?")
                .setCancelable(false)
                .setPositiveButton("Sim") { dialog, id ->
                    val deletedRows = dbHelper.deletarCadastro(cadastro?.id ?: -1)
                    if (deletedRows > 0) {
                        Toast.makeText(this, "Cadastro deletado com sucesso", Toast.LENGTH_SHORT).show()
                        adapter.remove(cadastro)
                        adapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this, "Erro ao deletar cadastro", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Não") { dialog, id ->
                    dialog.cancel()
                }
            val alert = dialogBuilder.create()
            alert.setTitle("Confirmar exclusão")
            alert.show()
        }
    }
}
