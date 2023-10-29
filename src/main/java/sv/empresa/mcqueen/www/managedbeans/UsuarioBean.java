package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.empresa.mcqueen.www.models.UsuariosModel;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import sv.empresa.mcqueen.www.utils.JsfUtil;

import java.util.List;

@ManagedBean
@RequestScoped
public class UsuarioBean {

    public UsuarioEntity usuario ;
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
