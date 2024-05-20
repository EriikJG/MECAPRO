package logica;

import Persistencia.AutomovilJpaController;
import Persistencia.ClienteJPAController;
import Persistencia.MecanicaJPAController;
import Persistencia.ReparacionJpaController;

import java.util.ArrayList;
import java.util.List;

    public class Controladora {

        MecanicaJPAController controlMecanica = new MecanicaJPAController();
        ClienteJPAController controlUsuario = new ClienteJPAController();
        AutomovilJpaController controlAutomovil = new AutomovilJpaController();

        ReparacionJpaController controlReparacion = new ReparacionJpaController();
        public Controladora() {
        }

        public void crearMecanica(String nombre, String direccion , String correo , List<Cliente> clientes) {

            Mecanica mecanicaController = controlMecanica.findMecanica(1);

            if (mecanicaController == null || !nombre.equals(mecanicaController.getNombre())){
                Mecanica mecanica = new Mecanica(nombre,direccion,correo, clientes);
                controlMecanica.create(mecanica);
            }

        }

        public Mecanica encontrarMecanica(int id) {
            return controlMecanica.findMecanica(id);
        }

        public void crearCliente(int cedula, String nombre, String apellido, String correo, String telefono, String direccion, List<Automovil> autos, Mecanica mecanica) {
            // Verificar si la cédula excede el máximo de caracteres permitidos
            if (String.valueOf(cedula).length() != 10) {
                throw new IllegalArgumentException("La cédula excede el máximo de caracteres permitidos");
            }

            Cliente cliente = new Cliente(cedula,nombre,apellido,telefono,correo,direccion,autos, mecanica);
            controlUsuario.create(cliente);
        }


        public Cliente encontrarUsuario(int cedula) {
            List<Cliente> clientes =  controlUsuario.findClienteEntities();
            for (Cliente cliente : clientes) {
                if(cliente.getCedula() == cedula) {
                    return cliente;
                }
            }
            return null;
        }


        public void crearAutomovil(String placa, String marca, String anioFab, Cliente propietario, List<Reparacion> reparaciones) {
            Automovil autoExistente = encontrarAuto(placa);
            if (autoExistente == null) {
                Automovil auto = new Automovil();
                aniadirDatosAutomovil(placa, marca, anioFab, propietario, reparaciones, auto);
                controlAutomovil.create(auto);
                aniadirAutomovil(auto, propietario);
            } else {
                System.out.println("El automóvil con la placa " + placa + " ya está registrado.");
            }
        }



        private void aniadirAutomovil(Automovil auto, Cliente propietario) {
            List<Automovil> autos = encontrarUsuario(propietario.getCedula()).getAutomoviles();
            autos.add(auto);
        }

        private static void aniadirDatosAutomovil(String placa, String marca, String anioFab, Cliente propietario, List<Reparacion> reparaciones, Automovil auto) {
            auto.setPlaca(placa);
            auto.setMarca(marca);
            auto.setAñoFabricacion(anioFab);
            auto.setPropietario(propietario);
            auto.setReparaciones(reparaciones);
        }

        public Automovil encontrarAuto(String placa) {
            List<Automovil> listaAutomoviles =  controlAutomovil.findAutomovilEntities();
            for (Automovil auto : listaAutomoviles) {
                if(auto.getPlaca().equals(placa)) {
                    return auto;
                }
            }
            return null;
        }

        public void crearReparacion(String descripcion, String costo, Automovil automovil) {
            if (descripcion == null || descripcion.isEmpty() || costo == null || costo.isEmpty()) {
                throw new IllegalArgumentException("La descripción y el costo no pueden estar vacíos");
            }

            Reparacion reparacion = new Reparacion();
            reparacion.setDescripcion(descripcion);
            reparacion.setCosto(costo);
            reparacion.setAutomovil(automovil);

            controlReparacion.create(reparacion);
            aniadirReparacion(automovil, reparacion);
        }

        private void aniadirReparacion(Automovil automovil, Reparacion reparacion) {
            List<Reparacion> reparaciones = automovil.getReparaciones();
            if (reparaciones == null) {
                reparaciones = new ArrayList<>();
                automovil.setReparaciones(reparaciones);
            }
            reparaciones.add(reparacion);
        }
        private static void enviarDatosReparacion(String descripcion, String costo, Automovil automovil, Reparacion reparacion) {
            reparacion.setDescripcion(descripcion);
            reparacion.setCosto(costo);
            reparacion.setAutomovil(automovil);
        }


        public List<Reparacion> getReparaciones() {
            return controlReparacion.findReparacionEntities();
        }

        public void crearClienteConCamposIncompletos(String nombre, String apellido, String email) {
            if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() || email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Los campos nombre, apellido y email no pueden estar vacíos");
            }
        }

        public List<Cliente> getClientes() {
            return controlUsuario.findClienteEntities();
        }



        public List<Automovil> getAutomoviles() {
            return controlAutomovil.findAutomovilEntities();
        }



    }


