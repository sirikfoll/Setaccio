package setaccio;

import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static setaccio.IOHandler.DoFilterFile;
import static setaccio.IOHandler.hmap;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class MainScreen extends javax.swing.JFrame {

    public static String FILE_NAME;
    public MainScreen() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSelectFile = new javax.swing.JButton();
        jComboBoxSelectPacketType = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListSpells = new javax.swing.JList<>();
        jLabelLoadedFile = new javax.swing.JLabel();
        jButtonFilter = new javax.swing.JButton();
        jTextFieldNpcEntry = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonSelectFile.setText("Selecionar arquivo");
        jButtonSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectFileActionPerformed(evt);
            }
        });

        jComboBoxSelectPacketType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar Packets", "SMSG_SPELL_START", "SMSG_SPELL_GO" }));

        jListSpells.setEnabled(false);
        jScrollPane1.setViewportView(jListSpells);

        jLabelLoadedFile.setText("Nenhum arquivo carregado.");

        jButtonFilter.setText("Filtrar");
        jButtonFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFilterActionPerformed(evt);
            }
        });

        jTextFieldNpcEntry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNpcEntryKeyTyped(evt);
            }
        });

        jLabel1.setText("Npc Entry:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabelLoadedFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldNpcEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jComboBoxSelectPacketType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)))
                .addContainerGap(416, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButtonFilter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonSelectFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxSelectPacketType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldNpcEntry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jButtonFilter)
                .addGap(44, 44, 44)
                .addComponent(jLabelLoadedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(311, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelectFileActionPerformed
        FileNameExtensionFilter fter = new FileNameExtensionFilter(".txt", "txt");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(fter);
        fc.setDialogTitle("Escolha o arquivo");
        int resposta = fc.showOpenDialog(null);
        
        if (resposta == JFileChooser.APPROVE_OPTION) {
            FILE_NAME = fc.getSelectedFile().getAbsolutePath();
            jLabelLoadedFile.setText(FILE_NAME);
        }
    }//GEN-LAST:event_jButtonSelectFileActionPerformed

    private void jButtonFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFilterActionPerformed
        if (FILE_NAME == null || FILE_NAME.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Carregue um arquivo antes.");
            return;
        }
        if (jComboBoxSelectPacketType.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione o tipo de packet a filtrar.");
            return;
        }
        if (jTextFieldNpcEntry.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione o npc.");
            return;
        }
        DoFilterFile(FILE_NAME, jComboBoxSelectPacketType.getSelectedItem().toString(), jTextFieldNpcEntry.getText());
        DefaultListModel listModel = new DefaultListModel();
        HashSet<String> spells = new HashSet<>();
        if (hmap.containsKey(jTextFieldNpcEntry.getText()))
            spells = hmap.get(jTextFieldNpcEntry.getText()).getUniqueCasts();
        for (String spId : spells)
            listModel.addElement(spId);
        jListSpells.setModel(listModel);
        jListSpells.setEnabled(true);
    }//GEN-LAST:event_jButtonFilterActionPerformed

    private void jTextFieldNpcEntryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNpcEntryKeyTyped
        String aloowedCharacters="0987654321";
        if(!aloowedCharacters.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldNpcEntryKeyTyped

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFilter;
    private javax.swing.JButton jButtonSelectFile;
    private javax.swing.JComboBox<String> jComboBoxSelectPacketType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelLoadedFile;
    private javax.swing.JList<String> jListSpells;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldNpcEntry;
    // End of variables declaration//GEN-END:variables
}
