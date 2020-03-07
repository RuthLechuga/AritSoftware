package aritsoftware;

import Utilidades.Graficas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class Galeria extends javax.swing.JFrame implements ActionListener{
    
    //DECLARACI�N DE COMPONENTES
    Container cp;
    JComboBox cbOpciones;
    ChartPanel panelGrafico;
    JPanel panelChart;
    String[] titulos;
    Graficas instancia_graficas = Graficas.getSingletonInstance();

    public Galeria() {
        initComponents();
        	
        //INICIALIZACI�N DE COMPONENTES
        cp = new Container();
        cbOpciones = new JComboBox();
        panelChart = new JPanel();
    
        setTitle("GALERIA");
        setResizable(false);
        this.getContentPane().setBackground(new Color(86,182,248));
        setLocation(0,0);
        setSize(new Dimension(760, 650));	
        
        cp = getContentPane(); 	
     	SpringLayout layout = new SpringLayout();       
     	cp.setLayout(layout);	
     	
	//------------------------------------FINAL TABLA-----------------------------------------------//
     	cbOpciones.setPreferredSize(new Dimension(700,25));
     	cbOpciones.setBackground(Color.WHITE);
        layout.putConstraint(SpringLayout.WEST, cbOpciones, 30, SpringLayout.WEST, cp);
        layout.putConstraint(SpringLayout.NORTH, cbOpciones, 40, SpringLayout.NORTH, cp);	
        
        panelChart.setLayout(new java.awt.BorderLayout());
        panelChart.setPreferredSize(new Dimension(700,500));
        layout.putConstraint(SpringLayout.WEST, panelChart, 30, SpringLayout.WEST, cp);
        layout.putConstraint(SpringLayout.NORTH, panelChart, 80, SpringLayout.NORTH, cp);
        add(panelChart);
        
        if(instancia_graficas.getGraficas() == null)
            return;
        
        for(JFreeChart jf: instancia_graficas.getGraficas())
            cbOpciones.addItem(jf.getTitle().getText());

        cbOpciones.addActionListener(this);
        cp.add(cbOpciones);
        
        graficar(instancia_graficas.getGraficas().get(0).getTitle().getText());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Galeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Galeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Galeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Galeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Galeria().setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.cbOpciones){
            graficar((String)cbOpciones.getSelectedItem());  
        }
    }
    
    public void graficar(String opcion){
        JFreeChart seleccion=null;
            
        for(JFreeChart jf: instancia_graficas.getGraficas()){
            if(opcion.compareTo(jf.getTitle().getText())==0){
                seleccion = jf;
                break;
            }
        }

        panelGrafico = new ChartPanel(seleccion);
        panelChart.removeAll();
        panelChart.add(panelGrafico,BorderLayout.CENTER);
        panelChart.validate(); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
