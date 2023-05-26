package datos;

public class Encargado extends Usuario {
	int id_encargado;

	public Encargado(String nombre, String apellido, String dni, String email, String contraseña, boolean login,
			int id_encargado) {
		super(nombre, apellido, dni, email, contraseña, login);
		this.id_encargado = id_encargado;
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
	
	
	
	public void verStockProductos() {

	}
	
	public void agregarProducto() {
		
	}
	
}
