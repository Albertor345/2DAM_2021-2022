package dao.impl.hibernate;

import configuration.HibernateConfig;
import dao.DAOLocations;
import lombok.extern.log4j.Log4j2;
import model.ObjetoEntity;
import model.PermisosUbicacionesEntity;
import model.UbicacionEntity;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoLocationsHibernateImpl implements DAOLocations {
    @Override
    public UbicacionEntity get(UbicacionEntity u) {
        return null;
    }

    @Override
    public List<UbicacionEntity> getAll() {
        List<UbicacionEntity> locations = new ArrayList<>();
        try (Session session = HibernateConfig.getSession()) {
            locations = session.createNamedQuery("getAllLocations", UbicacionEntity.class).getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return locations;
    }

    @Override
    public boolean add(UbicacionEntity u) {
        return false;
    }

    @Override
    public boolean update(UbicacionEntity u) {
        return false;
    }

    @Override
    public boolean delete(UbicacionEntity u) {
        try (Session session = HibernateConfig.getSession()) {
            session.beginTransaction();

            List<ObjetoEntity> objectsFromLocation =
                    session.createNamedQuery("getAllObjectsFromLocation", ObjetoEntity.class)
                            .setParameter("id", u.getId())
                            .getResultList();

            for (int i = 0; i < objectsFromLocation.size(); i++) {
                session.createNamedQuery("removeAllPermisos_ObjectsWhereObjectX")
                        .setParameter("id", objectsFromLocation.get(i).getId())
                        .executeUpdate();
            }

            session.createNamedQuery("removeAllObjectsWhereLocationX").setParameter("id", u.getId())
                    .executeUpdate();

            session.createNamedQuery("removeAllPermisos_LocationsWhereLocationX").setParameter("id", u.getId())
                    .executeUpdate();

            //intente hacerlo con remove pero me daba un error de
            //"A different object with the same identifier was already associated with the session"
            session.createQuery("delete from UbicacionEntity where id = :id")
                    .setParameter("id", u.getId()).executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public List<PermisosUbicacionesEntity> getAllPermisosLocations() {
        List<PermisosUbicacionesEntity> pl = new ArrayList<>();
        try (Session session = HibernateConfig.getSession()) {
            pl = session.createNamedQuery("getAllPermisosLocations", PermisosUbicacionesEntity.class).getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return pl;
    }
}
