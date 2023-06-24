/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyecto.Modelo;

/**
 * Fecha: 26/06/2022
 * @author Pedro Antonio Sánchez Salas, Erick Jeanick Lopez Gonzales, Jesus de Israel Jimenez Tostado, Juan Carlos Moreno Lopez, Ruy Jesé Luna Sandoval
 * Descripcion: Archivo Modelo del proyecto
 */

import Proyecto.Controlador.Controlador;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;



    
public class ModeloBD {
    
    //Objeto para libro de excel
    Workbook wb;
    
    
   
    
    //Funcion para consultar los clientes
    public DefaultTableModel tablaConsulta(Connection con, String query){
         try{
             
            CallableStatement stmt = con.prepareCall(query);
            ResultSet rs = stmt.executeQuery();
                    
            return actualizarTabla(rs);
        }catch(SQLException e){
            DefaultTableModel dtm = null;
            dtm.setRowCount(0);
            dtm.setColumnCount(0);
            return  dtm;
        }
    }
    
    //Funcion para consultar el corte de caja de una fecha especifica 
    public DefaultTableModel corteCajaDia(String a, Connection con){
        try{
            
            String query = "{CALL corteCajaDia(?)}";
            CallableStatement stmt = con.prepareCall(query);
            
            stmt.setString(1, a);
            ResultSet rs = stmt.executeQuery();
            
            return actualizarTabla(rs);
        }catch(SQLException e){
            DefaultTableModel dtm = null;
            dtm.setRowCount(0);
            dtm.setColumnCount(0);
            return  dtm;
        }
    }
     
