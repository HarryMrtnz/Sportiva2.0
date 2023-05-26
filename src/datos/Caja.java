package datos;

public class Caja {
	private int id_caja;
	private boolean abierta;
	private double recaudoTotal;
	
	public Caja(int id_caja, boolean abierta, double recaudoTotal ) {
		super();
		this.id_caja = id_caja;
		this.recaudoTotal = recaudoTotal;
		this.abierta = abierta;
	}
	
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
	
	public boolean isAbierta() {
		return abierta;
	}
	
	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}
	
	@Override
	public String toString() {
		return "Caja [id_caja=" + id_caja + ", recaudoTotal=" + recaudoTotal + ", abierta=" + abierta + "]";
	}
	
	
	
}

