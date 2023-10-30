package sv.empresa.mcqueen.www.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "repuestos", schema = "mcqueenautoparts")
public class RepuestosEntity {
    @Id
    @Column(name = "idRepuesto", nullable = false, length = 6)
    private String idRepuesto;
    @Basic
    @Column(name = "categorias", nullable = false, length = 30)
    private String categorias;
    @Basic
    @Column(name = "imagenRepuesto", nullable = false, length = 50)
    private String imagenRepuesto;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "precio", nullable = false, precision = 0)
    private double precio;
    @Basic
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Basic
    @Column(name = "marca", nullable = false, length = 15)
    private String marca;

    public String getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(String idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public String getImagenRepuesto() {
        return imagenRepuesto;
    }

    public void setImagenRepuesto(String imagenRepuesto) {
        this.imagenRepuesto = imagenRepuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepuestosEntity that = (RepuestosEntity) o;

        if (Double.compare(precio, that.precio) != 0) return false;
        if (cantidad != that.cantidad) return false;
        if (idRepuesto != null ? !idRepuesto.equals(that.idRepuesto) : that.idRepuesto != null) return false;
        if (categorias != null ? !categorias.equals(that.categorias) : that.categorias != null) return false;
        if (imagenRepuesto != null ? !imagenRepuesto.equals(that.imagenRepuesto) : that.imagenRepuesto != null)
            return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (marca != null ? !marca.equals(that.marca) : that.marca != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idRepuesto != null ? idRepuesto.hashCode() : 0;
        result = 31 * result + (categorias != null ? categorias.hashCode() : 0);
        result = 31 * result + (imagenRepuesto != null ? imagenRepuesto.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        temp = Double.doubleToLongBits(precio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + cantidad;
        result = 31 * result + (marca != null ? marca.hashCode() : 0);
        return result;
    }
}
