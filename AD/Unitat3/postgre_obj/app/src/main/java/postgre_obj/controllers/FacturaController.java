package postgre_obj.controllers;

import postgre_obj.entities.Factura;
import java.util.List;
import java.util.ArrayList;

public class FacturaController {

    private List<Factura> facturas = new ArrayList<>();

    public List<Factura> getAllFacturas() {
        return facturas;
    }

    public Factura getFactura(int idFactura) {
        for (Factura factura : facturas) {
            if (factura.getIdFactura() == idFactura) {
                return factura;
            }
        }
        return null;
    }

    public void addFactura(Factura factura) {
        facturas.add(factura);
    }

    public void updateFactura(Factura factura) {
        for (int i = 0; i < facturas.size(); i++) {
            if (facturas.get(i).getIdFactura() == factura.getIdFactura()) {
                facturas.set(i, factura);
                break;
            }
        }
    }

    public void deleteFactura(int idFactura) {
        facturas.removeIf(factura -> factura.getIdFactura() == idFactura);
    }
}
