package sv.empresa.mcqueen.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "automoviles", schema = "mcqueenautoparts", catalog = "")
public class AutomovilesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAutomovil", nullable = false, length = 6)
    private String idAutomovil;
    @Basic
    @Column(name = "marcaAutomovil", nullable = false, length = 30)
    private String marcaAutomovil;
    @Basic
    @Column(name = "modeloAutomovil", nullable = false, length = 30)
    private String modeloAutomovil;
    @Basic
    @Column(name = "yearAutomovil", nullable = false)
    private int yearAutomovil;
    @Basic
    @Column(name = "especificaciones", nullable = false, length = 100)
    private String especificaciones;
    @Basic
    @Column(name = "colorAutomovil", nullable = false, length = 30)
    private String colorAutomovil;
    @Basic
    @Column(name = "precioAutomovil", nullable = false, precision = 0)
    private double precioAutomovil;
    @Basic
    @Column(name = "fotoAutomovil", nullable = false, length = 50)
    private String fotoAutomovil;
    @Basic
    @Column(name = "cantidad", nullable = true)
    private Integer cantidad;
    @Basic
    @Column(name = "estado", nullable = true)
    private Integer estado;
    @OneToMany(mappedBy = "automovilesByIdCarro")
    private Collection<RentasEntity> rentasByIdAutomovil;
    @OneToMany(mappedBy = "automovilesByIdCarro")
    private Collection<VentasautoEntity> ventasautosByIdAutomovil;

    public String getIdAutomovil() {
        return idAutomovil;
    }

    public void setIdAutomovil(String idAutomovil) {
        this.idAutomovil = idAutomovil;
    }

    public String getMarcaAutomovil() {
        return marcaAutomovil;
    }

    public void setMarcaAutomovil(String marcaAutomovil) {
        this.marcaAutomovil = marcaAutomovil;
    }

    public String getModeloAutomovil() {
        return modeloAutomovil;
    }

    public void setModeloAutomovil(String modeloAutomovil) {
        this.modeloAutomovil = modeloAutomovil;
    }

    public int getYearAutomovil() {
        return yearAutomovil;
    }

    public void setYearAutomovil(int yearAutomovil) {
        this.yearAutomovil = yearAutomovil;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public String getColorAutomovil() {
        return colorAutomovil;
    }

    public void setColorAutomovil(String colorAutomovil) {
        this.colorAutomovil = colorAutomovil;
    }

    public double getPrecioAutomovil() {
        return precioAutomovil;
    }

    public void setPrecioAutomovil(double precioAutomovil) {
        this.precioAutomovil = precioAutomovil;
    }

    public String getFotoAutomovil() {
        return fotoAutomovil;
    }

    public void setFotoAutomovil(String fotoAutomovil) {
        this.fotoAutomovil = fotoAutomovil;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutomovilesEntity that = (AutomovilesEntity) o;

        if (yearAutomovil != that.yearAutomovil) return false;
        if (Double.compare(precioAutomovil, that.precioAutomovil) != 0) return false;
        if (idAutomovil != null ? !idAutomovil.equals(that.idAutomovil) : that.idAutomovil != null) return false;
        if (marcaAutomovil != null ? !marcaAutomovil.equals(that.marcaAutomovil) : that.marcaAutomovil != null)
            return false;
        if (modeloAutomovil != null ? !modeloAutomovil.equals(that.modeloAutomovil) : that.modeloAutomovil != null)
            return false;
        if (especificaciones != null ? !especificaciones.equals(that.especificaciones) : that.especificaciones != null)
            return false;
        if (colorAutomovil != null ? !colorAutomovil.equals(that.colorAutomovil) : that.colorAutomovil != null)
            return false;
        if (fotoAutomovil != null ? !fotoAutomovil.equals(that.fotoAutomovil) : that.fotoAutomovil != null)
            return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idAutomovil != null ? idAutomovil.hashCode() : 0;
        result = 31 * result + (marcaAutomovil != null ? marcaAutomovil.hashCode() : 0);
        result = 31 * result + (modeloAutomovil != null ? modeloAutomovil.hashCode() : 0);
        result = 31 * result + yearAutomovil;
        result = 31 * result + (especificaciones != null ? especificaciones.hashCode() : 0);
        result = 31 * result + (colorAutomovil != null ? colorAutomovil.hashCode() : 0);
        temp = Double.doubleToLongBits(precioAutomovil);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (fotoAutomovil != null ? fotoAutomovil.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    public Collection<RentasEntity> getRentasByIdAutomovil() {
        return rentasByIdAutomovil;
    }

    public void setRentasByIdAutomovil(Collection<RentasEntity> rentasByIdAutomovil) {
        this.rentasByIdAutomovil = rentasByIdAutomovil;
    }

    public Collection<VentasautoEntity> getVentasautosByIdAutomovil() {
        return ventasautosByIdAutomovil;
    }

    public void setVentasautosByIdAutomovil(Collection<VentasautoEntity> ventasautosByIdAutomovil) {
        this.ventasautosByIdAutomovil = ventasautosByIdAutomovil;
    }
}
