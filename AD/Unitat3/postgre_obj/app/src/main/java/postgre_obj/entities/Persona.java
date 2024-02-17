package postgre_obj.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Persona {

    @Id
    private int id;
    private String nombre;
    private String apellido;
    private Direccion direccion;
    private Contacto contacto;
    
    @OneToOne(mappedBy = "persona")
    private Cliente cliente;

    @OneToOne(mappedBy = "persona")
    private Empleado empleado;

    @OneToOne(mappedBy = "persona")
    private Proveedor proveedor;
    
    public Persona() {
    }
    public Persona(int id, String nombre, String apellido, Direccion direccion, Contacto contacto, Cliente cliente, Empleado empleado, Proveedor proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.contacto = contacto;
        this.cliente = cliente;
        this.empleado = empleado;
        this.proveedor = proveedor;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    // Getters y Setters
}