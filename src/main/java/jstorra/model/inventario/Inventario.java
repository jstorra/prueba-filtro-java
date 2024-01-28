package jstorra.model.inventario;

public class Inventario {
    private long idInventario;
    private long idProducto;
    private int cantidad;

    public long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(long idInventario) {
        this.idInventario = idInventario;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "idInventario=" + idInventario +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                '}';
    }
}
