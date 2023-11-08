package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.entities.CitasmecEntity;
import sv.empresa.mcqueen.www.entities.RentasEntity;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import sv.empresa.mcqueen.www.utils.JpaUtil;

import java.util.List;

public class CitasmecModel {
    public List<CitasmecEntity> listarRentas(int estadoCita){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM CitasmecEntity e WHERE e.estadoCita = :estado");
            consulta.setParameter("estado",estadoCita);
            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<CitasmecEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public List<CitasmecEntity> citasMecanico(String idMeca){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM CitasmecEntity e WHERE e.mecanicosByIdMecanico.dui = :id AND e.estadoCita = :estado");
            consulta.setParameter("id",idMeca);
            consulta.setParameter("estado",51);
            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<CitasmecEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public int insertarCita(CitasmecEntity newCita){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try{
            tran.begin();
            em.persist(newCita);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }
    public int modificarCita(CitasmecEntity modiCita){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.merge(modiCita);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return  0;
        }
    }
}
