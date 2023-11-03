package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.utils.JpaUtil;
import sv.empresa.mcqueen.www.entities.RepuestosEntity;
import java.util.List;

public class RepuestosModel {
    public List<RepuestosEntity> listaRepuestos(){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM RepuestosEntity e");

            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<RepuestosEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
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
    public int modificarRepuestos(RepuestosEntity actuRepuesto){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.merge(actuRepuesto);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return  0;
        }
    }
    public int eliminarRepuesto(String idRep){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        int filasBorradas = 0;
        try {
            // Recuperando el objeto a eliminar
            RepuestosEntity repuesto = em.find(RepuestosEntity.class,idRep);
            if (repuesto != null){
                tran.begin();
                em.remove(repuesto);
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
