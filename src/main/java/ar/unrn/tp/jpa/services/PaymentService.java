package ar.unrn.tp.jpa.services;

import ar.unrn.tp.api.PaymentInterfaz;
import ar.unrn.tp.modelo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PaymentService implements PaymentInterfaz{

    private EntityManagerFactory emf;
    private EntityManager em;
    private final ProductService pService;


    public PaymentService(String s,ProductService p) {
        emf = Persistence.createEntityManagerFactory(s);
        em = emf.createEntityManager();
        this.pService = p;
    }

    @Override
    public Long crearPago(Long idCard,Long idCart) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            System.out.println("ENTRE");
            Cart cart = this.buscarCarrito(idCart);
            Card card = em.find(Card.class,idCard);
            Payment pay = new Payment(card,cart);
            em.persist(pay);
            tx.commit();
            return pay.getId();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long crearCarrito(List<Product> lis, Discount dis) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Cart carr = new Cart(lis,dis);
            em.persist(carr);
            tx.commit();
            return carr.getId();

        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public Cart buscarCarrito(Long idCart) {
        return em.find(Cart.class, idCart);
    }
    public Payment buscarPago(Long idPay) {
        return em.find(Payment.class, idPay);
    }
}
