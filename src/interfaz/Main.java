package interfaz;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		
		

	

	}
		
	public static void iniciarSecion() {
		String dni =  JOptionPane.showInputDialog("Ingrese su DNI");
		String contraseña = JOptionPane.showInputDialog("Ingrese su contraseña");
		
	}
	
	//Menu que debe aparecer si el usuario es Gerente
	public static void menuGerente() {
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
				
			default:
				break;
			}
			
		} while(!opcion.equals(" Salir" ));
	}
	//Menu que debe aparecer si el usuario es Vendedor
	public static void menuVendedor() {
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
				
				break;
			case " Imprimir factura":
				
				break;
			case " Revisar stock de un producto":
				
				break;
			case " Ver stock de todos los productos":
				
				break;
			case " Realizar pedido al deposito":
				
				break;
			case " Ver ventas realizadas":
				
				break;
			case " Recaudación Total":
				
				break;
				
			default:
				break;			
			}
		
		} while(!opcion.equals(" Salir" ));
		
	}
	//Menu que debe aparecer si el usuario es Encargado
	public static void menuEncargado() {
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
					
				default:
					break;			
				}
			
			} while(!opcion.equals(" Salir" ));
	}


}
	
