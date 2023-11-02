package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import sv.empresa.mcqueen.www.utils.JpaUtil;
import sv.empresa.mcqueen.www.entities.EmpleadosEntity;
import java.util.List;

public class EmpleadosModel {
    public List<EmpleadosEntity> listarEmpleados(){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM EmpleadosEntity e");

            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<EmpleadosEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public String iniciarSesionEmpleado(String correoUsuario,String passUsuario){
        //declaramos una entyManager para manejar las consultas y un idAdmin que regresaremos
        EntityManager entyManager = JpaUtil.getEntityManager();
        String idUsuario = "";
        try {
            Query consulta = entyManager.createQuery("SELECT e.dui FROM EmpleadosEntity e WHERE e.correo = :correo AND e.pass = :pass ");
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

    public int verificarCorreoExistEmpleado(String correo){
        int existe = 0;
        EntityManager entyManager = JpaUtil.getEntityManager();
        try {
            Query consulta = entyManager.createQuery("SELECT e.dui FROM EmpleadosEntity e WHERE e.correo = :email");
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

    public int verificarDuiMecanicoEmpleado(String dui){
        int existe = 0;
        EntityManager entyManager = JpaUtil.getEntityManager();
        try {
            Query consulta = entyManager.createQuery("SELECT e.correo FROM EmpleadosEntity e WHERE e.dui = :dui");
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
    public EmpleadosEntity obtenerEmpleado(String dui){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // Recupero el objeto desde la BD a través del método find
            EmpleadosEntity empleado = em.find(EmpleadosEntity.class, dui);
            em.close();
            return empleado;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public int insertarEmpleado(EmpleadosEntity newEmpleado){
        EntityManager eM = JpaUtil.getEntityManager();
        EntityTransaction tranS = eM.getTransaction();

        try {
            tranS.begin();
            eM.persist(newEmpleado);
            tranS.commit();
            eM.close();
            return 1;
        }catch (Exception e){
            eM.close();
            return 0;
        }
    }
    public int modificarEmpleado(EmpleadosEntity modiEmpleado){
        EntityManager eM = JpaUtil.getEntityManager();
        EntityTransaction tran = eM.getTransaction();
        try{
            tran.begin();
            eM.merge(modiEmpleado);
            tran.commit();
            eM.close();
            return 1;
        }catch (Exception e){
            eM.close();
            return 0;
        }
    }
    public int eliminarEmpleado(String duiEmpleado){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        int filasBorradas = 0;
        try {
            EmpleadosEntity empleado = em.find(EmpleadosEntity.class,duiEmpleado);
            if (empleado != null){
                trans.begin();
                em.remove(empleado);
                trans.commit();
                em.close();
                filasBorradas = 1;
            }
            return filasBorradas;
        }catch (Exception e){
            em.close();
            return filasBorradas;
        }
    }
}
