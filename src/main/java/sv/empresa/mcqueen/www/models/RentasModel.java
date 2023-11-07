package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.entities.EmpleadosEntity;
import sv.empresa.mcqueen.www.entities.RentasEntity;
import sv.empresa.mcqueen.www.utils.JpaUtil;

import java.util.List;

public class RentasModel {
    public List<RentasEntity> listarRentas(int tipoRenta){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM RentasEntity e WHERE e.estado = :tipo");
            consulta.setParameter("tipo",tipoRenta);
            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<RentasEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public List<RentasEntity> solicitudesRentas(String idAutomovilSoli){ // esta se ocupa para verificar si hay rentas entre el rango de una solicitud
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM RentasEntity e WHERE e.automovilesByIdCarro.idAutomovil = :idAuto");
            consulta.setParameter("idAuto",idAutomovilSoli);
            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<RentasEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public RentasEntity obtenerRenta(String idRentador){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // Recupero el objeto desde la BD a través del método find
            RentasEntity renta = em.find(RentasEntity.class, idRentador);
            em.close();
            return renta;
        } catch (Exception e) {
            em.close();
            return null;
        }
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
    public int modificarRenta(RentasEntity actuRenta){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.merge(actuRenta);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return  0;
        }
    }
}
