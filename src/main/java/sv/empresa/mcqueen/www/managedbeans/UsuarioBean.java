package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import sv.empresa.mcqueen.www.models.UsuariosModel;

import java.util.List;

@ManagedBean
@RequestScoped
public class UsuarioBean {

    UsuarioEntity usuario ;
    UsuariosModel modeloUsuario = new UsuariosModel();
    List<UsuarioEntity> listaUsuarios;
    public UsuarioBean(){usuario = new UsuarioEntity();}


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
