/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chr97lubuntu
 */
public class er extends javax.swing.JFrame {
Conexion con = new Conexion();
   
    Connection Consulta = con.conexion();
    String ValorDevoluciones="";
    String mercaderia=ValorMercaderias();
    String ivacobrar=ivaporcobrar();
    String ivapagar=ivaporpagar();
    /**
     * Creates new form er
     */
    public er() {
        initComponents();
        //ldia.setText("31");
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE); 
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();    
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        DefaultTableModel modelo = new DefaultTableModel() {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
            modelo.addColumn("                          ");
            modelo.addColumn("                ");
            modelo.addColumn("                ");
            modelo.addColumn("                ");
            cuentas.setModel(modelo);
            
            String datos[] = new String[4];
            
            
            datos[0]="      Activo         ";
            datos[1]="";
            datos[2]="";
            datos[3]="";
            modelo.addRow(datos);
            datos[0]="      Corriente         ";
            datos[1]="";
            datos[2]="";
            datos[3]="";
            modelo.addRow(datos);
            
            float sumatoria=0;
            float activo=0;
            float pasivo=0;
            float gananciadespuesisr=valorisr();
            float capital=0;
                    

          try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT nombre, saldo from  cuentasbalance");
          
            while (Ca.next()) {
               
                datos[0] = Ca.getString(1);
                datos[1] = Ca.getString(2);
                datos[2] = "";
                datos[3]="";
                
                if(datos[0].equals("Capital")){
                   capital=activo-pasivo-gananciadespuesisr;
                   datos[1]="";
                    datos[2]=String.valueOf(capital);
                }
                if(datos[0].equals("Ganancia neta del ejercicio")){
                   datos[2]=String.valueOf(gananciadespuesisr);
                   datos[1]="";
                  
                }
                if(datos[0].equals("Mercaderías")){
                   //datos[2]=String.valueOf(gananciadespuesisr);
                   
                   datos[1]=mercaderia;
                  
                }
                  if(datos[0].equals("IVA por cobrar")){
                //   datos[2]=String.valueOf(gananciadespuesisr);
                      System.out.println("IVA POR COBRAR "+ivacobrar);
                   datos[1]=ivacobrar;
                  
                }
                      if(datos[0].equals("IVA por pagar")){
                  // datos[2]=String.valueOf(gananciadespuesisr);
                          System.out.println("IVA POR PAGAR "+ivapagar);
                   datos[1]=ivapagar;
                  
                }
                
                if(datos[1].equals("")){
                   datos[1]="0";
                }
                sumatoria+=Float.parseFloat(datos[1]);
                modelo.addRow(datos);
                
                //(-) Devoluciones y rebajas sobre ventas
                
                if(datos[0].equals("Utiles y enseres")){
                    datos[0] = "           No corriente";
                    datos[1] = "";
                    datos[2] = "";
                    datos[3]="";
                    modelo.addRow(datos);
                }
                if(datos[0].equals("Gastos de instalación")){
                    datos[0] = "Suma de activo";
                    datos[2] = String.valueOf(sumatoria);
                    activo=sumatoria;
                    sumatoria=0;
                    
                    datos[1] = "";
                    datos[3]="";
                    modelo.addRow(datos);
                    datos[0] = "           Pasivo";
                    datos[1] = "";
                    datos[2] = "";
                    datos[3]="";
                    modelo.addRow(datos);
                    datos[0] = "           Corriente";
                    datos[1] = "";
                    datos[2] = "";
                    datos[3]="";
                    modelo.addRow(datos);
                }
                if(datos[0].equals("Hipotecas a corto plazo")){
                   
                    datos[0] = "           No Corriente";
                    datos[1] = "";
                    datos[2] = "";
                    datos[3]="";
                    modelo.addRow(datos);
                }
                if(datos[0].equals("Préstamos bancarios a largo plazo")){
                   
                     datos[0] = "Suma del pasivo";
                    datos[2] = String.valueOf(sumatoria);
                    pasivo=sumatoria;
                    sumatoria=0;
                    datos[1] = "";
                    datos[3]="";
                    modelo.addRow(datos);
                    datos[0] = "           Patrimonio neto";
                    datos[1] = "";
                    datos[2] = "";
                    datos[3]="";
                    modelo.addRow(datos);
                }//Ganancia neta del ejercicio
                
                if(datos[0].equals("Ganancia neta del ejercicio")){
                   
                    datos[0] = "Suma del pasivo y patrimonio neto";
                    datos[1] = "";
                    datos[2] = "";
                    datos[3]=String.valueOf(pasivo+gananciadespuesisr+capital);
                    modelo.addRow(datos);
                }
                
            }
            cuentas.setModel(modelo);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        cuentas.setVisible(true);
        cuentas.getColumn("                          ").setPreferredWidth(200);
            
            
            
            
            
    }
    // String ivacobrar=ivaporcobrar();
   // String ivapagar=ivapporpagar();
    
