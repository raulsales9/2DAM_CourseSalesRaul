package postgre_obj.controllers;

import postgre_obj.entities.Pedido;
import java.util.List;
import java.util.ArrayList;

public class PedidoController {

    private List<Pedido> pedidos = new ArrayList<>();

    public List<Pedido> getAllPedidos() {
        return pedidos;
    }

    public Pedido getPedido(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void updatePedido(Pedido pedido) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == pedido.getId()) {
                pedidos.set(i, pedido);
                break;
            }
        }
    }

    public void deletePedido(int id) {
        pedidos.removeIf(pedido -> pedido.getId() == id);
    }
}
