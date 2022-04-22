package com.example.ejer31anny.Configuraciones;

public class Transaccion {
    public static final String NAME_DATABASE = "DBEmpleados";

    public static final String NAME_TABLE = "Empleados";

    public static final String id_Empleado = "id";
    public static final String nombres_Empleado = "nombres";
    public static final String apellidos_Empleado= "apellidos";
    public static final String edad_Empleado = "edad";
    public static final String direccion_Empleado = "direccion";
    public static final String puesto_Empleado = "puesto";

    public static final String CREATE_TABLE_Empleados = "CREATE TABLE " + NAME_TABLE +
            "("+
            id_Empleado +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            nombres_Empleado +" TEXT, "+
            apellidos_Empleado +" TEXT, "+
            edad_Empleado +" TEXT, "+
            direccion_Empleado +" TEXT, "+
            puesto_Empleado +" TEXT"+
            ")";

    public static final String DROP_TABLE_Empleados = "DROP TABLE IF EXIST " + NAME_TABLE;
    public static final String SELECT_ALL_TABLE_Empleados = "SELECT * FROM " + NAME_TABLE;
}