    public String ivaporcobrar() {
        
        
                         float iss=0;
    try {
        Statement sx = Consulta.createStatement();
        //cargo, total devengado+250,
        ResultSet Ca = sx.executeQuery("SELECT saldo from cuentasestador where nombre='"+"(+) Compras"+"'");
        while(Ca.next())
            iss=Float.parseFloat(Ca.getString(1))*12/100;
    
    } catch (SQLException ex) {
        Logger.getLogger(er.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    
    return String.valueOf(iss);
    
        
        
    }
    public String ivaporpagar() {
    
                         float iss=0;
    try {
        Statement sx = Consulta.createStatement();
        //cargo, total devengado+250,
        ResultSet Ca = sx.executeQuery("SELECT saldo from cuentasestador where nombre='"+"Ventas"+"'");
        while(Ca.next())
            iss=Float.parseFloat(Ca.getString(1));
    
    } catch (SQLException ex) {
        Logger.getLogger(er.class.getName()).log(Level.SEVERE, null, ex);
    }
    
        try {
        Statement sx = Consulta.createStatement();
        //cargo, total devengado+250,
        ResultSet Cad = sx.executeQuery("SELECT saldo from cuentasestador where nombre='"+"(-) Devoluciones y rebajas sobre ventas"+"'");
        while(Cad.next())
            iss=iss-Float.parseFloat(Cad.getString(1));
    
    } catch (SQLException ex) {
        Logger.getLogger(er.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    
    return String.valueOf(iss);
    
        
        
    }
    
    
    
    public String ValorMercaderias(){
             float iss=0;
    try {
        Statement sx = Consulta.createStatement();
        //cargo, total devengado+250,
        ResultSet Ca = sx.executeQuery("SELECT saldo from cuentasestador where nombre='"+"(-) Mercaderias (Inventario Final)"+"'");
        while(Ca.next())
            iss=Float.parseFloat(Ca.getString(1));
    
    } catch (SQLException ex) {
        Logger.getLogger(er.class.getName()).log(Level.SEVERE, null, ex);
    }
    return String.valueOf(iss);
    
        
        
    }
    
    
    
    public float valorisr(){
        float iss=0;
    try {
        Statement sx = Consulta.createStatement();
        //cargo, total devengado+250,
        ResultSet Ca = sx.executeQuery("SELECT saldo from cuentasbalance where nombre='"+"Ganancia neta del ejercicio"+"'");
        while(Ca.next())
            iss=Float.parseFloat(Ca.getString(1));
    
    } catch (SQLException ex) {
        Logger.getLogger(er.class.getName()).log(Level.SEVERE, null, ex);
    }
    return iss;
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cuentas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(17, 111, 172));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VENTA DE REPUESTOS SHALOM");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 790, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Balance de situación general (Cifras en quetzales)");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 790, -1));

        cuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(cuentas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 770, 340));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(er.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(er.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(er.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(er.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new er().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable cuentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
