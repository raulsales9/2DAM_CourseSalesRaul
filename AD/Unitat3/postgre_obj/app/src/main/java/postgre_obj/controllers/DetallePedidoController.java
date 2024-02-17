package postgre_obj.controllers;

import postgre_obj.entities.DetallePedido;
import java.util.List;
import java.util.ArrayList;

public class DetallePedidoController {

    private List<DetallePedido> detallesPedido = new ArrayList<>();

    public List<DetallePedido> getAllDetallesPedido() {
        return detallesPedido;
    }

    public DetallePedido getDetallePedido(int idPedido) {
        for (DetallePedido detallePedido : detallesPedido) {
            if (detallePedido.getIdPedido() == idPedido) {
                return detallePedido;
            }
        }
        return null;
    }

    public void addDetallePedido(DetallePedido detallePedido) {
        detallesPedido.add(detallePedido);
    }

    public void updateDetallePedido(DetallePedido detallePedido) {
        for (int i = 0; i < detallesPedido.size(); i++) {
            if (detallesPedido.get(i).getIdPedido() == detallePedido.getIdPedido()) {
                detallesPedido.set(i, detallePedido);
                break;
            }
        }
    }

    public void deleteDetallePedido(int idPedido) {
        detallesPedido.removeIf(detallePedido -> detallePedido.getIdPedido() == idPedido);
    }
}
