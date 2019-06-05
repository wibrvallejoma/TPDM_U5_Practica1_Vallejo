package com.example.tpdm_u5_tarea1_vallejo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE HOROSCOPO(NOMBRE VARCHAR, DESCRIPCION VARCHAR)");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('ARIES','Te va a ir bien en la vida.')");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('TAURO','Esperanzas en se aproximan en el amor.')");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('GEMINIS','Ten cuidado con las malas lenguas.')");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('CANCER',' Podrás sentir más nerviosismo e impaciencia porque presientes que algo especial sucederá en este día.')");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('LEO','Llegan sorpresas con amores del pasado.')");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('VIRGO','Vivir situaciones distintas es lo que te movilizará a ti y a tu grupo de tus amistades')");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('LIBRA','Tienes mucha ansiedad por ver que todo cambie en tu ambiente profesional.')");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('ESCORPIO','Es un día en el cual debes tener la mente bien abierta para aceptar los cambios favorables que se te pueden presentar.')");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('SAGITARIO','Pueden llegarte noticias de un bono que ya no creías recibir.')");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('CAPRICORNIO','Urano y la Luna se unen en la casa de las relaciones personales de tu carta astral.')");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('ACUARIO','Desde hace tiempo atrás, tienes muchos deseos de independizarte y ansias de salir de la estructura laboral y de las ordenes de los jefes. ')");
        db.execSQL("INSERT INTO HOROSCOPO VALUES('PISCIS','Hoy podrás sentir en ti que llega toda la emoción en tu corazón con la aparición de nuevas personas a tu vida. ')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
