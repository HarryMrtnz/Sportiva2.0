package negocio;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import datos.Vendedor;
import datos.Venta;
import datos.Gerente;
import datos.Producto;

public class Verifica {
	
//--- Hecho por Fran ------------------------------------------------------------
	Gerente nuevousuario = new Gerente("","","","","","",0,0); 
	
  	public LinkedList<Gerente> verificaLista(){
		
		return nuevousuario.verUsuarios();
	}
  	
  	public LinkedList<Gerente> verificaUsuario(int id){
  		
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
									clave = JOptionPane.showInputDialog("Error al ingresar la clave, "
											+ "debe tener entre 5 y 12 caracteres\n"
											+ "Ingrese la clave de la nueva persona a agregar");
								}
							} else {
								telefono = JOptionPane.showInputDialog("Error al ingresar el telefono, "
										+ "debe tener entre 8 y 14 caracteres numericos\n"
										+ "Ingrese el telefono de la nueva persona a agregar");
							}
						} else {
							email=JOptionPane.showInputDialog("Error al ingresar el email, "
									+ "debe tener entre 8 y 50 letras\n"
									+ "Ingrese el email de la nueva persona a agregar");
						} 
					}else {
						dni = JOptionPane.showInputDialog("Error al ingresar el dni, "
								+ "debe tener 8 caracteres numericos\n"
								+ "Ingrese el dni de la nueva persona a agregar");
					}
				}else {
					apellido = JOptionPane.showInputDialog("Error al ingresar el apellido, "
							+ "debe tener entre 3 y 15 letras\n"
							+ "Ingrese el apellido de la nueva persona a agregar");
				}
			}else {
				 nombre = JOptionPane.showInputDialog("Error al ingresar el nombre, "
				 		+ "debe tener entre 3 y 15 letras\n"
				 		+ "Ingrese el nombre de la nueva persona a agregar");
			}
		}while(error == 0);
		return false;
	}
	
	public boolean verificaEditar(Gerente usuario) {
	// Traigo el usuario que estoy editando en el Main
		if(Integer.parseInt(usuario.getId())>0) {
			nuevousuario = usuario;
	// Hago las correspondientes validaciones	
			int error=0;
			do {
				if (usuario.getNombre().length()>=3 &&  usuario.getNombre().length()<=15 && usuario.getNombre().matches("[a-zA-Z]+")){
					if (usuario.getApellido().length()>=3 && usuario.getApellido().length()<=20 && usuario.getApellido().matches("[a-zA-Z]+")){
						if (usuario.getDni().length()==8 && usuario.getDni().matches("\\d+")){
							if (usuario.getEmail().length()>=8 && usuario.getEmail().length()<=50) {
								if (usuario.getTelefono().length()>=6 && usuario.getTelefono().length()<=14 && usuario.getTelefono().matches("-?\\d+(-\\d+)*")) {
									if(usuario.getClave().length()>=5 && usuario.getClave().length()<=12) {
										
										error=1;
										nuevousuario.setApellido(usuario.getApellido());
										nuevousuario.setDni(usuario.getDni());
										nuevousuario.setNombre(usuario.getNombre());
										nuevousuario.setEmail(usuario.getEmail());
										nuevousuario.setTelefono(usuario.getTelefono());
										nuevousuario.setClave(usuario.getClave());
										nuevousuario.setPuesto(usuario.getPuesto());
										nuevousuario.setSucursal(usuario.getSucursal());
										return nuevousuario.editarUsuario();
											
									} else{
										usuario.setClave(JOptionPane.showInputDialog("Error al ingresar la clave, "
												+ "debe tener entre 5 y 12 caracteres\n"
												+ "Ingrese la clave de la nueva persona a agregar"));
									}
								} else {
								usuario.setTelefono(JOptionPane.showInputDialog("Error al ingresar el telefono, "
										+ "debe tener entre 8 y 14 caracteres numericos\n"
										+ "Ingrese el telefono de la nueva persona a agregar"));
								}
							} else {
								usuario.setEmail(JOptionPane.showInputDialog("Error al ingresar el email, "
										+ "debe tener entre 8 y 50 letras\n"
										+ "Ingrese el email de la nueva persona a agregar"));
							} 
						}else {
							usuario.setDni(JOptionPane.showInputDialog("Error al ingresar el dni, "
									+ "debe tener 8 caracteres numericos\n"
									+ "Ingrese el dni de la nueva persona a agregar"));
						}
					}else {
						usuario.setApellido(JOptionPane.showInputDialog("Error al ingresar el apellido, "
								+ "debe tener entre 3 y 15 letras\n"
								+ "Ingrese el apellido de la nueva persona a agregar"));
					}
				}else {
					usuario.setNombre(JOptionPane.showInputDialog("Error al ingresar el nombre, "
							+ "debe tener entre 3 y 15 letras\n"
							+ "Ingrese el nombre de la nueva persona a agregar"));
				}
				
			}while(error==0);
				return false;
				
		}else {
			return false;
		}
	}
	
