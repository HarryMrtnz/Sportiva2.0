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

	LinkedList<Producto> productos = new LinkedList<Producto>();
	LinkedList<Producto> stockEnCorrientes = new LinkedList<Producto>();
	LinkedList<Producto> stockEnBrasil = new LinkedList<Producto>();
	LinkedList<Producto> stockEnGaona = new LinkedList<Producto>();
	
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


//metodos
	public String productosPorSucursal() {
		int numSucursal = 0;
		String lista="", mensaje="", acumulativo ="";
		String[] sucursales = {"Corrientes","Brasil","Gaona"}; //opciones
		
		//pregunto de cual sucursal desea ver los productos
		String sucursal = (String) JOptionPane.showInputDialog(
				null, "Seleccione en cual sucursal se encuentra:","SPORTIVA"
				,JOptionPane.QUESTION_MESSAGE ,null ,sucursales, sucursales[0]);
		//igualo sucursal seleccionada con el numero id de cada sucursal
		if (sucursal.equals("Corrientes")) {
			numSucursal = 1;
		}else if(sucursal.equals("Brasil")){
			numSucursal = 2;
		}else if(sucursal.equals("Gaona")){
			numSucursal = 3;
		}
		//Me traigo solo nombre y stock de todos los productos.
		String sql = "SELECT nombre, inventario.stock "
				+ "FROM producto "
				+ "INNER JOIN inventario "
				+ "ON id_producto = inventario.fk_producto "
				+ "WHERE inventario.fk_deposito = ?;";

		String[] datos = new String[2]; 
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setLong(1, numSucursal);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				datos[0]= result.getString(1);//nombre
				datos[1]= result.getString(2);//stock
				
			//Creo objeto Producto y guardo los datos de la bd en una linkedlist
			//Constructor Producto = id, nombre, descripcion, marca, precio, categoria
				Producto nuevoProducto = new Producto(0, datos[0], null, null, 0, null);
				
			//como stock no forma parte del constructor setteo el stock a cada producto 
				nuevoProducto.setStock(Integer.parseInt(datos[1]));
				
			//Agrego los objetos creados a la lista productos
				productos.add(nuevoProducto);
	
			}
			//recorro con foreach la linkedlist recien creada	
			for (Producto producto : productos) {
				mensaje = "→ Producto: "+producto.getNombre()+" - Stock: "+producto.getStock()+"\n";
				lista = "Hay "+productos.size()+" articulos en sucursal "+sucursal+"\n";
				acumulativo += mensaje;
			}
		}catch(Exception excepcion){
			JOptionPane.showMessageDialog(null, excepcion.getMessage());
		}
		return lista+acumulativo;
	}
	
	public String stockPorProducto() {
		int numSucursal = 0;
		String mensaje="";
		String[] sucursales = {"Corrientes","Brasil","Gaona"}; //Opciones

		String sucursal = (String) JOptionPane.showInputDialog(
				null, "Seleccione de cual sucursal quiere ver:","SPORTIVA"
				,JOptionPane.QUESTION_MESSAGE ,null ,sucursales, sucursales[0]);
		//igualo sucursal seleccionada con el numero id de cada sucursal
		if (sucursal.equals("Corrientes")) {
			numSucursal = 1;
		}else if(sucursal.equals("Brasil")){
			numSucursal = 2;
		}else if(sucursal.equals("Gaona")){
			numSucursal = 3;
		}
		//Me traigo datos de todos los productos.
		String sql = "SELECT nombre, descripcion, marca, precio,"
				+ "inventario.stock, categoria.categoria "
				+ "FROM producto "
				+ "INNER JOIN inventario ON id_producto = inventario.fk_producto "
				+ "INNER JOIN categoria ON id_categoria = fk_categoria "
				+ "WHERE inventario.fk_deposito = ?;"; 

		String[] datos = new String[6];
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setLong(1, numSucursal);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				datos[0]= result.getString(1);//nombre
				datos[1]= result.getString(2);//descripcion
				datos[2]= result.getString(3);//marca
				datos[3]= result.getString(4);//precio
				datos[4]= result.getString(5);//stock
				datos[5]= result.getString(6);//categoria
				Producto nuevoProducto = new Producto(0, datos[0], datos[1], datos[2]
						, Double.parseDouble(datos[3]), datos[5]);
				
				nuevoProducto.setStock(Integer.parseInt(datos[4]));
				
				productos.add(nuevoProducto);
			}
			//Creo un array con el tamaño de la lista de productos.
			String[] aux = new String[productos.size()];
			//Agrego al menu el nombre de cada producto de la sucursal elegida.
			for (Producto producto : productos) {
				aux[productos.indexOf(producto)]= producto.getNombre();
			}
			//muestro menu con todos los productos de la sucursal elegida.
			String opcion = (String) JOptionPane.showInputDialog(
					null ,"Seleccione el producto a vender:","SPORTIVA"
					,JOptionPane.QUESTION_MESSAGE ,null ,aux, aux[0]);
			
			for (Producto producto : productos) {
			//igualo la opcion del producto elegido con el nombre de algun producto en la lista
				if (opcion.equals(producto.getNombre())) {
					
					mensaje = producto.getNombre() +"\n"
							+ producto.getDescripcion()+"\n"
							+ "Marca: "+producto.getMarca()+" - Precio: $"+producto.getPrecio()+"\n"
							+ "Stock: "+producto.getStock()+" - Categoria: "+producto.getCategoria();
				}
			}
		}catch(Exception excepcion){
			mensaje = excepcion.getMessage();
		}
		return mensaje;
	}

	public void obtenerIdPrecioStock () { //
//		int idProducto = 0;
//		double precioProducto = 0;
//		int stockProducto = 0;
//		int numSucursal = 0;
//		String sql = "SELECT nombre, precio, inventario.stock"
//				+ "FROM producto "
//				+ "INNER JOIN inventario ON id_producto = inventario.fk_producto "
//				+ "WHERE inventario.fk_deposito = ?;"; 
//
//		String[] datos = new String[3];
//		try {
//			stmt = conexion.prepareStatement(sql);
//			stmt.setLong(1, numSucursal);
//			ResultSet result = stmt.executeQuery();
//			
//			while(result.next()) {
//				datos[0]= result.getString(1);//nombre
//				datos[1]= result.getString(2);//precio
//				datos[2]= result.getString(3);//stock
//				Producto nuevoProducto = new Producto(0, datos[0], null, null
//						, Double.parseDouble(datos[1]), null);
//				stockEnCorrientes.add(nuevoProducto);
//				nuevoProducto.setStock(Integer.parseInt(datos[2]));
//			}
//			
//			String[] aux = new String[stockEnCorrientes.size()];
//			for (Producto producto : stockEnCorrientes) {
//										
//				aux[stockEnCorrientes.indexOf(producto)]= producto.getNombre();
//			}
//			
//			String prod = (String) JOptionPane.showInputDialog(
//					null ,"Seleccione el producto a vender:","SPORTIVA"
//					,JOptionPane.QUESTION_MESSAGE ,null ,aux, aux[0]);
//			
//			for (Producto producto : stockEnCorrientes) {
//				if (prod.equals(producto.getNombre())) {
//					
//					idProducto = producto.getId_producto();
//					precioProducto = producto.getPrecio();
//					stockProducto = producto.getStock();
//				}
//			}
//		}catch(Exception excepcion){
//			JOptionPane.showMessageDialog(null, excepcion.getMessage());
//		}
	}

	
	
	
}

