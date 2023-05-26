package datos;

import java.util.LinkedList;

public class Deposito {
	String id_deposito;
	LinkedList<Producto> listaProductos;

	public Deposito(String id, LinkedList<Producto> lista) {
		id_deposito=id;
		listaProductos=lista;
		
	}

	public String getId_deposito() {
		return id_deposito;
	}

	public void setId_deposito(String id_deposito) {
		this.id_deposito = id_deposito;
	}

	public LinkedList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(LinkedList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	} 
	
	
	
}
