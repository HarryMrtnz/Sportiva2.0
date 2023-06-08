package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import interfaces.Conexion;

public class Caja {
	private int id_caja;
	private double recaudoTotal;
	private int sucursal;
	
	//conectar a bdd	
		Conexion con = new Conexion();
		Connection conexion = con.conectar();
		PreparedStatement stmt;

//Constructor
	public Caja(int id_caja, double recaudoTotal, int sucursal) {
		super();
		this.id_caja = id_caja;
		this.recaudoTotal = recaudoTotal;
		this.sucursal = sucursal;
	}
//Getters y Setters

	public int getId_caja() {
		return id_caja;
	}

	public void setId_caja(int id_caja) {
		this.id_caja = id_caja;
	}

	public double getRecaudoTotal() {
		return recaudoTotal;
	}

	public void setRecaudoTotal(double recaudoTotal) {
		this.recaudoTotal = recaudoTotal;
	}

	public int getSucursal() {
		return sucursal;
	}

	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public String toString() {
		return "Caja [id_caja=" + id_caja + ", recaudoTotal=" + recaudoTotal + ", sucursal=" + sucursal + "]";
	}
	
//Metodos
	public LinkedList<Caja> listaCajas(int sucursal) {
		Caja caja = null;
		LinkedList<Caja> listaCajas = new LinkedList<Caja>();
		int id = 0;
		
		String sql = "SELECT id_caja "
				+ "FROM caja "
				+ "WHERE fk_sucursal = ? ";
		
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setLong(1, sucursal);
			ResultSet result = stmt.executeQuery();
//			conexion.close();
			
			//Traigo id de cajas de la bdd.
			while(result.next()) {
				//Setteo la id de la base de datos a la caja elegida.
				id = result.getInt(1);
				
				caja = new Caja(id, 0, sucursal);
				
				listaCajas.add(caja);
			}
		}catch(Exception excepcion){
			System.out.println("Error al seleccionar la caja:\n"+excepcion.getMessage()+"\n");
		}
		return listaCajas;
	}
	
	public int selecionarCaja(int sucursal) {
		LinkedList<Caja> menu = new LinkedList<Caja>(); //Lista para crear el menu de cajas
		int idCaja = 0;
		
		for (Caja caja : listaCajas(sucursal)) { //Obtengo lista de un metodo
			menu.add(caja); //Guardo las cajas en la lista de menu.
		}
		
	//Creo un array con el tamaño de la lista de cajas.
		String[] aux = new String [menu.size()];
	//Agrego al menu la id de cada caja de la sucursal elegida.
		for (Caja caja : menu) {
//											 Integer.toString para parsear un int a String
			aux[menu.indexOf(caja)]= Integer.toString(caja.getId_caja());
		}
	//muestro menu con las cajas de la sucursal correspondiente.
		String opcion = (String)JOptionPane.showInputDialog(null
					, "Cajas disponibles de tu sucursal.\n"
					+ "Seleccione una:","SPORTIVA - SUCURSAL N°" +sucursal
					, JOptionPane.QUESTION_MESSAGE ,null ,aux, aux[0]);

		idCaja = Integer.parseInt(opcion);
		
		System.out.println("Caja " + idCaja + " trabajando.\n");
		return idCaja;
	}
	
	public String recaudacionCaja(int caja, int sucursal) {
		double recaudacionCaja = 0;
		String mensaje ="";

		String nombreSucursal = ""; 
		if (sucursal == 1) {
			nombreSucursal = "Corrientes";
		} else if(sucursal == 2){
			nombreSucursal = "Brasil";
		} else if(sucursal == 3){
			nombreSucursal = "Gaona";
		}
		
		String sql = "SELECT recaudacion FROM caja "
				+ "WHERE id_caja = ? "
				+ "AND fk_sucursal = ? ";
		
		try {
			stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			stmt.setInt(1, caja);
			stmt.setInt(2, sucursal);
			stmt.executeUpdate();
//			conexion.close();
			
			while(result.next()) {
				recaudacionCaja = result.getDouble(1);
			}
			
			mensaje = "La caja N°" + caja +" de la sucursal" + nombreSucursal
					+ "\nha recaudado $" + recaudacionCaja;
		}catch(Exception excepcion){
			System.out.println("Error al obtener recaudacion de la caja:\n"+ excepcion.getMessage()+"\n");
			return "";
		}
		return mensaje;
	}
	
	public String recaudacionSucursal(int sucursal) {
		double recaudacionSucursal = 0;
		String mensaje ="";

		String nombreSucursal = ""; 
		if (sucursal == 1) {
			nombreSucursal = "Corrientes";
		} else if(sucursal == 2){
			nombreSucursal = "Brasil";
		} else if(sucursal == 3){
			nombreSucursal = "Gaona";
		}
		
		String sql = "SELECT SUM(recaudacion) "
				+ "FROM caja WHERE fk_sucursal = ? ";
		
		try {
			stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			stmt.setInt(1, sucursal);
			stmt.executeUpdate();
//			conexion.close();
			
			while(result.next()) {
				recaudacionSucursal = result.getDouble(1);
			}
			
			mensaje = "La sucursal" + nombreSucursal
					+ "ha recaudado $" + recaudacionSucursal;
		}catch(Exception excepcion){
			System.out.println("Error al obtener recaudacion de la sucursal:\n"+ excepcion.getMessage()+"\n");
			return "";
		}
		return mensaje;
	}
	
	public String recaudacionTotal() {
		double recaudacionTotal = 0;
		String mensaje ="";

		String sql = "SELECT SUM(recaudacion) FROM caja";
		
		try {
			stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			stmt.executeUpdate();
//			conexion.close();
			
			while(result.next()) {
				recaudacionTotal = result.getDouble(1);
			}
			
			mensaje = "La empresa ha recaudado $"+recaudacionTotal;
		}catch(Exception excepcion){
			System.out.println("Error al obtener recaudacion de la sucursal:\n"+excepcion.getMessage()+"\n");
			return mensaje = "";
		}
		return mensaje;
	}
	
	
	
}

