package ar.unrn.tp.jpa.services;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import ar.unrn.tp.api.DescuentoInterfaz;
import ar.unrn.tp.modelo.Discount;

public class DiscountService implements DescuentoInterfaz {
	private EntityManagerFactory emf; 
    private EntityManager em;
    
    public DiscountService (String s) {
    	emf = Persistence.createEntityManagerFactory(s);
    	em = emf.createEntityManager();
    }
	@Override
	public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta,float porcentaje) {
		
	}

	@Override
	public void crearDescuento(Long porcentaje, String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta) {
		EntityTransaction tx = em.getTransaction();
        try {
        	tx.begin();
        	Discount descuento = new Discount(porcentaje,marcaProducto,fechaDesde,fechaHasta);
        	em.persist(descuento);
        	tx.commit();
        	
        }catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } /*finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }*/
	}
	
	public List<Discount> descuentos () {
        return em.createQuery("SELECT d FROM Discount d", Discount.class).getResultList();
    }

}
