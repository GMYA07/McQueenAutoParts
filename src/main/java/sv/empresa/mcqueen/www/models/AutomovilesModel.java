package sv.empresa.mcqueen.www.models;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.utils.JpaUtil;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;

public class AutomovilesModel {

    public List<AutomovilesEntity> listarAutomoviles(){
        List<AutomovilesEntity> lista = null;

        return lista;
    }

    public List<AutomovilesEntity> listarAutomovilesAgencia(){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM AutomovilesEntity e WHERE e.idAutomovil LIKE 'ATA%'");

            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<AutomovilesEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public List<AutomovilesEntity> listarAutomovilesRenta(){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM AutomovilesEntity e WHERE e.idAutomovil LIKE 'ATR%'");

            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<AutomovilesEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public List<AutomovilesEntity> listarAutomovilesUsuarios(){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM AutomovilesEntity e WHERE e.idAutomovil LIKE 'ATS%'");

            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<AutomovilesEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public List<AutomovilesEntity> listarAutomovilesRentar(){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM AutomovilesEntity e WHERE e.idAutomovil LIKE 'ATR%'");

            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<AutomovilesEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public List<AutomovilesEntity> listarAutomovilesMisAutosUser(UsuarioEntity dui){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM AutomovilesEntity e WHERE e.usuarioByIdClienteVenta = :dui ");
            consulta.setParameter("dui",dui);
            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<AutomovilesEntity> lista = consulta.getResultList();
            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public AutomovilesEntity obtenerAutomovil(String idAuto){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // Recupero el objeto desde la BD a través del método find
            AutomovilesEntity automovil = em.find(AutomovilesEntity.class, idAuto);
            em.close();
            return automovil;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }

    public int cambiarEstadoAutomovil(AutomovilesEntity autoCambiarEstado){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(autoCambiarEstado);
            trans.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }

    public int insertarAutomovil(AutomovilesEntity newAutomovil){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            tran.begin();
            em.persist(newAutomovil);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }
    public int modificarAutomovil(AutomovilesEntity actuAuto){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.merge(actuAuto);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return  0;
        }
    }

    public int eliminarAutomovil(String idAutomovil){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        int filasBorradas = 0;
        try {
            // Recuperando el objeto a eliminar
            AutomovilesEntity automovil = em.find(AutomovilesEntity.class,idAutomovil);
            if (automovil != null){
                tran.begin();
                em.remove(automovil);
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
