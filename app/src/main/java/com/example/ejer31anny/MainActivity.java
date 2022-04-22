package com.example.ejer31anny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ejer31anny.Configuraciones.InfoEmpleados;
import com.example.ejer31anny.Configuraciones.SQLiteConexion;
import com.example.ejer31anny.Configuraciones.Transaccion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnNuevoEmpleado;
    SQLiteConexion conexion;
    ListView listapersonas;
    ArrayList<InfoEmpleados> lista;
    ArrayList<String> ArregloPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//llenar la lista
        conexion=new SQLiteConexion(this, Transaccion.NAME_DATABASE, null, 1);
        listapersonas=(ListView) findViewById(R.id.listview);

        ObtenerListaEmpleados();

        ArrayAdapter adp = new ArrayAdapter( this, android.R.layout.simple_list_item_1, ArregloPersonas);
        listapersonas.setAdapter(adp);

        //doble click
        listapersonas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                InfoEmpleados mostrar = lista.get(i);

                Intent intent = new Intent(MainActivity.this, Activity_modificar.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("empleado", mostrar);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        // Para ir a agregar un nuevo empleado
        btnNuevoEmpleado = (Button) findViewById(R.id.btnAgreagarnuevo);
        btnNuevoEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity_agregar.class);
                startActivity(intent);
            }
        });

    }

    private void ObtenerListaEmpleados() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        InfoEmpleados list_personas = null;
        lista = new ArrayList<InfoEmpleados>();

        //cursor de base de dats : nos apoya a recorrer la informacion de la tabla a la cual consultamos//

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transaccion.NAME_TABLE, null);

        //Recorremos la informacion del cursor

        while (cursor.moveToNext()) {
            list_personas = new InfoEmpleados();
            list_personas.setId(cursor.getInt(0));
            // list_personas.setId(cursor.getString(0));
            list_personas.setNombres(cursor.getString(1));
            list_personas.setApellidos(cursor.getString(2));
            list_personas.setEdad(cursor.getString(3));
            list_personas.setDireccion(cursor.getString(4));
            list_personas.setPuesto(cursor.getString(5));
            // list_personas.setImagen(cursor.getBlob(5));
            lista.add(list_personas);
        }
        cursor.close();

        filllist();


    }

    private void filllist() {
        ArregloPersonas = new ArrayList<String>();

        for (int i = 0; i < lista.size(); i++) {
            ArregloPersonas.add(lista.get(i).getId() + " | "
                    + lista.get(i).getNombres() + " "
                    + lista.get(i).getApellidos() + " | "
                   /* + lista.get(i).getEdad() + " años |"*/
                    + lista.get(i).getDireccion() + " |"
                    + lista.get(i).getPuesto());
        }
    }
}