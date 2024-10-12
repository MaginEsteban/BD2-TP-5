package ar.unrn.tp.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class Category {
    private String name_category;
    protected Category(){

    }
    // Constructor
    public Category(String name_category){


        if (name_category == null) {
            throw new IllegalArgumentException("El nombre de la categoria no puede ser nulo");
        }


        this.name_category = name_category;
    }

    // Getters and setters
    public String getNameCategory() {
        return name_category;
    }
    public void setNameCategory(String name_category) {
        this.name_category = name_category;
    }
    public String toString() {
        return this.name_category;
    }
}
