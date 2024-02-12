package jaume.ad.domain.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Competicion")
public class Competicion {
    @Id
    @GeneratedValue
    private long idCompeticion;

    @Column
    private String nombre;

    @Column
    private double premio;
    @ManyToMany
    @JoinTable(
            name = "Competicion_Equipo",
            joinColumns = @JoinColumn(name = "idCompeticion"),
            inverseJoinColumns = @JoinColumn(name = "idEquipo"))
    private Set<Equipo> equipos;


    public long getIdCompeticion() {
        return idCompeticion;
    }

    public void setIdCompeticion(long idCompeticion) {
        this.idCompeticion = idCompeticion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPremio() {
        return premio;
    }

    public void setPremio(double premio) {
        this.premio = premio;
    }

    public Set<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(Set<Equipo> equipos) {
        this.equipos = equipos;
    }
}
