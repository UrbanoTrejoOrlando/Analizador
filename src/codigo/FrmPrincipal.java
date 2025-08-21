package codigo;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.util.List;
import java_cup.runtime.Symbol;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author starlord
 */
public class FrmPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmPrincipal.class.getName());

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();                // Construye todo el diseño
        this.setLocationRelativeTo(null); // Centra

    }

    private void analizarLexico() throws IOException {
    String expr = (String) txtAnalizar.getText();
    Lexer lexer = new Lexer(new StringReader(expr));
    String resultado = "LINEA\t\tSIMBOLO\n";
    
    while (true) {
        Tokens token = lexer.yylex();
        if (token == null) {
            txtAnalizarLex.setText(resultado);
            return;
        }
        
        // Obtener el número de línea actual del lexer (yyline empieza en 0, así que sumamos 1)
        int numeroLinea = lexer.yyline + 1;
        
        switch (token) {
            case Linea:
                // Solo ignorar el token Linea, ya no lo necesitamos para contar
                break;
            case Comillas:
                resultado += "LINEA " + numeroLinea + "\t<Comillas>\t\t" + lexer.lexeme + "\n";
                break;
            case Cadena_texto:
                resultado += "LINEA " + numeroLinea + "\t<Cadena completa>\t" + lexer.lexeme + "\n";
                break;
            case Include:
                resultado += "LINEA " + numeroLinea + "\t<Reservada include>\t" + lexer.lexeme + "\n";
                break;
            case Libreria:
                resultado += "LINEA " + numeroLinea + "\t<Libreria>\t\t" + lexer.lexeme + "\n";
                break;
            case CLASS_T:
                resultado += "LINEA " + numeroLinea + "\t<Reservada class>\t" + lexer.lexeme + "\n";
                break;
            case Void:
                resultado += "LINEA " + numeroLinea + "\t<Reservada void>\t" + lexer.lexeme + "\n";
                break;
            case Print:
                resultado += "LINEA " + numeroLinea + "\t<Reservada print>\t" + lexer.lexeme + "\n";
                break;
            case Printf:
                resultado += "LINEA " + numeroLinea + "\t<Reservada printf>\t" + lexer.lexeme + "\n";
                break;
            case Scanf:
                resultado += "LINEA " + numeroLinea + "\t<Reservada scanf>\t" + lexer.lexeme + "\n";
                break;
            case Return:
                resultado += "LINEA " + numeroLinea + "\t<Reservada return>\t" + lexer.lexeme + "\n";
                break;
            case TIPO_DATO:
                resultado += "LINEA " + numeroLinea + "\t<Tipo de dato>\t" + lexer.lexeme + "\n";
                break;
            case Cadena:
                resultado += "LINEA " + numeroLinea + "\t<Cadena>\t\t" + lexer.lexeme + "\n";
                break;
            case If:
                resultado += "LINEA " + numeroLinea + "\t<Reservada if>\t" + lexer.lexeme + "\n";
                break;
            case Else:
                resultado += "LINEA " + numeroLinea + "\t<Reservada else>\t" + lexer.lexeme + "\n";
                break;
            case Do:
                resultado += "LINEA " + numeroLinea + "\t<Reservada do>\t" + lexer.lexeme + "\n";
                break;
            case While:
                resultado += "LINEA " + numeroLinea + "\t<Reservada while>\t" + lexer.lexeme + "\n";
                break;
            case For:
                resultado += "LINEA " + numeroLinea + "\t<Reservada for>\t" + lexer.lexeme + "\n";
                break;
            case Igual:
                resultado += "LINEA " + numeroLinea + "\t<Operador igual>\t" + lexer.lexeme + "\n";
                break;
            case Suma:
                resultado += "LINEA " + numeroLinea + "\t<Operador suma>\t" + lexer.lexeme + "\n";
                break;
            case Resta:
                resultado += "LINEA " + numeroLinea + "\t<Operador resta>\t" + lexer.lexeme + "\n";
                break;
            case Multiplicacion:
                resultado += "LINEA " + numeroLinea + "\t<Operador multiplicacion>\t" + lexer.lexeme + "\n";
                break;
            case Division:
                resultado += "LINEA " + numeroLinea + "\t<Operador division>\t" + lexer.lexeme + "\n";
                break;
            case Op_logico:
                resultado += "LINEA " + numeroLinea + "\t<Operador logico>\t" + lexer.lexeme + "\n";
                break;
            case Op_incremento:
                resultado += "LINEA " + numeroLinea + "\t<Operador incremento>\t" + lexer.lexeme + "\n";
                break;
            case Op_relacional:
                resultado += "LINEA " + numeroLinea + "\t<Operador relacional>\t" + lexer.lexeme + "\n";
                break;
            case Op_atribucion:
                resultado += "LINEA " + numeroLinea + "\t<Operador atribucion>\t" + lexer.lexeme + "\n";
                break;
            case Op_booleano:
                resultado += "LINEA " + numeroLinea + "\t<Operador booleano>\t" + lexer.lexeme + "\n";
                break;
            case Parentesis_a:
                resultado += "LINEA " + numeroLinea + "\t<Parentesis de apertura>\t" + lexer.lexeme + "\n";
                break;
            case Parentesis_c:
                resultado += "LINEA " + numeroLinea + "\t<Parentesis de cierre>\t" + lexer.lexeme + "\n";
                break;
            case Llave_a:
                resultado += "LINEA " + numeroLinea + "\t<Llave de apertura>\t" + lexer.lexeme + "\n";
                break;
            case Llave_c:
                resultado += "LINEA " + numeroLinea + "\t<Llave de cierre>\t" + lexer.lexeme + "\n";
                break;
            case Corchete_a:
                resultado += "LINEA " + numeroLinea + "\t<Corchete de apertura>\t" + lexer.lexeme + "\n";
                break;
            case Corchete_c:
                resultado += "LINEA " + numeroLinea + "\t<Corchete de cierre>\t" + lexer.lexeme + "\n";
                break;
            case Coma:
                resultado += "LINEA " + numeroLinea + "\t<Coma>\t\t" + lexer.lexeme + "\n";
                break;
            case MAIN_T:
                resultado += "LINEA " + numeroLinea + "\t<Reservada main>\t" + lexer.lexeme + "\n";
                break;
            case P_coma:
                resultado += "LINEA " + numeroLinea + "\t<Punto y coma>\t" + lexer.lexeme + "\n";
                break;
            case Identificador:
                resultado += "LINEA " + numeroLinea + "\t<Identificador>\t" + lexer.lexeme + "\n";
                break;
            case Numero:
                resultado += "LINEA " + numeroLinea + "\t<Numero entero>\t" + lexer.lexeme + "\n";
                break;
            case Numero_decimal:
                resultado += "LINEA " + numeroLinea + "\t<Numero decimal>\t" + lexer.lexeme + "\n";
                break;
            case ERROR:
                resultado += "LINEA " + numeroLinea + "\t<Simbolo no definido>\t" + lexer.lexeme + "\n";
                break;
            default:
                resultado += "LINEA " + numeroLinea + "\t<" + lexer.lexeme + ">\n";
                break;
        }
    }
}
    // Métodos para el subrayado de errores
    private void highlightError(int start, int length) {
        try {
            StyledDocument doc = (StyledDocument) txtAnalizar.getDocument();
            SimpleAttributeSet attrs = new SimpleAttributeSet();
            StyleConstants.setForeground(attrs, Color.RED);
            StyleConstants.setBackground(attrs, new Color(255, 230, 230)); // Fondo rojo claro
            StyleConstants.setBold(attrs, true);
            StyleConstants.setUnderline(attrs, true);

            doc.setCharacterAttributes(start, length, attrs, false);
        } catch (Exception e) {
            System.err.println("Error al resaltar texto: " + e.getMessage());
        }
    }

    private void resetTextStyle() {
        try {
            StyledDocument doc = (StyledDocument) txtAnalizar.getDocument();
            SimpleAttributeSet attrs = new SimpleAttributeSet();
            StyleConstants.setForeground(attrs, Color.BLACK);
            StyleConstants.setBackground(attrs, Color.WHITE);
            StyleConstants.setBold(attrs, false);
            StyleConstants.setUnderline(attrs, false);

            doc.setCharacterAttributes(0, doc.getLength(), attrs, true);
        } catch (Exception e) {
            System.err.println("Error al resetear estilo: " + e.getMessage());
        }
    }

    private int calculateColumn(String text, int position) {
        if (text == null || position < 0 || position >= text.length()) {
            return 0;
        }

        int lastNewLine = text.lastIndexOf('\n', position - 1);
        if (lastNewLine == -1) {
            return position;
        }
        return position - lastNewLine - 1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnAbrirArchivo = new javax.swing.JButton();
        btnAnalizar1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAnalizar = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnalizarLex = new javax.swing.JTextArea();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnAbrirCarpeta = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        ListAbrirCarpeta = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        btnAnalizarSin = new javax.swing.JButton();
        btnLimpiar3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAnalizarSin = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Adwaita Mono", 0, 24)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analizador Lexico y Sintactico", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Noto Sans", 1, 24))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N

        btnAbrirArchivo.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnAbrirArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codigo/icons/abrirarchivo.png"))); // NOI18N
        btnAbrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirArchivoActionPerformed(evt);
            }
        });

        btnAnalizar1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnAnalizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codigo/icons/token.png"))); // NOI18N
        btnAnalizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizar1ActionPerformed(evt);
            }
        });

        txtAnalizar.setBackground(new java.awt.Color(204, 204, 255));
        txtAnalizar.setColumns(20);
        txtAnalizar.setRows(5);
        jScrollPane1.setViewportView(txtAnalizar);

        txtAnalizarLex.setBackground(new java.awt.Color(204, 204, 255));
        txtAnalizarLex.setColumns(20);
        txtAnalizarLex.setRows(5);
        jScrollPane2.setViewportView(txtAnalizarLex);

        btnLimpiar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codigo/icons/borrar.png"))); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codigo/icons/guardararchivo.png"))); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codigo/icons/nuevoarchivo.png"))); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAbrirCarpeta.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnAbrirCarpeta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codigo/icons/archivo.png"))); // NOI18N
        btnAbrirCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCarpetaActionPerformed(evt);
            }
        });

        ListAbrirCarpeta.setBackground(new java.awt.Color(204, 204, 255));
        ListAbrirCarpeta.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        ListAbrirCarpeta.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ListAbrirCarpetaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane5.setViewportView(ListAbrirCarpeta);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAbrirCarpeta)
                        .addGap(18, 18, 18)
                        .addComponent(btnAbrirArchivo)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAnalizar1)
                        .addGap(27, 27, 27)
                        .addComponent(btnLimpiar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimpiar)
                            .addComponent(btnAnalizar1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnNuevo)
                                .addComponent(btnAbrirCarpeta)
                                .addComponent(btnAbrirArchivo)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Terminal", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Noto Sans", 1, 18))); // NOI18N

        btnAnalizarSin.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnAnalizarSin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codigo/icons/ejecutar.png"))); // NOI18N
        btnAnalizarSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarSinActionPerformed(evt);
            }
        });

        btnLimpiar3.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        btnLimpiar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codigo/icons/eliminar.png"))); // NOI18N
        btnLimpiar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar3ActionPerformed(evt);
            }
        });

        txtAnalizarSin.setColumns(20);
        txtAnalizarSin.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        txtAnalizarSin.setRows(5);
        jScrollPane3.setViewportView(txtAnalizarSin);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btnAnalizarSin)
                        .addGap(41, 41, 41)
                        .addComponent(btnLimpiar3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar3)
                    .addComponent(btnAnalizarSin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.getAccessibleContext().setAccessibleName("Analizador");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtAnalizarLex.setText(null);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnLimpiar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar3ActionPerformed
        txtAnalizarSin.setText(null);
    }//GEN-LAST:event_btnLimpiar3ActionPerformed

    private void btnAbrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirArchivoActionPerformed
        String archivoSeleccionado = ListAbrirCarpeta.getSelectedValue(); // Archivo seleccionado
    if (archivoSeleccionado != null && carpetaActual != null) {
        File archivo = new File(carpetaActual, archivoSeleccionado);
        try {
            String contenido = new String(Files.readAllBytes(archivo.toPath()));
            txtAnalizar.setText(contenido); // Mostrar contenido en txtAnalizar
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al abrir el archivo: " + ex.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un archivo de la lista primero.");
    }

    }//GEN-LAST:event_btnAbrirArchivoActionPerformed

    private void btnAnalizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizar1ActionPerformed
        try {
            analizarLexico();
        } catch (IOException ex) {
            System.getLogger(FrmPrincipal.class.getName()).log(Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_btnAnalizar1ActionPerformed

    private void btnAnalizarSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarSinActionPerformed
        String ST = txtAnalizar.getText();
        Sintax s = new Sintax(new codigo.LexerCup(new StringReader(ST)));

        // Limpiar errores previos
        s.clearErrors();

        try {
            s.parse();
            List<Symbol> errors = s.getErrors();

            if (errors.isEmpty()) {
                txtAnalizarSin.setText("Análisis realizado correctamente");
                txtAnalizarSin.setForeground(new Color(25, 111, 61));
            } else {
                // Construir mensaje con todos los errores
                StringBuilder errorMsg = new StringBuilder();
                errorMsg.append("Se encontraron ").append(errors.size()).append(" errores:\n\n");

                for (int i = 0; i < errors.size(); i++) {
                    Symbol sym = errors.get(i);

                    // Calcular columna relativa al inicio de la línea
                    int columna = sym.left;
                    int linea = sym.right + 1;

                    if (ST != null && !ST.isEmpty()) {
                        int inicioLinea = ST.lastIndexOf('\n', sym.left - 1) + 1;
                        columna = sym.left - inicioLinea;
                    }

                    errorMsg.append(i + 1).append(". Error en línea ")
                            .append(linea)
                            .append(", columna ")
                            .append(columna + 1)
                            .append(": \"")
                            .append(sym.value != null ? sym.value.toString() : "símbolo inesperado")
                            .append("\"\n");
                }

                txtAnalizarSin.setText(errorMsg.toString());
                txtAnalizarSin.setForeground(Color.RED);
            }
        } catch (Exception ex) {
            // Manejar errores inesperados de manera más específica
            String errorMessage = "Error durante el análisis: ";
            if (ex.getMessage().contains("Can't recover")) {
                errorMessage += "Múltiples errores sintácticos detectados";
            } else {
                errorMessage += ex.getMessage();
            }

            txtAnalizarSin.setText(errorMessage);
            txtAnalizarSin.setForeground(Color.RED);
        }
    }//GEN-LAST:event_btnAnalizarSinActionPerformed

    private File carpetaActual;
            
    private void btnAbrirCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCarpetaActionPerformed
        JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        carpetaActual = chooser.getSelectedFile();
        File[] archivos = carpetaActual.listFiles();
        DefaultListModel<String> model = new DefaultListModel<>();

        if (archivos != null) {
            for (File f : archivos) {
                if (f.isFile()) {
                    model.addElement(f.getName());
                }
            }
        }
        ListAbrirCarpeta.setModel(model); // Mostrar archivos en el JList
    }
    }

