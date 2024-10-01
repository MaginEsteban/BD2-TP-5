package ar.unrn.tp.modelo;


import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long code;
    private String description;
    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_discount")
    private Discount promo;
    @Embedded
    private Category category;

    protected Product(){

    }
    // Constructor
    public Product(Long code,String description, Category category, double price,Discount descuento) {


        if (description == null){
            throw new IllegalArgumentException("La descripcion no puede ser nula");
        }

        if (category == null){
            throw new IllegalArgumentException("La categoria no puede ser nula");
        }

        if (price <= 0){
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }


        this.code = code;
        this.description = description;
        this.category = category;
        this.price = price;
        this.promo = descuento;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long getCode() {
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }
    protected String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    private Category getCategory() {
        return category;
    }
    private void setCategory(Category category) { this.category = category; }
    protected double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    protected Discount getPromo() {
        return promo;
    }
    public void setPromo(Discount promo) {
        this.promo = promo;
    }
    public boolean existeDescuento(){
        if(promo!= null) {
            return promo.getMarca().equals(description);

        }
        return false;
    }
    @Override
    public String toString() {
        return this.category.getNameCategory() + " " + this.description + "    $" + this.price;
    }
}

