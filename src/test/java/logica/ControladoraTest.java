package logica;

import Persistencia.AutomovilJpaController;
import Persistencia.ClienteJPAController;
import Persistencia.MecanicaJPAController;
import Persistencia.ReparacionJpaController;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControladoraTest {

    private Controladora controladora;
    private Cliente cliente;


    @Before
    public void setUp() {
        controladora = new Controladora();


    }

    @Test
    public void dadoClienteYVehiculoConPlacaFormatoEcuatoriano_cuandoSeRegistraVehiculo_entoncesVehiculoEsRegistrado() {

        cliente = new Cliente(1234167891, "Juan", "Perez", "0987654321", "juan.perez@example.com", "Direccion 123", new ArrayList<>(), null);
        String placa = "ASC-2254";
        String marca = "Toyota";
        String anioFab = "2020";
        List<Reparacion> reparaciones = new ArrayList<>();

        // Crear el cliente y agregarlo al sistema
        controladora.crearCliente(cliente.getCedula(), cliente.getNombre(), cliente.getApellido(), cliente.getCorreo(), cliente.getTelefono(), cliente.getDireccion(), cliente.getAutomoviles(), null);

        // Registrar el vehículo
        controladora.crearAutomovil(placa, marca, anioFab, cliente, reparaciones);

        // Verificar que el vehículo ha sido registrado y es visible en la lista de vehículos
        Automovil vehiculoRegistrado = controladora.encontrarAuto(placa);
        assertNotNull(vehiculoRegistrado);
        assertEquals(placa, vehiculoRegistrado.getPlaca());
    }

    @Test
    public void dadoVehiculoConPlacaExistente_cuandoSeIntentaRegistrarOtraVez_entoncesVehiculoNoEsRegistrado() {
        cliente = new Cliente(1234567899, "Juan", "Perez", "0987654321", "juan.perez@example.com", "Direccion 123", new ArrayList<>(), null);
        String placa = "ACD-0234";
        String marca = "Toyota";
        String anioFab = "2020";
        List<Reparacion> reparaciones = new ArrayList<>();

        // Crear el cliente y agregarlo al sistema
        controladora.crearCliente(cliente.getCedula(), cliente.getNombre(), cliente.getApellido(), cliente.getCorreo(), cliente.getTelefono(), cliente.getDireccion(), cliente.getAutomoviles(), null);

        // Registrar el primer vehículo
        controladora.crearAutomovil(placa, marca, anioFab, cliente, reparaciones);

        // Intentar registrar el mismo vehículo nuevamente
        controladora.crearAutomovil(placa, marca, anioFab, cliente, reparaciones);

        // Verificar que el vehículo no ha sido registrado nuevamente
        List<Automovil> listaAutomoviles = controladora.getAutomoviles();
        long count = listaAutomoviles.stream().filter(auto -> auto.getPlaca().equals(placa)).count();
        assertEquals(1, count);
    }
    @Test
    public void dadoVehiculoConPlacaYReparacionValida_cuandoSeRegistraReparacion_entoncesReparacionEsRegistrada() {
        // Arrange
        String descripcion = "Cambio de aceite2";
        String costo = "50";

        // Act
        Automovil automovilRecuperado = controladora.encontrarAuto("ACD-0234");
        assertNotNull(automovilRecuperado);
        int cantidadReparacionesAntes = automovilRecuperado.getReparaciones().size();
        controladora.crearReparacion(descripcion, costo, automovilRecuperado);

        // Assert
        Automovil automovilConReparaciones = controladora.encontrarAuto("ACD-0234");
        assertNotNull(automovilConReparaciones);
        int cantidadReparacionesDespues = automovilConReparaciones.getReparaciones().size();
        assertEquals("Se esperaba que se registrara una nueva reparación", cantidadReparacionesAntes , cantidadReparacionesDespues);
    }



    @Test(expected = IllegalArgumentException.class)
    public void dadoReparacionConInformacionIncompleta_cuandoSeIntentaRegistrar_entoncesLanzaExcepcion() {
        String descripcion = "";
        String costo = "50";
        Automovil automovil = controladora.encontrarAuto("ACD-0234");

        /// assertNotNull("El automóvil no debe ser nulo", automovil);

        // Intentar registrar la reparación con información incompleta
        controladora.crearReparacion(descripcion, costo, automovil);

        // Verificar que la reparación no esté registrada
        List<Reparacion> reparaciones = automovil.getReparaciones();
        assertTrue(reparaciones.isEmpty());
    }


    @Test
    public void dadoDatosClienteValidos_cuandoSeCreaCliente_entoncesClienteEsCreado() {
        // Arrange
        int cedula = 1230567890;
        String nombre = "Juan";
        String apellido = "Perez";
        String correo = "juan@example.com";
        String telefono = "123456789";
        String direccion = "Calle Principal";

        // Act
        controladora.crearCliente(cedula, nombre, apellido, correo, telefono, direccion, new ArrayList<>(), null);

        // Assert
        Cliente clienteCreado = controladora.encontrarUsuario(cedula);
        assertNotNull("El cliente debería haber sido creado", clienteCreado);
    }

    @Test
    public void dadoClienteConCedulaExcediendoMaximoCaracteres_cuandoSeIntentaCrearCliente_entoncesLanzaExcepcion() {
        // Arrange
        int cedula = 454640650; // Esto excede el máximo de caracteres permitidos
        String nombre = "Juan";
        String apellido = "Perez";
        String correo = "juan@example.com";
        String telefono = "123456789";
        String direccion = "Calle 123";

        // Act
        try {
            controladora.crearCliente(cedula, nombre, apellido, correo, telefono, direccion, new ArrayList<>(), null);
            fail("Se esperaba que se lanzara una IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Assert
            assertEquals("La cédula excede el máximo de caracteres permitidos", e.getMessage());
            assertNull("No se debe haber registrado ningún cliente", controladora.encontrarUsuario(cedula));
        }
    }

}