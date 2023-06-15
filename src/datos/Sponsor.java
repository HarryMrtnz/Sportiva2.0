package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import interfaces.Conexion;

//--- Hecho por Kari ------------------------------------------------------------
public class Sponsor {
	
    private int id_sponsor;
    private String nombre;
    
  //Conectar a la bdd
  	Conexion con = new Conexion();
  	Connection conexion = con.conectar();
  	PreparedStatement stmt;

    public Sponsor(int id_sponsor, String nombre) {
        this.id_sponsor = id_sponsor;
        this.nombre=nombre;
    }

	public int getId_sponsor() {
        return id_sponsor;
    }

    public void setId_sponsor(int id_sponsor) {
        this.id_sponsor = id_sponsor;
    }
    
    public String getNombre() {
    	return nombre;
    }
    
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    @Override
	public String toString() {
		return "\n\nId_sponsor: " + id_sponsor + "\nNombre: " + nombre ;
	}
    
    
    public LinkedList<Sponsor> TraerSponsor() {
		LinkedList<Sponsor> sponsor = new LinkedList<Sponsor>();
		String sql ="SELECT * FROM sponsor";
		
		String[] datos = new String[2]; 
		try {
			
			stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				datos[0]= result.getString(1);
				datos[1]= result.getString(2);
				
				sponsor.add(new Sponsor(Integer.parseInt(datos[0]), datos[1]));
			}
			return sponsor;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return null;
		}
	}

}