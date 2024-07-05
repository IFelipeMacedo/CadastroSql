package com.desenvolvimentomobile.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CadastroAdapter(context: Context, private val cadastros: List<Cadastro>) : ArrayAdapter<Cadastro>(context, 0, cadastros) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val cadastro = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_cadastro, parent, false)

        val textViewId = view.findViewById<TextView>(R.id.textViewId)
        val textViewNome = view.findViewById<TextView>(R.id.textViewNome)
        val textViewEmail = view.findViewById<TextView>(R.id.textViewEmail)

        textViewId.text = "ID: ${cadastro?.id}"
        textViewNome.text = "Nome: ${cadastro?.nome}"
        textViewEmail.text = "Email: ${cadastro?.email}"

        return view
    }
}
