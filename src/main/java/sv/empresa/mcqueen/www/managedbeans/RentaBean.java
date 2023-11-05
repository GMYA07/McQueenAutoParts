package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.entities.RentasEntity;
import sv.empresa.mcqueen.www.models.RentasModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;

import java.util.List;

@ManagedBean
@RequestScoped
public class RentaBean {
    private RentasEntity rentaAuto;
    private AutomovilesEntity autoRentar = new AutomovilesEntity();
    private RentasModel modeloRenta = new RentasModel();
    private List<RentasEntity> listaRentas;
    public RentaBean(){rentaAuto = new RentasEntity();}
    public String guardarRenta(AutomovilesEntity auto){
        autoRentar = auto;
        if (autoRentar == null){
            return "indexCliente";
        }else {
            return "FAlquiler";
        }

    }

    //GETTER AND SETTER

    public List<RentasEntity> getListaRentas() {
        return modeloRenta.listarRentas();
    }

    public RentasEntity getRentaAuto() {
        return rentaAuto;
    }

    public void setRentaAuto(RentasEntity rentaAuto) {
        this.rentaAuto = rentaAuto;
    }

    public AutomovilesEntity getAutoRentar() {
        return autoRentar;
    }

    public void setAutoRentar(AutomovilesEntity autoRentar) {
        this.autoRentar = autoRentar;
    }
}
