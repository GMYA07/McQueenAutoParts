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
    private String codigoVentaMoment;
    private AutomovilesEntity automovil;
    private VentasAutoModel modeloVenta = new VentasAutoModel();
    private AutomovilesModel modeloAutomovil = new AutomovilesModel();
    private UsuariosModel modeloUsuario = new UsuariosModel();

    private List<VentasautoEntity> listaVentas;
    private List<VentasautoEntity> listaVentaAutoAgencia;
    private List<VentasautoEntity> listaMensajesVentas;
    private List<VentasautoEntity> listaTodasVentasAutosAgencias;

    public VentasAutoBean(){ventaAuto = new VentasautoEntity(); automovil = new AutomovilesEntity();}

    public void registrarVentaUsuario(){
        //settearemos los demas campos que requiere la venta
        ventaAuto.setUsuarioByIdCliente(modeloUsuario.obtenerUsuario(JsfUtil.getRequest().getParameter("idComprador")));
        ventaAuto.setAutomovilesByIdCarro(modeloAutomovil.obtenerAutomovil(JsfUtil.getRequest().getParameter("idAuto")));
        ventaAuto.setEstado(11);
        ventaAuto.setPrecio((int)Float.parseFloat(JsfUtil.getRequest().getParameter("precioVenta")));
        //crearemos el id para la venta
        crearIDVentaAuto(1);
        //registraremos la venta de usuario
        if (modeloVenta.registrarVentaAutomovilUsuario(ventaAuto) == 1){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingresado la venta Exitosamente", "Registrado"));
        }else {
            JsfUtil.setErrorMessage(null, "No se pudo registrar la venta");
        }
    }
    public void registrarVentaAutoAgencia(){
        //settearemos los demas campos que requiere la venta
        ventaAuto.setUsuarioByIdCliente(modeloUsuario.obtenerUsuario(JsfUtil.getRequest().getParameter("idComprador")));
        ventaAuto.setAutomovilesByIdCarro(modeloAutomovil.obtenerAutomovil(JsfUtil.getRequest().getParameter("idAuto")));
        ventaAuto.setEstado(0);
        ventaAuto.setMensajeVenta(crearCodigoCanje());
        ventaAuto.setPrecio((int)Float.parseFloat(JsfUtil.getRequest().getParameter("precioVenta")));
        //crearemos el id para la venta
        crearIDVentaAuto(2);
        //registramos la venta de agencia
        if (modeloVenta.registrarVentaAutomovilAgencia(ventaAuto) == 1){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingresado la venta Exitosamente", "Registrado"));
        }else {
            JsfUtil.setErrorMessage(null, "No se pudo registrar la venta");
        }
    }

    public void canjearCodigoVenta(){

        if (codigoVentaMoment.equals(ventaAuto.getMensajeVenta())){
            ventaAuto.setEstado(1);
            ventaAuto.setUsuarioByIdCliente(modeloUsuario.obtenerUsuario(JsfUtil.getRequest().getParameter("idComprador")));
            ventaAuto.setAutomovilesByIdCarro(modeloAutomovil.obtenerAutomovil(JsfUtil.getRequest().getParameter("idAuto")));
           if (modeloVenta.cambiarEstadoVenta(ventaAuto) == 1){
               //Obtenemos el objeto y luego de eso le restamos un automovil puesto q este fue canjeado
               automovil = modeloAutomovil.obtenerAutomovil(JsfUtil.getRequest().getParameter("idAuto"));
               automovil.setCantidad(automovil.getCantidad() - 1);

               if (modeloAutomovil.modificarAutomovil(automovil) == 1){
                   FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Codigo Canjeado Exitosamente", "Registrado"));
               }
           }
        }else {
            JsfUtil.setErrorMessage(null, "No se pudo canjear la comprar");
        }
    }

    public void cambiarEstadoVenta(VentasautoEntity venta){
        String estadoAccion = JsfUtil.getRequest().getParameter("estadoAccion");

        if (venta.getEstado() == 11){ //si el estado esta en 11 que significa q la venta esta en modo de conversacion con el usuario interesado pasara dado caso sea asi pues pasa
            //evaluamos si aceptaremos o rechazaremos
            if (estadoAccion.equals("222")){
                venta.setEstado(12); //si es 12 es por q si c completo la venta
            }else {
                venta.setEstado(13);//si es 13 es por q no c completo la venta
            }
            if (venta.getEstado() == 12){ // aqui verificamos si es una cancelacion de venta por si no llegaron a un acuerdo
                if (modeloVenta.cambiarEstadoVenta(venta) == 1){ // validamos si cambia el estado

                    //Obtenemos el automovil para poder cambiar el estado a vendido para poder sacarlo de publicacin
                    automovil = modeloAutomovil.obtenerAutomovil(venta.getAutomovilesByIdCarro().getIdAutomovil());
                    automovil.setEstado(4);
                    if (modeloAutomovil.cambiarEstadoAutomovil(automovil) == 1){ // validamos que se cambie el estado
                       if (modeloVenta.borrarVentasFallidas(venta.getAutomovilesByIdCarro().getIdAutomovil()) == 1){ // aqui borramos las demas conversaciones de venta que hubieron anteriormente
                           FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Ventao Exitosa", "Venta"));
                       }
                    }
                }

            }else {
                if (modeloVenta.cambiarEstadoVenta(venta) == 1){ //dado caso la venta fue cancelada validamos que se cambie el estado a 13 q es venta fallida
                    FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Ventao Cancelada", "Venta"));

                }
            }
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
    public String crearCodigoCanje(){

        // Definir las letras disponibles
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Crear un objeto Random
        Random random = new Random();

        StringBuilder codigoAleatorio = new StringBuilder();

        // Generar 3 letras aleatorias
        for (int i = 0; i < 3; i++) {
            int indice = random.nextInt(letras.length());
            codigoAleatorio.append(letras.charAt(indice));
        }

        // Generar 3 números aleatorios
        for (int i = 0; i < 3; i++) {
            int numeroAleatorio = random.nextInt(10);
            codigoAleatorio.append(numeroAleatorio);
        }

        return codigoAleatorio.toString();
    }

    public void settearFormVentas(String idAuto){
        automovil = modeloAutomovil.obtenerAutomovil(idAuto);
    }

    public void settearFormCanjeoCod(String idVenta){
        ventaAuto = modeloVenta.obtenerVenta(idVenta);
    }



    //GETTER Y SETTER

    public List<VentasautoEntity> getListaVentas() {
        return modeloVenta.listarVentas();
    }
    public List<VentasautoEntity> getListaMensajesVentas(String dui) {
        return modeloVenta.listaMensajesVentaUser(dui);
    }
    public List<VentasautoEntity> getListaVentaAutoAgencia(String dui) {
        return modeloVenta.listaMensajesVentaAgencia(dui);
    }

    public List<VentasautoEntity> getListaTodasVentasAutosAgencias() {
        return modeloVenta.listaAllVentasAgencia();
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

    public String getCodigoVentaMoment() {
        return codigoVentaMoment;
    }

    public void setCodigoVentaMoment(String codigoVentaMoment) {
        this.codigoVentaMoment = codigoVentaMoment;
    }
}
