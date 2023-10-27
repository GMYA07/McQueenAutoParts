package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import sv.empresa.mcqueen.www.models.UsuariosModel;

import java.util.List;

@ManagedBean
@RequestScoped
public class UsuarioBean {

    public UsuarioEntity usuario ;
    private UsuariosModel modeloUsuario = new UsuariosModel();
    List<UsuarioEntity> listaUsuarios;
    public UsuarioBean(){usuario = new UsuarioEntity();}

    public String registarUsuario(){

        if (modeloUsuario.verificarCorreoExist(usuario.getCorreo()) == 0){
            return "InicioSesion";
        }else {
            return "index";
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
