package ar.unrn.tp.jpa.services;

import ar.unrn.tp.api.ProductoInterfaz;
import ar.unrn.tp.modelo.Category;
import ar.unrn.tp.modelo.Discount;
import ar.unrn.tp.modelo.Product;

import jakarta.persistence.*;

import java.util.List;

public class ProductService implements ProductoInterfaz {
    private  EntityManagerFactory emf;
    private  EntityManager em;

    public ProductService(String s){
        emf = Persistence.createEntityManagerFactory(s);
        em = emf.createEntityManager();
    }
    @Override
    public void crearProducto(Long codigo, String descripcion, double precio, Category cat, Long idDiscount) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Discount d = em.find(Discount.class,idDiscount);
            Product producto = new Product(codigo, descripcion,cat,precio,d);
            em.persist(producto);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        
    }

    @Override
    public void modificarProducto(Long idProducto, Long codigo, String descripcion, double precio,Discount dis,Category cat) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Product producto = em.find(Product.class, idProducto);
            producto.setCode(codigo);
            producto.setDescription(descripcion);
            producto.setPrice(precio);
            producto.setPromo(dis);
            producto.setCategory(cat);
            em.merge(producto);
            tx.commit();
        }catch (OptimisticLockException e) {
            tx.rollback();
            throw new OptimisticLockException("Error el producto ha sido modificado por otro proceso" + e.getMessage());
        }
        catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public List<Product> listarProductos() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
    public List<String> listarCategorias() {
        return em.createQuery("SELECT c.name_category FROM Product p JOIN p.category c", String.class).getResultList();
    }

    public Product buscarProducto(Long idProducto){
        return em.find(Product.class,idProducto);
    }

}
