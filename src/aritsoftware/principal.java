package aritsoftware;

import arbol.Arbol;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Utilities;

public class principal extends javax.swing.JFrame {

    JPanel[] panel;
    JTextPane[] tpPanel;
    JScrollPane[] scrolls;
    TextLineNumber[] tln;
    int n;
    int tabSeleccionada;
    String[] urls;
    SimpleAttributeSet set;
    
    public principal() {
        initComponents();
        setLocationRelativeTo(null);
        
        panel = new JPanel[100000];
        tpPanel = new JTextPane[100000];
        scrolls = new JScrollPane[100000];
        tln = new TextLineNumber[100000];
        urls = new String[100000];
        n = 0;
        tabSeleccionada = 0;
        jMenuItem1.doClick();
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        lblFila = new javax.swing.JLabel();
        lblColumna = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        ejecutar = new javax.swing.JMenu();
        ejAscendente = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

        jMenuItem5.setText("jMenuItem5");

        jMenuItem11.setText("jMenuItem11");

        jMenuItem12.setText("jMenuItem12");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Arit Software");
        setPreferredSize(new java.awt.Dimension(720, 720));
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(0, 153, 255));

        jTextPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTextPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTextPane1.setEnabled(false);
        jScrollPane1.setViewportView(jTextPane1);

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        jLabel1.setText("Consola");

        lblFila.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFila.setText("Fila: 1");

        lblColumna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblColumna.setText("Columna: 1");

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenu1.setBackground(new java.awt.Color(0, 0, 0));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Archivo");

