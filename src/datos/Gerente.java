package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import interfaces.Conexion;
import datos.Usuario;

public class Gerente extends Usuario{


	
/*	Conexion con = new Conexion();
	Connection conexion = con.conectar(); */
	
	PreparedStatement stmt;

	public Gerente(String nombre, String apellido, String dni, String email, String tel, String clave,
			int puesto, int sucursal) {
		super(nombre, apellido, dni, email, tel, clave, puesto, sucursal);
		
	}


	 

	
	public LinkedList<Usuario> traerUsuario(int id)  {
	    LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
	    
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
	            
	            
	            
	            usuarios.add(new Usuario(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], Integer.parseInt(datos[6]), Integer.parseInt(datos[7]), datos[8]));
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
	
	

		
		public LinkedList<Usuario> verUsuarios()  {
		    LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		    
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
		            
		            
		            
		            usuarios.add(new Usuario(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], Integer.parseInt(datos[6]), Integer.parseInt(datos[7]), datos[8]));
		        }
		        
		        return usuarios;
		        
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, e.getMessage());
		        return null;
		    }
		}
		
	
	
	public boolean crearUsuario() {
		
		String sql = "INSERT INTO `usuario`( `nombre`, `apellido`, `dni`, `email`, `telefono`, `fk_puesto`, `fk_sucursal`, `clave`) VALUES (?,?,?,?,?,?,?,?)";
		
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
		

	}

	
	
	public boolean editarUsuario(int id) {
		int error=0;
		String sql = "UPDATE `usuario` SET `nombre`=?,`apellido`=?,`dni`=?,`email`=?,`telefono`=?,`fk_puesto`=?,`fk_sucursal`=?,`clave`=? WHERE id_usuario=?";
	
		
		String[] opcionesP = { "1",
				  "2",
				  "3"};
		String[] opcionesS = { "1",
				  "2",
				  "3"};
		
		String nombre= "asdasd";
		String apellido= "asdasd";
		String dni= "11111111";
		String email= "aaaa@aaa.aaa";
		String telefono= "11111111";
		int puesto= 1;
		int sucursal= 1;
		String clave="11111111";
		

		do {
			
		
		
		
		if (nombre.length()>=3 &&  nombre.length()<=15 && nombre.matches("[a-zA-Z]+")){
			if (apellido.length()>=3 && apellido.length()<=20 && apellido.matches("[a-zA-Z]+")){
				if (dni.length()==8 && dni.matches("\\d+")){
					if (email.length()>=8 && email.length()<=50) {
						if (telefono.length()>=6 && telefono.length()<=14 && telefono.matches("-?\\d+(-\\d+)*")) {
							if(clave.length()>=5 && clave.length()<=12) {
								
								nombre= JOptionPane.showInputDialog("Ingrese nuevo nombre del usuario");
								apellido= JOptionPane.showInputDialog("Ingrese nuevo apellido del usuario");
								dni= JOptionPane.showInputDialog("Ingrese nuevo dni del  usuario");
								email= JOptionPane.showInputDialog("Ingrese nuevo email del usuario");
								telefono= JOptionPane.showInputDialog("Ingrese nuevo telefono del usuario");
								puesto= Integer.parseInt((String) JOptionPane.showInputDialog(null, "Seleccione nuevo puesto\n1-Gerente\n2-Vendedor\n3-Encargado",
										"SPORTIVA - MENU GERENTE",JOptionPane.DEFAULT_OPTION,null, opcionesP,opcionesP[0]));
								sucursal= Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese nueva sucursal\n1-Corrientes\n2-Brasil\n3-Gaona",
										"SPORTIVA - MENU GERENTE",JOptionPane.DEFAULT_OPTION,null, opcionesS,opcionesS[0]));
								clave= JOptionPane.showInputDialog("Ingrese nueva clave del usuario");
								error=1;

									
									
								} else{
									clave=JOptionPane.showInputDialog("Error al ingresar la clave, debe tener entre 5 y 12 caracteres  \n Ingrese la nueva clave");
							}
							} else {
							telefono=JOptionPane.showInputDialog("Error al ingresar el telefono, debe tener entre 8 y 14 caracteres numericos  \n Ingrese el nuevo telefono");
						}
						
						} else {
					email=JOptionPane.showInputDialog("Error al ingresar el email, debe tener entre 8 y 50 letras \n Ingrese el nuevo email");
				} 
					
				}else {
					dni = JOptionPane.showInputDialog("Error al ingresar el dni, debe tener 8 caracteres numericos \n Ingrese el nuevo dni");
				}
			}else {
				apellido = JOptionPane.showInputDialog("Error al ingresar el apellido, debe tener entre 3 y 15 letras \n Ingrese el nuevo apellido");
			}
		}else {
			 nombre = JOptionPane.showInputDialog("Error al ingresar el nombre, debe tener entre 3 y 15 letras \n Ingrese el nuevo nombre ");
		}
		
		} while (error==0);
		
		
		
		
		
		
		
		
		
		
		
		try {
			
			stmt = conexion.prepareStatement(sql);
			stmt.setString(1, nombre);
			stmt.setString(2, apellido);
			stmt.setString(3, dni);
			stmt.setString(4, email);
			stmt.setString(5, telefono);
			stmt.setInt(6, puesto);
			stmt.setInt(7, sucursal);
			stmt.setString(8, clave);
			stmt.setLong(9, id);
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