//--- Hecho por Harry ------------------------------------------------------------
	
	Vendedor vendedor = new Vendedor ("Harry", "Martinez", "", "", "", "", 2, 1);
	Producto producto = new Producto (0, "", "", "", 0, "");
	Venta venta = new Venta(0, 0, null, 0);
	LinkedList<Producto>productos;
	
	public String verProductos(int sucursal){
		
		System.out.println(vendedor.getNombre()+ " anda mirando listado de productos.\n");
		return vendedor.verProductos(sucursal);
	}
	
	public String verStock(int sucursal) {
		
		System.out.println(vendedor.getNombre()+ " anda mirando info de un producto.\n");
		return vendedor.verStock(sucursal);
	}
	
	public boolean agregarVenta(Producto producto, int cantidad, int caja, String metodoPago) {

		int flag = 0;
		double subtotal;
		double total;
		
		do {
			if (cantidad > 0 && cantidad <= producto.getStock()){
				LocalDate fecha = LocalDate.now();
				subtotal = producto.getPrecio()*cantidad;
			//Aplicar descuento en efectivo
				if (metodoPago.equals("Efectivo")){
					total = subtotal*0.95;// 5% de descuento 
				}else {//Otra forma de pago no aplica descuento
					total = subtotal;
				}
			//Aplicar descuento en zapatillas los jueves
				if (producto.getCategoria().equals("1") //1 = zapatillas
					&& fecha.getDayOfWeek().equals("THURSDAY")){//Dia de la semana = Jueves (THURSDAY)

					total = total*0.70; // 30% de descuento
				}
				flag = 1;
				venta.setCaja(caja);
				venta.setCantidad(cantidad);
				venta.setProducto(producto.getId_producto());
				venta.setMetodoPago(metodoPago);
				venta.setTotal(total);
				
				vendedor.realizarVenta();
				return true;

			}else {
				  cantidad = Integer.parseInt(JOptionPane.showInputDialog(
						  	"No hay suficientes productos  a la venta\n"
						  	+ "Solo hay "+producto.getStock()+" unidades\n"
							+ "Intente un valor menor" ));
			}
		}while(flag == 0);
			return false;
	}
	
  	public LinkedList<Venta> listaVentas(){

		return vendedor.verVentas();
	}
	
	public boolean hacerPedidoDeposito(Producto producto) {
		
		int flag = 0;
		do {
			if (!producto.getNombre().isEmpty()){
				if (producto.getStock() > 0) {
					flag = 1;
					//vendedor.hacerPedido();
					
					return true;
				}else {
					producto.setStock(Integer.parseInt(JOptionPane.showInputDialog(""
							+ "Error al ingresar cantidad:\n"
							+ "Debe elegir un minimo de 1 unidad\n\n"
							+ "Ingrese nuevamente cantidad a ordenar.")));
				}
			}
		}while(flag == 0);
			return false;
	}	
	
	public String recaudacionCaja(int caja, int sucursal) {
		return vendedor.verRecaudacionCaja(caja, sucursal);

	}
	
	public String recaudacionSucursal(int sucursal) {
		return vendedor.verRecaudacionSucursal(sucursal);
	}
	
	public String recaudacionTotal() {
		return vendedor.verRecaudacionTotal();
	}
	
//--- Hecho por Kari ------------------------------------------------------------
	
	




}


