
package Prueba;

import Proyecto.Controlador.*;
import Proyecto.Modelo.Conexion;
import Proyecto.Modelo.ModeloBD;
import Proyecto.Vista.Empleados;
import Proyecto.Vista.Login;
import Proyecto.Vista.MenuPrincipal;
import Proyecto.Vista.PanelBarcos;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import Proyecto.Vista.PanelPrincipal;
import Proyecto.Vista.PanelClientes;
import Proyecto.Vista.PanelTablaDiqueSeco;
import Proyecto.Vista.PanelTablaMuelles;
import Proyecto.Vista.VentanaBorrarCliente;
import Proyecto.Vista.VentanaFecha;
import Proyecto.Vista.VentanaFechaMes;
import Proyecto.Vista.VentanaNombre;
import Proyecto.Vista.VentanaNuevoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import Proyecto.Vista.VentanaBorrarCliente;
import Proyecto.Vista.VentanaFechaMes;
import Proyecto.Vista.VentanaEditarCliente;
import Proyecto.Vista.VentanaFecha;
import Proyecto.Vista.VentanaNombre;
import Proyecto.Vista.VentanaNuevoCliente;
import Proyecto.Vista.PanelTablaDiqueSeco;
import Proyecto.Vista.PanelEstadoCuenta;
import Proyecto.Vista.PanelFolio;
import Proyecto.Vista.PanelTablaCaseta;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import Proyecto.Vista.VentanaExcel;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Fecha: 26/06/2022
 * @author Pedro Antonio Sánchez Salas, Erick Jeanick Lopez Gonzales, Jesus de Israel Jimenez Tostado, Juan Carlos Moreno Lopez, Ruy Jesé Luna Sandoval
 * Descripcion: Archivo del Controlador del proyecto
 */
public class ControladorVentanaChida implements ActionListener{
    
    
    // Creamos los objetos de todos los archivos
    private ModeloBD modelo;
    private Login login;
    private MenuPrincipal menuPrincipal;
    private PanelBarcos panelBarcos;
    private PanelClientes clientes;
    private PanelEstadoCuenta panelEstadoCuenta;
    private PanelFolio panelFolio;
    private PanelPrincipal principal;
    private PanelTablaCaseta panelTablaCaseta;
    private PanelTablaDiqueSeco panelTablaDique;
    private PanelTablaMuelles panelTablaMuelles;
    private VentanaBorrarCliente ventanaBorrar;
    private VentanaEditarCliente ventanaEditar;
    private VentanaFecha ventanaFecha;
    private VentanaFechaMes ventanaFechaMes;
    private VentanaNombre ventanaNombre;
    private VentanaNuevoCliente ventanaNuevoCliente;
    private VentanaExcel ventanaExcel;
    private Empleados empleados;
    JFileChooser selectArchivo = new JFileChooser();
    File archivo;
    
    
    
    Conexion conexion = new Conexion();
    Connection con;
    int contadorAccion =0;
    
    
    
    
    

    //Constructor con los listeners
        public ControladorVentanaChida(ModeloBD modelo, Login login, MenuPrincipal menuPrincipal, PanelBarcos panelBarcos,
                PanelClientes clientes, PanelEstadoCuenta panelEstadoCuenta, PanelFolio panelFolio,
                PanelPrincipal principal, PanelTablaCaseta panelTablaCaseta, PanelTablaDiqueSeco panelTablaDique,
                PanelTablaMuelles panelTablaMuelles, VentanaBorrarCliente ventanaBorrar, VentanaEditarCliente ventanaEditar,
                VentanaFecha ventanaFecha, VentanaFechaMes ventanaFechaMes, VentanaNombre ventanaNombre,
                VentanaNuevoCliente ventanaNuevoCliente, VentanaExcel ventanaExcel, Empleados empleados) {    
            
        this.modelo = modelo;
        this.login = login;
        this.menuPrincipal = menuPrincipal;
        this.panelBarcos = panelBarcos;
        this.clientes = clientes;
        this.panelEstadoCuenta = panelEstadoCuenta;
        this.panelFolio = panelFolio;
        this.principal = principal;
        this.panelTablaCaseta = panelTablaCaseta;
        this.panelTablaDique = panelTablaDique;
        this.panelTablaMuelles = panelTablaMuelles;
        this.ventanaBorrar = ventanaBorrar;
        this.ventanaEditar = ventanaEditar;
        this.ventanaFecha = ventanaFecha;
        this.ventanaFechaMes = ventanaFechaMes;
        this.ventanaNombre = ventanaNombre;
        this.ventanaExcel = ventanaExcel;
        this.ventanaNuevoCliente = ventanaNuevoCliente;
        this.empleados = empleados;
        this.clientes.btnMostrar.addActionListener(this);
        this.clientes.btnNuevo.addActionListener(this);
        this.clientes.btnEditar.addActionListener(this);
        this.clientes.btnEliminar.addActionListener(this);
        this.clientes.btnExcelIE.addActionListener(this);
        this.clientes.btnInsertar.addActionListener(this);
        this.clientes.btnMostrar.addActionListener(this);
        this.clientes.btnNuevo.addActionListener(this);
        this.clientes.btnEditar.addActionListener(this);
        this.clientes.btnEliminar.addActionListener(this);
        this.ventanaFecha.btnBuscar.addActionListener(this);
        this.ventanaFechaMes.btnBuscar.addActionListener(this);
        this.menuPrincipal.menuTablasMuelle.addActionListener(this);
        this.menuPrincipal.menuTablasDique.addActionListener(this);
        this.menuPrincipal.menuTablaCasetas.addActionListener(this);
        this.menuPrincipal.menuTablasBarco.addActionListener(this);
        this.menuPrincipal.menuItemClientes.addActionListener(this);
        this.menuPrincipal.menuItemPrincipal.addActionListener(this);
        this.menuPrincipal.menuItemEstadoCuenta.addActionListener(this);
        this.menuPrincipal.menuItemFolio.addActionListener(this);
        this.login.btnEntrar.addActionListener(this);
        this.ventanaExcel.btnExportar.addActionListener(this);
        this.ventanaExcel.btnImportar.addActionListener(this);
        this.menuPrincipal.menuItemEmpleados.addActionListener(this);
        this.ventanaNombre.btnBuscar.addActionListener(this);
        this.ventanaBorrar.btnBorrar.addActionListener(this);
        this.ventanaBorrar.btnBuscarId.addActionListener(this);
        this.ventanaBorrar.btnBuscarNombre.addActionListener(this);
        
        
    }

