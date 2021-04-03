/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Irving
 */
public class Estudiante {
     //aqui es todo el modelo del estudiante
    private int id;
    private String nombre;
    private int calificacion;
    //variables necesarias para la conexion de la bd
    Connection c=null;
    Conexion cm;
    
    public Estudiante(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    public Estudiante getEstudiante(int id){//es el select de un estudiante en especifico
        cm=new Conexion();
        Estudiante retorno=new Estudiante();
        c=cm.getConexion();//establecemos conexion
        ResultSet resultadoNoConvertido;
        String consulta="call seleccionarEstudiante(?)";//el simbolo de interrogacion hace referencia a un numero ordenado,aqui solo hace referencia a 1
        try{//manejo de errores
            PreparedStatement ps=c.prepareStatement(consulta);//esto es un asistente de elaboracion de consultas
            ps.setInt(1, id);//aqui vamos a sustituir ese simbolo de interrogacion con su numero referencia 1
            resultadoNoConvertido=ps.executeQuery();//aqui realizamos la consulta y la guardamos en resultset
            while(resultadoNoConvertido.next()){//el resultset requiere ser recorrido, de objeto a objeto
                retorno.setId(resultadoNoConvertido.getInt("id"));//guardamos en la instancia los valores obtenidos
                retorno.setNombre(resultadoNoConvertido.getString("nombre"));
                retorno.setCalificacion(resultadoNoConvertido.getInt("calificacion"));
            }
            c.close();//cerramos la conexion
            return retorno;//regresamos la instacia pero con los datos obtenidos de resultset
        }catch(Exception e){//manejo de errores
            System.out.println(e.getStackTrace());
            try{
                c.close();
            }catch(Exception e2){
                
            }
            return null;
        }
    }
    public ArrayList<Estudiante> getEstudiantes(){//es igual que la parte que solo obtiene un valor,aqui se obtienen todos los estudiantes
        cm=new Conexion();
        ArrayList<Estudiante> estudiantes=new ArrayList<>();
        c=cm.getConexion();
        ResultSet resultadoNoConvertido;
        String consulta="call seleccionarEstudiantes()";//esta parte cambia
        try{
            Statement s=c.createStatement();
            resultadoNoConvertido=s.executeQuery(consulta);//el statement solo sirve de contenedor para mandar la consulta
            while(resultadoNoConvertido.next()){//el recorrido con una instancia temporal
                Estudiante temp=new Estudiante();
                temp.setId(resultadoNoConvertido.getInt("id"));
                temp.setNombre(resultadoNoConvertido.getString("nombre"));
                temp.setCalificacion(resultadoNoConvertido.getInt("calificacion"));
                estudiantes.add(temp);//agregamos la instancia al arreglo que vamos a retornar
            }
            c.close();//cierre conexion
            return estudiantes;//retornamos el arreglo lleno con varias instancias temporales
        }catch(Exception e){//manejo de errores
            e.printStackTrace();
            try{
                c.close();
            }catch(Exception e2){
                
            }
            return null;
        }
    }
    
    public int borrarEstudiante(int id){//consulta para eliminar una celda, pide la id de la fila a borrar
        cm=new Conexion();
        c=cm.getConexion();
        String consultaBorrar="call borrarEstudiante(?)";
        try{
            PreparedStatement ps=c.prepareStatement(consultaBorrar);
            ps.setInt(1, id);
            int retorno=ps.executeUpdate();//en caso de que se borre uno solo regresa 1 en retorno
            c.close();
            return retorno;//regresa los datos de exito
        }catch(Exception e){//manejo de errores
            System.out.println(e.getMessage());
            try{
                c.close();
            }catch(Exception e2){
                
            }
            return 0;
        }
    }
    
    public int insertarEstudiante(Estudiante e){//consulta para la insercion de estudiantes
        cm=new Conexion();
        c=cm.getConexion();
        String consultaInsercion="call crearEstudiante(?,?)";//sentencia para la insercion
        try{
            PreparedStatement ps=c.prepareStatement(consultaInsercion);
            ps.setString(1, e.getNombre());//ponemos el nombre y calificacion de la id que ha sido transferida
            ps.setInt(2, e.getCalificacion());
            int retorno=ps.executeUpdate();//confirmacion de insercion como en el borrado
            c.close();
            return retorno;
        }catch(Exception exc){//manejo de errores
            exc.printStackTrace();
            try{
                c.close();
            }catch(Exception e2){
                
            }
            return 0;
        }
    }
    
    public int actualizarEstudiante(Estudiante e){// es como insert solo cambia la consulta y ciertas cuestiones del preparedstatement
        cm=new Conexion();
        c=cm.getConexion();
        String consultaActualizacion="call actualizarEstudiante(?,?,?)";
        try{
            PreparedStatement ps=c.prepareStatement(consultaActualizacion);
            ps.setString(2, e.getNombre());
            ps.setInt(3, e.getCalificacion());
            ps.setInt(1, e.getId());
            int retorno=ps.executeUpdate();
            c.close();
            return retorno;
        }catch(Exception e2){
            try{
                c.close();
                e2.printStackTrace();
            }catch(Exception e3){
                e3.printStackTrace();
            }
            return 0;
        }
    }
}
