package com.example.ladm_u3_p1_imagenetdb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    //context: El ACTIVITY que llamara la conexion
    //name: El nombre dek archivo de la base de datos
    //factory: Objeto cursor para navegar entre tplas resultado ACTUALMENTE OBSOLETO
    //Version:La version de la BD Id,NOMbre,Dom=  1version
    ///                 mas adelanta agregar EDAD=2Version
    override fun onCreate(db: SQLiteDatabase) {
        // AMBOS METODOS SIRVEN PARA CONSTRUIR LA ESTRUCTURA
        db.execSQL("Create table actividades(ID_Actividad INTEGER PRIMARY KEY AUTOINCREMENT,Descripcion varchar(200),fechaCaptura date,fechaEntrega date)")
        db.execSQL("Create table evidencias(ID_Evidencias INTEGER PRIMARY KEY AUTOINCREMENT,ID_Actividad INTEGER,foto BLOB,FOREIGN KEY(ID_Actividad) REFERENCES actividades(ID_Actividad)) ")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Tambien sirve para construir estructura de DB pero contcretamente actualizaciones
        //UPTADE= actualizacion menor =Camcion en datos almacenados
        //UPGRADE= Actualizacion mayor= Cambois en estrutura de tablas
    }
}