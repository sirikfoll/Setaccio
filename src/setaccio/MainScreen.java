package setaccio;

import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static setaccio.InputHandler.DoFilterFile;
import static setaccio.InputHandler.hmap;
import static setaccio.OutputHandler.WriteOutputFile;

/**
 *
 * @author Gustavo Soares <gustavosc_17@hotmail.com>
 */
public class MainScreen extends javax.swing.JFrame {

    Filter filter;
    public static String FILE_NAME;
    public MainScreen() {
        initComponents();
        jListSpells.setVisible(false);
        filter = new Filter();
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
        jTextFieldEntryRemover = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabelFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButtonSelectFile.setText("Selecionar arquivo");
        jButtonSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectFileActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSelectFile);
        jButtonSelectFile.setBounds(10, 11, 137, 30);

        jComboBoxSelectPacketType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar Packets", "SMSG_SPELL_START", "SMSG_SPELL_GO", "SMSG_ATTACK_START", "SMSG_AI_REACTION", "SMSG_PARTY_KILL_LOG" }));
        jComboBoxSelectPacketType.setEnabled(false);
        getContentPane().add(jComboBoxSelectPacketType);
        jComboBoxSelectPacketType.setBounds(10, 50, 190, 30);

        jListSpells.setEnabled(false);
        jScrollPane1.setViewportView(jListSpells);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(220, 40, 250, 490);

        jLabelLoadedFile.setText("Nenhum arquivo carregado.");
        getContentPane().add(jLabelLoadedFile);
        jLabelLoadedFile.setBounds(160, 10, 322, 23);

        jButtonFilter.setText("Filtrar");
        jButtonFilter.setEnabled(false);
        jButtonFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFilterActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFilter);
        jButtonFilter.setBounds(60, 150, 80, 30);

        jTextFieldNpcEntry.setEnabled(false);
        jTextFieldNpcEntry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNpcEntryKeyTyped(evt);
            }
        });
        getContentPane().add(jTextFieldNpcEntry);
        jTextFieldNpcEntry.setBounds(90, 90, 100, 30);
        getContentPane().add(jTextFieldEntryRemover);
        jTextFieldEntryRemover.setBounds(790, 100, 120, 30);

        jLabel3.setText("Entry:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(740, 110, 34, 14);

        jLabel1.setText("Npc Entry:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 100, 70, 14);

        jLabel2.setText("Remover Dados do Sniff");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(770, 30, 140, 14);

        jButton1.setText("Apagar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(810, 170, 90, 40);

        jLabelFundo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fundoMadeira.jpg"))); // NOI18N
        jLabelFundo.setText("jLabel2");
        getContentPane().add(jLabelFundo);
        jLabelFundo.setBounds(0, 0, 1010, 550);

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
        
         if (FILE_NAME == null || FILE_NAME.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Falha ao carregar arquivo.");
            return;
        }
        
        DoFilterFile(FILE_NAME);
        jComboBoxSelectPacketType.setEnabled(true);
        jTextFieldNpcEntry.setEnabled(true);
        jButtonFilter.setEnabled(true);
    }//GEN-LAST:event_jButtonSelectFileActionPerformed

    private void jButtonFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFilterActionPerformed
        if (!jTextFieldNpcEntry.getText().isEmpty()) {
            filter.FilterByEntry(jTextFieldNpcEntry.getText());
            //JOptionPane.showMessageDialog(null, "Selecione o npc.");
        }
        if (jComboBoxSelectPacketType.getSelectedIndex() != 0) {
            filter.FilterByPacket(jComboBoxSelectPacketType.getSelectedItem().toString());
            //JOptionPane.showMessageDialog(null, "Selecione o tipo de packet a filtrar.");
        }
        DefaultListModel listModel = new DefaultListModel();
        HashSet<String> spells = new HashSet<>();
        for (Unit u : Filter.filteredInfo) {
            if (u.getEntry().equals(jTextFieldNpcEntry.getText())) {
                spells = u.getUniqueCasts();
                break;
            }
        }
        for (String spId : spells)
            listModel.addElement(spId);
        jListSpells.setModel(listModel);
        jListSpells.setVisible(true);
        jListSpells.setEnabled(true);

        WriteOutputFile();
    }//GEN-LAST:event_jButtonFilterActionPerformed

    private void jTextFieldNpcEntryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNpcEntryKeyTyped
        String aloowedCharacters="0987654321";
        if(!aloowedCharacters.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldNpcEntryKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextFieldEntryRemover.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Selecione alguma entry para apagar.");
            return;
        }
        Rewriter rw = new Rewriter();
        rw.removeLineFromFile(FILE_NAME, jTextFieldEntryRemover.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonFilter;
    private javax.swing.JButton jButtonSelectFile;
    private javax.swing.JComboBox<String> jComboBoxSelectPacketType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelFundo;
    private javax.swing.JLabel jLabelLoadedFile;
    private javax.swing.JList<String> jListSpells;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldEntryRemover;
    private javax.swing.JTextField jTextFieldNpcEntry;
    // End of variables declaration//GEN-END:variables
}
