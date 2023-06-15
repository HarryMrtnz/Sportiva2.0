package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import interfaces.Conexion;

//--- Hecho por Kari ------------------------------------------------------------
public class Equipo {

	private int id_equipo;
    private String nombre;
    private String clasificacion;
    private String deporte;
    
  //variables fuera del constructor:
    private String sponsor;

  //Conectar a la bdd
  	Conexion con = new Conexion();
  	Connection conexion = con.conectar();
  	PreparedStatement stmt;
    
    public Equipo(int id_equipo, String nombre, String clasificacion, String deporte) {
       super(); 
       this.id_equipo = id_equipo;
       this.nombre = nombre;
       this.clasificacion=clasificacion;
       this.deporte=deporte;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}
	
    public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	@Override
    public String toString() {
		return "Equipo: " + nombre + " - Clasificacion: " + clasificacion + "\n"
				+ "   Deporte: " + deporte + " - Sponsor: " + sponsor +".\n";
	}

public LinkedList<Equipo> VerEquipoClasificado() {
	LinkedList<Equipo> equipos = new LinkedList<Equipo>();
	String sql = "SELECT e.nombre, d.nombre , e.clasificacion, s.nombre "
			+ "FROM equipo_clasificado "
			+ "INNER JOIN equipo e ON e.id_equipo = fk_equipo "
			+ "INNER JOIN sponsor s ON s.id_sponsor = fk_sponsor "
			+ "INNER JOIN deporte d ON d.id_deporte = e.fk_deporte ";
	
	String[] datos = new String[4]; 
	try {
		
		stmt = conexion.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		while(result.next()) {
			datos[0]= result.getString(1);//nombre de equipo
			datos[1]= result.getString(2);//nombre de deporte
			datos[2]= result.getString(3);//clasificacion
			datos[3]= result.getString(4);//nombre de sponsor
			
			Equipo equipo = new Equipo(0, datos[0], datos[2], datos[1]); //Creo objetos de equipo
			//Agrego Sponsor a parte al no ser parte del constructor.	
			equipo.setSponsor(datos[3]); 
			
			equipos.add(equipo); //Agrego Equipo a la lista.
		}
		return equipos; //Retorno Lista.
		
	}catch(Exception excepcion){
		System.out.println(excepcion.getMessage());
		return null;
	}
}
	
	public LinkedList<Equipo> TraerEquipo() {
		LinkedList<Equipo> equipos = new LinkedList<Equipo>();
		String deporte = "";

		String sql ="SELECT id_equipo, e.nombre, clasificacion, fk_deporte, s.nombre "
				+ "FROM equipo e "
				+ "LEFT JOIN equipo_clasificado ec ON ec.fk_equipo = id_equipo "
				+ "LEFT JOIN sponsor s ON s.id_sponsor = ec.fk_sponsor ";
		
		String[] datos = new String[5]; 
		try {
			
			stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				datos[0]= result.getString(1);//id
				datos[1]= result.getString(2);//nombre
				datos[2]= result.getString(3);//clasificacion
				datos[3]= result.getString(4);//deporte
				datos[4]= result.getString(5);//sponsor
				
				if (datos[3].equals("1")) {
					deporte = "futbol";
				} else if(datos[3].equals("2")){
					deporte = "basquet";
				} else if(datos[3].equals("3")){
					deporte = "rugby";
				}
				Equipo equipo = new Equipo(Integer.parseInt(datos[0]), datos[1], datos[2], deporte);
				equipo.setSponsor(datos[4]);
				equipos.add(equipo);
				
			}
			return equipos;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return null;
		}
	}
	

	
	public boolean clasificarEquipo(Sponsor sponsor) {
		String sql = " INSERT INTO equipo_clasificado(fk_sponsor, fk_equipo, fk_puesto) "
				+ "VALUES (?, ?, ?)";
		int idEquipo = this.getId_equipo();
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, sponsor.getId_sponsor());
			stmt.setInt(2, idEquipo);//Nombre del equipo
			stmt.setInt(3, 1);// 1 = Gerente
			stmt.executeUpdate();
//			conexion.close();
			return true;
			
		}catch(Exception excepcion){
			System.out.println("Error al guardar equipo clasificado:\n"+ excepcion.getMessage()+"\n");
			return false;
		}
	}
}