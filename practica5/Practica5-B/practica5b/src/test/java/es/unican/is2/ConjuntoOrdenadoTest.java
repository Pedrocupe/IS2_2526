package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConjuntoOrdenadoTest {
     
    private ConjuntoOrdenado<Integer> sut;

    @BeforeEach
    public void setUp() {
        sut = new ConjuntoOrdenado<Integer>();
    }

    @Test
    public void testGet() {
        // CASOS VÁLIDOS
        // Caso 1: [10], 0: 10
        sut.add(10);
        assertEquals(10, sut.get(0));

        // Caso 2: [30, 20, 10], 1: 20
        sut.clear();
        sut.add(30);
        sut.add(20);
        sut.add(10);
        assertEquals(20, sut.get(1));

        // Caso 3: [30, 20, 10], 2: 10
        assertEquals(10, sut.get(2));

        // CASOS NO VÁLIDOS
        sut.clear();

        // Caso 1: [ ], 0: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0));

        // Caso 2: [20, 10], -1:   IndexOutOfBoundsException
        sut.add(20);
        sut.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));

        // Caso 3: [20, 10], -4: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-4));

        // Caso 4: [20, 10], 2: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(2));

        // Caso 5: [20, 10], 7: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(7));
    }

    @Test
    public void testAdd() {
        // CASOS VÁLIDOS
        // Caso 1: [ ], 30: true, ([30])
        assertTrue(sut.add(30));
        assertEquals(1, sut.size());
        assertEquals(30, sut.get(0));
       
        // Caso 2: [30], 10: true ([30, 10])
        assertTrue(sut.add(10));
        assertEquals(2, sut.size());
        assertEquals(30, sut.get(0));
        assertEquals(10, sut.get(1));

        // Caso 3: [30, 10], 20: true ([30, 20, 10])
        assertTrue(sut.add(20));
        assertEquals(3, sut.size());
        assertEquals(30, sut.get(0));
        assertEquals(20, sut.get(1));
        assertEquals(10, sut.get(2));

        // Caso 4: [30, 20, 10], 10: false, ([30, 20, 10])
        // ERROR: Antes daba true, pero debe ser false porque el codigo permite insertar elementos duplicados, hay que corregir el código.
        assertFalse(sut.add(10));
        assertEquals(3, sut.size()); 
        assertEquals(30, sut.get(0));
        assertEquals(20, sut.get(1));
        assertEquals(10, sut.get(2));

        // CASOS NO VÁLIDOS
        sut.clear();
        assertThrows(NullPointerException.class, () -> sut.add(null));
    }

    @Test
    public void testRemove() {
        // CASOS VÁLIDOS
        // Lista inicial: [50, 40, 20]
        sut.add(50);
        sut.add(40);
        sut.add(20);

        // Caso 1: [50, 40, 20], 1: 40, ([50, 20])
        assertEquals(40, sut.remove(1));
        assertEquals(2, sut.size());
        assertEquals(50, sut.get(0));

        // Caso 2: [50, 20], 1: 20, ([50])
        assertEquals(20, sut.remove(1));
        assertEquals(1, sut.size());
        assertEquals(50, sut.get(0));

        // Caso 3: [50], 0: 50, ([])
        assertEquals(50, sut.remove(0));
        assertEquals(0, sut.size());

        // CASOS NO VÁLIDOS
        sut.clear();

        // Caso 1: [ ], 0: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0));
        
        // Caso 2: [10], -1:   IndexOutOfBoundsException
        sut.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));

        // Caso 3: [20, 10], -4: IndexOutOfBoundsException
        sut.add(20);
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-4));
        
        // Caso 4: [20, 10], 2: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(2));

        // Caso 5: [20, 10], 7: IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(7));
    }

    @Test 
    public void testSize() {
        // Caso 1: []: 0
        assertEquals(0, sut.size());

        // Caso 2: [10]: 1
        sut.add(10);
        assertEquals(1, sut.size());

        // Caso 3: [30, 20, 10]: 3
        sut.add(20);
        sut.add(30);
        assertEquals(3, sut.size());
    }

    @Test
    public void testClear() {
        // Caso 1: []: ([])
        sut.clear();
        assertEquals(0, sut.size());

        // Caso 2: [10]: ([])
        sut.add(10);
        sut.clear();
        assertEquals(0, sut.size());

        // Caso 3: [30, 20, 10]: ([])
        sut.add(30);
        sut.add(20);
        sut.add(10);
        sut.clear();
        assertEquals(0, sut.size());
    }
}
