package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sv.empresa.mcqueen.www.entities.RepuestosEntity;
import sv.empresa.mcqueen.www.utils.JpaUtil;

import java.util.List;

public class RepuestosModel {
    public List<RepuestosEntity> listaRepuestos(){
        List<RepuestosEntity> lista = null;

        return lista;
    }

    public int insertarRepuestos(RepuestosEntity newRepuesto){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.persist(newRepuesto);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }
}
