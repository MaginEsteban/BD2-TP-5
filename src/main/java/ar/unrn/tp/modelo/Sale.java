package ar.unrn.tp.modelo;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Sale {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private LocalTime time;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Client client;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Payment payment;

    //Tp5
    private String saleNumber;


    protected Sale(){

    }
    // Constructor
    public Sale(Date date, LocalTime time, Client client, Payment payment) {

        if (date == null){
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }

        if (time == null){
            throw new IllegalArgumentException("La hora no puede ser nula");
        }

        if (client == null){
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }

        if (payment == null){
            throw new IllegalArgumentException("El pago no puede ser nulo");
        }

        this.date = date;
        this.time = time;
        this.client = client;
        this.payment = payment;
    }

    // Getters y setters
    private Long getId() {
        return id;
    }
    private void setId(Long id) {
        this.id = id;
    }
    protected Date getDate() {
        return date;
    }
    private void setDate(Date date) {
        this.date = date;
    }
    private LocalTime getTime() {
        return time;
    }
    private void setTime(LocalTime time) {
        this.time = time;
    }
    private Client getClient() {
        return client;
    }
    private void setClient(Client client) {
        this.client = client;
    }
    protected Payment getPayment() {
        return payment;
    }
    private void setPayment(Payment payment) {
        this.payment = payment;
    }

    public double total(Card c,LocalDate d){
        return payment.getCart().total() - this.aplicarDescuentos(c,d);
    }

    public double aplicarDescuentos(Card c, LocalDate date){
        return payment.getCart().calcularTodosLosDescuentos(c,date);
    }
    public double aplicarSoloCompra(Card c,LocalDate date){
        return payment.getCart().calcularDescuentoCompra(c,date);
    }
    public double aplicarSoloProducto(LocalDate date){
        return payment.getCart().calcularDescuentoMarca(date);
    }
    public String toString() {
        return "Venta: " + this.time;
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }
    /*
    public double test(String provider,LocalDate startDate, LocalDate endDate){
        // Verifica si la fecha está dentro del rango, incluyendo los extremos.

        ArrayList<Product> aux = new ArrayList<>();
        aux = this.payment.getCart();
        int size = this.payment.getCart().size();
        int count=0;

        double descuento=0;
        if(payment.getProviderCard().equals(provider) && ((date.isEqual(startDate) ||
           date.isAfter(startDate)) && (date.isEqual(endDate) || date.isBefore(endDate))))
        {
            while(count < size){
                descuento = descuento + aux.get(count).getPrice();
                count++;
            }

            return descuento - (descuento*0.08);
        }

        System.out.println("No se puede aplicar el descuento en la compra");

        return getTotalPrice();
    }

    public double getTotalPrice(){
        ArrayList<Product> aux = new ArrayList<>();
        aux = this.payment.getCart();
        int size = this.payment.getCart().size();
        int count=0;
        double descuento=0;

        while(count < size){
            descuento = descuento + aux.get(count).getPrice();
            count++;
        }

        return descuento;
    }

     */
}

