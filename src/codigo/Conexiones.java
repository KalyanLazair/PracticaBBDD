/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marta
 */
public class Conexiones {
    
    Connection conn= null;
    
    public Conexiones(){
        
        conn=null;
        
        try {
           String url1 = "jdbc:mysql://localhost:3306/discografica?serverTimezone=UTC";
           String user = "root";
           String password = "";
           //La clase DiverManager tiene un método getConnection que nos va a establecer la conexión
           //cuando le pasamos los parámetros de entrada url, user y password.
           conn = (Connection) DriverManager.getConnection(url1, user, password);
             if (conn != null) {
               System.out.println("Conectado a discográfica…");
             }
        } catch (SQLException ex) {
            System.out.println("ERROR: dirección o usuario/clave no válida");
            ex.printStackTrace();
        }
    
    }
    //Método para cerrar la conexión.
    public void cerrarConexion(){
       
        try{
            
         conn.close();
         
        }catch(SQLException ex){
           System.out.println("ERROR:al cerrar la conexión");
           ex.printStackTrace();
        }
    
    }
    //Método para alterar la tabla y crear un campo carátula.
    public int caratula(){
        
       try {
          // Crea un statement
          Statement sta = conn.createStatement();
          // Ejecuta la query de alter table para modificar la tabla
          sta.executeUpdate("ALTER TABLE album ADD caratula VARCHAR(120) DEFAULT NULL");
          // Cierra el statement
          sta.close();
          return 1;
       } catch (SQLException ex) {
          System.out.println("ERROR:al hacer un Alter Table");
          return -1;
       }
     
    }
    
    //Método para hacer una inserción de datos en la tabla.
     public int insertaCommit(int ID,String titulo, String autor, Short anno, String tituloC, String duracion, String letra) {
          try {
              //Ponemos el autoCommit a false para que no nos haga un commit después de cada inserción, que sólo
              //lo haga al final de la inserción correcta en ambas tablas.
             conn.setAutoCommit(false);
             //Creamos el objeto Statement con el que vamos a ejecutar las querys.
             Statement sta = conn.createStatement();
             //ejecutamos los dos insert.
             sta.executeUpdate("INSERT INTO album (ID,titulo,autor,publicacion) VALUES ("+ID+", '"+titulo+"', '"+autor+"', "+anno+")");
             sta.executeUpdate("INSERT INTO cancion (titulo, duracion, letras, album) VALUES ('"+tituloC+"','"+duracion+"','"+letra+"','"+ID+"')");
            //Hacemos un commit una vez ambas inserciones se han realizado correctamente.
             conn.commit();
             return 1;
         } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            //El rollback en el catch para que nos devuelva la BBDD a un estado consistente
           //en caso de error. Tiene que ir en todas las sentencias.
            try{
             if(conn!=null)
               conn.rollback();
            }catch(SQLException se2){
               se2.printStackTrace();
            }
            return -1;
         }
    }
     
     //Consultas parametrizadas
     
     public DefaultTableModel consultaAlbum(String consulta){
         //Creamos un objeto DefaultTableModel que nos va a permitir insertar los datos en el jTable.
         DefaultTableModel model = new DefaultTableModel(new String[]{"titulo", "autor", "publicacion"}, 0);
         
        try{
           //Creamos el statement para poder ejecutar la consulta.
           Statement sta = conn.createStatement();
           //Ejecutamos la consulta.
           ResultSet rs = sta.executeQuery(consulta);

           //recorremos la tabla
           
              while(rs.next()){
                String titulo=rs.getString("titulo");
                String autor=rs.getString("autor");
                String publicacion=String.valueOf(rs.getObject("publicacion"));
                //insertamos los datos en el model que nos va a devolver, creando una tupla.
                 model.addRow(new Object[]{titulo, autor, publicacion});
              }
            
        }catch(SQLException ex){
          System.out.println("ERROR:al consultar");
          ex.printStackTrace();
        }
        //Devolvemos el model.
        return model;
  
     }
     
     public DefaultTableModel consultaCanciones(String consulta){
         //Creamos un objeto DefaultTableModel que nos va a permitir insertar los datos en el jTable.
         DefaultTableModel model = new DefaultTableModel(new String[]{"titulo", "duracion", "letra","album"}, 0);
         
        try{
           //Creamos el statement para poder ejecutar la consulta.
           Statement sta = conn.createStatement();
           //Ejecutamos la consulta.
           ResultSet rs = sta.executeQuery(consulta);

           //recorremos la tabla   
              while(rs.next()){
                  String titulo=rs.getString("titulo");
                  String duracion=rs.getString("duracion");
                  String letra=rs.getString("letras");
                  String album=rs.getString("a.titulo");
                  //insertamos los datos en el model que nos va a devolver.
                  model.addRow(new Object[]{titulo, duracion, letra, album}); 

           }
            
        }catch(SQLException ex){
          System.out.println("ERROR:al consultar");
          ex.printStackTrace();
        }
        //Devolvemos el model.
        return model;
  
     }
     
     //Consultas con prepared statement
     
    
}
