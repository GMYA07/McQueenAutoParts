package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.entities.RentasEntity;
import sv.empresa.mcqueen.www.utils.JpaUtil;

import java.util.List;

public class RentasModel {
    public List<RentasEntity> listarRentas(){
        List<RentasEntity> lista = null;
        return lista;
    }
    public List<RentasEntity> solicitudesRentas(String idAutomovilSoli){
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
