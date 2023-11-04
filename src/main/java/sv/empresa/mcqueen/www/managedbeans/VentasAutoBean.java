package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.entities.VentasautoEntity;
import sv.empresa.mcqueen.www.models.AutomovilesModel;
import sv.empresa.mcqueen.www.models.VentasAutoModel;

import java.util.List;

@ManagedBean
@RequestScoped
public class VentasAutoBean {
    private VentasautoEntity ventaAuto;
    private AutomovilesEntity automovil;
    private VentasAutoModel modeloVenta;
    private AutomovilesModel modeloAutomovil;

    private List<VentasautoEntity> listaVentas;

    public VentasAutoBean(){ventaAuto = new VentasautoEntity();}

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
