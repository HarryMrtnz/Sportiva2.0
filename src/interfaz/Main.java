package interfaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import datos.Encargado;
import datos.Gerente;
import datos.Producto;
import datos.Usuario;
import datos.Vendedor;
import interfaces.Conexion;
import negocio.Verifica;

public class Main {
	public static void main(String[] args) {

		//conectar a la bdd
		Conexion con = new Conexion();
		Connection conexion = con.conectar();
		PreparedStatement stmt;
		
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		
		//Datos que traigo de la base de datos para iniciar sesion
		String sql = "SELECT nombre, apellido, dni, clave, fk_puesto " 
				   + "FROM usuario;";
		String[] datos = new String[5];

		try {
			stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) { //Guardo en array los Datos de la query
				datos[0] = result.getString(1);// nombre
				datos[1] = result.getString(2);// apellido
				datos[2] = result.getString(3);// dni
				datos[3] = result.getString(4);// clave
				datos[4] = result.getString(5);// puesto
				
				if (Integer.parseInt(datos[4]) == 1) { //Comparo el puesto con 1 = Gerente
					Gerente nuevoGerente = new Gerente(datos[0], datos[1], datos[2], null, null, datos[3], Integer.parseInt(datos[4]), 0);
					usuarios.add(nuevoGerente); //Guardo el objeto Gerente en lista usuarios.
					
				} else if (Integer.parseInt(datos[4]) == 2) { //Comparo el puesto con 2 = Vendedor
					Vendedor nuevoVendedor = new Vendedor(datos[0], datos[1], datos[2], null, null, datos[3], Integer.parseInt(datos[4]), 0);
					usuarios.add(nuevoVendedor); //Guardo el objeto Vendedor en lista usuarios.
					
				} else if (Integer.parseInt(datos[4]) == 3) { //Comparo el puesto con 3 = Encargado
					Encargado nuevoEncargado= new Encargado(datos[0], datos[1], datos[2], null, null, datos[3], Integer.parseInt(datos[4]), 0);
					usuarios.add(nuevoEncargado); //Guardo el objeto Encargado en lista usuarios.
				}
			}
		} catch (Exception excepcion) {
			JOptionPane.showMessageDialog(null, excepcion.getMessage());
		}
		
