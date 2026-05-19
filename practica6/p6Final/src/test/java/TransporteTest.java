

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import es.unican.is2.TransporteMercancias;
import es.unican.is2.TransporteMercanciasPeligrosas;
import es.unican.is2.TransportePersonas;


public class TransporteTest {

    @Test
    public void testConstructor() {

        // Casos validos
        TransporteMercancias sut = new TransporteMercancias(1, 1);
        assertEquals(1, sut.getHoras());
        assertEquals(1, sut.getToneladas());
        
        TransporteMercanciasPeligrosas sut2 = new TransporteMercanciasPeligrosas(10, 1000);
        assertEquals(10, sut2.getHoras());
        assertEquals(1000, sut2.getToneladas());
        
        TransportePersonas sut3 = new TransportePersonas(10, 10);
        assertEquals(10, sut3.getHoras());
        assertEquals(10, sut3.getPersonas());

        // Casos no validos
        // No se pueden crear transportes con horas o toneladas negativas o personas negativas
        // Con comprobar que uno de las clases lance excepción para horas <= 0 vale, mismo padre.
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(10, 0));
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(10, 0));
        assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(10, 0));
    }

}
