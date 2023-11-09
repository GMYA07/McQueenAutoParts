package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.entities.*;
import sv.empresa.mcqueen.www.utils.JpaUtil;

import java.util.List;

public class VentaRepuestoModel {
    public List<VentasrepuestosEntity>listarVentas(){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM VentasrepuestosEntity e");

            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<VentasrepuestosEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public List<VentasrepuestosEntity>listarVentaTipo(int estado){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM VentasrepuestosEntity e WHERE e.estadoVenta = :estado");
            consulta.setParameter("estado",estado);
            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<VentasrepuestosEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public List<VentasrepuestosEntity>listarVentaCliente(String duiCliente){
        // Obtengo una instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT e FROM VentasrepuestosEntity e WHERE e.usuarioByIdCliente.dui = :dui");
            consulta.setParameter("dui",duiCliente);
            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<VentasrepuestosEntity> lista = consulta.getResultList();

            em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public int insertarVentaRep(VentasrepuestosEntity newVenta){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.persist(newVenta);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }
    public VentasrepuestosEntity obtenerVenta(String duiCliente){
        EntityManager em = JpaUtil.getEntityManager();
        try {
            //Obtener Item Carrito
            Query consulta = em.createQuery("SELECT e FROM VentasrepuestosEntity e WHERE e.usuarioByIdCliente.dui = :dui ");
            consulta.setParameter("dui",duiCliente);
            if (!consulta.getResultList().isEmpty()){
                VentasrepuestosEntity venta = (VentasrepuestosEntity) consulta.getSingleResult(); //Con "getSingleResult" Obtenemos solo 1 resultado de lo q desesmos
                em.close();
                return venta;
            }else {
                em.close();
                return null;
            }
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public int modificarVentaRep(VentasrepuestosEntity modiVenta){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.merge(modiVenta);
            tran.commit();
            em.close();
            return 1;
        }catch (Exception e){
            em.close();
            return 0;
        }
    }
}
