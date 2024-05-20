package Persistencia;

import logica.Cliente;
import logica.Mecanica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MecanicaJPAController implements Serializable  {



/**
 *
 * @author USER
**/
    public MecanicaJPAController(EntityManagerFactory emf) {

        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MecanicaJPAController() {
        emf = Persistence.createEntityManagerFactory("mecanica_PU");
    }
    public void create(Mecanica mecanica) {
        // Verifica si la lista de clientes en la entidad Cliente es nula y la inicializa si es necesario
        if (mecanica.getClientes() == null) {
            mecanica.setClientes(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            // Inicia la transacción con el EntityManager
            em = getEntityManager();
            em.getTransaction().begin();
            // Adjunta los automóviles al EntityManager
            List<Cliente> clientesAdjuntos = adjuntarClientes(em, mecanica.getClientes());
            mecanica.setClientes(clientesAdjuntos);
            // Persiste la entidad Mecanica en la base de datos
            em.persist(mecanica);
            // Confirma la transacción
            em.getTransaction().commit();
        } finally {
            // Cierra el EntityManager
            if (em != null) {
                em.close();
            }
        }
    }

    // Adjunta los automóviles al EntityManager
    private List<Cliente> adjuntarClientes(EntityManager em, List<Cliente> clientes) {
        List<Cliente> clientesAdjuntos = new ArrayList<>();
        for (Cliente cliente : clientes) {
            // Obtiene una referencia a cada automóvil y lo agrega a la lista de automóviles adjuntos
            clientesAdjuntos.add(em.getReference(Cliente.class, cliente.getCedula()));
        }
        return clientesAdjuntos; // Devuelve la lista de automóviles adjuntos
    }

    public Mecanica findMecanica(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mecanica.class, id);
        } finally {
            em.close();
        }
    }



}
