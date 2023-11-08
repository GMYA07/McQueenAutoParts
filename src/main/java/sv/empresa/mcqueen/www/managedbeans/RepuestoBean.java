package sv.empresa.mcqueen.www.managedbeans;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import sv.empresa.mcqueen.www.models.RepuestosModel;
import sv.empresa.mcqueen.www.utils.JsfUtil;
import sv.empresa.mcqueen.www.entities.RepuestosEntity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;


@MultipartConfig
@ManagedBean
@RequestScoped
public class RepuestoBean {
    private RepuestosEntity repuesto;
    private RepuestosModel modeloRepuestos = new RepuestosModel();
    List<RepuestosEntity> listaRepuestos;
    public RepuestoBean(){ repuesto = new RepuestosEntity();}

    //Variables para guardar la img
    private Part imagen;

    public void registrarRepuestos() throws IOException {
        crearIDRepuestos();
        repuesto.setImagenRepuesto(imagen.getSubmittedFileName());
        if (modeloRepuestos.insertarRepuestos(repuesto) != 1){
            JsfUtil.setErrorMessage("","Error: No se inserto el nuevo Repuesto");
        }else {
            if (subirIMGCarpetaInterna(imagen.getInputStream(),imagen.getSubmittedFileName()) == 1){
                repuesto = null;
                FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado el Producto Exitosamente", "Actualizado"));
            }else {
                    JsfUtil.setErrorMessage("","Error: Ocurrio un error al guardar la img");
            }

        }
    }

    public void modificarRepuesto(){
        if (modeloRepuestos.modificarRepuestos(repuesto) > 0){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado el Producto Exitosamente", "Actualizado"));
        }
    }
    public void eliminarRepuesto(String idRep){
        if (modeloRepuestos.eliminarRepuesto(idRep) > 0){
            FacesContext.getCurrentInstance().addMessage("successMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "El Producto Fue Eliminado", "Actualizado"));
        }
    }

    //FUNCIONES PARA UTILIDADES
    public void crearIDRepuestos(){
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(900) + 100;
        repuesto.setIdRepuesto("RPT" + numeroAleatorio);

    }
    //Esta funcion realizar el llenado para el formulario de actualizar
    public void settearTodoElProducto(String nombre, double precio, int cantidad, String categoria, String marca, String foto, String idRep){
        repuesto.setIdRepuesto(idRep);
        repuesto.setNombre(nombre);
        repuesto.setCategorias(categoria);
        repuesto.setMarca(marca);
        repuesto.setImagenRepuesto(foto);
        repuesto.setPrecio(precio);
        repuesto.setCantidad(cantidad);
    }
    //Con esta funcion guardamos imagenes en una carpeta interna del proyecto
    public int subirIMGCarpetaInterna(InputStream inputS, String nombreIMG){
        try {
            //GuardarIMG en una ubiacion del  Local

            String rutaCarpeta = "C:\\Users\\YAMG\\Documents\\McQueenAutoparts\\src\\main\\webapp\\resources\\imgRepuestos\\";

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
    public List<RepuestosEntity> getListaRepuestos() {
        return modeloRepuestos.listaRepuestos();
    }

    public RepuestosEntity getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(RepuestosEntity repuesto) {
        this.repuesto = repuesto;
    }

    public Part getImagen() {
        return imagen;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
    }
}
