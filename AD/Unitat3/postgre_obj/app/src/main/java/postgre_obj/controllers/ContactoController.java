package postgre_obj.controllers;

import postgre_obj.entities.Contacto;
import java.util.List;
import java.util.ArrayList;

public class ContactoController {

    private List<Contacto> contactos = new ArrayList<>();

    public List<Contacto> getAllContactos() {
        return contactos;
    }

    public Contacto getContacto(String telefono) {
        for (Contacto contacto : contactos) {
            if (contacto.getTelefono().equals(telefono)) {
                return contacto;
            }
        }
        return null;
    }

    public void addContacto(Contacto contacto) {
        contactos.add(contacto);
    }

    public void updateContacto(Contacto contacto) {
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getTelefono().equals(contacto.getTelefono())) {
                contactos.set(i, contacto);
                break;
            }
        }
    }

    public void deleteContacto(String telefono) {
        contactos.removeIf(contacto -> contacto.getTelefono().equals(telefono));
    }
}
