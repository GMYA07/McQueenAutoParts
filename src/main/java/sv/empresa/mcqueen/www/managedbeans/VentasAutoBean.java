package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.entities.VentasautoEntity;
import sv.empresa.mcqueen.www.models.AutomovilesModel;
import sv.empresa.mcqueen.www.models.UsuariosModel;
import sv.empresa.mcqueen.www.models.VentasAutoModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;

import java.util.List;
import java.util.Random;

@ManagedBean
@RequestScoped
public class VentasAutoBean {
    private VentasautoEntity ventaAuto;
    private AutomovilesEntity automovil;
    private VentasAutoModel modeloVenta = new VentasAutoModel();
    private AutomovilesModel modeloAutomovil = new AutomovilesModel();
    private UsuariosModel modeloUsuario = new UsuariosModel();

    private List<VentasautoEntity> listaVentas;

    public VentasAutoBean(){ventaAuto = new VentasautoEntity(); automovil = new AutomovilesEntity();}

    public void registrarVentaUsuario(){
        //settearemos los demas campos que requiere la venta
        ventaAuto.setUsuarioByIdCliente(modeloUsuario.obtenerUsuario(JsfUtil.getRequest().getParameter("idComprador")));
        ventaAuto.setAutomovilesByIdCarro(modeloAutomovil.obtenerAutomovil(JsfUtil.getRequest().getParameter("idAuto")));
        ventaAuto.setEstado(12);
        ventaAuto.setPrecio(123);
        //crearemos el id para la venta
        crearIDVentaAuto(1);
        //registraremos la venta
        if (modeloVenta.registrarVentaAutomovilUsuario(ventaAuto) == 1){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingresado la venta Exitosamente", "Registrado"));
        }else {
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al enviar la venta ", "Error"));
        }
    }

    public void crearIDVentaAuto(int tipo){
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(900) + 100;
        if (tipo == 1){
            ventaAuto.setIdVenta("VTU"+numeroAleatorio);
        }else {
            ventaAuto.setIdVenta("VTA"+numeroAleatorio);
        }
    }

    public void settearFormVentas(String idAuto){
        automovil = modeloAutomovil.obtenerAutomovil(idAuto);
    }


    //GETTER Y SETTER
    public List<VentasautoEntity> getListaVentas() {
        return modeloVenta.listarVentas();
    }

    public VentasautoEntity getVentaAuto() {
        return ventaAuto;
    }

    public void setVentaAuto(VentasautoEntity ventaAuto) {
        this.ventaAuto = ventaAuto;
    }

    public AutomovilesEntity getAutomovil() {
        return automovil;
    }

    public void setAutomovil(AutomovilesEntity automovil) {
        this.automovil = automovil;
    }
}
