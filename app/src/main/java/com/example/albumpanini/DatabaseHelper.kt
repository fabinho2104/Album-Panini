package com.example.albumpanini

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {

    companion object {
        private const val DATABASE_NAME = "album_panini.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createTable = """
            CREATE TABLE laminas (
                numero INTEGER PRIMARY KEY,
                nombre TEXT,
                obtenida INTEGER,
                repetidas INTEGER
            )
        """.trimIndent()

        db.execSQL(createTable)

        Log.d("SQLITE", "Tabla laminas creada correctamente")
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        // Por ahora vacío
    }

    fun insertarLamina(lamina: Lamina): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put("numero", lamina.numero)
        values.put("nombre", lamina.nombre)
        values.put("obtenida", if (lamina.obtenida) 1 else 0)
        values.put("repetidas", lamina.repetidas)

        val resultado = db.insert(
            "laminas",
            null,
            values
        )

        db.close()

        return resultado != -1L
    }

    fun obtenerTodasLasLaminas(): MutableList<Lamina> {

        val lista = mutableListOf<Lamina>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM laminas",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                val numero = cursor.getInt(
                    cursor.getColumnIndexOrThrow("numero")
                )

                val nombre = cursor.getString(
                    cursor.getColumnIndexOrThrow("nombre")
                )

                val obtenida = cursor.getInt(
                    cursor.getColumnIndexOrThrow("obtenida")
                ) == 1

                val repetidas = cursor.getInt(
                    cursor.getColumnIndexOrThrow("repetidas")
                )

                lista.add(
                    Lamina(
                        numero,
                        nombre,
                        obtenida,
                        repetidas
                    )
                )

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return lista
    }

    fun buscarLaminaPorNumero(numeroBuscado: Int): Lamina? {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM laminas WHERE numero = ?",
            arrayOf(numeroBuscado.toString())
        )

        var lamina: Lamina? = null

        if (cursor.moveToFirst()) {

            val numero = cursor.getInt(
                cursor.getColumnIndexOrThrow("numero")
            )

            val nombre = cursor.getString(
                cursor.getColumnIndexOrThrow("nombre")
            )

            val obtenida = cursor.getInt(
                cursor.getColumnIndexOrThrow("obtenida")
            ) == 1

            val repetidas = cursor.getInt(
                cursor.getColumnIndexOrThrow("repetidas")
            )

            lamina = Lamina(
                numero,
                nombre,
                obtenida,
                repetidas
            )
        }



        cursor.close()
        db.close()

        return lamina
    }

    fun actualizarRepetidas(
        numero: Int,
        nuevasRepetidas: Int
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put(
            "repetidas",
            nuevasRepetidas
        )

        val filasAfectadas = db.update(
            "laminas",
            values,
            "numero = ?",
            arrayOf(numero.toString())
        )

        db.close()

        return filasAfectadas > 0
    }
}