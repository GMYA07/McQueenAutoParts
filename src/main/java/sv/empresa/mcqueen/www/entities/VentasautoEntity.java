package sv.empresa.mcqueen.www.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ventasauto", schema = "mcqueenautoparts", catalog = "")
public class VentasautoEntity {
    @Id
    @Column(name = "idVenta", nullable = false, length = 6)
    private String idVenta;
    @Basic
    @Column(name = "estado", nullable = false)
    private int estado;
    @Basic
    @Column(name = "precio", nullable = false)
    private int precio;
    @Basic
    @Column(name = "mensajeVenta", nullable = true, length = 100)
    private String mensajeVenta;
    @ManyToOne
    @JoinColumn(name = "idCarro", referencedColumnName = "idAutomovil", nullable = false)
    private AutomovilesEntity automovilesByIdCarro;
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "dui", nullable = false)
    private UsuarioEntity usuarioByIdCliente;

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getMensajeVenta() {
        return mensajeVenta;
    }

    public void setMensajeVenta(String mensajeVenta) {
        this.mensajeVenta = mensajeVenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VentasautoEntity that = (VentasautoEntity) o;

        if (estado != that.estado) return false;
        if (precio != that.precio) return false;
        if (idVenta != null ? !idVenta.equals(that.idVenta) : that.idVenta != null) return false;
        if (mensajeVenta != null ? !mensajeVenta.equals(that.mensajeVenta) : that.mensajeVenta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVenta != null ? idVenta.hashCode() : 0;
        result = 31 * result + estado;
        result = 31 * result + precio;
        result = 31 * result + (mensajeVenta != null ? mensajeVenta.hashCode() : 0);
        return result;
    }

    public AutomovilesEntity getAutomovilesByIdCarro() {
        return automovilesByIdCarro;
    }

    public void setAutomovilesByIdCarro(AutomovilesEntity automovilesByIdCarro) {
        this.automovilesByIdCarro = automovilesByIdCarro;
    }

    public UsuarioEntity getUsuarioByIdCliente() {
        return usuarioByIdCliente;
    }

    public void setUsuarioByIdCliente(UsuarioEntity usuarioByIdCliente) {
        this.usuarioByIdCliente = usuarioByIdCliente;
    }
}
