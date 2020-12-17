package com.example.ladm_u3_p1_imagenetdb

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var baseDatos= BaseDatos(this,"basedatos1", null,1)
    var listaID=ArrayList<String>()
    var id =""
    var idSeleccionadoEnLista=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAgregarEvidencias.setOnClickListener {
            insertar()



        }
    }

    private fun insertar() {
        try{
            /*
            1.- apertura de base de datos basados en modo LECTURA o ESCRITRA
            2.-Construccion de sentecia SQL
            3.-Ejecucion y mostrado de resultados
            * */
            var trans = baseDatos.writableDatabase//permite leer y escribir
            var variables = ContentValues()
//Descripcion varchar(200),fechaCaptura date,fechaEntrega date
            val sdf = SimpleDateFormat("dd/MM/yy")
            val netDate = Date(calvFechaCaptura.date)
            val netDate2 = Date(calvFechaEntrega.date)
            val dateCaptura = sdf.format(netDate)
            val dateEntrega = sdf.format(netDate2)
            variables.put("Descripcion",edtDescripcion.text.toString())
            variables.put("fechaCaptura",dateCaptura)
            variables.put("FechaEntrega",dateEntrega)

            var resp = trans.insert("actividades",null,variables)

            id= resp.toString()
            Toast.makeText(this,"ID "+id+"", Toast.LENGTH_SHORT).show()

            if(resp==-1L){
                mensaje("Error no se pudo insertar")
            }else{
                mensaje("Se Inserto Con Exito")
                limpiarCampo()
            }
            trans.close()
        }catch (e: SQLiteException){
            mensaje(e.message!!)
        }

        var intent = Intent(this,MainActivity2::class.java)
        intent.putExtra("id_actividades",id)
        startActivity(intent)
    }

    private fun mensaje(s: String) {
        AlertDialog.Builder(this)
                .setTitle("ATENCION")
                .setMessage(s)
                .setPositiveButton("OK"){d,i->d.dismiss()}
                .show()
    }

    private fun limpiarCampo() {
        edtDescripcion.setText("")

    }
}