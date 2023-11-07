package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.entities.RentasEntity;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import sv.empresa.mcqueen.www.models.AutomovilesModel;
import sv.empresa.mcqueen.www.models.RentasModel;
import sv.empresa.mcqueen.www.models.UsuariosModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
        rentaAuto.setEstado(41);
        //Arreglando el formato de la fecha
        rentaAuto.setFechaInicio(convertirFormato(rentaAuto.getFechaInicio()));
        rentaAuto.setFechaFinal(convertirFormato(rentaAuto.getFechaFinal()));

        //hacemos el calculo de los dias q rentara el auto y dependiendo del precio de renta por dia asi se le multiplicara
        int fechasDeLaRenta = calcularDiferenciaDias(rentaAuto.getFechaInicio(),rentaAuto.getFechaFinal());
        rentaAuto.setPrecioRenta((fechasDeLaRenta * (int)autoRentar.getPrecioAutomovil()));

        //creando ID para renta
        crearIDRenta();

       if (compararFechaConHoy(rentaAuto.getFechaInicio())){
           if (compararFechas(rentaAuto.getFechaInicio(),rentaAuto.getFechaFinal())){
               listaRentas = modeloRenta.solicitudesRentas(JsfUtil.getRequest().getParameter("idAutomovil"));

               boolean exitSoli = false;

               for (RentasEntity solicitudes : listaRentas) {
                   exitSoli = verificarFechaEnRango(rentaAuto.getFechaInicio(),solicitudes.getFechaInicio(),solicitudes.getFechaFinal());
                   if (exitSoli == true){
                       break;
                   }
               }
               if (exitSoli == false){
                   if (modeloRenta.registrarRenta(rentaAuto) == 1){
                       FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Renta Ingresada", "Registrado"));
                       return "indexCliente";
                   }else {
                       JsfUtil.setErrorMessage(null, "No se pudo canjear la comprar");
                       return "FAlquiler";
                   }
               }else {
                   JsfUtil.setErrorMessage(null, "Ya hay una solicitud entre en rango de esa fecha");
                   return "FAlquiler";
               }
           }else {
               JsfUtil.setErrorMessage(null, "Las Fechas de inicio es mayor a la final");
               return "FAlquiler";
           }
       }else {
           JsfUtil.setErrorMessage(null, "Las Fechas de inicio debe ser un dia despues de la fecha actual ");
           return "FAlquiler";
       }

    }
    public void aceptarSolicitudRenta(){
        autoRentar = modeloAuto.obtenerAutomovil(JsfUtil.getRequest().getParameter("idAuto"));
        if (autoRentar.getEstado() == 30){
            autoRentar.setEstado(31);
            rentaAuto = modeloRenta.obtenerRenta(JsfUtil.getRequest().getParameter("idRenta"));
            rentaAuto.setEstado(42);

            if (modeloAuto.modificarAutomovil(autoRentar) == 1){
                if (modeloRenta.modificarRenta(rentaAuto) == 1){
                    FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Renta Aceptada", "Registrado"));
                }else{
                    JsfUtil.setErrorMessage(null, "No se pudo aceptar ");
                }
            }else {
                JsfUtil.setErrorMessage(null, "No se pudo Cambiar de estado el automovil ");
            }
        }else {
            JsfUtil.setErrorMessage(null, "No se pudo Rentar el auto por que esta siendo rentado ");
        }
    }
    //INICIO DE FUNCIONES PARA COMPARAR FECHAS
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
    public boolean compararFechaConHoy(String fechaInicioStr) throws ParseException {
        // Obtener la fecha actual (hoy)
        Date fechaHoy = new Date();

        // Crear un formato de fecha para analizar la fecha de inicio
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Convertir la fecha de inicio en un objeto Date
        Date fechaInicio = sdf.parse(fechaInicioStr);

        // Comparar la fecha de inicio con la fecha actual
        return !fechaInicio.before(fechaHoy);
    }

    public static int calcularDiferenciaDias(String fechaInicioStr, String fechaFinalStr) {
        // Crear un formateador de fecha personalizado para el formato "dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convertir las cadenas de fecha en objetos LocalDate utilizando el formateador
        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, formatter);
        LocalDate fechaFinal = LocalDate.parse(fechaFinalStr, formatter);

        // Calcular la diferencia en días y convertirla a int
        int diferenciaDias = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFinal);

        return diferenciaDias; //se le agrega un mas 1 para contar el dia de la fecha de inicio
    }
    public boolean verificarFechaEnRango(String fechaUsuarioStr, String fechaInicioStr, String fechaFinalStr) throws ParseException {
        // Crear un formato de fecha para analizar las cadenas
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Convertir las cadenas en objetos Date
        Date fechaInicio = sdf.parse(fechaInicioStr);
        Date fechaFinal = sdf.parse(fechaFinalStr);
        Date fechaUsuario = sdf.parse(fechaUsuarioStr);

        // Verificar si la fecha del usuario está dentro del rango
        return (fechaUsuario.after(fechaInicio) && fechaUsuario.before(fechaFinal));
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
        return !fechaInicio.after(fechaFinal); //se le coloca ! para poder negar el if del condicional de la funcion de registrar por q si es true pues necesitamos q se niege haya para no pasar
    }

    //FIN DE FUNCIONES PARA COMPARAR FECHAS


    public void crearIDRenta(){
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(900) + 100;
        rentaAuto.setIdRenta("RTA"+numeroAleatorio);

    }

    //GETTER AND SETTER

    public List<RentasEntity> getListaRentas(int tipoRenta) {
        return modeloRenta.listarRentas(tipoRenta);
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
