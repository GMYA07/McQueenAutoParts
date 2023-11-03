package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import sv.empresa.mcqueen.www.models.MecanicosModel;
import sv.empresa.mcqueen.www.entities.MecanicosEntity;
import sv.empresa.mcqueen.www.utils.JsfUtil;

import java.util.List;

@ManagedBean
@RequestScoped
public class MecanicoBean {
    private MecanicosEntity mecanico;
    private MecanicosModel modeloMecanico = new MecanicosModel();
    List<MecanicosEntity> listaMecanicos;

    public MecanicoBean(){ mecanico = new MecanicosEntity();}

    public String registrarMecanico(){

        mecanico.setEstado(0);

        if (modeloMecanico.verificarDuiMecanico(mecanico.getDui()) != 1){
            if (modeloMecanico.verificarCorreoExistMecanico(mecanico.getCorreo()) != 1){
                if (modeloMecanico.insertarMecanico(mecanico) != 1){
                    JsfUtil.setErrorMessage("","Error: No se inserto el Mecanico");
                    return "registroMecanico";
                }else {
                    return "crudMecanicos"; // se inserto el usuario exitosamente
                }
            }else {
                JsfUtil.setErrorMessage("","Error: el correo ya existe en la plataforma");
                return "registroMecanico";
            }
        }else {
            JsfUtil.setErrorMessage("","Error: el dui ya existe en la plataforma");
            return "registroMecanico";
        }
    }
    public void modificarMecanico(){
        if (modeloMecanico.modificarMecanico(mecanico) == 1){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro el Mecanico Exitosamente", "Ingresado"));
        }else {
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No se pudo ingresar el mecanico", "No registrado"));
        }
    }
    public void eliminarMecanico(String duiMeca){
        if (modeloMecanico.eliminarMecanico(duiMeca) == 1){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se registro el Mecanico Exitosamente", "Ingresado"));
        }else {
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No se pudo ingresar el mecanico", "No registrado"));
        }
    }

    public void settearMecanicoForm(String duiMeca){
        mecanico = modeloMecanico.obtenerMecanico(duiMeca);
    }

    //GETTER Y SETTER

    public MecanicosEntity getMecanico() {
        return mecanico;
    }

    public void setMecanico(MecanicosEntity mecanico) {
        this.mecanico = mecanico;
    }

    public List<MecanicosEntity> getListaMecanicos() {
        return modeloMecanico.listarMecanicos();
    }
}
