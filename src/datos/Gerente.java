package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Gerente extends Usuario{

/*	Conexion con = new Conexion();
	Connection conexion = con.conectar(); */
	PreparedStatement stmt;

	public Gerente(String nombre, String apellido, String dni, String email, String tel, String clave,
			int puesto, int sucursal) {
		super(nombre, apellido, dni, email, tel, clave, puesto, sucursal);
	}
	
	public Gerente(String id, String nombre, String apellido, String dni, String email, String tel, 
			int p, int s, String clave) {
		super(id, nombre, apellido, dni, email, tel, p, s, clave);
	}

	public LinkedList<Gerente> traerUsuario(int id)  {
	    LinkedList<Gerente> usuarios = new LinkedList<Gerente>();
	    
	    String sql = "SELECT * FROM `usuario` WHERE id_usuario=?";
	    
	    try {
	        stmt = conexion.prepareStatement(sql);
	        stmt.setInt(1, id);
	       
	        ResultSet result = stmt.executeQuery();

	        while (result.next()) {
	            String[] datos = new String[9];
	            datos[0] = result.getString(1);
	            datos[1] = result.getString(2);
	            datos[2] = result.getString(3);
	            datos[3] = result.getString(4);
	            datos[4] = result.getString(5);
	            datos[5] = result.getString(6);
	            datos[6] = result.getString(7);
	            datos[7] = result.getString(8);
	            datos[8] = result.getString(9);
	            
	            usuarios.add(new Gerente(datos[0], datos[1], datos[2], datos[3], datos[4], 
	            datos[5], Integer.parseInt(datos[6]), Integer.parseInt(datos[7]), datos[8]));
	        }
	        
	        if (usuarios.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "ID no existe en la base de datos");
	            return null;
	        }
	        return usuarios;
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e.getMessage());
	        return null;
	    }
	}
	public LinkedList<Gerente> verUsuarios()  {
	    LinkedList<Gerente> usuarios = new LinkedList<Gerente>();
	    
	    String sql = "SELECT * FROM `usuario`";
	    
	    try {
	        stmt = conexion.prepareStatement(sql);
	        ResultSet result = stmt.executeQuery();
	        
	        while (result.next()) {
	            String[] datos = new String[9];
	            datos[0] = result.getString(1);
	            datos[1] = result.getString(2);
	            datos[2] = result.getString(3);
	            datos[3] = result.getString(4);
	            datos[4] = result.getString(5);
	            datos[5] = result.getString(6);
	            datos[6] = result.getString(7);
	            datos[7] = result.getString(8);
	            datos[8] = result.getString(9);
	            
	            usuarios.add(new Gerente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], 
	            		Integer.parseInt(datos[6]), Integer.parseInt(datos[7]), datos[8]));
	        }
	        return usuarios;
	        
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e.getMessage());
	        return null;
	    }
	}
		
	public boolean crearUsuario() {

		String sql = "INSERT INTO `usuario`( `nombre`, `apellido`, `dni`, `email`, `telefono`, `fk_puesto`, `fk_sucursal`, `clave`) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setString(1, this.getNombre());  
			stmt.setString(2, this.getApellido());
			stmt.setString(3, this.getDni());
			stmt.setString(4, this.getEmail());
			stmt.setString(5, this.getTelefono());
			stmt.setInt(6, this.getPuesto());
			stmt.setInt(7, this.getSucursal());
			stmt.setString(8, this.getClave());
			stmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		} 
		// Aca estoy cerrando la base de datos, pero me tira error todavia, hay que pulirlo
		/*finally {

	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error al cerrar la conexi�n: " + e.getMessage());
	        }
	    }*/
	
	}

	public boolean editarUsuario() {
		String sql = "UPDATE `usuario` "
				+ "SET `nombre`=?,`apellido`=?,`dni`=?,`email`=?,`telefono`=?,"
				+ "	   `fk_puesto`=?,`fk_sucursal`=?,`clave`=? "
				+ "WHERE id_usuario=?";
		try {
			
			stmt = conexion.prepareStatement(sql);
			stmt.setString(1, this.getNombre());
			stmt.setString(2, this.getApellido());
			stmt.setString(3, this.getDni());
			stmt.setString(4, this.getEmail());
			stmt.setString(5, this.getTelefono());
			stmt.setInt(6, this.getPuesto());
			stmt.setInt(7, this.getSucursal());
			stmt.setString(8, this.getClave());
			stmt.setLong(9, Integer.parseInt(this.getId()));
			stmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	
	public boolean eliminarUsuario(int id) {
		
		String sql = "DELETE FROM `usuario` WHERE id_usuario = ?";
		
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setLong(1, id);  
			stmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;
		}
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
