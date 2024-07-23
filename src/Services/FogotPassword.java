
package Services;

import Utility.ShortcutMap;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class FogotPassword extends javax.swing.JFrame {

    ShortcutMap shortcutCheck = new ShortcutMap();
    
    public FogotPassword() {
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        pnlLogo = new javax.swing.JPanel();
        lblIconLogo = new javax.swing.JLabel();
        pnlContainerFogotPass = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        lblUserName = new javax.swing.JLabel();
        pnlCoppyButton = new javax.swing.JPanel();
        btnCopy1 = new javax.swing.JLabel();
        btnCopy2 = new javax.swing.JLabel();
        sepQuestion = new javax.swing.JSeparator();
        lblSequrityQuestion = new javax.swing.JLabel();
        comboSequrityQuestions = new javax.swing.JComboBox<>();
        lblSecurityAnswer = new javax.swing.JLabel();
        txtSecurityAnswer = new javax.swing.JTextField();
        pnlClearButton = new javax.swing.JPanel();
        btnClear1 = new javax.swing.JLabel();
        btnClear2 = new javax.swing.JLabel();
        pnlSearchButton = new javax.swing.JPanel();
        btnSearch = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        pnlHeader.setBackground(new java.awt.Color(102, 153, 255));

        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/closeRed.png"))); // NOI18N
        btnClose.setToolTipText("Exit");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 204, 0));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("MLT Holdings pvt Ltd");

        btnBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back.png"))); // NOI18N
        btnBack.setToolTipText("Back to Login Page");
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addComponent(btnBack)
                .addGap(140, 140, 140)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBack)
            .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(lblTitle)
                .addComponent(btnClose))
        );

        pnlLogo.setBackground(new java.awt.Color(23, 35, 51));

        lblIconLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N

        javax.swing.GroupLayout pnlLogoLayout = new javax.swing.GroupLayout(pnlLogo);
        pnlLogo.setLayout(pnlLogoLayout);
        pnlLogoLayout.setHorizontalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIconLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlLogoLayout.setVerticalGroup(
            pnlLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(lblIconLogo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlContainerFogotPass.setBackground(new java.awt.Color(71, 120, 197));

        txtUserName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtUserName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUserName.setToolTipText("Enter Your User Name");
        txtUserName.setBorder(null);
        txtUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserNameKeyPressed(evt);
            }
        });

        lblUserName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(255, 255, 255));
        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserName.setText("User Name :");

        pnlCoppyButton.setBackground(new java.awt.Color(71, 120, 197));
        pnlCoppyButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnCopy1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnCopy1.setForeground(new java.awt.Color(255, 255, 255));
        btnCopy1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCopy1.setText("Copy");
        btnCopy1.setToolTipText("Coppy Password");
        btnCopy1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCopy1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCopy1MouseClicked(evt);
            }
        });

        btnCopy2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCopy2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/copy.png"))); // NOI18N
        btnCopy2.setToolTipText("Coppy Password");
        btnCopy2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlCoppyButtonLayout = new javax.swing.GroupLayout(pnlCoppyButton);
        pnlCoppyButton.setLayout(pnlCoppyButtonLayout);
        pnlCoppyButtonLayout.setHorizontalGroup(
            pnlCoppyButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCoppyButtonLayout.createSequentialGroup()
                .addComponent(btnCopy1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCopy2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
        );
        pnlCoppyButtonLayout.setVerticalGroup(
            pnlCoppyButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCopy1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCopy2, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        lblSequrityQuestion.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSequrityQuestion.setForeground(new java.awt.Color(255, 255, 255));
        lblSequrityQuestion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSequrityQuestion.setText("Security Question :");

        comboSequrityQuestions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboSequrityQuestions.setToolTipText("Select the Security Question");
        comboSequrityQuestions.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboSequrityQuestionsKeyPressed(evt);
            }
        });

        lblSecurityAnswer.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSecurityAnswer.setForeground(new java.awt.Color(255, 255, 255));
        lblSecurityAnswer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSecurityAnswer.setText("Security Answer :");

        txtSecurityAnswer.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtSecurityAnswer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSecurityAnswer.setToolTipText("Enter your Sequrity Answer");
        txtSecurityAnswer.setBorder(null);
        txtSecurityAnswer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSecurityAnswerKeyPressed(evt);
            }
        });

        pnlClearButton.setBackground(new java.awt.Color(71, 120, 197));
        pnlClearButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btnClear1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnClear1.setForeground(new java.awt.Color(255, 255, 255));
        btnClear1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClear1.setText("Clear");
        btnClear1.setToolTipText("Clear All");
        btnClear1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClear1MouseClicked(evt);
            }
        });

        btnClear2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClear2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/clear.png"))); // NOI18N
        btnClear2.setToolTipText("Clear All");
        btnClear2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlClearButtonLayout = new javax.swing.GroupLayout(pnlClearButton);
        pnlClearButton.setLayout(pnlClearButtonLayout);
        pnlClearButtonLayout.setHorizontalGroup(
            pnlClearButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClearButtonLayout.createSequentialGroup()
                .addComponent(btnClear1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlClearButtonLayout.setVerticalGroup(
            pnlClearButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClear1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnClear2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlSearchButton.setBackground(new java.awt.Color(23, 35, 51));
        pnlSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        pnlSearchButton.setToolTipText("Search Security Question");
        pnlSearchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnSearch.setToolTipText("Search Sequrity Question");
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlSearchButtonLayout = new javax.swing.GroupLayout(pnlSearchButton);
        pnlSearchButton.setLayout(pnlSearchButtonLayout);
        pnlSearchButtonLayout.setHorizontalGroup(
            pnlSearchButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addContainerGap())
        );
        pnlSearchButtonLayout.setVerticalGroup(
            pnlSearchButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addGap(25, 25, 25))
        );

        txtPassword.setEditable(false);
        txtPassword.setBackground(new java.awt.Color(71, 120, 197));
        txtPassword.setFont(new java.awt.Font("Segoe Print", 0, 36)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(204, 0, 0));
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPassword.setBorder(null);
        txtPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout pnlContainerFogotPassLayout = new javax.swing.GroupLayout(pnlContainerFogotPass);
        pnlContainerFogotPass.setLayout(pnlContainerFogotPassLayout);
        pnlContainerFogotPassLayout.setHorizontalGroup(
            pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerFogotPassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContainerFogotPassLayout.createSequentialGroup()
                        .addComponent(sepQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlContainerFogotPassLayout.createSequentialGroup()
                        .addGroup(pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContainerFogotPassLayout.createSequentialGroup()
                                .addGroup(pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSequrityQuestion)
                                    .addGroup(pnlContainerFogotPassLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addGroup(pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContainerFogotPassLayout.createSequentialGroup()
                                                .addComponent(lblUserName)
                                                .addGap(36, 36, 36)
                                                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(comboSequrityQuestions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(pnlSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlContainerFogotPassLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword)
                                    .addGroup(pnlContainerFogotPassLayout.createSequentialGroup()
                                        .addComponent(lblSecurityAnswer)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSecurityAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContainerFogotPassLayout.createSequentialGroup()
                        .addComponent(pnlCoppyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(pnlClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))))
        );
        pnlContainerFogotPassLayout.setVerticalGroup(
            pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerFogotPassLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContainerFogotPassLayout.createSequentialGroup()
                        .addGroup(pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUserName)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContainerFogotPassLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(lblSequrityQuestion))
                            .addGroup(pnlContainerFogotPassLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(comboSequrityQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlSearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(sepQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSecurityAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSecurityAnswer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContainerFogotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCoppyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlClearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pnlContainerFogotPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlContainerFogotPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        this.dispose();
        Login newLogin = new Login();
        newLogin.setVisible(true);
    }//GEN-LAST:event_btnBackMouseClicked

    private void txtUserNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserNameKeyPressed
        shortcutCheck.shortcut(evt, this);
    }//GEN-LAST:event_txtUserNameKeyPressed

    private void comboSequrityQuestionsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboSequrityQuestionsKeyPressed
        shortcutCheck.shortcut(evt, this);
    }//GEN-LAST:event_comboSequrityQuestionsKeyPressed

    private void txtSecurityAnswerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSecurityAnswerKeyPressed
        shortcutCheck.shortcut(evt, this);
    }//GEN-LAST:event_txtSecurityAnswerKeyPressed

    private void btnCopy1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCopy1MouseClicked
            String password=txtPassword.getText();
            StringSelection selection = new StringSelection(password);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection,null);
    }//GEN-LAST:event_btnCopy1MouseClicked

    private void btnClear1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClear1MouseClicked
        txtPassword.setText("");
        txtUserName.setText("");
        txtSecurityAnswer.setText("");
        comboSequrityQuestions.setSelectedIndex(0);
    }//GEN-LAST:event_btnClear1MouseClicked

    /**
     * @param args the command line arguments
     */
    
    public String getPassword(){
        return txtPassword.getText();
    }
    public void setPassword(){
        txtPassword.setText("");
    }
    public void setUserName(){
        txtUserName.setText("");
    }
    public void setsecurityAnswer(){
        txtSecurityAnswer.setText("");
    }
    public void setsecurityQuestion(){
        comboSequrityQuestions.setSelectedIndex(0);
    }
    
    
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
            java.util.logging.Logger.getLogger(FogotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FogotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FogotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FogotPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FogotPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnClear1;
    private javax.swing.JLabel btnClear2;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnCopy1;
    private javax.swing.JLabel btnCopy2;
    private javax.swing.JLabel btnSearch;
    private javax.swing.JComboBox<String> comboSequrityQuestions;
    private javax.swing.JLabel lblIconLogo;
    private javax.swing.JLabel lblSecurityAnswer;
    private javax.swing.JLabel lblSequrityQuestion;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel pnlClearButton;
    private javax.swing.JPanel pnlContainerFogotPass;
    private javax.swing.JPanel pnlCoppyButton;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlSearchButton;
    private javax.swing.JSeparator sepQuestion;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSecurityAnswer;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
