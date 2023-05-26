package datos;

public class Vendedor extends Usuario {
	
	private int id_vendedor;
	private Sucursal sucursal;
	
	public Vendedor(String nombre, String apellido, String dni, String email, String clave, boolean login,
			int id_vendedor, Sucursal sucursal) {
		super(nombre, apellido, dni, email, clave, login);
		this.id_vendedor = id_vendedor;
		this.sucursal = sucursal;
	}
	
	public int getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	@Override
	public String toString() {
		return "Vendedor [id_vendedor=" + id_vendedor + ", sucursal=" + sucursal + "]";
	}

	
	
	public void abrirCaja() {

	}
	
	public void realizarVenta() {
		
	}
	
	public void revisarStock() {

	}
	
	public void hacerPedido() {

	}
	
	public void verVentas() {
		
	}
}

