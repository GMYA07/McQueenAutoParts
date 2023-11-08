package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.utils.JpaUtil;

import java.util.List;

public class CarritoModel {
    /*public List<CarritoEntity> listarCarrito(){
        EntityManager Em = JpaUtil.getEntityManager();
        try {
            Query consulta = Em.createQuery("SELECT e FROM CarritoEntity e");

            // El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<CarritoEntity> lista = consulta.getResultList();

            Em.close(); // Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            Em.close();
            return null;
        }
    }*/
}
