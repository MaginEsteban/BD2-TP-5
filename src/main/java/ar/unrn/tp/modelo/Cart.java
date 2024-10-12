package ar.unrn.tp.modelo;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> cart;
    @OneToOne(cascade = CascadeType.MERGE)
    private Discount promoCart;

    protected Cart(){

    }
    public Cart(List<Product> cart, Discount promo) {

        if (cart == null) {
            throw new IllegalArgumentException("El carrito no puede ser nulo");
        }

        if (cart.size() <= 0) {
            throw new IllegalArgumentException("La cantidad de productos del carrito debe ser al menos 1");
        }


        this.cart = cart;
        this.promoCart = promo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private List<Product> getCart() {
        return cart;
    }
    private void setCart(List<Product> cart) {
        this.cart = cart;
    }
    public Discount getPromoCart() {
        return promoCart;
    }
    private void setPromoCart(Discount promoCart) {
        this.promoCart = promoCart;
    }

    public double total(){
        double res = 0.0;
        for (Product prod: cart){
            res = res + prod.getPrice();
        }
        return res;
    }

    public double calcularTodosLosDescuentos(Card c, LocalDate date){
        double desc = 0.0;
        desc = desc + calcularDescuentoCompra(c,date) + calcularDescuentoMarca(date);
        return desc;
    }
    // Calcula el descuento del carrito completo en caso de que exista si no devuelve 0.0
    public double calcularDescuentoCompra(Card c, LocalDate date){
        double descuento = 0.0;
        if( cart != null && c != null) {
            List<Product> aux = new ArrayList<>();
            aux = this.cart;
            int sizeProductos = this.cart.size();
            int count = 0;
            if ((promoCart != null) && (c != null) && (promoCart.getMarca().equals(c.getProvider())) && (date.isEqual(promoCart.getInicio()) ||
                    date.isAfter(promoCart.getInicio())) && (date.isEqual(promoCart.getFin()) || date.isBefore(promoCart.getFin()))) {
            	while (count < sizeProductos) {
                    descuento = descuento + aux.get(count).getPrice();
                    count++;
                }
                descuento = descuento * promoCart.getDescu() / 100;
            }
        }
        return descuento;
    }

    //Calcula el descuento en caso de coincidir la marca si no devuelve 0.0
    public double calcularDescuentoMarca(LocalDate date){
        double descuento = 0.0;
        if(cart != null) {
            List<Product> aux = new ArrayList<>();
            aux = this.cart;
            int sizeProductos = this.cart.size();
            int count = 0;
            while (count < sizeProductos) {
                if ((aux.get(count).existeDescuento()) && (date.isEqual(aux.get(count).getPromo().getInicio()) ||
                        date.isAfter(aux.get(count).getPromo().getInicio()) && (date.isEqual(aux.get(count).getPromo().getFin()) || date.isBefore(aux.get(count).getPromo().getFin())))){
                    descuento = descuento + aux.get(count).getPrice() * aux.get(count).getPromo().getDescu() / 100;
                }
                count++;
            }
        }
        return descuento;
    }
}
