package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ClienteTest {
    
    // Crea un seguro con precio fijo de 400.0 (Terceros, potencia normal, antiguo)
    private Seguro crearSeguroTerceros() {
        Seguro s = new Seguro();
        s.setCobertura(Cobertura.TERCEROS); 
        s.setPotencia(89); 
        s.setFechaInicio(LocalDate.now().minusYears(2)); 
        return s;
    }

    // Crea un seguro con precio fijo de 600.0 (Terceros Lunas, potencia normal, antiguo)
    private Seguro crearSeguroTercerosLunas() {
        Seguro s = new Seguro();
        s.setCobertura(Cobertura.TERCEROS_LUNAS); 
        s.setPotencia(89); 
        s.setFechaInicio(LocalDate.now().minusYears(2)); 
        return s;
    }

    // 1. TESTS SIN MINUSVALÍA (No hay descuento)

    @Test
    public void testTotalSeguros_SinMinusvalia_SinSeguros() {
        Cliente cliente = new Cliente();
        cliente.setMinusvalia(false);
        // La lista por defecto está vacía
        
        assertEquals(0.0, cliente.totalSeguros(), 0.001);
    }

    @Test
    public void testTotalSeguros_SinMinusvalia_UnSeguro() {
        Cliente cliente = new Cliente();
        cliente.setMinusvalia(false);
        
        List<Seguro> seguros = new LinkedList<>();
        seguros.add(crearSeguroTerceros()); // Cuesta 400
        cliente.setSeguros(seguros);
        
        assertEquals(400.0, cliente.totalSeguros(), 0.001);
    }

    @Test
    public void testTotalSeguros_SinMinusvalia_VariosSeguros() {
        Cliente cliente = new Cliente();
        cliente.setMinusvalia(false);
        
        List<Seguro> seguros = new LinkedList<>();
        seguros.add(crearSeguroTerceros()); // Cuesta 400
        seguros.add(crearSeguroTercerosLunas()); // Cuesta 600
        cliente.setSeguros(seguros);
        
        assertEquals(1000.0, cliente.totalSeguros(), 0.001); // 400 + 600
    }

    // 2. TESTS CON MINUSVALÍA (Descuento del 25%)

    @Test
    public void testTotalSeguros_ConMinusvalia_SinSeguros() {
        Cliente cliente = new Cliente();
        cliente.setMinusvalia(true);
        // La lista por defecto está vacía
        
        assertEquals(0.0, cliente.totalSeguros(), 0.001);
    }

    @Test
    public void testTotalSeguros_ConMinusvalia_UnSeguro() {
        Cliente cliente = new Cliente();
        cliente.setMinusvalia(true);
        
        List<Seguro> seguros = new LinkedList<>();
        seguros.add(crearSeguroTerceros()); // Cuesta 400
        cliente.setSeguros(seguros);
        
        assertEquals(300.0, cliente.totalSeguros(), 0.001);
    }

    @Test
    public void testTotalSeguros_ConMinusvalia_VariosSeguros() {
        Cliente cliente = new Cliente();
        cliente.setMinusvalia(true);
        
        List<Seguro> seguros = new LinkedList<>();
        seguros.add(crearSeguroTerceros()); // Cuesta 400
        seguros.add(crearSeguroTercerosLunas()); // Cuesta 600
        cliente.setSeguros(seguros);
        
        assertEquals(750.0, cliente.totalSeguros(), 0.001);
    }

    // 3. TEST DE CLASE NO VÁLIDA (Lista null)

    @Test
    public void testTotalSeguros_ListaNula() {
        Cliente cliente = new Cliente();
        cliente.setSeguros(null); 
        
        // Al iterar sobre una lista null, Java lanza NullPointerException
        assertThrows(NullPointerException.class, () -> cliente.totalSeguros());
    }
}
