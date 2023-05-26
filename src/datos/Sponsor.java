package datos;

public class Sponsor {
	
    private String id_sponsor;
    private Equipo equipo;

    public Sponsor(String id_sponsor, Equipo equipo, boolean aptosponsor) {
        this.id_sponsor = id_sponsor;
        this.equipo = equipo;
    }

    public String getId_sponsor() {
        return id_sponsor;
    }

    public void setId_sponsor(String id_sponsor) {
        this.id_sponsor = id_sponsor;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
    
    @Override
	public String toString() {
		return "\n\nid_sponsor: " + id_sponsor + "\nequipo: " + equipo;
	}


}

