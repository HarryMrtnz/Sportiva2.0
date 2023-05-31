package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import interfaces.Conexion;


public class Vendedor extends Usuario {
	
	private int id_vendedor;
	

	Conexion con = new Conexion();
	Connection conexion = con.conectar();
	PreparedStatement stmt;
		
	public Vendedor(String nombre, String apellido, String dni, String email, String tel, String clave, int p, int s) {
		super(nombre, apellido, dni, email, tel, clave, p, s);
	}

	public int getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}
	
	@Override
	public String toString() {
		return "Vendedor [id_vendedor=" + id_vendedor + ", con=" + con + ", conexion="
				+ conexion + ", stmt=" + stmt + "]";
	}

	
	
	
	public boolean realizarVenta() {
		Venta nuevaVenta = new Venta(0, null, "", 0);
		
		nuevaVenta.guardar();
		return true;
	}
	
	public String verStockProductos() {
		Producto nuevoProducto = new Producto (0, "", "", "", 0, "");
		
		return nuevoProducto.productosPorSucursal();
	}
	
	public String verStock() {
		Producto nuevoProducto = new Producto (0, "", "", "", 0, "");
		
		return nuevoProducto.stockPorProducto();
	}
	
//	public void obtenerId() {
//		Producto nuevoProducto = new Producto (0, "", "", "", 0, "");
//		
//		//return nuevoProducto.obtenerId();
//	}

	public void hacerPedido() {

	}
	
	public void verVentas() {
		
	}
}

