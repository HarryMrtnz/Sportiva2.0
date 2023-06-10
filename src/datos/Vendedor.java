package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import interfaces.Conexion;


public class Vendedor extends Usuario {
	
	private int id_vendedor;
	Caja caja = new Caja(0, 0, 0);
	Producto producto = new Producto (0, "", "", "", 0, "");
	Venta venta = new Venta(0, 0, null, 0);

//Conectar a la bdd
	Conexion con = new Conexion();
	Connection conexion = con.conectar();
	PreparedStatement stmt;
	
//Constructor
	public Vendedor(String nombre, String apellido, String dni, String email, String tel, String clave, int p, int s) {
		super(nombre, apellido, dni, email, tel, clave, p, s);
	}

//Getters y Setters
	public int getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}
	
	@Override
	public String toString() {
		return "Vendedor [id_vendedor=" + id_vendedor + "]";
	}

//Metodos
	public Producto seleccionarProducto( Vendedor vendedor) {
		LinkedList<Producto> menu = new LinkedList<Producto>(); //Lista para crear el menu de productos
		Producto productoElegido = new Producto(0, "", "", "", 0, ""); //producto a retornar
		
		for (Producto producto : productoElegido.listaProductos(vendedor.getSucursal())) { //Obtengo lista de un metodo
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
				, "Seleccione el producto a vender:"
				, "SPORTIVA - SUCURSAL N°" + vendedor.getSucursal()
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
		}
		return productoElegido;
	}
	
	public String verStock(int sucursal) {
		return producto.infoProducto(sucursal);
	}

	public String verProductos(int sucursal) {
		return producto.productosPorSucursal(sucursal);
	}
	
	public String hacerPedido() {
		return producto.pedirProducto();
	}
	
	public LinkedList<Venta> verVentas() {
		return venta.listaVentas();
	}

	

	
	
	
}

