
package Proyecto.Controlador;

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
import Proyecto.Vista.VentanaBorrarBarco;
import Proyecto.Vista.VentanaBorrarEmpleado;
import Proyecto.Vista.VentanaEditarBarco;
import Proyecto.Vista.VentanaEditarCaseta;
import Proyecto.Vista.VentanaEditarDiqueSeco;
import Proyecto.Vista.VentanaEditarEmpleado;
import Proyecto.Vista.VentanaEditarEstadoCuenta;
import Proyecto.Vista.VentanaEditarFolioPago;
import Proyecto.Vista.VentanaEditarMuelle;
import Proyecto.Vista.PanelEntrada;
import Proyecto.Vista.PanelSalida;
import Proyecto.Vista.VentanaEditarEntrada;
import Proyecto.Vista.VentanaEditarSalida;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import Proyecto.Vista.VentanaExcel;
import Proyecto.Vista.VentanaNuevoBarco;
import Proyecto.Vista.VentanaNuevoEmpleado;
import Proyecto.Vista.VentanaNuevoEntrada;
import Proyecto.Vista.VentanaNuevoEstadoCuenta;
import Proyecto.Vista.VentanaNuevoFolioPago;
import Proyecto.Vista.VentanaNuevoSalida;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Fecha: 26/06/2022
 * @author Pedro Antonio Sánchez Salas, Erick Jeanick Lopez Gonzales, Jesus de Israel Jimenez Tostado, Juan Carlos Moreno Lopez, Ruy Jesé Luna Sandoval
 * Descripcion: Archivo del Controlador del proyecto
 */
public class Controlador implements ActionListener{
    
    
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
    private VentanaBorrarCliente ventanaBorrarCliente;
    private VentanaEditarCliente ventanaEditarCliente;
    private VentanaFecha ventanaFecha;
    private VentanaFechaMes ventanaFechaMes;
    private VentanaNombre ventanaNombre;
    private VentanaNuevoCliente ventanaNuevoCliente;
    private VentanaExcel ventanaExcel;
    private Empleados empleados;
    private PanelSalida panelSalida;
    private PanelEntrada panelEntrada;
    JFileChooser selectArchivo = new JFileChooser();
    File archivo;
    
    
    
    Conexion conexion = new Conexion();
    Connection con;
    int contadorAccion =0;
    
    
    
    
    

    //Constructor con los listeners
        public Controlador(ModeloBD modelo, Login login, MenuPrincipal menuPrincipal, PanelBarcos panelBarcos,
                PanelClientes clientes, PanelEstadoCuenta panelEstadoCuenta, PanelFolio panelFolio,
                PanelPrincipal principal, PanelTablaCaseta panelTablaCaseta, PanelTablaDiqueSeco panelTablaDique,
                PanelTablaMuelles panelTablaMuelles, VentanaBorrarCliente ventanaBorrarCliente, VentanaEditarCliente ventanaEditarCliente,
                VentanaFecha ventanaFecha, VentanaFechaMes ventanaFechaMes, VentanaNombre ventanaNombre,
                VentanaNuevoCliente ventanaNuevoCliente, VentanaExcel ventanaExcel, Empleados empleados, PanelEntrada panelEntrada, PanelSalida panelSalida) {    
            
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
        this.ventanaBorrarCliente = ventanaBorrarCliente;
        this.ventanaEditarCliente = ventanaEditarCliente;
        this.ventanaFecha = ventanaFecha;
        this.ventanaFechaMes = ventanaFechaMes;
        this.ventanaNombre = ventanaNombre;
        this.ventanaExcel = ventanaExcel;
        this.ventanaNuevoCliente = ventanaNuevoCliente;
        this.empleados = empleados;
        this.panelEntrada = panelEntrada;
        this.panelSalida = panelSalida;
        this.clientes.btnMostrar.addActionListener(this);
        this.clientes.btnNuevo.addActionListener(this);
        this.clientes.btnEditar.addActionListener(this);
        this.clientes.btnEliminar.addActionListener(this);
        this.clientes.btnExcelIE.addActionListener(this);
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
        this.menuPrincipal.menuItemEntrada.addActionListener(this);
        this.menuPrincipal.menuItemSalidas.addActionListener(this);
        this.login.btnEntrar.addActionListener(this);
        this.ventanaExcel.btnExportar.addActionListener(this);
        this.ventanaExcel.btnImportar.addActionListener(this);
        this.menuPrincipal.menuItemEmpleados.addActionListener(this);
        this.ventanaNombre.btnBuscar.addActionListener(this);
        this.ventanaBorrarCliente.btnBorrar.addActionListener(this);
        this.ventanaBorrarCliente.btnBuscarId.addActionListener(this);
        this.ventanaBorrarCliente.btnBuscarNombre.addActionListener(this);
        this.ventanaEditarCliente.btnBuscarId.addActionListener(this);
        this.ventanaEditarCliente.btnBuscarNombre.addActionListener(this);
        this.ventanaEditarCliente.btnGuardar.addActionListener(this);
        this.ventanaBorrarCliente.btnLimpiar.addActionListener(this);
        this.ventanaNuevoCliente.btnGuardar.addActionListener(this);
        this.panelTablaMuelles.btnEditar.addActionListener(this);
        this.panelTablaCaseta.btnEditar.addActionListener(this);
        this.panelTablaDique.btnEditar.addActionListener(this);
        this.panelTablaMuelles.btnMostrar.addActionListener(this);
        this.panelTablaDique.btnMostrar.addActionListener(this);
        this.panelTablaDique.btnExcelIE.addActionListener(this);
        this.panelTablaDique.btnEditar.addActionListener(this);
        this.panelTablaCaseta.btnMostrar.addActionListener(this);
        this.panelTablaCaseta.btnExcelIE.addActionListener(this);
        this.panelTablaCaseta.btnEditar.addActionListener(this);
        this.panelBarcos.btnExcelIE.addActionListener(this);
        this.panelBarcos.btnMostrar.addActionListener(this);
        this.panelBarcos.btnEditar.addActionListener(this);
        this.panelBarcos.btnNuevo.addActionListener(this);
        this.panelBarcos.btnEliminar.addActionListener(this);
        this.panelEstadoCuenta.btnEditar.addActionListener(this);
        this.panelEstadoCuenta.btnExcelIE.addActionListener(this);
        this.panelEstadoCuenta.btnNuevo.addActionListener(this);
        this.panelEstadoCuenta.btnMostrar.addActionListener(this);
        this.panelFolio.btnExcelIE.addActionListener(this);
        this.panelFolio.btnMostrar.addActionListener(this);
        this.panelFolio.btnNuevo.addActionListener(this);
        this.panelFolio.btnEditar.addActionListener(this);
        this.empleados.btnEditar.addActionListener(this);
        this.empleados.btnEliminar.addActionListener(this);
        this.empleados.btnNuevo.addActionListener(this);
        this.empleados.btnMostrar.addActionListener(this);
        this.panelEntrada.btnEditar.addActionListener(this);
        this.panelEntrada.btnMostrar.addActionListener(this);
        this.panelEntrada.btnNuevo.addActionListener(this);
        this.panelEntrada.btnExcelIE.addActionListener(this);
        this.panelSalida.btnEditar.addActionListener(this);
        this.panelSalida.btnMostrar.addActionListener(this);
        this.panelSalida.btnNuevo.addActionListener(this);
        this.panelSalida.btnExcelIE.addActionListener(this);
        
        
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
            }
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
        