        jMenuItem1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setText("Nueva Pestana");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem2.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem2.setText("Abrir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem3.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem3.setText("Guardar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem4.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem4.setText("Guardar como");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("Edición");

        jMenuItem7.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem7.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem7.setText("Color Background");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        ejecutar.setForeground(new java.awt.Color(255, 255, 255));
        ejecutar.setText("Ejecutar");

        ejAscendente.setBackground(new java.awt.Color(0, 0, 0));
        ejAscendente.setForeground(new java.awt.Color(255, 255, 255));
        ejAscendente.setText("Ascendente");
        ejAscendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejAscendenteActionPerformed(evt);
            }
        });
        ejecutar.add(ejAscendente);

        jMenuItem14.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem14.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem14.setText("Descendente");
        ejecutar.add(jMenuItem14);

        jMenuBar1.add(ejecutar);
        ejecutar.getAccessibleContext().setAccessibleName("bttEjecutar");

        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setText("Reportes");

        jMenuItem6.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem6.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem6.setText("Errores");
        jMenu4.add(jMenuItem6);
        jMenuItem6.getAccessibleContext().setAccessibleName("bttErrores");

        jMenuItem8.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem8.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem8.setText("AST");
        jMenu4.add(jMenuItem8);
        jMenuItem8.getAccessibleContext().setAccessibleName("bttAST");

        jMenuItem9.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem9.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem9.setLabel("Tabla de Simbolos");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);
        jMenuItem9.getAccessibleContext().setAccessibleName("bttTablaSimbolos");

        jMenuItem10.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem10.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem10.setText("Gráficas");
        jMenu4.add(jMenuItem10);
        jMenuItem10.getAccessibleContext().setAccessibleName("bttGraficas");
        jMenuItem10.getAccessibleContext().setAccessibleDescription("");

        jMenuBar1.add(jMenu4);
        jMenu4.getAccessibleContext().setAccessibleName("bttReportes");

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFila)
                .addGap(58, 58, 58)
                .addComponent(lblColumna)
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFila)
                        .addComponent(lblColumna))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Metodo para crear nuevas pestanas
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        panel[n] = new JPanel();
        tpPanel[n] = new JTextPane();
        scrolls[n] = new JScrollPane();
        tln[n] = new TextLineNumber(tpPanel[n]);
        scrolls[n].setRowHeaderView(tln[n]);

        tpPanel[n].setContentType("text/rtf");
        panel[n].setPreferredSize(new Dimension(700, 460));
        scrolls[n].setPreferredSize(new Dimension(700, 460));
        tpPanel[n].setPreferredSize(new Dimension(6, 20));
        tpPanel[n].setBackground(new Color(153,204,255));
        panel[n].setName(Integer.toString(n));

        jTabbedPane1.add(panel[n]);
        panel[n].add(scrolls[n]);
        scrolls[n].setViewportView(tpPanel[n]);
        panel[n].add(scrolls[n]);

        jTabbedPane1.addChangeListener(
                new ChangeListener(){
                    @Override
                    public void stateChanged(ChangeEvent changeEvent) {
                        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                        int index = sourceTabbedPane.getSelectedIndex();
                        tabSeleccionada = Integer.parseInt(sourceTabbedPane.getTitleAt(index));
                    }                        
                }
        );
        
        tpPanel[n].addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                lblFila.setText("Fila: " + getRow(e.getDot(), (JTextComponent)e.getSource()));
                lblColumna.setText("Columna: "+ getColumn(e.getDot(), (JTextComponent)e.getSource()));
            }
        });

        n++; 
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    
    public static int getRow(int pos, JTextComponent editor) {
        int rn = (pos==0) ? 1 : 0;
        try {
            int offs=pos;
            while( offs>0) {
                offs=Utilities.getRowStart(editor, offs)-1;
                rn++;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return rn;
    }

    public static int getColumn(int pos, JTextComponent editor) {
        try {
            return pos-Utilities.getRowStart(editor, pos)+1;
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return -1;
    }
     
    //Metodo para guardar un archivo
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        String texto = "";
        String linea = "";
        try{
                FileWriter fEscribir = new FileWriter(urls[tabSeleccionada]);
                fEscribir.write(tpPanel[tabSeleccionada].getDocument().getText(0, tpPanel[tabSeleccionada].getDocument().getLength()));
                
                fEscribir.close();
                JOptionPane.showMessageDialog(null,"Guardado exitosamente");           
        }catch(Exception e){
            
        }        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    //Metodo para guardar como
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        String texto = "";
        String linea = "";
        try{
            JFileChooser selector = new JFileChooser();
            selector.showSaveDialog(this);
            
            File guardar = selector.getSelectedFile();
            urls[tabSeleccionada] = guardar.toString()+".txt";
            
            if(guardar != null){
                FileWriter fEscribir = new FileWriter(guardar+".txt");
                fEscribir.write(tpPanel[tabSeleccionada].getDocument().getText(0, tpPanel[tabSeleccionada].getDocument().getLength()));
                
                fEscribir.close();
                JOptionPane.showMessageDialog(null,"Guardado exitosamente");
            }
            
        }catch(Exception e){
            
        }        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    //Metodo para abrir un archivo
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String texto = "";
        String linea = "";
        try{
            JFileChooser selector = new JFileChooser();
            selector.showOpenDialog(this);
            
            File archivo = selector.getSelectedFile();
            urls[tabSeleccionada] = archivo.toString();
            
            if(archivo != null){
                FileReader fLector = new FileReader(archivo);
                BufferedReader linea_lector = new BufferedReader(fLector);
               
                while((linea=linea_lector.readLine())!= null){
                    texto+= linea+"\n";
                }
                linea_lector.close();
                fLector.close();
               tpPanel[tabSeleccionada].getStyledDocument().insertString(tpPanel[tabSeleccionada].getStyledDocument().getLength(), texto, null);
            }
            
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        JColorChooser colorVentana=new JColorChooser();
        Color color=colorVentana.showDialog(null, "Seleccione un Color", Color.gray);
        tpPanel[tabSeleccionada].setBackground(color);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void ejAscendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejAscendenteActionPerformed
        String entrada="";
        try {
            entrada = tpPanel[tabSeleccionada].getDocument().getText(0, tpPanel[tabSeleccionada].getDocument().getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        interpretar(entrada);
    }//GEN-LAST:event_ejAscendenteActionPerformed
    
    public void interpretar(String entrada){
        
        analizadores.Sintactico pars;
        Arbol AST_arbolSintaxisAbstracta=null;
        try {
            pars=new analizadores.Sintactico(new analizadores.Lexico(new BufferedReader(new StringReader(entrada))));
            pars.parse();        
            AST_arbolSintaxisAbstracta=pars.getAST();
        } catch (Exception ex) {
            System.err.println("Error fatal en compilación de entrada.");
        } 
        if(AST_arbolSintaxisAbstracta==null){
            System.err.println("No es posible ejecutar las instrucciones porque\r\n"
                    + "el árbol no fue cargado de forma adecuada por la existencia\r\n"
                    + "de errores léxicos o sintácticos.");
            return;
        }
        AST_arbolSintaxisAbstracta.ejecutar();
    }
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ejAscendente;
    private javax.swing.JMenu ejecutar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblColumna;
    private javax.swing.JLabel lblFila;
    // End of variables declaration//GEN-END:variables

}
