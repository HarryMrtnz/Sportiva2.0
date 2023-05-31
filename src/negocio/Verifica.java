package negocio;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import datos.Vendedor;
import datos.Gerente;
import datos.Producto;
import datos.Usuario;

public class Verifica {
	LinkedList<Producto>productos;

	Vendedor nuevoVendedor = new Vendedor ("", "", "", "", "", "", 0, 0);
	Producto nuevoProducto = new Producto (0, "", "", "", 0, "");
	
	
//--- Hecho por Fran -----
	Gerente nuevousuario = new Gerente("","","","","","",0,0); 
	

  	public LinkedList<Usuario> verificaLista(){
		
		return nuevousuario.verUsuarios();
	}
  	
  	public LinkedList<Usuario> verificaUsuario(int id){
  		
		if(id>0) {
		return nuevousuario.traerUsuario(id);
		} else {
			JOptionPane.showMessageDialog(null, "Error al ingresar el ID");
			return null;
		}
	} 
	
	
 
  	
  	public boolean verificarEliminar(int id) {
  		if (id>0 && id<9999999) {
  			nuevousuario.eliminarUsuario(id);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "El ID no puede ser negativo");
			return false;
		}
  	}
	
	
	public boolean verificarAgregar(String nombre , String apellido, String dni, String email, String telefono, String clave, int puesto, int sucursal) {
		int error=0;
		do {
			
	
		if (nombre.length()>=3 &&  nombre.length()<=15 && nombre.matches("[a-zA-Z]+")){
			if (apellido.length()>=3 && apellido.length()<=20 && apellido.matches("[a-zA-Z]+")){
				if (dni.length()==8 && dni.matches("\\d+")){
					if (email.length()>=8 && email.length()<=50) {
						if (telefono.length()>=6 && telefono.length()<=14 && telefono.matches("-?\\d+(-\\d+)*")) {
							if(clave.length()>=5 && clave.length()<=12) {
								
									
					error=1;
					nuevousuario.setApellido(apellido);
					nuevousuario.setDni(dni);
					nuevousuario.setNombre(nombre);
					nuevousuario.setEmail(email);
					nuevousuario.setTelefono(telefono);
					nuevousuario.setClave(clave);
					nuevousuario.setPuesto(puesto);
					nuevousuario.setSucursal(sucursal);
					 return nuevousuario.crearUsuario();
									
									
								} else{
									clave=JOptionPane.showInputDialog("Error al ingresar la clave, debe tener entre 5 y 12 caracteres  \n Ingrese la clave de la nueva persona a a�adir");
							}
							} else {
							telefono=JOptionPane.showInputDialog("Error al ingresar el telefono, debe tener entre 8 y 14 caracteres numericos  \n Ingrese el telefono de la nueva persona a a�adir");
						}
						
						} else {
					email=JOptionPane.showInputDialog("Error al ingresar el email, debe tener entre 8 y 50 letras \n Ingrese el email de la nueva persona a a�adir");
				} 
					
				}else {
					dni = JOptionPane.showInputDialog("Error al ingresar el dni, debe tener 8 caracteres numericos \n Ingrese el dni de la nueva persona a a�adir");
				}
			}else {
				apellido = JOptionPane.showInputDialog("Error al ingresar el apellido, debe tener entre 3 y 15 letras \n Ingrese el apellido de la nueva persona a a�adir");
			}
		}else {
			 nombre = JOptionPane.showInputDialog("Error al ingresar el nombre, debe tener entre 3 y 15 letras \n Ingrese el nombre de la nueva persona a a�adir");
		}
		}while(error==0);
		return false;
		
	}
	
	
	public boolean verificaEditar(int id) {
		if (id>0) {
			return nuevousuario.editarUsuario(id);
			
		} else {
			JOptionPane.showMessageDialog(null, "Error al ingresar el ID");
			return false;

		}
	}
	
//--- Hecho por Harry -----
	
	public String verStockProductos(){
		
		return nuevoVendedor.verStockProductos();
	}
	
	
	public String stockPorProducto() {
		
		return nuevoVendedor.verStock();
	}
	
//	public boolean guardarVenta(int cantidad, int caja, double total, LinkedList<Producto> productos) {
//	int flag=0;
//	do {
//		if (cantidad >= this.nuevaVenta.getProducto().getStock()){
//			if (caja >0 && caja < 4){
//				if ()){
//					flag=1;
//					nuevaVenta.setCaja(null);
//					nuevaVenta.setCaja(null);
//					nuevaVenta.setCaja(null);
//					return nuevoVendedor.realizarVenta();
//				}else {
//					dni = JOptionPane.showInputDialog("Error al ingresar el dni debe tener 8 caracteres \n Ingrese el dni de la nueva persona a añadir");
//				}
//			}else {
//				apellido = JOptionPane.showInputDialog("Error al ingresar el apellido debe tener entre 3 y 15 letras \n Ingrese el apellido de la nueva persona a añadir");
//			}
//		}else {
//			 nombre = JOptionPane.showInputDialog("Error al ingresar el nombre debe tener entre 3 y 15 letras \n Ingrese el nombre de la nueva persona a añadir");
//		}
//	}while(flag==0);
//	return false;
//	
//}
}


