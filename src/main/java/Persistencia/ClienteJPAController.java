package Persistencia;

import logica.Automovil;
import logica.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteJPAController implements Serializable {
    // EntityManagerFactory para la gestión de entidades
    private final EntityManagerFactory emf;

    // Constructor que recibe una instancia de EntityManagerFactory
    public ClienteJPAController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Constructor por defecto que crea una instancia de EntityManagerFactory
    public ClienteJPAController() {
        emf = Persistence.createEntityManagerFactory("mecanica_PU");
    }

    // Método para obtener un EntityManager
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Método para crear una nueva reparación en la base de datos
    public void create(Cliente cliente) {
        // Verifica si la lista de reparaciones en el automóvil es nula y la inicializa si es necesario
        if (cliente.getAutomoviles() == null) {
            cliente.setAutomoviles(new ArrayList<Automovil>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Adjunta las reparaciones al EntityManager
            adjuntarAutomoviles(em, cliente);
            // Persiste el automóvil en la base de datos
            em.persist(cliente);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void adjuntarAutomoviles(EntityManager em, Cliente cliente) {
        List<Automovil> automoviles = cliente.getAutomoviles();
        if (automoviles != null && !automoviles.isEmpty()) {
            List<Automovil> automovilesAdjuntos = new ArrayList<>();
            for (Automovil automovil : automoviles) {
                automovilesAdjuntos.add(em.getReference(automovil.getClass(), automovil.getId()));
            }
            cliente.setAutomoviles(automovilesAdjuntos);
        }
    }
    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    // Método privado para encontrar las entidades Automovil con opciones de paginación
    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Cliente> cq = em.getCriteriaBuilder().createQuery(Cliente.class);
            cq.select(cq.from(Cliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}