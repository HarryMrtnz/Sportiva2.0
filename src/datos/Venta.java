package datos;

public class Venta {
	
	private int id_venta;
	private String fecha;
	private Producto producto;
	private int cantidad;
	private String metodoPago;
	private double total;
	
	
	
	public Venta(int id_venta, String fecha, Producto producto, int cantidad, String metodoPago, double total) {
		super();
		this.id_venta = id_venta;
		this.fecha = fecha;
		this.producto = producto;
		this.cantidad = cantidad;
		this.metodoPago = metodoPago;
		this.total = total;
	}

	@Override
	public String toString() {
		return "Venta [id_venta=" + id_venta + ", total=" + total + ", fecha=" + fecha + ", metodoPago=" + metodoPago
				+ ", producto=" + producto + ", cantidad=" + cantidad + "]";
	}

	public int getId_venta() {
		return id_venta;
	}

	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
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

	
	
	
	public void descuentoJueves() {

	}
	
	public void descuentoEfectivo() {

	}
	
}


