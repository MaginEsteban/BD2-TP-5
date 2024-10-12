package ar.unrn.tp.jpa.services;

import ar.unrn.tp.api.VentaInterfaz;
import ar.unrn.tp.modelo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.util.List;
import java.util.Random;

public class SaleService implements VentaInterfaz {
    private EntityManagerFactory emf;
    private EntityManager em;
    private final ClientService clService;
    private final ProductService pService;
    private final PaymentService payService;

    public SaleService(String s, ClientService c, ProductService p, PaymentService pa) {
        emf = Persistence.createEntityManagerFactory(s);
        em = emf.createEntityManager();
        this.clService = c;
        this.pService = p;
        this.payService = pa;
    }

    @Override
    public void realizarVenta(Long idCliente,Long idPayment) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Client cliente = clService.buscarCliente(idCliente);
            Payment pago = payService.buscarPago(idPayment);
            Sale venta = new Sale(Date.valueOf(LocalDate.now()), LocalTime.now(), cliente,pago);
            em.persist(venta);
            tx.commit();
            em.clear();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public double calcularMonto(Long idSale, LocalDate d,Long idTarjeta) {
        EntityTransaction tx = em.getTransaction();
        double tot = 0.0;
        try {
            tx.begin();
            Sale s = this.buscarVenta(idSale);
            Card c = em.find(Card.class,idTarjeta);
            tot = s.total(c,d);
            tx.commit();
            em.clear();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        
        return tot;
    }

        public List<Sale> ventas () {
            return em.createQuery("SELECT s FROM Sale s", Sale.class).getResultList();
        }


        public Sale buscarVenta (Long idSale){
            return em.find(Sale.class,idSale);
        }
}
