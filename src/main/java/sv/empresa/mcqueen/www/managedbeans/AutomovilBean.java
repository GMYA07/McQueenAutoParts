package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import sv.empresa.mcqueen.www.models.AutomovilesModel;
import sv.empresa.mcqueen.www.models.UsuariosModel;
import sv.empresa.mcqueen.www.models.VentasAutoModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

@MultipartConfig
@ManagedBean
@RequestScoped
public class AutomovilBean {
    private AutomovilesEntity automovil;
    public String duiSolicitante;
    private AutomovilesModel modeloAutomovil = new AutomovilesModel();
    private UsuariosModel modeloUsuario = new UsuariosModel();
    private VentasAutoModel modeloVentaAuto = new VentasAutoModel();
    //Listas de diferentes tipos para cada crud que sea necesario
    private List<AutomovilesEntity> listaAutomoviles;
    private List<AutomovilesEntity> listaAutomovilesAgencia;
    private List<AutomovilesEntity> listaAutomovilesUsuarios;
    private List<AutomovilesEntity> listaAutomovilesRentar;
    private List<AutomovilesEntity> listaAutomovilesMisAutos; //esta lista es para un usuario especifico
    private List<AutomovilesEntity> listaAutomovilesRentados;

    //Variables para guardar la img
    private Part imagen;


    public AutomovilBean(){automovil = new AutomovilesEntity();}

    //Variables para guardar IMG

    public void  registrarNuevoAutomovil(String tipo) throws IOException {

        automovil.setFotoAutomovil(imagen.getSubmittedFileName());

        if (tipo.equals("agencia")){
            //Usamos Funcion para crear el ID del Automovil
            crearIDAutomovil(1);
            //le colocamos un estado al Carro para poder saber su disponiblidad
            automovil.setEstado(11);
            //ACCIONES PARA INSERTARLO EN LA BDD
            if (modeloAutomovil.insertarAutomovil(automovil) != 1){
                JsfUtil.setErrorMessage("","Error: No se inserto los nuevos Automoviles de Agencia");
            }else {
                if (subirIMGCarpetaInterna(imagen.getInputStream(),imagen.getSubmittedFileName()) == 1){
                    FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se registro exitosamente el Automovil", "Registrado"));
                }else {
                    JsfUtil.setErrorMessage("","Error: Ocurrio un error al guardar la img");

                }
            }
        }else if (tipo.equals("renta")){
            //Usamos Funcion para crear el ID del Automovil
            crearIDAutomovil(0);
            //le colocamos un estado al Carro para poder saber su disponiblidad
            automovil.setEstado(30);
            //ACCIONES PARA INSERTARLO EN LA BDD
            if (modeloAutomovil.insertarAutomovil(automovil) != 1){
                JsfUtil.setErrorMessage("","Error: No se inserto los nuevo Automovil de Renta");
            }else {
                if (subirIMGCarpetaInterna(imagen.getInputStream(),imagen.getSubmittedFileName()) == 1){
                    FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se registro exitosamente el Automovil", "Registrado"));
                }else {
                    JsfUtil.setErrorMessage("","Error: Ocurrio un error al guardar la img");

                }
            }

        }else {
            if (modeloUsuario.verificarDui(duiSolicitante) == 1){
                //Usamos Funcion para crear el ID del Automovil
                crearIDAutomovil(2);
                //Buscamos el usuario solicitante
                UsuarioEntity userSolicitante = modeloUsuario.obtenerUsuario(duiSolicitante);
                automovil.setUsuarioByIdClienteVenta(userSolicitante);
                //le colocamos un estado al Carro para poder saber su disponiblidad
                automovil.setEstado(0);
                //ACCIONES PARA INSERTARLO EN LA BDD
                if (modeloAutomovil.insertarAutomovil(automovil) != 1){
                    JsfUtil.setErrorMessage("","Error: No se inserto los nuevo Automovil de Renta");

                }else {
                    if (subirIMGCarpetaInterna(imagen.getInputStream(),imagen.getSubmittedFileName()) == 1){
                        FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Se registro exitosamente el Automovil", "Registrado"));
                    }else {
                        JsfUtil.setErrorMessage("","Error: Ocurrio un error al guardar la img");

                    }
                }
            }else {
                JsfUtil.setErrorMessage("","Error: No se encuentra el dui del usuario registrado");

            }
        }

    }
    public void modificarAutomovil(){

        if (modeloAutomovil.modificarAutomovil(automovil) > 0){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Se actualizo exitosamente el Automovil", "Registrado"));
        }else {
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No se pudo ingresar el Auto", "No registrado"));
        }
    }