// Método recursivo para listar archivos y carpetas
    private void listarArchivosRecursivo(File carpeta, int nivel) {

    }//GEN-LAST:event_btnAbrirCarpetaActionPerformed

    private void ListAbrirCarpetaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ListAbrirCarpetaAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_ListAbrirCarpetaAncestorAdded

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtAnalizar.setText("");
    }//GEN-LAST:event_btnNuevoActionPerformed

    // Variable global para almacenar el archivo actual
    private File archivoActual = null;
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
        if (archivoActual == null) { // Archivo nuevo
            JFileChooser chooser = new JFileChooser();
            int seleccion = chooser.showSaveDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivoActual = chooser.getSelectedFile();

                // Asegurar que tenga extensión .txt
                if (!archivoActual.getName().endsWith(".txt")) {
                    archivoActual = new File(archivoActual.getAbsolutePath() + ".txt");
                }
            } else {
                return; // Canceló
            }
        }

        // Guardar el contenido del JTextArea en archivoActual
        Files.write(archivoActual.toPath(), txtAnalizar.getText().getBytes());

        JOptionPane.showMessageDialog(null, "Archivo guardado correctamente");

        // Si quieres permitir guardar otro archivo diferente en el futuro, puedes desactivar esto:
        // archivoActual = null;
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + ex.getMessage());
        ex.printStackTrace();
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListAbrirCarpeta;
    private javax.swing.JButton btnAbrirArchivo;
    private javax.swing.JButton btnAbrirCarpeta;
    private javax.swing.JButton btnAnalizar1;
    private javax.swing.JButton btnAnalizarSin;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiar3;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea txtAnalizar;
    private javax.swing.JTextArea txtAnalizarLex;
    private javax.swing.JTextArea txtAnalizarSin;
    // End of variables declaration//GEN-END:variables
}
