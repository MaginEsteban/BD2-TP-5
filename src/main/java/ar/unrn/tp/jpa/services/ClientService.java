package ar.unrn.tp.jpa.services;

import ar.unrn.tp.api.ClienteInterfaz;
import ar.unrn.tp.modelo.Card;
import ar.unrn.tp.modelo.Client;

import jakarta.persistence.*;
import java.util.List;

public class ClientService implements ClienteInterfaz {
	private EntityManagerFactory emf;
    private  EntityManager em;
    
    public ClientService (String s) {
    	emf = Persistence.createEntityManagerFactory(s);
    	em = emf.createEntityManager();
    }
    public void crearCliente(Long dni,String nombre, String apellido, String email) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("Entre");
            /*
            TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.dni = :dni",Client.class);
            query.setParameter("dni", dni);
            List<Client> resultados = query.getResultList();

            if (resultados.isEmpty()) {
                // Si no existe, crear y persistir el nuevo cliente
                Client cliente = new Client(dni, nombre, apellido, email);
                em.persist(cliente);
                System.out.println("Cliente creado con éxito.");
            } else {
                System.out.println("Error: Ya existe un cliente con ese DNI.");
            }*/
            Client cliente = new Client(dni, nombre, apellido, email);
            em.persist(cliente);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen())
                em.close();

        }
    }

    @Override
    public void modificarCliente(Long idCliente,Long dni, String nombre,String apellido,String email) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Client cliente = em.getReference(Client.class, idCliente);
            cliente.setName(nombre);
            cliente.setLastname(apellido);
            cliente.setDni(dni);
            cliente.setEmail(email);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } /*finally {
           if (em != null && em.isOpen()) {
                em.close();
           }
        }*/
    }

    @Override
    public void agregarTarjeta(Long idCliente, Card card) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Client cliente = em.find(Client.class, idCliente);
            if (cliente != null) {
                cliente.addTarjeta(card);
                em.merge(cliente);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }/* finally{
                if (em != null && em.isOpen())
                    em.close();

            }
            */


    }
    @Override
    public List<Card> listarTarjetas(Long idCliente) {
        Client cliente = em.getReference(Client.class, idCliente);
        return cliente.getCardList();
    }

    @Override
    public Client buscarCliente(Long idCliente) {
        return em.find(Client.class, idCliente);
    }
    
}

