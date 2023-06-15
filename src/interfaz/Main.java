package interfaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import datos.Caja;
import datos.Encargado;
import datos.Equipo;
import datos.Gerente;
import datos.Producto;
import datos.Sponsor;
import datos.Sucursal;
import datos.Usuario;
import datos.Vendedor;
import datos.Venta;
import interfaces.Conexion;
import negocio.Verifica;

public class Main {
		
	public static void main(String[] args) {
		
//--- Hecho por Harry ------------------------------------------------------------		
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		
		//conectar a la bdd
		Conexion con = new Conexion();
		Connection conexion = con.conectar();
		PreparedStatement stmt;
		
		//Datos que traigo de la base de datos para iniciar sesion
		String sql = "SELECT nombre, apellido, dni, clave, fk_puesto, fk_sucursal " 
				   + "FROM usuario;";
		String[] datos = new String[6];

		try {
			stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) { //Guardo en array los Datos de la query
				datos[0] = result.getString(1);// nombre
				datos[1] = result.getString(2);// apellido
				datos[2] = result.getString(3);// dni
				datos[3] = result.getString(4);// clave
				datos[4] = result.getString(5);// puesto
				datos[5] = result.getString(6);// sucursal
				
				if (Integer.parseInt(datos[4]) == 1) { //Comparo el puesto con 1 = Gerente
					Gerente gerente = new Gerente(datos[0], datos[1], datos[2], null, null, datos[3], 
							Integer.parseInt(datos[4]), Integer.parseInt(datos[5]));
					usuarios.add(gerente); //Guardo el objeto Gerente en lista usuarios.
					
				} else if (Integer.parseInt(datos[4]) == 2) { //Comparo el puesto con 2 = Vendedor
					Vendedor vendedor = new Vendedor(datos[0], datos[1], datos[2], null, null, datos[3], 
							Integer.parseInt(datos[4]), Integer.parseInt(datos[5]));
					usuarios.add(vendedor); //Guardo el objeto Vendedor en lista usuarios.
				} else if (Integer.parseInt(datos[4]) == 3) { //Comparo el puesto con 3 = Encargado
					Encargado encargado= new Encargado(datos[0], datos[1], datos[2], null, null, datos[3], 
							Integer.parseInt(datos[4]), Integer.parseInt(datos[5]));
					usuarios.add(encargado); //Guardo el objeto Encargado en lista usuarios.
				}
			}
		} catch (Exception excepcion) {
			System.out.println("Error al buscar usuario.\n"+ excepcion.getMessage()+"\n");
		}

