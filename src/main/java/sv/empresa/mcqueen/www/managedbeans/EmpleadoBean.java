package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import sv.empresa.mcqueen.www.models.EmpleadosModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;
import sv.empresa.mcqueen.www.entities.EmpleadosEntity;

import java.util.List;

@ManagedBean
@RequestScoped
public class EmpleadoBean {
    private EmpleadosEntity empleado;
    private EmpleadosModel modeloEmpleado = new EmpleadosModel();
    List<EmpleadosEntity> listaEmpleados;

    public EmpleadoBean(){ empleado = new EmpleadosEntity();}

    public String registrarEmpleado(){
        if (modeloEmpleado.verificarDuiMecanicoEmpleado(empleado.getDui())!= 1){
            if (modeloEmpleado.verificarCorreoExistEmpleado(empleado.getCorreo()) != 1){
                if (modeloEmpleado.insertarEmpleado(empleado)!= 1){
                    JsfUtil.setErrorMessage("","Error: No se inserto el Empleado");
                    return "registroEmpleado";
                }else {
                    return "crudEmpleados"; // se inserto el usuario exitosamente
                }
            }else {
                JsfUtil.setErrorMessage("","Error: el correo ya existe en la plataforma");
                return "registroEmpleado";
            }
        }else {
            JsfUtil.setErrorMessage("","Error: el dui ya existe en la plataforma");
            return "registroEmpleado";
        }
    }
    public void modificarEmpleado(){
        if (modeloEmpleado.modificarEmpleado(empleado) == 1){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se modifico el Empleado Exitosamente", "Ingresado"));
        }else {
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No se pudo modificar el Empleado", "No Modificado"));
        }
    }
    public void eliminarEmpleado(String duiE){
        if (modeloEmpleado.eliminarEmpleado(duiE) == 1){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino el Empleado Exitosamente", "Ingresado"));
        }else {
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No se pudo eliminar el Empleado", "No Modificado"));
        }
    }
    public void settearFormEmple(String duiE){
        empleado = modeloEmpleado.obtenerEmpleado(duiE);
    }

    //GETTER AND SETTER
    public List<EmpleadosEntity> getListaEmpleados() {
        return modeloEmpleado.listarEmpleados();
    }

    public EmpleadosEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadosEntity empleado) {
        this.empleado = empleado;
    }
}
