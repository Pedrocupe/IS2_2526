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
	private static final int LIMITE_GRUPO_COLECTIVO = 10;
	private static final double EXTRA_NO_COLECTIVO = 0.5;
	private static final int PRECIO_HORA_CONDUCIDA = 5;
	private static final int EXTRA_POR_TONELADA = 2;
	private static final int EXTRA_MERC_PELIGROSAS = 50;

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
	// tipo Pull Up Method/Field
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

	public String apellido2() {
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
        double sueldoExtraTransporte = 0.0;
        switch (t.getCategoria()) {  // CCog + 2
            case Mercancias: // WMC + 1
                sueldoExtraTransporte = t.getTonelada() * EXTRA_POR_TONELADA;
                break;
            case MercanciasPeligrosas: // WMC + 1
                sueldoExtraTransporte = t.getTonelada() * EXTRA_POR_TONELADA + EXTRA_MERC_PELIGROSAS;
                break;
            case Personas: // WMC + 1
                if (t.getPersonas() < LIMITE_GRUPO_COLECTIVO) // WMC + 1, CCog + 1
                    sueldoExtraTransporte = t.getHoras() * EXTRA_NO_COLECTIVO;
                else // WMC + 1, CCog + 1
                    sueldoExtraTransporte = t.getHoras();
                break;
        }
		// WMC = 6 CCog = 4
        return t.getHoras() * PRECIO_HORA_CONDUCIDA + sueldoExtraTransporte;
    }

	public void anhadeTransporte(Transporte t) {
		transportes.add(t); // WMC = 1, CCog = 0
	}

}
