package com.iudigital.presentation;

import com.iudigital.controller.FamiliarController;
import com.iudigital.controller.FuncionarioController;
import com.iudigital.domain.Familiar;
import com.iudigital.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws SQLException {

        int opt = -1;
        Scanner sc = new Scanner(System.in);
        
        while (opt != 0) {
            
            // Menú de opciones
            System.out.println("""
                               - MENU
                                  1. Menú funcionarios
                                  2. Menú familiares 
                                  0. Salir.
                                """);
            
            System.out.print("- Selección: ");
            
            opt = sc.nextInt();
            sc.nextLine();
            
            switch(opt) {
                
                case 0 -> System.out.println("- Cerrando interfaz...");
                case 1 -> menuFuncionario();
                case 2 -> menuFamiliar();           
                default -> System.out.println("- Digite una opción válida...");
    
            }        
                
        }

    }
    
    // operaciones familiar
    
    public static void menuFamiliar() throws SQLException{
        
        int opt = -1;
        Scanner sc = new Scanner(System.in);
        FamiliarController familiarController = new FamiliarController();
        while (opt != 0) {
            
            // Menú de opciones
            System.out.println("""
                               - MENU FAMILIARES
                                  1. Mostrar familiares
                                  2. Mostrar familiar (id)
                                  3. Crear familiar
                                  4. Eliminar familiar
                                  5. Actualizar familiar
                                  0. Salir.
                                """);
            
            System.out.print("- Selección: ");
            opt = sc.nextInt();
           
            switch(opt) {
                
                case 0 -> System.out.println("- Cerrando interfaz...");
                case 1 -> obtenerFamiliares(familiarController);
                case 2 -> obtenerFamiliar(familiarController);
                case 3 -> crearFamiliar(familiarController);                
                case 4 -> eliminarFamiliar(familiarController);     
                case 5 -> actualizarFamiliar(familiarController);
                default -> System.out.println("- Digite una opción válida...");
    
            }
            
        }         
        
    }
    
    public static void obtenerFamiliar(FamiliarController familiarController){
        
        Scanner sc = new Scanner(System.in);
        System.out.print("\n- Digite el id del familiar: ");
        int familiarId = sc.nextInt();
                
        try {
            
            Familiar familiar = familiarController.obtenerFamiliar(familiarId);
            
            if (familiar != null){
                System.out.println("");
                System.out.println("- ID: " + familiar.getId() + "\n"
                    + "   - Funcionario afiliado: " + familiar.getFuncionarioID() + "\n"                          
                    + "   - Nombre: " + familiar.getNombres() + "\n"
                    + "   - Apellidos: " + familiar.getApellidos() + "\n"
                    + "   - Rol: " + familiar.getRol() + "\n") ;        
                    
            } else {
                System.out.println("\n- El familiar con id "+familiarId+" no existe...\n");
            }
            
        } catch(SQLException exception){
            
            exception.printStackTrace();
            
        }
        
    }

    public static void obtenerFamiliares(FamiliarController familiarController){
        
        try {
            List<Familiar> familiares = familiarController.obtenerFamiliares();
            // Validación sobre los registros existentes
            if (familiares.isEmpty()) {                
                System.out.println("\n- No hay registros...\n");                
            } else {
                // Mostrar registros existentes
                System.out.println("\n- Mostrando registros...\n");
                familiares.forEach(familiar -> {

                    System.out.println("- ID: " + familiar.getId() + "\n"
                        + "   - Funcionario afiliado: " + familiar.getFuncionarioID() + "\n"                          
                        + "   - Nombre: " + familiar.getNombres() + "\n"
                        + "   - Apellidos: " + familiar.getApellidos() + "\n"
                        + "   - Rol: " + familiar.getRol() + "\n") ;          
                    });
                
            }
            
        } catch(SQLException exception){
            exception.printStackTrace();
        }
        
    }
    
    public static void actualizarFamiliar(FamiliarController familiarController) throws SQLException{
        
        Scanner sc = new Scanner(System.in); 
        
        try {
            
            System.out.print("\n- Digite el id del familiar: ");
            int familiarId = sc.nextInt();
            
            Familiar familiar = familiarController.obtenerFamiliar(familiarId);        
            
            if (familiar != null){
                
                // Atajar el enter
                sc.nextLine();

                // Recolección de datos       
                
                // TODO VALIDAR EXISTENCIA DE FUNCIONARIO
                System.out.print("\n- Digite id del funcionario afiliado: ");
                int funcionarioId = sc.nextInt();      
                sc.nextLine();
                
                System.out.print("\n- Digite nombres: ");
                String nombreFamiliar = sc.nextLine();   

                System.out.print("- Digite apellidos: ");
                String apellidoFamiliar = sc.nextLine();              

                System.out.print("- Digite rol: ");
                String rolFamiliar = sc.nextLine();

                System.out.println("\n- Familiar actualizado con éxito...\n");

                // Instanciar Funcionario

                //Funcionario funcionario = new Funcionario();
                familiar.setFuncionarioID(funcionarioId);
                familiar.setNombres(nombreFamiliar);
                familiar.setApellidos(apellidoFamiliar);
                familiar.setRol(rolFamiliar);
                
                familiarController.actualizarFamiliar(familiarId, familiar);                   
                
            } else{
                
                System.out.println("\n- El funcionario con id " + familiarId + " no existe...\n");                
                
            }
            
        } catch(SQLException exception){
            
            exception.printStackTrace();
            
        }
    }
    
    public static void crearFamiliar(FamiliarController familiarController){
        
        try {
            
            Scanner sc = new Scanner(System.in);
                    
            // Recolección de datos       
            System.out.print("\n- Digite id del funcionario relacionado: ");
            int funcionarioId = sc.nextInt();      
            sc.nextLine();

            System.out.print("\n- Digite nombres: ");
            String nombreFamiliar = sc.nextLine();   

            System.out.print("- Digite apellidos: ");
            String apellidoFamiliar = sc.nextLine();              

            System.out.print("- Digite rol: ");
            String rolFamiliar = sc.nextLine();
            
            // Instanciar Familiar            
            Familiar familiar = new Familiar();
            familiar.setFuncionarioID(funcionarioId);
            familiar.setNombres(nombreFamiliar);
            familiar.setApellidos(apellidoFamiliar);
            familiar.setRol(rolFamiliar);
            
            // Crear Familiar
            familiarController.crear(familiar);             
            
            System.out.println("\n- Familiar creado con éxito...\n");            
            
        } catch(SQLException exception){
            
            exception.printStackTrace();
            
        }
        
    }
    
    public static void eliminarFamiliar(FamiliarController familiarController){
        
        Scanner sc = new Scanner(System.in);
        
        try {
            
            System.out.print("\n- Digite el id del familiar: ");
            int familiarId = sc.nextInt();
            
            Familiar familiar = familiarController.obtenerFamiliar(familiarId);
            
            if (familiar != null) {
                
                familiarController.eliminarFamiliar(familiarId);
                System.out.println("\n- Funcionario eliminado con éxito...\n");                
                
            } else {
                
                System.out.println("\n- El funcionario con id " + familiarId + " no existe...\n");
                
            }
            
        } catch(SQLException exception){
            
            exception.printStackTrace();
            
        }
    }    
        
    // operaciones funcionario
    
    public static void menuFuncionario() throws SQLException{
        
        
        int opt = -1;
        Scanner sc = new Scanner(System.in);
        FuncionarioController funcionarioController = new FuncionarioController();
        while (opt != 0) {
            
            // Menú de opciones
            System.out.println("""
                               - MENU FUNCIONARIOS
                                  1. Mostrar funcionarios
                                  2. Mostrar funcionario (id)
                                  3. Crear funcionario
                                  4. Eliminar funcionario
                                  5. Actualizar funcionario (b3t4)    
                                  0. Salir.
                                """);
            
            System.out.print("- Selección: ");
            opt = sc.nextInt();
            switch(opt) {
                
                case 0 -> System.out.println("- Cerrando interfaz...");
                case 1 -> obtenerFuncionarios(funcionarioController);
                case 2 -> obtenerFuncionario(funcionarioController);
                case 3 -> crearFuncionario(funcionarioController);                
                case 4 -> eliminarFuncionario(funcionarioController);     
                case 5 -> actualizarFuncionario(funcionarioController);
                default -> System.out.println("- Digite una opción válida...");
    
            }
            
        }           
    }     

    public static void obtenerFuncionario(FuncionarioController funcionarioController){
        
        Scanner sc = new Scanner(System.in);
        System.out.print("\n- Digite el id del funcionario: ");
        int funcionarioId = sc.nextInt();
                
        try {
            
            Funcionario funcionario = funcionarioController.obtenerFuncionario(funcionarioId);
            
            if (funcionario != null){
                System.out.println("");
                System.out.println("- ID: " + funcionario.getId() + "\n"
                    + "   - Tipo de identificación: " + funcionario.getTipoIdentificacion() + "\n"
                    + "   - Numero de identificación: " + funcionario.getNumeroIdentificacion() + "\n"                            
                    + "   - Nombre: " + funcionario.getNombres() + "\n"
                    + "   - Apellidos: " + funcionario.getApellidos() + "\n"
                    + "   - Estado civil: " + funcionario.getEstadoCivil() + "\n"                                    
                    + "   - Sexo: " + funcionario.getSexo() + "\n"
                    + "   - Direccion: " + funcionario.getDireccion() + "\n"
                    + "   - Teléfono: " + funcionario.getTelefono() + "\n"                                    
                    + "   - Fecha de nacimiento: " + funcionario.getFechaNacimiento() + "\n"
                    + "   - Información académica: " + funcionario.getInformacionAcademica() + "\n") ;        
                    
            } else {
                System.out.println("\n- El funcionario con id "+funcionarioId+" no existe...\n");
            }
            
        } catch(SQLException exception){
            
            exception.printStackTrace();
            
        }
        
    }

    public static void obtenerFuncionarios(FuncionarioController funcionarioController){
        
        try {
            List<Funcionario> funcionarios = funcionarioController.obtenerFuncionarios();
            // Validación sobre los registros existentes
            if (funcionarios.isEmpty()) {                
                System.out.println("\n- No hay registros...\n");                
            } else {
                // Mostrar registros existentes
                System.out.println("\n- Mostrando registros...\n");
                funcionarios.forEach(funcionario -> {
                    
                    System.out.println("- ID: " + funcionario.getId() + "\n"
                            + "   - Tipo de identificación: " + funcionario.getTipoIdentificacion() + "\n"
                            + "   - Numero de identificación: " + funcionario.getNumeroIdentificacion() + "\n"                            
                            + "   - Nombre: " + funcionario.getNombres() + "\n"
                            + "   - Apellidos: " + funcionario.getApellidos() + "\n"
                            + "   - Estado civil: " + funcionario.getEstadoCivil() + "\n"                                    
                            + "   - Sexo: " + funcionario.getSexo() + "\n"
                            + "   - Direccion: " + funcionario.getDireccion() + "\n"
                            + "   - Teléfono: " + funcionario.getTelefono() + "\n"                                    
                            + "   - Fecha de nacimiento: " + funcionario.getFechaNacimiento() + "\n"
                            + "   - Información académica: " + funcionario.getInformacionAcademica() + "\n") ;        
                    
                });
                
            }
            
        } catch(SQLException exception){
            exception.printStackTrace();
        }
        
    }
    
    public static void actualizarFuncionario(FuncionarioController funcionarioController) throws SQLException{
        
        Scanner sc = new Scanner(System.in); 
        
        try {
            
            System.out.print("\n- Digite el id del funcionario: ");
            int funcionarioId = sc.nextInt();
            
            Funcionario funcionario = funcionarioController.obtenerFuncionario(funcionarioId);            
            
            if (funcionario != null){
                
                // Atajar el enter
                sc.nextLine();

                // Recolección de datos            
                System.out.print("\n- Digite tipo de identificación: ");
                String tipoIdentificacionFuncionario = sc.nextLine();   

                System.out.print("- Digite número de identificación: ");
                String numeroIdentificacionFuncionario = sc.nextLine();              

                System.out.print("- Digite nombres: ");
                String nombreFuncionario = sc.nextLine();

                System.out.print("- Digite apellidos: ");
                String apellidoFuncionario = sc.nextLine();

                System.out.print("- Digite estado civil: ");
                String estadoCivilFuncionario = sc.nextLine();

                System.out.print("- Digite sexo: ");
                String sexoFuncionario = sc.nextLine();

                System.out.print("- Digite dirección: ");
                String direccionFuncionario = sc.nextLine();               

                System.out.print("- Digite número de teléfono: ");
                String telefonoFuncionario = sc.nextLine();

                System.out.print("- Digite fecha de nacimiento: ");
                String fechaNacimientoFuncionario = sc.nextLine();

                System.out.print("- Digite información académica: ");
                String informacionAcademicaFuncionario = sc.nextLine(); 

                System.out.println("\n- Funcionario actualizado con éxito...\n");

                // Instanciar Funcionario

                //Funcionario funcionario = new Funcionario();
                funcionario.setTipoIdentificacion(tipoIdentificacionFuncionario);
                funcionario.setNumeroIdentificacion(numeroIdentificacionFuncionario);
                funcionario.setNombres(nombreFuncionario);
                funcionario.setApellidos(apellidoFuncionario);
                funcionario.setEstadoCivil(estadoCivilFuncionario);
                funcionario.setSexo(sexoFuncionario);
                funcionario.setDireccion(direccionFuncionario);
                funcionario.setTelefono(telefonoFuncionario);
                funcionario.setFechaNacimiento(fechaNacimientoFuncionario);
                funcionario.setInformacionAcademica(informacionAcademicaFuncionario);            

                funcionarioController.actualizarFuncionario(funcionarioId, funcionario);                
                
            } else{
                
                System.out.println("\n- El funcionario con id " + funcionarioId + " no existe...\n");                
                
            }
            
        } catch(SQLException exception){
            
            exception.printStackTrace();
            
        }
    }
    
    public static void crearFuncionario(FuncionarioController funcionarioController){
        
        try {
            
            Scanner sc = new Scanner(System.in);
            
            // Recolección de datos            
            System.out.print("\n- Digite tipo de identificación: ");
            String tipoIdentificacionFuncionario = sc.nextLine();   
            
            System.out.print("- Digite número de identificación: ");
            String numeroIdentificacionFuncionario = sc.nextLine();              
            
            System.out.print("- Digite nombres: ");
            String nombreFuncionario = sc.nextLine();
            
            System.out.print("- Digite apellidos: ");
            String apellidoFuncionario = sc.nextLine();
            
            System.out.print("- Digite estado civil: ");
            String estadoCivilFuncionario = sc.nextLine();
            
            System.out.print("- Digite sexo: ");
            String sexoFuncionario = sc.nextLine();
            
            System.out.print("- Digite dirección: ");
            String direccionFuncionario = sc.nextLine();               
            
            System.out.print("- Digite número de teléfono: ");
            String telefonoFuncionario = sc.nextLine();
                                    
            System.out.print("- Digite fecha de nacimiento: ");
            String fechaNacimientoFuncionario = sc.nextLine();
            
            System.out.print("- Digite información académica: ");
            String informacionAcademicaFuncionario = sc.nextLine();  
            
            // Instanciar Funcionario
            
            Funcionario funcionario = new Funcionario();
            funcionario.setTipoIdentificacion(tipoIdentificacionFuncionario);
            funcionario.setNumeroIdentificacion(numeroIdentificacionFuncionario);
            funcionario.setNombres(nombreFuncionario);
            funcionario.setApellidos(apellidoFuncionario);
            funcionario.setEstadoCivil(estadoCivilFuncionario);
            funcionario.setSexo(sexoFuncionario);
            funcionario.setDireccion(direccionFuncionario);
            funcionario.setTelefono(telefonoFuncionario);
            funcionario.setFechaNacimiento(fechaNacimientoFuncionario);
            funcionario.setInformacionAcademica(informacionAcademicaFuncionario);
            
            funcionarioController.crearFuncionario(funcionario); 
            
            System.out.println("\n- Funcionario creado con éxito...\n");            
            
        } catch(SQLException exception){
            
            exception.printStackTrace();
            
        }
        
    }
    
    public static void eliminarFuncionario(FuncionarioController funcionarioController){
        
        Scanner sc = new Scanner(System.in);
        
        try {
            
            System.out.print("\n- Digite el id del funcionario: ");
            int funcionarioId = sc.nextInt();
            
            Funcionario funcionario = funcionarioController.obtenerFuncionario(funcionarioId);
            
            if (funcionario != null) {
                
                funcionarioController.eliminarFuncionario(funcionarioId);
                System.out.println("\n- Funcionario eliminado con éxito...\n");                
                
            } else {
                
                System.out.println("\n- El funcionario con id " + funcionarioId + " no existe...\n");
                
            }
            
        } catch(SQLException exception){
            
            exception.printStackTrace();
            
        }
    }
    
}