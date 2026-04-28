package es.unican.is2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.Cliente.SeguroNoEncontradoException;
import es.unican.is2.Seguro.PotenciaInvalidaException;

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
    public void testConstructor() {
        Cliente c = new Cliente();
        
        // Valores por defecto
        assertEquals(null, c.getNombre());
        assertEquals(null, c.getDni());
        assertEquals(false, c.getMinusvalia());
        
        // Comprobamos que la lista se ha instanciado y está vacía
        assertEquals(0, c.getSeguros().size());
    }
    
    @Test
    public void testAnhadirSeguro_CasosValidos() {
        // Preparamos los 3 seguros distintos
        Seguro s1 = crearSeguroTerceros();
        Seguro s2 = crearSeguroTercerosLunas();
        Seguro s3 = new Seguro(); 
        s3.setCobertura(Cobertura.TODO_RIESGO); // Seguro distinto para el caso 3

        // 1. [], Seguro1
        assertEquals(0, cliente.getSeguros().size()); // Comprobamos que está vacía []
        cliente.anhadirSeguro(s1);
        assertEquals(1, cliente.getSeguros().size());
        assertTrue(cliente.getSeguros().contains(s1));

        // 2. [Seguro1], Seguro2
        cliente.anhadirSeguro(s2);
        assertEquals(2, cliente.getSeguros().size());
        assertTrue(cliente.getSeguros().contains(s2));

        // 3. [Seguro1,Seguro2], Seguro3
        cliente.anhadirSeguro(s3);
        assertEquals(3, cliente.getSeguros().size());
        assertTrue(cliente.getSeguros().contains(s3));
    }

    @Test
    public void testAnhadirSeguro_CasosNoValidos() {
        Seguro s1 = crearSeguroTerceros();
        
        // 1. [], null
        cliente.setSeguros(new LinkedList<>()); // Aseguramos que es []
        assertThrows(NullPointerException.class, () -> cliente.anhadirSeguro(null));

        // 2. null, Seguro1
        cliente.setSeguros(null);
        assertThrows(NullPointerException.class, () -> cliente.anhadirSeguro(s1));

        // Restauramos la lista a vacía para el siguiente caso
        cliente.setSeguros(new LinkedList<>());

        // 3. [Seguro1], Seguro1
        cliente.anhadirSeguro(s1); // Lo añadimos la primera vez para tener [Seguro1]
        
        // Intentamos añadir s1 de nuevo (el código de Cliente lanzará RuntimeException)
        assertThrows(RuntimeException.class, () -> cliente.anhadirSeguro(s1));
    }
    
    @Test
    public void testTotalSeguros_CasosValidos() throws NullPointerException, PotenciaInvalidaException { 
        // 1. SIN MINUSVALÍA
        cliente.setMinusvalia(false);
        
        // Sin seguros
        assertEquals(0, cliente.totalSeguros());
        
        // Un seguro
        seguros.add(crearSeguroTerceros()); 
        cliente.setSeguros(seguros);
        assertEquals(400, cliente.totalSeguros());
        
        // Varios seguros
        seguros.add(crearSeguroTercerosLunas()); 
        assertEquals(1000, cliente.totalSeguros());

        // 2. CON MINUSVALÍA
        Cliente clienteConMinusvalia = new Cliente();
        clienteConMinusvalia.setMinusvalia(true);
        List<Seguro> segurosMinus = new LinkedList<>();
        
        // Varios seguros con descuento (1000€ * 0.75 = 750€)
        segurosMinus.add(crearSeguroTerceros()); 
        segurosMinus.add(crearSeguroTercerosLunas()); 
        clienteConMinusvalia.setSeguros(segurosMinus);
        assertEquals(750, clienteConMinusvalia.totalSeguros());
        
        // Un seguro con descuento (400€ * 0.75 = 300€)
        segurosMinus.remove(1); 
        assertEquals(300, clienteConMinusvalia.totalSeguros());
        
        // Sin seguros con minusvalía
        clienteConMinusvalia.setSeguros(new LinkedList<>());
        assertEquals(0, clienteConMinusvalia.totalSeguros());
    }

    @Test
    public void testTotalSeguros_CasosNoValidos() {
        cliente.setSeguros(null); 
        assertThrows(NullPointerException.class, () -> cliente.totalSeguros());
    }
}