package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sv.empresa.mcqueen.www.entities.VentasautoEntity;
import sv.empresa.mcqueen.www.utils.JpaUtil;

import java.util.List;

public class VentasAutoModel {
    public List<VentasautoEntity>listarVentas(){
        List<VentasautoEntity> lista = null;
        return lista;
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
}