    //Acciones realizadas cuando se pulsen los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Boton Entrar del login
        if(login.btnEntrar == e.getSource()){
            try {
                con = conexion.abrirConexion(login.txtUsuario.getText(),login.txtContraseña.getText());
            }catch(SQLException s){
            }
            if(con != null){
                
                
                principal.setSize(menuPrincipal.content.getSize());
                principal.setLocation(0,0);
                menuPrincipal.setVisible(true);
                login.setVisible(false);
                menuPrincipal.content.removeAll();
                menuPrincipal.content.add(principal);
                menuPrincipal.content.revalidate();
                menuPrincipal.content.repaint();
                
                String[] barcoCliente = modelo.conseguirNombres(con, "wa");
                
            }
        }
        
        
        
        if(menuPrincipal.menuItemEmpleados == e.getSource()){
            empleados.setSize(979, 473);
            empleados.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(empleados);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
            
//            this.setContentPane(fondoPanel);
            
        }
        
        // Boton de Importar/Exportar de excel
        if(clientes.btnExcelIE==e.getSource()){
            contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            
            
            ventanaExcel.setVisible(true);
            
        }
        
        if(ventanaExcel.btnImportar==e.getSource()){
            if(selectArchivo.showDialog(null, "Seleccionar Archivo")==JFileChooser.APPROVE_OPTION){
                archivo= selectArchivo.getSelectedFile();
                if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                    JOptionPane.showMessageDialog(null, modelo.Importar(archivo, ventanaExcel.tbDatos));
                }else{
                     JOptionPane.showMessageDialog(null, "Elija un formato valido");
                }
            }
        }
        
        if(ventanaExcel.btnExportar == e.getSource()){
            if(selectArchivo.showDialog(null, "Exportar")==JFileChooser.APPROVE_OPTION){
                archivo= selectArchivo.getSelectedFile();
                if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                    JOptionPane.showMessageDialog(null, modelo.Exportar(archivo, clientes.tbClientes));
                    
                }else{
                     JOptionPane.showMessageDialog(null, "Elija un formato valido");
                }
            }
        }
        
        
        if(ventanaBorrar.btnBuscarId == e.getSource()){
            
        }
        if(ventanaBorrar.btnBorrar == e.getSource()){
            
        }
        
        
        // Acciones del boton insertar global
        if(clientes.btnInsertar == e.getSource()){
            
            String[] campos = new String[modelo.numeroColumnas(modelo.llamarTabla(con,modelo.verCasetas()))];
            String[] tipos = new String[modelo.numeroColumnas(modelo.llamarTabla(con,modelo.verCasetas()))];
            Object[] datos = new Object[modelo.numeroColumnas(modelo.llamarTabla(con,modelo.verCasetas()))];
            boolean[] ints = new boolean[modelo.numeroColumnas(modelo.llamarTabla(con,modelo.verCasetas()))];
            AñadirRegistro añadirRegistro = new AñadirRegistro();
            modelo.datosTabla(campos,tipos,añadirRegistro.tbInserts,modelo.llamarTabla(con,modelo.verCasetas()));
            
            añadirRegistro.lblTitulo.setText("Casetas");
             try{   
                if(campos[1]!=null){
                    añadirRegistro.lblCampo1.setVisible(true);
                    añadirRegistro.lblCampo1.setText(campos[1]);

                    if(tipos[1]=="DATE" || tipos[1]=="VARCHAR"){
                        añadirRegistro.txtCampo1.setVisible(true);


                    }else if(tipos[1]=="TINYINT" || campos[1].startsWith("fk")){
                        añadirRegistro.cbCampo1.setVisible(true);
                        switch(añadirRegistro.lblCampo1.getText()){
                            case "fkEmpleados":
                                String[] salidaEmpleados = modelo.conseguirNombres(con, modelo.foraneoSalidaEmpleados());
                                añadirRegistro.lblCampo1.setText("Nombre del Barco");
                                for(int i = 0; i<salidaEmpleados.length;i++){
                                    añadirRegistro.cbCampo4.addItem(salidaEmpleados[i]);
                                }
                                break;
                            case "fkBarcos":
                                String[] salidaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo1.setText("Nombre del Barco");
                                for(int i = 0; i<salidaBarcos.length;i++){
                                    añadirRegistro.cbCampo1.addItem(salidaBarcos[i]);
                                }
                                break;
                            case "fkEmpleado":   
                                String[] entradaEmpleado = modelo.conseguirNombres(con, modelo.foraneoEntradaBarco());
                                añadirRegistro.lblCampo1.setText("Nombre del Barco");
                                for(int i = 0; i<entradaEmpleado.length;i++){
                                    añadirRegistro.cbCampo1.addItem(entradaEmpleado[i]);
                                }
                                break;
                            case "fkBarco":
                                String[] entradaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo1.setText("Nombre del Barco");
                                for(int i = 0; i<entradaBarcos.length;i++){
                                    añadirRegistro.cbCampo1.addItem(entradaBarcos[i]);
                                }
                                break;
                            case "fk_barcos":
                                String[] diqueSecoBarcos = modelo.conseguirNombres(con, modelo.foraneoDiqueSecoBarco());
                                añadirRegistro.lblCampo1.setText("Nombre del Barco");
                                for(int i = 0; i<diqueSecoBarcos.length;i++){
                                    añadirRegistro.cbCampo1.addItem(diqueSecoBarcos[i]);
                                }
                                break;
                            case "fk_Barco":
                                String[] muelleBarco = modelo.conseguirNombres(con, modelo.foraneoMuelleBarco());
                                añadirRegistro.lblCampo1.setText("Nombre del Barco");
                                for(int i = 0; i<muelleBarco.length;i++){
                                    añadirRegistro.cbCampo1.addItem(muelleBarco[i]);
                                }
                                break;
                            case "fk_Cliente":

                                if(añadirRegistro.lblTitulo.getText().equals("Casetas")){
                                    String[] casetasClientes = modelo.conseguirNombres(con, modelo.foraneoCasetasClientes());
                                    for(int i = 0; i<casetasClientes.length;i++){
                                        añadirRegistro.cbCampo1.addItem(casetasClientes[i]);
                                    }
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("FolioPago")){
                                    String[] folioPagoClientes = modelo.conseguirNombres(con, modelo.foraneoFolioPagoClientes());
                                    for(int i = 0; i<folioPagoClientes.length;i++){
                                        añadirRegistro.cbCampo1.addItem(folioPagoClientes[i]);
                                    }
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("EstadoCuenta")){
                                    String[] estadoCuentaClientes = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaClientes()); 
                                    for(int i = 0; i<estadoCuentaClientes.length;i++){
                                        añadirRegistro.cbCampo1.addItem(estadoCuentaClientes[i]);
                                    }
                                }
                                break; 
                            case "fk_IDFolios":
                                String[] foliosCliente = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaFolioPago());
                                añadirRegistro.lblCampo1.setText("Numero de folio");
                                for(int i = 0; i<foliosCliente.length;i++){
                                    añadirRegistro.cbCampo1.addItem(foliosCliente[i]);
                                }
                                break;
                            case "fk_IDCliente":
                                String[] barcoCliente = modelo.conseguirNombres(con, modelo.foraneoBarcoClientes());
                                añadirRegistro.lblCampo1.setText("Nombre Cliente");
                                for(int i = 0; i<barcoCliente.length;i++){
                                    añadirRegistro.cbCampo1.addItem(barcoCliente[i]);
                                }
                                break;
                            default:
                                añadirRegistro.cbCampo1.addItem("Disponible");
                                añadirRegistro.cbCampo1.addItem("No disponible");
                        }

                    }else if(tipos[1]=="INT" && !tipos[1].startsWith("fk")){
                       añadirRegistro.txtCampo1.setVisible(true);
                    }else{
                         añadirRegistro.lblCampo1.setVisible(false);
                    }
                }
                if(campos[2]!=null){

                    añadirRegistro.lblCampo2.setVisible(true);
                    añadirRegistro.lblCampo2.setText(campos[2]);
                    if(tipos[2]=="DATE" || tipos[2]=="VARCHAR"){
                        añadirRegistro.txtCampo2.setVisible(true);

                    }else if(tipos[2]=="TINYINT" || campos[2].startsWith("fk")){
                        añadirRegistro.cbCampo2.setVisible(true);
                        switch(añadirRegistro.lblCampo2.getText()){
                            case "fkEmpleados":
                                String[] salidaEmpleados = modelo.conseguirNombres(con, modelo.foraneoSalidaEmpleados());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<salidaEmpleados.length;i++){
                                    añadirRegistro.cbCampo2.addItem(salidaEmpleados[i]);
                                }
                                break;
                            case "fkBarcos":
                                String[] salidaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<salidaBarcos.length;i++){
                                    añadirRegistro.cbCampo2.addItem(salidaBarcos[i]);
                                }
                                break;
                            case "fkEmpleado":   
                                String[] entradaEmpleado = modelo.conseguirNombres(con, modelo.foraneoEntradaBarco());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<entradaEmpleado.length;i++){
                                    añadirRegistro.cbCampo2.addItem(entradaEmpleado[i]);
                                }
                                break;
                            case "fkBarco":
                                String[] entradaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<entradaBarcos.length;i++){
                                    añadirRegistro.cbCampo2.addItem(entradaBarcos[i]);
                                }
                                break;
                            case "fk_barcos":
                                String[] diqueSecoBarcos = modelo.conseguirNombres(con, modelo.foraneoDiqueSecoBarco());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<diqueSecoBarcos.length;i++){
                                    añadirRegistro.cbCampo2.addItem(diqueSecoBarcos[i]);
                                }
                                break;
                            case "fk_Barco":
                                String[] muelleBarco = modelo.conseguirNombres(con, modelo.foraneoMuelleBarco());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<muelleBarco.length;i++){
                                    añadirRegistro.cbCampo2.addItem(muelleBarco[i]);
                                }
                                break;
                            case "fk_Cliente":

                                if(añadirRegistro.lblTitulo.getText().equals("Casetas")){
                                    String[] casetasClientes = modelo.conseguirNombres(con, modelo.foraneoCasetasClientes());
                                    for(int i = 0; i<casetasClientes.length;i++){
                                        añadirRegistro.cbCampo2.addItem(casetasClientes[i]);
                                    }
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("FolioPago")){
                                    String[] folioPagoClientes = modelo.conseguirNombres(con, modelo.foraneoFolioPagoClientes());
                                    for(int i = 0; i<folioPagoClientes.length;i++){
                                        añadirRegistro.cbCampo2.addItem(folioPagoClientes[i]);
                                    }
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("EstadoCuenta")){
                                    String[] estadoCuentaClientes = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaClientes()); 
                                    for(int i = 0; i<estadoCuentaClientes.length;i++){
                                        añadirRegistro.cbCampo2.addItem(estadoCuentaClientes[i]);
                                    }
                                }

                                break; 
                            case "fk_IDFolios":
                                String[] foliosCliente = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaFolioPago());
                                añadirRegistro.lblCampo2.setText("Numero de folio");
                                for(int i = 0; i<foliosCliente.length;i++){
                                    añadirRegistro.cbCampo2.addItem(foliosCliente[i]);
                                }
                                break;
                            case "fk_IDCliente":
                                String[] barcoCliente = modelo.conseguirNombres(con, modelo.foraneoBarcoClientes());
                                añadirRegistro.lblCampo2.setText("Nombre Cliente");
                                for(int i = 0; i<barcoCliente.length;i++){
                                    añadirRegistro.cbCampo2.addItem(barcoCliente[i]);
                                }
                                break;
                        }

                    }else {
                        añadirRegistro.lblCampo2.setVisible(false);
                    }
                }    
                if(campos[3]!=null){

                    añadirRegistro.lblCampo3.setVisible(true);
                    añadirRegistro.lblCampo3.setText(campos[3]);
                    if(tipos[3]=="DATE" || tipos[3]=="VARCHAR"){
                        añadirRegistro.txtCampo3.setVisible(true);

                    }else if(tipos[3]=="TINYINT" || campos[3].startsWith("fk_")){
                        añadirRegistro.cbCampo3.setVisible(true);
                        switch(añadirRegistro.lblCampo3.getText()){
                            case "fkEmpleados":
                                String[] salidaEmpleados = modelo.conseguirNombres(con, modelo.foraneoSalidaEmpleados());
                                añadirRegistro.lblCampo3.setText("Nombre del Barco");
                                for(int i = 0; i<salidaEmpleados.length;i++){
                                    añadirRegistro.cbCampo3.addItem(salidaEmpleados[i]);
                                }
                                break;
                            case "fkBarcos":
                                String[] salidaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo3.setText("Nombre del Barco");
                                for(int i = 0; i<salidaBarcos.length;i++){
                                    añadirRegistro.cbCampo3.addItem(salidaBarcos[i]);
                                }
                                break;
                            case "fkEmpleado":   
                                String[] entradaEmpleado = modelo.conseguirNombres(con, modelo.foraneoEntradaBarco());
                                añadirRegistro.lblCampo3.setText("Nombre del Barco");
                                for(int i = 0; i<entradaEmpleado.length;i++){
                                    añadirRegistro.cbCampo3.addItem(entradaEmpleado[i]);
                                }
                                break;
                            case "fkBarco":
                                String[] entradaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo3.setText("Nombre del Barco");
                                for(int i = 0; i<entradaBarcos.length;i++){
                                    añadirRegistro.cbCampo3.addItem(entradaBarcos[i]);
                                }
                                break;
                            case "fk_barcos":
                                String[] diqueSecoBarcos = modelo.conseguirNombres(con, modelo.foraneoDiqueSecoBarco());
                                añadirRegistro.lblCampo3.setText("Nombre del Barco");
                                for(int i = 0; i<diqueSecoBarcos.length;i++){
                                    añadirRegistro.cbCampo3.addItem(diqueSecoBarcos[i]);
                                }
                                break;
                            case "fk_Barco":
                                String[] muelleBarco = modelo.conseguirNombres(con, modelo.foraneoMuelleBarco());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<muelleBarco.length;i++){
                                    añadirRegistro.cbCampo3.addItem(muelleBarco[i]);
                                }
                                break;
                            case "fk_Cliente":

                                if(añadirRegistro.lblTitulo.getText().equals("Casetas")){
                                    String[] casetasClientes = modelo.conseguirNombres(con, modelo.foraneoCasetasClientes());
                                    String[] casetasClientesId = modelo.conseguirNombres(con, modelo.foraneoCasetasClientesId());
                                    for(int i = 0; i<casetasClientes.length;i++){
                                        añadirRegistro.cbCampo3.addItem(casetasClientes[i]);
                                    }
                                    añadirRegistro.cbCampo3.getSelectedIndex();
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("FolioPago")){
                                    String[] folioPagoClientes = modelo.conseguirNombres(con, modelo.foraneoFolioPagoClientes());
                                    for(int i = 0; i<folioPagoClientes.length;i++){
                                        añadirRegistro.cbCampo3.addItem(folioPagoClientes[i]);
                                    }
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("EstadoCuenta")){
                                    String[] estadoCuentaClientes = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaClientes()); 
                                    for(int i = 0; i<estadoCuentaClientes.length;i++){
                                        añadirRegistro.cbCampo3.addItem(estadoCuentaClientes[i]);
                                    }
                                }

                                break; 
                            case "fk_IDFolios":
                                String[] foliosCliente = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaFolioPago());
                                añadirRegistro.lblCampo3.setText("Numero de folio");
                                for(int i = 0; i<foliosCliente.length;i++){
                                    añadirRegistro.cbCampo3.addItem(foliosCliente[i]);
                                }
                                break;
                            case "fk_IDCliente":
                                String[] barcoCliente = modelo.conseguirNombres(con, modelo.foraneoBarcoClientes());
                                añadirRegistro.lblCampo3.setText("Nombre Cliente");
                                for(int i = 0; i<barcoCliente.length;i++){
                                    añadirRegistro.cbCampo3.addItem(barcoCliente[i]);
                                }
                                break;
                                
                                
                            default:
                                añadirRegistro.cbCampo3.addItem("Disponible");
                                añadirRegistro.cbCampo3.addItem("No disponible");    
                        }

                    }else if(tipos[3]=="INT" &&  !campos[3].startsWith("fk_")   || tipos[3]=="DOUBLE"){
                        añadirRegistro.txtCampo3.setVisible(true);
                    }



                }else{
                    añadirRegistro.lblCampo3.setVisible(false);
                }
                    añadirRegistro.setVisible(true);
                if(campos[4]!=null){

                    añadirRegistro.lblCampo4.setVisible(true);
                    añadirRegistro.lblCampo4.setText(campos[4]);
                    if(tipos[4]=="DATE" || tipos[4]=="VARCHAR"){
                        añadirRegistro.txtCampo4.setVisible(true);

                    }else if(tipos[4]=="TINYINT" || campos[4].startsWith("fk_")){
                        añadirRegistro.cbCampo4.setVisible(true);
                        switch(añadirRegistro.lblCampo4.getText()){
                            case "fkEmpleados":
                                String[] salidaEmpleados = modelo.conseguirNombres(con, modelo.foraneoSalidaEmpleados());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<salidaEmpleados.length;i++){
                                    añadirRegistro.cbCampo4.addItem(salidaEmpleados[i]);
                                }
                                break;
                            case "fkBarcos":
                                String[] salidaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<salidaBarcos.length;i++){
                                    añadirRegistro.cbCampo4.addItem(salidaBarcos[i]);
                                }
                                break;
                            case "fkEmpleado":   
                                String[] entradaEmpleado = modelo.conseguirNombres(con, modelo.foraneoEntradaBarco());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<entradaEmpleado.length;i++){
                                    añadirRegistro.cbCampo4.addItem(entradaEmpleado[i]);
                                }
                                break;
                            case "fkBarco":
                                String[] entradaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<entradaBarcos.length;i++){
                                    añadirRegistro.cbCampo4.addItem(entradaBarcos[i]);
                                }
                                break;
                            case "fk_barcos":
                                String[] diqueSecoBarcos = modelo.conseguirNombres(con, modelo.foraneoDiqueSecoBarco());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<diqueSecoBarcos.length;i++){
                                    añadirRegistro.cbCampo4.addItem(diqueSecoBarcos[i]);
                                }
                                break;
                            case "fk_Barco":
                                String[] muelleBarco = modelo.conseguirNombres(con, modelo.foraneoMuelleBarco());
                                añadirRegistro.lblCampo4.setText("Nombre del Barco");
                                for(int i = 0; i<muelleBarco.length;i++){
                                    añadirRegistro.cbCampo4.addItem(muelleBarco[i]);
                                }
                                break;
                            case "fk_Cliente":

                                if(añadirRegistro.lblTitulo.getText().equals("Casetas")){
                                    String[] casetasClientes = modelo.conseguirNombres(con, modelo.foraneoCasetasClientes());
                                    for(int i = 0; i<casetasClientes.length;i++){
                                        añadirRegistro.cbCampo4.addItem(casetasClientes[i]);
                                    }
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("FolioPago")){
                                    String[] folioPagoClientes = modelo.conseguirNombres(con, modelo.foraneoFolioPagoClientes());
                                    for(int i = 0; i<folioPagoClientes.length;i++){
                                        añadirRegistro.cbCampo4.addItem(folioPagoClientes[i]);
                                    }
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("EstadoCuenta")){
                                    String[] estadoCuentaClientes = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaClientes()); 
                                    for(int i = 0; i<estadoCuentaClientes.length;i++){
                                        añadirRegistro.cbCampo4.addItem(estadoCuentaClientes[i]);
                                    }
                                }

                                break; 
                            case "fk_IDFolios":
                                String[] foliosCliente = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaFolioPago());
                                añadirRegistro.lblCampo4.setText("Numero de folio");
                                for(int i = 0; i<foliosCliente.length;i++){
                                    añadirRegistro.cbCampo4.addItem(foliosCliente[i]);
                                }
                                break;
                            case "fk_IDCliente":
                                String[] barcoCliente = modelo.conseguirNombres(con, modelo.foraneoBarcoClientes());
                                añadirRegistro.lblCampo4.setText("Nombre Cliente");
                                for(int i = 0; i<barcoCliente.length;i++){
                                    añadirRegistro.cbCampo4.addItem(barcoCliente[i]);
                                }
                                break;
                        }

                    }else if(tipos[4]=="INT"&&  !campos[4].startsWith("fk_")){
                        añadirRegistro.txtCampo4.setVisible(true);
                    }


                }else{
                    añadirRegistro.lblCampo4.setVisible(false);
                }
                if(campos[5]!=null){

                    añadirRegistro.lblCampo5.setText(campos[5]);
                    añadirRegistro.lblCampo5.setVisible(true);
                    if(tipos[5]=="DATE" || tipos[5]=="VARCHAR"){
                        añadirRegistro.txtCampo5.setVisible(true);

                    }else if(tipos[5]=="TINYINT" || campos[5].startsWith("fk_")){
                        añadirRegistro.cbCampo5.setVisible(true);
                        switch(añadirRegistro.lblCampo5.getText()){
                            case "fkEmpleados":
                                String[] salidaEmpleados = modelo.conseguirNombres(con, modelo.foraneoSalidaEmpleados());
                                añadirRegistro.lblCampo5.setText("Nombre del Barco");
                                for(int i = 0; i<salidaEmpleados.length;i++){
                                    añadirRegistro.cbCampo5.addItem(salidaEmpleados[i]);
                                }
                                break;
                            case "fkBarcos":
                                String[] salidaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo5.setText("Nombre del Barco");
                                for(int i = 0; i<salidaBarcos.length;i++){
                                    añadirRegistro.cbCampo5.addItem(salidaBarcos[i]);
                                }
                                break;
                            case "fkEmpleado":   
                                String[] entradaEmpleado = modelo.conseguirNombres(con, modelo.foraneoEntradaBarco());
                                añadirRegistro.lblCampo5.setText("Nombre del Barco");
                                for(int i = 0; i<entradaEmpleado.length;i++){
                                    añadirRegistro.cbCampo5.addItem(entradaEmpleado[i]);
                                }
                                break;
                            case "fkBarco":
                                String[] entradaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo5.setText("Nombre del Barco");
                                for(int i = 0; i<entradaBarcos.length;i++){
                                    añadirRegistro.cbCampo5.addItem(entradaBarcos[i]);
                                }
                                break;
                            case "fk_barcos":
                                String[] diqueSecoBarcos = modelo.conseguirNombres(con, modelo.foraneoDiqueSecoBarco());
                                añadirRegistro.lblCampo5.setText("Nombre del Barco");
                                for(int i = 0; i<diqueSecoBarcos.length;i++){
                                    añadirRegistro.cbCampo5.addItem(diqueSecoBarcos[i]);
                                }
                                break;
                            case "fk_Barco":
                                String[] muelleBarco = modelo.conseguirNombres(con, modelo.foraneoMuelleBarco());
                                añadirRegistro.lblCampo5.setText("Nombre del Barco");
                                for(int i = 0; i<muelleBarco.length;i++){
                                    añadirRegistro.cbCampo5.addItem(muelleBarco[i]);
                                }
                                break;
                            case "fk_Cliente":

                                if(añadirRegistro.lblTitulo.getText().equals("Casetas")){
                                    String[] casetasClientes = modelo.conseguirNombres(con, modelo.foraneoCasetasClientes());
                                    for(int i = 0; i<casetasClientes.length;i++){
                                        añadirRegistro.cbCampo5.addItem(casetasClientes[i]);
                                    }
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("FolioPago")){
                                    String[] folioPagoClientes = modelo.conseguirNombres(con, modelo.foraneoFolioPagoClientes());
                                    for(int i = 0; i<folioPagoClientes.length;i++){
                                        añadirRegistro.cbCampo5.addItem(folioPagoClientes[i]);
                                    }
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("EstadoCuenta")){
                                    String[] estadoCuentaClientes = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaClientes()); 
                                    for(int i = 0; i<estadoCuentaClientes.length;i++){
                                        añadirRegistro.cbCampo5.addItem(estadoCuentaClientes[i]);
                                    }
                                }

                                break; 
                            case "fk_IDFolios":
                                String[] foliosCliente = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaFolioPago());
                                añadirRegistro.lblCampo5.setText("Numero de folio");
                                for(int i = 0; i<foliosCliente.length;i++){
                                    añadirRegistro.cbCampo5.addItem(foliosCliente[i]);
                                }
                                break;
                            case "fk_IDCliente":
                                String[] barcoCliente = modelo.conseguirNombres(con, modelo.foraneoBarcoClientes());
                                añadirRegistro.lblCampo5.setText("Nombre Cliente");
                                for(int i = 0; i<barcoCliente.length;i++){
                                    añadirRegistro.cbCampo5.addItem(barcoCliente[i]);
                                }
                                break;
                        }

                    }else if(tipos[5]=="INT" && !campos[5].startsWith("fk_")){
                        añadirRegistro.txtCampo5.setVisible(true);
                    }

                }else{
                    añadirRegistro.lblCampo5.setVisible(false);
                }
                if(campos[6]!=null){

                    añadirRegistro.lblCampo6.setText(campos[6]);
                    añadirRegistro.lblCampo6.setVisible(true);
                    if(tipos[6]=="DATE" || tipos[6]=="VARCHAR"){
                        añadirRegistro.txtCampo6.setVisible(true);

                    }else if(tipos[6]=="TINYINT" || campos[6].startsWith("fk_")){
                        añadirRegistro.cbCampo6.setVisible(true);
                        switch(añadirRegistro.lblCampo6.getText()){
                            case "fkEmpleados":
                                String[] salidaEmpleados = modelo.conseguirNombres(con, modelo.foraneoSalidaEmpleados());
                                añadirRegistro.lblCampo6.setText("Nombre del Barco");
                                for(int i = 0; i<salidaEmpleados.length;i++){
                                    añadirRegistro.cbCampo6.addItem(salidaEmpleados[i]);
                                }
                                break;
                            case "fkBarcos":
                                String[] salidaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo6.setText("Nombre del Barco");
                                for(int i = 0; i<salidaBarcos.length;i++){
                                    añadirRegistro.cbCampo6.addItem(salidaBarcos[i]);
                                }
                                break;
                            case "fkEmpleado":   
                                String[] entradaEmpleado = modelo.conseguirNombres(con, modelo.foraneoEntradaBarco());
                                añadirRegistro.lblCampo6.setText("Nombre del Barco");
                                for(int i = 0; i<entradaEmpleado.length;i++){
                                    añadirRegistro.cbCampo6.addItem(entradaEmpleado[i]);
                                }
                                break;
                            case "fkBarco":
                                String[] entradaBarcos = modelo.conseguirNombres(con, modelo.foraneoSalidaBarco());
                                añadirRegistro.lblCampo6.setText("Nombre del Barco");
                                for(int i = 0; i<entradaBarcos.length;i++){
                                    añadirRegistro.cbCampo6.addItem(entradaBarcos[i]);
                                }
                                break;
                            case "fk_barcos":
                                String[] diqueSecoBarcos = modelo.conseguirNombres(con, modelo.foraneoDiqueSecoBarco());
                                añadirRegistro.lblCampo6.setText("Nombre del Barco");
                                for(int i = 0; i<diqueSecoBarcos.length;i++){
                                    añadirRegistro.cbCampo6.addItem(diqueSecoBarcos[i]);
                                }
                                break;
                            case "fk_Barco":
                                String[] muelleBarco = modelo.conseguirNombres(con, modelo.foraneoMuelleBarco());
                                añadirRegistro.lblCampo6.setText("Nombre del Barco");
                                for(int i = 0; i<muelleBarco.length;i++){
                                    añadirRegistro.cbCampo6.addItem(muelleBarco[i]);
                                }
                                break;
                            case "fk_Cliente":

                                if(añadirRegistro.lblTitulo.getText().equals("Casetas")){
                                    String[] casetasClientes = modelo.conseguirNombres(con, modelo.foraneoCasetasClientes());
                                    for(int i = 0; i<casetasClientes.length;i++){
                                        añadirRegistro.cbCampo6.addItem(casetasClientes[i]);
                                    }
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("FolioPago")){
                                    String[] folioPagoClientes = modelo.conseguirNombres(con, modelo.foraneoFolioPagoClientes());
                                    for(int i = 0; i<folioPagoClientes.length;i++){
                                        añadirRegistro.cbCampo6.addItem(folioPagoClientes[i]);
                                    }
                                }
                                if(añadirRegistro.lblTitulo.getText().equals("EstadoCuenta")){
                                    String[] estadoCuentaClientes = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaClientes()); 
                                    for(int i = 0; i<estadoCuentaClientes.length;i++){
                                        añadirRegistro.cbCampo6.addItem(estadoCuentaClientes[i]);
                                    }
                                }

                                break; 
                            case "fk_IDFolios":
                                String[] foliosCliente = modelo.conseguirNombres(con, modelo.foraneoEstadoCuentaFolioPago());
                                añadirRegistro.lblCampo6.setText("Numero de folio");
                                for(int i = 0; i<foliosCliente.length;i++){
                                    añadirRegistro.cbCampo6.addItem(foliosCliente[i]);
                                }
                                break;
                            case "fk_IDCliente":
                                String[] barcoCliente = modelo.conseguirNombres(con, modelo.foraneoBarcoClientes());
                                añadirRegistro.lblCampo6.setText("Nombre Cliente");
                                for(int i = 0; i<barcoCliente.length;i++){
                                    añadirRegistro.cbCampo6.addItem(barcoCliente[i]);
                                }
                                break;
                        }

                    }else if(tipos[6]=="INT" && !campos[6].startsWith("fk_") ||tipos[6]=="DOUBLE" ){
                        añadirRegistro.txtCampo6.setVisible(true);

                    }

                }else{
                    añadirRegistro.lblCampo6.setVisible(false);
                }
            }catch(Exception a){
            }        
             ActionListener oyenteDeAccion1 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(añadirRegistro.txtCampo1.isVisible()){
                        if(tipos[1]!="INT"){
                             datos[1] = Integer.parseInt(añadirRegistro.txtCampo1.getText());
                        }else {
                            datos[1] = añadirRegistro.txtCampo1.getText();
                        }
                        
                    }else if(añadirRegistro.cbCampo1.isVisible()){
                        
                    }
                    if(añadirRegistro.txtCampo2.isVisible()){
                        if(tipos[2]!="INT"){
                             datos[2] = Integer.parseInt(añadirRegistro.txtCampo2.getText());
                        }else {
                            datos[2] = añadirRegistro.txtCampo2.getText();
                        }
                    }else if(añadirRegistro.cbCampo2.isVisible()){
                        
                    }
                    if(añadirRegistro.txtCampo3.isVisible()){
                        if(tipos[3]!="INT"){
                             datos[3] = Integer.parseInt(añadirRegistro.txtCampo3.getText());
                        }else {
                            datos[3] = añadirRegistro.txtCampo3.getText();
                        }
                    }else if(añadirRegistro.cbCampo3.isVisible()){
                        
                    }
                    if(añadirRegistro.txtCampo4.isVisible()){
                        if(tipos[4]!="INT"){
                             datos[4] = Integer.parseInt(añadirRegistro.txtCampo4.getText());
                        }else {
                            datos[4] = añadirRegistro.txtCampo4.getText();
                        }
                    }else if(añadirRegistro.cbCampo4.isVisible()){
                        
                    }
                    if(añadirRegistro.txtCampo5.isVisible()){
                        if(tipos[5]!="INT"){
                             datos[5] = Integer.parseInt(añadirRegistro.txtCampo5.getText());
                        }else {
                            datos[5] = añadirRegistro.txtCampo5.getText();
                        }
                    }else if(añadirRegistro.cbCampo5.isVisible()){
                        
                    }
                    if(añadirRegistro.txtCampo6.isVisible()){
                        if(tipos[6]!="INT"){
                             datos[6] = Integer.parseInt(añadirRegistro.txtCampo6.getText());
                        }else {
                            datos[6] = añadirRegistro.txtCampo6.getText();
                        }
                    }else if(añadirRegistro.cbCampo6.isVisible()){
                        
                    }
                    modelo.añadirATabla(datos,añadirRegistro.tbInserts);
                }
            };
            añadirRegistro.btnGuardar.addActionListener(oyenteDeAccion1);
            
            ActionListener oyenteDeAccion2 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                }
            };
            añadirRegistro.btnQuitar.addActionListener(oyenteDeAccion2);
            ActionListener oyenteDeAccion3 = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { 
                    
                }
            };
            
            añadirRegistro.btnAgregar.addActionListener(oyenteDeAccion3);       
        }
            
        
        // Llamado al panel principal
        if(menuPrincipal.menuItemPrincipal == e.getSource()){
            
            principal.setSize(979, 473);
            principal.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(principal);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
        }
        
        
        
        //Listener del boton mostrar del panel principal
        if(clientes.btnMostrar == e.getSource()){
            
            //Opciones del dropdown del panel principal
            switch(clientes.dpPrincipal.getSelectedItem().toString()){
                case "Corte de caja por dia":
                    this.ventanaFecha.setVisible(true);
                    break;
                case "Corte de caja por mes":
                    this.ventanaFechaMes.setVisible(true);
                    break;
                case "Ver clientes y su estado de cuenta":
                    this.ventanaNombre.setVisible(true);
                    break;
                case "Ver clientes":
                    clientes.tbClientes.setModel(modelo.tablaConsulta(con,modelo.verClientes()));
                    
                    break;
                    
                case "Producto mas vendido":
                    clientes.tbClientes.setModel(modelo.tablaConsulta(con,modelo.productoMasVendido()));
                    break;
                    
                case "Insertar Cliente":
                    this.ventanaNuevoCliente.setVisible(true);
                    break;
                    
                case "Borrar Cliente":
                    this.ventanaBorrar.setVisible(true);
                    break;
            }
        }
        
        // Ventana de fecha
        if(ventanaFecha.btnBuscar == e.getSource()){
            clientes.tbClientes.setModel(modelo.corteCajaDia(ventanaFecha.txtFecha.getText(),con));
            ventanaFecha.txtFecha.setText("");
            this.ventanaFecha.setVisible(false);
            
        }
        // Ventana de mes
        if(ventanaFechaMes.btnBuscar == e.getSource()){
            clientes.tbClientes.setModel(modelo.corteCajaMes(Integer.parseInt(ventanaFechaMes.txtDia.getText()),con));
            this.ventanaFechaMes.setVisible(false);
            ventanaFechaMes.txtDia.setText("");
        }
        
        // Ventana por nombre
        if(ventanaNombre.btnBuscar == e.getSource()){
            clientes.tbClientes.setModel(modelo.verClienteEstado(ventanaNombre.txtNombre.getText(),con));
            this.ventanaNombre.setVisible(false);
            ventanaNombre.txtNombre.setText("");
        }
        
        
        // Llamado al panel de la tabla de muelle
        if(menuPrincipal.menuTablasMuelle == e.getSource()){
            
            
            panelTablaMuelles.setSize(979, 473);
            panelTablaMuelles.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(panelTablaMuelles, BorderLayout.CENTER);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
            
            
        }
        
        // Llamado al panel de la tabla de dique seco
        if(menuPrincipal.menuTablasDique== e.getSource()){
            
            panelTablaDique.setSize(979, 473);
            panelTablaDique.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(panelTablaDique, BorderLayout.CENTER);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
        }
        
        // Llamado al panel de la tabla de casetas
        if(menuPrincipal.menuTablaCasetas== e.getSource()){
            
            panelTablaCaseta.setSize(979, 473);
            panelTablaCaseta.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(panelTablaCaseta, BorderLayout.CENTER);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
        }
        
        // Llamado al panel de la tabla de barcos
        if(menuPrincipal.menuTablasBarco == e.getSource()){
            
            panelBarcos.setSize(979, 473);
            panelBarcos.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(panelBarcos, BorderLayout.CENTER);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
        }
        
        
        // Llamado al panel de clientes
        if(menuPrincipal.menuItemClientes == e.getSource()){
            
            clientes.setSize(979, 473);
            clientes.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(clientes, BorderLayout.CENTER);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
        }
        
        
        
        // Listener del boton mostrar del panel de clientes
        if(clientes.btnMostrar == e.getSource()){
            
            switch(clientes.dpPrincipal.getSelectedItem().toString()){
                
                 // Mostrar clientes
                case "CONSULTA DE CLIENTES":
                    clientes.tbClientes.setModel(modelo.tablaConsulta(con,modelo.verClientes()));
                    break;
                case "CONSULTA DE CLIENTE Y SU ESTADO DE CUENTA":
                    
                    break;
                case "CONSULTA DE CLIENTE CON ADEUDOS":
                    
                    break;
                case "CONSULTA DE CLIENTES SIN ADEUDOS":
                    
                    
                    break;
                    
                case "CONSULTA DE CLIENTES SIN BARCO":
                    
                    break;
                    
                case "CONSULTA DE CLIENTES CON BARCO":
                    
                    break;
            }
            
        }
        
        // Llamado al panel de estado de cuenta
        if(menuPrincipal.menuItemEstadoCuenta == e.getSource()){
            
            panelEstadoCuenta.setSize(979, 473);
            panelEstadoCuenta.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(panelEstadoCuenta, BorderLayout.CENTER);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
        }
        
        //Llamado al panel folio
        if(menuPrincipal.menuItemFolio == e.getSource()){
            
            panelFolio.setSize(979, 473);
            panelFolio.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(panelFolio, BorderLayout.CENTER);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
        }
        
        
    }
    
    public void agregarFiltro(){
        selectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
    }
   
    
}
