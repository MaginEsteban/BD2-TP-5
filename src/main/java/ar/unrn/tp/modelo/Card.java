package ar.unrn.tp.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue
    private Long id;
    private Long number;
    private String provider;

    protected Card(){

    }
    // Constructor
    public Card(Long num, String provider) {

        if (num == null){
            throw new IllegalArgumentException("El numero de la tarjeta no puede ser nulo");
        }

        if (num <= 0){
            throw new IllegalArgumentException("El numero de la tarjeta debe ser un numero mayor a 0");
        }

        if (provider == null){
            throw new IllegalArgumentException("El proveedor de la tarjeta no puede ser nulo");
        }


        this.number = num;
        this.provider = provider;
    }

    // Getter y setter
    private Long getId() {
        return id;
    }
    private void setId(Long id) {
        this.id = id;
    }
    public String getProvider() {
        return provider;
    }
    protected Long getNumber() {
        return number;
    }
    private void setProvider(String provider){
        this.provider = provider;
    }
    
    private String convertir() {
    	String numeroStr = number.toString(); // Convertir a String

        // Verificar que el número tiene al menos 4 dígitos
        if (numeroStr.length() > 4) {
            // Crear una cadena de asteriscos del tamaño adecuado
            String asteriscos = "*".repeat(numeroStr.length() - 4);
            String ultimosCuatro = numeroStr.substring(numeroStr.length() - 4); // Obtener los últimos 4 dígitos
            return asteriscos + ultimosCuatro; // Concatenar asteriscos y los últimos 4 dígitos
        } else {
            // Si el número tiene 4 o menos dígitos, simplemente devolver el número
            return numeroStr;
        }
    }

    @Override
    public String toString() {
        return this.getProvider() + " terminada en " + this.convertir();
    }
}

