package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import sv.empresa.mcqueen.www.models.UsuariosModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import java.util.List;

@ManagedBean
@RequestScoped
public class UsuarioBean {

    private UsuarioEntity usuario ;
    private UsuariosModel modeloUsuario = new UsuariosModel();
    List<UsuarioEntity> listaUsuarios;
    public UsuarioBean(){usuario = new UsuarioEntity();}

    public String registarUsuario(){

        if(modeloUsuario.verificarDui(usuario.getDui()) != 1){
            if (modeloUsuario.verificarCorreoExist(usuario.getCorreo()) != 1){
                if (modeloUsuario.insertarUsuario(usuario) != 1){
                    JsfUtil.setErrorMessage("","Error: No se inserto el usuario");
                    return "Registro";
                }else {
                    return "InicioSesion"; // se inserto el usuario exitosamente
                }
            }else {
                JsfUtil.setErrorMessage("","Error: el correo ya existe en la plataforma");
                return "Registro";
            }
        }else {
            JsfUtil.setErrorMessage("","Error: el dui ya existe en la plataforma");
            return "Registro";
        }

    }

    public void modificarUser(){
        if (modeloUsuario.modificarUsuario(usuario) == 1){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modifico el Usuario Exitosamente", "Ingresado"));
        }else {
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No se pudo modificar el Usuario", "No Modificado"));
        }
    }
    public void eliminarUser(String duiUser){
        if (modeloUsuario.eliminarUsuario(duiUser) == 1){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino el Usuario Exitosamente", "Ingresado"));
        }else {
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No se pudo eliminar el Usuario", "No Modificado"));
        }
    }

    public void setteFormUser(String duiUser){
        usuario = modeloUsuario.obtenerUsuario(duiUser);
    }


    //GETTER AND SETTER
    public List<UsuarioEntity> getListaUsuarios() {
        return modeloUsuario.listarUsuarios();
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
