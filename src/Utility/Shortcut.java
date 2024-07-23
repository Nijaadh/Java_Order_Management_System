/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import Services.Dashboard;
import Services.FogotPassword;
import Services.Login;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Shortcut {
    
    
    void closeApplication(JFrame frame){           
            System.exit(0);           
    }
    void goToFogotPassword(JFrame frame){
            frame.dispose();
            FogotPassword newFogotPassword = new FogotPassword();
            newFogotPassword.setVisible(true);
    }  
    void backToLogin(JFrame frame){
            frame.dispose();
            Login newLogin = new Login();
            newLogin.setVisible(true);
    }
    void copyPassword(JFrame frame){
        
            FogotPassword newFogotPassword = new FogotPassword();
            String password=newFogotPassword.getPassword();
            StringSelection selection = new StringSelection(password);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection,null);
    }
    void clearFogotPasswordForm(JFrame frame){       
        ((FogotPassword) frame).setUserName();
        ((FogotPassword) frame).setsecurityQuestion();
        ((FogotPassword) frame).setsecurityAnswer();
        ((FogotPassword) frame).setPassword();
    }
    
    
    public void DashboardShortcut(JFrame frame,int key){
        JTabbedPane modulePnl=((Dashboard)frame).getModulePnl();
        if(key==0){
            ((Dashboard)frame).setSelectedNavigationKey(0);
            ((Dashboard)frame).navigationPnlSelector();
            ((Dashboard)frame).setFocus(0); 
            modulePnl.setSelectedIndex(0);
            
        }
        else if(key==1){
            ((Dashboard)frame).setSelectedNavigationKey(1);
            ((Dashboard)frame).navigationPnlSelector();
            ((Dashboard)frame).setFocus(1);
            modulePnl.setSelectedIndex(1);
        }
        else if(key==2){
            ((Dashboard)frame).setSelectedNavigationKey(2);
            ((Dashboard)frame).navigationPnlSelector();
            ((Dashboard)frame).setFocus(2);
            modulePnl.setSelectedIndex(2);
        }
        else if(key==3){
            ((Dashboard)frame).setSelectedNavigationKey(3);
            ((Dashboard)frame).navigationPnlSelector();
            ((Dashboard)frame).setFocus(3);
            modulePnl.setSelectedIndex(3);
        }
        else if(key==4){
            ((Dashboard)frame).setSelectedNavigationKey(4);
            ((Dashboard)frame).navigationPnlSelector();
            ((Dashboard)frame).setFocus(4);
            modulePnl.setSelectedIndex(4);
        }
        else if(key==5){
            ((Dashboard)frame).setSelectedNavigationKey(5);
            ((Dashboard)frame).navigationPnlSelector();
            ((Dashboard)frame).setFocus(5);
            modulePnl.setSelectedIndex(5);
        }
        else if(key==6){
            ((Dashboard)frame).setSelectedNavigationKey(6);
            ((Dashboard)frame).navigationPnlSelector();
            ((Dashboard)frame).setFocus(6);
            modulePnl.setSelectedIndex(6);
        }
        
    }
}
