package postgre_obj.entities;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Cliente extends Persona {
    private Date fechaRegistro;

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
public String toString() {
    return "Cliente{" +
            "ID=" + getId() +
            ", Nombre='" + getNombre() + '\'' +
            ", Apellido='" + getApellido() + '\'' +
            ", FechaRegistro=" + getFechaRegistro() +
            '}';
}
}