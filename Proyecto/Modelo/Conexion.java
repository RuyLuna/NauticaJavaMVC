/*
Descripción: Clase para la conexión con la BD de la aplicación
Fecha: 26-junio-2021
Nombre: Rosa Angélica Rosales Camacho
 */
package Proyecto.Modelo;
//import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    private String usuario;
    private String contraseña;
    
    public Conexion() {
        this.usuario = "";
        this.contraseña = "";
    }

    public Conexion(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
    public Connection abrirConexion(String user, String cont) throws SQLException{
        Connection con;
        
        contraseña = cont;
        usuario = user;
        //Para conectarnos a nuestra base de datos
        try{
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nautica",usuario,contraseña); // Se establece la conexión
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion");         
            con = null;
        }            
        return con;
    }
    
    public void cerrarConexion(Connection c) throws SQLException{        
        try{
            if(!c.isClosed()){
                c.close();
            }
        }catch(SQLException e){
            System.out.println("Error al cerrar la conexión");
        }        
    }
    
    
}