        // Llamado al panel de clientes
        if(menuPrincipal.menuItemClientes == e.getSource()){
            
            clientes.setSize(979, 473);
            clientes.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(clientes, BorderLayout.CENTER);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
        }
        
        // Boton de Importar/Exportar de excel del panel de clientes
        if(clientes.btnExcelIE==e.getSource()){
            contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            VentanaExcel ventanaExcel = new VentanaExcel();
            ventanaExcel.setVisible(true);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
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
                        
                }
            };
            ventanaExcel.btnImportar.addActionListener(listener);
            ventanaExcel.btnExportar.addActionListener(listener);
            
        }
        
        // Boton de eliminar clientes
        if(clientes.btnEliminar == e.getSource()){
            this.ventanaBorrarCliente.setVisible(true);
        }
        
        // Boton de buscar cliente por id de la ventana de borrar cliente
        if(ventanaBorrarCliente.btnBuscarId == e.getSource()){
           String[] datos = modelo.buscarId(con,modelo.buscarClienteId(),Integer.parseInt(ventanaBorrarCliente.txtID.getText()));
           ventanaBorrarCliente.txtNombre.setText(datos[0]);
           ventanaBorrarCliente.txtCorreo.setText(datos[1]);
           ventanaBorrarCliente.txtTelefono.setText(datos[2]);
        }
        // Boton de buscar cliente por nombre de la ventana de borrar cliente
        if(ventanaBorrarCliente.btnBuscarNombre == e.getSource()){
           String[] datos = modelo.buscarNombre(con, modelo.buscarClienteNombre(), ventanaBorrarCliente.txtNombre.getText());
           ventanaBorrarCliente.txtID.setText(datos[0]);
           ventanaBorrarCliente.txtCorreo.setText(datos[1]);
           ventanaBorrarCliente.txtTelefono.setText(datos[2]);
        }
        
        
        // Boton de borrar cliente de la ventana de borrar cliente
        if(ventanaBorrarCliente.btnBorrar == e.getSource()){
            modelo.borrarCliente(con, Integer.parseInt(ventanaBorrarCliente.btnBuscarId.getText()));
            JOptionPane.showMessageDialog(null, "Cliente Borrado con exito");
            ventanaBorrarCliente.setVisible(false);
            ventanaBorrarCliente.txtID.setText("");
            ventanaBorrarCliente.txtNombre.setText("");
            ventanaBorrarCliente.txtCorreo.setText("");
            ventanaBorrarCliente.txtTelefono.setText("");
        }
        
        //Boton de limpiar de la ventana de borrar cliente
        if(ventanaBorrarCliente.btnLimpiar == e.getSource()){
           ventanaBorrarCliente.txtNombre.setText("");
           ventanaBorrarCliente.txtCorreo.setText("");
           ventanaBorrarCliente.txtTelefono.setText("");
        }
        
        // Boton de añadir nuevo cliente
        if(clientes.btnNuevo == e.getSource()){
            this.ventanaNuevoCliente.setVisible(true);
        }
        
        // Boton de guardar el nuevo cliente
        if(ventanaNuevoCliente.btnGuardar == e.getSource()){
            modelo.insertarCliente(con, ventanaNuevoCliente.txtNombre.getText(), ventanaNuevoCliente.txtCorreo.getText(), ventanaNuevoCliente.txtTelefono.getText());
            JOptionPane.showMessageDialog(null, "Cliente añadido con éxito");
            ventanaNuevoCliente.setVisible(false);
            ventanaNuevoCliente.txtNombre.setText("");
            ventanaNuevoCliente.txtCorreo.setText("");
            ventanaNuevoCliente.txtTelefono.setText("");
        }
        
        //Boton de editar Cliente
        if(clientes.btnEditar == e.getSource()){
            this.ventanaEditarCliente.setVisible(true);
        }
        
        // Boton de Buscar cliente por id en la ventada de editar cliente
        if(ventanaEditarCliente.btnBuscarId == e.getSource()){
            String[] datos = modelo.buscarId(con, modelo.buscarClienteId(), Integer.parseInt(ventanaEditarCliente.txtID.getText()));
            ventanaEditarCliente.txtNombre.setText(datos[0]);
            ventanaEditarCliente.txtCorreo.setText(datos[1]);
            ventanaEditarCliente.txtTelefono.setText(datos[2]);
        }
        
        // Boton de Buscar cliente por nombre en la ventada de editar cliente
        if(ventanaEditarCliente.btnBuscarNombre == e.getSource()){
            String[] datos = modelo.buscarNombre(con, modelo.buscarClienteNombre(), ventanaEditarCliente.txtNombre.getText());
            ventanaEditarCliente.txtID.setText(datos[0]);
            ventanaEditarCliente.txtCorreo.setText(datos[1]);
            ventanaEditarCliente.txtTelefono.setText(datos[2]);
        }
        
        //Boton de guardar de editar cliente
        if(ventanaEditarCliente.btnGuardar == e.getSource()){
            boolean est;
            if(ventanaEditarCliente.cbEstado.getSelectedItem() == "ACTIVO"){
                est = true;
            } else{
                est =false;
            }
            
            modelo.editarCliente(con,Integer.parseInt(ventanaEditarCliente.txtID.getText()), ventanaEditarCliente.txtNombre.getText(), ventanaEditarCliente.txtCorreo.getText(), ventanaEditarCliente.txtTelefono.getText(),est);
            JOptionPane.showMessageDialog(null, "Cliente editado con éxito");
            ventanaEditarCliente.setVisible(false);
            ventanaEditarCliente.txtID.setText("");
            ventanaEditarCliente.txtNombre.setText("");
            ventanaEditarCliente.txtCorreo.setText("");
            ventanaEditarCliente.txtTelefono.setText("");
        }
        
        
        
        //Listener del boton mostrar del panel de clientes
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
                    this.ventanaBorrarCliente.setVisible(true);
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
        
        // Boton de editar del panel de muelles
        
        if(panelTablaMuelles.btnEditar == e.getSource()){
            VentanaEditarMuelle ventanaEditarMuelle = new VentanaEditarMuelle();
            String[] barcos = modelo.conseguirNombres(con, modelo.nombreBarco());
            String[] ids = modelo.conseguirNombres(con, modelo.idBarco());

            for(int i=0; i<barcos.length;i++){
                ventanaEditarMuelle.cbBarco.addItem(barcos[i]);
            }
            
            
            ventanaEditarMuelle.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    if(ventanaEditarMuelle.btnGuardar==e.getSource()){
                        
                        boolean disp = true;
                        
                        if(ventanaEditarMuelle.cbDisponibilidad.getSelectedItem().equals("DISPONIBLE") ){
                            disp = true;
                        }else{
                            disp = false;
                        }
                        modelo.editarMuelle(con,ventanaEditarMuelle.cbLugar.getSelectedIndex()+1,disp,
                                Integer.parseInt(ventanaEditarMuelle.txtTamaño.getText()),Integer.parseInt(ids[ventanaEditarMuelle.cbBarco.getSelectedIndex()]));
                         JOptionPane.showMessageDialog(null, "Añadido con exito");
                         ventanaEditarMuelle.dispose();
                        
                    }
                    if(ventanaEditarMuelle.btnCancelar==e.getSource()){
                        ventanaEditarMuelle.dispose();
                    }
                    
                        
                    }
                };
                ventanaEditarMuelle.btnGuardar.addActionListener(listener);
                ventanaEditarMuelle.btnCancelar.addActionListener(listener);
            
        }
        
        
        // Boton de importar/exportar de excel del panel de muelles
        if(panelTablaMuelles.btnExcelIE==e.getSource()){
            contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            ventanaExcel.setVisible(true);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
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
                                JOptionPane.showMessageDialog(null, modelo.Exportar(archivo, panelTablaDique.tbDique));

                            }else{
                                 JOptionPane.showMessageDialog(null, "Elija un formato valido");
                            }
                        }
                    }
                        
                }
            };
            ventanaExcel.btnImportar.addActionListener(listener);
            ventanaExcel.btnExportar.addActionListener(listener);
        }
        
        // Boton de mostrar la tabla de muelles
        if(panelTablaMuelles.btnMostrar == e.getSource()){
            panelTablaMuelles.tbMuelles.setModel(modelo.tablaConsulta(con, modelo.verMuelle()));
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
        
 
        // Boton de excel del panel de dique seco
        if(panelTablaDique.btnExcelIE == e.getSource()){
           contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            VentanaExcel ventanaExcel = new VentanaExcel();
            ventanaExcel.setVisible(true);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
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
                        
                }
            };
            ventanaExcel.btnImportar.addActionListener(listener);
            ventanaExcel.btnExportar.addActionListener(listener); 
        }
        
        
        // Boton de editar del panel de dique seco
        if(panelTablaDique.btnEditar == e.getSource()){
            VentanaEditarDiqueSeco ventanaEditarDique = new VentanaEditarDiqueSeco();
            String[] barcos = modelo.conseguirNombres(con, modelo.nombreBarco());
            String[] ids = modelo.conseguirNombres(con, modelo.idBarco());

            for(int i=0; i<barcos.length;i++){
                ventanaEditarDique.cbBarco.addItem(barcos[i]);
            }
            for(int i=0; i<52; i++){
                ventanaEditarDique.cbLugar.addItem(Integer.toString(i+1));
            }
            
            
            ventanaEditarDique.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    if(ventanaEditarDique.btnGuardar==e.getSource()){
                        
                        boolean disp = true;
                        
                        if(ventanaEditarDique.cbDisponibilidad.getSelectedItem().equals("DISPONIBLE") ){
                            disp = true;
                        }else{
                            disp = false;
                        }
                        modelo.editarDique(con,ventanaEditarDique.cbLugar.getSelectedIndex()+1,disp,
                                Integer.parseInt(ventanaEditarDique.txtTamaño.getText()),Integer.parseInt(ids[ventanaEditarDique.cbBarco.getSelectedIndex()]));
                         JOptionPane.showMessageDialog(null, "Añadido con exito");
                         ventanaEditarDique.dispose();
                        
                    }
                    if(ventanaEditarDique.btnCancelar==e.getSource()){
                        ventanaEditarDique.dispose();
                    }
                    
                        
                    }
                };
                ventanaEditarDique.btnGuardar.addActionListener(listener);
                ventanaEditarDique.btnCancelar.addActionListener(listener);
            
        }
        
        
        // Boton de mostrar la tabla de dique seco
        if(panelTablaDique.btnMostrar == e.getSource()){
            panelTablaDique.tbDique.setModel(modelo.tablaConsulta(con, modelo.verDiqueseco()));
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
        
        
        // Boton de excel del panel de caseta
        if(panelTablaCaseta.btnExcelIE == e.getSource()){
           contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            VentanaExcel ventanaExcel = new VentanaExcel();
            ventanaExcel.setVisible(true);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
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
                                JOptionPane.showMessageDialog(null, modelo.Exportar(archivo, panelTablaCaseta.tbCaseta));

                            }else{
                                 JOptionPane.showMessageDialog(null, "Elija un formato valido");
                            }
                        }
                    }
                        
                }
            };
            ventanaExcel.btnImportar.addActionListener(listener);
            ventanaExcel.btnExportar.addActionListener(listener); 
        }
        
        
        // Boton de editar caseta
        if(panelTablaCaseta.btnEditar == e.getSource()){
            VentanaEditarCaseta ventanaEditarCaseta = new VentanaEditarCaseta();
           
            String[] clientes = modelo.conseguirNombres(con, modelo.nombreClientes());
            String[] ids = modelo.conseguirNombres(con, modelo.idBarco());

            for(int i=0; i<clientes.length;i++){
                ventanaEditarCaseta.cbCliente.addItem(clientes[i]);
            }
            for(int i=0; i<43; i++){
                ventanaEditarCaseta.cbLugar.addItem(Integer.toString(i+1));
            }
            
            
            ventanaEditarCaseta.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    if(ventanaEditarCaseta.btnGuardar==e.getSource()){
                        
                        boolean disp = true;
                        
                        if(ventanaEditarCaseta.cbDisponibilidad.getSelectedItem().equals("DISPONIBLE") ){
                            disp = true;
                        }else{
                            disp = false;
                        }
                        modelo.editarCaseta(con,ventanaEditarCaseta.cbLugar.getSelectedIndex()+1,disp,
                                Integer.parseInt(ventanaEditarCaseta.txtTamaño.getText()),Integer.parseInt(ids[ventanaEditarCaseta.cbCliente.getSelectedIndex()]));
                         JOptionPane.showMessageDialog(null, "Añadido con exito");
                         ventanaEditarCaseta.dispose();
                        
                    }
                    if(ventanaEditarCaseta.btnCancelar==e.getSource()){
                        ventanaEditarCaseta.dispose();
                    }
                    }
                };
               ventanaEditarCaseta.btnGuardar.addActionListener(listener);
               ventanaEditarCaseta.btnCancelar.addActionListener(listener);
        }
        
        
        // Boton de mostrar la tabla de caseta
        if(panelTablaCaseta.btnMostrar == e.getSource()){
            panelTablaCaseta.tbCaseta.setModel(modelo.tablaConsulta(con, modelo.verCasetas()));
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
        
        // Boton de editar el panel de barcos
        if(panelBarcos.btnEditar== e.getSource()){
            VentanaEditarBarco ventanaEditarBarco = new VentanaEditarBarco();
            String[] clientes = modelo.conseguirNombres(con, modelo.nombreClientes());
            String[] ids = modelo.conseguirNombres(con, modelo.idBarco());

            for(int i=0; i<clientes.length;i++){
                ventanaEditarBarco.cbCliente.addItem(clientes[i]);
            }
            
            
            ventanaEditarBarco.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    if(ventanaEditarBarco.btnBuscarID == e.getSource()){
                        String[] datos = modelo.buscarId(con,modelo.buscarBarcoId(),Integer.parseInt(ventanaEditarBarco.txtBarcoID.getText()));
                        
                        ventanaEditarBarco.txtNombre.setText(datos[0]);
                        ventanaEditarBarco.txtCapitan.setText(datos[1]);
                        ventanaEditarBarco.txtTamaño.setText(datos[2]);
                        ventanaEditarBarco.cbCliente.setSelectedIndex(Integer.parseInt(datos[3])-1);
                        ventanaEditarBarco.txtFoto.setText(datos[4]);
                        ventanaEditarBarco.txtFechaEstimadaSalida.setText(datos[5]);
                        
                    }
                    
                    if(ventanaEditarBarco.btnBuscarNombre == e.getSource()){
                    String[] datos = modelo.buscarNombre(con,modelo.buscarBarcoNombre(),ventanaEditarBarco.txtNombre.getText());
                    

                    ventanaEditarBarco.txtBarcoID.setText(datos[0]);
                    ventanaEditarBarco.txtCapitan.setText(datos[1]);
                    ventanaEditarBarco.txtTamaño.setText(datos[2]);
                    ventanaEditarBarco.cbCliente.setSelectedIndex(Integer.parseInt(datos[3])-1);
                    ventanaEditarBarco.txtFoto.setText(datos[4]);
                    ventanaEditarBarco.txtFechaEstimadaSalida.setText(datos[5]);
                        
                    }
                    
                    
                    if(ventanaEditarBarco.btnGuardar==e.getSource()){
                        boolean est;
                        
                        if(ventanaEditarBarco.cbEstado.toString() == "ACTIVO"){
                            est = true;
                        }else{
                            est =false;
                        }
                        
                        modelo.editarBarco(con, Integer.parseInt(ventanaEditarBarco.txtBarcoID.getText()), ventanaEditarBarco.txtNombre.getText(),
                                ventanaEditarBarco.txtCapitan.getText(),Integer.parseInt(ventanaEditarBarco.txtTamaño.getText()),
                                est, Integer.parseInt(ids[ventanaEditarBarco.cbCliente.getSelectedIndex()]),ventanaEditarBarco.txtFoto.getText() ,ventanaEditarBarco.txtFechaEstimadaSalida.getText());
                        JOptionPane.showMessageDialog(null, "Editado con exito");
                        ventanaEditarBarco.dispose();
                        
                    }
                    if(ventanaEditarBarco.btnCancelar==e.getSource()){
                        ventanaEditarBarco.dispose();
                    }
                    
                        
                    }
                };
                ventanaEditarBarco.btnGuardar.addActionListener(listener);
                ventanaEditarBarco.btnCancelar.addActionListener(listener);
                ventanaEditarBarco.btnBuscarNombre.addActionListener(listener);
                ventanaEditarBarco.btnBuscarID.addActionListener(listener);
        }
        
        // Boton de añadir barco
        if(panelBarcos.btnNuevo == e.getSource()){
            VentanaNuevoBarco ventanaNuevoBarco = new VentanaNuevoBarco();
            String[] clientes = modelo.conseguirNombres(con, modelo.nombreClientes());
            String[] ids = modelo.conseguirNombres(con, modelo.idBarco());

            for(int i=0; i<clientes.length;i++){
                ventanaNuevoBarco.cbCliente.addItem(clientes[i]);
            }
            
            
            ventanaNuevoBarco.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    
                    if(ventanaNuevoBarco.btnGuardar==e.getSource()){
                        modelo.insertarBarco(con, ventanaNuevoBarco.txtNombre.getText(), 
                                ventanaNuevoBarco.txtCapitan.getText(), Integer.parseInt(ventanaNuevoBarco.txtTamaño.getText()),
                                Integer.parseInt(ids[ventanaNuevoBarco.cbCliente.getSelectedIndex()]),ventanaNuevoBarco.txtFoto.getText() , ventanaNuevoBarco.txtFechaEstimadaSalida.getText());
                        JOptionPane.showMessageDialog(null, "Añadido con exito");
                        ventanaNuevoBarco.dispose();
                        
                        
                    }
                    if(ventanaNuevoBarco.btnCancelar==e.getSource()){
                        ventanaNuevoBarco.dispose();
                    }
                    
                        
                    }
                };
                ventanaNuevoBarco.btnGuardar.addActionListener(listener);
                ventanaNuevoBarco.btnCancelar.addActionListener(listener);

        }
        
        // Boton de eliminar barco
        if(panelBarcos.btnEliminar == e.getSource()){
            
            VentanaBorrarBarco ventanaBorrarBarco = new VentanaBorrarBarco();
            String[] clientes = modelo.conseguirNombres(con, modelo.nombreClientes());
            String[] ids = modelo.conseguirNombres(con, modelo.idBarco());

            for(int i=0; i<clientes.length;i++){
                ventanaBorrarBarco.cbCliente.addItem(clientes[i]);
            }
            
            
            ventanaBorrarBarco.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    if(ventanaBorrarBarco.btnBuscarID == e.getSource()){
                        
                        String[] datos = modelo.buscarId(con,modelo.buscarBarcoId(),Integer.parseInt(ventanaBorrarBarco.txtBarcoID.getText()));
                        
                        ventanaBorrarBarco.txtNombre.setText(datos[0]);
                        ventanaBorrarBarco.txtCapitan.setText(datos[1]);
                        ventanaBorrarBarco.txtTamaño.setText(datos[2]);
                        ventanaBorrarBarco.cbCliente.setSelectedIndex(Integer.parseInt(datos[3])-1);
                        ventanaBorrarBarco.txtFoto.setText(datos[4]);
                        ventanaBorrarBarco.txtFechaEstimadaSalida.setText(datos[5]);
                        
                        
                    }
                    
                    if(ventanaBorrarBarco.btnBuscarNombre == e.getSource()){
                        String[] datos = modelo.buscarNombre(con,modelo.buscarBarcoNombre(),ventanaBorrarBarco.txtNombre.getText());
                        
                        ventanaBorrarBarco.txtBarcoID.setText(datos[0]);
                        ventanaBorrarBarco.txtCapitan.setText(datos[1]);
                        ventanaBorrarBarco.txtTamaño.setText(datos[2]);
                        ventanaBorrarBarco.cbCliente.setSelectedIndex(Integer.parseInt(datos[3])-1);
                        ventanaBorrarBarco.txtFoto.setText(datos[4]);
                        ventanaBorrarBarco.txtFechaEstimadaSalida.setText(datos[5]);
                    }
                    
                    
                    if(ventanaBorrarBarco.btnGuardar==e.getSource()){
                        modelo.borrarBarco(con, Integer.parseInt(ventanaBorrarBarco.txtBarcoID.getText()));
                        
                        JOptionPane.showMessageDialog(null, "Eliminado con exito");
                        ventanaBorrarBarco.dispose();
                        
                        
                    }
                    if(ventanaBorrarBarco.btnCancelar==e.getSource()){
                        ventanaBorrarBarco.dispose();
                    }
                    
                        
                    }
                };
                ventanaBorrarBarco.btnGuardar.addActionListener(listener);
                ventanaBorrarBarco.btnCancelar.addActionListener(listener);
                ventanaBorrarBarco.btnBuscarNombre.addActionListener(listener);
                ventanaBorrarBarco.btnBuscarID.addActionListener(listener);
            
        }
        
        
        // Boton de excel del panel de barcos
        if(panelBarcos.btnExcelIE==e.getSource()){
            contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            ventanaExcel.setVisible(true);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
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
                                JOptionPane.showMessageDialog(null, modelo.Exportar(archivo, panelBarcos.tbBarcos));

                            }else{
                                 JOptionPane.showMessageDialog(null, "Elija un formato valido");
                            }
                        }
                    }
                        
                }
            };
            ventanaExcel.btnImportar.addActionListener(listener);
            ventanaExcel.btnExportar.addActionListener(listener);
        }
        
        // Boton de mostrar la tabla de barcos
        
        if(panelBarcos.btnMostrar == e.getSource()){
            panelBarcos.tbBarcos.setModel(modelo.tablaConsulta(con, modelo.verBarco()));
        }
        
        if(panelTablaDique.btnExcelIE == e.getSource()){
           contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            VentanaExcel ventanaExcel = new VentanaExcel();
            ventanaExcel.setVisible(true);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
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
                        
                }
            };
            ventanaExcel.btnImportar.addActionListener(listener);
            ventanaExcel.btnExportar.addActionListener(listener); 
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
        
        // Boton de editar el panel de estado de cuenta
        if(panelEstadoCuenta.btnEditar== e.getSource()){
            VentanaEditarEstadoCuenta ventanaEditarEstadoCuenta = new VentanaEditarEstadoCuenta();
            String[] clientes = modelo.conseguirNombres(con, modelo.nombreClientes());
            String[] ids = modelo.conseguirNombres(con, modelo.idBarco());

            for(int i=0; i<clientes.length;i++){
                ventanaEditarEstadoCuenta.cbCliente.addItem(clientes[i]);
            }
            
            
            ventanaEditarEstadoCuenta.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    if(ventanaEditarEstadoCuenta.btnBuscar == e.getSource()){
//                        String[] datos = modelo.buscarClienteId(con,Integer.parseInt(ventanaEditarEstadoCuenta.txtEstadoCuentaID.getText()));
                        
                        
                        
                    }
                    
                    
                    if(ventanaEditarEstadoCuenta.btnGuardar==e.getSource()){
                        
                        
                        
                    }
                    if(ventanaEditarEstadoCuenta.btnCancelar==e.getSource()){
                        ventanaEditarEstadoCuenta.dispose();
                    }
                    
                        
                    }
                };
                ventanaEditarEstadoCuenta.btnGuardar.addActionListener(listener);
                ventanaEditarEstadoCuenta.btnCancelar.addActionListener(listener);
                ventanaEditarEstadoCuenta.btnBuscar.addActionListener(listener);
        }
        
        // Boton dee añadir estado de cuenta
        if(panelEstadoCuenta.btnNuevo == e.getSource()){
            VentanaNuevoEstadoCuenta ventanaNuevoEstadoCuenta = new VentanaNuevoEstadoCuenta();
            String[] clientes = modelo.conseguirNombres(con, modelo.nombreClientes());
            String[] ids = modelo.conseguirNombres(con, modelo.idBarco());

            for(int i=0; i<clientes.length;i++){
                ventanaNuevoEstadoCuenta.cbCliente.addItem(clientes[i]);
            }
            
            
            ventanaNuevoEstadoCuenta.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    
                    if(ventanaNuevoEstadoCuenta.btnGuardar==e.getSource()){
                        
                        
                        
                    }
                    if(ventanaNuevoEstadoCuenta.btnCancelar==e.getSource()){
                        ventanaNuevoEstadoCuenta.dispose();
                    }
                    
                        
                    }
                };
                ventanaNuevoEstadoCuenta.btnGuardar.addActionListener(listener);
                ventanaNuevoEstadoCuenta.btnCancelar.addActionListener(listener);

        }
        

        // Boton de excel del panel de Estado de cuenta
        if(panelEstadoCuenta.btnExcelIE==e.getSource()){
            contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            ventanaExcel.setVisible(true);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
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
                                JOptionPane.showMessageDialog(null, modelo.Exportar(archivo, panelEstadoCuenta.tbEstadoCuenta));

                            }else{
                                 JOptionPane.showMessageDialog(null, "Elija un formato valido");
                            }
                        }
                    }
                        
                }
            };
            ventanaExcel.btnImportar.addActionListener(listener);
            ventanaExcel.btnExportar.addActionListener(listener);
        }
        
        if(panelEstadoCuenta.btnMostrar == e.getSource()){
            panelEstadoCuenta.tbEstadoCuenta.setModel(modelo.tablaConsulta(con, modelo.verEstadoCuenta()));
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
        
        // Boton de editar el panel de Folio
        if(panelFolio.btnEditar== e.getSource()){
            VentanaEditarFolioPago ventanaEditarFolioPago = new VentanaEditarFolioPago();
            String[] clientes = modelo.conseguirNombres(con, modelo.nombreClientes());
            String[] ids = modelo.conseguirNombres(con, modelo.idBarco());

            for(int i=0; i<clientes.length;i++){
                ventanaEditarFolioPago.cbCliente.addItem(clientes[i]);
            }
            
            
            ventanaEditarFolioPago.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    if(ventanaEditarFolioPago.btnBuscar == e.getSource()){
//                        String[] datos = modelo.buscarClienteId(con,Integer.parseInt(ventanaEditarFolioPago.txtFolioID.getText()));
                        
                        
                        
                    }
                    
                    
                    if(ventanaEditarFolioPago.btnGuardar==e.getSource()){
                        
                        
                        
                    }
                    if(ventanaEditarFolioPago.btnCancelar==e.getSource()){
                        ventanaEditarFolioPago.dispose();
                    }
                    
                        
                    }
                };
                ventanaEditarFolioPago.btnGuardar.addActionListener(listener);
                ventanaEditarFolioPago.btnCancelar.addActionListener(listener);
                ventanaEditarFolioPago.btnBuscar.addActionListener(listener);
        }
        
        // Boton dee añadir estado de cuenta
        if(panelFolio.btnNuevo == e.getSource()){
            VentanaNuevoFolioPago ventanaNuevoFolioPago = new VentanaNuevoFolioPago();
            String[] clientes = modelo.conseguirNombres(con, modelo.nombreClientes());
            String[] ids = modelo.conseguirNombres(con, modelo.idBarco());

            for(int i=0; i<clientes.length;i++){
                ventanaNuevoFolioPago.cbCliente.addItem(clientes[i]);
            }
            
            
            ventanaNuevoFolioPago.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    
                    if(ventanaNuevoFolioPago.btnGuardar==e.getSource()){
                        
                        
                        
                    }
                    if(ventanaNuevoFolioPago.btnCancelar==e.getSource()){
                        ventanaNuevoFolioPago.dispose();
                    }
                    
                        
                    }
                };
                ventanaNuevoFolioPago.btnGuardar.addActionListener(listener);
                ventanaNuevoFolioPago.btnCancelar.addActionListener(listener);

        }
        

        // Boton de excel del panel de Estado de cuenta
        if(panelFolio.btnExcelIE==e.getSource()){
            contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            ventanaExcel.setVisible(true);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
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
                                JOptionPane.showMessageDialog(null, modelo.Exportar(archivo, panelFolio.tbFolios));

                            }else{
                                 JOptionPane.showMessageDialog(null, "Elija un formato valido");
                            }
                        }
                    }
                        
                }
            };
            ventanaExcel.btnImportar.addActionListener(listener);
            ventanaExcel.btnExportar.addActionListener(listener);
        }
        
        if(panelFolio.btnMostrar == e.getSource()){
            panelFolio.tbFolios.setModel(modelo.tablaConsulta(con, modelo.verFolioPago()));
        }
        
        // Botono del panel de empleados
        if(menuPrincipal.menuItemEmpleados == e.getSource()){
            empleados.setSize(979, 473);
            empleados.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(empleados);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
        }
        
        // Boton de editar el panel de Empleado
        if(empleados.btnEditar== e.getSource()){
            VentanaEditarEmpleado ventanaEditarEmpleado = new VentanaEditarEmpleado();
            
            
            
            ventanaEditarEmpleado.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    if(ventanaEditarEmpleado.btnBuscar == e.getSource()){
                       
                        
                        
                        
                    }
                    
                    
                    if(ventanaEditarEmpleado.btnGuardar==e.getSource()){
                        
                        
                        
                    }
                    if(ventanaEditarEmpleado.btnCancelar==e.getSource()){
                        ventanaEditarEmpleado.dispose();
                    }
                    
                        
                    }
                };
                ventanaEditarEmpleado.btnGuardar.addActionListener(listener);
                ventanaEditarEmpleado.btnCancelar.addActionListener(listener);
                ventanaEditarEmpleado.btnBuscar.addActionListener(listener);
        }
        
        // Boton dee añadir Empleado
        if(empleados.btnNuevo == e.getSource()){
            VentanaNuevoEmpleado ventanaNuevoEmpleado = new VentanaNuevoEmpleado();

            ventanaNuevoEmpleado.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    
                    if(ventanaNuevoEmpleado.btnGuardar==e.getSource()){
                        
                        
                        
                    }
                    if(ventanaNuevoEmpleado.btnCancelar==e.getSource()){
                        ventanaNuevoEmpleado.dispose();
                    }
                    
                        
                    }
                };
                ventanaNuevoEmpleado.btnGuardar.addActionListener(listener);
                ventanaNuevoEmpleado.btnCancelar.addActionListener(listener);

        }
        

        // Boton de excel del panel de Empleados
        if(empleados.btnExcelIE==e.getSource()){
            contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            ventanaExcel.setVisible(true);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
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
                                JOptionPane.showMessageDialog(null, modelo.Exportar(archivo, empleados.tbEmpleados));

                            }else{
                                 JOptionPane.showMessageDialog(null, "Elija un formato valido");
                            }
                        }
                    }
                        
                }
            };
            ventanaExcel.btnImportar.addActionListener(listener);
            ventanaExcel.btnExportar.addActionListener(listener);
        }
        
        if(empleados.btnEliminar == e.getSource()){
            
            VentanaBorrarEmpleado ventanaBorrarEmpleado = new VentanaBorrarEmpleado();

            
            
            ventanaBorrarEmpleado.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    if(ventanaBorrarEmpleado.btnBuscarId == e.getSource()){

                        
                        
                    }
                    
                    if(ventanaBorrarEmpleado.btnBuscarNombre == e.getSource()){

                    }
                    
                    
                    if(ventanaBorrarEmpleado.btnBorrar ==e.getSource()){
                        
                        
                        
                    }
                    if(ventanaBorrarEmpleado.btnCancelar==e.getSource()){
                        ventanaBorrarEmpleado.dispose();
                    }
                    
                        
                    }
                };
                ventanaBorrarEmpleado.btnBorrar.addActionListener(listener);
                ventanaBorrarEmpleado.btnCancelar.addActionListener(listener);
                ventanaBorrarEmpleado.btnBuscarNombre.addActionListener(listener);
                ventanaBorrarEmpleado.btnBuscarId.addActionListener(listener);
            
        }
        
        
        if(empleados.btnMostrar == e.getSource()){
            empleados.tbEmpleados.setModel(modelo.tablaConsulta(con, modelo.verEmpleados()));
        }
        
        // Botono del panel de Entradas
        if(menuPrincipal.menuItemEntrada == e.getSource()){
            panelEntrada.setSize(979, 473);
            panelEntrada.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(panelEntrada);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
        }
        
        // Boton de editar el panel de Empleado
        if(panelEntrada.btnEditar== e.getSource()){
            VentanaEditarEntrada ventanaEditarEntrada = new VentanaEditarEntrada();
            
            
            
            ventanaEditarEntrada.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    if(ventanaEditarEntrada.btnBuscar == e.getSource()){
                       
                        
                        
                        
                    }
                    
                    
                    if(ventanaEditarEntrada.btnGuardar==e.getSource()){
                        
                        
                        
                    }
                    if(ventanaEditarEntrada.btnCancelar==e.getSource()){
                        ventanaEditarEntrada.dispose();
                    }
                    
                        
                    }
                };
                ventanaEditarEntrada.btnGuardar.addActionListener(listener);
                ventanaEditarEntrada.btnCancelar.addActionListener(listener);
                ventanaEditarEntrada.btnBuscar.addActionListener(listener);
        }
        
        // Boton dee añadir Empleado
        if(panelEntrada.btnNuevo == e.getSource()){
            VentanaNuevoEntrada ventanaNuevoEntrada = new VentanaNuevoEntrada();

            ventanaNuevoEntrada.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    
                    if(ventanaNuevoEntrada.btnGuardar==e.getSource()){
                        
                        
                        
                    }
                    if(ventanaNuevoEntrada.btnCancelar==e.getSource()){
                        ventanaNuevoEntrada.dispose();
                    }
                    
                        
                    }
                };
                ventanaNuevoEntrada.btnGuardar.addActionListener(listener);
                ventanaNuevoEntrada.btnCancelar.addActionListener(listener);

        }
        

        // Boton de excel del panel de Empleados
        if(panelEntrada.btnExcelIE==e.getSource()){
            contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            ventanaExcel.setVisible(true);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
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
                                JOptionPane.showMessageDialog(null, modelo.Exportar(archivo, panelEntrada.tbEntrada));

                            }else{
                                 JOptionPane.showMessageDialog(null, "Elija un formato valido");
                            }
                        }
                    }
                        
                }
            };
            ventanaExcel.btnImportar.addActionListener(listener);
            ventanaExcel.btnExportar.addActionListener(listener);
        }
        
        
        //Mostrar la tabla de entradas
        if(panelEntrada.btnMostrar == e.getSource()){
            panelEntrada.tbEntrada.setModel(modelo.tablaConsulta(con, modelo.verEntrada()));
        }
        
        
        
        
        
        // Botono del panel de Salidas
        if(menuPrincipal.menuItemSalidas == e.getSource()){
            panelSalida.setSize(979, 473);
            panelSalida.setLocation(0,0);
            
            menuPrincipal.content.removeAll();
            menuPrincipal.content.add(panelSalida);
            menuPrincipal.content.revalidate();
            menuPrincipal.content.repaint();
            
        }
        
        // Boton de editar el panel de Salidas
        if(panelSalida.btnEditar== e.getSource()){
            VentanaEditarSalida ventanaEditarSalida = new VentanaEditarSalida();
            
            
            
            ventanaEditarSalida.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    if(ventanaEditarSalida.btnBuscar == e.getSource()){
                       
                        
                        
                        
                    }
                    
                    
                    if(ventanaEditarSalida.btnGuardar==e.getSource()){
                        
                        
                        
                    }
                    if(ventanaEditarSalida.btnCancelar==e.getSource()){
                        ventanaEditarSalida.dispose();
                    }
                    
                        
                    }
                };
                ventanaEditarSalida.btnGuardar.addActionListener(listener);
                ventanaEditarSalida.btnCancelar.addActionListener(listener);
                ventanaEditarSalida.btnBuscar.addActionListener(listener);
        }
        
        // Boton de añadir del panel salidas
        if(panelSalida.btnNuevo == e.getSource()){
            VentanaNuevoSalida ventanaNuevoSalida = new VentanaNuevoSalida();

            ventanaNuevoSalida.setVisible(true);
            
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    
                    
                    if(ventanaNuevoSalida.btnGuardar==e.getSource()){
                        
                        
                        
                    }
                    if(ventanaNuevoSalida.btnCancelar==e.getSource()){
                        ventanaNuevoSalida.dispose();
                    }
                    
                        
                    }
                };
                ventanaNuevoSalida.btnGuardar.addActionListener(listener);
                ventanaNuevoSalida.btnCancelar.addActionListener(listener);

        }
        

        // Boton de excel del panel de Salidas
        if(panelSalida.btnExcelIE==e.getSource()){
            contadorAccion++;
            if(contadorAccion ==1){
                agregarFiltro();
            }
            ventanaExcel.setVisible(true);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
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
                                JOptionPane.showMessageDialog(null, modelo.Exportar(archivo, panelSalida.tbSalida));

                            }else{
                                 JOptionPane.showMessageDialog(null, "Elija un formato valido");
                            }
                        }
                    }
                        
                }
            };
            ventanaExcel.btnImportar.addActionListener(listener);
            ventanaExcel.btnExportar.addActionListener(listener);
        }
        
        
        //Mostrar la tabla de Salidas
        if(panelSalida.btnMostrar == e.getSource()){
            panelSalida.tbSalida.setModel(modelo.tablaConsulta(con, modelo.verSalida()));
        }
        
        
        
    }
    
    public void agregarFiltro(){
        selectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selectArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
    }
   
    
}
