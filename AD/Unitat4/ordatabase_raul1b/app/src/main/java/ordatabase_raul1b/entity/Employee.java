/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordatabase_raul1b.entity;

/**
 *
 * @author rauls
 */
import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", nombre=" + nombre + ", tipoCalle=" + tipoCalle + ", calle=" + calle + ", numero=" + numero + ", telefonos=" + telefonos + ", superior=" + superior + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoCalle() {
        return tipoCalle;
    }

    public void setTipoCalle(String tipoCalle) {
        this.tipoCalle = tipoCalle;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Set<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Set<String> telefonos) {
        this.telefonos = telefonos;
    }

    public Employee getSuperior() {
        return superior;
    }

    public void setSuperior(Employee superior) {
        this.superior = superior;
    }

    public Employee(Long id, String nombre, String tipoCalle, String calle, String numero, Set<String> telefonos, Employee superior) {
        this.id = id;
        this.nombre = nombre;
        this.tipoCalle = tipoCalle;
        this.calle = calle;
        this.numero = numero;
        this.telefonos = telefonos;
        this.superior = superior;
    }

    public Employee() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipoCalle;
    private String calle;
    private String numero;
    @ElementCollection
    private Set<String> telefonos;
    @ManyToOne
    private Employee superior;

    // getters y setters
}
