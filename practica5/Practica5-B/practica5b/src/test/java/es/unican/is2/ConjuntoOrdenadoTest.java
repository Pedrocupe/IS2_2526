package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConjuntoOrdenadoTest {
     
    private ConjuntoOrdenado<Integer> lista;

    @BeforeEach
    public void setUp() {
        lista = new ConjuntoOrdenado<Integer>();
    }

    @Test
    public void testGet() {
        // CASOS VÁLIDOS
        // Caso 1: [10], 0: 10
        lista.add(10);
        assertEquals(10, lista.get(0));

        // Caso 2: [30, 20, 10], 1: 20
        lista.clear();
        lista.add(30);
        lista.add(20);
        lista.add(10);
        assertEquals(20, lista.get(1));

        // Caso 3: [30, 20, 10], 2: 10
        assertEquals(10, lista.get(2));

        // CASOS NO VÁLIDOS
        lista.clear();

        // Caso 1: [ ], 0: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(0));

        // Caso 2: [20, 10], -1:   IndexOutOfBoundsException
        lista.add(20);
        lista.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-1));

        // Caso 3: [20, 10], -4: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-4));

        // Caso 4: [20, 10], 2: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(2));

        // Caso 5: [20, 10], 7: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(7));
    }

    @Test
    public void testAdd() {
        // CASOS VÁLIDOS
        // Caso 1: [ ], 30: true, ([30])
        assertTrue(lista.add(30));
       
        // Caso 2: [30], 10: true ([30, 10])
        assertTrue(lista.add(10));

        // Caso 3: [30, 10], 20: true ([30, 20, 10])
        assertTrue(lista.add(20));

        // Caso 4: [30, 20, 10], 10: false, ([30, 20, 10])
        // ERROR: Antes daba true, pero debe ser false porque el codigo permite insertar elementos duplicados, hay que corregir el código.
        assertFalse(lista.add(10));

        // CASOS NO VÁLIDOS
        lista.clear();
        assertThrows(NullPointerException.class, () -> lista.add(null));
    }

    @Test
    public void testRemove() {
        // CASOS VÁLIDOS
        // Lista inicial: [50, 40, 20]
        lista.add(50);
        lista.add(40);
        lista.add(20);

        // Caso 1: [50, 40, 20], 1: 40, ([50, 20])
        assertEquals(40, lista.remove(1));
        assertEquals(2, lista.size());
        assertEquals(50, lista.get(0));

        // Caso 2: [50, 20], 1: 20, ([50])
        assertEquals(20, lista.remove(1));
        assertEquals(1, lista.size());
        assertEquals(50, lista.get(0));

        // Caso 3: [50], 0: 50, ([])
        assertEquals(50, lista.remove(0));
        assertEquals(0, lista.size());

        // CASOS NO VÁLIDOS
        lista.clear();

        // Caso 1: [ ], 0: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(0));
        
        // Caso 2: [10], -1:   IndexOutOfBoundsException
        lista.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-1));

        // Caso 3: [20, 10], -4: IndexOutOfBoundsException
        lista.add(20);
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-4));
        
        // Caso 4: [20, 10], 2: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(2));

        // Caso 5: [20, 10], 7: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(7));
    }

    @Test 
    public void testSize() {
        // Caso 1: []: 0
        assertEquals(0, lista.size());

        // Caso 2: [10]: 1
        lista.add(10);
        assertEquals(1, lista.size());

        // Caso 3: [30, 20, 10]: 3
        lista.add(20);
        lista.add(30);
        assertEquals(3, lista.size());
    }

    @Test
    public void testClear() {
        // Caso 1: []: ([])
        lista.clear();
        assertEquals(0, lista.size());

        // Caso 2: [10]: ([])
        lista.add(10);
        lista.clear();
        assertEquals(0, lista.size());

        // Caso 3: [30, 20, 10]: ([])
        lista.add(30);
        lista.add(20);
        lista.add(10);
        lista.clear();
        assertEquals(0, lista.size());
    }
}