    public void solicitarDenuevoPublicAuto() throws IOException {

        automovil.setFotoAutomovil(imagen.getSubmittedFileName());
        automovil.setUsuarioByIdClienteVenta(modeloUsuario.obtenerUsuario(duiSolicitante));
        automovil.setEstado(0);

        if (modeloAutomovil.modificarAutomovil(automovil) > 0){
            if (subirIMGCarpetaInterna(imagen.getInputStream(),imagen.getSubmittedFileName()) == 1){
                FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Se registro exitosamente el Automovil", "Registrado"));
            }else {
                JsfUtil.setErrorMessage("","Error: Ocurrio un error al guardar la img");

            }
        }else {
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "No se pudo ingresar el Auto", "No registrado"));
        }
    }

    public void eliminarAutomovil(String idAutomovil){
        if (modeloAutomovil.eliminarAutomovil(idAutomovil) > 0){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Se elimino el automovil", "Eliminado"));
        }
    }

    public void cambiarEstadoAuto(AutomovilesEntity auto){
        String estadoAccion = JsfUtil.getRequest().getParameter("estadoAccion");
       if (auto.getEstado() == 0){
           //evaluamos si aceptaremos rechazaremos
           if (estadoAccion.equals("111")){
               auto.setEstado(1);
           }else {
               auto.setEstado(2);
           }

           if (modeloAutomovil.cambiarEstadoAutomovil(auto) > 0){
               FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado el Estado Exitosamente", "Actualizado"));
           }
       }else if(auto.getEstado() == 1){
               auto.setEstado(3);   //si ingresa a este apartado es por q si no se vendio el vehiculo y este quiere ser quitado de publicacion
               if (modeloVentaAuto.borrarVentasPorDeleteDeAuto(auto.getIdAutomovil()) == 1){
                   if (modeloAutomovil.cambiarEstadoAutomovil(auto) > 0){
                       FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado el Estado Exitosamente", "Actualizado"));
                   }
               }else { // se coloca esto por si acaso no haya ningun interes de compra pues siempre cambiara su estado
                   if (modeloAutomovil.cambiarEstadoAutomovil(auto) > 0){
                       FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado el Estado Exitosamente", "Actualizado"));
                   }
               }

       }else if(auto.getEstado() == 2){ // si presiona el boton y esta en 2 eso significa que hace un cambio en su solicitud
           if (estadoAccion.equals("777")){
               modeloAutomovil.eliminarAutomovil(auto.getIdAutomovil());
           }else {
               auto.setEstado(0);
               if (modeloAutomovil.cambiarEstadoAutomovil(auto) > 0){
                   FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado el Estado Exitosamente", "Actualizado"));
               }
           }
       }
    }

    //FUNCIONES PARA UTILIDADES
    public void crearIDAutomovil(int tipo){
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(900) + 100;
       if (tipo == 1){
           automovil.setIdAutomovil("ATA"+numeroAleatorio);
       }else if (tipo == 0){
           automovil.setIdAutomovil("ATR"+numeroAleatorio);
       }else {
           automovil.setIdAutomovil("ATS"+numeroAleatorio);
       }
    }
    public void setterAutomovilModi(String idAuto){
        automovil = modeloAutomovil.obtenerAutomovil(idAuto);
    }
    public void setterSoliAutomovilModi(String idAuto){
        automovil = modeloAutomovil.obtenerAutomovil(idAuto);
        automovil.setEstado(0);
    }
    //Con esta funcion guardamos imagenes en una carpeta interna del proyecto
    public int subirIMGCarpetaInterna(InputStream inputS, String nombreIMG){
        try {
            //GuardarIMG en una ubiacion del  Local

            String rutaCarpeta = "C:\\Users\\YAMG\\Documents\\McQueenAutoparts\\src\\main\\webapp\\resources\\imgAutomoviles\\";

            //Ruta completa del archivo
            String rutaCompleta = rutaCarpeta + nombreIMG;

            try (OutputStream ouput = new FileOutputStream(rutaCompleta)){
                int bytesLec;
                byte[] buffer = new byte[8192];
                while ((bytesLec = inputS.read(buffer)) != -1){
                    ouput.write(buffer,0,bytesLec);
                }
                return 1;
            }catch (Exception e){
                e.printStackTrace();
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    //GETTER AND SETTER

    public List<AutomovilesEntity> getListaAutomovilesMisAutos(String duiU) {
        return modeloAutomovil.listarAutomovilesMisAutosUser(modeloUsuario.obtenerUsuario(duiU));
    }

    public List<AutomovilesEntity> getListaAutomovilesRentar() {
        return modeloAutomovil.listarAutomovilesRentar();
    }

    public List<AutomovilesEntity> getListaAutomovilesUsuarios() {
        return modeloAutomovil.listarAutomovilesUsuarios();
    }

    public List<AutomovilesEntity> getListaAutomoviles() {
        return modeloAutomovil.listarAutomoviles();
    }

    public List<AutomovilesEntity> getListaAutomovilesAgencia() {
        return modeloAutomovil.listarAutomovilesAgencia();
    }

    public List<AutomovilesEntity> getListaAutomovilesRentados() {
        return modeloAutomovil.listarAutomovilesRenta();
    }

    public AutomovilesEntity getAutomovil() {
        return automovil;
    }

    public void setAutomovil(AutomovilesEntity automovil) {
        this.automovil = automovil;
    }

    public Part getImagen() {
        return imagen;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
    }

    public String getDuiSolicitante() {
        return duiSolicitante;
    }

    public void setDuiSolicitante(String duiSolicitante) {
        this.duiSolicitante = duiSolicitante;
    }

}
