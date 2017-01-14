package setaccio;

import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static setaccio.InputHandler.DoFilterFile;
import static setaccio.InputHandler.hmap;

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
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButtonSelectFile.setText("Selecionar arquivo");
        jButtonSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectFileActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSelectFile);
        jButtonSelectFile.setBounds(10, 11, 137, 23);

        jComboBoxSelectPacketType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar Packets", "SMSG_SPELL_START", "SMSG_SPELL_GO" }));
        getContentPane().add(jComboBoxSelectPacketType);
        jComboBoxSelectPacketType.setBounds(10, 40, 137, 20);

        jListSpells.setEnabled(false);
        jScrollPane1.setViewportView(jListSpells);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(165, 11, 101, 130);

        jLabelLoadedFile.setText("Nenhum arquivo carregado.");
        getContentPane().add(jLabelLoadedFile);
        jLabelLoadedFile.setBounds(39, 213, 322, 23);

        jButtonFilter.setText("Filtrar");
        jButtonFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFilterActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFilter);
        jButtonFilter.setBounds(52, 146, 61, 23);

        jTextFieldNpcEntry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNpcEntryKeyTyped(evt);
            }
        });
        getContentPane().add(jTextFieldNpcEntry);
        jTextFieldNpcEntry.setBounds(65, 78, 82, 20);

        jLabel1.setText("Npc Entry:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 81, 51, 14);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundoMadeira.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1010, 550);

        setSize(new java.awt.Dimension(1026, 586));
        setLocationRelativeTo(null);
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
        //if (hmap.containsKey(jTextFieldNpcEntry.getText()))
        //    spells = hmap.get(jTextFieldNpcEntry.getText()).getUniqueCasts();
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelLoadedFile;
    private javax.swing.JList<String> jListSpells;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldNpcEntry;
    // End of variables declaration//GEN-END:variables
}
