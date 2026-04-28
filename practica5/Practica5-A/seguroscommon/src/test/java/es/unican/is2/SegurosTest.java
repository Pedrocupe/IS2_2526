package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.Seguro.PotenciaInvalidaException;

public class SegurosTest {

    private Seguro seguro;

    @BeforeEach
    public void setUp() {
        seguro = new Seguro();
    }

    @Test
    public void testConstructor() {
        Seguro s = new Seguro();
        
        // Valores por defecto
        assertEquals(null, s.getCobertura());
        assertEquals(null, s.getFechaInicio());
        assertEquals(0, s.getPotencia());
    }

    @Test
    public void testPrecio_CasosValidos() throws NullPointerException, PotenciaInvalidaException{
        
        // 1. fechaActual - 1 año + 1 día, TODO_RIESGO, 1
        seguro.setFechaInicio(LocalDate.now().minusYears(1).plusDays(1));
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(1);
        assertEquals(800.0, seguro.precio());

        // 2. fechaActual - 6 meses, TERCEROS_LUNAS, 90
        seguro.setFechaInicio(LocalDate.now().minusMonths(6));
        seguro.setCobertura(Cobertura.TERCEROS_LUNAS);
        seguro.setPotencia(90);
        assertEquals(504.0, seguro.precio());

        // 3. fechaActual, TERCEROS, 111
        seguro.setFechaInicio(LocalDate.now());
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(111);
        assertEquals(384.0, seguro.precio());

        // 4. fechaActual - 3 años, TODO_RIESGO, 50
        seguro.setFechaInicio(LocalDate.now().minusYears(3));
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(50);
        assertEquals(1000.0, seguro.precio());

        // 5. fechaActual - 1 año, TERCEROS_LUNAS, 100
        seguro.setFechaInicio(LocalDate.now().minusYears(1));
        seguro.setCobertura(Cobertura.TERCEROS_LUNAS);
        seguro.setPotencia(100);
        assertEquals(630.0, seguro.precio());

        // 6. fechaActual + 1 día, TERCEROS, 125
        seguro.setFechaInicio(LocalDate.now().plusDays(1));
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(125);
        assertEquals(0.0, seguro.precio());

        // 7. fechaActual + 1 año, TODO_RIESGO, 89
        seguro.setFechaInicio(LocalDate.now().plusYears(1));
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(89);
        assertEquals(0.0, seguro.precio());

        // 8. fechaActual, TERCEROS_LUNAS, 110
        seguro.setFechaInicio(LocalDate.now());
        seguro.setCobertura(Cobertura.TERCEROS_LUNAS);
        seguro.setPotencia(110);
        assertEquals(504.0, seguro.precio());
    }

    @Test
    public void testPrecio_CasosNoValidos() {
        
        // 1. null, TERCEROS, 1
        seguro.setFechaInicio(null);
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(1);

       assertThrows(NullPointerException.class, () -> seguro.precio());

        // 2. fechaActual, null, 90
        seguro.setFechaInicio(LocalDate.now());
        seguro.setCobertura(null);
        seguro.setPotencia(90);
    
        assertThrows(NullPointerException.class, () -> seguro.precio());

        // 3. fechaActual - 3 años, TODO_RIESGO, 0
        seguro.setFechaInicio(LocalDate.now().minusYears(3));
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(0);
        
        assertThrows(PotenciaInvalidaException.class, () -> seguro.precio());

        // 4. fechaActual + 1 día, TERCEROS_LUNAS, -15
        seguro.setFechaInicio(LocalDate.now().plusDays(1));
        seguro.setCobertura(Cobertura.TERCEROS_LUNAS);
        seguro.setPotencia(-15);

        assertThrows(PotenciaInvalidaException.class, () -> seguro.precio());
    }
}