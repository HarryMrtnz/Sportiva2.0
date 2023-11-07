package negocio;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import datos.Vendedor;
import datos.Venta;
import datos.Caja;
import datos.Encargado;
import datos.Equipo;
import datos.Gerente;
import datos.Producto;
import datos.Sponsor;

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
	Caja caja = new Caja (0, 0, 0);
	Venta venta = new Venta(0, 0, null, 0);
	LinkedList<Producto> productos;
	
	
	
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
					total = subtotal*0.95;// 5% de descuento. 
				}else {//Otra forma de pago no aplica descuento.
					total = subtotal;
				}
			//Aplicar descuento en zapatillas los jueves.
				if (producto.getCategoria().equals("Calzado") //Categoria del producto = calzado
					&& fecha.getDayOfWeek().name().equals("THURSDAY")){//Dia de la semana = Jueves (THURSDAY)
					total = total*0.70; // 30% de descuento
				}

				flag = 1;
				venta.setCaja(caja);
				venta.setCantidad(cantidad);
				venta.setProducto(producto.getId_producto());
				venta.setMetodoPago(metodoPago);
				venta.setTotal(total);
				
				venta.realizarVenta();
				return true;

			}else {
				  cantidad = Integer.parseInt(JOptionPane.showInputDialog(
						  	"Solo hay "+producto.getStock()+" unidades\n"
							+ "Elige un numero entre 1 y "+producto.getStock()+" porfis." ));
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
	
	public boolean recaudacionCaja(int idCaja) {
		if (caja.recaudacionCaja(idCaja).equals("0,00")) {
			return false;
		}else {
			System.out.println("Imprimiendo recaudacion Caja N°"+idCaja+".\n");
			return true;
		}
	}
	
	public boolean recaudacionSucursal(int sucursal) {
		if (caja.recaudacionSucursal(sucursal).equals("0,00")) {
			return false;
		} else {
			System.out.println("Imprimiendo recaudacion Sucurrsal N°"+sucursal+".\n");
			return true;
		}		
	}
	
	public boolean recaudacionTotal() {
		if (caja.recaudacionTotal().equals("0,00")) {
			return false;
		} else {
			System.out.println("Imprimiendo recaudacion total.\n");
			return true;
		} 
	}
	
//--- Hecho por Kari ------------------------------------------------------------
	Encargado encargado = new Encargado("Karina", "Litvak", "", "", "", "", 3, 1);
	Producto nuevoproducto = new Producto(0, "", "", "", 0, "");
	Sponsor sponsor = new Sponsor(0, "");
	Equipo equipo = new Equipo(0, "", "", "");
	
	public boolean verificarAgregarProducto(String nombre, String descripcion, String marca, double precio, String categoria) {
		int error=0;
		do {
			if (nombre.length()>=3 &&  nombre.length()<=15 && nombre.matches("[a-zA-Z]+")){
				if (descripcion.length()>=8 && descripcion.length()<=30 && descripcion.matches("[a-z A-Z]+")){
					if (marca.length()>=3 && marca.length()<=30 && marca.matches("[a-zA-Z]+")) {
						if (precio>=10) {
							error=1;
									nuevoproducto.setNombre(nombre);
									nuevoproducto.setDescripcion(descripcion);
									nuevoproducto.setMarca(marca);
									nuevoproducto.setPrecio(precio);
									nuevoproducto.setCategoria(categoria);
									
									return nuevoproducto.agregarProducto();	
												
								} else{
									precio = Double.parseDouble(JOptionPane.showInputDialog("Error al ingresar el precio, "
											+ "debe ser mayor a $10\n"
											+ "Ingrese el precio del nuevo producto a agregar"));
								}
							} else {
								marca = JOptionPane.showInputDialog("Error al ingresar la marca, "
										+ "debe tener entre 3 y 30 letras\n"
										+ "Ingrese la marca del nuevo producto a agregar");
							}
						} else {
							descripcion =JOptionPane.showInputDialog("Error al ingresar la descripcion, "
									+ "debe tener entre 8 y 30 letras\n"
									+ "Ingrese la descripcion del nuevo producto a agregar");
						} 
					}else {
						nombre = JOptionPane.showInputDialog("Error al ingresar el nombre, "
								+ "debe tener entre 3 y 15 letras\n"
								+ "Ingrese el nombre del nuevo producto a agregar");
					}
				
		}while(error == 0);
		return false;
	}

	public boolean verificarStock(Producto producto, int stock) {
		int flag = 0;
		do {
			if (stock > 0) {// Si es numero positivo.
				flag = 1;
				//Llamo al metodo modificar Stock con el producto selecionado
				producto.modificarStock(stock, encargado); 
				return true;
			} else {
				stock =  Integer.parseInt(JOptionPane.showInputDialog("Error al ingresar stock, "
						+ "debe ser un numero mayor a cero.\n"
						+ "Ingrese un valor nuevamente."));
			}
			
		 } while(flag == 0);
		 return false; 
	}

	public LinkedList<Producto> verificaLista3(){
		
		return nuevoproducto.listaProductos(0);
	}
	
	public String verificaProducto(int id_producto) {
		if(id_producto>0) {
			return nuevoproducto.infoProducto(id_producto);
		} else {
			JOptionPane.showMessageDialog(null, "Error al ingresar el ID");
			return null;
		}
	}

	 public LinkedList<Sponsor> verificaLista1(){
			
		return sponsor.TraerSponsor();
	}

	 public LinkedList<Equipo> verificaLista2(){
			
		return equipo.VerEquipoClasificado();
	}
	 
	public boolean aprobarEquipo(Equipo equipo) {
	
		if (equipo.getDeporte().equals("futbol")) {
			if (equipo.getClasificacion().equals("Primera A") || equipo.getClasificacion().equals("Primera B Nacional") ) {
				
				return true;
			}else {
				JOptionPane.showMessageDialog(null
						,equipo.getNombre()+" no cumple los requisitos minimos para ser aprobado.");
				return false;
			}
		} else if(equipo.getDeporte().equals("basquet")){
			if (equipo.getClasificacion().equals("Liga A Nacional") || equipo.getClasificacion().equals("Liga B Argentina")) {
				
				return true;
			} else {
				JOptionPane.showMessageDialog(null
						,equipo.getNombre()+" no cumple los requisitos minimos para ser aprobado.");
				return false;
			}
		} else if(equipo.getDeporte().equals("rugby")){
			if (equipo.getClasificacion().equals("Primera A") || equipo.getClasificacion().equals("Primera B")) {
				
				return true;
			}else {
				JOptionPane.showMessageDialog(null
						,equipo.getNombre()+" no cumple los requisitos minimos para ser aprobado.");
				return false;
			}
		}else {
			JOptionPane.showMessageDialog(null
					,equipo.getNombre()+" no es un deporte... supongo.\n"
							+ ":carita_confusa:");
			return false;
		}
	}
	
	public boolean clasificarEquipo(Equipo equipo, Sponsor sponsor) {
		
		if (equipo.clasificarEquipo(sponsor)) {
			return true;			
		} else {
			return false;
		}
	}
	
	
	
}



