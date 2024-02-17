package postgre_obj.controllers;

import postgre_obj.entities.Persona;
import java.util.List;
import java.util.ArrayList;

public class PersonaController {

    private List<Persona> personas = new ArrayList<>();

    public List<Persona> getAllPersonas() {
        return personas;
    }

    public Persona getPersona(int id) {
        for (Persona persona : personas) {
            if (persona.getId() == id) {
                return persona;
            }
        }
        return null;
    }

    public void addPersona(Persona persona) {
        personas.add(persona);
    }

    public void updatePersona(Persona persona) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getId() == persona.getId()) {
                personas.set(i, persona);
                break;
            }
        }
    }

    public void deletePersona(int id) {
        personas.removeIf(persona -> persona.getId() == id);
    }
}
