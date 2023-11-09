package sv.empresa.mcqueen.www.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ventasrepuestos", schema = "mcqueenautoparts", catalog = "")
public class VentasrepuestosEntity {
    @Id
    @Column(name = "idVentaRepuesto", nullable = false, length = 6)
    private String idVentaRepuesto;
    @Basic
    @Column(name = "precioVentaRep", nullable = false)
    private int precioVentaRep;
    @Basic
    @Column(name = "estadoVenta", nullable = false)
    private int estadoVenta;
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "dui", nullable = false)
    private UsuarioEntity usuarioByIdCliente;

    public String getIdVentaRepuesto() {
        return idVentaRepuesto;
    }

    public void setIdVentaRepuesto(String idVentaRepuesto) {
        this.idVentaRepuesto = idVentaRepuesto;
    }

    public int getPrecioVentaRep() {
        return precioVentaRep;
    }

    public void setPrecioVentaRep(int precioVentaRep) {
        this.precioVentaRep = precioVentaRep;
    }

    public int getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(int estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VentasrepuestosEntity that = (VentasrepuestosEntity) o;

        if (precioVentaRep != that.precioVentaRep) return false;
        if (estadoVenta != that.estadoVenta) return false;
        if (idVentaRepuesto != null ? !idVentaRepuesto.equals(that.idVentaRepuesto) : that.idVentaRepuesto != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVentaRepuesto != null ? idVentaRepuesto.hashCode() : 0;
        result = 31 * result + precioVentaRep;
        result = 31 * result + estadoVenta;
        return result;
    }

    public UsuarioEntity getUsuarioByIdCliente() {
        return usuarioByIdCliente;
    }

    public void setUsuarioByIdCliente(UsuarioEntity usuarioByIdCliente) {
        this.usuarioByIdCliente = usuarioByIdCliente;
    }
}
