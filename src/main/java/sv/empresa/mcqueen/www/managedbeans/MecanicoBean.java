package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.empresa.mcqueen.www.models.MecanicosModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;
import sv.empresa.mcqueen.www.entities.MecanicosEntity;

import java.util.List;

@ManagedBean
@RequestScoped
public class MecanicoBean {
    public MecanicosEntity mecanico;
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
