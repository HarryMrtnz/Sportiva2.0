package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import interfaces.Conexion;

public class Producto {
	private int id_producto;
	private String nombre;
	private String descripcion;
	private String marca;
	private double precio;
	private String categoria;
	private int stock;

	
//conectar a bdd	
	Conexion con = new Conexion();
	Connection conexion = con.conectar();
	PreparedStatement stmt;
		
//constructor
	public Producto(int id_producto, String nombre, String descripcion, String marca, double precio, String categoria) {
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.precio = precio;
		this.categoria = categoria;
		//this.stock = stock;
	}

//getters y setters
	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [id_producto=" + id_producto + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", marca=" + marca + ", precio=" + precio + ", categoria=" + categoria + "]\n";
	}
	
//--- Hecho por Harry ------------------------------------------------------------

//metodos
	public LinkedList<Producto> listaProductos(int numDeposito) {
		LinkedList<Producto> listaProductos = new LinkedList<Producto>(); //Lista a retornar
		Producto productoElegido = null;
		
		String sql = "SELECT id_producto, nombre, descripcion, marca, precio, "
				+ "inventario.stock, categoria.categoria "
				+ "FROM producto "
				+ "INNER JOIN inventario ON id_producto = inventario.fk_producto "
				+ "INNER JOIN categoria ON id_categoria = fk_categoria "
				+ "WHERE inventario.fk_deposito = ? "; 

		String[] datos = new String[7];
		
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setLong(1, numDeposito);
			ResultSet result = stmt.executeQuery();
//			conexion.close();
			
			//Traigo datos de todos los productos.
			while(result.next()) {
				datos[0]= result.getString(1);//id
				datos[1]= result.getString(2);//nombre
				datos[2]= result.getString(3);//descripcion
				datos[3]= result.getString(4);//marca
				datos[4]= result.getString(5);//precio
				datos[5]= result.getString(6);//stock
				datos[6]= result.getString(7);//categoria
				
				productoElegido = new Producto(Integer.parseInt(datos[0]), datos[1],
						datos[2], datos[3], Double.parseDouble(datos[4]), datos[6]);
			//como stock no forma parte del constructor setteo el stock a cada producto 
				productoElegido.setStock(Integer.parseInt(datos[5]));
				
				listaProductos.add(productoElegido);
			}
		}catch(Exception excepcion){
			System.out.println("Error al seleccionar producto:\n"+excepcion.getMessage()+"\n");
		}
		return listaProductos;
	}

	
	public String infoProducto( int sucursal) {
		String mensaje = "";
		LinkedList<Producto> menu = new LinkedList<Producto>(); //Lista para crear el menu de productos
		Producto productoElegido = new Producto(0, "", "", "", 0, ""); //producto a retornar
		
		for (Producto producto : listaProductos(sucursal)) { //Obtengo lista de un metodo
			menu.add(producto); //Guardo los productos en la lista de menu
		}

		//Creo un array con el tamaño de la lista de productos.
		String[] aux = new String [menu.size()];
		//Agrego al menu el nombre de cada producto de la sucursal elegida.
		for (Producto producto : menu) {
			aux[menu.indexOf(producto)]= producto.getNombre();
		}
		//muestro menu con todos los productos de la sucursal elegida.
		String opcion = (String) JOptionPane.showInputDialog(null
					, "Estos son los productos disponibles.\n"
					+ "Seleccione uno:","SPORTIVA - SUCURSAL N°" + sucursal
					, JOptionPane.QUESTION_MESSAGE ,null ,aux, aux[0]);
		
		for (Producto producto : menu) {
		//igualo la opcion del producto elegido con el nombre de algun producto en la lista
			if (opcion.equals(producto.getNombre())) {
			//Guardo en el producto elegido todos sus atributos.
				productoElegido.setId_producto(producto.getId_producto());
				productoElegido.setNombre(producto.getNombre());
				productoElegido.setDescripcion(producto.getDescripcion());
				productoElegido.setMarca(producto.getMarca());
				productoElegido.setPrecio(producto.getPrecio());
				productoElegido.setCategoria(producto.getCategoria());
				productoElegido.setStock(producto.getStock());
			}
			
			mensaje = productoElegido.getNombre() +"\n"+ productoElegido.getDescripcion()+"\n"
			+ "Marca: "+productoElegido.getMarca()+" - Precio: $"+productoElegido.getPrecio()+"\n"
			+ "Stock: "+productoElegido.getStock()+" - Categoria: "+productoElegido.getCategoria();
		}
		return mensaje;
	}

	
	public String productosPorSucursal(int sucursal) {
		String lista = "", mensaje = "", acumulativo = ""; //Mensajes a retornar
		String nombreSucursal = "";
		//Paso de numero de sucursal a nombre de sucursal
		if (sucursal == 1) {
			nombreSucursal = "Corrientes";
		} else if (sucursal == 2) {
			nombreSucursal = "Brasil";
		} else if (sucursal == 3) {
			nombreSucursal = "Gaona";
		}
		LinkedList<Producto> productos = new LinkedList<Producto>();

		//recorro con foreach la linkedlist recien creada	
		for (Producto producto : listaProductos(sucursal)) { //Obtengo lista de un metodo
			productos.add(producto); //Guardo los productos en la lista 

			mensaje = "→ Producto: "+producto.getNombre()+" - Stock: "+producto.getStock()+"\n";
			lista = "Hay "+productos.size()+" articulos en sucursal "+nombreSucursal+"\n";
			
			acumulativo += mensaje;
		}
		return lista + acumulativo;
	}
	
	
	public boolean restarStock(int cantidad, Vendedor vendedor) {
		
		//Resto del Stock la cantidad vendida en la ultima venta.
		int nuevoStock = this.getStock()-cantidad; 
		this.setStock(nuevoStock); //Setteo nuevo stock al objeto producto. 
		
		//Actualizo el stock en la bdd.
		String sql = "UPDATE inventario SET stock = ? "
				+ "WHERE fk_producto = ? "
				+ "AND fk_deposito = ? ";
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setLong(1, this.getStock());
			stmt.setLong(2, this.getId_producto());
			stmt.setLong(3, vendedor.getSucursal());
			stmt.executeUpdate();
//			conexion.close();
			return true;
				
		}catch(Exception excepcion) {
			System.out.println("Error al actualizar stock:\n"+ excepcion.getMessage()+"\n");
			return false;
		}
	}

	public String pedirProducto() {
		LinkedList<Producto> productos = new LinkedList<Producto>();

		//Me traigo datos de todos los productos.
		String nombreProducto;
		String opcion = "";
		String sql = "SELECT p.nombre FROM producto p "
				+ "INNER JOIN inventario i ON i.fk_producto = p.id_producto "
				+ "WHERE i.fk_deposito = 1 "; 

		try {
			stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
//			conexion.close();
			
			while(result.next()) {
				nombreProducto = result.getString(1);

				Producto producto = new Producto(0, nombreProducto, "", "", 0, "");
				
				productos.add(producto);
			}
			//Creo un array con el tamaño de la lista de productos.
			String[] aux = new String[productos.size()];
			//Agrego al menu el nombre de cada producto de la sucursal elegida.
			for (Producto producto : productos) {
				aux[productos.indexOf(producto)]= producto.getNombre();
			}
			//muestro menu con todos los productos
			opcion = (String) JOptionPane.showInputDialog(null
					, "Seleccione el producto a pedir: ","SPORTIVA"
					, JOptionPane.QUESTION_MESSAGE ,null ,aux, aux[0]);
			
			return opcion;  //Devuelve el nombre de un producto.

		}catch(Exception excepcion){
			System.out.println("Error al mostrar productos:\n"+excepcion.getMessage()+"\n");
			return "";
		}
	}

//--- Hecho por Kari ------------------------------------------------------------	
	public boolean agregarProducto() {
		String sql = "INSERT INTO `producto`( `nombre`, `descripcion`, `marca`, `precio`, `fk_categoria`) "
				+ "VALUES (?,?,?,?,?)";

		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setString(1, this.getNombre());  
			stmt.setString(2, this.getDescripcion());
			stmt.setString(3, this.getMarca());
			stmt.setDouble(4, this.getPrecio());
			stmt.setString(5, this.getCategoria());
			
			stmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		} 
    }
	
	public boolean modificarStock(int stock, Encargado encargado) {	
		String sql = "UPDATE inventario SET stock = ? "
				+ "WHERE fk_producto = ? "
				+ "AND fk_deposito = ? ";
		try {
			
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, stock);
			stmt.setLong(2, this.getId_producto());
			stmt.setLong(3, encargado.getSucursal());
			
			stmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	
}

