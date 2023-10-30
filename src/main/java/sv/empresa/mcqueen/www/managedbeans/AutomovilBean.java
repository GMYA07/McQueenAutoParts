package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import sv.empresa.mcqueen.www.models.AutomovilesModel;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.models.UsuariosModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;

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
    List<AutomovilesEntity> listaAutomoviles;

    //Variables para guardar la img
    private Part imagen;


    public AutomovilBean(){automovil = new AutomovilesEntity();}

    //Variables para guardar IMG

    public String  registrarNuevoAutomovil() throws IOException {
        String tipoAuto = JsfUtil.getRequest().getParameter("tipoAuto");
        automovil.setFotoAutomovil(imagen.getSubmittedFileName());

        if (tipoAuto.equals("agencia")){
            crearIDAutomovil(1);
            if (modeloAutomovil.insertarAutomovil(automovil) != 1){
                JsfUtil.setErrorMessage("","Error: No se inserto los nuevos Automoviles de Agencia");
                return "registroAutomovilesAgencia";
            }else {
                if (subirIMGCarpetaInterna(imagen.getInputStream(),imagen.getSubmittedFileName()) == 1){
                    return "addAutomoviles";
                }else {
                    JsfUtil.setErrorMessage("","Error: Ocurrio un error al guardar la img");
                    return "registroAutomovilesAgencia";
                }
            }
        }else if (tipoAuto.equals("renta")){
            crearIDAutomovil(0);
            if (modeloAutomovil.insertarAutomovil(automovil) != 1){
                JsfUtil.setErrorMessage("","Error: No se inserto los nuevo Automovil de Renta");
                return "registroAutomovilesParaRenta";
            }else {
                if (subirIMGCarpetaInterna(imagen.getInputStream(),imagen.getSubmittedFileName()) == 1){
                    return "addAutomoviles";
                }else {
                    JsfUtil.setErrorMessage("","Error: Ocurrio un error al guardar la img");
                    return "registroAutomovilesParaRenta";
                }
            }

        }else {
            if (modeloUsuario.verificarDui(duiSolicitante) == 1){
                crearIDAutomovil(2);
                UsuarioEntity userSolicitante = modeloUsuario.obtenerUsuario(duiSolicitante);
                automovil.setEstado(0);
                automovil.setUsuarioByIdClienteVenta(userSolicitante);
                if (modeloAutomovil.insertarAutomovil(automovil) != 1){
                    JsfUtil.setErrorMessage("","Error: No se inserto los nuevo Automovil de Renta");
                    return "SolicitarVenta";
                }else {
                    if (subirIMGCarpetaInterna(imagen.getInputStream(),imagen.getSubmittedFileName()) == 1){
                        return "indexCliente";
                    }else {
                        JsfUtil.setErrorMessage("","Error: Ocurrio un error al guardar la img");
                        return "SolicitarVenta";
                    }
                }
            }else {
                JsfUtil.setErrorMessage("","Error: No se encuentra el dui del usuario registrado");
                return "SolicitarVenta";
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

    public List<AutomovilesEntity> getListaAutomoviles() {
        return modeloAutomovil.listarAutomoviles();
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
