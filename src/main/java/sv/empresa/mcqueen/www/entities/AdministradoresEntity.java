package sv.empresa.mcqueen.www.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "administradores", schema = "mcqueenautoparts", catalog = "")
public class AdministradoresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAdmin", nullable = false)
    private int idAdmin;
    @Basic
    @Column(name = "usuario", nullable = false, length = 25)
    private String usuario;
    @Basic
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;
    @Basic
    @Column(name = "pass", nullable = false, length = 25)
    private String pass;

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

        AdministradoresEntity that = (AdministradoresEntity) o;

        if (idAdmin != that.idAdmin) return false;
        if (usuario != null ? !usuario.equals(that.usuario) : that.usuario != null) return false;
        if (correo != null ? !correo.equals(that.correo) : that.correo != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAdmin;
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (correo != null ? correo.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        return result;
    }
}
