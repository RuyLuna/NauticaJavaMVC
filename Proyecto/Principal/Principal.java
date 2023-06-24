
package Proyecto.Principal;
import Proyecto.Modelo.ModeloBD;
import Proyecto.Controlador.Controlador;
import Proyecto.Vista.Empleados;
import Proyecto.Vista.Login;
import Proyecto.Vista.MenuPrincipal;
import Proyecto.Vista.PanelBarcos;
import Proyecto.Vista.PanelClientes;
import Proyecto.Vista.PanelEntrada;
import Proyecto.Vista.PanelEstadoCuenta;
import Proyecto.Vista.PanelFolio;
import Proyecto.Vista.PanelPrincipal;
import Proyecto.Vista.PanelSalida;
import Proyecto.Vista.PanelTablaCaseta;
import Proyecto.Vista.PanelTablaDiqueSeco;
import Proyecto.Vista.PanelTablaMuelles;
import Proyecto.Vista.VentanaBorrarCliente;
import Proyecto.Vista.VentanaEditarCliente;
import Proyecto.Vista.VentanaFecha;
import Proyecto.Vista.VentanaFechaMes;
import Proyecto.Vista.VentanaNombre;
import Proyecto.Vista.VentanaNuevoCliente;
import Proyecto.Vista.VentanaExcel;
        
import java.awt.BorderLayout;


/**
 * Fecha: 26/06/2022
 * @author Pedro Antonio Sánchez Salas, Erick Jeanick Lopez Gonzales, Jesus de Israel Jimenez Tostado, Juan Carlos Moreno Lopez, Ruy Jesé Luna Sandoval
 * Descripcion: Archivo de inicio principal del proyecto
 */

public class Principal {

    
    public static void main(String[] args) {
       
        ModeloBD modelo = new ModeloBD();
        Login login = new Login();
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        PanelBarcos panelBarcos = new PanelBarcos();
        PanelClientes panelClientes = new PanelClientes();
        PanelEstadoCuenta panelEstadoCuenta = new PanelEstadoCuenta();
        PanelFolio panelFolio = new PanelFolio();
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        PanelTablaCaseta panelTablaCaseta = new PanelTablaCaseta();
        PanelTablaDiqueSeco panelTablaDique = new PanelTablaDiqueSeco();
        PanelTablaMuelles panelTablaMuelles = new PanelTablaMuelles();
        VentanaBorrarCliente ventanaBorrarCliente = new VentanaBorrarCliente();
        VentanaEditarCliente ventanaEditarCliente = new VentanaEditarCliente();
        VentanaFecha ventanaFecha = new VentanaFecha();
        VentanaFechaMes ventanaFechaMes = new VentanaFechaMes();
        VentanaNombre ventanaNombre = new VentanaNombre();
        VentanaNuevoCliente ventanaNuevoCliente = new VentanaNuevoCliente();
        VentanaExcel ventanaExcel = new VentanaExcel();
        Empleados empleados = new Empleados();
        PanelEntrada panelEntrada = new PanelEntrada();
        PanelSalida panelSalida = new PanelSalida();

        
        
        
        
        
        
        
        Controlador controlador = new Controlador(modelo, login, menuPrincipal, panelBarcos ,panelClientes , panelEstadoCuenta , panelFolio, 
                panelPrincipal,panelTablaCaseta,panelTablaDique,panelTablaMuelles,ventanaBorrarCliente,ventanaEditarCliente, ventanaFecha,
                ventanaFechaMes,ventanaNombre,ventanaNuevoCliente,ventanaExcel, empleados,panelEntrada, panelSalida);
        
        
        login.setVisible(true);
                
        
       
        
    }
    
    
}
