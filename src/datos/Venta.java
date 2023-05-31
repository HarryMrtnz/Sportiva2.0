package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;

import interfaces.Conexion;

public class Venta {
	
	private int id_venta;
	private Caja caja;
	private String fecha;
	private double total;
	
	//detalle_venta
	private Producto producto;
	private int cantidad;
	private String metodoPago;
	
	LinkedList<Venta>ventas = new LinkedList<Venta>();
	
	//conectar a bdd	
		Conexion con = new Conexion();
		Connection conexion = con.conectar();
		PreparedStatement stmt;
	
	public Venta(int id_venta, Caja caja, String fecha, double total) {
		super();
		this.id_venta = id_venta;
		this.caja = caja;
		this.fecha = fecha;
		this.total = total;
	}

	public int getId_venta() {
		return id_venta;
	}

	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	@Override
	public String toString() {
		return "Venta [id_venta=" + id_venta + ", total=" + total + ", fecha=" + fecha + ", metodoPago=" + metodoPago
				+ ", producto=" + producto + ", cantidad=" + cantidad + "]";
	}
	
	
	
	public boolean guardar() {

		LinkedList<Producto>productos = new LinkedList<Producto>();
		
		String sql1 ="INSERT INTO detalle_venta(fk_producto, cantidad, fk_venta) "
				+ "VALUES (?,?,?);";
		String sql2 ="INSERT INTO venta(fecha, fk_caja, total) "
				+ "VALUES (?,?,?,?);";
		try {
			stmt = conexion.prepareStatement(sql1);
			stmt.setLong(1, this.getProducto().getId_producto());
			stmt.setLong(2, this.getCantidad());
			stmt.setLong(3, this.getId_venta());
			stmt.executeUpdate();
			
			stmt = conexion.prepareStatement(sql2);
			stmt.setString(1, this.getFecha());
			stmt.setLong(2, this.getCaja().getId_caja());
			stmt.setDouble(3, this.getTotal());
			stmt.executeUpdate();
			return true;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return false;
		}		
	}


	public void descuentoJueves() {

	}
	
	public void descuentoEfectivo() {

	}
	
}


