package Utility;

import Services.Dashboard;
import Services.FogotPassword;
import Services.Login;
import javax.swing.JFrame;

public class ShortcutMap {

    Shortcut shortcut = new Shortcut();

    public void shortcut(java.awt.event.KeyEvent evt, JFrame frame) {

        if (frame instanceof Login) {

            if (evt.isControlDown() && evt.getKeyCode() == evt.VK_F) {
                shortcut.goToFogotPassword(frame);
            }

        } else if (frame instanceof FogotPassword) {

            if (evt.isControlDown() && evt.getKeyCode() == evt.VK_BACK_SPACE) {
                shortcut.backToLogin(frame);
            } else if (evt.isControlDown() && evt.getKeyCode() == evt.VK_C) {
                shortcut.copyPassword(frame);
            } else if (evt.isControlDown() && evt.getKeyCode() == evt.VK_DELETE) {
                shortcut.clearFogotPasswordForm(frame);

            }

        }

    }

    public void DashboardShortcutMap(java.awt.event.KeyEvent evt, JFrame frame) {
        if (frame instanceof Dashboard) {
            if (evt.getKeyCode() == evt.VK_F1) {
                shortcut.DashboardShortcut(frame,0);               
            }
            else if(evt.getKeyCode() == evt.VK_F2){
                shortcut.DashboardShortcut(frame,1);
            }
            else if(evt.getKeyCode() == evt.VK_F3){
                shortcut.DashboardShortcut(frame,2);
            }
            else if(evt.getKeyCode() == evt.VK_F4){
                shortcut.DashboardShortcut(frame,3);
            }
            else if(evt.getKeyCode() == evt.VK_F5){
                shortcut.DashboardShortcut(frame,4);
            }
            else if(evt.getKeyCode() == evt.VK_F6){
                shortcut.DashboardShortcut(frame,5);
            }
            else if(evt.getKeyCode() == evt.VK_F7){
                shortcut.DashboardShortcut(frame,6);
            }
        }
    }

}
