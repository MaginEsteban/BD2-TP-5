package ar.unrn.tp.modelo;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalTime;
@Embeddable
public class Payment {
    private Card paymentCard;
    private Cart cart;

    protected Payment(){

    }
    // Constructor
    public Payment(Card paymentCard, Cart cart) {

        if (paymentCard == null){
            throw new IllegalArgumentException("La tarjeta no puede ser nula");
        }

        if (cart == null){
            throw new IllegalArgumentException("El carrito no puede ser nulo");
        }

        this.paymentCard = paymentCard;
        this.cart = cart;
    }

    // Getters y setters

    public Card getPaymentCard() {
        return paymentCard;
    }

    private void setPaymentCard(Card paymentCard) {
        this.paymentCard = paymentCard;
    }

    public Cart getCart() {
        return cart;
    }

    private void setCart(Cart cart) {
        this.cart = cart;
    }


    protected String getProviderCard(){
        return this.paymentCard.getProvider();
    }
    /*
    public Sale generarVenta(){
        return new Sale(LocalDate.now(), LocalTime.now(),client,this);
    }

     */
}

