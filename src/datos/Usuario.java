package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import interfaces.Conexion;

//--- Hecho por Fran ------------------------------------------------------------
public abstract class Usuario {

	private String nombre;
	private String apellido;
	private String dni;
	private String email;
	private String telefono;
	private String clave;
	private int puesto = 0;
	private int sucursal = 0;
	String id;
	private boolean login;

	//conectar a la bdd
	Conexion con = new Conexion();
	Connection conexion = con.conectar();
	PreparedStatement stmt;

	// constructor para crear un nuevo usuario
	public Usuario(String nombre, String apellido, String dni, String email, String tel, String clave, int p, int s) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.telefono = tel;
		this.clave = clave;
		this.puesto = p;
		this.sucursal = s;
	}

	// constructor para traer los datos de la BDD
	public Usuario(String id, String nombre, String apellido, String dni, String email, String tel, int p, int s,
			String clave) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.telefono = tel;
		this.clave = clave;
		this.puesto = p;
		this.sucursal = s;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPuesto() {
		return puesto;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	public int getSucursal() {
		return sucursal;
	}

	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Usuario:   id: " + id + ", nombre: " + nombre + ", apellido: " + apellido + ", dni: " + dni
				+ ", email: " + email + "Telefono: " + telefono + ", clave: " + clave + ", puesto: " + puesto
				+ ", sucursal: " + sucursal + "\n";
	}
		
	
//--- Hecho por Harry ------------------------------------------------------------	
	public boolean login(String dni, String clave) {

		//Comparo dni y clave en la linkedList para iniciar sesion
		if (dni.equals(this.getDni())) {
			if (clave.equals(this.getClave()) ) {
				System.out.println("Usuario "+this.nombre+" "+this.apellido +
						" Ingreso correctamente.\n");
				return true;
			} else {
				return false;
			}
		}else {
			return false;
		}
	}

		

}
