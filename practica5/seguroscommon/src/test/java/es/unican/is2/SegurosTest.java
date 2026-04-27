package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class SegurosTest {

    // ==========================================
    // 1. TESTS PARA LA FUNCIÓN: PRECIO TOTAL
    // PARÁMETRO: POTENCIA
    // (Fijamos Cobertura: TERCEROS = 400.0, Fecha: Antigua = Sin descuento)
    // ==========================================

    @Test
    public void testPrecio_PotenciaNormal_LimiteInferior() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS); 
        seguro.setFechaInicio(LocalDate.now().minusYears(2)); 
        seguro.setPotencia(1); // Límite inferior
        assertEquals(400.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_PotenciaNormal_Medio() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setPotencia(45); // Medio
        assertEquals(400.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_PotenciaNormal_LimiteSuperior() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setPotencia(89); // Límite superior
        assertEquals(400.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_PotenciaRecargo5_LimiteInferior() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setPotencia(90); // Límite inferior (Recargo 5%)
        assertEquals(420.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_PotenciaRecargo5_Medio() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setPotencia(100); // Medio (Recargo 5%)
        assertEquals(420.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_PotenciaRecargo5_LimiteSuperior() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setPotencia(110); // Límite superior (Recargo 5%)
        assertEquals(420.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_PotenciaRecargo20_LimiteInferior() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setPotencia(111); // Límite inferior (Recargo 20%)
        assertEquals(480.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_PotenciaRecargo20_Medio() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setPotencia(200); // Medio (Recargo 20%)
        assertEquals(480.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_PotenciaInvalida() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setPotencia(0); // No válido
        // El código actual ignora el 0 y cobra el precio base
        assertEquals(400.0, seguro.precio(), 0.001);
    }


    // ==========================================
    // 2. TESTS PARA LA FUNCIÓN: PRECIO TOTAL
    // PARÁMETRO: COBERTURA
    // (Fijamos Potencia: 89 = Sin recargo, Fecha: Antigua = Sin descuento)
    // ==========================================

    @Test
    public void testPrecio_CoberturaTodoRiesgo() {
        Seguro seguro = new Seguro();
        seguro.setPotencia(89);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        assertEquals(1000.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_CoberturaTercerosLunas() {
        Seguro seguro = new Seguro();
        seguro.setPotencia(89);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setCobertura(Cobertura.TERCEROS_LUNAS);
        assertEquals(600.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_CoberturaTerceros() {
        Seguro seguro = new Seguro();
        seguro.setPotencia(89);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setCobertura(Cobertura.TERCEROS);
        assertEquals(400.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_CoberturaInvalida() {
        Seguro seguro = new Seguro();
        seguro.setPotencia(89);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));
        seguro.setCobertura(null); // No válido
        assertEquals(0.0, seguro.precio(), 0.001);
    }


    // ==========================================
    // 3. TESTS PARA LA FUNCIÓN: PRECIO TOTAL
    // PARÁMETRO: FECHA INICIO
    // (Fijamos Cobertura: TERCEROS = 400.0, Potencia: 89 = Sin recargo)
    // ==========================================

    // --- Bloque 1: Recientes (Descuento 20% -> 400 * 0.8 = 320.0) ---
    @Test
    public void testPrecio_FechaReciente_LimiteInferior() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(89);
        seguro.setFechaInicio(LocalDate.now().minusYears(1).plusDays(1)); // Límite inferior
        assertEquals(320.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_FechaReciente_Medio() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(89);
        seguro.setFechaInicio(LocalDate.now().minusMonths(6)); // Medio
        assertEquals(320.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_FechaReciente_LimiteSuperior() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(89);
        seguro.setFechaInicio(LocalDate.now()); // Límite superior (Hoy)
        assertEquals(320.0, seguro.precio(), 0.001);
    }

    // --- Bloque 2: Antiguos (Sin descuento -> 400.0) ---
    @Test
    public void testPrecio_FechaAntigua_LimiteSuperior() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(89);
        seguro.setFechaInicio(LocalDate.now().minusYears(1)); // Límite superior (Justo 1 año)
        assertEquals(400.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_FechaAntigua_Medio() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(89);
        seguro.setFechaInicio(LocalDate.now().minusYears(2)); // Medio
        assertEquals(400.0, seguro.precio(), 0.001);
    }

    // --- Bloque 3: Futuro (Precio = 0.0) ---
    @Test
    public void testPrecio_FechaFutura_LimiteInferior() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(89);
        seguro.setFechaInicio(LocalDate.now().plusDays(1)); // Límite inferior (Mañana)
        assertEquals(0.0, seguro.precio(), 0.001);
    }

    @Test
    public void testPrecio_FechaFutura_Medio() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(89);
        seguro.setFechaInicio(LocalDate.now().plusYears(1)); // Medio
        assertEquals(0.0, seguro.precio(), 0.001);
    }

    // --- Bloque 4: No válido ---
    @Test
    public void testPrecio_FechaInvalida() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(89);
        seguro.setFechaInicio(null); // No válido
        // El código ignora el null en las comprobaciones de fechas y aplica el precio normal
        assertEquals(400.0, seguro.precio(), 0.001);
    }
}