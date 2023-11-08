package sv.empresa.mcqueen.www.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito", schema = "mcqueenautoparts", catalog = "")
public class CarritoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCarrito", nullable = false)
    private int idCarrito;
    @Basic
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "idRepuesto", nullable = false)
    private RepuestosEntity repuestosByIdProducto;

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarritoEntity that = (CarritoEntity) o;

        if (idCarrito != that.idCarrito) return false;
        if (cantidad != that.cantidad) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCarrito;
        result = 31 * result + cantidad;
        return result;
    }

    public RepuestosEntity getRepuestosByIdProducto() {
        return repuestosByIdProducto;
    }

    public void setRepuestosByIdProducto(RepuestosEntity repuestosByIdProducto) {
        this.repuestosByIdProducto = repuestosByIdProducto;
    }
}
