package com.desenvolvimentomobile.myapplication

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListaCadastrosActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: CadastroAdapter
    private lateinit var dbHelper: BancoDeDadosHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_cadastros)

        listView = findViewById(R.id.listaCadastros)
        dbHelper = BancoDeDadosHelper(this)

        val cadastros = dbHelper.listarCadastros()
        adapter = CadastroAdapter(this, cadastros)
        listView.adapter = adapter
    }
}
