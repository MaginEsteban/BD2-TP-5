package ar.unrn.tp.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Getter
@Setter
public class Client {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long dni;
    private String name;    
    private String lastname;
    private String email;
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private List<Card> cardList;
    
    protected Client(){

    }
    // Constructor
    public Client(Long dni, String name, String lastname, String email) {

        if (dni == null){
            throw new IllegalArgumentException("El DNI no puede ser nulo");
        }

        if (dni <= 0) {
            throw new IllegalArgumentException("El DNI debe ser un numero mayor a 0");
        }

        if (name == null){
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }

        if (lastname == null){
            throw new IllegalArgumentException("El apellido no puede ser nulo");
        }

        if (email == null){
            throw new IllegalArgumentException("El email no puede ser nulo");
        }
        String regex = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";

        // Crear un patrón y un matcher
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        // Verificar si el correo cumple con el formato
        if (!matcher.matches()) {
            throw new IllegalArgumentException("El email debe tener un formato example@dominio.com");
        }

        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        //this.cardList = new ArrayList<>();
    }

    // Getters and setters
    private Long getId() {
        return id;
    }
    private void setId(Long id) {
        this.id = id;
    }
    private Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    private String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Card> getCardList() {
        return cardList;
    }
    private void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
    public void addTarjeta(Card c) {
        if (cardList == null) {
            List<Card> lis = new ArrayList<>();
            lis.add(c);
            this.setCardList(lis);
        } else {
            this.cardList.add(c);
        }


    }

}
