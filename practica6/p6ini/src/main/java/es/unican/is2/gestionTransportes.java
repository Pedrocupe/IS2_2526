package es.unican.is2;

import java.util.ArrayList;
import java.util.List;

public class gestionTransportes {

	private ArrayList<Conductor> listaConductores = new ArrayList<Conductor>();
	
	public Conductor buscaConductor(String dni) {		
		// WMC = 1, CCog = 0
		for(Conductor c: listaConductores) // WMC + 1, CCog + 1 
			if (c.getDni().equals(dni)) // WMC + 1, CCog + 2
				return c;
		
		return null;
		// WMC = 3 CCog = 3
	}
	
	public boolean anhadeConductor(Conductor conductor) {
		
		// WMC = 1, CCog = 0
		if (buscaConductor(conductor.getDni()) != null) // WMC + 1, CCog + 1
			return false;
		listaConductores.add(conductor);
		return true;
		// WMC = 2 CCog = 1
	}

	public List<Conductor> conductores() {
		return listaConductores; // WMC = 1, CCog = 0
	}
	
}
