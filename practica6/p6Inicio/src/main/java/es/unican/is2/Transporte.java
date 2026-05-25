/* Clase que representa un transporte realizado por un conductor */
public class Transporte {
	
	private double horas;
	private int ton;
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
		if (horas <= 0 || valor <= 0 || categoria == null) { // WMC + 3, CCog + 2
			throw new IllegalArgumentException();
		}
		this.horas = horas;
		this.cat = cat;
		if (categoria.equals(CategoriaTransporte.Personas)) { // WMC + 1, CCog + 1
			this.personas = valor;
		} else  { // WMC + 1, CCog + 1
			this.ton = valor;
		}
		// WMC = 6 CCog = 4
	}
	
	public double horas() {
		return horas; // WMC = 1, CCog = 0
	}

	public CategoriaTransporte categoria() {
		return cat; // WMC = 1, CCog = 0
	}

	public int ton() {
		return ton; // WMC = 1, CCog = 0
	}

	public int getPersonas() {
		return personas; // WMC = 1, CCog = 0
	}
	
}
