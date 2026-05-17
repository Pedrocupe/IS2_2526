package es.unican.is2;

import java.util.ArrayList;
import java.util.List;

public class gestionTransportes {

	private ArrayList<Conductor> cs = new ArrayList<Conductor>();
	
	public Conductor buscaConductor(String DNI) {		
		// WMC = 1, CCog = 0
		for(Conductor c: cs) // WMC + 1, CCog + 1 
			if (c.dni().equals(DNI)) // WMC + 1, CCog + 2
				return c;
		
		return null;
		// WMC = 3 CCog = 3
	}
	
	public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) {
		
		// WMC = 1, CCog = 0
		if (buscaConductor(dni) != null) // WMC + 1, CCog + 1
			return false;
		cs.add(new Conductor(dni, nombre, apellido1, apellido2,direccion));
		return true;
		// WMC = 2 CCog = 1
	}

	public List<Conductor> conductores() {
		return cs; // WMC = 1, CCog = 0
	}
	
}
