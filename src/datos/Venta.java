package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import interfaces.Conexion;

public class Venta {
	
	private int id_venta;
	private int caja;
	private LocalDate fecha;
	private double total;
	
	//detalle_venta
	private int producto;
	private int cantidad;
	
	//variables que no estan en constructor:
	private String nombreProducto;
	private String metodoPago;
	
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("EEEE dd / MMMM / yyyy");

	//conectar a bdd	
		Conexion con = new Conexion();
		Connection conexion = con.conectar();
		PreparedStatement stmt;
	
	//Constructor
	public Venta(int id_venta, int caja, LocalDate fecha, double total) {
		super();
		this.id_venta = id_venta;
		this.caja = caja;
		this.fecha = fecha;
		this.total = total;
	}
//getters y setters
	public int getId_venta() {
		return id_venta;
	}

	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}

	public int getCaja() {
		return caja;
	}

	public void setCaja(int caja) {
		this.caja = caja;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}
	
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
	@Override
	public String toString() {
		return "Venta N°: " + id_venta + " - " + fecha.format(formato) + "\n"
				+ "Producto: "+ nombreProducto+ " x" + cantidad + "Unidades.\n"
				+ "Metodo de Pago: " + metodoPago + "\n"
				+ "Total: $" + total+"\n";
	}
	//Metodos
	public boolean guardarVenta() {

		String sql ="INSERT INTO venta(fk_caja, total) VALUES (?,?)";
//		String sql ="INSERT INTO venta(fk_caja) VALUES (?)"; // Probando solo guardar un int en fk_caja. NO ANDA

		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setLong(1, this.getCaja());
			stmt.setDouble(2, this.getTotal());
			stmt.executeUpdate();
//			conexion.close();
			return true;
			
		}catch(Exception excepcion){
			System.out.println("Error al guardar datos en venta:\n"+ excepcion.getMessage()+"\n");
			return false;
		}		
	}
	
	public int idVenta() {
		
	/*Traigo el maximo numero de id (ultimo en la lista) de la tabla "venta"
	  para poder completar la tabla "detalle_venta", que me pide la fk de venta*/
		String sql = "SELECT MAX(id_venta) FROM venta ";
		int id = 0; //Variable a retornar
		try {
			stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
			
				id = result.getInt(1); //Guardo la ultima id de la tabla "venta".
			}
		}catch(Exception excepcion){
			System.out.println("Error al buscar la id de venta:\n"+ excepcion.getMessage()+"\n");
		}
		return id;
	}
	
	public boolean guardarDetalle() {
		String sql ="INSERT INTO detalle_venta(fk_producto, cantidad, fk_venta) "
				+ "VALUES (?,?,?);";
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setInt(1, this.getProducto());
			stmt.setInt(2, this.getCantidad());
			stmt.setInt(3, this.idVenta() ); //Aplico metodo para obtener id_venta
			stmt.executeUpdate();
//			conexion.close();
			return true;
			
		}catch(Exception excepcion){
			System.out.println("Error al guardar detalle de venta:\n"+ excepcion.getMessage()+"\n");
			return false;
		}
	}

	public LinkedList <Venta> listaVentas(){
		
		LinkedList<Venta> ventas = new LinkedList<Venta>(); //Lista a retornar
		
		String sql = "SELECT v.id_venta, v.fk_caja, fecha, p.nombre, dv.cantidad, v.total "
				+ "FROM venta v "
				+ "INNER JOIN detalle_venta dv ON dv.fk_venta = id_venta "
				+ "INNER JOIN producto p ON p.id_producto = dv.fk_producto ";
		
		String[] datos = new String[7];
		try {
			stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
//			conexion.close();
			
			while(result.next()) {
				datos[0]= result.getString(1);//id
				datos[1]= result.getString(2);//caja
				datos[2]= result.getString(3);//fecha
				datos[3]= result.getString(4);//producto
				datos[4]= result.getString(5);//cantidad
				datos[5]= result.getString(6);//total
				
				Venta nuevaVenta = new Venta(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]),
						LocalDate.parse(datos[2]), Double.parseDouble(datos[5]));
				//Agrego los datos que no estan en el constructor
				nuevaVenta.setNombreProducto(datos[3]); //Setteo nombre de producto
				nuevaVenta.setCantidad(Integer.parseInt(datos[4])); //Setteo cantidad
				//Guardo las ventas en una lista para mostrar
				ventas.add(nuevaVenta);
				
			}
		}catch(Exception excepcion){
			System.out.println("Error al mostrar lista de ventas:\n"+ excepcion.getMessage()+"\n");
		}	
		return ventas;
	}
	
	public boolean actualizarRecaudacion(){
		int numSucursal = 0;
		double total = 0;

		//calcular Sucursal a travez de la id caja.
		if (this.getCaja()> 0 && this.getCaja() < 5) {
			numSucursal = 1;
		} else if (this.getCaja()> 5 && this.getCaja() < 8){
			numSucursal = 2;
		} else if(this.getCaja()> 8  && this.getCaja() < 11) {
			numSucursal = 3;
		}
		
		String sql1 = "SELECT recaudacion FROM caja "
				+ "WHERE id_caja = ? "
				+ "AND fk_sucursal = ? ";
		try {
			stmt = conexion.prepareStatement(sql1);
			ResultSet result = stmt.executeQuery();
			
			stmt.setInt(1, this.getCaja());
			stmt.setInt(2, numSucursal);
			stmt.executeUpdate();
//			conexion.close();
			
			while(result.next()) {
				total = result.getDouble(1);
			}
			
			String sql2 = "UPDATE caja SET recaudacion = ? "
					+ "WHERE id_caja = ? "
					+ "AND fk_sucursal= ? ";
			try {
				stmt = conexion.prepareStatement(sql2);
				stmt.setDouble(1, this.getTotal()+ total);
				stmt.setInt(2, this.getCaja());
				stmt.setInt(3, numSucursal);
				stmt.executeUpdate();
//				conexion.close();
				System.out.println("Venta finalizada.\n");
				return true;
				
			}catch(Exception excepcion){
				System.out.println("Error al obtener total de venta:\n"+ excepcion.getMessage()+"\n");
				return false;
			}
		}catch(Exception excepcion){
			System.out.println("Error al actualizar recaudacion:\n"+ excepcion.getMessage()+"\n");
			return false;
		}
	}
	
}


