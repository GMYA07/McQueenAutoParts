package sv.empresa.mcqueen.www.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "usuario", schema = "mcqueenautoparts", catalog = "")
public class UsuarioEntity {
    @Id
    @Column(name = "dui", nullable = false, length = 10)
    private String dui;
    @Basic
    @Column(name = "licencia", nullable = false, length = 10)
    private String licencia;
    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;
    @Basic
    @Column(name = "pass", nullable = false, length = 25)
    private String pass;
    @OneToMany(mappedBy = "usuarioByIdCliente")
    private Collection<CitasmecEntity> citasmecsByDui;
    @OneToMany(mappedBy = "usuarioByIdCliente")
    private Collection<RentasEntity> rentasByDui;

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioEntity usuario = (UsuarioEntity) o;

        if (dui != null ? !dui.equals(usuario.dui) : usuario.dui != null) return false;
        if (licencia != null ? !licencia.equals(usuario.licencia) : usuario.licencia != null) return false;
        if (nombre != null ? !nombre.equals(usuario.nombre) : usuario.nombre != null) return false;
        if (correo != null ? !correo.equals(usuario.correo) : usuario.correo != null) return false;
        if (pass != null ? !pass.equals(usuario.pass) : usuario.pass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dui != null ? dui.hashCode() : 0;
        result = 31 * result + (licencia != null ? licencia.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        return result;
    }

    public Collection<CitasmecEntity> getCitasmecsByDui() {
        return citasmecsByDui;
    }

    public void setCitasmecsByDui(Collection<CitasmecEntity> citasmecsByDui) {
        this.citasmecsByDui = citasmecsByDui;
    }

    public Collection<RentasEntity> getRentasByDui() {
        return rentasByDui;
    }

    public void setRentasByDui(Collection<RentasEntity> rentasByDui) {
        this.rentasByDui = rentasByDui;
    }
}
