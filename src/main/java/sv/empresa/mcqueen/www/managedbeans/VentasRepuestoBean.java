package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import sv.empresa.mcqueen.www.entities.CarritoEntity;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import sv.empresa.mcqueen.www.entities.VentasrepuestosEntity;
import sv.empresa.mcqueen.www.models.CarritoModel;
import sv.empresa.mcqueen.www.models.UsuariosModel;
import sv.empresa.mcqueen.www.models.VentaRepuestoModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;

import java.util.List;
import java.util.Random;

@ManagedBean
@RequestScoped
public class VentasRepuestoBean {
    private VentasrepuestosEntity ventaRepuesto;
    private String idClienteValidarVenta = "";
    private int idVentaTemp = 0;
    private VentaRepuestoModel modeloVentaRepuesto = new VentaRepuestoModel();
    private UsuariosModel modeloUsuario = new UsuariosModel();
    private CarritoModel modeloCarrito = new CarritoModel();
    private List<VentasrepuestosEntity> listaVentasRepuesto;
    private List<VentasrepuestosEntity> listaVentasRepuestoTipo;
    private List<CarritoEntity> listaCarrito;
    public VentasRepuestoBean(){ventaRepuesto = new VentasrepuestosEntity();}
    public void registrarVenta(String duiCliente){
        listaCarrito = modeloCarrito.listarCarrito();
        if (!listaCarrito.isEmpty()){
            //obtenemos el carrito y sumamos para poder saber cuanto sale en total
            int precioFinal = 0;
            listaCarrito = modeloCarrito.listarCarrito();
            for (CarritoEntity producto : listaCarrito){
                if (producto.getCantidad() > 1){
                    precioFinal += ((int) producto.getRepuestosByIdProducto().getPrecio() * producto.getCantidad());
                }else {
                    precioFinal += (int) producto.getRepuestosByIdProducto().getPrecio();
                }
            }

            //settearemos la venta
            UsuarioEntity cliente = modeloUsuario.obtenerUsuario(duiCliente);
            crearIDVentaRep(); //setteamos el id
            ventaRepuesto.setUsuarioByIdCliente(cliente);
            ventaRepuesto.setPrecioVentaRep(precioFinal);
            ventaRepuesto.setEstadoVenta(61);

            if (modeloVentaRepuesto.insertarVentaRep(ventaRepuesto) == 1){
                FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se realizo la venta", "Ingresado"));
                if (modeloCarrito.vaciarCarrito() == 1){
                    FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Carrito vaciado", "Ingresado"));
                }else {
                    JsfUtil.setErrorMessage("","Error: No se vacio el carrito");
                }
            }else {
                JsfUtil.setErrorMessage("","Error: No se pudo hacer la venta");
            }
        }else {
            JsfUtil.setErrorMessage("","Error: No se pudo hacer la venta CARRITO VACIO");
        }
    }
    public void retirarProductos(VentasrepuestosEntity venta){
        int verificarUsers = modeloUsuario.verificarDui(idClienteValidarVenta);

        if (verificarUsers == 0){
            JsfUtil.setErrorMessage("","Error: El dui proporcionado es erroneo");
        }else {
            ventaRepuesto = venta;
            ventaRepuesto.setEstadoVenta(62);
            if (modeloVentaRepuesto.modificarVentaRep(ventaRepuesto) == 1){
                FacesContext.getCurrentInstance().addMessage("successMessage2", new FacesMessage(FacesMessage.SEVERITY_INFO, "Productos Retirados", "Retirado"));
            }else {
                JsfUtil.setErrorMessage("","Error: No se Retiro la Venta");
            }
        }
    }
    public void settearVentaTemp(VentasrepuestosEntity venta){
        ventaRepuesto = venta;
    }
    public void crearIDVentaRep(){
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(900) + 100;
        ventaRepuesto.setIdVentaRepuesto("VTR" + numeroAleatorio);
    }
    //GETTER AND SETTER
    public List<VentasrepuestosEntity> getListaVentasRepuesto() {
        return modeloVentaRepuesto.listarVentas();
    }

    public List<VentasrepuestosEntity> getListaVentasRepuestoTipo(int tipo) {
        return modeloVentaRepuesto.listarVentaTipo(tipo);
    }
    public List<CarritoEntity> getListaCarrito() {
        return listaCarrito;
    }

    public VentasrepuestosEntity getVentaRepuesto() {
        return ventaRepuesto;
    }

    public void setVentaRepuesto(VentasrepuestosEntity ventaRepuesto) {
        this.ventaRepuesto = ventaRepuesto;
    }

    public String getIdClienteValidarVenta() {
        return idClienteValidarVenta;
    }

    public void setIdClienteValidarVenta(String idClienteValidarVenta) {
        this.idClienteValidarVenta = idClienteValidarVenta;
    }

    public int getIdVentaTemp() {
        return idVentaTemp;
    }

    public void setIdVentaTemp(int idVentaTemp) {
        this.idVentaTemp = idVentaTemp;
    }
}