    // Funcion para consultar el corte de caja de un mes del año
    public DefaultTableModel corteCajaMes(int a, Connection con){
        try{
            
            
            String query = "{CALL corteCajaMes(?)}";
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, a);
            ResultSet rs = stmt.executeQuery();
            return actualizarTabla(rs);
        }catch(SQLException e){
            DefaultTableModel dtm = null;
            dtm.setRowCount(0);
            dtm.setColumnCount(0);
            return  dtm;
        }
    }
    
    // Funcion para ver el estado de cuenta de un cliente
    public DefaultTableModel verClienteEstado(String a, Connection con){
        try{
            String query = "{CALL verClienteEstado(?)}";
            CallableStatement stmt = con.prepareCall(query);
            stmt.setString(1, a);
            ResultSet rs = stmt.executeQuery();
            
            
            return actualizarTabla(rs);
        }catch(SQLException e){
            DefaultTableModel dtm = null;
            dtm.setRowCount(0);
            dtm.setColumnCount(0);
            return  dtm;
        }
    }
    
    
    
    
    
    public void editarCliente(Connection con, int id,String nombre, String correo, String telefono, boolean est){
        
        try{
            String query = "{CALL editarCliente(?,?,?,?,?)}";
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.setString(3,correo);
            stmt.setString(4,telefono);
            stmt.setBoolean(5, est);
            ResultSet rs = stmt.executeQuery();
            
        }catch(SQLException e){
        
        }   
    }
    
    public void borrarCliente(Connection con, int id){
        
        try{
            
            String query = "{CALL borrarCliente(?)}";
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, id);
            stmt.executeQuery();
            
        }catch(SQLException e){
            
        }  
    }
    public void borrarBarco(Connection con, int id){
        
        try{
            
            String query = "{CALL borrarBarco(?)}";
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, id);
            stmt.executeQuery();
            
        }catch(SQLException e){
            
        }  
    }
    public void borrarEmpleado(Connection con, int id){
        
        try{
            
            String query = "{CALL borrarEmpleado(?)}";
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, id);
            stmt.executeQuery();
            
        }catch(SQLException e){
            
        }  
    }
    
    
    // Funcion para actualizar el los datos del DefaultTableModel
    public DefaultTableModel actualizarTabla(ResultSet rs ){
        try{
            DefaultTableModel dtm = new DefaultTableModel();
            ResultSetMetaData rsMd =  rs.getMetaData();
            int columnas = rsMd.getColumnCount(); // regresa el número de columnas
            // ciclo para las columnas
            for(int i=1; i <= columnas; i++){  // sirve para obtener los nombres de cada columna (encabezado)
                dtm.addColumn(rsMd.getColumnLabel(i));
            }

            // ciclo para las filas
            while(rs.next()){
                Object[] fila = new Object[columnas];
                for(int i=0; i< columnas; i++){
                    fila[i] = rs.getObject(i+1);
                }
                dtm.addRow(fila);
            }

            return dtm;
        }catch(SQLException excepcion){
            DefaultTableModel dtm = null;
            dtm.setRowCount(0);
            dtm.setColumnCount(0);
            return  dtm;
        }
    }
    
    
    // Importar de excel
    public String Importar(File archivo, JTable tablaD){
        String respuesta="No se pudo realizar la importacion";
        
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        try{
            wb = WorkbookFactory.create(new FileInputStream(archivo));
            Sheet hoja = wb.getSheetAt(0);
            Iterator filaIterator = hoja.rowIterator();
            int indiceFila =-1;
            while (filaIterator.hasNext()){
                indiceFila++;
                Row fila = (Row) filaIterator.next();
                Iterator columnaIterator = fila.cellIterator();
                Object[] listaColumna = new Object[5];
                int indiceColumna=-1;
                while(columnaIterator.hasNext()){
                    indiceColumna++;
                    Cell celda = (Cell) columnaIterator.next();
                    if(indiceFila==0){
                        modeloT.addColumn(celda.getStringCellValue());
                    }else{
                        if(celda!=null){
                            switch(celda.getCellType()){
                                case Cell.CELL_TYPE_NUMERIC:
                                    listaColumna[indiceColumna]= (int)Math.round(celda.getNumericCellValue());
                                    break;
                                
                                case Cell.CELL_TYPE_STRING:
                                    listaColumna[indiceColumna] = celda.getStringCellValue();
                                    break;
                                    
                                case Cell.CELL_TYPE_BOOLEAN:
                                    listaColumna[indiceColumna] = celda.getBooleanCellValue();
                                    break;
                                    
                                default:
                                    listaColumna[indiceColumna] = celda.getDateCellValue();
                                    break;
                            }
                        }
                    }
                }
                if(indiceFila!=0)modeloT.addRow(listaColumna);
            }
            
            respuesta="Importacion exitosa";
        }catch(Exception e){
            
        }
        
        return respuesta;
    }
    
     
    
    public String Exportar(File archivo, JTable tablaD){
        String respuesta="No se realizao con exito la exportacion";
        int numFila=tablaD.getRowCount(), numColumna=tablaD.getColumnCount();
        
        if(archivo.getName().endsWith("xls")){
            wb = new HSSFWorkbook();
        }else{
            wb = new XSSFWorkbook();
        }
        
        Sheet hoja = wb.createSheet("Pruebita");
        
        try{
            for(int i = -1; i< numFila; i++){
                Row fila =hoja.createRow(i+1);
                for(int j =0; j<numColumna;j++){
                    Cell celda = fila.createCell(j);
                    if(i==-1){
                        celda.setCellValue(String.valueOf(tablaD.getColumnName(j)));
                    }else {
                        celda.setCellValue(String.valueOf(tablaD.getValueAt(i,j)));
                    }
                    wb.write(new FileOutputStream(archivo));
                }
            }
            respuesta= "Exportacion exitosa";
        }catch(Exception e){
            
        }
        
        return respuesta;
    }

    public class FondoPanel extends JPanel{
        public Image imagen;
        
        public void paint(Graphics g){
            imagen = new ImageIcon(getClass().getResource("/Imagenes/si.jpg")).getImage();
            
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            
            setOpaque(false);
            
            super.paint(g);
            
        }
        
    }
    
    public ResultSet llamarTabla(Connection con, String query){
         try{
            CallableStatement stmt = con.prepareCall(query);
            ResultSet rs = stmt.executeQuery();
            
            
            
            return rs;
         }catch(SQLException e){
            return null;
        }
    }
    
    
    public int numeroColumnas(ResultSet rs){
        
        try{
            
        ResultSetMetaData rsMd =  rs.getMetaData();
        int columnas = rsMd.getColumnCount();
        return columnas;
        }catch(SQLException excepcion){
           return 0; 
        }
        
    }
    
    public void datosTabla(String[] campo, String[] tipo,JTable tablaD ,ResultSet rs){
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        
        try{
            
            ResultSetMetaData rsMd =  rs.getMetaData();
            int columnas = rsMd.getColumnCount(); // regresa el número de columnas
            // ciclo para las columnas
            for(int i=0; i <campo.length; i++){  // sirve para obtener los nombres de cada columna (encabezado)
                campo[i] = rsMd.getColumnName(i+1);
                tipo[i] = rsMd.getColumnTypeName(i+1);
                modeloT.addColumn(rsMd.getColumnLabel(i+2));
                
            }
        }catch(SQLException e){
            
        }
        
    }
    
    
    public void añadirATabla(Object[] datos, JTable tablaD){
        
        DefaultTableModel modeloT = (DefaultTableModel)tablaD.getModel();
        modeloT.addRow(datos);
        tablaD.setModel(modeloT);
        
        
        
    }

    public String[] conseguirNombres(Connection con, String query){
         try{
             
            CallableStatement stmt = con.prepareCall(query);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            rs.last();
            String[] nombres = new String[rs.getRow()];
            rs.beforeFirst();
            int columnas = rsMd.getColumnCount();
            int a=0;
            while(rs.next()){
                String dato = "";
                for(int i=0; i< columnas; i++){
                    dato = rs.getObject(i+1).toString();
                }
                nombres[a] = dato;
                a++;
            }
            
            
            
            return nombres;
                    
            
        }catch(SQLException e){

            return null;
        }
    }
    
    public void insertarBarco(Connection con, String nombre, String cpt, int tam, int fk, String foto, String FechaSalidaEst){
        try{
            String query = "{CALL insertarBarco(?,?,?,?,?,?,?,?)}";
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, 0);
            stmt.setString(2, nombre);
            stmt.setString(3, cpt);
            stmt.setInt(4, tam);
            stmt.setBoolean(5, true);
            stmt.setInt(6, fk);
            stmt.setString(7, foto);
            stmt.setString(8, FechaSalidaEst);
            stmt.executeQuery();
            
            
        }catch(SQLException e){
        }
    }
    
    public void insertarCliente(Connection con, String nombre, String correo, String telefono){
        try{
            String query = "{CALL insertarCliente(?,?,?)}";
            CallableStatement stmt = con.prepareCall(query);
            stmt.setString(1, nombre);
            stmt.setString(2,correo);
            stmt.setString(3,telefono);
            stmt.executeQuery();
            
            
        }catch(SQLException e){
        }
    }
    
    
    
    public String[] buscarId(Connection con,String query, int id){
        try{
            
            CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            String[] datos = new String[rsMd.getColumnCount()];
            int columnas = rsMd.getColumnCount();
            // ciclo para las filas
            while(rs.next()){
                for(int i=0; i< columnas; i++){
                    datos[i] = rs.getString(i+1);
                }
            }
            return datos;
        }catch(SQLException e){
         return null;   
        }
    } 
    
    public String buscarClienteId(){
        String query = "{CALL buscarClienteId(?)}";
        return query;
    }
    public String buscarBarcoId(){
        String query = "{CALL buscarBarcoId(?)}";
        return query;
    }
    
    public String buscarEmpleadoId(){
        String query = "{CALL buscarEmpleadoeId(?)}";
        return query;
    }
    
    public String[] buscarNombre(Connection con, String query, String nombre){
        try{
            
            CallableStatement stmt = con.prepareCall(query);
            stmt.setString(1, nombre);

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            String[] datos = new String[rsMd.getColumnCount()];
            int columnas = rsMd.getColumnCount();
            // ciclo para las filas
            while(rs.next()){
                for(int i=0; i< columnas; i++){
                    datos[i] = rs.getString(i+1);

                }
            }
            return datos;
        }catch(SQLException e){
         return null;   
        }
    } 
    
    public String buscarClienteNombre(){
        String query = "{CALL buscarClienteNombre(?)}";
        return query;
    }
    
    public String buscarBarcoNombre(){
        String query = "{CALL buscarBarcoNombre(?)}";
        return query;
    }
    
    public String buscarEmpleadoNombre(){
        String query = "{CALL buscarEmpleadoNombre(?)}";
        return query;
    }
    
    
    public void editarMuelle(Connection con, int id, boolean disp, int tam, int fk){
        String query = "{CALL editarMuelle(?,?,?,?)}";
        
        try{
           CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, id); 
            stmt.setBoolean(2, disp);
            stmt.setInt(3, tam);
            stmt.setInt(4, fk);
            stmt.executeQuery();
            
            System.out.println("Si jala we");
            
        }catch(SQLException e){
            System.out.println("No jala we");
        }
        
    }
    
    public void editarDique(Connection con, int id, boolean disp, int tam, int fk){
        String query = "{CALL editarDique(?,?,?,?)}";
        
        try{
           CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, id); 
            stmt.setBoolean(2, disp);
            stmt.setInt(3, tam);
            stmt.setInt(4, fk);
            stmt.executeQuery();
            
            System.out.println("Si jala we");
            
        }catch(SQLException e){
            System.out.println("No jala we");
        }
        
    }
    
    public void editarCaseta(Connection con, int id, boolean disp, int tam, int fk){
        String query = "{CALL editarCaseta(?,?,?,?)}";
        
        try{
           CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, id); 
            stmt.setBoolean(2, disp);
            stmt.setInt(3, tam);
            stmt.setInt(4, fk);
            stmt.executeQuery();
            
            System.out.println("Si jala we");
            
        }catch(SQLException e){
            System.out.println("No jala we");
        }
        
    }

    public void editarBarco(Connection con,int id, String nmbr, String cpt, int tam, boolean estad, int fk, String foto, String fechaSalida){
        String query = "{CALL editarBarco(?,?,?,?,?,?,?,?)}";
        
        try{
           CallableStatement stmt = con.prepareCall(query);
            stmt.setInt(1, id); 
            stmt.setString(2, nmbr);
            stmt.setString(3, cpt);
            stmt.setInt(4, tam);
            stmt.setBoolean(5, estad);
            stmt.setInt(6, fk);
            stmt.setString(7, foto);
            stmt.setString(8, fechaSalida);
            stmt.executeQuery();
            
            System.out.println("Si jala we");
            
        }catch(SQLException e){
            System.out.println("No jala we");
        }
    }
  
    public String foraneoEstadoCuentaClientes(){
        String query = "{CALL foraneoEstadoCuentaClientes()}";
        return query;
    }
    
    public String foraneoEstadoCuentaFolioPago(){
        String query = "{CALL foraneoEstadoCuentaFolioPago()}";
        return query;
    }
    
    public String foraneoFolioPagoClientes(){
        String query = "{CALL foraneoFolioPagoClientes()}";
        return query;
    }
    
    public String foraneoCasetasClientes(){
        String query = "{CALL foraneoCasetasClientes()}";
        return query;
    }
    
    public String foraneoDiqueSecoBarco(){
        String query = "{CALL foraneoDiqueSecoBarco()}";
        return query;
    }
    public String foraneoMuelleBarco(){
        String query = "{CALL foraneoMuelleBarco()}";
        return query;
    }
     public String foraneoMuelleBarcoId(){
        String query = "{CALL foraneoMuelleBarcoId()}";
        return query;
    }
    
    public String foraneoBarcoClientes(){
        String query = "{CALL foraneoBarcoClientes()}";
        return query;
    }
    
    public String foraneoEntradaEmpleados(){
        String query = "{CALL foraneoEntradaEmpleados()}";
        return query;
    }
    
    public String foraneoEntradaBarco(){
        String query = "{CALL foraneoEntradaBarco()}";
        return query;
    }
    
    public String foraneoSalidaEmpleados(){
        String query = "{CALL foraneoSalidaEmpleados()}";
        return query;
    }
    
    public String foraneoSalidaBarco(){
        String query = "{CALL foraneoSalidaBarco()}";
        return query;
    }
    
    public String verClientes(){
        String query = "{CALL verClientes()}";
        return query;
    }
    
    // Funcion para ver el producto mas vendido
    public String productoMasVendido(){
        String query = "{CALL productoMasVendido(1)}";
        return query;
    }
    
    public String verBarco(){
        String query = "{CALL verBarco()}";
        return query;
    }
    public String verCasetas(){
        String query = "{CALL verCasetas()}";
        return query;
    }
    public String verDiqueseco(){
        String query = "{CALL verDiqueseco()}";
        return query;
    }
    public String  verEmpleados(){
        String query = "{CALL  verEmpleados()}";
        return query;
    }
    
    public String verMuelle(){
        String query = "{CALL  verMuelle()}";
        return query;
    }
    
    public String verEstadoCuenta(){
        String query = "{CALL  verEstadoCuenta()}";
        return query;
    }
    
    public String verFolioPago(){
        String query = "{CALL  verFolioPago()}";
        return query;
    }
    
    public String verEntrada(){
        String query = "{CALL  verEntrada()}";
        return query;
    }
    
     public String verSalida(){
        String query = "{CALL  verSalida()}";
        return query;
    }
    
    
    
    public String  foraneoCasetasClientesId(){
        String query = "{CALL  foraneoCasetasClientesId()}";
        return query;
    }
    
    public String nombreBarco(){
        String query = "{CALL  nombreBarco()}";
        return query;
    }
    
    public String nombreClientes(){
        String query = "{CALL  nombreClientes()}";
        return query;
    }
     
    public String nombreEmpleados(){
        String query = "{CALL  nombreEmpleados()}";
        return query;
    }
    
    public String idBarco(){
        String query = "{CALL  idBarco()}";
        return query;
    }
    
    public String idEmpleados(){
        String query = "{CALL  idEmpleados()}";
        return query;
    }
    
    public String idClientes(){
        String query = "{CALL  idClientes()}";
        return query;
    }
}


