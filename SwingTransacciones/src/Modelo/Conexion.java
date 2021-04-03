/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Irving
 */
public class Conexion {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  //Seleccion del driver,las nuevas versiones requieren cj
   static final String DB_URL = "jdbc:mysql://localhost/estudiantes";//es la ruta a la base de datos,estudiantes es el nombre de la bd

   //  Usuario y contraseña con el que se desea administrar la base de datos
   static final String USER = "root";
   static final String PASS = "pwd";//contraseña
   Connection con=null;//definimos la variable conexion, la cual se conectara a la base de datos
   
   public Conexion() {//cuando se hace una instacia de la clase conexion se hace:
   try{
      Class.forName(JDBC_DRIVER);//definimos el driver
      con = DriverManager.getConnection(DB_URL,USER,PASS);//realizamos la conexion
   }catch(Exception e){
       e.printStackTrace();//Manejo de errores durante la conexion
   }
   }
   public Connection getConexion(){
       return con;//se utiliza para obtener la conexion
   }
}
