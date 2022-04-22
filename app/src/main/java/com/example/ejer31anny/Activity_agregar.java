package com.example.ejer31anny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejer31anny.Configuraciones.SQLiteConexion;
import com.example.ejer31anny.Configuraciones.Transaccion;

public class Activity_agregar extends AppCompatActivity {

    EditText nombre, apellidos, edad, direccion, puesto;
    Button agregar,btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        nombre = (EditText) findViewById(R.id.txtNombres);
        apellidos =(EditText) findViewById(R.id.txtApellidos);
        edad = (EditText) findViewById(R.id.txtEdad1);
        direccion = (EditText) findViewById(R.id.txtDireccion);

        puesto = (EditText) findViewById(R.id.txtCargo);

        agregar = (Button) findViewById(R.id.btnEliminar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!nombre.getText().toString().isEmpty() && !apellidos.getText().toString().isEmpty() && !edad.getText().toString().isEmpty() && !direccion.getText().toString().isEmpty()) {
                    AggEmpleado();

                }
                else {

                    Toast.makeText(getApplicationContext(),"Llene todos los datos ", Toast.LENGTH_LONG).show();
                }
            }

        });


        btnAtras = (Button) findViewById(R.id.btnLista);
        // volver a pagina principal
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void AggEmpleado() {

        SQLiteConexion conexion=new SQLiteConexion(this, Transaccion.NAME_DATABASE, null, 1);
        SQLiteDatabase db=conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transaccion.nombres_Empleado, nombre.getText().toString());
        valores.put(Transaccion.apellidos_Empleado, apellidos.getText().toString());
        valores.put(Transaccion.edad_Empleado, edad.getText().toString());
        valores.put(Transaccion.puesto_Empleado, puesto.getText().toString());
        valores.put(Transaccion.direccion_Empleado, direccion.getText().toString());


        Long resultado= db.insert(Transaccion.NAME_TABLE, Transaccion.id_Empleado, valores);
        Toast.makeText(getApplicationContext(),"Ingresado su Codigo : " + resultado.toString(),Toast.LENGTH_LONG).show();
        db.close();
        Limpiar();

    }

    private void Limpiar() {
        nombre.setText("");
        apellidos.setText("");
        edad.setText("");
        direccion.setText("");
        puesto.setText("");

    }
}