

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import es.unican.is2.Conductor;
import es.unican.is2.TransporteMercancias;
import es.unican.is2.TransporteMercanciasPeligrosas;
import es.unican.is2.TransportePersonas;

public class ConductorTest {
	
	private static Conductor sut;


	@Test
	public void testConstructor() {
		// Casos validos
		sut= new Conductor("123123123X", "Pepe", "Martinez", "Fernandez", "Avda. de los Castros s/n" );
		assertEquals("123123123X", sut.getDni());
		assertEquals("123123123X", sut.getDni());
		assertEquals("Pepe", sut.getNombre());
		assertEquals("Martinez", sut.getApellido1());
		assertEquals("Fernandez", sut.getApellido2());
		assertEquals("Avda. de los Castros s/n", sut.getDireccion());

		sut= new Conductor("123123123X", "Pepe", "Martinez", null, "Avda. de los Castros s/n" );
		assertEquals("123123123X", sut.getDni());
		assertEquals("123123123X", sut.getDni());
		assertEquals("Pepe", sut.getNombre());
		assertEquals("Martinez", sut.getApellido1());
		assertNull(sut.getApellido2());
		assertEquals("Avda. de los Castros s/n", sut.getDireccion());

		// Casos no validos
		assertThrows(IllegalArgumentException.class, () -> new Conductor(null, "Pepe", "Martinez", "Fernandez", "Avda. de los Castros s/n" ));
		assertThrows(IllegalArgumentException.class, () -> new Conductor("123123123X", null, "Martinez", "Fernandez", "Avda. de los Castros s/n" ));
		assertThrows(IllegalArgumentException.class, () -> new Conductor("123123123X", "Pepe", null, "Fernandez", "Avda. de los Castros s/n" ));				
		assertThrows(IllegalArgumentException.class, () -> new Conductor("123123123X", "Pepe", "Martinez", "Fernandez", null));
	}

	@Test
	public void testSueldoYAnhadeTransporte() {
		sut= new Conductor("123123123X", "Pepe", "Martinez", "Fernandez", "Avda. de los Castros s/n" );
		
		// Casos validos
		assertTrue(sut.sueldo() == 700);

		TransportePersonas transPersonas1hora1per = new TransportePersonas(1, 1);
		sut.anhadeTransporte(transPersonas1hora1per);
		assertEquals(705.5, sut.sueldo());
		TransportePersonas transPersonas10horas9per = new TransportePersonas(10, 9);
		sut.anhadeTransporte(transPersonas10horas9per);
		assertEquals(760.5, sut.sueldo());
		TransportePersonas transPersonas1hora10per = new TransportePersonas(1, 10);
		sut.anhadeTransporte(transPersonas1hora10per);
		assertEquals(766.5, sut.sueldo());
		TransportePersonas transPersonas10horas20per = new TransportePersonas(10, 20);
		sut.anhadeTransporte(transPersonas10horas20per);
		assertEquals(826.5, sut.sueldo());

		TransporteMercancias transMercancias1hora1ton = new TransporteMercancias(1, 1);
		sut.anhadeTransporte(transMercancias1hora1ton);
		assertEquals(833.5, sut.sueldo());
		TransporteMercanciasPeligrosas transMercancias10horas100ton = new TransporteMercanciasPeligrosas(10, 100);
		sut.anhadeTransporte(transMercancias10horas100ton);
		assertEquals(1133.5, sut.sueldo());
	}

}
