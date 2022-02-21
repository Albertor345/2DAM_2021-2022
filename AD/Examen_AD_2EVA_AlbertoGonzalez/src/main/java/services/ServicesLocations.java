package services;

import model.*;

import java.util.List;

public interface ServicesLocations {
    UbicacionEntity add(UbicacionEntity u);

    boolean delete(UbicacionEntity u);

    boolean update();

    List<UbicacionEntity> getAll();

    UbicacionEntity get();

    List<PermisosUbicacionesEntity> getAllPermisosLocations();
}
