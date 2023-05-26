package datos;

public class Gerente extends Usuario{

	private int id_gerente;
	private Sucursal sucursal;

	public Gerente(String nombre, String apellido, String dni, String email, String clave, boolean login,
			int id_gerente, Sucursal sucursal) {
		super(nombre, apellido, dni, email, clave, login);
		this.id_gerente = id_gerente;
		this.sucursal = sucursal;
	}

	public int getId_gerente() {
		return id_gerente;
	}

	public void setId_gerente(int id_gerente) {
		this.id_gerente = id_gerente;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}


	
	
	public void VerUsuarios() {
		
	}
	
	public void crearUsuario() {

	}
	
	public void editarUsuario() {
	
	}
	
	public void eliminarUsuario() {
		
	}
}
