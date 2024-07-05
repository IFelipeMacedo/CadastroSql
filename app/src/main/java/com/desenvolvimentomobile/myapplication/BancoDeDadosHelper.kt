package com.desenvolvimentomobile.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoDeDadosHelper(context: Context) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO_BANCO) {

    companion object {
        private const val NOME_BANCO = "cadastro.db"
        private const val VERSAO_BANCO = 1
        private const val TABELA_CADASTRO = "cadastro"
        private const val COLUNA_ID = "id"
        private const val COLUNA_NOME = "nome"
        private const val COLUNA_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val criarTabela = ("CREATE TABLE $TABELA_CADASTRO ("
                + "$COLUNA_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUNA_NOME TEXT, "
                + "$COLUNA_EMAIL TEXT)")
        db.execSQL(criarTabela)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABELA_CADASTRO")
        onCreate(db)
    }

    fun adicionarCadastro(nome: String, email: String): Long {
        val db = this.writableDatabase
        val valores = ContentValues()
        valores.put(COLUNA_NOME, nome)
        valores.put(COLUNA_EMAIL, email)
        return db.insert(TABELA_CADASTRO, null, valores)
    }

    fun listarCadastros(): List<Cadastro> {
        val cadastros = mutableListOf<Cadastro>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABELA_CADASTRO", null)
        if (cursor.moveToFirst()) {
            do {
                val cadastro = Cadastro(
                    cursor.getInt(cursor.getColumnIndex(COLUNA_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUNA_NOME)),
                    cursor.getString(cursor.getColumnIndex(COLUNA_EMAIL))
                )
                cadastros.add(cadastro)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return cadastros
    }
    fun buscarCadastroPorId(id: Int): Cadastro? {
        val db = this.readableDatabase
        var cadastro: Cadastro? = null
        val cursor = db.query(
            TABELA_CADASTRO,
            arrayOf(COLUNA_ID, COLUNA_NOME, COLUNA_EMAIL),
            "$COLUNA_ID = ?",
            arrayOf(id.toString()),
            null, null, null, null
        )
        cursor.use {
            if (it.moveToFirst()) {
                cadastro = Cadastro(
                    it.getInt(it.getColumnIndex(COLUNA_ID)),
                    it.getString(it.getColumnIndex(COLUNA_NOME)),
                    it.getString(it.getColumnIndex(COLUNA_EMAIL))
                )
            }
        }
        return cadastro
    }

    fun atualizarCadastro(id: Int, nome: String, email: String): Int {
        val db = this.writableDatabase
        val valores = ContentValues().apply {
            put(COLUNA_NOME, nome)
            put(COLUNA_EMAIL, email)
        }
        return db.update(TABELA_CADASTRO, valores, "$COLUNA_ID = ?", arrayOf(id.toString()))
    }



    fun deletarCadastro(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABELA_CADASTRO, "$COLUNA_ID = ?", arrayOf(id.toString()))
    }

}
