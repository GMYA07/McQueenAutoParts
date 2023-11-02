package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.entities.EmpleadosEntity;
import sv.empresa.mcqueen.www.entities.MecanicosEntity;
import sv.empresa.mcqueen.www.utils.JpaUtil;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import java.util.List;

public class UsuariosModel {

    public List<UsuarioEntity> listarUsuarios(){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM UsuarioEntity e");

            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<UsuarioEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public int verificarCorreoExist(String correo){
        int existe = 0;
        EntityManager entyManager = JpaUtil.getEntityManager();
        try {
            Query consulta = entyManager.createQuery("SELECT e.dui FROM UsuarioEntity e WHERE e.correo = :email");
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

    public int verificarDui(String dui){
        int existe = 0;
        EntityManager entyManager = JpaUtil.getEntityManager();
        try {
            Query consulta = entyManager.createQuery("SELECT e.correo FROM UsuarioEntity e WHERE e.dui = :dui");
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

    public String iniciarSesionUsuario(String correoUsuario,String passUsuario){
        //declaramos una entyManager para manejar las consultas y un idAdmin que regresaremos
        EntityManager entyManager = JpaUtil.getEntityManager();
        String idUsuario = "";
        try {
            Query consulta = entyManager.createQuery("SELECT e.dui FROM UsuarioEntity e WHERE e.correo = :correo AND e.pass = :pass ");
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

    public UsuarioEntity obtenerUsuario(String dui){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // Recupero el objeto desde la BD a través del método find
            UsuarioEntity usuario = em.find(UsuarioEntity.class, dui);
            em.close();
            return usuario;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int insertarUsuario(UsuarioEntity newUsuario){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();
            em.persist(newUsuario);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }

    public int modificarUsuario(UsuarioEntity modifiUsuario){
        EntityManager eM = JpaUtil.getEntityManager();
        EntityTransaction traN = eM.getTransaction();
        try {
            traN.begin();
            eM.merge(modifiUsuario);
            traN.commit();
            eM.close();
            return 1;
        }catch (Exception e){
            eM.close();
            return 0;
        }
    }
    public int eliminarUsuario(String duiUser){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        int filasBorradas = 0;
        try {
            // Recuperando el objeto a eliminar
            UsuarioEntity usuario = em.find(UsuarioEntity.class,duiUser);
            if (usuario != null){
                tran.begin();
                em.remove(usuario);
                tran.commit();
                em.close();
                filasBorradas = 1;
            }
            return filasBorradas;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }
}