		//Inicio el login
		iniciarSesion(usuarios);
		
	} //Fin del Main
		
	public static void iniciarSesion(LinkedList<Usuario> usuarios) {
		String dni =  JOptionPane.showInputDialog("Ingrese su DNI");
		String clave = JOptionPane.showInputDialog("Ingrese su CLAVE");
		
			for (Usuario usuario : usuarios) { //Recorro lista ssuarios
			//Si logueo exitoso abre el menu del puesto que corresponda
				if (usuario.login(dni, clave) && usuario.getPuesto()==1) {
					JOptionPane.showMessageDialog(null, "Sesion iniciada correctamente!"
							+ "\n Bienvenido "+usuario.getNombre()+" "+usuario.getApellido() );
					menuGerente(usuarios);
				} else if(usuario.login(dni, clave) && usuario.getPuesto()==2){
					JOptionPane.showMessageDialog(null, "Sesion iniciada correctamente!"
							+ "\n Bienvenido "+usuario.getNombre()+" "+usuario.getApellido() );
					menuVendedor(usuarios);
				} else if(usuario.login(dni, clave) && usuario.getPuesto()==3) {
					JOptionPane.showMessageDialog(null, "Sesion iniciada correctamente!"
							+ "\n Bienvenido "+usuario.getNombre()+" "+usuario.getApellido() );
					menuEncargado(usuarios);
				} 
			}
			// Logueo NO exitoso
				JOptionPane.showMessageDialog(null, "Datos incorrectos\n"
						+ "Has escrito algo mal, "
						+ "o te han funado xD\n\n"
						+ "Vuelve a intentarlo");
				iniciarSesion(usuarios);
	}
	
	//Menu que debe aparecer si el usuario es Gerente
	public static void menuGerente(LinkedList<Usuario> usuarios) {
		String[] opciones = { " Visualizar usuarios",
							  " Agregar usuario",
							  " Mostrar lista usuarios",
							  " Eliminar usuario",
							  " Editar usuario",
							  " Ver sponsors disponibles",
							  " Ver equipos clasificados",
							  " Ver equipos pendientes",
							  " Salir" };
		String opcion="";
		
		do {
			opcion = (String)JOptionPane.showInputDialog(null, "Seleccione la opcion deseada",
					"SPORTIVA - MENU GERENTE",JOptionPane.DEFAULT_OPTION,null, opciones,opciones[0]);
			
			switch (opcion) {
			case " Visualizar usuarios":
				
				break;
			case " Agregar usuario":
				
				break;
			case " Mostrar lista usuarios":
				
				break;
			case " Eliminar usuario":
				
				break;
			case " Editar usuario":
				
				break;
			case " Ver sponsors disponibles":
				
				break;
			case " Ver equipos clasificados":
				
				break;
			case " Ver equipos pendientes":
				
				break;
				
			default: iniciarSesion(usuarios);
				break;
			}
			
		} while(!opcion.equals(" Salir" ));
	}
	//Menu que debe aparecer si el usuario es Vendedor
	public static void menuVendedor(LinkedList<Usuario> usuarios) {
		//Vendedor nuevoVendedor = new Vendedor ("", "", "", "", "", false, 0);
		LinkedList<Producto> productos = new LinkedList<Producto>();
		LinkedList<Producto> stockEnCorrientes = new LinkedList<Producto>();
		LinkedList<Producto> stockEnBrasil = new LinkedList<Producto>();
		LinkedList<Producto> stockEnGaona = new LinkedList<Producto>();

		//conectar a bdd	
		Conexion con = new Conexion();
		Connection conexion = con.conectar();
		PreparedStatement stmt;
		
		Verifica verifica = new Verifica();
		
		String[] opciones = { " Realizar una venta",
							  " Imprimir factura",
							  " Revisar stock de un producto",
							  " Ver stock de todos los productos",
							  " Realizar pedido al deposito",
							  " Ver ventas realizadas",
							  " Recaudación Total",
							  " Salir" };
		String opcion="";
		
		do {
			opcion = (String)JOptionPane.showInputDialog(null, "Seleccione la opcion deseada",
				"SPORTIVA - MENU VENDEDOR",JOptionPane.DEFAULT_OPTION,null, opciones,opciones[0]);
			switch (opcion) {
			case " Realizar una venta":
				String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
				String[] cajas = {"1","2","3"};
				
				String fecha = (String) JOptionPane.showInputDialog(
						null, "Seleccione el dia de hoy","SPORTIVA"
						,JOptionPane.QUESTION_MESSAGE ,null ,dias, dias[0]);
				
				String caja = (String) JOptionPane.showInputDialog(
						null, "Seleccione en cual caja está operando:","SPORTIVA"
						,JOptionPane.QUESTION_MESSAGE ,null ,cajas, cajas[0]);
				
				int cantidad = Integer.parseInt(JOptionPane.showInputDialog("ingrese cantidad de productos:"));
				
				
				
				break;
			case " Imprimir factura":
				
				break;
			case " Revisar stock de un producto":
					if(verifica.stockPorProducto().isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se encontró producto");
					}else {
						JOptionPane.showMessageDialog(null, verifica.stockPorProducto());
					}
				
				break;
			case " Ver stock de todos los productos":
				if( verifica.verStockProductos().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Lista vacia, no hay stock");
				}else {
					JOptionPane.showMessageDialog(null, verifica.verStockProductos());
				}
				break;
			case "Mostrar lista personas":
				break;
			case " Realizar pedido al deposito":
				
				break;
			case " Ver ventas realizadas":
				
				break;
			case " Recaudación Total":
				
				break;
				
			default: iniciarSesion(usuarios);
				break;			
			}
		
		} while(!opcion.equals(" Salir" ));
		
	}
	//Menu que debe aparecer si el usuario es Encargado
	public static void menuEncargado(LinkedList<Usuario> usuarios) {
		String[] opciones = { " Ver stock de todos los productos",
							  " Ver stock de un producto",
							  " Agregar nuevo producto",
							  " Renovar stock de un producto",
							  " Hacer pedido al proveedor",
							  " Salir" };
		String opcion="";
			
			do {
				opcion = (String)JOptionPane.showInputDialog(null, "Seleccione la opcion deseada",
						"SPORTIVA - MENU ENCARGADO",JOptionPane.DEFAULT_OPTION,null, opciones,opciones[0]);
				
				switch (opcion) {
				case " Ver stock de todos los productos":
					
					break;
				case " Ver stock de un producto":
					
					break;
				case " Agregar nuevo producto":
					
					break;
				case  " Renovar stock de un producto":
					
					break;
				case " Hacer pedido al proveedor":
					
					break;
					
				default: iniciarSesion(usuarios);
					break;			
				}
			
			} while(!opcion.equals(" Salir" ));
	}


}
	
