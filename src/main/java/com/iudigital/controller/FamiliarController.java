package com.iudigital.controller;

import com.iudigital.data.FamiliarDao;
import com.iudigital.domain.Familiar;
import java.sql.SQLException;
import java.util.List;

public class FamiliarController {
    
    private FamiliarDao familiarDao;
    
    public FamiliarController(){
        familiarDao = new FamiliarDao();
    }
    
    // Obtener familiares
    public List<Familiar> obtenerFamiliares() throws SQLException {
        return familiarDao.obtenerFamiliares();
    }
    
    // Crear familiar
    public void crear (Familiar familiar) throws SQLException {
        familiarDao.crearFamiliar(familiar);
    }
    
    // Obtener familiar por id
    public Familiar obtenerFamiliar (int id) throws SQLException {
        return familiarDao.obtenerFamiliar(id);
    }

    // Actualizar familiar
    public void actualizarFamiliar(int id, Familiar familiar) throws SQLException {
        familiarDao.actualizarFamiliar(id, familiar);
    }
    
    // Eliminar familiar
    public void eliminarFamiliar(int id) throws SQLException {
        familiarDao.eliminarFamiliar(id);
    }
    
}
