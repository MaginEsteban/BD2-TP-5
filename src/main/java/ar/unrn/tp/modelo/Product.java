package ar.unrn.tp.modelo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DialectOverride;

@Entity
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long code;
    private String description;
    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Discount promo;
    @Embedded
    private Category category;
    @Version
    private Long version;

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

    public Long getCode() {
        return code;
    }
    public void setCode(Long code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) { this.category = category; }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Discount getPromo() {
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

