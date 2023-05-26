package datos;

public class Producto {
	private int id_producto;
	private String tipo;
	private String nombre;
	private double precio;
	private int cantidad;
	
	public Producto(int id_producto, String tipo, String nombre, double precio, int cantidad) {
		super();
		this.id_producto = id_producto;
		this.tipo = tipo;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Producto [id_producto=" + id_producto + ", tipo=" + tipo + ", nombre=" + nombre + ", precio=" + precio
				+ ", cantidad=" + cantidad + "]";
	}
	
	
	

}

