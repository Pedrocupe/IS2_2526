package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConjuntoOrdenadoTest {
     
    private ConjuntoOrdenado<Integer> conjunto;

    @BeforeEach
    public void setUp() {
        conjunto = new ConjuntoOrdenado<Integer>();
    }

    @Test
    public void testCasosValidos() {
        //FUNCIÓN GET
        // Caso 1: [10], 0: 10
        conjunto.add(10);
        assertEquals(10, conjunto.get(0));

        // Caso 2: [30, 20, 10], 1: 20
        conjunto.clear();
        conjunto.add(30);
        conjunto.add(20);
        conjunto.add(10);
        assertEquals(20, conjunto.get(1));

        // Caso 3: [30, 20, 10], 2: 10
        assertEquals(10, conjunto.get(2));

        //FUNCIÓN ADD
        // Caso 1: [10], 10: ([10]). No añade duplicados
        conjunto.clear();
        conjunto.add(10);
        conjunto.add(10);
        assertEquals(1, conjunto.size());
        // Error de caja negra, la lista en este momento permite insertar duplicados, hay que corregirlo con caja blanca
        assertEquals(10, conjunto.get(0));

        // Caso 2: [30, 20, 10], 20: ([30, 20, 10]). No añade duplicados
        conjunto.add(30);
        conjunto.add(20);
        conjunto.add(10);
        conjunto.add(20);
        assertEquals(3, conjunto.size());

        // Caso 3: [30, 20, 10], 30: ([30, 20, 10]). No añade duplicados
        conjunto.add(30);
        assertEquals(3, conjunto.size());

        // Caso 4: [30, 20, 10], 50: ([50, 30, 20, 10])
        conjunto.add(50);
        assertEquals(4, conjunto.size());
        assertEquals(50, conjunto.get(0)); // El 50 va al principio

        // Caso 5: [50, 30, 20, 10], 40: ([50, 40, 30, 20, 10])
        conjunto.add(40);
        assertEquals(5, conjunto.size());
        assertEquals(40, conjunto.get(1)); // El 40 va en la posición 1

        // Caso 6: [50, 40, 30, 20, 10], 0: ([50, 40, 30, 20, 10, 0])
        conjunto.add(0);
        assertEquals(6, conjunto.size());
        assertEquals(0, conjunto.get(5)); // El 0 va al final

        //FUNCIÓN REMOVE
        conjunto.clear();

        // Lista inicial: [50, 40, 30, 20, 10]
        conjunto.add(50);
        conjunto.add(40);
        conjunto.add(30);
        conjunto.add(20);
        conjunto.add(10);

        // Límite Inferior: índice = 0. Devuelve 50. Estado: [40, 30, 20, 10]
        assertEquals(50, conjunto.remove(0));
        assertEquals(4, conjunto.size());
        assertEquals(40, conjunto.get(0));

        // Valor medio: índice = 2. Devuelve 20. Estado: [40, 30, 10]
        assertEquals(20, conjunto.remove(2));
        assertEquals(3, conjunto.size());
        assertEquals(10, conjunto.get(2));

        // Valor superior: índice = lista.size() - 1. Devuelve 10. Estado: [40, 30]
        assertEquals(10, conjunto.remove(conjunto.size() - 1));
        assertEquals(2, conjunto.size());
        
        // Lista con varios elementos -> lista con 1 elemento: índice = 0. Devuelve 40. Estado: [30]
        assertEquals(40, conjunto.remove(0));
        assertEquals(1, conjunto.size());
        assertEquals(30, conjunto.get(0));

        // Lista con 1 elemento -> lista sin elementos: índice = 0. Devuelve 30. Estado: []
        assertEquals(30, conjunto.remove(0));
        assertEquals(0, conjunto.size());

        // FUNCIÓN SIZE
        conjunto.clear();

        // Caso 1: []: 0
        assertEquals(0, conjunto.size());

        // Caso 2: [10]: 1
        conjunto.add(10);
        assertEquals(1, conjunto.size());

        // Caso 3: [30, 20, 10]: 3
        conjunto.add(20);
        conjunto.add(30);
        assertEquals(3, conjunto.size());

        // FUNCIÓN CLEAR
        conjunto.clear();

        // Caso 1: []: 0
        conjunto.clear();
        assertEquals(0, conjunto.size());

        // Caso 2: [10]: 0
        conjunto.add(10);
        conjunto.clear();
        assertEquals(0, conjunto.size());

        // Caso 3: [30, 20, 10]: 0
        conjunto.add(30);
        conjunto.add(20);
        conjunto.add(10);
        conjunto.clear();
        assertEquals(0, conjunto.size());

    }

    @Test
    public void testCasosNoValidos() {
        // FUNCIÓN GET
        //  Lista ejemplo = [10, 20, 30]
        conjunto.add(30);
        conjunto.add(20);
        conjunto.add(10);

        // Justo debajo del límite: índice = -1
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.get(-1));

        // Justo encima del límite: índice = 3 (tamaño de la lista)
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.get(3));

        // FUNCIÓN ADD
        conjunto.clear();
        
        assertThrows(NullPointerException.class, () -> conjunto.add(null));

        // FUNCIÓN REMOVE
        conjunto.clear();

        // Para probar los límites no válidos (3 y -1), preparamos una lista de tamaño 3
        conjunto.add(30);
        conjunto.add(20);
        conjunto.add(10);

        // Justo debajo del límite: índice = -1
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.remove(-1));

        // Justo encima del límite: índice = 3
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.remove(3));
    }

}
