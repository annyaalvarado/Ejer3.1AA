package com.example.ejer31anny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejer31anny.Configuraciones.InfoEmpleados;
import com.example.ejer31anny.Configuraciones.SQLiteConexion;
import com.example.ejer31anny.Configuraciones.Transaccion;

public class Activity_modificar extends AppCompatActivity {
    Button btnAtras;
    SQLiteConexion conexion;
    TextView id;
    EditText nombres, apellidos, edad, direccion,puesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        conexion = new SQLiteConexion(this, Transaccion.NAME_DATABASE, null, 1);

        //Botones

        Button eliminar = (Button) findViewById(R.id.btnEliminar);
        Button actualizar = (Button) findViewById(R.id.btnSalvar);


        id = (TextView) findViewById(R.id.txtID);
        nombres = (EditText) findViewById(R.id.txtNombres);
        apellidos = (EditText) findViewById(R.id.txtApellidos);
        edad = (EditText) findViewById(R.id.txtEdad);
        direccion = (EditText) findViewById(R.id.txtDireccion);
        puesto = (EditText) findViewById(R.id.txtCargo);
        btnAtras = (Button) findViewById(R.id.btnVolver);


        Bundle obj = getIntent().getExtras();

        InfoEmpleados mostra = null;

        if (obj != null) {
            mostra = (InfoEmpleados) obj.getSerializable("empleado");

            id.setText(mostra.getId().toString());
            nombres.setText(mostra.getNombres().toString());
            apellidos.setText(mostra.getApellidos().toString());
            edad.setText(mostra.getEdad());
            direccion.setText(mostra.getDireccion().toString());
            puesto.setText(mostra.getPuesto().toString());
        }
        // volver a pagina principal
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nombres.getText().toString().isEmpty() && !apellidos.getText().toString().isEmpty() && !edad.getText().toString().isEmpty() && !direccion.getText().toString().isEmpty() ){
                    Actualizar();
                }
                else{
                    Toast.makeText(getApplicationContext()," Llenar los espacios vacios" ,Toast.LENGTH_LONG).show();

                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });

    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        String[] params = {id.getText().toString()};
        String wherecond = Transaccion.id_Empleado + "=?";
        db.delete(Transaccion.NAME_TABLE, wherecond, params);
        Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
        limpiar();
    }

    private void limpiar() {
        id.setText("");
        apellidos.setText("");
        nombres.setText("");
        direccion.setText("");
        edad.setText("");
        puesto.setText("");
    }

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] params = {id.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put(Transaccion.nombres_Empleado, nombres.getText().toString());
        valores.put(Transaccion.apellidos_Empleado, apellidos.getText().toString());
        valores.put(Transaccion.edad_Empleado, edad.getText().toString());
        valores.put(Transaccion.direccion_Empleado, direccion.getText().toString());
        valores.put(Transaccion.puesto_Empleado, puesto.getText().toString());
        db.update(Transaccion.NAME_TABLE, valores, Transaccion.id_Empleado + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato actualizado", Toast.LENGTH_LONG).show();
        limpiar();

    }
}