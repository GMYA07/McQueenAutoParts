package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import sv.empresa.mcqueen.www.utils.JpaUtil;
import java.util.List;
import sv.empresa.mcqueen.www.entities.MecanicosEntity;

public class MecanicosModel {
    public List<MecanicosEntity> listarMecanicos(){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM MecanicosEntity e");

            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<MecanicosEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public String iniciarSesionMecanico(String correoUsuario,String passUsuario){
        //declaramos una entyManager para manejar las consultas y un idAdmin que regresaremos
        EntityManager entyManager = JpaUtil.getEntityManager();
        String idUsuario = "";
        try {
            Query consulta = entyManager.createQuery("SELECT e.dui FROM MecanicosEntity e WHERE e.correo = :correo AND e.pass = :pass ");
            consulta.setParameter("correo",correoUsuario);
            consulta.setParameter("pass",passUsuario);

            if (!consulta.getResultList().isEmpty()){
                idUsuario = (String) consulta.getSingleResult(); //Con "getSingleResult" Obtenemos solo 1 resultado de lo q desesmos
                entyManager.close();
                return idUsuario;
            }else {
                entyManager.close();
                return idUsuario;
            }

        }catch (Exception e){
            entyManager.close();
            return idUsuario;
        }

    }

    public int verificarCorreoExistMecanico(String correo){
        int existe = 0;
        EntityManager entyManager = JpaUtil.getEntityManager();
        try {
            Query consulta = entyManager.createQuery("SELECT e.dui FROM MecanicosEntity e WHERE e.correo = :email");
            consulta.setParameter("email",correo);

            if (consulta.getResultList().isEmpty()){
                existe = 0;
                entyManager.close();
                return existe;
            }else {
                existe = 1;
                entyManager.close();
                return existe;
            }

        }catch (Exception e){
            entyManager.close();
            return existe;
        }
    }

    public int verificarDuiMecanico(String dui){
        int existe = 0;
        EntityManager entyManager = JpaUtil.getEntityManager();
        try {
            Query consulta = entyManager.createQuery("SELECT e.correo FROM MecanicosEntity e WHERE e.dui = :dui");
            consulta.setParameter("dui",dui);

            if (consulta.getResultList().isEmpty()){
                existe = 0;
                entyManager.close();
                return existe;
            }else {
                existe = 1;
                entyManager.close();
                return existe;
            }

        }catch (Exception e){
            entyManager.close();
            return existe;
        }
    }

    public int insertarMecanico(MecanicosEntity newMecanico){
        EntityManager eManager = JpaUtil.getEntityManager();
        EntityTransaction transac = eManager.getTransaction();
        try {
            transac.begin();
            eManager.persist(newMecanico);
            transac.commit();
            eManager.close();
            return 1;
        }catch (Exception e){
            eManager.close();
            return 0;
        }

    }
}
