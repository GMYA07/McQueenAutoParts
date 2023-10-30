package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.utils.JpaUtil;
import sv.empresa.mcqueen.www.entities.AdministradoresEntity;
public class AdministradorModel {
    //Funcion para poder Un administrador
    public AdministradoresEntity obtenerAdministrador(int idAdmin){
        EntityManager entyManager = JpaUtil.getEntityManager();
        try{
            //Salimos a buscar el administrador en AdministradoresEntity con el idAdmin
            AdministradoresEntity administrador;
           if (entyManager.find(AdministradoresEntity.class,idAdmin) != null){ // verificamos si esta el administrador

               //Obtenemos la informacion del administrador
               administrador = entyManager.find(AdministradoresEntity.class,idAdmin);

               entyManager.close();
               return administrador;
           }else {
               entyManager.close();
               return null;
           }

        }catch (Exception e){
            entyManager.close();
            return null;
        }
    }

    public int iniciarSesionAdmin(String correoAdmin,String passAdmin){
        //declaramos una entyManager para manejar las consultas y un idAdmin que regresaremos
        EntityManager entyManager = JpaUtil.getEntityManager();
        int idAdmin = 0;
        try {
            Query consulta = entyManager.createQuery("SELECT e.idAdmin FROM AdministradoresEntity e WHERE e.correo = :correo AND e.pass = :pass ");
            consulta.setParameter("correo",correoAdmin);
            consulta.setParameter("pass",passAdmin);

            if (!consulta.getResultList().isEmpty()){
               idAdmin = (Integer) consulta.getSingleResult(); //Con "getSingleResult" Obtenemos solo 1 resultado de lo q desesmos
               entyManager.close();
               return idAdmin;
            }else {
                entyManager.close();
                return idAdmin;
            }

        }catch (Exception e){
            entyManager.close();
            return idAdmin;
        }

    }
}
