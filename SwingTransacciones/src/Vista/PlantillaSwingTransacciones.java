/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Estudiante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Irving
 */
public class PlantillaSwingTransacciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame ventana=new JFrame();//definimos la ventana
        JButton borrar=new JButton();//definimos los botones basicos a utilizar
        JButton crear=new JButton();
        JButton actualizar=new JButton();
        JLabel nombreText=new JLabel();//estos son los espacios de texto
        JLabel noexiste=new JLabel();
        JLabel calificacionText=new JLabel();
        JTextField nombreEnt=new JTextField();//estas son las entradas de texto
        JTextField calificacionEnt=new JTextField();
        borrar.setText("Borrar");//aqui ponemos el texto de los espacios de texto
        crear.setText("Crear");
        actualizar.setText("actualizar");
        nombreText.setText("Nombre:");
        calificacionText.setText("calificacion:");
        borrar.setBounds(640, 60, 80, 80);//el formato de definicion de espacio x, y, ancho y alto correspondientemente
        nombreText.setBounds(40, 0, 120, 40);
        nombreEnt.setBounds(120, 0, 120, 40);
        calificacionText.setBounds(260,0,120,40);
        calificacionEnt.setBounds(340, 0, 120, 40);
        crear.setBounds(460, 0, 120, 60);
        actualizar.setBounds(640, 180, 80, 80);
        
        
        String[] columnasTabla={"Id","Nombre","Calificacion"};//nombres de la columna
        DefaultTableModel modeloTabla=new DefaultTableModel(columnasTabla,0){//precioso
            @Override
            public boolean isCellEditable(int row, int column) {///en esta seccion hacemos que no se pueda editar la tabla directamente
            return false;
            }
        };//esto es un adaptador de datos a tabla
        JTable tabla=new JTable(modeloTabla);//creamos la tabla
        tabla.getTableHeader().setReorderingAllowed(false);//esto desactiva la manipulacion de los nombres de columna
        tabla.setBounds(60, 60, 540, 540);//coordenadas x e y,ancho y alto correspondientemente
        
        
        llenarTabla(modeloTabla);//llamamos al metodo llenar tabla para que rellene la tabla despues de haber hecho cambios
        
        
        
        borrar.addActionListener(new ActionListener(){//estas son las acciones que realizara el boton borrar
            @Override
            public void actionPerformed(ActionEvent e) {
                Estudiante borrar=new Estudiante();//se crea una instancia de estudiante se puede usar como parametro objeto y envio de datos a la bd al mismo tiempo
                int indiceColumna=0;//esto hace referencia a la columna id
                int fila=tabla.getSelectedRow();//esto al numero de orden de la fila seleccionada
                String valor=tabla.getModel().getValueAt(fila, indiceColumna).toString();//obtenemos el valor en la columna indiceCol.. y fila de la tabla y lo pasamos a string
                int exito=borrar.borrarEstudiante(Integer.valueOf(valor));//aqui borramos el estudiante
                llenarTabla(modeloTabla);//aqui llenamos la tabla con los nuevos cambios
            }
            
        });
        actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceColumna=0;//columna id
                int indiceColumna1=1;//columna nombre
                int indiceColumna2=2;//columna calificacion
                int fila=tabla.getSelectedRow();//fila seleccionada
                String valor=tabla.getModel().getValueAt(fila, indiceColumna).toString();//se obtiene el valor seleccionado de id
                String valor1=tabla.getModel().getValueAt(fila, indiceColumna1).toString();//"""" de nombre
                String valor2=tabla.getModel().getValueAt(fila, indiceColumna2).toString();//"""" de calificacion
                noexiste.setText(valor);//este es un espacio que no existe para almacenar datos
                nombreEnt.setText(valor1);//aqui tomamos el valor de la tabla,nombre seleccionado y lo ponemos en su entrada de nombre
                calificacionEnt.setText(valor2);//lo mismo para calificacion
                crear.setText("hecho");//esto es una bandera para saber que se va a actualizar con el boton crear
            }
            
        });
        
        crear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(crear.getText().equals("hecho")){//verificamos que este la bandera de actualizar
                    Estudiante actualizar=new Estudiante();//preparamos el manejador basico
                    if(nombreEnt.getText().length()>0){//verificamos que la entrada nombre no este vacia
                        actualizar.setNombre(nombreEnt.getText());//no estaba vacio por lo que pasa
                        try{//en caso de que la entrada calificacion no sea un entero manda error
                            int trato=Integer.valueOf(calificacionEnt.getText());//aqui es donde se detiene
                            if(trato>10){//si la calificacion es mayor a 10 la convierte a 10
                                trato=10;
                            }
                            actualizar.setCalificacion(trato);//despues de pasar ese manejo de errores calificacion pasa
                            actualizar.setId(Integer.valueOf(noexiste.getText()));//hacemos uso del almacen de datos que no existe para obtener la id
                            int exito=actualizar.actualizarEstudiante(actualizar);//y actualizamos
                            //if(exito>0){//si es mayor a 0,si actualizo
                                System.out.print("se actualizo el estudiante");
                                llenarTabla(modeloTabla);//refrescamos la tabla
                                nombreEnt.setText("");//dejamos todas las cosas como estaban
                                calificacionEnt.setText("");
                                crear.setText("Crear");
                            //}
                        }catch(Exception e33){//manejo de errores
                            System.out.println("La calificacion ingresada no es valida");
                            e33.printStackTrace();
                        }
                    }else{
                        System.out.println("No se ha ingresado el nombre");
                    }
                    
                }
                else{//esto es la seccion de crear
                Estudiante nuevo=new Estudiante();//realiza las mismas verificaciones que arriba
                if(nombreEnt.getText().length()>0){
                    nuevo.setNombre(nombreEnt.getText());
                    try{
                        int intento=Integer.valueOf(calificacionEnt.getText());
                        if(intento>10){
                                intento=10;
                        }
                        nuevo.setCalificacion(intento);
                        int exito=nuevo.insertarEstudiante(nuevo);//aqui inserta estudiante
                        System.out.print(exito);
                        //if(exito>0){
                            System.out.println("Se inserto el estudiante");
                            llenarTabla(modeloTabla);
                            nombreEnt.setText("");
                            calificacionEnt.setText("");
                        //}
                    }catch(Exception e2){//manejo de errores
                        nuevo.setCalificacion(0);
                        System.out.println("No se ingreso una calificacion valida");
                    }
                }else{
                    nuevo.setCalificacion(0);
                    System.out.println("No se ingreso nombre");
                }
                }
            }
            
        });
        
        
        JScrollPane sp=new JScrollPane(tabla);//este es un contenedor con una ventana desplazable arriba y abajo
        sp.setBounds(60, 60, 540, 540);//x y,ancho y altura, el tamaño de este debe ser igual o mayor a la tabla
        tabla.setVisible(true);//ponemos la tabla visible
        ventana.add(sp);//agregamos el contenedor de arriba que contiene la tabla
        ventana.add(borrar);
        ventana.add(nombreEnt);//agregamos los componentes que necesitamos
        ventana.add(nombreText);
        ventana.add(calificacionEnt);
        ventana.add(calificacionText);
        ventana.add(crear);      
        ventana.add(actualizar);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//esto permite que al hacer click en cerrar se detenga el programa
        ventana.setSize(780,720);//tamaño de la ventana
        ventana.setLayout(null);//sin layout, investigar para mas informacion,java swing layout
        ventana.setVisible(true);//ponemos la ventana visible
    }
    
    public static void llenarTabla(DefaultTableModel dt){
        dt.setRowCount(0);//esto va a dejar vacia la tabla
        ArrayList<Estudiante> datos=new ArrayList<>();//aqui se guardaran los estudiantes obtenidos
        Estudiante base=new Estudiante();//aqui se guardara el estudiante seleccionado
        base=base.getEstudiante(1);//aqui ponemos la id del estudiante que queremos
        System.out.println(" id: "+base.getId()+" nombre: "+base.getNombre()+" calificacion: "+base.getCalificacion());//y aqui lo vamos a mostrar
        datos=base.getEstudiantes();//aqui hacemos el pase a nuestra variable de todos los estudiantes
        for(Estudiante i:datos){//hacemos un recorrido tanto como para mostrarlos en terminal como para preparar un objeto para pasarlo en la tabla
            System.out.println(" id: "+i.getId()+" nombre: "+i.getNombre()+"calificacion: "+i.getCalificacion());//aqui se muestra
            Object[] temp={i.getId(),i.getNombre(),i.getCalificacion()};//aqui preparamos el objeto
            dt.addRow(temp);//y se lo pasamos al adaptador de datos
        }
    }
}