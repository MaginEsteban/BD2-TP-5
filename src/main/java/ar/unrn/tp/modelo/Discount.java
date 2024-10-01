package ar.unrn.tp.modelo;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Long desc;
    private String marca;
    private LocalDate inicio;
    private LocalDate fin;

    protected Discount(){

    }
    public Discount(Long desc, String marca, LocalDate finicio,LocalDate ffin) {

        if (desc == null) {
            throw new IllegalArgumentException("El descuento no puede ser nulo");
        }

        if (desc <= 0) {
            throw new IllegalArgumentException("El descuento debe ser un numero mayor a 0");
        }

        if (marca == null){
            throw new IllegalArgumentException("La marca no puede ser nula");
        }

        if (finicio == null) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser nula");
        }
        if (ffin == null) {
            throw new IllegalArgumentException("La fecha de fin no puede ser nula");
        }

        if(finicio.isAfter(ffin)){
            throw new IllegalArgumentException("La fecha de incio debe ser anterior a la fecha de finalizacion del descuento");
        }
        if(finicio.equals(ffin)){
            throw new IllegalArgumentException("Las fechas no pueden ser las mismas");
        }

        this.desc = desc;
        this.marca = marca;
        this.inicio = finicio;
        this.fin = ffin;
    }

    
    private Long getId() {
		return id;
	}
	private void setId(Long id) {
		this.id = id;
	}
	public Long getDesc() {
        return desc;
    }
    public void setDesc(Long desc) {
        this.desc = desc;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public LocalDate getInicio() {
        return inicio;
    }
    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }
    public LocalDate getFin() {
        return fin;
    }
    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

}
