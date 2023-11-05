package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.entities.AutomovilesEntity;
import sv.empresa.mcqueen.www.entities.VentasautoEntity;
import sv.empresa.mcqueen.www.utils.JpaUtil;

import java.util.List;

public class VentasAutoModel {
    public List<VentasautoEntity>listarVentas(){
        List<VentasautoEntity> lista = null;
        return lista;
    }
    public List<VentasautoEntity>listaMensajesVentaUser(String dui){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM VentasautoEntity e WHERE e.usuarioByIdCliente.dui = :dui AND e.automovilesByIdCarro.idAutomovil LIKE 'ATS%'");
            consulta.setParameter("dui",dui);
            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<VentasautoEntity> lista = consulta.getResultList();
            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public List<VentasautoEntity>listaMensajesVentaAgencia(String dui){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM VentasautoEntity e WHERE e.usuarioByIdCliente.dui = :dui AND e.idVenta LIKE 'VTA%'");
            consulta.setParameter("dui",dui);
            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<VentasautoEntity> lista = consulta.getResultList();
            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public List<VentasautoEntity> listaAllVentasAgencia(){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM VentasautoEntity e WHERE e.idVenta LIKE 'VTA%'");
            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<VentasautoEntity> lista = consulta.getResultList();
            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public int cambiarEstadoVenta(VentasautoEntity ventaCambiarEstado){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(ventaCambiarEstado);
            trans.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }
    public int borrarVentasFallidas(String idCar){
        EntityManager eM = JpaUtil.getEntityManager();
        EntityTransaction tran = eM.getTransaction();
        try {

            tran.begin();
            Query consulta = eM.createQuery("DELETE FROM VentasautoEntity e WHERE e.estado = :tipo OR e.estado = :tipo2 AND e.automovilesByIdCarro.idAutomovil = :idCarro");
            consulta.setParameter("tipo",11);
            consulta.setParameter("tipo2",13);
            consulta.setParameter("idCarro",idCar);
            int rowCount = consulta.executeUpdate();
            tran.commit();

            if (rowCount == 1){
                eM.close();
                return rowCount;
            }else {
                return 0;
            }

        }catch (Exception e){
            eM.close();
            return 0;
        }
    }
    public int borrarVentasPorDeleteDeAuto(String idCar){
        EntityManager eM = JpaUtil.getEntityManager();
        EntityTransaction tran = eM.getTransaction();
        try {

            tran.begin();
            Query consulta = eM.createQuery("DELETE FROM VentasautoEntity e WHERE e.automovilesByIdCarro.idAutomovil = :idCarro");
            consulta.setParameter("idCarro",idCar);
            int rowCount = consulta.executeUpdate();
            tran.commit();

            if (rowCount == 1){
                eM.close();
                return rowCount;
            }else {
                return 0;
            }

        }catch (Exception e){
            eM.close();
            return 0;
        }
    }
    public VentasautoEntity obtenerVenta(String idVenta){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            // Recupero el objeto desde la BD a través del método find
            VentasautoEntity venta = em.find(VentasautoEntity.class, idVenta);
            em.close();
            return venta;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public int registrarVentaAutomovilUsuario(VentasautoEntity newVenta){
        EntityManager eM = JpaUtil.getEntityManager();
        EntityTransaction tran = eM.getTransaction();
        try {
            tran.begin();
            eM.persist(newVenta);
            tran.commit();
            return 1;
        }catch (Exception e){
            eM.close();
            return 0;
        }
    }
    public int registrarVentaAutomovilAgencia(VentasautoEntity newVenta){
        EntityManager eM = JpaUtil.getEntityManager();
        EntityTransaction tran = eM.getTransaction();
        try {
            tran.begin();
            eM.persist(newVenta);
            tran.commit();
            return 1;
        }catch (Exception e){
            eM.close();
            return 0;
        }
    }

}
