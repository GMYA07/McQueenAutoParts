package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sv.empresa.mcqueen.www.entities.RentasEntity;
import sv.empresa.mcqueen.www.utils.JpaUtil;

import java.util.List;

public class RentasModel {
    public List<RentasEntity> listarRentas(){
        List<RentasEntity> lista = null;
        return lista;
    }
    public int registrarRenta(RentasEntity newRenta){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.persist(newRenta);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }
}
