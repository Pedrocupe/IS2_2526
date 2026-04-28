package es.unican.is2;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.swing.launcher.ApplicationLauncher.application;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.finder.WindowFinder;

// IMPORTANTE: Según el guion, debe incluir "IT" en el nombre y ejecutarse en la fase verify de Maven
public class VistaAgenteIT {

    private FrameFixture demo;

    @BeforeEach
    public void setUp() {
        // 1. Lanzamos la aplicación
        application(Runner.class).start();
        
        // 2. Creamos el robot
        Robot robot = BasicRobot.robotWithCurrentAwtHierarchy();
        
        // 3. Le decimos al robot que busque la ventana de tipo VistaAgente
        demo = WindowFinder.findFrame(VistaAgente.class).using(robot);
    }

    @AfterEach
    public void tearDown() {
        // Limpiamos los recursos después de cada test cerrando la ventana
        if (demo != null) {
            demo.cleanUp();
        }
    }

    @Test
    public void testConsultaCliente_Existe() {
        
        // 1. Introducir datos (Juan, DNI: 11111111A - Ver Tabla 1 del Anexo 2)
        demo.textBox("txtDNICliente").enterText("11111111A");
        
        // 2. Ejecutar acción
        demo.button("btnBuscar").click();
        
        // 3. Comprobar resultados
        // Comprobamos el nombre
        demo.textBox("txtNombreCliente").requireText("Juan");
        
        // Para este DNI, sabemos que tiene un seguro a terceros de 400€
        // Y según la tabla, Juan NO tiene minusvalía (false), así que no hay descuento.
        demo.textBox("txtTotalCliente").requireText("1820.0");
    }

    @Test
    public void testConsultaCliente_NoExiste() {
        // 1. Introducir Dni cualquiera
        demo.textBox("txtDNICliente").enterText("00000000Z");
        
        // 2. Ejecutar acción
        demo.button("btnBuscar").click();
        
        // 3. Comprobar resultados
        demo.textBox("txtNombreCliente").requireText("DNI No Valido");
        demo.textBox("txtTotalCliente").requireText("");
    }
    
    
    @Test
    public void testConsultaCliente_ErrorBBDD() {
        // 1. INTRODUCIR DATOS (Inyección SQL para romper la consulta)
        // Al enviar una comilla simple, forzamos un error de sintaxis en H2
        demo.textBox("txtDNICliente").enterText("'");
        
        // 2. EJECUTAR ACCIÓN
        // El DAO intentará ejecutar la consulta rota, lanzará SQLException,
        // y tu VistaAgente lo atrapará en el bloque catch.
        demo.button("btnBuscar").click();
        
        // 3. COMPROBAR RESULTADOS
        demo.textBox("txtNombreCliente").requireText("Error en BBDD");
    }

    
}
