package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.servlet.annotation.MultipartConfig;
import sv.empresa.mcqueen.www.models.AutomovilesModel;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import java.util.List;

@MultipartConfig
@ManagedBean
@RequestScoped
public class AutomovilBean {

    public AutomovilesEntity automovil;
    private AutomovilesModel modeloAutomovil = new AutomovilesModel();
    List<AutomovilesEntity> listaAutomoviles;

    public AutomovilBean(){automovil = new AutomovilesEntity();}

    public String  registrarNuevoAutomovil(){
        return "crudCarrosRenta";
    }

    //GETTER AND SETTER

    public List<AutomovilesEntity> getListaAutomoviles() {
        return modeloAutomovil.listarAutomoviles();
    }


    public AutomovilesEntity getAutomovil() {
        return automovil;
    }

    public void setAutomovil(AutomovilesEntity automovil) {
        this.automovil = automovil;
    }

}
