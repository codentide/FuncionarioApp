/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.iudigital.presentation;

import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.iudigital.controller.FamiliarController;
import com.iudigital.controller.FuncionarioController;
import com.iudigital.domain.Familiar;
import com.iudigital.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Main2 extends javax.swing.JFrame {
    
    private final FuncionarioController funcionarioController;
    private final static String[] COLUMNS = {"ID","TIPO ID","NUM. IDENTIFICACION",
        "NOMBRES","APELLIDOS","ESTADO CIVIL","SEXO","DIRECCION","NUM. TELEFONO",
        "FECHA NACIMIENTO","INFORMACION ACADÉMICA"};
    private final static String SELECCIONE = "Seleccione";
    
    private final FamiliarController familiarController;
    private final static String[] FAMILIAR_COLUMNS = {"ID", "FUNCIONARIO AFILIADO",
        "NOMBRES", "APELLIDOS", "ROL"};

    public Main2() throws SQLException {
        initComponents();
        
        txtFuncionarioIDEditar.setEditable(false);                        
        funcionarioController = new FuncionarioController();        
        
        txtFamiliarIDEditar.setEditable(false);
        txtFuncionarioIDFamiliarCrear.setEditable(false);
        familiarController = new FamiliarController();
        
        listFuncionarios();
        addListener();
           
        listFamiliares();
        addListenerCrearFamiliar();
        addListenerEditarFamiliar();
    }
    
    private void listFamiliares(){
        
        DefaultTableModel defaultTableModel = new DefaultTableModel();

        cbxFamiliarEditar.removeAllItems();
        Familiar familiarCbx = new Familiar();
        familiarCbx.setNombres(SELECCIONE);
        familiarCbx.setApellidos("");
        cbxFamiliarEditar.addItem(familiarCbx);
        
        for (String col : FAMILIAR_COLUMNS){
            defaultTableModel.addColumn(col);
        }
        
        tablaFamiliares.setModel(defaultTableModel);
        
        try {
            List<Familiar> familiares = familiarController.obtenerFamiliares();
            
            if (familiares.isEmpty()){
                return;
            }
            defaultTableModel.setRowCount(familiares.size());
            int row = 0;
            for (Familiar familiar: familiares){
                defaultTableModel.setValueAt(familiar.getId(), row, 0);
                defaultTableModel.setValueAt(familiar.getFuncionarioID(), row, 1);
                defaultTableModel.setValueAt(familiar.getNombres(), row, 2);
                defaultTableModel.setValueAt(familiar.getApellidos(), row, 3);
                defaultTableModel.setValueAt(familiar.getRol(), row, 4);   
                row++;       
                
                cbxFamiliarEditar.addItem(familiar);
            }

        } catch (SQLException ex) {
            
            ex.printStackTrace();
            
        }
        
    }   
    
    private void listFuncionarios(){
        
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        
        cbxFuncionarioFamiliarCrear.removeAllItems();
        cbxFuncionarioEditar.removeAllItems();
        
        Funcionario funcionarioCbx = new Funcionario();
        funcionarioCbx.setNombres(SELECCIONE);
        funcionarioCbx.setApellidos("");
        
        cbxFuncionarioFamiliarCrear.addItem(funcionarioCbx);
        cbxFuncionarioEditar.addItem(funcionarioCbx);

        
        for (String col : COLUMNS){           
            defaultTableModel.addColumn(col);
        }
        
        tablaFuncionario.setModel(defaultTableModel);
        
        try {            
            List<Funcionario> funcionarios = funcionarioController.obtenerFuncionarios();
            if (funcionarios.isEmpty()){                
                return;                
            }
            defaultTableModel.setRowCount(funcionarios.size());
            int row = 0;
            for (Funcionario funcionario: funcionarios){
                defaultTableModel.setValueAt(funcionario.getId(), row, 0);
                defaultTableModel.setValueAt(funcionario.getTipoIdentificacion(), row, 1);
                defaultTableModel.setValueAt(funcionario.getNumeroIdentificacion(), row, 2);
                defaultTableModel.setValueAt(funcionario.getNombres(), row, 3);
                defaultTableModel.setValueAt(funcionario.getApellidos(), row, 4);
                defaultTableModel.setValueAt(funcionario.getEstadoCivil(), row, 5);
                defaultTableModel.setValueAt(funcionario.getSexo(), row, 6);
                defaultTableModel.setValueAt(funcionario.getDireccion(), row, 7);
                defaultTableModel.setValueAt(funcionario.getTelefono(), row, 8);
                defaultTableModel.setValueAt(funcionario.getFechaNacimiento(), row, 9);
                defaultTableModel.setValueAt(funcionario.getInformacionAcademica(), row, 10);    
                row++;

                cbxFuncionarioEditar.addItem(funcionario);
                cbxFuncionarioFamiliarCrear.addItem(funcionario);
                
            }
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            
        }
    }
    
    private void addListenerCrearFamiliar(){
        
        cbxFuncionarioFamiliarCrear.addItemListener(event -> {
            
            Funcionario selectedFuncionario = (Funcionario) event.getItem();
            if (selectedFuncionario.getNombres().equals(SELECCIONE)) {
                
                txtFuncionarioIDFamiliarCrear.setText("");
                       
            } else {
               
                txtFuncionarioIDFamiliarCrear.setText(String.valueOf(selectedFuncionario.getId()));
                          
            }
        });          
    }
    
    private void addListenerEditarFamiliar(){
        
        cbxFamiliarEditar.addItemListener(event -> {
        
            Familiar selectedFamiliar = (Familiar) event.getItem();
            if (selectedFamiliar.getNombres().equals(SELECCIONE)) {
                           
                txtFamiliarIDEditar.setText("");
                txtFamiliarFuncionarioIDEditar.setText("");                
                txtNombreFamiliarEditar.setText("");
                txtApellidoFamiliarEditar.setText("");
                txtRolFamiliarEditar.setText("");  
                
            } else {
                
                txtFamiliarIDEditar.setText(String.valueOf(selectedFamiliar.getId()));
                txtFamiliarFuncionarioIDEditar.setText(String.valueOf(selectedFamiliar.getFuncionarioID()));
                txtNombreFamiliarEditar.setText(String.valueOf(selectedFamiliar.getNombres()));
                txtApellidoFamiliarEditar.setText(String.valueOf(selectedFamiliar.getApellidos()));
                txtRolFamiliarEditar.setText(String.valueOf(selectedFamiliar.getRol()));
                                
            }
        });
        
    }
    
    private void addListener() {
        
        cbxFuncionarioEditar.addItemListener(event -> {
            Funcionario selectedFuncionario = (Funcionario) event.getItem();
            if (selectedFuncionario.getNombres().equals(SELECCIONE)) {
                
                txtFuncionarioIDEditar.setText("");
                txtFuncionarioTipoIdentificacionEditar.setText("");
                txtNumeroIdentificacionFuncionarioEditar.setText("");
                txtNombreFuncionarioEditar.setText("");
                txtApellidoFuncionarioEditar.setText("");
                txtEstadoCivilFuncionarioEditar.setText("");
                txtSexoFuncionarioEditar.setText("");
                txtFechaNacimientoEditar.setText("");
                txtNumeroTelefonoFuncionarioEditar.setText(""); 
                txtDireccionFuncionarioEditar.setText("");
                txtFechaNacimientoEditar.setText("");
                txtInformacionAcademicaEditar.setText("");
                
            } else {
               
                txtFuncionarioIDEditar.setText(String.valueOf(selectedFuncionario.getId()));
                txtFuncionarioTipoIdentificacionEditar.setText(String.valueOf(selectedFuncionario.getTipoIdentificacion()));
                txtNumeroIdentificacionFuncionarioEditar.setText(String.valueOf(selectedFuncionario.getTipoIdentificacion()));
                txtNombreFuncionarioEditar.setText(String.valueOf(selectedFuncionario.getNombres()));
                txtApellidoFuncionarioEditar.setText(String.valueOf(selectedFuncionario.getApellidos()));
                txtEstadoCivilFuncionarioEditar.setText(String.valueOf(selectedFuncionario.getEstadoCivil()));
                txtSexoFuncionarioEditar.setText(String.valueOf(selectedFuncionario.getSexo()));
                txtFechaNacimientoEditar.setText(String.valueOf(selectedFuncionario.getFechaNacimiento()));
                txtNumeroTelefonoFuncionarioEditar.setText(String.valueOf(selectedFuncionario.getTelefono()));  
                txtDireccionFuncionarioEditar.setText(String.valueOf(selectedFuncionario.getDireccion()));
                txtFechaNacimientoEditar.setText(String.valueOf(selectedFuncionario.getFechaNacimiento()));                
                txtInformacionAcademicaEditar.setText(String.valueOf(selectedFuncionario.getInformacionAcademica()));
                
            }
        });                
    }
    

    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTPanels = new javax.swing.JTabbedPane();
        jPanelFuncionarios = new javax.swing.JPanel();
        jPanelTablaFuncionario = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaFuncionario = new javax.swing.JTable();
        jPanelCrearFuncionario = new javax.swing.JPanel();
        jPanelFormularioCrearFuncionario = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtFuncionarioTipoIdentificacion = new javax.swing.JTextField();
        txtNumeroIdentificacionFuncionario = new javax.swing.JTextField();
        txtNombreFuncionario = new javax.swing.JTextField();
        txtApellidoFuncionario = new javax.swing.JTextField();
        txtEstadoCivilFuncionario = new javax.swing.JTextField();
        txtSexoFuncionario = new javax.swing.JTextField();
        txtFechaNacimiento = new javax.swing.JTextField();
        txtNumeroTelefonoFuncionario = new javax.swing.JTextField();
        txtDireccionFuncionario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInformacionAcademica = new javax.swing.JTextArea();
        btnCrearFuncionario = new javax.swing.JButton();
        jPanelEditarFuncionario = new javax.swing.JPanel();
        jPanelFormularioEditarFuncionario = new javax.swing.JPanel();
        cbxFuncionarioEditar = new javax.swing.JComboBox<>();
        txtFuncionarioEditar = new javax.swing.JLabel();
        txtTipoIdentificacionEditar = new javax.swing.JLabel();
        txtNombreEditar = new javax.swing.JLabel();
        txtEstadoCivilEditar = new javax.swing.JLabel();
        txtNacimientoEditar = new javax.swing.JLabel();
        txtFechaNacimientoEditar = new javax.swing.JTextField();
        txtEstadoCivilFuncionarioEditar = new javax.swing.JTextField();
        txtNombreFuncionarioEditar = new javax.swing.JTextField();
        txtFuncionarioTipoIdentificacionEditar = new javax.swing.JTextField();
        NumeroIdentificacionFuncionarioEditar = new javax.swing.JLabel();
        txtNumeroIdentificacionFuncionarioEditar = new javax.swing.JTextField();
        apellidoFuncionarioEditar = new javax.swing.JLabel();
        txtApellidoFuncionarioEditar = new javax.swing.JTextField();
        txtSexoEditar = new javax.swing.JLabel();
        txtSexoFuncionarioEditar = new javax.swing.JTextField();
        NumeroTelefonoFuncionarioEditar = new javax.swing.JLabel();
        txtNumeroTelefonoFuncionarioEditar = new javax.swing.JTextField();
        direccionFuncionarioEditar = new javax.swing.JLabel();
        txtDireccionFuncionarioEditar = new javax.swing.JTextField();
        txtInformacionAcademicaFuncionarioEditar = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtInformacionAcademicaEditar = new javax.swing.JTextArea();
        btnEliminarFuncionario = new javax.swing.JButton();
        btnActualizarFuncionario = new javax.swing.JButton();
        txtFuncionarioIDEditar = new javax.swing.JTextField();
        txtFuncionarioEditar1 = new javax.swing.JLabel();
        jPanelFamiliares = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblFamiliarCrear = new javax.swing.JLabel();
        cbxFuncionarioFamiliarCrear = new javax.swing.JComboBox<>();
        txtFuncionarioIDFamiliarCrear = new javax.swing.JTextField();
        lblFuncionarioFamiliarCrear = new javax.swing.JLabel();
        lblNombreFamiliar = new javax.swing.JLabel();
        txtNombreFamiliar = new javax.swing.JTextField();
        lblApellidoFamiliar = new javax.swing.JLabel();
        txtApellidoFamiliar = new javax.swing.JTextField();
        lblRolFamiliar = new javax.swing.JLabel();
        txtRolFamiliar = new javax.swing.JTextField();
        btnCrearFamiliar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaFamiliares = new javax.swing.JTable();
        jPanelFamiliares1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblFamiliarEditar = new javax.swing.JLabel();
        cbxFamiliarEditar = new javax.swing.JComboBox<>();
        txtFamiliarIDEditar = new javax.swing.JTextField();
        lblFamiliarIFuncionarioIDEditar = new javax.swing.JLabel();
        lblNombreFamiliarEditar = new javax.swing.JLabel();
        txtNombreFamiliarEditar = new javax.swing.JTextField();
        lblApellidoFamiliarEditar = new javax.swing.JLabel();
        txtApellidoFamiliarEditar = new javax.swing.JTextField();
        lblRolFamiliarEditar = new javax.swing.JLabel();
        txtRolFamiliarEditar = new javax.swing.JTextField();
        btnActualizarFamiliar = new javax.swing.JButton();
        btnEliminarFamiliar = new javax.swing.JButton();
        txtFamiliarFuncionarioIDEditar = new javax.swing.JTextField();
        lblFamiliarIDEditar1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("FUNCIONARIO APP");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        tablaFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tablaFuncionario);

        javax.swing.GroupLayout jPanelTablaFuncionarioLayout = new javax.swing.GroupLayout(jPanelTablaFuncionario);
        jPanelTablaFuncionario.setLayout(jPanelTablaFuncionarioLayout);
        jPanelTablaFuncionarioLayout.setHorizontalGroup(
            jPanelTablaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTablaFuncionarioLayout.setVerticalGroup(
            jPanelTablaFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelFuncionariosLayout = new javax.swing.GroupLayout(jPanelFuncionarios);
        jPanelFuncionarios.setLayout(jPanelFuncionariosLayout);
        jPanelFuncionariosLayout.setHorizontalGroup(
            jPanelFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTablaFuncionario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelFuncionariosLayout.setVerticalGroup(
            jPanelFuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFuncionariosLayout.createSequentialGroup()
                .addComponent(jPanelTablaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 68, Short.MAX_VALUE))
        );

        jTPanels.addTab("Funcionarios", jPanelFuncionarios);

        jPanelFormularioCrearFuncionario.setBorder(javax.swing.BorderFactory.createTitledBorder("Rellene el siguiente formulario"));

        jLabel2.setText("Tipo de Identificación");

        jLabel3.setText("Num. Identificación");

        jLabel4.setText("Nombres");

        jLabel5.setText("Apellidos");

        jLabel6.setText("Estado Civil");

        jLabel7.setText("Sexo");

        jLabel8.setText("Dirección");

        jLabel9.setText("Num. Teléfono");

        jLabel10.setText("Nacimiento");

        jLabel11.setText("Información Académica");

        txtFuncionarioTipoIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFuncionarioTipoIdentificacionActionPerformed(evt);
            }
        });

        txtNombreFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreFuncionarioActionPerformed(evt);
            }
        });

        txtDireccionFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionFuncionarioActionPerformed(evt);
            }
        });

        txtInformacionAcademica.setColumns(20);
        txtInformacionAcademica.setRows(5);
        jScrollPane1.setViewportView(txtInformacionAcademica);

        btnCrearFuncionario.setText("Crear Funcionario");
        btnCrearFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFuncionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormularioCrearFuncionarioLayout = new javax.swing.GroupLayout(jPanelFormularioCrearFuncionario);
        jPanelFormularioCrearFuncionario.setLayout(jPanelFormularioCrearFuncionarioLayout);
        jPanelFormularioCrearFuncionarioLayout.setHorizontalGroup(
            jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                                .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNumeroIdentificacionFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtFuncionarioTipoIdentificacion))
                                .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)))
                                    .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(txtNombreFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(txtApellidoFuncionario))))
                            .addComponent(jLabel3))
                        .addGap(16, 16, 16)
                        .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSexoFuncionario)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEstadoCivilFuncionario)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaNacimiento)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(txtNumeroTelefonoFuncionario)))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(btnCrearFuncionario))
                        .addGap(0, 0, 0))
                    .addComponent(txtDireccionFuncionario))
                .addGap(16, 16, 16))
        );
        jPanelFormularioCrearFuncionarioLayout.setVerticalGroup(
            jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                            .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(8, 8, 8)
                                    .addComponent(txtFuncionarioTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(8, 8, 8)
                                    .addComponent(txtNombreFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(8, 8, 8)
                            .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5))
                            .addGap(8, 8, 8)
                            .addGroup(jPanelFormularioCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNumeroIdentificacionFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtApellidoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(8, 8, 8)
                            .addComponent(txtEstadoCivilFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8)
                            .addComponent(jLabel7)
                            .addGap(8, 8, 8)
                            .addComponent(txtSexoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelFormularioCrearFuncionarioLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(8, 8, 8)
                        .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel9)
                        .addGap(8, 8, 8)
                        .addComponent(txtNumeroTelefonoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(jLabel8)
                .addGap(8, 8, 8)
                .addComponent(txtDireccionFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel11)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btnCrearFuncionario)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelCrearFuncionarioLayout = new javax.swing.GroupLayout(jPanelCrearFuncionario);
        jPanelCrearFuncionario.setLayout(jPanelCrearFuncionarioLayout);
        jPanelCrearFuncionarioLayout.setHorizontalGroup(
            jPanelCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrearFuncionarioLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jPanelFormularioCrearFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        jPanelCrearFuncionarioLayout.setVerticalGroup(
            jPanelCrearFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCrearFuncionarioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanelFormularioCrearFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTPanels.addTab("Crear Funcionario", jPanelCrearFuncionario);

        jPanelFormularioEditarFuncionario.setBorder(javax.swing.BorderFactory.createTitledBorder("Modifique los campos del formulario"));

        txtFuncionarioEditar.setText("Funcionario");

        txtTipoIdentificacionEditar.setText("Tipo de Identificación");

        txtNombreEditar.setText("Nombres");

        txtEstadoCivilEditar.setText("Estado Civil");

        txtNacimientoEditar.setText("Nacimiento");

        txtNombreFuncionarioEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreFuncionarioEditarActionPerformed(evt);
            }
        });

        txtFuncionarioTipoIdentificacionEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFuncionarioTipoIdentificacionEditarActionPerformed(evt);
            }
        });

        NumeroIdentificacionFuncionarioEditar.setText("Num. Identificación");

        apellidoFuncionarioEditar.setText("Apellidos");

        txtSexoEditar.setText("Sexo");

        txtSexoFuncionarioEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSexoFuncionarioEditarActionPerformed(evt);
            }
        });

        NumeroTelefonoFuncionarioEditar.setText("Num. Teléfono");

        direccionFuncionarioEditar.setText("Dirección");

        txtDireccionFuncionarioEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionFuncionarioEditarActionPerformed(evt);
            }
        });

        txtInformacionAcademicaFuncionarioEditar.setText("Información Académica");

        txtInformacionAcademicaEditar.setColumns(20);
        txtInformacionAcademicaEditar.setRows(5);
        jScrollPane2.setViewportView(txtInformacionAcademicaEditar);

        btnEliminarFuncionario.setText("Eliminar Funcionario");
        btnEliminarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFuncionarioActionPerformed(evt);
            }
        });

        btnActualizarFuncionario.setText("Actualizar Funcionario");
        btnActualizarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarFuncionarioActionPerformed(evt);
            }
        });

        txtFuncionarioIDEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFuncionarioIDEditarActionPerformed(evt);
            }
        });

        txtFuncionarioEditar1.setText("ID");

        javax.swing.GroupLayout jPanelFormularioEditarFuncionarioLayout = new javax.swing.GroupLayout(jPanelFormularioEditarFuncionario);
        jPanelFormularioEditarFuncionario.setLayout(jPanelFormularioEditarFuncionarioLayout);
        jPanelFormularioEditarFuncionarioLayout.setHorizontalGroup(
            jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                        .addComponent(cbxFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFuncionarioEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                .addComponent(txtFuncionarioIDEditar)
                                .addGap(16, 16, 16))))
                    .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                        .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtNumeroIdentificacionFuncionarioEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                            .addComponent(txtTipoIdentificacionEditar, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFuncionarioTipoIdentificacionEditar))
                                        .addGap(16, 16, 16)
                                        .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtApellidoFuncionarioEditar)
                                            .addComponent(txtNombreFuncionarioEditar)
                                            .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                                .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(apellidoFuncionarioEditar)
                                                    .addComponent(txtNombreEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, 0))))
                                    .addComponent(NumeroIdentificacionFuncionarioEditar))
                                .addGap(16, 16, 16)
                                .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                        .addComponent(txtEstadoCivilEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(27, 27, 27)
                                        .addComponent(txtNacimientoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                        .addComponent(txtSexoEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(NumeroTelefonoFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                        .addComponent(txtEstadoCivilFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFechaNacimientoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(txtSexoFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(txtNumeroTelefonoFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                .addComponent(btnActualizarFuncionario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminarFuncionario))
                            .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                                    .addComponent(direccionFuncionarioEditar, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtInformacionAcademicaFuncionarioEditar, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDireccionFuncionarioEditar, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFuncionarioEditar, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(16, 16, 16))))
        );
        jPanelFormularioEditarFuncionarioLayout.setVerticalGroup(
            jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuncionarioEditar)
                    .addComponent(txtFuncionarioEditar1))
                .addGap(8, 8, 8)
                .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuncionarioIDEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                .addComponent(txtTipoIdentificacionEditar)
                                .addGap(8, 8, 8)
                                .addComponent(txtFuncionarioTipoIdentificacionEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                                .addComponent(txtNombreEditar)
                                .addGap(8, 8, 8)
                                .addComponent(txtNombreFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NumeroIdentificacionFuncionarioEditar)
                            .addComponent(apellidoFuncionarioEditar))
                        .addGap(8, 8, 8)
                        .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumeroIdentificacionFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidoFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelFormularioEditarFuncionarioLayout.createSequentialGroup()
                        .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEstadoCivilEditar)
                            .addComponent(txtNacimientoEditar))
                        .addGap(8, 8, 8)
                        .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFechaNacimientoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstadoCivilFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSexoEditar)
                            .addComponent(NumeroTelefonoFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSexoFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumeroTelefonoFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addComponent(direccionFuncionarioEditar)
                .addGap(10, 10, 10)
                .addComponent(txtDireccionFuncionarioEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtInformacionAcademicaFuncionarioEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanelFormularioEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizarFuncionario)
                    .addComponent(btnEliminarFuncionario))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelEditarFuncionarioLayout = new javax.swing.GroupLayout(jPanelEditarFuncionario);
        jPanelEditarFuncionario.setLayout(jPanelEditarFuncionarioLayout);
        jPanelEditarFuncionarioLayout.setHorizontalGroup(
            jPanelEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditarFuncionarioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanelFormularioEditarFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        jPanelEditarFuncionarioLayout.setVerticalGroup(
            jPanelEditarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditarFuncionarioLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanelFormularioEditarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTPanels.addTab("Editar Funcionario", jPanelEditarFuncionario);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Rellena el formulario"));

        lblFamiliarCrear.setText("Funcionario");

        cbxFuncionarioFamiliarCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFuncionarioFamiliarCrearActionPerformed(evt);
            }
        });

        txtFuncionarioIDFamiliarCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFuncionarioIDFamiliarCrearActionPerformed(evt);
            }
        });

        lblFuncionarioFamiliarCrear.setText("ID");

        lblNombreFamiliar.setText("Nombres");

        txtNombreFamiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreFamiliarActionPerformed(evt);
            }
        });

        lblApellidoFamiliar.setText("Apellidos");

        lblRolFamiliar.setText("Rol");

        btnCrearFamiliar.setText("Crear Familiar");
        btnCrearFamiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearFamiliarActionPerformed(evt);
            }
        });

        tablaFamiliares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tablaFamiliares);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCrearFamiliar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFamiliarCrear)
                                    .addComponent(cbxFuncionarioFamiliarCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFuncionarioFamiliarCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFuncionarioIDFamiliarCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblNombreFamiliar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNombreFamiliar, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtApellidoFamiliar, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                    .addComponent(lblApellidoFamiliar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRolFamiliar)
                                    .addComponent(lblRolFamiliar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(16, 16, 16))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFamiliarCrear)
                    .addComponent(lblFuncionarioFamiliarCrear))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuncionarioIDFamiliarCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxFuncionarioFamiliarCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNombreFamiliar)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidoFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRolFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblApellidoFamiliar)
                        .addComponent(lblRolFamiliar)))
                .addGap(16, 16, 16)
                .addComponent(btnCrearFamiliar)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelFamiliaresLayout = new javax.swing.GroupLayout(jPanelFamiliares);
        jPanelFamiliares.setLayout(jPanelFamiliaresLayout);
        jPanelFamiliaresLayout.setHorizontalGroup(
            jPanelFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFamiliaresLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanelFamiliaresLayout.setVerticalGroup(
            jPanelFamiliaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFamiliaresLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTPanels.addTab("Crear Familiar", jPanelFamiliares);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Rellena el formulario"));

        lblFamiliarEditar.setText("Familiar");

        cbxFamiliarEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFamiliarEditarActionPerformed(evt);
            }
        });

        txtFamiliarIDEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFamiliarIDEditarActionPerformed(evt);
            }
        });

        lblFamiliarIFuncionarioIDEditar.setText("Funcionario ID");

        lblNombreFamiliarEditar.setText("Nombres");

        txtNombreFamiliarEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreFamiliarEditarActionPerformed(evt);
            }
        });

        lblApellidoFamiliarEditar.setText("Apellidos");

        lblRolFamiliarEditar.setText("Rol");

        btnActualizarFamiliar.setText("Actualizar Familiar");
        btnActualizarFamiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarFamiliarActionPerformed(evt);
            }
        });

        btnEliminarFamiliar.setText("Eliminar Familiar");
        btnEliminarFamiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFamiliarActionPerformed(evt);
            }
        });

        txtFamiliarFuncionarioIDEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFamiliarFuncionarioIDEditarActionPerformed(evt);
            }
        });

        lblFamiliarIDEditar1.setText("ID");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnActualizarFamiliar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarFamiliar)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFamiliarEditar)
                            .addComponent(cbxFamiliarEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFamiliarIDEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFamiliarIDEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreFamiliarEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(lblNombreFamiliarEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtApellidoFamiliarEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(lblApellidoFamiliarEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRolFamiliarEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(lblRolFamiliarEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(lblFamiliarIFuncionarioIDEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(txtFamiliarFuncionarioIDEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFamiliarEditar)
                    .addComponent(lblFamiliarIDEditar1))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFamiliarIDEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxFamiliarEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNombreFamiliarEditar)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreFamiliarEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidoFamiliarEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRolFamiliarEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFamiliarFuncionarioIDEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblApellidoFamiliarEditar)
                        .addComponent(lblRolFamiliarEditar)
                        .addComponent(lblFamiliarIFuncionarioIDEditar)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnActualizarFamiliar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnEliminarFamiliar)))
                .addContainerGap(258, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelFamiliares1Layout = new javax.swing.GroupLayout(jPanelFamiliares1);
        jPanelFamiliares1.setLayout(jPanelFamiliares1Layout);
        jPanelFamiliares1Layout.setHorizontalGroup(
            jPanelFamiliares1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFamiliares1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        jPanelFamiliares1Layout.setVerticalGroup(
            jPanelFamiliares1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFamiliares1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTPanels.addTab("Editar Familiar", jPanelFamiliares1);

        getContentPane().add(jTPanels, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 790, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreFuncionarioActionPerformed

    private void txtFuncionarioTipoIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFuncionarioTipoIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuncionarioTipoIdentificacionActionPerformed

    private void btnCrearFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearFuncionarioActionPerformed
                
        try {
            // Validar TXTs
            if (txtFuncionarioTipoIdentificacion.getText().trim().length() == 0){
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Tipo de identifiación");
                txtFuncionarioTipoIdentificacion.requestFocus();            
                return;
            }
            if (txtNumeroIdentificacionFuncionario.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Num. Identificación");
                txtNumeroIdentificacionFuncionario.requestFocus();
                return;
            }
            if (txtNombreFuncionario.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Nombres");
                txtNombreFuncionario.requestFocus();
                return;
            }
            if (txtApellidoFuncionario.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Apellidos");
                txtApellidoFuncionario.requestFocus();
                return;
            }
            if (txtEstadoCivilFuncionario.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Estado civil");
                txtEstadoCivilFuncionario.requestFocus();
                return;
            }
            if (txtSexoFuncionario.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Sexo");
                txtSexoFuncionario.requestFocus();
                return;
            }
            if (txtFechaNacimiento.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Fecha de nacimiento");
                txtFechaNacimiento.requestFocus();
                return;
            }
            if (txtNumeroTelefonoFuncionario.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Número de teléfono");
                txtNumeroTelefonoFuncionario.requestFocus();
                return;
            }
            if (txtDireccionFuncionario.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Dirección");
                txtDireccionFuncionario.requestFocus();
                return;
            }
            if (txtInformacionAcademica.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Información académica");
                txtInformacionAcademica.requestFocus();
                return;
            }
            
            Funcionario funcionario = new Funcionario();
            funcionario.setTipoIdentificacion(txtFuncionarioTipoIdentificacion.getText().trim());
            funcionario.setNumeroIdentificacion(txtNumeroIdentificacionFuncionario.getText().trim());
            funcionario.setNombres(txtNombreFuncionario.getText().trim());
            funcionario.setApellidos(txtApellidoFuncionario.getText().trim());
            funcionario.setEstadoCivil(txtEstadoCivilFuncionario.getText().trim());
            funcionario.setSexo(txtSexoFuncionario.getText().trim());
            funcionario.setFechaNacimiento(txtFechaNacimiento.getText().trim());
            funcionario.setTelefono(txtNumeroTelefonoFuncionario.getText().trim());
            funcionario.setDireccion(txtDireccionFuncionario.getText().trim());
            funcionario.setInformacionAcademica(txtInformacionAcademica.getText().trim());
            
            funcionarioController.crearFuncionario(funcionario);
                        
            txtFuncionarioTipoIdentificacion.setText("");            
            txtNumeroIdentificacionFuncionario.setText("");
            txtNombreFuncionario.setText("");
            txtApellidoFuncionario.setText("");
            txtEstadoCivilFuncionario.setText("");
            txtSexoFuncionario.setText("");
            txtFechaNacimiento.setText("");
            txtNumeroTelefonoFuncionario.setText("");
            txtDireccionFuncionario.setText("");
            txtInformacionAcademica.setText("");
            
            listFuncionarios();
            
            JOptionPane.showMessageDialog(null, "Funcionario creado con éxito");
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear funcionario");            
        }
        
    }//GEN-LAST:event_btnCrearFuncionarioActionPerformed

    private void txtDireccionFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionFuncionarioActionPerformed

    private void txtNombreFuncionarioEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreFuncionarioEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreFuncionarioEditarActionPerformed

    private void txtFuncionarioTipoIdentificacionEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFuncionarioTipoIdentificacionEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuncionarioTipoIdentificacionEditarActionPerformed

    private void txtDireccionFuncionarioEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionFuncionarioEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionFuncionarioEditarActionPerformed

    private void btnEliminarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFuncionarioActionPerformed
   
        if (txtFuncionarioIDEditar.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Escoja un funcionario de la lista");
            txtFuncionarioIDEditar.requestFocus();            
            return;
        }         
        
        int opt = JOptionPane.showConfirmDialog(null, "Desea continuar con la eliminación?",
                "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opt == 0) {
            try {
                funcionarioController.eliminarFuncionario(Integer.parseInt(txtFuncionarioIDEditar.getText().trim()));
                txtFuncionarioTipoIdentificacion.setText("");
                txtNumeroIdentificacionFuncionario.setText("");
                txtNombreFuncionario.setText("");
                txtApellidoFuncionario.setText("");
                txtEstadoCivilFuncionario.setText("");
                txtSexoFuncionario.setText("");
                txtFechaNacimiento.setText("");
                txtNumeroTelefonoFuncionario.setText("");
                txtDireccionFuncionario.setText("");
                txtInformacionAcademica.setText("");

                listFuncionarios();

                JOptionPane.showMessageDialog(null, "Funcionario eliminado con éxito");

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar funcionario");
            }
            
        }        
        
    }//GEN-LAST:event_btnEliminarFuncionarioActionPerformed

    private void btnActualizarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarFuncionarioActionPerformed
        
        if (txtFuncionarioIDEditar.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Escoja un funcionario de la lista");
            txtFuncionarioIDEditar.requestFocus();            
            return;
        }            
        if (txtFuncionarioTipoIdentificacionEditar.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Rellene el campo " + "Tipo de identifiación");
            txtFuncionarioTipoIdentificacionEditar.requestFocus();            
            return;
        }
        if (txtNumeroIdentificacionFuncionarioEditar.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Rellene el campo " + "Num. Identificación");
            txtNumeroIdentificacionFuncionarioEditar.requestFocus();
            return;
        }
        if (txtNombreFuncionarioEditar.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Rellene el campo " + "Nombres");
            txtNombreFuncionarioEditar.requestFocus();
            return;
        }
        if (txtApellidoFuncionarioEditar.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Rellene el campo " + "Apellidos");
            txtApellidoFuncionarioEditar.requestFocus();
            return;
        }
        if (txtEstadoCivilFuncionarioEditar.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Rellene el campo " + "Estado civil");
            txtEstadoCivilFuncionario.requestFocus();
            return;
        }
        if (txtSexoFuncionarioEditar.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Rellene el campo " + "Sexo");
            txtSexoFuncionarioEditar.requestFocus();
            return;
        }
        if (txtFechaNacimientoEditar.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Rellene el campo " + "Fecha de nacimiento");
            txtFechaNacimientoEditar.requestFocus();
            return;
        }
        if (txtNumeroTelefonoFuncionarioEditar.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Rellene el campo " + "Número de teléfono");
            txtNumeroTelefonoFuncionarioEditar.requestFocus();
            return;
        }
        if (txtDireccionFuncionarioEditar.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Rellene el campo " + "Dirección");
            txtDireccionFuncionarioEditar.requestFocus();
            return;
        }
        if (txtInformacionAcademicaEditar.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Rellene el campo " + "Información académica");
            txtInformacionAcademicaEditar.requestFocus();
            return;
        }

        Funcionario funcionario = new Funcionario();
        // funcionario.setId(Integer.parseInt(txtFuncionarioIDEditar.getText().trim()));    
        funcionario.setTipoIdentificacion(txtFuncionarioTipoIdentificacionEditar.getText().trim());
        funcionario.setNumeroIdentificacion(txtNumeroIdentificacionFuncionarioEditar.getText().trim());
        funcionario.setNombres(txtNombreFuncionarioEditar.getText().trim());
        funcionario.setApellidos(txtApellidoFuncionarioEditar.getText().trim());
        funcionario.setEstadoCivil(txtEstadoCivilFuncionarioEditar.getText().trim());
        funcionario.setSexo(txtSexoFuncionarioEditar.getText().trim());
        funcionario.setFechaNacimiento(txtFechaNacimientoEditar.getText().trim());
        funcionario.setTelefono(txtNumeroTelefonoFuncionarioEditar.getText().trim());
        funcionario.setDireccion(txtDireccionFuncionarioEditar.getText().trim());
        funcionario.setInformacionAcademica(txtInformacionAcademicaEditar.getText().trim());

        int opt = JOptionPane.showConfirmDialog(null, "Desea continuar con la actualización?",
                "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opt == 0) {
            try {
                funcionarioController.actualizarFuncionario(Integer.parseInt(txtFuncionarioIDEditar.getText().trim()), funcionario);
                txtFuncionarioTipoIdentificacion.setText("");
                txtNumeroIdentificacionFuncionario.setText("");
                txtNombreFuncionario.setText("");
                txtApellidoFuncionario.setText("");
                txtEstadoCivilFuncionario.setText("");
                txtSexoFuncionario.setText("");
                txtFechaNacimiento.setText("");
                txtNumeroTelefonoFuncionario.setText("");
                txtDireccionFuncionario.setText("");
                txtInformacionAcademica.setText("");

                listFuncionarios();

                JOptionPane.showMessageDialog(null, "Funcionario actualizado con éxito");

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al actualizar funcionario");
            }
        }

    }//GEN-LAST:event_btnActualizarFuncionarioActionPerformed

    private void txtFuncionarioIDEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFuncionarioIDEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuncionarioIDEditarActionPerformed

    private void txtSexoFuncionarioEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSexoFuncionarioEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoFuncionarioEditarActionPerformed

    private void txtFuncionarioIDFamiliarCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFuncionarioIDFamiliarCrearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuncionarioIDFamiliarCrearActionPerformed

    private void cbxFuncionarioFamiliarCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFuncionarioFamiliarCrearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFuncionarioFamiliarCrearActionPerformed

    private void txtNombreFamiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreFamiliarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreFamiliarActionPerformed

    private void btnCrearFamiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearFamiliarActionPerformed
        
        try {
            // Validar TXTs
            if (txtFuncionarioIDFamiliarCrear.getText().trim().length() == 0){
                JOptionPane.showMessageDialog(null, "Escoja un funcionario de la lista");
                txtFuncionarioIDFamiliarCrear.requestFocus();            
                return;
            }            
            if (txtNombreFamiliar.getText().trim().length() == 0){
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Nombres");
                txtNombreFamiliar.requestFocus();            
                return;
            }
            if (txtApellidoFamiliar.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Apellidos");
                txtApellidoFamiliar.requestFocus();
                return;
            }
            if (txtRolFamiliar.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Rellene el campo " + "Rol");
                txtRolFamiliar.requestFocus();
                return;
            }
            
            Familiar familiar = new Familiar();
            familiar.setFuncionarioID(Integer.parseInt(txtFuncionarioIDFamiliarCrear.getText().trim()));
            familiar.setNombres(txtNombreFamiliar.getText().trim());
            familiar.setApellidos(txtApellidoFamiliar.getText().trim());
            familiar.setRol(txtRolFamiliar.getText().trim());           
            
            familiarController.crearFamiliar(familiar);
                        
            txtNombreFamiliar.setText("");            
            txtApellidoFamiliar.setText("");
            txtRolFamiliar.setText("");
            
            listFamiliares();
            
            JOptionPane.showMessageDialog(null, "Familiar creado con éxito");
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear familiar");            
        }       
        
        
        
    }//GEN-LAST:event_btnCrearFamiliarActionPerformed

    private void cbxFamiliarEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFamiliarEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFamiliarEditarActionPerformed

    private void txtFamiliarIDEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFamiliarIDEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFamiliarIDEditarActionPerformed

    private void txtNombreFamiliarEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreFamiliarEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreFamiliarEditarActionPerformed

    private void btnActualizarFamiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarFamiliarActionPerformed
        
        if (txtFamiliarIDEditar.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Escoja un familiar de la lista");
            txtFamiliarIDEditar.requestFocus();            
            return;
        } 
        if (txtFamiliarFuncionarioIDEditar.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Escoja un familiar de la lista");
            txtFamiliarFuncionarioIDEditar.requestFocus();
            return;
        }
        if (txtNombreFamiliarEditar.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Rellene el campo Nombre");
            txtNombreFamiliarEditar.requestFocus();            
            return;
        }    
        if (txtApellidoFamiliarEditar.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Rellene el campo Apellido");
            txtApellidoFamiliarEditar.requestFocus();            
            return;
        }    
        if (txtRolFamiliarEditar.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Rellene el campo Rol");
            txtRolFamiliarEditar.requestFocus();            
            return;
        }   
        
        Familiar familiar = new Familiar();
        
        familiar.setFuncionarioID(Integer.parseInt(txtFamiliarFuncionarioIDEditar.getText().trim()));
        familiar.setNombres(txtNombreFamiliarEditar.getText().trim());
        familiar.setApellidos(txtApellidoFamiliarEditar.getText().trim());
        familiar.setRol(txtRolFamiliarEditar.getText().trim());

        int opt = JOptionPane.showConfirmDialog(null, "Desea continuar con la actualización?",
                "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);        
        
        if (opt == 0) {
            try {
                familiarController.actualizarFamiliar(Integer.parseInt(txtFamiliarIDEditar.getText().trim()), familiar);
                
                txtNombreFamiliarEditar.setText("");
                txtApellidoFamiliarEditar.setText("");
                txtRolFamiliarEditar.setText("");

                listFamiliares();

                JOptionPane.showMessageDialog(null, "Familiar actualizado con éxito");

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al actualizar familiar");
            }
        }        
        
    }//GEN-LAST:event_btnActualizarFamiliarActionPerformed

    private void btnEliminarFamiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFamiliarActionPerformed
                
        if (txtFamiliarIDEditar.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Escoja un funcionario de la lista");
            txtFamiliarIDEditar.requestFocus();            
            return;
        }             
        
        int opt = JOptionPane.showConfirmDialog(null, "Desea continuar con la eliminación?",
                "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);        
        
        if (opt == 0) {
            try {
                familiarController.eliminarFamiliar(Integer.parseInt(txtFamiliarIDEditar.getText().trim()));
                txtNombreFamiliarEditar.setText("");
                txtApellidoFamiliarEditar.setText("");
                txtRolFamiliarEditar.setText("");

                listFamiliares();

                JOptionPane.showMessageDialog(null, "Familiar eliminado con éxito");

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar familiar");
            }
        }          
    }//GEN-LAST:event_btnEliminarFamiliarActionPerformed

    private void txtFamiliarFuncionarioIDEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFamiliarFuncionarioIDEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFamiliarFuncionarioIDEditarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            // UIManager.setLookAndFeel(new FlatLightLaf());
            FlatMonokaiProIJTheme.setup();
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
/*       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Window".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
*/      //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main2().setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NumeroIdentificacionFuncionarioEditar;
    private javax.swing.JLabel NumeroTelefonoFuncionarioEditar;
    private javax.swing.JLabel apellidoFuncionarioEditar;
    private javax.swing.JButton btnActualizarFamiliar;
    private javax.swing.JButton btnActualizarFuncionario;
    private javax.swing.JButton btnCrearFamiliar;
    private javax.swing.JButton btnCrearFuncionario;
    private javax.swing.JButton btnEliminarFamiliar;
    private javax.swing.JButton btnEliminarFuncionario;
    private javax.swing.JComboBox<Familiar> cbxFamiliarEditar;
    private javax.swing.JComboBox<Funcionario> cbxFuncionarioEditar;
    private javax.swing.JComboBox<Funcionario> cbxFuncionarioFamiliarCrear;
    private javax.swing.JLabel direccionFuncionarioEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelCrearFuncionario;
    private javax.swing.JPanel jPanelEditarFuncionario;
    private javax.swing.JPanel jPanelFamiliares;
    private javax.swing.JPanel jPanelFamiliares1;
    private javax.swing.JPanel jPanelFormularioCrearFuncionario;
    private javax.swing.JPanel jPanelFormularioEditarFuncionario;
    private javax.swing.JPanel jPanelFuncionarios;
    private javax.swing.JPanel jPanelTablaFuncionario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTPanels;
    private javax.swing.JLabel lblApellidoFamiliar;
    private javax.swing.JLabel lblApellidoFamiliarEditar;
    private javax.swing.JLabel lblFamiliarCrear;
    private javax.swing.JLabel lblFamiliarEditar;
    private javax.swing.JLabel lblFamiliarIDEditar1;
    private javax.swing.JLabel lblFamiliarIFuncionarioIDEditar;
    private javax.swing.JLabel lblFuncionarioFamiliarCrear;
    private javax.swing.JLabel lblNombreFamiliar;
    private javax.swing.JLabel lblNombreFamiliarEditar;
    private javax.swing.JLabel lblRolFamiliar;
    private javax.swing.JLabel lblRolFamiliarEditar;
    private javax.swing.JTable tablaFamiliares;
    private javax.swing.JTable tablaFuncionario;
    private javax.swing.JTextField txtApellidoFamiliar;
    private javax.swing.JTextField txtApellidoFamiliarEditar;
    private javax.swing.JTextField txtApellidoFuncionario;
    private javax.swing.JTextField txtApellidoFuncionarioEditar;
    private javax.swing.JTextField txtDireccionFuncionario;
    private javax.swing.JTextField txtDireccionFuncionarioEditar;
    private javax.swing.JLabel txtEstadoCivilEditar;
    private javax.swing.JTextField txtEstadoCivilFuncionario;
    private javax.swing.JTextField txtEstadoCivilFuncionarioEditar;
    private javax.swing.JTextField txtFamiliarFuncionarioIDEditar;
    private javax.swing.JTextField txtFamiliarIDEditar;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtFechaNacimientoEditar;
    private javax.swing.JLabel txtFuncionarioEditar;
    private javax.swing.JLabel txtFuncionarioEditar1;
    private javax.swing.JTextField txtFuncionarioIDEditar;
    private javax.swing.JTextField txtFuncionarioIDFamiliarCrear;
    private javax.swing.JTextField txtFuncionarioTipoIdentificacion;
    private javax.swing.JTextField txtFuncionarioTipoIdentificacionEditar;
    private javax.swing.JTextArea txtInformacionAcademica;
    private javax.swing.JTextArea txtInformacionAcademicaEditar;
    private javax.swing.JLabel txtInformacionAcademicaFuncionarioEditar;
    private javax.swing.JLabel txtNacimientoEditar;
    private javax.swing.JLabel txtNombreEditar;
    private javax.swing.JTextField txtNombreFamiliar;
    private javax.swing.JTextField txtNombreFamiliarEditar;
    private javax.swing.JTextField txtNombreFuncionario;
    private javax.swing.JTextField txtNombreFuncionarioEditar;
    private javax.swing.JTextField txtNumeroIdentificacionFuncionario;
    private javax.swing.JTextField txtNumeroIdentificacionFuncionarioEditar;
    private javax.swing.JTextField txtNumeroTelefonoFuncionario;
    private javax.swing.JTextField txtNumeroTelefonoFuncionarioEditar;
    private javax.swing.JTextField txtRolFamiliar;
    private javax.swing.JTextField txtRolFamiliarEditar;
    private javax.swing.JLabel txtSexoEditar;
    private javax.swing.JTextField txtSexoFuncionario;
    private javax.swing.JTextField txtSexoFuncionarioEditar;
    private javax.swing.JLabel txtTipoIdentificacionEditar;
    // End of variables declaration//GEN-END:variables
}
