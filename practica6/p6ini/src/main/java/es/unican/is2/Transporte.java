package es.unican.is2;

/* Clase que representa un transporte realizado por un conductor */
public class Transporte {
	
	private double horas;
	private int tonelada;
	private int personas;
	private CategoriaTransporte cat;
	
	/**
	 * Constructor de la clase Transporte
	 * @param horas Horas que ha durado el transporte
	 * @param cat Categoria del transporte
	 * @param valor En caso de ser un transporte de tipo Personas, 
	 * representa el numero de personas, en caso de ser de tipo Mercancias 
	 * representa las toneladas
	 */ 
	public Transporte(double horas, CategoriaTransporte cat, int valor) throws IllegalArgumentException {

		// WMC = 1, CCog = 0
		if (horas <= 0 || valor <= 0 || cat == null) { // WMC + 3, CCog + 2
			throw new IllegalArgumentException();
		}
		this.horas = horas;
		this.cat = cat;
		if (cat.equals(CategoriaTransporte.Personas)) { // WMC + 1, CCog + 1
			this.personas = valor;
		} else  { // WMC + 1, CCog + 1
			this.tonelada = valor;
		}

		// WMC = 6 CCog = 4
	}
	
	public double getHoras() { // WMC = 1
		return horas;
	}

	public CategoriaTransporte getCategoria() { // WMC = 1
		return cat;
	}

	public int getTonelada() { // WMC = 1
		return tonelada;
	}

	public int getPersonas() { // WMC = 1
		return personas;
	}
	
}
