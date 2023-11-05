package com.iudigital.data;

import com.iudigital.domain.Funcionario;
import com.iudigital.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {
    
    // SQL GET
    private static final String GET_FUNCIONARIO = "select * from funcionarios" ;
    
    // SQL CREATE
    private static final String CREATE_FUNCIONARIO = "insert into funcionarios "
            + "(tipoIdentificacion, numeroIdentificacion, nombres, apellidos, " +
            "estadoCivil, sexo, direccion, telefono, fechaNacimiento, informacionAcademica) "
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
   
    // SQL GET_BY_ID
    private static final String GET_FUNCIONARIO_BY_ID = "select * from funcionarios where id = ?";
    
    // SQL UPDATE
    private static final String UPDATE_FUNCIONARIO = "update funcionarios set "
            + "tipoIdentificacion = ?, "
            + "numeroIdentificacion = ?, "
            + "nombres = ?, "
            + "apellidos = ?, "
            + "estadoCivil = ?, "
            + "sexo = ?, "
            + "direccion = ?, "
            + "telefono = ?, "
            + "fechaNacimiento = ?, "
            + "informacionAcademica = ? "
            + "where id = ?";
    
    // SQL DELETE
    private static final String DELETE_FUNCIONARIO = "delete from funcionarios where id = ?";
   
    // Obtener funcionaro 
    public List<Funcionario> obtenerFuncionario() throws SQLException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIO);            
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id"));
                funcionario.setTipoIdentificacion(resultSet.getString("tipoIdentificacion"));
                funcionario.setNumeroIdentificacion(resultSet.getString("numeroIdentificacion"));                        
                funcionario.setNombres(resultSet.getString("nombres"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setEstadoCivil(resultSet.getString("estadoCivil"));               
                funcionario.setSexo(resultSet.getString("sexo"));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getString("fechaNacimiento"));
                funcionario.setInformacionAcademica(resultSet.getString("informacionAcademica"));             
                funcionarios.add(funcionario);
                
            }
            
            return funcionarios;
            
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
    
    // Crear funcionario 
    public void crearFuncionario(Funcionario funcionario) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(CREATE_FUNCIONARIO);
            
            preparedStatement.setString(1, funcionario.getTipoIdentificacion());
            preparedStatement.setString(2, funcionario.getNumeroIdentificacion());                       
            preparedStatement.setString(3, funcionario.getNombres());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setString(5, funcionario.getEstadoCivil());            
            preparedStatement.setString(6, funcionario.getSexo());
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
            preparedStatement.setString(9, funcionario.getFechaNacimiento());
            preparedStatement.setString(10, funcionario.getInformacionAcademica());
            preparedStatement.executeUpdate();
            
        }finally {
            
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
        }
        
    }
    
    // Obtener funcionario por id
    public Funcionario obtenerFuncionario(int id) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Funcionario funcionario = null;
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(GET_FUNCIONARIO_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        
            while (resultSet.next()) {
                
                funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id"));
                funcionario.setTipoIdentificacion(resultSet.getString("tipoIdentificacion"));
                funcionario.setNumeroIdentificacion(resultSet.getString("numeroIdentificacion"));                        
                funcionario.setNombres(resultSet.getString("nombres"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setEstadoCivil(resultSet.getString("estadoCivil"));               
                funcionario.setSexo(resultSet.getString("sexo"));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getString("fechaNacimiento"));
                funcionario.setInformacionAcademica(resultSet.getString("informacionAcademica"));            
                
            }            
            return funcionario;
            
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
    
    // Actualizar funcionario
    public void actualizarFuncionario(int id, Funcionario funcionario) throws SQLException {
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareCall(UPDATE_FUNCIONARIO);
            preparedStatement.setString(1, funcionario.getTipoIdentificacion());
            preparedStatement.setString(2, funcionario.getNumeroIdentificacion());
            preparedStatement.setString(3, funcionario.getNombres());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setString(5, funcionario.getEstadoCivil());
            preparedStatement.setString(6, funcionario.getSexo());
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
            preparedStatement.setString(9, funcionario.getFechaNacimiento());
            preparedStatement.setString(10, funcionario.getInformacionAcademica());
            preparedStatement.setInt(11, id);   
            
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

    // Eliminar funcionario
    public void eliminarFuncionario(int id) throws SQLException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {                   
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FUNCIONARIO);
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
