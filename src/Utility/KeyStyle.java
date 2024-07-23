
package Utility;

import Services.Dashboard;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KeyStyle {
    static public int selectedKey;
    
    public static void setKeyColorGreen(JPanel pnl,JLabel lbl){
        pnl.setBackground(new java.awt.Color(68,194,137));
        lbl.setForeground(new java.awt.Color(71,120,197));
    }
    
    public static void setKeyColorBlue(JPanel pnl,JLabel lbl){
        pnl.setBackground(new java.awt.Color(23,35,51));
        lbl.setForeground(new java.awt.Color(204,204,204));
    }
    
    public void setCloseButtonColorRed(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/closeRedDashBoard50.png"))); // NOI18N
    }
   
    public void setMinimizeButtonColorBlue(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/minimizeBlue34.png"))); // NOI18N
    }
    public void setCloseButtonColorGray(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/closeGray.png"))); // NOI18N
    }
   
    public void setMinimizeButtonColorGray(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/minimizeGray.png"))); // NOI18N
    }
    
    public void setSettingsOrangeIconBig(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/settingsOrangeBig42.png"))); // NOI18N
    }
    public void setSettingsOrangeIconSmall(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/settingsOrangeSmall34.png"))); // NOI18N
    }
    
    public void setShearedFolderYellowIconBig(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sharedFolderYellowBig42.png"))); // NOI18N
    }
    public void setShearedFolderYellowIconSmall(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sharedFolderYellowSmall34.png"))); // NOI18N
    }
    
    public void setChatRoomGreenIconBig(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chatRoomGreenBig42.png"))); // NOI18N
    }
    public void setChatRoomGreenIconSmall(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chatRoomGreenSmall34.png"))); // NOI18N
    }
    
    public void setNotificationIconBig(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/notificationCeyanBig42.png"))); // NOI18N
    }
    public void setNotificationIconSmall(JLabel lbl){       
        lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/notificationCeyansmall34.png"))); // NOI18N
    }
}
