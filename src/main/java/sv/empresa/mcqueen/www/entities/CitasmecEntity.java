package sv.empresa.mcqueen.www.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "citasmec", schema = "mcqueenautoparts", catalog = "")
public class CitasmecEntity {
    @Id
    @Column(name = "idCita", nullable = false)
    private int idCita;
    @Basic
    @Column(name = "descripcionCita", nullable = false, length = 100)
    private String descripcionCita;
    @Basic
    @Column(name = "estadoCita", nullable = false)
    private int estadoCita;
    @ManyToOne
    @JoinColumn(name = "idMecanico", referencedColumnName = "dui", nullable = false)
    private MecanicosEntity mecanicosByIdMecanico;
    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "dui", nullable = false)
    private UsuarioEntity usuarioByIdCliente;

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getDescripcionCita() {
        return descripcionCita;
    }

    public void setDescripcionCita(String descripcionCita) {
        this.descripcionCita = descripcionCita;
    }

    public int getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(int estadoCita) {
        this.estadoCita = estadoCita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitasmecEntity that = (CitasmecEntity) o;

        if (idCita != that.idCita) return false;
        if (estadoCita != that.estadoCita) return false;
        if (descripcionCita != null ? !descripcionCita.equals(that.descripcionCita) : that.descripcionCita != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCita;
        result = 31 * result + (descripcionCita != null ? descripcionCita.hashCode() : 0);
        result = 31 * result + estadoCita;
        return result;
    }

    public MecanicosEntity getMecanicosByIdMecanico() {
        return mecanicosByIdMecanico;
    }

    public void setMecanicosByIdMecanico(MecanicosEntity mecanicosByIdMecanico) {
        this.mecanicosByIdMecanico = mecanicosByIdMecanico;
    }

    public UsuarioEntity getUsuarioByIdCliente() {
        return usuarioByIdCliente;
    }

    public void setUsuarioByIdCliente(UsuarioEntity usuarioByIdCliente) {
        this.usuarioByIdCliente = usuarioByIdCliente;
    }
}
