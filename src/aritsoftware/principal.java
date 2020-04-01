package aritsoftware;

import Entorno.Simbolo;
import Entorno.TablaDeSimbolos;
import Entorno.Tipo.tipo_primitivo;
import Estructuras.Vector;
import Utilidades.Mensaje;
import Utilidades.Mensaje.tipo_mensaje;
import analizadores.Gramatica;
import analizadores.ParseException;
import analizadores.TokenMgrError;
import arbol.Arbol;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import static javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
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
    Arbol AST_arbolSintaxisAbstracta;
    LinkedList<Mensaje> errores;
    
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
        ejNuevaPestana.doClick();
        tablaErrores.getColumnModel().getColumn(3).setPreferredWidth(400);
        tablaErrores.setAutoResizeMode(AUTO_RESIZE_LAST_COLUMN);
        consola.setAutoscrolls(rootPaneCheckingEnabled);
        consola.setContentType("text/html");

    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        lblFila = new javax.swing.JLabel();
        lblColumna = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaErrores = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consola = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        ejNuevaPestana = new javax.swing.JMenuItem();
        ejAbrir = new javax.swing.JMenuItem();
        ejGuardar = new javax.swing.JMenuItem();
        ejGuardarComo = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        ejColor = new javax.swing.JMenuItem();
        ejecutar = new javax.swing.JMenu();
        ejAscendente = new javax.swing.JMenuItem();
        ejDescendente = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        ejReporteAST = new javax.swing.JMenuItem();
        ejReporteTS = new javax.swing.JMenuItem();
        ejReporteGraficas = new javax.swing.JMenuItem();

        jMenuItem5.setText("jMenuItem5");

        jMenuItem11.setText("jMenuItem11");

        jMenuItem12.setText("jMenuItem12");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Arit Software");
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(0, 153, 255));

        lblFila.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFila.setText("Fila: 1");

        lblColumna.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblColumna.setText("Columna: 1");

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        jLabel1.setText("Consola");

        tablaErrores.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaErrores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Linea", "Columna", "Descripcion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaErrores);

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        jLabel2.setText("Errores");

        jScrollPane1.setViewportView(consola);

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));
        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jMenu1.setBackground(new java.awt.Color(0, 0, 0));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Archivo");

        ejNuevaPestana.setBackground(new java.awt.Color(0, 0, 0));
        ejNuevaPestana.setForeground(new java.awt.Color(255, 255, 255));
        ejNuevaPestana.setText("Nueva Pestana");
        ejNuevaPestana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejNuevaPestanaActionPerformed(evt);
            }
        });
        jMenu1.add(ejNuevaPestana);

        ejAbrir.setBackground(new java.awt.Color(0, 0, 0));
        ejAbrir.setForeground(new java.awt.Color(255, 255, 255));
        ejAbrir.setText("Abrir");
        ejAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(ejAbrir);

        ejGuardar.setBackground(new java.awt.Color(0, 0, 0));
        ejGuardar.setForeground(new java.awt.Color(255, 255, 255));
        ejGuardar.setText("Guardar");
        ejGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(ejGuardar);

        ejGuardarComo.setBackground(new java.awt.Color(0, 0, 0));
        ejGuardarComo.setForeground(new java.awt.Color(255, 255, 255));
        ejGuardarComo.setText("Guardar como");
        ejGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejGuardarComoActionPerformed(evt);
            }
        });
        jMenu1.add(ejGuardarComo);

        jMenuBar1.add(jMenu1);

        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("Edición");

        ejColor.setBackground(new java.awt.Color(0, 0, 0));
        ejColor.setForeground(new java.awt.Color(255, 255, 255));
        ejColor.setText("Color Background");
        ejColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejColorActionPerformed(evt);
            }
        });
        jMenu3.add(ejColor);

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

        ejDescendente.setBackground(new java.awt.Color(0, 0, 0));
        ejDescendente.setForeground(new java.awt.Color(255, 255, 255));
        ejDescendente.setText("Descendente");
        ejDescendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejDescendenteActionPerformed(evt);
            }
        });
        ejecutar.add(ejDescendente);

        jMenuBar1.add(ejecutar);
        ejecutar.getAccessibleContext().setAccessibleName("bttEjecutar");

        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setText("Reportes");

        ejReporteAST.setBackground(new java.awt.Color(0, 0, 0));
        ejReporteAST.setForeground(new java.awt.Color(255, 255, 255));
        ejReporteAST.setText("AST");
        ejReporteAST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejReporteASTActionPerformed(evt);
            }
        });
        jMenu4.add(ejReporteAST);
        ejReporteAST.getAccessibleContext().setAccessibleName("bttAST");

        ejReporteTS.setBackground(new java.awt.Color(0, 0, 0));
        ejReporteTS.setForeground(new java.awt.Color(255, 255, 255));
        ejReporteTS.setLabel("Tabla de Simbolos");
        ejReporteTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejReporteTSActionPerformed(evt);
            }
        });
        jMenu4.add(ejReporteTS);
        ejReporteTS.getAccessibleContext().setAccessibleName("bttTablaSimbolos");

        ejReporteGraficas.setBackground(new java.awt.Color(0, 0, 0));
        ejReporteGraficas.setForeground(new java.awt.Color(255, 255, 255));
        ejReporteGraficas.setText("Gráficas");
        ejReporteGraficas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejReporteGraficasActionPerformed(evt);
            }
        });
        jMenu4.add(ejReporteGraficas);
        ejReporteGraficas.getAccessibleContext().setAccessibleName("bttGraficas");
        ejReporteGraficas.getAccessibleContext().setAccessibleDescription("");

        jMenuBar1.add(jMenu4);
        jMenu4.getAccessibleContext().setAccessibleName("bttReportes");

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFila)
                        .addGap(71, 71, 71)
                        .addComponent(lblColumna)
                        .addGap(529, 529, 529))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFila)
                            .addComponent(lblColumna))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(8, 8, 8)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Metodo para crear nuevas pestanas
    private void ejNuevaPestanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejNuevaPestanaActionPerformed
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
    }//GEN-LAST:event_ejNuevaPestanaActionPerformed
    
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
    private void ejGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejGuardarActionPerformed
        String texto = "";
        String linea = "";
        try{
                FileWriter fEscribir = new FileWriter(urls[tabSeleccionada]);
                fEscribir.write(tpPanel[tabSeleccionada].getDocument().getText(0, tpPanel[tabSeleccionada].getDocument().getLength()));
                
                fEscribir.close();
                JOptionPane.showMessageDialog(null,"Guardado exitosamente");           
        }catch(Exception e){
            
        }        
    }//GEN-LAST:event_ejGuardarActionPerformed

    //Metodo para guardar como
    private void ejGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejGuardarComoActionPerformed
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
    }//GEN-LAST:event_ejGuardarComoActionPerformed

    //Metodo para abrir un archivo
    private void ejAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejAbrirActionPerformed
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
    }//GEN-LAST:event_ejAbrirActionPerformed

    private void ejColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejColorActionPerformed
        JColorChooser colorVentana=new JColorChooser();
        Color color=colorVentana.showDialog(null, "Seleccione un Color", Color.gray);
        tpPanel[tabSeleccionada].setBackground(color);
    }//GEN-LAST:event_ejColorActionPerformed

    private void ejAscendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejAscendenteActionPerformed
        String entrada="";
        try {
            entrada = tpPanel[tabSeleccionada].getDocument().getText(0, tpPanel[tabSeleccionada].getDocument().getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        interpretar(entrada);
        imprimirErrores();
        imprimirConsola();
    }//GEN-LAST:event_ejAscendenteActionPerformed

    private void ejReporteTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejReporteTSActionPerformed

        if(AST_arbolSintaxisAbstracta != null){
            TablaDeSimbolos ts = AST_arbolSintaxisAbstracta.getTsglobal();

            String temporal = "<html>\n" +
            "\n" +
            "<head>\n" +
            "    <title>Tabla de Simbolos</title>\n" +
            "</head>\n" +
            "\n" +
            "<style>\n" +
            "    table {\n" +
            "        width:100%;\n" +
            "    }\n" +
            "    table, th, td {\n" +
            "        border: 1px solid black;\n" +
            "        border-collapse: collapse;\n" +
            "    }\n" +
            "    th, td {\n" +
            "        padding: 15px;\n" +
            "        text-align: left;\n" +
            "    }\n" +
            "    table th {\n" +
            "      background-color: black;\n" +
            "      color: white;\n" +
            "      width: 25%;\n" +
            "    }\n" +
            "</style>\n" +
            "    \n" +
            "\n" +
            "<body>\n" +
            "    <table>\n" +
            "        <tr>\n" +
            "          <th>Identificador</th>\n" +
            "          <th>Tipo          </th> \n" +
            "          <th>Dimension(x,y)</th>\n" +
            "          <th>Valor         </th>\n" +
            "        </tr>\n";

            for(Simbolo s: ts.getLocal()){
                temporal += "        <tr>\n" +
                "          <td>"+s.getIdentificador()+"</td>\n";

                if(s.getTipo().getTipo_primitivo()==tipo_primitivo.VECTOR)
                temporal+="          <td>VECTOR: "+((Vector)s.getValor()).getTipo_dato().getTipo_primitivo().name()+"</td>\n";
                else
                temporal+="          <td>"+s.getTipo().getTipo_primitivo().name()+"</td>\n";

                temporal+= "          <td>"+s.getDimensionX()+","+s.getDimensionY()+"</td>\n" +
                "          <td>"+s.getValorCadena()+"</td>\n" +
                "        </tr>\n";
            }

            temporal += "    </table>  \n" +
            "</body>\n" +
            "\n" +
            "</html>";

            String ruta = "reporteTS.html";
            File archivo = new File(ruta);
            BufferedWriter bw;
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
                PrintWriter wr = new PrintWriter(bw);
                wr.write(temporal);
                wr.close();
                bw.close();
                File objetofile = new File(ruta);
                Desktop.getDesktop().open(objetofile);

            } catch (IOException ex) {
                System.out.println("Error al escribir el archivo");
            }
        }
    }//GEN-LAST:event_ejReporteTSActionPerformed

    private void ejReporteGraficasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejReporteGraficasActionPerformed
        Galeria graficas = new Galeria();
        graficas.show();
    }//GEN-LAST:event_ejReporteGraficasActionPerformed

    private void ejDescendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejDescendenteActionPerformed
        try {
            errores = new LinkedList<>();
            String entrada = tpPanel[tabSeleccionada].getDocument().getText(0, tpPanel[tabSeleccionada].getDocument().getLength());
            Gramatica parser = new Gramatica(new BufferedReader(new StringReader(entrada)));
            parser.start();
            
            AST_arbolSintaxisAbstracta = new Arbol(parser.instrucciones);
            AST_arbolSintaxisAbstracta.ejecutar();
            imprimirErrores();
            imprimirConsola();
            
        } catch(TokenMgrError e){
            System.err.println(e.getMessage());
        } catch (BadLocationException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ejDescendenteActionPerformed

    private void ejReporteASTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejReporteASTActionPerformed
        String arbol = AST_arbolSintaxisAbstracta.reporteAST();
        System.out.println(arbol);
        
        String ruta = "ast";
        File archivo = new File(ruta+".dot");
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(archivo));
            PrintWriter wr = new PrintWriter(bw);
            wr.write(arbol);
            wr.close();
            bw.close();
            File objetofile = new File(ruta+".dot");
        } catch (IOException ex) {
            System.out.println("Error al escribir el archivo");
        }
        
        try
        {       
                ProcessBuilder pbuilder;
                pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", ruta+".png", ruta+".dot" );
                pbuilder.redirectErrorStream( true );
                pbuilder.start();
                JOptionPane.showMessageDialog(null, "Reporte producido de forma exitosa.");

        } catch (Exception e) { e.printStackTrace(); }

    }//GEN-LAST:event_ejReporteASTActionPerformed
    
    public void interpretar(String entrada){
        errores = new LinkedList<>();
        analizadores.Sintactico pars = null;
        AST_arbolSintaxisAbstracta=null;
        analizadores.Lexico lexico = null;
        try {
            lexico = new analizadores.Lexico(new BufferedReader(new StringReader(entrada)));
            pars=new analizadores.Sintactico(lexico);
            pars.parse();        
            AST_arbolSintaxisAbstracta=pars.getAST();
            
        } catch (Exception ex) {
            System.err.println("Error fatal en compilación de entrada.");
        } 
        if(AST_arbolSintaxisAbstracta==null){
            
            if(lexico != null)
                errores.addAll(lexico.errores_lexicos);
            
            if(pars != null)
                errores.addAll(pars.getErrores());
            
            System.err.println("No es posible ejecutar las instrucciones porque\r\n"
                    + "el árbol no fue cargado de forma adecuada por la existencia\r\n"
                    + "de errores léxicos o sintácticos.");
            return;
        }
        AST_arbolSintaxisAbstracta.ejecutar();
              
    }
    
    public void imprimirErrores(){
        DefaultTableModel modelo = (DefaultTableModel) tablaErrores.getModel();
        modelo.setRowCount(0);
        
        Object[] fila = new Object[4];
        
        for(Mensaje mensaje: errores){
            if(mensaje.getTipo()==tipo_mensaje.MENSAJE)
                continue;

            fila[0] = mensaje.getTipo().name();
            fila[1] = mensaje.getLinea();
            fila[2] = mensaje.getColumna();
            fila[3] = mensaje.getDescripcion();
            modelo.addRow(fila);
        }    
        
        if(AST_arbolSintaxisAbstracta != null){
            for(Mensaje mensaje: AST_arbolSintaxisAbstracta.getMensajes()){
                if(mensaje.getTipo()==tipo_mensaje.MENSAJE)
                    continue;

                fila[0] = mensaje.getTipo().name();
                fila[1] = mensaje.getLinea();
                fila[2] = mensaje.getColumna();
                fila[3] = mensaje.getDescripcion();
                modelo.addRow(fila);
            }
        }
          
        tablaErrores.setModel(modelo);
    }
    
    public void imprimirConsola(){
        String temporal = "";
        
        temporal+= "<style>\n" +
                    "table {\n" +
                    "  width:100%;\n" +
                    "}\n" +
                    "table, th, td {\n" +
                    "  border: 1px solid black;\n" +
                    "  border-collapse: collapse;\n" +
                    "}\n" +
                    "th, td {\n" +
                    "  padding: 15px;\n" +
                    "  text-align: left;\n" +
                    "}\n" +
                    "table#t01 tr:nth-child(even) {\n" +
                    "  background-color: #eee;\n" +
                    "}\n" +
                    "table#t01 tr:nth-child(odd) {\n" +
                    " background-color: #fff;\n" +
                    "}\n" +
                    "table#t01 th {\n" +
                    "  background-color: black;\n" +
                    "  color: white;\n" +
                    "}\n" +
                    "</style>";

        if(AST_arbolSintaxisAbstracta != null){
            for(Mensaje mensaje: AST_arbolSintaxisAbstracta.getMensajes()){
                if(mensaje.getTipo()==tipo_mensaje.MENSAJE)
                    temporal += mensaje.getDescripcion()+"\n" ;
            }
        }
        temporal+="</font>";
        consola.setText(temporal);
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
    private javax.swing.JTextPane consola;
    private javax.swing.JMenuItem ejAbrir;
    private javax.swing.JMenuItem ejAscendente;
    private javax.swing.JMenuItem ejColor;
    private javax.swing.JMenuItem ejDescendente;
    private javax.swing.JMenuItem ejGuardar;
    private javax.swing.JMenuItem ejGuardarComo;
    private javax.swing.JMenuItem ejNuevaPestana;
    private javax.swing.JMenuItem ejReporteAST;
    private javax.swing.JMenuItem ejReporteGraficas;
    private javax.swing.JMenuItem ejReporteTS;
    private javax.swing.JMenu ejecutar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblColumna;
    private javax.swing.JLabel lblFila;
    private javax.swing.JTable tablaErrores;
    // End of variables declaration//GEN-END:variables

}
