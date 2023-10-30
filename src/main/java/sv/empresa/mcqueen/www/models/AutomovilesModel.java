package sv.empresa.mcqueen.www.models;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sv.empresa.mcqueen.www.utils.JpaUtil;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
public class AutomovilesModel {

    public List<AutomovilesEntity> listarAutomoviles(){
        List<AutomovilesEntity> lista = null;
        return lista;
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
