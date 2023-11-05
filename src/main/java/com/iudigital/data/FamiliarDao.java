package com.iudigital.data;

import com.iudigital.domain.Familiar;
import com.iudigital.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FamiliarDao {
  
    // SQL GET
    private static final String GET_FAMILIAR = "select * from familiares" ;
    
    // SQL CREATE
    private static final String CREATE_FAMILIAR = "insert into familiares "
            + "( funcionarioID, nombres, apellidos, rol) "
            + "VALUES ( ?, ?, ?, ?)" ;

    // SQL GET_BY_ID
    private static final String GET_FAMILIAR_BY_ID = "select * from familiares where id = ?";
    
    // SQL UPDATE
    private static final String UPDATE_FAMILIAR = "update familiares set "
            + "funcionarioID = ?, "
            + "nombres = ?, "
            + "apellidos = ?, "
            + "rol = ? "
            + "where id = ?";
    
    // SQL DELETE
    private static final String DELETE_FAMILIAR = "delete from familiares where id = ?";      
    
    // Obtener familiar
    public List<Familiar> obtenerFamiliares() throws SQLException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;        
        List<Familiar> familiares = new ArrayList<>();
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_FAMILIAR);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                
                Familiar familiar = new Familiar();
                familiar.setId(resultSet.getInt("id"));
                familiar.setFuncionarioID(resultSet.getInt("funcionarioID"));
                familiar.setNombres(resultSet.getString("nombres"));
                familiar.setApellidos(resultSet.getString("apellidos"));
                familiar.setRol(resultSet.getString("rol"));
                familiares.add(familiar);
                
            }
            
            return familiares;
            
        }finally{
            
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            
        }
        
    }
    
    // Crear familiar 
    public void crearFamiliar(Familiar familiar) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(CREATE_FAMILIAR);
            
            preparedStatement.setInt(1, familiar.getFuncionarioID());
            preparedStatement.setString(2, familiar.getNombres());
            preparedStatement.setString(3, familiar.getApellidos());
            preparedStatement.setString(4, familiar.getRol());
            preparedStatement.executeUpdate();            
            
        } finally {
            
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
        }
        
    }
    
    // Obtener familiar por id
    public Familiar obtenerFamiliar(int id) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Familiar familiar = null;
        
        try {
            
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(GET_FAMILIAR_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();       
            
            while (resultSet.next()){
                
                familiar = new Familiar();
                familiar.setId(resultSet.getInt("id"));
                familiar.setFuncionarioID(resultSet.getInt("funcionarioID"));
                familiar.setNombres(resultSet.getString("nombres"));
                familiar.setApellidos(resultSet.getString("apellidos"));
                familiar.setRol(resultSet.getString("rol"));
                                
            }
            
            return familiar;
            
        } finally {
            
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }   
    
    // Actualizar familiar
    public void actualizarFamiliar(int id, Familiar familiar) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(UPDATE_FAMILIAR);  
            
            preparedStatement.setInt(1, familiar.getFuncionarioID());
            preparedStatement.setString(2, familiar.getNombres());
            preparedStatement.setString(3, familiar.getApellidos());
            preparedStatement.setString(4, familiar.getRol());
            preparedStatement.setInt(5, id); 
            
            preparedStatement.executeUpdate(); 
            
        } finally {
            
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }        
            
        }   
        
    }
    
    // Eliminar familiar
    public void eliminarFamiliar(int id) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {                   
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FAMILIAR);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
        } finally {
            
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }  
            
        }
        
    }
    
}



