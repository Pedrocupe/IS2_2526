package es.unican.is2;

import java.util.LinkedList;
import java.util.List;
import fundamentos.*;

/**
 * Gestion de una empresa de transportes
 */
public class GestionTransportesGUI {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) {
		// WMC = 1, CCog = 0
		// opciones del menu
		final int ANHADE_CONDUCTOR = 0, ANHADE_TRANSPORTE = 1, 
		SUELDO_CONDUCTOR = 2, MEJOR_CONDUCTOR = 3;

		// variables auxiliares
		String dni;
		Lectura lectura;
		Conductor conductor;

		// crea la empresa de transportes
		gestionTransportes gestionTransportes = new gestionTransportes();
		// crea la ventana de menu
		Menu menu = new Menu("Transportes");
		menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
		menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
		menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
		menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);
		
		int opcion;

		// lazo de espera de comandos del usuario
		while(true) { // CCog+1, WMC+1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) { // CCog+2
			case  ANHADE_CONDUCTOR: // WMC+1
				lectura = new Lectura("Datos Conductor");
				lectura.creaEntrada("DNI", "");
				lectura.creaEntrada("Nombre","");
				lectura.creaEntrada("Apellido1", "");
				lectura.creaEntrada("Apellido2", "");
				lectura.creaEntrada("Direccion", "");
				lectura.esperaYCierra();
				dni = lectura.leeString("DNI");
				String nombre = lectura.leeString("Nombre");
				String apellido1 = lectura.leeString("Apellido1");
				String apellido2 = lectura.leeString("Apellido2");
				String direccion = lectura.leeString("Direccion");
				// Anhade el conductor
				if (!gestionTransportes.anhadeConductor(new Conductor(dni, nombre, apellido1, apellido2, direccion))) // CCog+3, WMC+1
					mensaje("ERROR", "Ya existe un conductor con DNI "+dni);
				break;

			case ANHADE_TRANSPORTE: // WMC+1
				lectura = new Lectura("Nuevo transporte");
				lectura.creaEntrada("DNI", "");
				lectura.creaEntrada("Tipo Transporte: P | M | MP", "");
				lectura.creaEntrada("Horas", 0);
				lectura.creaEntrada("Personas", 0);
				lectura.creaEntrada("Toneladas", 0);
				lectura.esperaYCierra();
				dni = lectura.leeString("DNI");
				String tipo = lectura.leeString("Tipo Transporte: P | M | MP");
				int horas = lectura.leeInt("Horas");
				int personas = lectura.leeInt("Personas");
				int toneladas = lectura.leeInt("Toneladas");

				Transporte t = null;
				conductor = gestionTransportes.buscaConductor(dni);
				if (conductor!=null) { // CCog+3, WMC+1
					switch (tipo) { // CCog+4
						case "P": // WMC+1
							t = new Transporte(horas,CategoriaTransporte.Personas, personas);
							conductor.anhadeTransporte(t);
							break;
						case "M": // WMC+1
							t = new Transporte(horas, CategoriaTransporte.Mercancias, toneladas);
							conductor.anhadeTransporte(t);
							break;
						case "MP": // WMC+1
							t = new Transporte(horas, CategoriaTransporte.MercanciasPeligrosas, toneladas);
							conductor.anhadeTransporte(t);
							break;		
					}
				} else { // CCog+1, WMC+1
					mensaje("ERROR", "No existe un conductor con DNI "+dni);
				}
				break;
				
			case SUELDO_CONDUCTOR: // WMC+1
				lectura = new Lectura("Transportes Peligrosos");
				lectura.creaEntrada("DNI", "");
				lectura.esperaYCierra();
				dni = lectura.leeString("DNI");
				conductor = gestionTransportes.buscaConductor(dni);
				if (conductor!=null){ // CCog+3, WMC+1
					mensaje("Sueldo", "El sueldo del conductor es: "+ conductor.sueldo());
				} else { // CCog+1, WMC+1
					mensaje("ERROR", "No existe un conductor con DNI "+dni);
				}
 				break;

			case MEJOR_CONDUCTOR: // WMC+1
				List<Conductor> resultado = new LinkedList<Conductor>();
				double maxSueldo = 0.0;
				for (Conductor c : gestionTransportes.conductores()) { // CCog+3, WMC+1
					if (c.sueldo() > maxSueldo) { // CCog+4, WMC+1
						maxSueldo = c.sueldo();
						resultado.clear();
						resultado.add(c); 
					} else if (c.sueldo() == maxSueldo) { // CCog+1, WMC+1
						resultado.add(c);
					}
				}		
				String msj = "";
				if (resultado.size() == 0) { // CCog+3, WMC+1
					msj = "No hay conductores";
				} else { // CCog+1, WMC+1
					for (Conductor c : resultado) { // CCog+4, WMC+1
						msj += c.getNombre() + " "+c.getNombre()+"\n";
					}
				}
				mensaje("MEJOR CONDUCTOR", msj);
				break;
			}
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
