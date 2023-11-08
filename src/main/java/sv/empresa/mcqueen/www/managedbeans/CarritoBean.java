package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.empresa.mcqueen.www.entities.RentasEntity;
import sv.empresa.mcqueen.www.entities.RepuestosEntity;
import sv.empresa.mcqueen.www.models.CarritoModel;
import sv.empresa.mcqueen.www.entities.CarritoEntity;

import java.util.List;


@ManagedBean
@RequestScoped
public class CarritoBean {
    private CarritoEntity carrito;
    private CarritoModel modelCarrito = new CarritoModel();
    private List<CarritoEntity> listaCarrito;
    public CarritoBean(){carrito = new CarritoEntity();}

    public void addCarrito(RepuestosEntity newItemCarrito){
        //Verificamos si existe ya un producto en el carrito que es similar para solo aumentar la cantidad q lleva
        boolean exitSoli = false;
        listaCarrito = modelCarrito.listarCarrito();
        for (CarritoEntity productos : listaCarrito) {
            if (productos.getRepuestosByIdProducto().getIdRepuesto().equals(newItemCarrito.getIdRepuesto())){
                exitSoli = true;
                break;
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
