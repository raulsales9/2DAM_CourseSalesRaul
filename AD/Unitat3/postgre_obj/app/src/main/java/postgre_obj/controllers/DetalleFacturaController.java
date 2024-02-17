package postgre_obj.controllers;

import postgre_obj.entities.DetalleFactura;
import java.util.List;
import java.util.ArrayList;

public class DetalleFacturaController {

    private List<DetalleFactura> detallesFactura = new ArrayList<>();

    public List<DetalleFactura> getAllDetallesFactura() {
        return detallesFactura;
    }

    public DetalleFactura getDetalleFactura(int idFactura) {
        for (DetalleFactura detalleFactura : detallesFactura) {
            if (detalleFactura.getIdFactura() == idFactura) {
                return detalleFactura;
            }
        }
        return null;
    }

    public void addDetalleFactura(DetalleFactura detalleFactura) {
        detallesFactura.add(detalleFactura);
    }

    public void updateDetalleFactura(DetalleFactura detalleFactura) {
        for (int i = 0; i < detallesFactura.size(); i++) {
            if (detallesFactura.get(i).getIdFactura() == detalleFactura.getIdFactura()) {
                detallesFactura.set(i, detalleFactura);
                break;
            }
        }
    }

    public void deleteDetalleFactura(int idFactura) {
        detallesFactura.removeIf(detalleFactura -> detalleFactura.getIdFactura() == idFactura);
    }
}
