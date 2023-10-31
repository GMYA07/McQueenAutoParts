package sv.empresa.mcqueen.www.models;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.utils.JpaUtil;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
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
}
