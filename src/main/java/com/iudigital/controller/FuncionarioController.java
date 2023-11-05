package com.iudigital.controller;

import com.iudigital.data.FuncionarioDao;
import com.iudigital.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {
    
    private FuncionarioDao funcionarioDao;
    
    public FuncionarioController(){
        funcionarioDao = new FuncionarioDao();
    }
    
    // Obtener funcionario
    public List<Funcionario> obtenerFuncionarios() throws SQLException {
        return funcionarioDao.obtenerFuncionario();
    }
    
    // Crear funcionario
    public void crearFuncionario (Funcionario funcionario) throws SQLException {
        funcionarioDao.crearFuncionario(funcionario);
    }
    
    // Obtener funcionario por id
    public Funcionario obtenerFuncionario (int id) throws SQLException {
        return funcionarioDao.obtenerFuncionario(id);
    }

    // Actualizar funcionario
    public void actualizarFuncionario(int id, Funcionario funcionario) throws SQLException {
        funcionarioDao.actualizarFuncionario(id, funcionario);
    }
    
    // Eliminar funcionario
    public void eliminarFuncionario(int id) throws SQLException {
        funcionarioDao.eliminarFuncionario(id);
    }
}
