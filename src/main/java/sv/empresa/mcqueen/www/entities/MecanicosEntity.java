package sv.empresa.mcqueen.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "mecanicos", schema = "mcqueenautoparts", catalog = "")
public class MecanicosEntity {
    @Id
    @Column(name = "dui", nullable = false, length = 10)
    private String dui;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;
    @Basic
    @Column(name = "pass", nullable = false, length = 25)
    private String pass;
    @Basic
    @Column(name = "estado", nullable = false)
    private int estado;
    @OneToMany(mappedBy = "mecanicosByIdMecanico")
    private Collection<CitasmecEntity> citasmecsByDui;

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

        MecanicosEntity that = (MecanicosEntity) o;

        if (estado != that.estado) return false;
        if (dui != null ? !dui.equals(that.dui) : that.dui != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (correo != null ? !correo.equals(that.correo) : that.correo != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dui != null ? dui.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + estado;
        return result;
    }

    public Collection<CitasmecEntity> getCitasmecsByDui() {
        return citasmecsByDui;
    }

    public void setCitasmecsByDui(Collection<CitasmecEntity> citasmecsByDui) {
        this.citasmecsByDui = citasmecsByDui;
    }
}
