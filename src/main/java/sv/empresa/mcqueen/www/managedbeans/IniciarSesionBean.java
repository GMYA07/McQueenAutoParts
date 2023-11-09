package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import sv.empresa.mcqueen.www.models.AdministradorModel;
import sv.empresa.mcqueen.www.models.EmpleadosModel;
import sv.empresa.mcqueen.www.models.MecanicosModel;
import sv.empresa.mcqueen.www.models.UsuariosModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;

@ManagedBean
@RequestScoped
public class IniciarSesionBean {

    public  IniciarSesionBean (){}

    private String correoUsuario;
    private String passUsuario;

    //Modelos para poder buscar al usuario
    AdministradorModel modeloAdministrador = new AdministradorModel();
    UsuariosModel modeloUsuario = new UsuariosModel();
    MecanicosModel modeloMecanico = new MecanicosModel();
    EmpleadosModel modeloEmpleado = new EmpleadosModel();

    //Metodos para Iniciar Sesion

    public String iniciarSesion(){

        if (modeloAdministrador.iniciarSesionAdmin(correoUsuario,passUsuario) != 0 ){
            // Obtener el contexto de JSF actual el cual es el que nos posibilita acceso a la funcionalidad y la información específica de JSF en el entorno de una solicitud web.
            FacesContext context = FacesContext.getCurrentInstance();

            // Obtener el contexto externo que proporciona acceso a los objetos subyacentes de Servlet, en otras palabra obtenemos funcionalidades de servlets
            ExternalContext externalContext = context.getExternalContext();

            // Obtener la sesión actual del usuario; si no existe una sesión, devuelve null (false)
            HttpSession session = (HttpSession) externalContext.getSession(false);
            session.setAttribute("idUsuario", modeloAdministrador.iniciarSesionAdmin(correoUsuario, passUsuario));

            return "vistasAdministrador/indexAdministrador";

        } else if (!modeloUsuario.iniciarSesionUsuario(correoUsuario,passUsuario).equals("")) {
            // Obtener el contexto de JSF actual el cual es el que nos posibilita acceso a la funcionalidad y la información específica de JSF en el entorno de una solicitud web.
            FacesContext context = FacesContext.getCurrentInstance();

            // Obtener el contexto externo que proporciona acceso a los objetos subyacentes de Servlet, en otras palabra obtenemos funcionalidades de servlets
            ExternalContext externalContext = context.getExternalContext();

            // Obtener la sesión actual del usuario; si no existe una sesión, devuelve null (false)
            HttpSession session = (HttpSession) externalContext.getSession(false);
            session.setAttribute("idUsuario", modeloUsuario.iniciarSesionUsuario(correoUsuario, passUsuario));

            return "vistasCliente/indexCliente";

        }else if (!modeloEmpleado.iniciarSesionEmpleado(correoUsuario,passUsuario).equals("")) {
            // Obtener el contexto de JSF actual el cual es el que nos posibilita acceso a la funcionalidad y la información específica de JSF en el entorno de una solicitud web.
            FacesContext context = FacesContext.getCurrentInstance();

            // Obtener el contexto externo que proporciona acceso a los objetos subyacentes de Servlet, en otras palabra obtenemos funcionalidades de servlets
            ExternalContext externalContext = context.getExternalContext();

            // Obtener la sesión actual del usuario; si no existe una sesión, devuelve null (false)
            HttpSession session = (HttpSession) externalContext.getSession(false);
            session.setAttribute("idUsuario", modeloEmpleado.iniciarSesionEmpleado(correoUsuario, passUsuario));

            return "vistasEmpleado/IndexEmpleado";

        }else if (!modeloMecanico.iniciarSesionMecanico(correoUsuario,passUsuario).equals("")) {
            // Obtener el contexto de JSF actual el cual es el que nos posibilita acceso a la funcionalidad y la información específica de JSF en el entorno de una solicitud web.
            FacesContext context = FacesContext.getCurrentInstance();

            // Obtener el contexto externo que proporciona acceso a los objetos subyacentes de Servlet, en otras palabra obtenemos funcionalidades de servlets
            ExternalContext externalContext = context.getExternalContext();

            // Obtener la sesión actual del usuario; si no existe una sesión, devuelve null (false)
            HttpSession session = (HttpSession) externalContext.getSession(false);
            session.setAttribute("idUsuario", modeloMecanico.iniciarSesionMecanico(correoUsuario, passUsuario));

            return "vistasMecanico/indexMecanico";
        }else {
            JsfUtil.setErrorMessage("","Error no se pudo iniciar Sesion");
            return "InicioSesion";
        }
    }

    public String cerrarSesion(HttpSession sessionS){
        // Obtener el contexto de JSF actual el cual es el que nos posibilita acceso a la funcionalidad y la información específica de JSF en el entorno de una solicitud web.
        FacesContext context = FacesContext.getCurrentInstance();
        // Obtener el contexto externo que proporciona acceso a los objetos subyacentes de Servlet, en otras palabra obtenemos funcionalidades de servlets
        ExternalContext externalContext = context.getExternalContext();
        // Obtener la sesión actual del usuario; si no existe una sesión, devuelve devuelve algo (true)
        sessionS= (HttpSession) externalContext.getSession(true);
        sessionS.setAttribute("idUsuario","");
        return "/index";
    }



    //GETTER Y SETTER
    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }
}
