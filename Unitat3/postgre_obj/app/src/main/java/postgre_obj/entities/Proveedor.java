package postgre_obj.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Proveedor extends Persona {
    private String empresa;
    @OneToOne
    private Persona persona;
    // Constructors, getters, and setters

    // You might want to add @ManyToOne or @OneToOne annotations for relationships
}