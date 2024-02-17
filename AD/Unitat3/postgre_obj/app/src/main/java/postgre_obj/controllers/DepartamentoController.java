package postgre_obj.controllers;

import postgre_obj.entities.Departamento;
import java.util.List;
import java.util.ArrayList;

public class DepartamentoController {

    private List<Departamento> departamentos = new ArrayList<>();

    public List<Departamento> getAllDepartamentos() {
        return departamentos;
    }

    public Departamento getDepartamento(int id) {
        for (Departamento departamento : departamentos) {
            if (departamento.getId() == id) {
                return departamento;
            }
        }
        return null;
    }

    public void addDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    public void updateDepartamento(Departamento departamento) {
        for (int i = 0; i < departamentos.size(); i++) {
            if (departamentos.get(i).getId() == departamento.getId()) {
                departamentos.set(i, departamento);
                break;
            }
        }
    }

    public void deleteDepartamento(int id) {
        departamentos.removeIf(departamento -> departamento.getId() == id);
    }
}
