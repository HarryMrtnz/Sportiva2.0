package datos;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Encargado extends Usuario {
	int id_encargado;
	Producto producto = new Producto (0, "", "", "", 0, "");
	
	
//--- Hecho por kari ------------------------------------------------------------

	public Encargado(String nombre, String apellido, String dni, String email, String tel, String clave, int p, int s) {
		super(nombre, apellido, dni, email, tel, clave, p, s);
	}

	public int getId_encargado() {
		return id_encargado;
	}

	public void setId_encargado(int id_encargado) {
		this.id_encargado = id_encargado;
	}

	@Override
	public String toString() {
		return "Encargado [id_encargado=" + id_encargado + "]";
	}

	public String verStockProductos(int sucursal) {
		return producto.productosPorSucursal(sucursal);
	}
	
	public String verStockProducto(int sucursal) {
		return producto.infoProducto(sucursal);
	}
	
	public String hacerPedidoProveedor() {
		return producto.pedirProducto();
	}

	public boolean agregarProducto() {
		return producto.agregarProducto();
	}

	public Producto seleccionarProducto( Encargado encargado) {
		LinkedList<Producto> menu = new LinkedList<Producto>(); //Lista para crear el menu de productos
		Producto productoElegido = new Producto(0, "", "", "", 0, ""); //producto a retornar
		
		//Obtengo lista de un metodo.
		for (Producto producto : productoElegido.listaProductos(encargado.getSucursal())) {
			menu.add(producto); //Guardo los productos en la lista de menu
		}

		//Creo un array con el tamanio de la lista de productos.
		String[] aux = new String [menu.size()];
		//Agrego al menu el nombre de cada producto de la sucursal elegida.
		for (Producto producto : menu) {
			aux[menu.indexOf(producto)]= producto.getNombre();
		}
		//muestro menu con todos los productos de la sucursal elegida.
		String opcion = (String) JOptionPane.showInputDialog(null
				, "Seleccione el producto a vender:"
				, "SPORTIVA - SUCURSAL N°" + encargado.getSucursal()
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
	


}

