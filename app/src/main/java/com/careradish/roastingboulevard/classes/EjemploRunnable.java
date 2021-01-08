package com.careradish.roastingboulevard.classes;

import android.content.Context;
import android.widget.Toast;

import com.careradish.roastingboulevard.tools.FirebaseConnection;
import com.careradish.roastingboulevard.tools.FirebaseConnection;

public class EjemploRunnable implements Runnable {
    private String nombre = "";
    private Food comida;
    private Context contexto;

    public EjemploRunnable(String nombre) {
        this.nombre = nombre;
    }

    public EjemploRunnable(FirebaseConnection connection) {
        comida = connection.readFood(0);
    }

    public EjemploRunnable(Context contexto) {
        this.contexto = contexto;
    }

    public void mostratToast() {
        Toast.makeText(contexto, comida.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void run() {
        if (contexto!=null&&comida!=null)
        Toast.makeText(contexto, comida.toString(), Toast.LENGTH_LONG).show();
    }
}
