package datos;

import javax.swing.JOptionPane;

public class Sucursal {

	private int id;
	private String nombre;
	private String direccion;
	
//Constructor	
	public Sucursal(int id, String nombre, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
	}

//Getteres y Setteres
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}
	
//Metodos
	
	public int seleccionarSucursal() { //Usado en el menu Vendedor para ver productos de sucursales
	
		int numSucursal = 0;
		String[] sucursales = {"Corrientes", "Brasil", "Gaona"}; //Opciones sucursal
	
		String sucursal = (String) JOptionPane.showInputDialog(
				null, "Seleccione de cual sucursal quiere ver:","SPORTIVA"
				,JOptionPane.QUESTION_MESSAGE ,null ,sucursales, sucursales[0]);
		
		if (sucursal.equals("Corrientes")) {
			numSucursal = 1;
		} else if (sucursal.equals("Brasil")) {
			numSucursal = 2;
		} else if (sucursal.equals("Gaona")) {
			numSucursal = 3;
		}
		return numSucursal;
	}
	
}
