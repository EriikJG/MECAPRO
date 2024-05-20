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

}
