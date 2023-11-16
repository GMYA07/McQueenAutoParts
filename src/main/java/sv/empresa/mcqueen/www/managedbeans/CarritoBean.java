package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import sv.empresa.mcqueen.www.entities.RentasEntity;
import sv.empresa.mcqueen.www.entities.RepuestosEntity;
import sv.empresa.mcqueen.www.models.CarritoModel;
import sv.empresa.mcqueen.www.entities.CarritoEntity;
import sv.empresa.mcqueen.www.models.RepuestosModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;

import java.util.List;


@ManagedBean
@RequestScoped
public class CarritoBean {
    private CarritoEntity carrito;
    private CarritoModel modelCarrito = new CarritoModel();
    private RepuestosModel modeloRepuesto = new RepuestosModel();
    private List<CarritoEntity> listaCarrito;
    public CarritoBean(){carrito = new CarritoEntity();}

    public void addCarrito(RepuestosEntity newItemCarrito){
        if (newItemCarrito.getCantidad() > 0){
            //Verificamos si existe ya un producto en el carrito que es similar para solo aumentar la cantidad q lleva
            boolean exitSoli = false;
            listaCarrito = modelCarrito.listarCarrito();
            for (CarritoEntity productos : listaCarrito) {
                if (productos.getRepuestosByIdProducto().getIdRepuesto().equals(newItemCarrito.getIdRepuesto())){
                    exitSoli = true;
                    break;
                }
            }
            if(exitSoli == false){
                newItemCarrito.setCantidad(newItemCarrito.getCantidad() - 1); //restamos 1 a la cantidad de items de la tabla de repuestos
                if (modeloRepuesto.modificarRepuestos(newItemCarrito) == 1){ //restamos un item de la cantidad que exiten e nla tabla de repuestos
                    carrito.setRepuestosByIdProducto(newItemCarrito);
                    carrito.setCantidad(1);
                    if (modelCarrito.insertarCarrito(carrito) == 1){ //insertamos el item en el carrito
                        FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Inserto al carrito", "Ingresado"));
                    }else {
                        JsfUtil.setErrorMessage("","Error: No se añadio al carrito 2");
                    }
                }else {
                    JsfUtil.setErrorMessage("","Error: No se añadio al carrito 1");
                }
            }else {
                carrito = modelCarrito.obtenerItemCarrito(newItemCarrito.getIdRepuesto());
                carrito.setCantidad(carrito.getCantidad() + 1);
                if (modelCarrito.modificarCarrito(carrito) == 1){// insertamos el item al carrito en el cual ya existe ese item en el carrito
                    newItemCarrito.setCantidad(newItemCarrito.getCantidad() - 1);//restamos 1 a la cantidad de items
                    if (modeloRepuesto.modificarRepuestos(newItemCarrito) == 1){//restamos un item de la cantidad que exiten e nla tabla de repuestos
                        FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Aumento el item al carrito", "Ingresado"));
                    }
                }else {
                    JsfUtil.setErrorMessage("","Error: No se añadio al carrito 3");
                }
            }
        }else {
            JsfUtil.setErrorMessage("","Error: No se añadio al carrito 4");
        }
    }

    public void borrarItemCarrito(CarritoEntity itemCarrito){

        if (itemCarrito.getCantidad() == 1){
            // aqui obtengo el item y lo modifico
            RepuestosEntity repuesto = modeloRepuesto.obtenerRepuesto(itemCarrito.getRepuestosByIdProducto().getIdRepuesto());
            repuesto.setCantidad(repuesto.getCantidad() + 1); //aqui le devolvemos el item q le quitamos

            if (modeloRepuesto.modificarRepuestos(repuesto) == 1){ //Aqui devolvemos la cantidad de items que se le quitaron al carrito
                if (modelCarrito.borrarItemCarrito(itemCarrito.getIdCarrito()) == 1){
                    FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Borro el articulo del carrito", "Ingresado"));
                }
            }

        }else {
            //Se elimina 1 item de carrito
            itemCarrito.setCantidad(itemCarrito.getCantidad() - 1);

            // aqui obtengo el item y lo modifico para agregarle de nuevo el item q se agrego al carrito
            RepuestosEntity repuesto = modeloRepuesto.obtenerRepuesto(itemCarrito.getRepuestosByIdProducto().getIdRepuesto());
            repuesto.setCantidad(repuesto.getCantidad() + 1); //aqui le devolvemos el item q le quitamos

            if (modeloRepuesto.modificarRepuestos(repuesto) == 1){ //Aqui devolvemos la cantidad de items que se le quitaron al carrito
                if (modelCarrito.modificarCarrito(itemCarrito) == 1){
                    FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Borro 1 articulo del carrito", "Ingresado"));
                }
            }

        }
    }

    //GETTER AND SETTER
    public List<CarritoEntity> getListaCarrito() {
        return modelCarrito.listarCarrito();
    }

    public CarritoEntity getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoEntity carrito) {
        this.carrito = carrito;
    }
}
