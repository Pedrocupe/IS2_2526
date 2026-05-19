package es.unican.is2;

import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado. 
 */
public class Conductor {

	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;

	// Atributos constantes para la refactorización de tipo Replace Magic Number with Symbolic Constant.
	private static final int SUELDO_BASE = 700;


	/**
	 * Constructor de la clase Conductor
	 * @param dni DNI del conductor
	 * @param nombre Nombre del conductor
	 * @param apellido1 Primer apellido del conductor
	 * @param apellido2 Segundo apellido del conductor
	 * @param direccion Dirección del conductor
	 */
	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) {
		
		// WMC = 1, CCog = 0
		if (dni == null || nombre == null || apellido1 == null || direccion == null) { // WMC + 4, CCog + 2
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
		// WMC = 5 CCog = 2
	}

	// Método eliminado porque está duplicado, aplicamos refactorización de
	// tipo Extract Method
	/* 
	public String dni() {
		return dni; // WMC = 1, CCog = 0
	}*/

	public String getDni() {
		return dni; // WMC = 1, CCog = 0
	}

	public String getNombre() {
		return nombre; // WMC = 1, CCog = 0
	}

	public String getApellido1() {
		return apellido1; // WMC = 1, CCog = 0
	}

	public String getApellido2() {
		return apellido2; // WMC = 1, CCog = 0
	}

	public String getDireccion() {
		return direccion; // WMC = 1, CCog = 0
	}

	public double sueldo() {
		// WMC = 1, CCog = 0
        double sueldoTransportes = 0;
        for (Transporte t : transportes) { // WMC + 1, CCog + 1
            // Llamamos al nuevo método extraído
            sueldoTransportes += calculaSueldoPorTransporte(t);
        }
		// Aplicamos Replace Magic Number with Symbolic Constant en el 700
		// WMC = 2 CCog = 1
        return SUELDO_BASE + sueldoTransportes;
    }

	// MÉTODO EXTRAÍDO: Encapsula la complejidad del switch
    private double calculaSueldoPorTransporte(Transporte t) {
		// WMC = 1, CCog = 0
        return t.extra();
    }

	public void anhadeTransporte(Transporte t) {
		transportes.add(t); // WMC = 1, CCog = 0
	}

}
