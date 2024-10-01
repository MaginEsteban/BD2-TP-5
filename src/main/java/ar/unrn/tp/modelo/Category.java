package ar.unrn.tp.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Category {
    private String name;
    protected Category(){

    }
    // Constructor
    public Category(String name_category){


        if (name_category == null) {
            throw new IllegalArgumentException("El nombre de la categoria no puede ser nulo");
        }


        this.name = name_category;
    }

    // Getters and setters
    public String getNameCategory() {
        return name;
    }
    private void setNameCategory(String name_category) {
        this.name = name_category;
    }
    public String toString() {
        return "Nombre de Categoria: " + this.name;
    }
}
