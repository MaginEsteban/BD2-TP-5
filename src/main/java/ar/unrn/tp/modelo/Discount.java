package ar.unrn.tp.modelo;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Long descu;
    private String marca;
    @Temporal(TemporalType.DATE)
    private LocalDate inicio;
    @Temporal(TemporalType.DATE)
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

        this.descu = desc;
        this.marca = marca;
        this.inicio = finicio;
        this.fin = ffin;
    }

    
    public Long getId() {
		return id;
	}
	private void setId(Long id) {
		this.id = id;
	}
	public Long getDescu() {
        return descu;
    }
    public void setDescu(Long descu) {
        this.descu = descu;
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
