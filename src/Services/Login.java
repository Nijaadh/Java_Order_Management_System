
package Services;
import Utility.*;
import javax.swing.JFrame;
public class Login extends javax.swing.JFrame {
    
    ShortcutMap shortcutCheck = new ShortcutMap();
    Validation validation = new Validation();
    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLogo = new javax.swing.JPanel();
        lblIconLogo = new javax.swing.JLabel();
        pnlHeader = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        pnlContainerCridentials = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        sepUserName = new javax.swing.JSeparator();
        sepPassword = new javax.swing.JSeparator();
        lblPassword = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        pnlEyeVisible = new javax.swing.JPanel();
        lblIconEyeInvisible = new javax.swing.JLabel();
        lblIconEyeVisible = new javax.swing.JLabel();
        pnlLockVisible = new javax.swing.JPanel();
        lblIconLock = new javax.swing.JLabel();
        pnlLoginButton = new javax.swing.JPanel();
        btnLogin = new javax.swing.JLabel();
        btnFogotPassword1 = new javax.swing.JLabel();
        btnFogotPassword2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(71, 120, 197));
        setUndecorated(true);
        setResizable(false);

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
                .addGap(86, 86, 86)
                .addComponent(lblIconLogo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlHeader.setBackground(new java.awt.Color(102, 153, 255));

        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/closeRed.png"))); // NOI18N
        btnClose.setToolTipText("Close");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 204, 0));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("MLT Holdings pvt Ltd");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addGap(74, 74, 74)
                .addComponent(btnClose))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClose)
            .addComponent(lblTitle)
        );

        pnlContainerCridentials.setBackground(new java.awt.Color(71, 120, 197));

        txtUserName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtUserName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUserName.setToolTipText("Enter your User Name");
        txtUserName.setBorder(null);
        txtUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserNameKeyPressed(evt);
            }
        });

        lblPassword.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassword.setText("Password :");

        lblUserName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(255, 255, 255));
        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserName.setText("User Name :");

        txtPassword.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPassword.setText("***************");
        txtPassword.setToolTipText("Enter Your Password");
        txtPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        pnlEyeVisible.setBackground(new java.awt.Color(71, 120, 197));
        pnlEyeVisible.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIconEyeInvisible.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconEyeInvisible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eyeInvisible.png"))); // NOI18N
        lblIconEyeInvisible.setToolTipText("Click to Visible");
        lblIconEyeInvisible.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblIconEyeInvisible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconEyeInvisibleMouseClicked(evt);
            }
        });
        pnlEyeVisible.add(lblIconEyeInvisible, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 26));

        lblIconEyeVisible.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconEyeVisible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eyeVisible.png"))); // NOI18N
        lblIconEyeVisible.setToolTipText("click to Hide");
        lblIconEyeVisible.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblIconEyeVisible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconEyeVisibleMouseClicked(evt);
            }
        });
        pnlEyeVisible.add(lblIconEyeVisible, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 26));

        pnlLockVisible.setBackground(new java.awt.Color(71, 120, 197));
        pnlLockVisible.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIconLock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconLock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lock.png"))); // NOI18N
        lblIconLock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlLockVisible.add(lblIconLock, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 26));

        pnlLoginButton.setBackground(new java.awt.Color(71, 120, 197));
        pnlLoginButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        pnlLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlLoginButtonMouseClicked(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLogin.setText("SignIn");
        btnLogin.setToolTipText("Click to Login");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlLoginButtonLayout = new javax.swing.GroupLayout(pnlLoginButton);
        pnlLoginButton.setLayout(pnlLoginButtonLayout);
        pnlLoginButtonLayout.setHorizontalGroup(
            pnlLoginButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlLoginButtonLayout.setVerticalGroup(
            pnlLoginButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        btnFogotPassword1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnFogotPassword1.setForeground(new java.awt.Color(255, 255, 255));
        btnFogotPassword1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnFogotPassword1.setText("Fogot Password...");
        btnFogotPassword1.setToolTipText("Recover Password");
        btnFogotPassword1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFogotPassword1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFogotPassword1MouseClicked(evt);
            }
        });
        btnFogotPassword1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnFogotPassword1KeyPressed(evt);
            }
        });

        btnFogotPassword2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnFogotPassword2.setForeground(new java.awt.Color(255, 255, 255));
        btnFogotPassword2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnFogotPassword2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fogotPasssword.png"))); // NOI18N
        btnFogotPassword2.setToolTipText("Recover Password");
        btnFogotPassword2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFogotPassword2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFogotPassword2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlContainerCridentialsLayout = new javax.swing.GroupLayout(pnlContainerCridentials);
        pnlContainerCridentials.setLayout(pnlContainerCridentialsLayout);
        pnlContainerCridentialsLayout.setHorizontalGroup(
            pnlContainerCridentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerCridentialsLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlContainerCridentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContainerCridentialsLayout.createSequentialGroup()
                        .addComponent(lblUserName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlContainerCridentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sepUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(txtUserName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlLockVisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlContainerCridentialsLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(pnlContainerCridentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContainerCridentialsLayout.createSequentialGroup()
                                .addComponent(btnFogotPassword1)
                                .addGap(0, 0, 0)
                                .addComponent(btnFogotPassword2))
                            .addGroup(pnlContainerCridentialsLayout.createSequentialGroup()
                                .addComponent(lblPassword)
                                .addGap(18, 18, 18)
                                .addGroup(pnlContainerCridentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(sepPassword)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlEyeVisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContainerCridentialsLayout.createSequentialGroup()
                                .addComponent(pnlLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142)))
                        .addGap(0, 24, Short.MAX_VALUE))))
        );
        pnlContainerCridentialsLayout.setVerticalGroup(
            pnlContainerCridentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContainerCridentialsLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(pnlContainerCridentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUserName)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlLockVisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pnlContainerCridentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlContainerCridentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPassword)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlEyeVisible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(sepPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(pnlContainerCridentialsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFogotPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFogotPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(pnlLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlContainerCridentials, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlContainerCridentials, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnCloseMouseClicked

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        if(txtPassword.getText().equals("***************")){
            txtPassword.setText("");
        }
    }//GEN-LAST:event_txtPasswordFocusGained

    private void lblIconEyeInvisibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconEyeInvisibleMouseClicked
        lblIconEyeInvisible.setVisible(false);
        lblIconEyeVisible.setVisible(true);
        txtPassword.setEchoChar('\0');
    }//GEN-LAST:event_lblIconEyeInvisibleMouseClicked

    private void lblIconEyeVisibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconEyeVisibleMouseClicked
        lblIconEyeVisible.setVisible(false);
        lblIconEyeInvisible.setVisible(true);
        txtPassword.setEchoChar('*');
    }//GEN-LAST:event_lblIconEyeVisibleMouseClicked

    private void btnFogotPassword1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFogotPassword1MouseClicked
        this.dispose();
        FogotPassword newFogotPassword = new FogotPassword();
        newFogotPassword.setVisible(true);
    }//GEN-LAST:event_btnFogotPassword1MouseClicked

    private void btnFogotPassword2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFogotPassword2MouseClicked
        this.dispose();
        FogotPassword newFogotPassword = new FogotPassword();
        newFogotPassword.setVisible(true);
    }//GEN-LAST:event_btnFogotPassword2MouseClicked

    private void btnFogotPassword1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFogotPassword1KeyPressed
        this.dispose();
        FogotPassword newFogotPassword = new FogotPassword();
        newFogotPassword.setVisible(true);
    }//GEN-LAST:event_btnFogotPassword1KeyPressed

    private void txtUserNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserNameKeyPressed
              shortcutCheck.shortcut(evt, this);
    }//GEN-LAST:event_txtUserNameKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
                shortcutCheck.shortcut(evt, this);
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        String userName=txtUserName.getText();
        String password=txtPassword.getText();
        String  status[] = validation.loginValidation(userName, password);
        
            MessageBox di = new MessageBox(status[0],status[1],this);
            this.setEnabled(false);
            di.setVisible(true);
            if(status[1].equals("granted")){
                this.dispose();
                Dashboard newDashboard = new Dashboard();
                newDashboard.setVisible(true);
            }
        
    }//GEN-LAST:event_btnLoginMouseClicked

    private void pnlLoginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLoginButtonMouseClicked
        
        
    }//GEN-LAST:event_pnlLoginButtonMouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnFogotPassword1;
    private javax.swing.JLabel btnFogotPassword2;
    private javax.swing.JLabel btnLogin;
    private javax.swing.JLabel lblIconEyeInvisible;
    private javax.swing.JLabel lblIconEyeVisible;
    private javax.swing.JLabel lblIconLock;
    private javax.swing.JLabel lblIconLogo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel pnlContainerCridentials;
    private javax.swing.JPanel pnlEyeVisible;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLockVisible;
    private javax.swing.JPanel pnlLoginButton;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JSeparator sepPassword;
    private javax.swing.JSeparator sepUserName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
