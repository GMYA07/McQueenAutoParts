package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import sv.empresa.mcqueen.www.entities.CitasmecEntity;
import sv.empresa.mcqueen.www.entities.MecanicosEntity;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import sv.empresa.mcqueen.www.models.CitasmecModel;
import sv.empresa.mcqueen.www.models.MecanicosModel;
import sv.empresa.mcqueen.www.models.UsuariosModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;

import java.util.List;

@ManagedBean
@RequestScoped
public class CitasMecBean {
    private CitasmecEntity citaCliente;
    private String idMecanico;
    private String duiCliente;
    private CitasmecModel modeloCita = new CitasmecModel();
    private MecanicosModel modeloMecanico = new MecanicosModel();
    private UsuariosModel modeloUsuario = new UsuariosModel();
    private List<CitasmecEntity> listaCitas;
    private List<CitasmecEntity> citasMeca;
    public CitasMecBean(){citaCliente = new CitasmecEntity();}
    public void registrarCita(){
        MecanicosEntity mecanico = modeloMecanico.obtenerMecanico(idMecanico);
        UsuarioEntity cliente = modeloUsuario.obtenerUsuario(duiCliente);
        citaCliente.setUsuarioByIdCliente(cliente);
        citaCliente.setMecanicosByIdMecanico(mecanico);
        citaCliente.setEstadoCita(51);
        if (modeloCita.insertarCita(citaCliente) == 1){
            mecanico.setEstado(1);
            if (modeloMecanico.modificarMecanico(mecanico) == 1){
                FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cita Ingresada", "Registrado"));
            }else {
                JsfUtil.setErrorMessage(null, "No se pudo ingresar la cita");
            }

        }else {
            JsfUtil.setErrorMessage(null, "No se pudo registrar la cita");
        }
    }
    public void finalizarCita(CitasmecEntity modiCita){
        modiCita.setEstadoCita(52);
        if (modeloCita.modificarCita(modiCita) == 1){
            MecanicosEntity meca = modeloMecanico.obtenerMecanico(modiCita.getMecanicosByIdMecanico().getDui());
            meca.setEstado(0);
            if (modeloMecanico.modificarMecanico(meca) == 1){
                FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cita Finalizadada", "Registrado"));
            }else {
                JsfUtil.setErrorMessage(null, "No se pudo finalizar la cita");
            }
        }

    }
    //GETTER AND SETTER
    public List<CitasmecEntity> getListaCitas(int estadoCita) {
        return modeloCita.listarRentas(estadoCita);
    }

    public List<CitasmecEntity> getCitasMeca(String duiMeca) {
        return modeloCita.citasMecanico(duiMeca);
    }

    public CitasmecEntity getCitaCliente() {
        return citaCliente;
    }

    public void setCitaCliente(CitasmecEntity citaCliente) {
        this.citaCliente = citaCliente;
    }

    public String getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(String idMecanico) {
        this.idMecanico = idMecanico;
    }

    public String getDuiCliente() {
        return duiCliente;
    }

    public void setDuiCliente(String duiCliente) {
        this.duiCliente = duiCliente;
    }
}
