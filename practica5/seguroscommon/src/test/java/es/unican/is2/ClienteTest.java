package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {
    
    private Cliente cliente;
    private List<Seguro> seguros;

    private Seguro crearSeguroTerceros() {
        Seguro s = new Seguro();
        s.setCobertura(Cobertura.TERCEROS); 
        s.setPotencia(89); 
        s.setFechaInicio(LocalDate.now().minusYears(2)); 
        return s;
    }

    private Seguro crearSeguroTercerosLunas() {
        Seguro s = new Seguro();
        s.setCobertura(Cobertura.TERCEROS_LUNAS); 
        s.setPotencia(89); 
        s.setFechaInicio(LocalDate.now().minusYears(2)); 
        return s;
    }

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        seguros = new LinkedList<>();
    }

    @Test
    public void testTotalSeguros_CasosValidos() {
        // 1. SIN MINUSVALÍA
        cliente.setMinusvalia(false);
        
        // Sin seguros
        assertEquals(0, cliente.totalSeguros(), 0.001);
        
        // Un seguro
        seguros.add(crearSeguroTerceros()); 
        cliente.setSeguros(seguros);
        assertEquals(400, cliente.totalSeguros(), 0.001);
        
        // Varios seguros
        seguros.add(crearSeguroTercerosLunas()); 
        assertEquals(1000, cliente.totalSeguros(), 0.001);

        // 2. CON MINUSVALÍA
        Cliente clienteConMinusvalia = new Cliente();
        clienteConMinusvalia.setMinusvalia(true);
        List<Seguro> segurosMinus = new LinkedList<>();
        
        // Varios seguros con descuento (1000€ * 0.75 = 750€)
        segurosMinus.add(crearSeguroTerceros()); 
        segurosMinus.add(crearSeguroTercerosLunas()); 
        clienteConMinusvalia.setSeguros(segurosMinus);
        assertEquals(750, clienteConMinusvalia.totalSeguros(), 0.001);
        
        // Un seguro con descuento (400€ * 0.75 = 300€)
        segurosMinus.remove(1); 
        assertEquals(300, clienteConMinusvalia.totalSeguros(), 0.001);
        
        // Sin seguros con minusvalía
        clienteConMinusvalia.setSeguros(new LinkedList<>());
        assertEquals(0, clienteConMinusvalia.totalSeguros(), 0.001);
    }

    @Test
    public void testTotalSeguros_CasosNoValidos() {
        cliente.setSeguros(null); 
        assertThrows(NullPointerException.class, () -> cliente.totalSeguros());
    }
}