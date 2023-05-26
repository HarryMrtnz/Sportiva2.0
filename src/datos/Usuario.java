package datos;

public abstract class Usuario  {
	
		private String nombre;
		private String apellido;
		private String dni;
		private String email;
		private String clave;
		private boolean login;
	
	public Usuario(String nombre, String apellido, String dni, String email, String clave, boolean login) {
			super();
			this.nombre = nombre;
			this.apellido = apellido;
			this.dni = dni;
			this.email = email;
			this.clave = clave;
			this.login = login;
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

	
	public String getclave() {
		return clave;
	}

	public void setclave(String clave) {
		this.clave = clave;
	}


	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", email=" + email
				+ ", clave=" + clave + ", login=" + login + "]";
	}
	
	

	public boolean login() {
		return true;
	}
	
	public void logOut() {
		setLogin(false);
	}
	
}