		//Inicio el login
		iniciarSesion(usuarios);

	} //Fin del Main
	
	public static void iniciarSesion(LinkedList<Usuario> usuarios) {
		String dni =  JOptionPane.showInputDialog("BIENVENIDO A SPORTIVA\n"
				+ "El ingreso requiere su N° dni, y clave.\n\nIngrese su DNI");
		String clave = JOptionPane.showInputDialog("Ingrese su CLAVE");
//		int numSucursal = 0; // Para usar al vender productos de la sucursal a la que perteneces
			for (Usuario usuario : usuarios) { //Recorro lista usuarios
			//Si logueo exitoso abre el menu del puesto que corresponda
				if (usuario.login(dni, clave)) {
					if (usuario.getPuesto()==1) {
//						 numSucursal = 1;
						JOptionPane.showMessageDialog(null, "Sesion iniciada correctamente!"
								+ "\n Bienvenido "+usuario.getNombre()+" "+usuario.getApellido() );
						menuGerente(usuarios);
					}else if (usuario.getPuesto()==2) {
//						numSucursal = 2;
						JOptionPane.showMessageDialog(null, "Sesion iniciada correctamente!"
								+ "\n Bienvenido "+usuario.getNombre()+" "+usuario.getApellido() );
						menuVendedor(usuarios);
					}else if (usuario.getPuesto()==3) {
//						numSucursal = 3;
						JOptionPane.showMessageDialog(null, "Sesion iniciada correctamente!"
								+ "\n Bienvenido "+usuario.getNombre()+" "+usuario.getApellido() );
						menuEncargado(usuarios);
					}
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
		Verifica ver = new Verifica();
		Verifica verificar = new Verifica();
		Gerente gerente = new Gerente("", "", "", "", "", "", 0, 0);

		String[] opciones = { " Visualizar usuario",
							  " Agregar usuario",
							  " Mostrar lista usuarios",
							  " Eliminar usuario",
							  " Editar usuario",
							  " Ver sponsors disponibles",
							  " Ver equipos clasificados",
							  " Ver equipos pendientes",
							  " Salir" };
		
//--- Hecho por Fran ------------------------------------------------------------		
		String[] opcionesP = {"1", "2", "3"};
		String[] opcionesS = {"1", "2", "3"};
		String opcion="";
		
		do {
			opcion = (String)JOptionPane.showInputDialog(null, "Seleccione la opcion deseada",
					"SPORTIVA - MENU GERENTE",JOptionPane.QUESTION_MESSAGE,null, opciones,opciones[0]);
			
			switch (opcion) {
			case " Visualizar usuario":
				int idE=0;
				do {
					try {
						idE= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del empleado a traer"));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Debe ingresar un numero");
					}
				} while (idE<=0);
				
				try {
					if (ver.verificaUsuario(idE).isEmpty()) {
						
					} else {
					JOptionPane.showMessageDialog(null, ver.verificaUsuario(idE));
					}
				} catch (Exception e) {
					
				}
				break;
			case " Agregar usuario":
				
				String nombre= JOptionPane.showInputDialog("Ingrese nombre del nuevo usuario");
				String apellido= JOptionPane.showInputDialog("Ingrese apellido del nuevo usuario");
				String dni= JOptionPane.showInputDialog("Ingrese dni del nuevo usuario");
				String email= JOptionPane.showInputDialog("Ingrese email del nuevo usuario");
				String telefono= JOptionPane.showInputDialog("Ingrese telefono del nuevo usuario");
				int puesto= Integer.parseInt((String) JOptionPane.showInputDialog(
						null, "Seleccione el puesto\n1 - Gerente\n2 - Vendedor\n3 - Encargado",
						"SPORTIVA - MENU GERENTE",JOptionPane.DEFAULT_OPTION,null, opcionesP,opcionesP[0]));
				int sucursal= Integer.parseInt((String) JOptionPane.showInputDialog(
						null, "Ingrese la sucursal\n1 - Corrientes\n2 - Brasil\n3 - Gaona",
						"SPORTIVA - MENU GERENTE", JOptionPane.DEFAULT_OPTION,null, opcionesS,opcionesS[0]));
				String clave = JOptionPane.showInputDialog("Ingrese clave del nuevo usuario");
				
				if (ver.verificarAgregar(nombre, apellido, dni, email, telefono, clave, puesto, sucursal)) {
					JOptionPane.showMessageDialog(null, "Usuario creado con exito");
				} else {
					JOptionPane.showMessageDialog(null, "Error al crear el usuario");
				}
				
				break;
			case " Mostrar lista usuarios":
				
				if( ver.verificaLista().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Lista vacia, todavia no hay usuarios");
				}else {
					JOptionPane.showMessageDialog(null, ver.verificaLista());
				}
				
				break;
			case " Eliminar usuario":
				
				// Verifico que la Base de datos tenga usuarios que eliminar
			    if (ver.verificaLista().isEmpty()) {
			        JOptionPane.showMessageDialog(null, "No hay usuarios disponibles para eliminar");
			    }
			    // Comienzo a eliminar
			    else {
			    	// Creo la lista de usuarios de mi BDD
			        LinkedList<Gerente> usuariosE = ver.verificaLista();
			        String[] nombres = new String[usuariosE.size()];
			        // Recorro la BDD y lleno la lista de usuarios con todos los usuarios de la BDD
			        for (int i = 0; i < usuariosE.size(); i++) {
			            nombres[i] = usuariosE.get(i).getNombre();
			        }
			        // Selecciono el usuario que quiero eliminar
			        String nombreselecionado = (String) JOptionPane.showInputDialog(null, 
			        		"Elija que usuario quiere eliminar", "Menu eliminar",
			                JOptionPane.DEFAULT_OPTION, null, nombres, nombres[0]);
			        // Creo la variable "Aeliminar" donde voy a poner el usuario que quiero borrar de mi BDD
			        Gerente Aeliminar = null;
			        // Ingreso el usuario a eliminar en la variable "Aeliminar"
			        for (Gerente usuario : usuariosE) {
			            if (usuario.getNombre().equals(nombreselecionado)) {
			                Aeliminar = usuario;
			            }
			            
						try {
							if (ver.verificaUsuario(Integer.parseInt( Aeliminar.getId())).isEmpty()) {
								
							} else if (ver.verificarEliminar(Integer.parseInt( Aeliminar.getId()))) {
								JOptionPane.showMessageDialog(null, "Se elimino correctamente el usuario");
							}
							else {
							JOptionPane.showMessageDialog(null, ver.verificaUsuario(Integer.parseInt( Aeliminar.getId())));
							}
						} catch (Exception e) {
							
						}
			        }
			    }
			    break;
			case " Editar usuario":
				
				// Verifico que la Base de datos tenga usuarios que editar
			    if (ver.verificaLista().isEmpty()) {
			        JOptionPane.showMessageDialog(null, "No hay usuarios disponibles para editar");
			    }
			    // Comienzo a editar
			    else {
			    	// Creo la lista de usuarios de mi BDD
			        LinkedList<Gerente> usuariosL = ver.verificaLista();
			        String[] nombres = new String[usuariosL.size()];
			        // Recorro la BDD y lleno la lista de usuarios con todos los usuarios de la BDD
			        for (int i = 0; i < usuariosL.size(); i++) {
			            nombres[i] = usuariosL.get(i).getNombre();
			        }
			        // Selecciono el usuario que quiero editar
			        String nombreselecionado = (String) JOptionPane.showInputDialog(null, "Elija que usuario quiere editar", "Menu editar",
			                JOptionPane.DEFAULT_OPTION, null, nombres, nombres[0]);
			        // Creo la variable "Aeditar" donde voy a colocar todos los datos de la persona a editar
			        Gerente Aeditar = null;
			        // Ingreso el usuario a editar en la variable "Aeditar"
			        for (Gerente usuario : usuariosL) {
			            if (usuario.getNombre().equals(nombreselecionado)) {
			                Aeditar = usuario;
			                JOptionPane.showMessageDialog(null,"Usuario a editar: \n" +  ver.verificaUsuario(Integer.parseInt(Aeditar.getId())));
			            }
			        }
			        
			        String[] editar = { "Nombre", "Apellido", "Dni", "Email", "Telefono",
			        					"Clave", "Sucursal", "Puesto", "Finalizar" };
			        String seleccionar = "";

			        do {
			            seleccionar = (String) JOptionPane.showInputDialog(null,
			                    "Elija cual dato va a editar del usuario " +
			                    Aeditar.getNombre() + " " + Aeditar.getApellido() , "", 
			                    JOptionPane.DEFAULT_OPTION, null, editar, editar[0]);

			            switch (seleccionar) {
			                case "Nombre":
			                    Aeditar.setNombre(JOptionPane.showInputDialog("Elija nombre nuevo"));
			                    break;
			                case "Apellido":
			                    Aeditar.setApellido(JOptionPane.showInputDialog("Elija apellido nuevo"));
			                    break;
			                case "Dni":
			                    Aeditar.setDni(JOptionPane.showInputDialog("Elija DNI nuevo"));
			                    break;
			                case "Email":
			                	Aeditar.setEmail(JOptionPane.showInputDialog("Elija email nuevo"));
			                	break;
			                case "Telefono":
			                	Aeditar.setTelefono(JOptionPane.showInputDialog("Elija telefono nuevo"));
			                	break;
			                case "Clave":
			                	Aeditar.setClave(JOptionPane.showInputDialog("Elija Clave nueva"));
			                	break;
			                case "Sucursal":
								Aeditar.setSucursal(Integer.parseInt((String) JOptionPane.showInputDialog(null, 
										"Ingrese la sucursal\n1 - Corrientes\n2 - Brasil\n3 - Gaona",
										"SPORTIVA - MENU GERENTE",JOptionPane.DEFAULT_OPTION,null, opcionesS,opcionesS[0])));
			                	break;
			                case "Puesto":
			                	Aeditar.setPuesto(Integer.parseInt((String) JOptionPane.showInputDialog(null, 
			                			"Seleccione el puesto\n1 - Gerente\n2 - Vendedor\n3 - Encargado",
										"SPORTIVA - MENU GERENTE",JOptionPane.DEFAULT_OPTION,null, opcionesP,opcionesP[0])));
			                	break;
			                default:
			                    break;
			            }
			        } while (!seleccionar.equals("Finalizar"));
			        
			        ver.verificaEditar(Aeditar);
			        JOptionPane.showMessageDialog(null,"Usuario editado: \n"
			        		+ ver.verificaUsuario(Integer.parseInt(Aeditar.getId())));
			    }
			    break;
				
//--- Hecho por Kari ------------------------------------------------------------
			    
			case " Ver sponsors disponibles":
				if( verificar.verificaLista1().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Lista vacia");
				}else {
					JOptionPane.showMessageDialog(null, verificar.verificaLista1());
				}
				break;
			case " Ver equipos clasificados":
				if( verificar.verificaLista2().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Lista vacia");
				}else {
					JOptionPane.showMessageDialog(null, verificar.verificaLista2());
				}
				break;
			case " Ver equipos pendientes":

				Equipo equipo = gerente.seleccionarEquipo();
				
				if (verificar.aprobarEquipo(equipo)) {
					
					int respuesta =	JOptionPane.showConfirmDialog(null 
							, "Equipo: "+equipo.getNombre()+ " - Deporte: "+equipo.getDeporte()+"\n"
							+ "Clasificación: "+equipo.getClasificacion()+".\n\n"
							+ "¿Aprobar a "+equipo.getNombre()+" y otorgarle un sponsor?");
					
					if (respuesta == 0) {
						Sponsor sponsor = gerente.seleccionarSponsor(); //selecciono sponsor
						verificar.clasificarEquipo(equipo, sponsor); //Guardo en la bdd.
						JOptionPane.showMessageDialog(null, "Sponsor agregado exitosamente al equipo "+equipo.getNombre());
					}
				}else {
					JOptionPane.showMessageDialog(null
							, "Equipo: "+equipo.getNombre()+ " - Deporte: "+equipo.getDeporte()+"\n"
							+ "Clasificación: "+equipo.getClasificacion()+".\n\n");
				}
				break;
				
			default:
				break;
			}
			
		} while(!opcion.equals(" Salir" ));
	}
	
//Menu que debe aparecer si el usuario es Vendedor
	public static void menuVendedor(LinkedList<Usuario> usuarios) {
	
		String[] opciones = { " Realizar una venta",
							  " Revisar stock de un producto",
							  " Ver stock de todos los productos",
							  " Realizar pedido al deposito",
							  " Ver ventas realizadas",
							  " Recaudaciones",
							  " Salir" };
		String opcion="";
		
//--- Hecho por Harry ------------------------------------------------------------		
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");
		String fecha = fechaActual.format(formato);
		int idCaja = 0;
		String nombreSucursal = "";
		
		Verifica verifica = new Verifica();
		Vendedor vendedor = new Vendedor("Harry", "Martinez", "", "", "", "", 2, 1);
		Producto producto = new Producto(0, "", "", "", 0, "");
		Sucursal sucursal = new Sucursal(0, "", "");
		        Caja caja = new Caja(0, 0, 0);
		      Venta venta = new Venta(0, 0, null, 0);
		
		if (vendedor.getSucursal() == 1) {
			nombreSucursal = "Corrientes";
		} else if(vendedor.getSucursal() == 2){
			nombreSucursal = "Brasil";
		} else if(vendedor.getSucursal() == 3){
			nombreSucursal = "Gaona";
		}	
		
		do {
			String[] pagos = {"Tarjeta","Efectivo","Mercado Pago"};
			
			opcion = (String)JOptionPane.showInputDialog(null, "Seleccione la opcion deseada",
				"SPORTIVA - MENU VENDEDOR",JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[0]);
			switch (opcion) {
			case " Realizar una venta":
				
				 idCaja = caja.selecionarCaja(vendedor.getSucursal());
				
		  /* - Creo el producto seleccionado.  
		     - Parametro vendedor = para saber en que sucursal estoy
			  	Y muestra solo productos disponibles de esa sucursal. */
				Producto productoVendido = vendedor.seleccionarProducto(vendedor);
				
				int cantidad = Integer.parseInt(JOptionPane.showInputDialog("ingrese cantidad de productos:"));

				String pago = (String) JOptionPane.showInputDialog(
						null, "Forma de pago del cliente:","SPORTIVA"
						,JOptionPane.QUESTION_MESSAGE ,null ,pagos, pagos[0]);
				
				if (verifica.agregarVenta(productoVendido ,cantidad, idCaja, pago) //Completa tablas de venta
					&&	(productoVendido.restarStock(cantidad, vendedor))) {//Actualiza stock del producto vendido
					System.out.println(vendedor.getNombre()+ " ha realizado una venta\n");
					
					int respuesta = JOptionPane.showConfirmDialog(null
							, "¡Venta realizada exitosamente!\n"
						    + "Pero aun no somos ricos...\n\n"
						    + "¿Desea imprimir Factura?"); 
					
					if (respuesta == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null
						, venta.factura(vendedor, productoVendido, cantidad, idCaja, pago, fechaActual));
					}
					
				} else {
					System.out.println(vendedor.getNombre()+ " ha fallado la venta.\n");
					JOptionPane.showMessageDialog(null, "Venta fallida, hoy no se come");
				}

				break;
			case " Revisar stock de un producto":
				//Pregunto de cual sucursal desea ver el producto, y guardo respuesta.
				int numSucursal = sucursal.seleccionarSucursal();
				
				//Muestro mensaje con producto elegido
				JOptionPane.showMessageDialog(null, verifica.verStock(numSucursal));
				
				break;
			case " Ver stock de todos los productos":
				//Pregunto de cual sucursal desea ver el producto, y guardo respuesta.
				int numSucursal2 = sucursal.seleccionarSucursal();
				
				//Muestro mensaje con la lista elegida
				JOptionPane.showMessageDialog(null, verifica.verProductos(numSucursal2));
				
				break;
			case " Realizar pedido al deposito":
				
				producto.setNombre(vendedor.hacerPedido());
				producto.setStock( Integer.parseInt(JOptionPane.showInputDialog(
						"Ingrese cantidad del producto a pedir al deposito"))); 
				
				if (verifica.hacerPedidoDeposito(producto)) {
					JOptionPane.showMessageDialog(null, "SPORTIVA  -  " + fecha + "\n"
							+ "_________________________________________\n"
							+ "SOLICITUD DE PEDIDO ENVIADO.\n"
							+ "DEPOSITO DESTINO: "+nombreSucursal + ".\n\n"
							+ "VENDEDOR: " + vendedor.getNombre() + " " + vendedor.getApellido() + "\n"
							+ "→  PEDIDO: " + producto.getNombre() + ".\n"
							+ "→  x"+producto.getStock() + " UNIDADES.\n"
							+ "_________________________________________\n"
							+ "                       ←  Sportiva  →");
				} else {
					System.out.println("Error al mandar solicitd de pedido al depostio\n");
					JOptionPane.showMessageDialog(null, "Error al mandar solicitd de pedido al depostio");
				}
				
				break;
			case " Ver ventas realizadas":
				if (verifica.listaVentas().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Lista tan vacia como mi alma.\n"
							+ "No se ha realizado ninguna venta");
				} else {
					JOptionPane.showMessageDialog(null, verifica.listaVentas());
				}
				
				break;
			case " Recaudaciones":
				
				String[] recaudaciones = { " 1 - De cajas en sucursal",
									 " 2 - Total de la sucursal",
			      					 " 3 - Total",
									 " 4 - Volver"};
			    String recaudacion;
			
			      do {
			          recaudacion = (String) JOptionPane.showInputDialog(null
		        		  , "Podras ver la recaudacion de las ventas realizadas.\n"
		                  + "Elige cual recaudacion total deseas ver: "
		        		  , "SPORTIVA - RECAUDACIONES",JOptionPane.QUESTION_MESSAGE,null, recaudaciones, recaudaciones[0]);
		
			          switch (recaudacion) {

			              case " 1 - De cajas en sucursal":

			            	  idCaja = caja.selecionarCaja(vendedor.getSucursal());
			            	  
			            	  if (verifica.recaudacionCaja(idCaja)) {
			            			JOptionPane.showMessageDialog(null
			            			, "RECAUDACION SUCURSAL: " + nombreSucursal + "\n"
			            			+ "CAJA N°" + idCaja + "\n"
			            			+ " → TOTAL $" + caja.recaudacionCaja(idCaja));

							} else {
								 JOptionPane.showMessageDialog(null
									, "RECAUDACION SUCURSAL: " + nombreSucursal + "\n"
			            			+ "CAJA N°" + idCaja + "\n"
			            			+ "→ No se han registrado ventas\n\n "
			            			+ "→ Por favor, ¡Pongase a trabajar!)");
							}
			            	  
			            	  break;
			              case " 2 - Total de la sucursal":
			            	  
			            	  if(verifica.recaudacionSucursal(vendedor.getSucursal())){
			            		  JOptionPane.showMessageDialog(null
			            			, "RECAUDACION SUCURSAL: " + nombreSucursal + "\n"
			            			+ " → TOTAL $" + caja.recaudacionSucursal(vendedor.getSucursal()));			            		  
			            	  } else {
			            		  JOptionPane.showMessageDialog(null
									, "RECAUDACION SUCURSAL: " + nombreSucursal + "\n"
			            			+ "CAJA N°" + idCaja + "\n"
			            			+ "→ No se han registrado ventas\n\n"
			            			+ "→ Por favor, ¡Pongase a trabajar!)");
			            	  }
			            	  break;
			              case " 3 - Total":
			            	  
			            	  if(verifica.recaudacionTotal()){
			            		  JOptionPane.showMessageDialog(null
			            			, "RECAUDACION GENERAL: \n"
			            			+ " → TOTAL $" + caja.recaudacionTotal());	
			            	  } else {
			            		  JOptionPane.showMessageDialog(null
									, "RECAUDACION GENERAL: \n"
			            			+ "→ No se han registrado ventas\n\n"
			            			+ "→ Por favor, ¡Pongase a trabajar! ");
			            	  }
			            	  break;

			              default:
			            	  
			                  break;
			          }
			      } while (!recaudacion.equals(" 4 - Volver"));
				break;
				
			default: 
				iniciarSesion(usuarios);
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
		
//--- Hecho por Kari ------------------------------------------------------------		
		String[] opcioness = {"1-Calzado", "2-Remera", "3-Pantalon", "4-Medias" , "5-Pelota", "6-Abrigo"};
		String opcion="";
		
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");
		String fecha = fechaActual.format(formato);
		
		Encargado encargado = new Encargado("Karina", "Litvak", "", "", "", "", 3, 1);
		Verifica verificar = new Verifica();
		Producto productos = new Producto(0, "", "", "", 0, "");
		Sucursal sucursal = new Sucursal(0, "", "");
		
		do {
			opcion = (String)JOptionPane.showInputDialog(null, "Seleccione la opcion deseada",
					"SPORTIVA - MENU ENCARGADO",JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[0]);
			
			switch (opcion) {
			case " Ver stock de todos los productos":
				
				int numeroSucursal = sucursal.seleccionarSucursal();
				
				JOptionPane.showMessageDialog(null, verificar.verProductos(numeroSucursal));
				
				break;
			case " Ver stock de un producto":
				
				int numeroSucursal1 = sucursal.seleccionarSucursal();
				JOptionPane.showMessageDialog(null, verificar.verStock(numeroSucursal1));
				break;
			case " Agregar nuevo producto":
				String Nombre= JOptionPane.showInputDialog("Ingrese nombre del nuevo producto");
				String Descripcion= JOptionPane.showInputDialog("Ingrese descripcion del nuevo producto");
				String Marca= JOptionPane.showInputDialog("Ingrese marca del nuevo producto");
				double Precio=Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio del nuevo producto"));
				String Categoria=(String) JOptionPane.showInputDialog(
						null, "Ingrese la categoria",
						"SPORTIVA - MENU ENCARGADO", JOptionPane.DEFAULT_OPTION,null, opcioness, opcioness[0]);
				
				if (verificar.verificarAgregarProducto(Nombre, Descripcion, Marca, Precio, Categoria)) {
					JOptionPane.showMessageDialog(null, "Producto creado con exito");
				} else {
					JOptionPane.showMessageDialog(null, "Error al crear el producto");
				}
				
				break;
				
			case " Renovar stock de un producto":
			
			    //Pido que elija un producto desde el metodo seleccionarProducto();
			    //Guardo el producto del metodo seleccionarProducto(), en un nuevo Producto productoAcambiar;
				Producto productoAcambiar = encargado.seleccionarProducto(encargado);
				
				int nuevoStock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo stock:"));
		        productos.setStock(nuevoStock);

		        //Mostrar mensajes.
		        if (verificar.verificarStock(productoAcambiar, nuevoStock)) { //Si es verdadero...
					JOptionPane.showMessageDialog(null,"Se ha modificado el stock correctamente.");
				} else {
					JOptionPane.showMessageDialog(null, "Error, no se ha podido actualizar Stock del producto.");
				}			    
			    
				break;
			case " Hacer pedido al proveedor":
				productos.setNombre(encargado.hacerPedidoProveedor());
				productos.setStock( Integer.parseInt(JOptionPane.showInputDialog(
						"Ingrese cantidad del producto a pedir al proveedor"))); 
				
				if (verificar.hacerPedidoDeposito(productos)) {
					
					JOptionPane.showMessageDialog(null, "SPORTIVA  -  " + fecha + "\n"
							+ "_________________________________________\n"
							+ "SOLICITUD DE PEDIDO ENVIADO.\n"
							+ "DESTINO PROVEEDOR.\n"
							+ "ENCARGADO: " + encargado.getNombre() + " " + encargado.getApellido() + "\n"
							+ "→  PEDIDO: " + productos.getNombre() + ".\n"
							+ "→  x"+productos.getStock() + " UNIDADES.\n"
							+ "_________________________________________\n"
							+ "                       ←  Sportiva  →");
				} else {
					System.out.println("Error al mandar solicitd de pedido al proveedor\n");
					JOptionPane.showMessageDialog(null, "Error al mandar solicitd de pedido al proveedor");
				}
				break;
				
			default: 
				iniciarSesion(usuarios);
				break;			
			}
		} while(!opcion.equals(" Salir" ));
	}
	



}
	
