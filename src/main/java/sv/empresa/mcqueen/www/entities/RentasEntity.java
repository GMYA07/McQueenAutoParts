package sv.empresa.mcqueen.www.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rentas", schema = "mcqueenautoparts", catalog = "")
public class RentasEntity {
    @Id
    @Column(name = "idRenta", nullable = false, length = 6)
    private String idRenta;
    @Basic
    @Column(name = "FechaInicio", nullable = false, length = 50)
    private String fechaInicio;
    @Basic
    @Column(name = "FechaFinal", nullable = false, length = 50)
    private String fechaFinal;
    @Basic
    @Column(name = "precioRenta", nullable = false)
    private int precioRenta;
    @Basic
    @Column(name = "estado", nullable = false)
    private int estado;
    @ManyToOne
    @JoinColumn(name = "idCarro", referencedColumnName = "idAutomovil", nullable = false)
    private AutomovilesEntity automovilesByIdCarro;
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "dui", nullable = false)
    private UsuarioEntity usuarioByIdCliente;

    public String getIdRenta() {
        return idRenta;
    }

    public void setIdRenta(String idRenta) {
        this.idRenta = idRenta;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getPrecioRenta() {
        return precioRenta;
    }

    public void setPrecioRenta(int precioRenta) {
        this.precioRenta = precioRenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentasEntity that = (RentasEntity) o;

        if (precioRenta != that.precioRenta) return false;
        if (estado != that.estado) return false;
        if (idRenta != null ? !idRenta.equals(that.idRenta) : that.idRenta != null) return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;
        if (fechaFinal != null ? !fechaFinal.equals(that.fechaFinal) : that.fechaFinal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRenta != null ? idRenta.hashCode() : 0;
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaFinal != null ? fechaFinal.hashCode() : 0);
        result = 31 * result + precioRenta;
        result = 31 * result + estado;
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
