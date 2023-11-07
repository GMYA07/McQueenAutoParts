package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.entities.RentasEntity;
import sv.empresa.mcqueen.www.models.AutomovilesModel;
import sv.empresa.mcqueen.www.models.RentasModel;
import sv.empresa.mcqueen.www.models.UsuariosModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@ManagedBean
@RequestScoped
public class RentaBean {
    private RentasEntity rentaAuto;
    private String idAuto;
    private String duiUserRenta;
    private AutomovilesEntity autoRentar = new AutomovilesEntity();
    private RentasModel modeloRenta = new RentasModel();
    private UsuariosModel modeloUser = new UsuariosModel();
    private AutomovilesModel modeloAuto = new AutomovilesModel();
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

    public String registrarRenta() throws ParseException {
        //settearemos toda la renta para poder enviarla a la bdd
        autoRentar = modeloAuto.obtenerAutomovil(JsfUtil.getRequest().getParameter("idAutomovil"));
        rentaAuto.setUsuarioByIdCliente(modeloUser.obtenerUsuario(duiUserRenta));
        rentaAuto.setAutomovilesByIdCarro(autoRentar);
        rentaAuto.setPrecioRenta((int)autoRentar.getPrecioAutomovil());
        rentaAuto.setEstado(31);
        //Arreglando el formato de la fecha
        rentaAuto.setFechaInicio(convertirFormato(rentaAuto.getFechaInicio()));
        rentaAuto.setFechaFinal(convertirFormato(rentaAuto.getFechaFinal()));
        //creando ID para renta
        crearIDRenta();

        if (compararFechas(rentaAuto.getFechaInicio(),rentaAuto.getFechaFinal())){
            if (modeloRenta.registrarRenta(rentaAuto) == 1){
                FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Renta Ingresada", "Registrado"));
                return "indexCliente";
            }else {
                JsfUtil.setErrorMessage(null, "No se pudo canjear la comprar");
                return "FAlquiler";
            }
        }else {
            JsfUtil.setErrorMessage(null, "Las Fechas de inicio es mayor a la final");
            return "FAlquiler";
        }

    }
    public static String convertirFormato(String fechaOriginal) {
        try {
            // Formato del patrón original
            SimpleDateFormat formatoOriginal = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);

            // Formato del patrón deseado
            SimpleDateFormat formatoDeseado = new SimpleDateFormat("dd/MM/yyyy");

            // Analizar la cadena original en un objeto Date
            Date fecha = formatoOriginal.parse(fechaOriginal);

            // Formatear la fecha en el formato deseado
            return formatoDeseado.format(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Devuelve null en caso de error
        }
    }
    public boolean compararFechas(String fechaInicioStr, String fechaFinalStr) throws ParseException {
        // Crear un formato de fecha para analizar las cadenas
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Convertir las cadenas en objetos Date
        Date fechaInicio = sdf.parse(fechaInicioStr);
        Date fechaFinal = sdf.parse(fechaFinalStr);

        // Comparar las fechas utilizando el método after
        // Si fechaInicio es posterior a fechaFinal, se devuelve false (error)
        // Si fechaInicio no es posterior a fechaFinal, se devuelve true (válido)
        return !fechaInicio.after(fechaFinal);
    }
    public void crearIDRenta(){
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(900) + 100;
        rentaAuto.setIdRenta("RTA"+numeroAleatorio);

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

    public String getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(String idAuto) {
        this.idAuto = idAuto;
    }

    public String getDuiUserRenta() {
        return duiUserRenta;
    }

    public void setDuiUserRenta(String duiUserRenta) {
        this.duiUserRenta = duiUserRenta;
    }
}
