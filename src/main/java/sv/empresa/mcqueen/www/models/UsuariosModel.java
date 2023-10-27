package sv.empresa.mcqueen.www.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sv.empresa.mcqueen.www.entities.UsuarioEntity;
import sv.empresa.mcqueen.www.utils.JpaUtil;

import java.util.List;

public class UsuariosModel {

    public List<UsuarioEntity> listarUsuarios(){
        List<UsuarioEntity> lista = null;

        return lista;
    }
    public int verificarCorreoExist(String correo){
        int existe = 0;
        EntityManager entyManager = JpaUtil.getEntityManager();
        try {

            Query consulta = entyManager.createQuery("SELECT e.dui FROM UsuarioEntity e WHERE e.correo = :email");
            consulta.setParameter("email",correo);

            if (consulta == null){
                existe = 0;
                entyManager.close();
                return existe;
            }else {
                existe = 1;
                entyManager.close();
                return existe;
            }

        }catch (Exception e){
            entyManager.close();
            return existe;
        }
    }
}
