package postgre_obj.controllers;

import postgre_obj.entities.Empleado;
import java.util.List;
import java.util.ArrayList;

public class EmpleadoController {

    private List<Empleado> empleados = new ArrayList<>();

    public List<Empleado> getAllEmpleados() {
        return empleados;
    }

    public Empleado getEmpleado(int id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }
        return null;
    }

    public void addEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void updateEmpleado(Empleado empleado) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId() == empleado.getId()) {
                empleados.set(i, empleado);
                break;
            }
        }
    }

    public void deleteEmpleado(int id) {
        empleados.removeIf(empleado -> empleado.getId() == id);
    }
}
