/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utility.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import java.util.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
//import sun.swing.table.DefaultTableCellHeaderRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author mohom
 */
public class Dashboard extends javax.swing.JFrame {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public Dashboard() {
        initComponents();
        conn = DbConnection.connection();

        if (conn != null) {
            lblDatabaseConnectionStatusIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/OKGreen.png"))); // NOI18N
            lblDatabseConnectionStatus.setText("Connected..");
            lblDatabseConnectionStatus.setForeground(new java.awt.Color(2, 183, 0));
        }

        dt();
        times();
        updatePartNo();
        //comboPartNo.setSelectedIndex(0);
        //System.out.println("the selected index"+comboPartNo.getSelectedIndex()+" "+comboPartNo.getSelectedItem());
        //comboPartNo.setSelectedIndex(-1);
        comboPartNo.setSelectedItem(null);
        System.out.println("the selected index" + comboPartNo.getSelectedIndex() + " " + comboPartNo.getSelectedItem());

        AutoCompleteDecorator.decorate(comboPartNo);
        btnHomePnl.requestFocus();

        //this.setExtendedState(Frame.MAXIMIZED_BOTH);
        //btnInventoryPnl.requestFocus();
        btnUpdate_IN_itemAdd.setVisible(false);
        btnDelete_IN_itemAdd.setVisible(false);
        btnUpdate_IN_order.setVisible(false);
        btnDelete_IN_order.setVisible(false);

        showItemsRecords(tblItem);
        showOrderRecords(tblOrder);

        btnHomePnl.requestFocus();
        //this.setExtendedState(Frame.MAXIMIZED_BOTH);
        //btnInventoryPnl.requestFocus();
        configItemsTbl();
        configOrderTbl();

    }
//    public void showOrderRecords(JTable table) {
//        try {
//            // Create a statement
//            stmt = conn.createStatement();
//
//            // Execute query to retrieve data
//            String sqlSelect = "SELECT * FROM user";
//            ResultSet res = stmt.executeQuery(sqlSelect);
//
//            // Use ResultSetConverter to convert ResultSet to DefaultTableModel
//            DefaultTableModel model = ResultSetConverter.resultSetToTableModel(res);
//
//            // Set the model to the JTable
//            table.setModel(model);
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        } finally {
//            // Close statement if needed
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
    // </editor-fold>

//    public void showRecods(JTable table) {
//        try {
//            stmt = conn.createStatement();
//            String sqlSelect = "SELECT * FROM vehiclePart";
//            ResultSet res = stmt.executeQuery(sqlSelect);
//            // Set the ResultSet data into the JTable
//            table.setModel(DbUtils.resultSetToTableModel(res));
//            //table.setModel(DbUtils.resultSetToTableModel(res));
//        } catch (SQLException e) {
//            // Handle SQL exceptions separately
//            JOptionPane.showMessageDialog(this, "SQL Exception: " + e.getMessage());
//        } catch (Exception e) {
//            // Handle other exceptions
//            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage());
//        }
//
//    }
    public void dt() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
        String dd = sdf.format(d);
        lblDate.setText(dd);
    }

    Timer t;
    SimpleDateFormat st;

    public void times() {
        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date dt = new Date();
                st = new SimpleDateFormat("hh:mm:ss a");
                String tt = st.format(dt);
                lblTime.setText(tt);
            }

        });
        t.start();
    }

    public void stopTimer() {
        if (t != null && t.isRunning()) {
            t.stop();
        }
    }

    public void configItemsTbl() {
        tblItem.getColumnModel().getColumn(0).setPreferredWidth(200);
        tblItem.getColumnModel().getColumn(1).setPreferredWidth(400);
        tblItem.getColumnModel().getColumn(2).setPreferredWidth(968);
        tblItem.getTableHeader().setForeground(new Color(23, 35, 51));

        tblItem.getTableHeader().setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        DefaultTableCellRenderer renderTblItem = (DefaultTableCellRenderer) tblItem.getTableHeader().getDefaultRenderer();
        renderTblItem.setHorizontalAlignment(jLabel1.CENTER);
        renderTblItem.setHorizontalAlignment(jLabel2.CENTER);
        renderTblItem.setHorizontalAlignment(jLabel3.CENTER);
    }

    public void configOrderTbl() {
        tblOrder.getColumnModel().getColumn(0).setPreferredWidth(784);
        tblOrder.getColumnModel().getColumn(1).setPreferredWidth(784);
        tblOrder.getTableHeader().setForeground(new Color(23, 35, 51));

        tblOrder.getTableHeader().setFont(new Font("Microsoft YaHei UI", Font.BOLD, 18));
        DefaultTableCellRenderer renderTblItem = (DefaultTableCellRenderer) tblOrder.getTableHeader().getDefaultRenderer();
        renderTblItem.setHorizontalAlignment(jLabel1.CENTER);
        renderTblItem.setHorizontalAlignment(jLabel2.CENTER);

    }

    public void showItemsRecords(JTable table) {
        try {
            // Create a statement
            stmt = conn.createStatement();

            // Execute query to retrieve data with custom sorting based on itemsId
            String sqlSelect = "SELECT * FROM items ORDER BY "
                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                    + "SUBSTRING(itemId, 1, 2), "
                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
            ResultSet res = stmt.executeQuery(sqlSelect);

            // Use ResultSetConverter to convert ResultSet to DefaultTableModel
            DefaultTableModel model = ResultSetConverter.resultSetToTableModel(res);

            // Set the model to the JTable
            table.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close statement if needed
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void showOrderRecords(JTable table) {
        try {
            // Create a statement
            stmt = conn.createStatement();

            // Execute query to retrieve data with custom sorting based on itemsId
            String sqlSelect = "SELECT * FROM orders ORDER BY "
                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                    + "SUBSTRING(itemId, 1, 2), "
                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
            ResultSet res = stmt.executeQuery(sqlSelect);

            // Use ResultSetConverter to convert ResultSet to DefaultTableModel
            DefaultTableModel model = ResultSetConverter.resultSetToTableModel(res);

            // Set the model to the JTable
            table.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close statement if needed
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void updatePartNo() {

        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM items";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String partNo = rs.getString("itemId");
                boolean exists = false;
                for (int i = 0; i < comboPartNo.getItemCount(); i++) {
                    if (partNo.equals(comboPartNo.getItemAt(i))) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    comboPartNo.addItem(partNo);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);

        }

    }

    ShortcutMap shortcutMap = new ShortcutMap();
    KeyStyle keyStyle = new KeyStyle();
    static private int selectedNavigationKey = 0;

    public void setSelectedNavigationKey(int key) {
        selectedNavigationKey = key;
    }

    public JTabbedPane getModulePnl() {
        return pnlModuleContainer;
    }

    public void setFocus(int key) {
        if (key == 0) {
            btnHomePnl.requestFocus();
        } else if (key == 1) {
            btnInventoryPnl.requestFocus();
        } else if (key == 2) {
            btnInvoicePnl.requestFocus();
        } else if (key == 3) {
            btnReturnPnl.requestFocus();
        } else if (key == 4) {
            btnSupplierPnl.requestFocus();
        } else if (key == 5) {
            btnCustomerPnl.requestFocus();
        } else if (key == 6) {
            btnUserPnl.requestFocus();
        }
    }

    public void navigationPnlSelector() {
        if (selectedNavigationKey == 0) {
            KeyStyle.setKeyColorGreen(btnHomePnl, btnHomeLbl);
            KeyStyle.setKeyColorBlue(btnInventoryPnl, btnInventoryLbl);
            KeyStyle.setKeyColorBlue(btnInvoicePnl, btnInvoiceLbl);
            KeyStyle.setKeyColorBlue(btnReturnPnl, btnReturnLbl);
            KeyStyle.setKeyColorBlue(btnSupplierPnl, btnSupplierLbl);
            KeyStyle.setKeyColorBlue(btnCustomerPnl, btnCustomerLbl);
            KeyStyle.setKeyColorBlue(btnUserPnl, btnUserLbl);
        } else if (selectedNavigationKey == 1) {
            KeyStyle.setKeyColorBlue(btnHomePnl, btnHomeLbl);
            KeyStyle.setKeyColorGreen(btnInventoryPnl, btnInventoryLbl);
            KeyStyle.setKeyColorBlue(btnInvoicePnl, btnInvoiceLbl);
            KeyStyle.setKeyColorBlue(btnReturnPnl, btnReturnLbl);
            KeyStyle.setKeyColorBlue(btnSupplierPnl, btnSupplierLbl);
            KeyStyle.setKeyColorBlue(btnCustomerPnl, btnCustomerLbl);
            KeyStyle.setKeyColorBlue(btnUserPnl, btnUserLbl);
        } else if (selectedNavigationKey == 2) {
            KeyStyle.setKeyColorBlue(btnHomePnl, btnHomeLbl);
            KeyStyle.setKeyColorBlue(btnInventoryPnl, btnInventoryLbl);
            KeyStyle.setKeyColorGreen(btnInvoicePnl, btnInvoiceLbl);
            KeyStyle.setKeyColorBlue(btnReturnPnl, btnReturnLbl);
            KeyStyle.setKeyColorBlue(btnSupplierPnl, btnSupplierLbl);
            KeyStyle.setKeyColorBlue(btnCustomerPnl, btnCustomerLbl);
            KeyStyle.setKeyColorBlue(btnUserPnl, btnUserLbl);
        } else if (selectedNavigationKey == 3) {
            KeyStyle.setKeyColorBlue(btnHomePnl, btnHomeLbl);
            KeyStyle.setKeyColorBlue(btnInventoryPnl, btnInventoryLbl);
            KeyStyle.setKeyColorBlue(btnInvoicePnl, btnInvoiceLbl);
            KeyStyle.setKeyColorGreen(btnReturnPnl, btnReturnLbl);
            KeyStyle.setKeyColorBlue(btnSupplierPnl, btnSupplierLbl);
            KeyStyle.setKeyColorBlue(btnCustomerPnl, btnCustomerLbl);
            KeyStyle.setKeyColorBlue(btnUserPnl, btnUserLbl);
        } else if (selectedNavigationKey == 4) {
            KeyStyle.setKeyColorBlue(btnHomePnl, btnHomeLbl);
            KeyStyle.setKeyColorBlue(btnInventoryPnl, btnInventoryLbl);
            KeyStyle.setKeyColorBlue(btnInvoicePnl, btnInvoiceLbl);
            KeyStyle.setKeyColorBlue(btnReturnPnl, btnReturnLbl);
            KeyStyle.setKeyColorGreen(btnSupplierPnl, btnSupplierLbl);
            KeyStyle.setKeyColorBlue(btnCustomerPnl, btnCustomerLbl);
            KeyStyle.setKeyColorBlue(btnUserPnl, btnUserLbl);
        } else if (selectedNavigationKey == 5) {
            KeyStyle.setKeyColorBlue(btnHomePnl, btnHomeLbl);
            KeyStyle.setKeyColorBlue(btnInventoryPnl, btnInventoryLbl);
            KeyStyle.setKeyColorBlue(btnInvoicePnl, btnInvoiceLbl);
            KeyStyle.setKeyColorBlue(btnReturnPnl, btnReturnLbl);
            KeyStyle.setKeyColorBlue(btnSupplierPnl, btnSupplierLbl);
            KeyStyle.setKeyColorGreen(btnCustomerPnl, btnCustomerLbl);
            KeyStyle.setKeyColorBlue(btnUserPnl, btnUserLbl);
        } else if (selectedNavigationKey == 6) {
            KeyStyle.setKeyColorBlue(btnHomePnl, btnHomeLbl);
            KeyStyle.setKeyColorBlue(btnInventoryPnl, btnInventoryLbl);
            KeyStyle.setKeyColorBlue(btnInvoicePnl, btnInvoiceLbl);
            KeyStyle.setKeyColorBlue(btnReturnPnl, btnReturnLbl);
            KeyStyle.setKeyColorBlue(btnSupplierPnl, btnSupplierLbl);
            KeyStyle.setKeyColorBlue(btnCustomerPnl, btnCustomerLbl);
            KeyStyle.setKeyColorGreen(btnUserPnl, btnUserLbl);
        }
    }

    public String removeLeadingZeros(String input) {
        if (input.length() > 2) {
            String firstTwoChars = input.substring(0, 2);
            String remainingChars = input.substring(2);
            // Remove leading zeros from remaining characters
            remainingChars = remainingChars.replaceFirst("^0+(?!$)", "");
            return firstTwoChars + remainingChars;
        } else {
            return input; // If the input is less than or equal to two characters, return it as is
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        btnSettingsPnl = new javax.swing.JPanel();
        btnSettingsLbl = new javax.swing.JLabel();
        btnSharedFolderPnl = new javax.swing.JPanel();
        btnsharedFolderLabel = new javax.swing.JLabel();
        btnComunicationPnl = new javax.swing.JPanel();
        btnComunicationLbl = new javax.swing.JLabel();
        btnNotificationPnl = new javax.swing.JPanel();
        btnNotificationLabel = new javax.swing.JLabel();
        pnlNavigation = new javax.swing.JPanel();
        btnHomePnl = new javax.swing.JPanel();
        btnHomeLbl = new javax.swing.JLabel();
        btnInventoryPnl = new javax.swing.JPanel();
        btnInventoryLbl = new javax.swing.JLabel();
        btnInvoicePnl = new javax.swing.JPanel();
        btnInvoiceLbl = new javax.swing.JLabel();
        btnReturnPnl = new javax.swing.JPanel();
        btnReturnLbl = new javax.swing.JLabel();
        btnSupplierPnl = new javax.swing.JPanel();
        btnSupplierLbl = new javax.swing.JLabel();
        btnCustomerPnl = new javax.swing.JPanel();
        btnCustomerLbl = new javax.swing.JLabel();
        btnUserPnl = new javax.swing.JPanel();
        btnUserLbl = new javax.swing.JLabel();
        btnClosePnl = new javax.swing.JPanel();
        btnCloseLbl = new javax.swing.JLabel();
        btnDBConnectionTestPnl = new javax.swing.JPanel();
        lblDatabaseConnectionStatusIcon = new javax.swing.JLabel();
        lblDatabseConnectionStatus = new javax.swing.JLabel();
        btnMinimizePnl = new javax.swing.JPanel();
        btnMinimizeLbl = new javax.swing.JLabel();
        pnlModuleContainer = new javax.swing.JTabbedPane();
        pnlHome = new javax.swing.JPanel();
        pnlInventory = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPartNo = new javax.swing.JTextField();
        txtVehicle = new javax.swing.JTextField();
        txtDiscription = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        pnlBtnContainer = new javax.swing.JPanel();
        btnAdd_IN_itemAdd = new javax.swing.JPanel();
        btnAddButtonLBL = new javax.swing.JLabel();
        btnUpdate_IN_itemAdd = new javax.swing.JPanel();
        btnUpdateButtonLBL = new javax.swing.JLabel();
        btnDelete_IN_itemAdd = new javax.swing.JPanel();
        btnDeleteButtonLBL = new javax.swing.JLabel();
        btnRefresh_IN_itemAdd = new javax.swing.JPanel();
        btnRefreshButtonLBL = new javax.swing.JLabel();
        btnPrint_IN_itemAdd = new javax.swing.JPanel();
        btnRefreshButtonLBL1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItem = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        pnlInvoice = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        lblVehicle_In_Order = new javax.swing.JLabel();
        lblDiscriptionIn_order = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        comboPartNo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnAdd_IN_order = new javax.swing.JPanel();
        btnAddButtonLBL1 = new javax.swing.JLabel();
        btnUpdate_IN_order = new javax.swing.JPanel();
        btnUpdateButtonLBL1 = new javax.swing.JLabel();
        btnDelete_IN_order = new javax.swing.JPanel();
        btnDeleteButtonLBL1 = new javax.swing.JLabel();
        btnRefresh_IN_order = new javax.swing.JPanel();
        btnRefreshButtonLBL2 = new javax.swing.JLabel();
        btnPrint_IN_order = new javax.swing.JPanel();
        btnRefreshButtonLBL3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtSerach = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        pnlReturn = new javax.swing.JPanel();
        pnlSupplier = new javax.swing.JPanel();
        pnlCustomer = new javax.swing.JPanel();
        pnlUser = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(23, 35, 51));

        lblTime.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("10:00:42 PM");

        lblDate.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDate.setText("2023-Jun-15");

        btnSettingsPnl.setBackground(new java.awt.Color(23, 35, 51));
        btnSettingsPnl.setToolTipText("Settings");
        btnSettingsPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSettingsPnlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSettingsPnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSettingsPnlMouseExited(evt);
            }
        });

        btnSettingsLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSettingsLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/settingsOrangeSmall34.png"))); // NOI18N

        javax.swing.GroupLayout btnSettingsPnlLayout = new javax.swing.GroupLayout(btnSettingsPnl);
        btnSettingsPnl.setLayout(btnSettingsPnlLayout);
        btnSettingsPnlLayout.setHorizontalGroup(
            btnSettingsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSettingsLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        btnSettingsPnlLayout.setVerticalGroup(
            btnSettingsPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSettingsLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnSharedFolderPnl.setBackground(new java.awt.Color(23, 35, 51));
        btnSharedFolderPnl.setToolTipText("Shared Folder");
        btnSharedFolderPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSharedFolderPnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSharedFolderPnlMouseExited(evt);
            }
        });

        btnsharedFolderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnsharedFolderLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sharedFolderYellowSmall34.png"))); // NOI18N

        javax.swing.GroupLayout btnSharedFolderPnlLayout = new javax.swing.GroupLayout(btnSharedFolderPnl);
        btnSharedFolderPnl.setLayout(btnSharedFolderPnlLayout);
        btnSharedFolderPnlLayout.setHorizontalGroup(
            btnSharedFolderPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnsharedFolderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        btnSharedFolderPnlLayout.setVerticalGroup(
            btnSharedFolderPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnsharedFolderLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnComunicationPnl.setBackground(new java.awt.Color(23, 35, 51));
        btnComunicationPnl.setToolTipText("Chat Room");
        btnComunicationPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnComunicationPnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnComunicationPnlMouseExited(evt);
            }
        });

        btnComunicationLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnComunicationLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/chatRoomGreenSmall34.png"))); // NOI18N

        javax.swing.GroupLayout btnComunicationPnlLayout = new javax.swing.GroupLayout(btnComunicationPnl);
        btnComunicationPnl.setLayout(btnComunicationPnlLayout);
        btnComunicationPnlLayout.setHorizontalGroup(
            btnComunicationPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnComunicationLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );
        btnComunicationPnlLayout.setVerticalGroup(
            btnComunicationPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnComunicationLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        btnNotificationPnl.setBackground(new java.awt.Color(23, 35, 51));
        btnNotificationPnl.setToolTipText("Notification");
        btnNotificationPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNotificationPnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNotificationPnlMouseExited(evt);
            }
        });

        btnNotificationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNotificationLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/notificationCeyanSmall34.png"))); // NOI18N

        javax.swing.GroupLayout btnNotificationPnlLayout = new javax.swing.GroupLayout(btnNotificationPnl);
        btnNotificationPnl.setLayout(btnNotificationPnlLayout);
        btnNotificationPnlLayout.setHorizontalGroup(
            btnNotificationPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNotificationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        btnNotificationPnlLayout.setVerticalGroup(
            btnNotificationPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNotificationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(lblTime)
                .addGap(95, 95, 95)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 564, Short.MAX_VALUE)
                .addComponent(btnNotificationPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnComunicationPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSharedFolderPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSettingsPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnComunicationPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNotificationPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSharedFolderPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSettingsPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 0, -1, -1));

        pnlNavigation.setBackground(new java.awt.Color(23, 35, 51));

        btnHomePnl.setBackground(new java.awt.Color(68, 194, 137));
        btnHomePnl.setToolTipText("Home");
        btnHomePnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomePnlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomePnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomePnlMouseExited(evt);
            }
        });
        btnHomePnl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnHomePnlKeyPressed(evt);
            }
        });

        btnHomeLbl.setFont(new java.awt.Font("Segoe UI Semilight", 1, 36)); // NOI18N
        btnHomeLbl.setForeground(new java.awt.Color(71, 120, 197));
        btnHomeLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHomeLbl.setText("Home");

        javax.swing.GroupLayout btnHomePnlLayout = new javax.swing.GroupLayout(btnHomePnl);
        btnHomePnl.setLayout(btnHomePnlLayout);
        btnHomePnlLayout.setHorizontalGroup(
            btnHomePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHomePnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHomeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnHomePnlLayout.setVerticalGroup(
            btnHomePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHomeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnInventoryPnl.setBackground(new java.awt.Color(23, 35, 51));
        btnInventoryPnl.setToolTipText("Inventory");
        btnInventoryPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInventoryPnlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInventoryPnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInventoryPnlMouseExited(evt);
            }
        });
        btnInventoryPnl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnInventoryPnlKeyPressed(evt);
            }
        });

        btnInventoryLbl.setFont(new java.awt.Font("Segoe UI Semilight", 1, 36)); // NOI18N
        btnInventoryLbl.setForeground(new java.awt.Color(204, 204, 204));
        btnInventoryLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnInventoryLbl.setText("Inventory");

        javax.swing.GroupLayout btnInventoryPnlLayout = new javax.swing.GroupLayout(btnInventoryPnl);
        btnInventoryPnl.setLayout(btnInventoryPnlLayout);
        btnInventoryPnlLayout.setHorizontalGroup(
            btnInventoryPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnInventoryPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInventoryLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnInventoryPnlLayout.setVerticalGroup(
            btnInventoryPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnInventoryLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnInvoicePnl.setBackground(new java.awt.Color(23, 35, 51));
        btnInvoicePnl.setToolTipText("Invoice");
        btnInvoicePnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInvoicePnlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInvoicePnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInvoicePnlMouseExited(evt);
            }
        });
        btnInvoicePnl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnInvoicePnlKeyPressed(evt);
            }
        });

        btnInvoiceLbl.setFont(new java.awt.Font("Segoe UI Semilight", 1, 36)); // NOI18N
        btnInvoiceLbl.setForeground(new java.awt.Color(204, 204, 204));
        btnInvoiceLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnInvoiceLbl.setText("Invoice");

        javax.swing.GroupLayout btnInvoicePnlLayout = new javax.swing.GroupLayout(btnInvoicePnl);
        btnInvoicePnl.setLayout(btnInvoicePnlLayout);
        btnInvoicePnlLayout.setHorizontalGroup(
            btnInvoicePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnInvoicePnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInvoiceLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnInvoicePnlLayout.setVerticalGroup(
            btnInvoicePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnInvoiceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnReturnPnl.setBackground(new java.awt.Color(23, 35, 51));
        btnReturnPnl.setToolTipText("Return");
        btnReturnPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReturnPnlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReturnPnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReturnPnlMouseExited(evt);
            }
        });
        btnReturnPnl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnReturnPnlKeyPressed(evt);
            }
        });

        btnReturnLbl.setFont(new java.awt.Font("Segoe UI Semilight", 1, 36)); // NOI18N
        btnReturnLbl.setForeground(new java.awt.Color(204, 204, 204));
        btnReturnLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnReturnLbl.setText("Return");

        javax.swing.GroupLayout btnReturnPnlLayout = new javax.swing.GroupLayout(btnReturnPnl);
        btnReturnPnl.setLayout(btnReturnPnlLayout);
        btnReturnPnlLayout.setHorizontalGroup(
            btnReturnPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnReturnPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReturnLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnReturnPnlLayout.setVerticalGroup(
            btnReturnPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnReturnLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnSupplierPnl.setBackground(new java.awt.Color(23, 35, 51));
        btnSupplierPnl.setToolTipText("Supplier");
        btnSupplierPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSupplierPnlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSupplierPnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSupplierPnlMouseExited(evt);
            }
        });
        btnSupplierPnl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSupplierPnlKeyPressed(evt);
            }
        });

        btnSupplierLbl.setFont(new java.awt.Font("Segoe UI Semilight", 1, 36)); // NOI18N
        btnSupplierLbl.setForeground(new java.awt.Color(204, 204, 204));
        btnSupplierLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSupplierLbl.setText("Supplier");

        javax.swing.GroupLayout btnSupplierPnlLayout = new javax.swing.GroupLayout(btnSupplierPnl);
        btnSupplierPnl.setLayout(btnSupplierPnlLayout);
        btnSupplierPnlLayout.setHorizontalGroup(
            btnSupplierPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSupplierPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSupplierLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnSupplierPnlLayout.setVerticalGroup(
            btnSupplierPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSupplierLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnCustomerPnl.setBackground(new java.awt.Color(23, 35, 51));
        btnCustomerPnl.setToolTipText("Customer");
        btnCustomerPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCustomerPnlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCustomerPnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCustomerPnlMouseExited(evt);
            }
        });
        btnCustomerPnl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCustomerPnlKeyPressed(evt);
            }
        });

        btnCustomerLbl.setFont(new java.awt.Font("Segoe UI Semilight", 1, 36)); // NOI18N
        btnCustomerLbl.setForeground(new java.awt.Color(204, 204, 204));
        btnCustomerLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCustomerLbl.setText("Customer");

        javax.swing.GroupLayout btnCustomerPnlLayout = new javax.swing.GroupLayout(btnCustomerPnl);
        btnCustomerPnl.setLayout(btnCustomerPnlLayout);
        btnCustomerPnlLayout.setHorizontalGroup(
            btnCustomerPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCustomerPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCustomerLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnCustomerPnlLayout.setVerticalGroup(
            btnCustomerPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCustomerLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnUserPnl.setBackground(new java.awt.Color(23, 35, 51));
        btnUserPnl.setToolTipText("User");
        btnUserPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUserPnlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUserPnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUserPnlMouseExited(evt);
            }
        });
        btnUserPnl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnUserPnlKeyPressed(evt);
            }
        });

        btnUserLbl.setFont(new java.awt.Font("Segoe UI Semilight", 1, 36)); // NOI18N
        btnUserLbl.setForeground(new java.awt.Color(204, 204, 204));
        btnUserLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUserLbl.setText("User");

        javax.swing.GroupLayout btnUserPnlLayout = new javax.swing.GroupLayout(btnUserPnl);
        btnUserPnl.setLayout(btnUserPnlLayout);
        btnUserPnlLayout.setHorizontalGroup(
            btnUserPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnUserPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUserLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnUserPnlLayout.setVerticalGroup(
            btnUserPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUserLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnClosePnl.setBackground(new java.awt.Color(23, 35, 51));
        btnClosePnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(71, 120, 197), 0, true));
        btnClosePnl.setToolTipText("Close");
        btnClosePnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClosePnlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClosePnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClosePnlMouseExited(evt);
            }
        });

        btnCloseLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCloseLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/closeGray.png"))); // NOI18N

        javax.swing.GroupLayout btnClosePnlLayout = new javax.swing.GroupLayout(btnClosePnl);
        btnClosePnl.setLayout(btnClosePnlLayout);
        btnClosePnlLayout.setHorizontalGroup(
            btnClosePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCloseLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
        );
        btnClosePnlLayout.setVerticalGroup(
            btnClosePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCloseLbl)
        );

        btnDBConnectionTestPnl.setBackground(new java.awt.Color(23, 35, 51));
        btnDBConnectionTestPnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(71, 120, 197), 0, true));
        btnDBConnectionTestPnl.setToolTipText("Connection");
        btnDBConnectionTestPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDBConnectionTestPnlMouseClicked(evt);
            }
        });

        lblDatabaseConnectionStatusIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDatabaseConnectionStatusIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/warningYellow.png"))); // NOI18N

        lblDatabseConnectionStatus.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDatabseConnectionStatus.setForeground(new java.awt.Color(255, 0, 0));
        lblDatabseConnectionStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDatabseConnectionStatus.setText("Warning !");

        javax.swing.GroupLayout btnDBConnectionTestPnlLayout = new javax.swing.GroupLayout(btnDBConnectionTestPnl);
        btnDBConnectionTestPnl.setLayout(btnDBConnectionTestPnlLayout);
        btnDBConnectionTestPnlLayout.setHorizontalGroup(
            btnDBConnectionTestPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDBConnectionTestPnlLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblDatabaseConnectionStatusIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblDatabseConnectionStatus)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        btnDBConnectionTestPnlLayout.setVerticalGroup(
            btnDBConnectionTestPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDBConnectionTestPnlLayout.createSequentialGroup()
                .addComponent(lblDatabaseConnectionStatusIcon)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(lblDatabseConnectionStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnMinimizePnl.setBackground(new java.awt.Color(23, 35, 51));
        btnMinimizePnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(71, 120, 197), 0, true));
        btnMinimizePnl.setToolTipText("Minimize");
        btnMinimizePnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizePnlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizePnlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizePnlMouseExited(evt);
            }
        });

        btnMinimizeLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMinimizeLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/minimizeGray.png"))); // NOI18N

        javax.swing.GroupLayout btnMinimizePnlLayout = new javax.swing.GroupLayout(btnMinimizePnl);
        btnMinimizePnl.setLayout(btnMinimizePnlLayout);
        btnMinimizePnlLayout.setHorizontalGroup(
            btnMinimizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMinimizeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        btnMinimizePnlLayout.setVerticalGroup(
            btnMinimizePnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMinimizeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlNavigationLayout = new javax.swing.GroupLayout(pnlNavigation);
        pnlNavigation.setLayout(pnlNavigationLayout);
        pnlNavigationLayout.setHorizontalGroup(
            pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHomePnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInventoryPnl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInvoicePnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnReturnPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSupplierPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCustomerPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnUserPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlNavigationLayout.createSequentialGroup()
                .addComponent(btnClosePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMinimizePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDBConnectionTestPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNavigationLayout.setVerticalGroup(
            pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavigationLayout.createSequentialGroup()
                .addGroup(pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnDBConnectionTestPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClosePnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlNavigationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnMinimizePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(90, 90, 90)
                .addComponent(btnHomePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnInventoryPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnInvoicePnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnReturnPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnSupplierPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCustomerPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnUserPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        getContentPane().add(pnlNavigation, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 740));

        pnlHome.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlHomeLayout = new javax.swing.GroupLayout(pnlHome);
        pnlHome.setLayout(pnlHomeLayout);
        pnlHomeLayout.setHorizontalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1240, Short.MAX_VALUE)
        );
        pnlHomeLayout.setVerticalGroup(
            pnlHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );

        pnlModuleContainer.addTab("Home", pnlHome);

        pnlInventory.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel1.setText("Vehicle :");

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel2.setText("Discription :");

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel3.setText("Part No:");

        txtPartNo.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtPartNo.setBorder(null);
        txtPartNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPartNoKeyReleased(evt);
            }
        });

        txtVehicle.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtVehicle.setBorder(null);
        txtVehicle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVehicleKeyReleased(evt);
            }
        });

        txtDiscription.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        txtDiscription.setBorder(null);
        txtDiscription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiscriptionKeyReleased(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(160, 160, 160));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        pnlBtnContainer.setBackground(new java.awt.Color(255, 255, 255));

        btnAdd_IN_itemAdd.setBackground(new java.awt.Color(68, 194, 137));
        btnAdd_IN_itemAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdd_IN_itemAddMouseClicked(evt);
            }
        });

        btnAddButtonLBL.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btnAddButtonLBL.setForeground(new java.awt.Color(255, 255, 255));
        btnAddButtonLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddButtonLBL.setText("ADD");

        javax.swing.GroupLayout btnAdd_IN_itemAddLayout = new javax.swing.GroupLayout(btnAdd_IN_itemAdd);
        btnAdd_IN_itemAdd.setLayout(btnAdd_IN_itemAddLayout);
        btnAdd_IN_itemAddLayout.setHorizontalGroup(
            btnAdd_IN_itemAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdd_IN_itemAddLayout.createSequentialGroup()
                .addComponent(btnAddButtonLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        btnAdd_IN_itemAddLayout.setVerticalGroup(
            btnAdd_IN_itemAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddButtonLBL, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        btnUpdate_IN_itemAdd.setBackground(new java.awt.Color(0, 153, 204));
        btnUpdate_IN_itemAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdate_IN_itemAddMouseClicked(evt);
            }
        });

        btnUpdateButtonLBL.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btnUpdateButtonLBL.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateButtonLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUpdateButtonLBL.setText("Update");

        javax.swing.GroupLayout btnUpdate_IN_itemAddLayout = new javax.swing.GroupLayout(btnUpdate_IN_itemAdd);
        btnUpdate_IN_itemAdd.setLayout(btnUpdate_IN_itemAddLayout);
        btnUpdate_IN_itemAddLayout.setHorizontalGroup(
            btnUpdate_IN_itemAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnUpdate_IN_itemAddLayout.createSequentialGroup()
                .addComponent(btnUpdateButtonLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        btnUpdate_IN_itemAddLayout.setVerticalGroup(
            btnUpdate_IN_itemAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUpdateButtonLBL, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        btnDelete_IN_itemAdd.setBackground(new java.awt.Color(204, 0, 51));
        btnDelete_IN_itemAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDelete_IN_itemAddMouseClicked(evt);
            }
        });

        btnDeleteButtonLBL.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btnDeleteButtonLBL.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteButtonLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDeleteButtonLBL.setText("Delete");

        javax.swing.GroupLayout btnDelete_IN_itemAddLayout = new javax.swing.GroupLayout(btnDelete_IN_itemAdd);
        btnDelete_IN_itemAdd.setLayout(btnDelete_IN_itemAddLayout);
        btnDelete_IN_itemAddLayout.setHorizontalGroup(
            btnDelete_IN_itemAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDelete_IN_itemAddLayout.createSequentialGroup()
                .addComponent(btnDeleteButtonLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );
        btnDelete_IN_itemAddLayout.setVerticalGroup(
            btnDelete_IN_itemAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDeleteButtonLBL, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        btnRefresh_IN_itemAdd.setBackground(new java.awt.Color(153, 153, 255));
        btnRefresh_IN_itemAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefresh_IN_itemAddMouseClicked(evt);
            }
        });

        btnRefreshButtonLBL.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btnRefreshButtonLBL.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshButtonLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRefreshButtonLBL.setText("Refresh");

        javax.swing.GroupLayout btnRefresh_IN_itemAddLayout = new javax.swing.GroupLayout(btnRefresh_IN_itemAdd);
        btnRefresh_IN_itemAdd.setLayout(btnRefresh_IN_itemAddLayout);
        btnRefresh_IN_itemAddLayout.setHorizontalGroup(
            btnRefresh_IN_itemAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRefresh_IN_itemAddLayout.createSequentialGroup()
                .addComponent(btnRefreshButtonLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 37, Short.MAX_VALUE))
        );
        btnRefresh_IN_itemAddLayout.setVerticalGroup(
            btnRefresh_IN_itemAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRefreshButtonLBL, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        btnPrint_IN_itemAdd.setBackground(new java.awt.Color(255, 204, 0));
        btnPrint_IN_itemAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrint_IN_itemAddMouseClicked(evt);
            }
        });

        btnRefreshButtonLBL1.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btnRefreshButtonLBL1.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshButtonLBL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRefreshButtonLBL1.setText("Print");

        javax.swing.GroupLayout btnPrint_IN_itemAddLayout = new javax.swing.GroupLayout(btnPrint_IN_itemAdd);
        btnPrint_IN_itemAdd.setLayout(btnPrint_IN_itemAddLayout);
        btnPrint_IN_itemAddLayout.setHorizontalGroup(
            btnPrint_IN_itemAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPrint_IN_itemAddLayout.createSequentialGroup()
                .addComponent(btnRefreshButtonLBL1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 37, Short.MAX_VALUE))
        );
        btnPrint_IN_itemAddLayout.setVerticalGroup(
            btnPrint_IN_itemAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRefreshButtonLBL1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlBtnContainerLayout = new javax.swing.GroupLayout(pnlBtnContainer);
        pnlBtnContainer.setLayout(pnlBtnContainerLayout);
        pnlBtnContainerLayout.setHorizontalGroup(
            pnlBtnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBtnContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd_IN_itemAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate_IN_itemAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete_IN_itemAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh_IN_itemAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint_IN_itemAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pnlBtnContainerLayout.setVerticalGroup(
            pnlBtnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBtnContainerLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlBtnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrint_IN_itemAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh_IN_itemAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete_IN_itemAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate_IN_itemAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd_IN_itemAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tblItem.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "itemID", "Vehicle Name", "Discription"
            }
        ));
        tblItem.setRowHeight(35);
        tblItem.setRowMargin(3);
        tblItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblItem);

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInventoryLayout = new javax.swing.GroupLayout(pnlInventory);
        pnlInventory.setLayout(pnlInventoryLayout);
        pnlInventoryLayout.setHorizontalGroup(
            pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventoryLayout.createSequentialGroup()
                .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlInventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInventoryLayout.createSequentialGroup()
                        .addComponent(pnlBtnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(318, 318, 318))
                    .addGroup(pnlInventoryLayout.createSequentialGroup()
                        .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator1)
                                .addGroup(pnlInventoryLayout.createSequentialGroup()
                                    .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtVehicle)
                                        .addComponent(txtPartNo)
                                        .addComponent(txtDiscription, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlInventoryLayout.setVerticalGroup(
            pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInventoryLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPartNo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(3, 3, 3)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiscription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBtnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
        );

        pnlModuleContainer.addTab("Inventory", pnlInventory);

        pnlInvoice.setBackground(new java.awt.Color(255, 255, 255));
        pnlInvoice.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel4.setText("Part No:");
        pnlInvoice.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 12, 153, -1));

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel5.setText("Qty       :");
        pnlInvoice.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 61, 153, -1));

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel6.setText("Discription :");
        pnlInvoice.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, -1, -1));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(160, 160, 160));
        pnlInvoice.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 52, 440, -1));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        pnlInvoice.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 440, -1));

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        jLabel7.setText("Vehicle       :");
        pnlInvoice.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 153, -1));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(160, 160, 160));
        pnlInvoice.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 373, -1));

        jLabel8.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        pnlInvoice.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1216, 131, 153, -1));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        pnlInvoice.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 369, -1));

        lblVehicle_In_Order.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        pnlInvoice.add(lblVehicle_In_Order, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 216, 34));

        lblDiscriptionIn_order.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        pnlInvoice.add(lblDiscriptionIn_order, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 500, 40));

        txtQty.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        pnlInvoice.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 64, 279, 34));

        comboPartNo.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        comboPartNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPartNoActionPerformed(evt);
            }
        });
        pnlInvoice.add(comboPartNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 16, 279, 32));

        tblOrder.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "itemID", "Qty"
            }
        ));
        tblOrder.setRowHeight(35);
        tblOrder.setRowMargin(3);
        tblOrder.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblOrderFocusLost(evt);
            }
        });
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblOrder);

        pnlInvoice.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 218, 1245, 480));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnAdd_IN_order.setBackground(new java.awt.Color(68, 194, 137));
        btnAdd_IN_order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdd_IN_orderMouseClicked(evt);
            }
        });

        btnAddButtonLBL1.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btnAddButtonLBL1.setForeground(new java.awt.Color(255, 255, 255));
        btnAddButtonLBL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddButtonLBL1.setText("ADD");

        javax.swing.GroupLayout btnAdd_IN_orderLayout = new javax.swing.GroupLayout(btnAdd_IN_order);
        btnAdd_IN_order.setLayout(btnAdd_IN_orderLayout);
        btnAdd_IN_orderLayout.setHorizontalGroup(
            btnAdd_IN_orderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnAdd_IN_orderLayout.createSequentialGroup()
                .addComponent(btnAddButtonLBL1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        btnAdd_IN_orderLayout.setVerticalGroup(
            btnAdd_IN_orderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddButtonLBL1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        btnUpdate_IN_order.setBackground(new java.awt.Color(0, 153, 204));
        btnUpdate_IN_order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdate_IN_orderMouseClicked(evt);
            }
        });

        btnUpdateButtonLBL1.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btnUpdateButtonLBL1.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateButtonLBL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUpdateButtonLBL1.setText("Update");

        javax.swing.GroupLayout btnUpdate_IN_orderLayout = new javax.swing.GroupLayout(btnUpdate_IN_order);
        btnUpdate_IN_order.setLayout(btnUpdate_IN_orderLayout);
        btnUpdate_IN_orderLayout.setHorizontalGroup(
            btnUpdate_IN_orderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnUpdate_IN_orderLayout.createSequentialGroup()
                .addComponent(btnUpdateButtonLBL1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        btnUpdate_IN_orderLayout.setVerticalGroup(
            btnUpdate_IN_orderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUpdateButtonLBL1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        btnDelete_IN_order.setBackground(new java.awt.Color(204, 0, 51));
        btnDelete_IN_order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDelete_IN_orderMouseClicked(evt);
            }
        });

        btnDeleteButtonLBL1.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btnDeleteButtonLBL1.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteButtonLBL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDeleteButtonLBL1.setText("Delete");

        javax.swing.GroupLayout btnDelete_IN_orderLayout = new javax.swing.GroupLayout(btnDelete_IN_order);
        btnDelete_IN_order.setLayout(btnDelete_IN_orderLayout);
        btnDelete_IN_orderLayout.setHorizontalGroup(
            btnDelete_IN_orderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDelete_IN_orderLayout.createSequentialGroup()
                .addComponent(btnDeleteButtonLBL1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );
        btnDelete_IN_orderLayout.setVerticalGroup(
            btnDelete_IN_orderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDeleteButtonLBL1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        btnRefresh_IN_order.setBackground(new java.awt.Color(153, 153, 255));
        btnRefresh_IN_order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefresh_IN_orderMouseClicked(evt);
            }
        });

        btnRefreshButtonLBL2.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btnRefreshButtonLBL2.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshButtonLBL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRefreshButtonLBL2.setText("Refresh");

        javax.swing.GroupLayout btnRefresh_IN_orderLayout = new javax.swing.GroupLayout(btnRefresh_IN_order);
        btnRefresh_IN_order.setLayout(btnRefresh_IN_orderLayout);
        btnRefresh_IN_orderLayout.setHorizontalGroup(
            btnRefresh_IN_orderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRefresh_IN_orderLayout.createSequentialGroup()
                .addComponent(btnRefreshButtonLBL2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 37, Short.MAX_VALUE))
        );
        btnRefresh_IN_orderLayout.setVerticalGroup(
            btnRefresh_IN_orderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRefreshButtonLBL2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnPrint_IN_order.setBackground(new java.awt.Color(255, 204, 0));
        btnPrint_IN_order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrint_IN_orderMouseClicked(evt);
            }
        });

        btnRefreshButtonLBL3.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btnRefreshButtonLBL3.setForeground(new java.awt.Color(255, 255, 255));
        btnRefreshButtonLBL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRefreshButtonLBL3.setText("Print");

        javax.swing.GroupLayout btnPrint_IN_orderLayout = new javax.swing.GroupLayout(btnPrint_IN_order);
        btnPrint_IN_order.setLayout(btnPrint_IN_orderLayout);
        btnPrint_IN_orderLayout.setHorizontalGroup(
            btnPrint_IN_orderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPrint_IN_orderLayout.createSequentialGroup()
                .addComponent(btnRefreshButtonLBL3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 37, Short.MAX_VALUE))
        );
        btnPrint_IN_orderLayout.setVerticalGroup(
            btnPrint_IN_orderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRefreshButtonLBL3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd_IN_order, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate_IN_order, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete_IN_order, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRefresh_IN_order, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint_IN_order, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelete_IN_order, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate_IN_order, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd_IN_order, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh_IN_order, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrint_IN_order, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        pnlInvoice.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 830, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 11, 0)));

        txtSerach.setBorder(null);
        txtSerach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSerachKeyReleased(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/searchOrange.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtSerach, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtSerach, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlInvoice.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, -1, -1));

        pnlModuleContainer.addTab("Invoice", pnlInvoice);

        javax.swing.GroupLayout pnlReturnLayout = new javax.swing.GroupLayout(pnlReturn);
        pnlReturn.setLayout(pnlReturnLayout);
        pnlReturnLayout.setHorizontalGroup(
            pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1240, Short.MAX_VALUE)
        );
        pnlReturnLayout.setVerticalGroup(
            pnlReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );

        pnlModuleContainer.addTab("Return", pnlReturn);

        javax.swing.GroupLayout pnlSupplierLayout = new javax.swing.GroupLayout(pnlSupplier);
        pnlSupplier.setLayout(pnlSupplierLayout);
        pnlSupplierLayout.setHorizontalGroup(
            pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1240, Short.MAX_VALUE)
        );
        pnlSupplierLayout.setVerticalGroup(
            pnlSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );

        pnlModuleContainer.addTab("Supplier", pnlSupplier);

        javax.swing.GroupLayout pnlCustomerLayout = new javax.swing.GroupLayout(pnlCustomer);
        pnlCustomer.setLayout(pnlCustomerLayout);
        pnlCustomerLayout.setHorizontalGroup(
            pnlCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1240, Short.MAX_VALUE)
        );
        pnlCustomerLayout.setVerticalGroup(
            pnlCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );

        pnlModuleContainer.addTab("Customer", pnlCustomer);

        javax.swing.GroupLayout pnlUserLayout = new javax.swing.GroupLayout(pnlUser);
        pnlUser.setLayout(pnlUserLayout);
        pnlUserLayout.setHorizontalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1240, Short.MAX_VALUE)
        );
        pnlUserLayout.setVerticalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );

        pnlModuleContainer.addTab("User", pnlUser);

        getContentPane().add(pnlModuleContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 11, 1240, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnCustomerPnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCustomerPnlMouseEntered
        KeyStyle.setKeyColorGreen(btnCustomerPnl, btnCustomerLbl);

    }//GEN-LAST:event_btnCustomerPnlMouseEntered

    private void btnCustomerPnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCustomerPnlMouseExited
        KeyStyle.setKeyColorBlue(btnCustomerPnl, btnCustomerLbl);
        navigationPnlSelector();

    }//GEN-LAST:event_btnCustomerPnlMouseExited

    private void btnHomePnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomePnlMouseEntered
        KeyStyle.setKeyColorGreen(btnHomePnl, btnHomeLbl);
    }//GEN-LAST:event_btnHomePnlMouseEntered

    private void btnHomePnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomePnlMouseExited
        KeyStyle.setKeyColorBlue(btnHomePnl, btnHomeLbl);
        navigationPnlSelector();
    }//GEN-LAST:event_btnHomePnlMouseExited

    private void btnInventoryPnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventoryPnlMouseEntered
        KeyStyle.setKeyColorGreen(btnInventoryPnl, btnInventoryLbl);
    }//GEN-LAST:event_btnInventoryPnlMouseEntered

    private void btnInventoryPnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventoryPnlMouseExited
        KeyStyle.setKeyColorBlue(btnInventoryPnl, btnInventoryLbl);
        navigationPnlSelector();
    }//GEN-LAST:event_btnInventoryPnlMouseExited

    private void btnInvoicePnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInvoicePnlMouseEntered
        KeyStyle.setKeyColorGreen(btnInvoicePnl, btnInvoiceLbl);
    }//GEN-LAST:event_btnInvoicePnlMouseEntered

    private void btnInvoicePnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInvoicePnlMouseExited
        KeyStyle.setKeyColorBlue(btnInvoicePnl, btnInvoiceLbl);
        navigationPnlSelector();
    }//GEN-LAST:event_btnInvoicePnlMouseExited

    private void btnReturnPnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnPnlMouseEntered
        KeyStyle.setKeyColorGreen(btnReturnPnl, btnReturnLbl);
    }//GEN-LAST:event_btnReturnPnlMouseEntered

    private void btnReturnPnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnPnlMouseExited
        KeyStyle.setKeyColorBlue(btnReturnPnl, btnReturnLbl);
        navigationPnlSelector();
    }//GEN-LAST:event_btnReturnPnlMouseExited

    private void btnSupplierPnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupplierPnlMouseEntered
        KeyStyle.setKeyColorGreen(btnSupplierPnl, btnSupplierLbl);
    }//GEN-LAST:event_btnSupplierPnlMouseEntered

    private void btnSupplierPnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupplierPnlMouseExited
        KeyStyle.setKeyColorBlue(btnSupplierPnl, btnSupplierLbl);
        navigationPnlSelector();
    }//GEN-LAST:event_btnSupplierPnlMouseExited

    private void btnUserPnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUserPnlMouseEntered
        KeyStyle.setKeyColorGreen(btnUserPnl, btnUserLbl);
    }//GEN-LAST:event_btnUserPnlMouseEntered

    private void btnUserPnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUserPnlMouseExited
        KeyStyle.setKeyColorBlue(btnUserPnl, btnUserLbl);
        navigationPnlSelector();
    }//GEN-LAST:event_btnUserPnlMouseExited

    private void btnCustomerPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCustomerPnlMouseClicked
        selectedNavigationKey = 5;
        navigationPnlSelector();
        pnlModuleContainer.setSelectedIndex(5);
    }//GEN-LAST:event_btnCustomerPnlMouseClicked

    private void btnHomePnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomePnlMouseClicked
        selectedNavigationKey = 0;
        navigationPnlSelector();
        pnlModuleContainer.setSelectedIndex(0);

        btnAdd_IN_itemAdd.setVisible(true);
        tblItem.clearSelection();
        txtPartNo.setText("");
        txtVehicle.setText("");
        txtDiscription.setText("");
        btnUpdate_IN_itemAdd.setVisible(false);
        btnDelete_IN_itemAdd.setVisible(false);

        txtPartNo.setEditable(true);
        showItemsRecords(tblItem);
        configItemsTbl();
    }//GEN-LAST:event_btnHomePnlMouseClicked

    private void btnInventoryPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventoryPnlMouseClicked
        selectedNavigationKey = 1;
        navigationPnlSelector();
        pnlModuleContainer.setSelectedIndex(1);

        btnAdd_IN_itemAdd.setVisible(true);
        tblItem.clearSelection();
        txtPartNo.setText("");
        txtVehicle.setText("");
        txtDiscription.setText("");
        btnUpdate_IN_itemAdd.setVisible(false);
        btnDelete_IN_itemAdd.setVisible(false);

        txtPartNo.setEditable(true);
        showItemsRecords(tblItem);
        configItemsTbl();
    }//GEN-LAST:event_btnInventoryPnlMouseClicked

    private void btnInvoicePnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInvoicePnlMouseClicked
        selectedNavigationKey = 2;
        navigationPnlSelector();
        pnlModuleContainer.setSelectedIndex(2);

        btnAdd_IN_order.setVisible(true);
        tblOrder.clearSelection();
        comboPartNo.setSelectedItem(null);
        lblVehicle_In_Order.setText("");
        lblDiscriptionIn_order.setText("");
        txtQty.setText("");

        btnUpdate_IN_order.setVisible(false);
        btnDelete_IN_order.setVisible(false);

        showOrderRecords(tblItem);
        configOrderTbl();


    }//GEN-LAST:event_btnInvoicePnlMouseClicked

    private void btnReturnPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReturnPnlMouseClicked
        selectedNavigationKey = 3;
        navigationPnlSelector();
        pnlModuleContainer.setSelectedIndex(3);
    }//GEN-LAST:event_btnReturnPnlMouseClicked

    private void btnSupplierPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupplierPnlMouseClicked
        selectedNavigationKey = 4;
        navigationPnlSelector();
        pnlModuleContainer.setSelectedIndex(4);
    }//GEN-LAST:event_btnSupplierPnlMouseClicked

    private void btnUserPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUserPnlMouseClicked
        selectedNavigationKey = 6;
        navigationPnlSelector();
        pnlModuleContainer.setSelectedIndex(6);
    }//GEN-LAST:event_btnUserPnlMouseClicked

    private void btnHomePnlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnHomePnlKeyPressed
        shortcutMap.DashboardShortcutMap(evt, this);
    }//GEN-LAST:event_btnHomePnlKeyPressed

    private void btnInventoryPnlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnInventoryPnlKeyPressed
        shortcutMap.DashboardShortcutMap(evt, this);
    }//GEN-LAST:event_btnInventoryPnlKeyPressed

    private void btnInvoicePnlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnInvoicePnlKeyPressed
        shortcutMap.DashboardShortcutMap(evt, this);
    }//GEN-LAST:event_btnInvoicePnlKeyPressed

    private void btnReturnPnlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnReturnPnlKeyPressed
        shortcutMap.DashboardShortcutMap(evt, this);
    }//GEN-LAST:event_btnReturnPnlKeyPressed

    private void btnSupplierPnlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSupplierPnlKeyPressed
        shortcutMap.DashboardShortcutMap(evt, this);
    }//GEN-LAST:event_btnSupplierPnlKeyPressed

    private void btnCustomerPnlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCustomerPnlKeyPressed
        shortcutMap.DashboardShortcutMap(evt, this);
    }//GEN-LAST:event_btnCustomerPnlKeyPressed

    private void btnUserPnlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUserPnlKeyPressed
        shortcutMap.DashboardShortcutMap(evt, this);
    }//GEN-LAST:event_btnUserPnlKeyPressed

    private void btnDBConnectionTestPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDBConnectionTestPnlMouseClicked
        JOptionPane.showMessageDialog(null, "Database connection Unsucsessfull!\n"
                + "Database LocalInventory is Notconnected \n");
    }//GEN-LAST:event_btnDBConnectionTestPnlMouseClicked

    private void btnClosePnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClosePnlMouseEntered
        keyStyle.setCloseButtonColorRed(btnCloseLbl);
    }//GEN-LAST:event_btnClosePnlMouseEntered

    private void btnClosePnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClosePnlMouseExited
        keyStyle.setCloseButtonColorGray(btnCloseLbl);
    }//GEN-LAST:event_btnClosePnlMouseExited

    private void btnMinimizePnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizePnlMouseEntered
        keyStyle.setMinimizeButtonColorBlue(btnMinimizeLbl);
    }//GEN-LAST:event_btnMinimizePnlMouseEntered

    private void btnMinimizePnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizePnlMouseExited
        keyStyle.setMinimizeButtonColorGray(btnMinimizeLbl);
    }//GEN-LAST:event_btnMinimizePnlMouseExited

    private void btnClosePnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClosePnlMouseClicked
        stopTimer();
        this.dispose();


    }//GEN-LAST:event_btnClosePnlMouseClicked

    private void btnMinimizePnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizePnlMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizePnlMouseClicked

    private void btnSettingsPnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingsPnlMouseEntered
        keyStyle.setSettingsOrangeIconBig(btnSettingsLbl);
    }//GEN-LAST:event_btnSettingsPnlMouseEntered

    private void btnSettingsPnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingsPnlMouseExited
        keyStyle.setSettingsOrangeIconSmall(btnSettingsLbl);
    }//GEN-LAST:event_btnSettingsPnlMouseExited

    private void btnSharedFolderPnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSharedFolderPnlMouseEntered
        keyStyle.setShearedFolderYellowIconBig(btnsharedFolderLabel);
    }//GEN-LAST:event_btnSharedFolderPnlMouseEntered

    private void btnSharedFolderPnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSharedFolderPnlMouseExited
        keyStyle.setShearedFolderYellowIconSmall(btnsharedFolderLabel);
    }//GEN-LAST:event_btnSharedFolderPnlMouseExited

    private void btnComunicationPnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComunicationPnlMouseEntered
        keyStyle.setChatRoomGreenIconBig(btnComunicationLbl);
    }//GEN-LAST:event_btnComunicationPnlMouseEntered

    private void btnComunicationPnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComunicationPnlMouseExited
        keyStyle.setChatRoomGreenIconSmall(btnComunicationLbl);
    }//GEN-LAST:event_btnComunicationPnlMouseExited

    private void btnNotificationPnlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNotificationPnlMouseEntered
        keyStyle.setNotificationIconBig(btnNotificationLabel);
    }//GEN-LAST:event_btnNotificationPnlMouseEntered

    private void btnNotificationPnlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNotificationPnlMouseExited
        keyStyle.setNotificationIconSmall(btnNotificationLabel);
    }//GEN-LAST:event_btnNotificationPnlMouseExited

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            //D:\Standalone project Backup\Interface\MLT 8.2.24\src\Reports\report1.jrxml
            //JasperDesign jdesign = JRXmlLoader.load("D:\\Standalone project Backup\\Interface\\MLT 8.2.24\\src\\Reports\\OrderList.jrxml");
            //JasperDesign jdesign = JRXmlLoader.load("src\\Reports\\OrderList.jrxml");
            JasperDesign jdesign = JRXmlLoader.load(PathSet.getOrderListPath());
            String quary = "SELECT * FROM Orders INNER JOIN Items ON Orders.itemID = Items.itemID;";

            JRDesignQuery updateJRDesignQuery = new JRDesignQuery();
            updateJRDesignQuery.setText(quary);

            jdesign.setQuery(updateJRDesignQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jReport, null, conn);

            //JasperViewer.HIDE_ON_CLOSE();
            JasperViewer.viewReport(jprint);

            //JasperViewer viewer = new JasperViewer(jprint, false);
            //viewer.setDefaultCloseOperation(viewer.HIDE_ON_CLOSE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnAdd_IN_itemAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdd_IN_itemAddMouseClicked

        String No = txtPartNo.getText();
        String vehicle = txtVehicle.getText();
        String discription = txtDiscription.getText();

        String partNo = removeLeadingZeros(No);

        if (partNo == null || partNo.trim().isEmpty() || !partNo.matches("[a-zA-Z]{2}[1-9][0-9]*")) {
            JOptionPane.showMessageDialog(null, "Insert a valid PartNo", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (vehicle == null || vehicle.trim().isEmpty() || !vehicle.matches("[a-zA-Z]{3}[a-zA-Z0-9]*")) {
            JOptionPane.showMessageDialog(null, "Insert a valid Vehicle Name", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            try {

                stmt = conn.createStatement();
                String sql = "SELECT * FROM items WHERE itemId = '" + partNo + "'";
                rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    JOptionPane.showMessageDialog(this, "part number haas been registerd an other item");

                } else {
                    try {
                        stmt = conn.createStatement();
                        String sql2 = "INSERT INTO items (itemId,vehicleName,itemDiscription) VALUES ('" + partNo + "', '" + vehicle + "','" + discription + "');";
                        stmt.executeUpdate(sql2);

                    } catch (SQLException ex) {
                        Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (Exception e) {
            }
            showItemsRecords(tblItem);
            configItemsTbl();

            String newADdedData;
            for (int i = 0; i < tblItem.getRowCount(); i++) {
                newADdedData = (String) tblItem.getModel().getValueAt(i, 0);
                if (newADdedData.equals(partNo)) {
                    tblItem.setRowSelectionInterval(i, i);
                    tblItem.scrollRectToVisible(tblItem.getCellRect(i, 0, true));
                    break;
                }

            }

            //showRecods(tblItem);
            txtPartNo.setText("");
            txtVehicle.setText("");
            txtDiscription.setText("");

        }


    }//GEN-LAST:event_btnAdd_IN_itemAddMouseClicked

    private void tblItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemMouseClicked
        try {
            btnAdd_IN_itemAdd.setVisible(false);
            int rowIndex = tblItem.getSelectedRow();
            int colmnIndex = 0;
            String selectedItem = (String) tblItem.getModel().getValueAt(rowIndex, colmnIndex);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM items WHERE itemId = '" + selectedItem + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {

                txtPartNo.setText(rs.getString("itemId"));
                txtVehicle.setText(rs.getString("vehicleName"));
                txtDiscription.setText(rs.getString("itemDiscription"));
                btnUpdate_IN_itemAdd.setVisible(true);
                btnDelete_IN_itemAdd.setVisible(true);
                txtPartNo.setEditable(false);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_tblItemMouseClicked

    private void btnUpdate_IN_itemAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdate_IN_itemAddMouseClicked

        String partNo = txtPartNo.getText();
        String vehicle = txtVehicle.getText();
        String discription = txtDiscription.getText();

        if (vehicle == null || vehicle.trim().isEmpty() || !vehicle.matches("[a-zA-Z]{3}[a-zA-Z0-9]*")) {
            JOptionPane.showMessageDialog(null, "Insert a valid Vehicle Name", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            try {
                stmt = conn.createStatement();
                String sql = "UPDATE items SET vehicleName = '" + vehicle + "', itemDiscription = '" + discription + "' WHERE itemId = '" + partNo + "';";
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            btnAdd_IN_itemAdd.setVisible(true);
            tblItem.clearSelection();
            txtPartNo.setText("");
            txtVehicle.setText("");
            txtDiscription.setText("");
            btnUpdate_IN_itemAdd.setVisible(false);

            txtPartNo.setEnabled(true);
            showItemsRecords(tblItem);
            configItemsTbl();

        }


    }//GEN-LAST:event_btnUpdate_IN_itemAddMouseClicked

    private void btnRefresh_IN_itemAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefresh_IN_itemAddMouseClicked

        btnAdd_IN_itemAdd.setVisible(true);
        tblItem.clearSelection();
        txtPartNo.setText("");
        txtVehicle.setText("");
        txtDiscription.setText("");
        btnUpdate_IN_itemAdd.setVisible(false);
        btnDelete_IN_itemAdd.setVisible(false);

        txtPartNo.setEditable(true);
        showItemsRecords(tblItem);
        configItemsTbl();

    }//GEN-LAST:event_btnRefresh_IN_itemAddMouseClicked

    private void btnDelete_IN_itemAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelete_IN_itemAddMouseClicked

        int rowIndex = tblItem.getSelectedRow();
        int selectedRowCount = tblItem.getSelectedRowCount();
        if (rowIndex < 0) {

            JOptionPane.showMessageDialog(null, "Select the part you want to delete");
        } else if (selectedRowCount > 1) {
            JOptionPane.showMessageDialog(null, "Multiple selection Not acceptable", "Error", JOptionPane.ERROR_MESSAGE);
            btnAdd_IN_itemAdd.setVisible(true);
            tblItem.clearSelection();
            txtPartNo.setText("");
            txtVehicle.setText("");
            txtDiscription.setText("");
            btnUpdate_IN_itemAdd.setVisible(false);
            btnDelete_IN_itemAdd.setVisible(false);
        } else {
            int yesOrNo = JOptionPane.showConfirmDialog(this, "Do You want to Delete this part ?");
            if (yesOrNo == JOptionPane.YES_OPTION) {
                try {
                    rowIndex = tblItem.getSelectedRow();
                    int colmnIndex = 0;
                    String selectedPart = (String) tblItem.getModel().getValueAt(rowIndex, colmnIndex);
                    stmt = conn.createStatement();
                    String sql = "DELETE FROM items WHERE itemId = '" + selectedPart + "';";

                    stmt.executeUpdate(sql);

                    JOptionPane.showMessageDialog(null, "Deletion Successfull...");
                    btnAdd_IN_itemAdd.setVisible(true);
                    tblItem.clearSelection();
                    txtPartNo.setText("");
                    txtVehicle.setText("");
                    txtDiscription.setText("");
                    btnUpdate_IN_itemAdd.setVisible(false);
                    btnDelete_IN_itemAdd.setVisible(false);

                    txtPartNo.setEditable(true);
                    showItemsRecords(tblItem);
                    configItemsTbl();

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(this, e);
                }
            } else if (yesOrNo == JOptionPane.NO_OPTION) {

                btnAdd_IN_itemAdd.setVisible(true);
                tblItem.clearSelection();
                txtPartNo.setText("");
                txtVehicle.setText("");
                txtDiscription.setText("");
                btnUpdate_IN_itemAdd.setVisible(false);
                btnDelete_IN_itemAdd.setVisible(false);

                txtPartNo.setEnabled(true);
                showItemsRecords(tblItem);
                configItemsTbl();
            } else {

            }
        }

    }//GEN-LAST:event_btnDelete_IN_itemAddMouseClicked

    private void txtPartNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPartNoKeyReleased
        txtPartNo.setText(txtPartNo.getText().toUpperCase());
        try {
            if (txtPartNo.getText().isEmpty() && txtVehicle.getText().isEmpty() && txtDiscription.getText().isEmpty()) {
                showItemsRecords(tblItem);
                configItemsTbl();
            } else {

                String No = txtPartNo.getText(); //itm
                String vehicle = txtVehicle.getText();//com
                String discription = txtDiscription.getText();//cat

                String itm = removeLeadingZeros(No);

                if (vehicle.equals("") && itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemDiscription LIKE '%" + discription + "%' ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (vehicle.equals("") && !itm.equals("") && discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemId LIKE '%" + itm + "%' ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (vehicle.equals("") && !itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemId LIKE '%" + itm + "%' AND itemDiscription LIKE '%" + discription + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && itm.equals("") && discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemDiscription LIKE '%" + discription + "%' AND vehicleName LIKE '%" + vehicle + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && !itm.equals("") && discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' AND itemId LIKE '%" + itm + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && !itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' AND itemId LIKE '%" + itm + "%' AND itemDiscription LIKE '%" + discription + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not found any Item ...");
        }

    }//GEN-LAST:event_txtPartNoKeyReleased

    private void txtVehicleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVehicleKeyReleased
        txtVehicle.setText(txtVehicle.getText().toUpperCase());
        try {
            if (txtPartNo.getText().isEmpty() && txtVehicle.getText().isEmpty() && txtDiscription.getText().isEmpty()) {
                showItemsRecords(tblItem);
                configItemsTbl();
            } else {

                String No = txtPartNo.getText(); //itm
                String vehicle = txtVehicle.getText();//com
                String discription = txtDiscription.getText();//cat

                String itm = removeLeadingZeros(No);

                if (vehicle.equals("") && itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemDiscription LIKE '%" + discription + "%' ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (vehicle.equals("") && !itm.equals("") && discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemId LIKE '%" + itm + "%' ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (vehicle.equals("") && !itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemId LIKE '%" + itm + "%' AND itemDiscription LIKE '%" + discription + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && itm.equals("") && discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemDiscription LIKE '%" + discription + "%' AND vehicleName LIKE '%" + vehicle + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && !itm.equals("") && discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' AND itemId LIKE '%" + itm + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && !itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' AND itemId LIKE '%" + itm + "%' AND itemDiscription LIKE '%" + discription + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not found any Item ...");
        }
    }//GEN-LAST:event_txtVehicleKeyReleased

    private void txtDiscriptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiscriptionKeyReleased
        txtDiscription.setText(txtDiscription.getText().toUpperCase());
        try {
            if (txtPartNo.getText().isEmpty() && txtVehicle.getText().isEmpty() && txtDiscription.getText().isEmpty()) {
                showItemsRecords(tblItem);
                configItemsTbl();
            } else {

                String No = txtPartNo.getText(); //itm
                String vehicle = txtVehicle.getText();//com
                String discription = txtDiscription.getText();//cat

                String itm = removeLeadingZeros(No);

                if (vehicle.equals("") && itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemDiscription LIKE '%" + discription + "%' ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (vehicle.equals("") && !itm.equals("") && discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemId LIKE '%" + itm + "%' ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (vehicle.equals("") && !itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemId LIKE '%" + itm + "%' AND itemDiscription LIKE '%" + discription + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && itm.equals("") && discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE itemDiscription LIKE '%" + discription + "%' AND vehicleName LIKE '%" + vehicle + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && !itm.equals("") && discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' AND itemId LIKE '%" + itm + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && !itm.equals("") && !discription.equals("")) {

                    try {
                        stmt = conn.createStatement();
                        String sql = "SELECT * FROM items ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
                        rs = stmt.executeQuery(sql);
                        if (rs.next()) {
                            stmt = conn.createStatement();
                            String sqlSelect = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' AND itemId LIKE '%" + itm + "%' AND itemDiscription LIKE '%" + discription + "%'ORDER BY "
                                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                    + "SUBSTRING(itemId, 1, 2), "
                                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                            ResultSet res = stmt.executeQuery(sqlSelect);
                            tblItem.setModel(ResultSetConverter.resultSetToTableModel(res));
                            configItemsTbl();
                        } else {
                            showItemsRecords(tblItem);
                            configItemsTbl();
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not found any Item ...");
        }
    }//GEN-LAST:event_txtDiscriptionKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //D:\Standalone project Backup\Interface\MLT 8.2.24\src\Reports\tableReport.jrxml
//        try {
//            DefaultTableModel tblModel = (DefaultTableModel)tblItem.getModel();
//        HashMap<String,Object> para=new HashMap<>();
//        para.put("ti", "");
//        JasperPrint jasperPrint = null;
//        JasperCompileManager.compileReportToFile("D:\\Standalone project Backup\\Interface\\MLT 8.2.24\\src\\Reports\\tableReport.jrxml");
//        jasperPrint=JasperFillManager.fillReport("D:\\Standalone project Backup\\Interface\\MLT 8.2.24\\src\\Reports\\tableReport.jasper", para,new JRTableModelDataSource(tblModel));
//        JasperViewer.viewReport(jasperPrint,false);
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        String printSQl = "SELECT * FROM items ORDER BY CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, SUBSTRING(itemId, 1, 2), CAST(SUBSTRING(itemId, 3) AS UNSIGNED);";

        try {
            if (txtPartNo.getText().isEmpty() && txtVehicle.getText().isEmpty() && txtDiscription.getText().isEmpty()) {

                printSQl = "SELECT * FROM items ORDER BY CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, SUBSTRING(itemId, 1, 2), CAST(SUBSTRING(itemId, 3) AS UNSIGNED);";

            } else {

                String No = txtPartNo.getText(); //itm
                String vehicle = txtVehicle.getText();//com
                String discription = txtDiscription.getText();//cat

                String itm = removeLeadingZeros(No);

                if (vehicle.equals("") && itm.equals("") && !discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE itemDiscription LIKE '%" + discription + "%' ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (vehicle.equals("") && !itm.equals("") && discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE itemId LIKE '%" + itm + "%' ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (vehicle.equals("") && !itm.equals("") && !discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE itemId LIKE '%" + itm + "%' AND itemDiscription LIKE '%" + discription + "%'ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && itm.equals("") && discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && itm.equals("") && !discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE itemDiscription LIKE '%" + discription + "%' AND vehicleName LIKE '%" + vehicle + "%'ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && !itm.equals("") && discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' AND itemId LIKE '%" + itm + "%'ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && !itm.equals("") && !discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' AND itemId LIKE '%" + itm + "%' AND itemDiscription LIKE '%" + discription + "%'ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not found any Item ...");
        }

        try {
            //D:\Standalone project Backup\Interface\MLT 8.2.24\src\Reports\report1.jrxml
            //JasperDesign jdesign = JRXmlLoader.load("D:\\Standalone project Backup\\Interface\\MLT 8.2.24\\src\\Reports\\tableReport.jrxml");
            //JasperDesign jdesign = JRXmlLoader.load("src\\Reports\\tableReport.jrxml");
            JasperDesign jdesign = JRXmlLoader.load(PathSet.getTblReportPath());
            String quary = printSQl;

            JRDesignQuery updateJRDesignQuery = new JRDesignQuery();
            updateJRDesignQuery.setText(quary);

            jdesign.setQuery(updateJRDesignQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jReport, null, conn);

            JasperViewer.viewReport(jprint);
        } catch (Exception e) {
            System.out.println(e);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPrint_IN_itemAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrint_IN_itemAddMouseClicked
        String printSQl = "SELECT * FROM items ORDER BY CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, SUBSTRING(itemId, 1, 2), CAST(SUBSTRING(itemId, 3) AS UNSIGNED);";

        try {
            if (txtPartNo.getText().isEmpty() && txtVehicle.getText().isEmpty() && txtDiscription.getText().isEmpty()) {

                printSQl = "SELECT * FROM items ORDER BY CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, SUBSTRING(itemId, 1, 2), CAST(SUBSTRING(itemId, 3) AS UNSIGNED);";

            } else {

                String No = txtPartNo.getText(); //itm
                String vehicle = txtVehicle.getText();//com
                String discription = txtDiscription.getText();//cat

                String itm = removeLeadingZeros(No);

                if (vehicle.equals("") && itm.equals("") && !discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE itemDiscription LIKE '%" + discription + "%' ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (vehicle.equals("") && !itm.equals("") && discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE itemId LIKE '%" + itm + "%' ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (vehicle.equals("") && !itm.equals("") && !discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE itemId LIKE '%" + itm + "%' AND itemDiscription LIKE '%" + discription + "%'ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && itm.equals("") && discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && itm.equals("") && !discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE itemDiscription LIKE '%" + discription + "%' AND vehicleName LIKE '%" + vehicle + "%'ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && !itm.equals("") && discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' AND itemId LIKE '%" + itm + "%'ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                } else if (!vehicle.equals("") && !itm.equals("") && !discription.equals("")) {

                    try {
                        printSQl = "SELECT * FROM items WHERE vehicleName LIKE '%" + vehicle + "%' AND itemId LIKE '%" + itm + "%' AND itemDiscription LIKE '%" + discription + "%'ORDER BY "
                                + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                                + "SUBSTRING(itemId, 1, 2), "
                                + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not found any Item ...");
        }

        try {
            //D:\Standalone project Backup\Interface\MLT 8.2.24\src\Reports\report1.jrxml
            //JasperDesign jdesign = JRXmlLoader.load("D:\\Standalone project Backup\\Interface\\MLT 8.2.24\\src\\Reports\\tableReport.jrxml");
            //JasperDesign jdesign = JRXmlLoader.load("src\\Reports\\tableReport.jrxml");
            JasperDesign jdesign = JRXmlLoader.load(PathSet.getTblReportPath());
            String quary = printSQl;

            JRDesignQuery updateJRDesignQuery = new JRDesignQuery();
            updateJRDesignQuery.setText(quary);

            jdesign.setQuery(updateJRDesignQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jReport, null, conn);

            JasperViewer.viewReport(jprint);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_btnPrint_IN_itemAddMouseClicked

    private void btnSettingsPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingsPnlMouseClicked
        Settings settings = new Settings();
        settings.setVisible(true);
    }//GEN-LAST:event_btnSettingsPnlMouseClicked

    private void comboPartNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPartNoActionPerformed

//       
//        String partNO = comboPartNo.getSelectedItem().toString();
//
//try {
//    String sql = "SELECT * FROM items WHERE itemId = ?";
//    PreparedStatement pstmt = conn.prepareStatement(sql);
//    pstmt.setString(1, partNO);
//    ResultSet rs = pstmt.executeQuery();
//
//    if (rs.next()) {
//        lblVehicle_In_Order.setText(rs.getString("vehicleName"));
//        lblDiscriptionIn_order.setText(rs.getString("itemDiscription"));
//    } else {
//        // Handle case where no matching item is found
//        lblVehicle_In_Order.setText("");
//        lblDiscriptionIn_order.setText("");
//    }
//
//    pstmt.close(); // Close resources
//    rs.close();
//} catch (Exception e) {
//    JOptionPane.showMessageDialog(null, e);
//}
//
//
//
//try {
//    String sql = "SELECT * FROM orders WHERE itemId = ?";
//    PreparedStatement pstmt = conn.prepareStatement(sql);
//    pstmt.setString(1, partNO);
//    ResultSet rs = pstmt.executeQuery();
//
//    if (rs.next()) {
//        txtQty.setText(rs.getString("itemQty"));
//        // Create a table model from the ResultSet
//        DefaultTableModel model = ResultSetConverter.resultSetToTableModel(rs);
//        // Set the model to the table
//        tblOrder.setModel(model);
//    } else {
//        // Handle case where no matching item is found
//        txtQty.setText("");
//        // Set an empty model to the table
//        //tblOrder.setModel(new DefaultTableModel());
//    }
//
//    // Close resources
//    pstmt.close();
//    rs.close();
//} catch (Exception e) {
//    JOptionPane.showMessageDialog(null, e);
//}
//try {
//    
//    String sql = "SELECT * FROM orders WHERE itemId = ? AND itemId LIKE ?";
//    PreparedStatement pstmt = conn.prepareStatement(sql);
//    pstmt.setString(1, partNO); // Setting the value for the 'orders' parameter
//    pstmt.setString(2, "%" + partNO + "%"); // Setting the value for the 'partNo' parameter
//    ResultSet rs = pstmt.executeQuery();
//
//    if (rs.next()) {
//        txtQty.setText(rs.getString("itemQty"));
//        
//        //tblOrder.setModel(ResultSetConverter.resultSetToTableModel(rs));
//        //configOrderTbl();
//    } else {
//        // Handle case where no matching item is found
//        
//        txtQty.setText("");
//    }
//
//    pstmt.close(); // Close resources
//    rs.close();
//} catch (Exception e) {
//    JOptionPane.showMessageDialog(null, e);
//}
//try {
//    //String partNO = comboPartNo.getSelectedItem().toString();
//    String sqlSelect = "SELECT * FROM orders WHERE itemId LIKE '%" + partNO + "%' ORDER BY "
//             + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
//             + "SUBSTRING(itemId, 1, 2), "
//             + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
//    ResultSet res = stmt.executeQuery(sqlSelect);
//    // Remove the following line to avoid closing the ResultSet prematurely
//    // res.close();
//    tblOrder.setModel(ResultSetConverter.resultSetToTableModel(res));
//    configOrderTbl();
//} catch (Exception e) {
//    JOptionPane.showMessageDialog(this, e);
//}
        try {
            String partNO = String.valueOf(comboPartNo.getSelectedItem());

            if (!partNO.isEmpty()) {
                // Fetch item details
                String itemSql = "SELECT * FROM items WHERE itemId = ?";
                try (PreparedStatement itemStmt = conn.prepareStatement(itemSql)) {
                    itemStmt.setString(1, partNO);
                    ResultSet itemRs = itemStmt.executeQuery();
                    if (itemRs.next()) {
                        lblVehicle_In_Order.setText(itemRs.getString("vehicleName"));
                        lblDiscriptionIn_order.setText(itemRs.getString("itemDiscription"));
                    }
                }

                // Fetch order details
//        String orderSql = "SELECT * FROM orders WHERE itemId = ?";
//        try (PreparedStatement orderStmt = conn.prepareStatement(orderSql)) {
//            orderStmt.setString(1, partNO);
//            ResultSet orderRs = orderStmt.executeQuery();
//            if (orderRs.next()) {
//                lblOrderQty.setText(String.valueOf(orderRs.getInt("itemQty")));
//                txtQty.setText(String.valueOf(orderRs.getInt("itemQty")));
//                
//                // Convert ResultSet to TableModel
//                DefaultTableModel model = ResultSetConverter.resultSetToTableModel(orderRs);
//                
//                // Debug output to print the contents of the model
//                System.out.println("TableModel contents:");
//                for (int i = 0; i < model.getRowCount(); i++) {
//                    for (int j = 0; j < model.getColumnCount(); j++) {
//                        System.out.print(model.getValueAt(i, j) + "\t");
//                    }
//                    System.out.println();
//                }
//                
//                // Set the TableModel on the JTable
//                tblOrder.setModel(model);
//            } else {
//                lblOrderQty.setText("00");
//                txtQty.setText("");
//                // Clear table if no orders found
//                tblOrder.setModel(new DefaultTableModel());
//            }
//        }
                String orderSql = "SELECT * FROM orders WHERE itemId = ?";
                try (PreparedStatement orderStmt = conn.prepareStatement(orderSql)) {
                    orderStmt.setString(1, partNO);
                    ResultSet orderRs = orderStmt.executeQuery();
                    if (orderRs.next()) {
                        //lblVehicle_In_Order.setText(itemRs.getString("vehicleName"));
                        //lblDiscriptionIn_order.setText(itemRs.getString("itemDiscription"));
                        txtQty.setText(orderRs.getString("itemQty"));
                        btnAdd_IN_order.setVisible(false);
                        btnUpdate_IN_order.setVisible(true);
                        btnDelete_IN_order.setVisible(true);

                        if (comboPartNo.getSelectedItem() == null) {
                            JOptionPane.showMessageDialog(null, "Please select an item.");
                        } else {
                            // Iterate through table rows to find matching selection
                            for (int i = 0; i < tblOrder.getRowCount(); i++) {
                                String selectedData = (String) tblOrder.getModel().getValueAt(i, 0);
                                if (selectedData != null && selectedData.equals(String.valueOf(comboPartNo.getSelectedItem()))) {
                                    tblOrder.setRowSelectionInterval(i, i);
                                    tblOrder.scrollRectToVisible(tblOrder.getCellRect(i, 0, true));
                                    break;
                                }
                            }
                        }

                    } else {
                        txtQty.setText("");
                        btnAdd_IN_order.setVisible(true);
                        btnUpdate_IN_order.setVisible(false);
                        btnDelete_IN_order.setVisible(false);
                        tblOrder.clearSelection();
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }


    }//GEN-LAST:event_comboPartNoActionPerformed

    private void tblOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMouseClicked
        int selectedRow = tblOrder.getSelectedRow();
        String selectedData = (String) tblOrder.getModel().getValueAt(selectedRow, 0);
        comboPartNo.setSelectedItem(selectedData);
        btnAdd_IN_order.setVisible(false);
        btnUpdate_IN_order.setVisible(true);
        btnDelete_IN_order.setVisible(true);
    }//GEN-LAST:event_tblOrderMouseClicked

    private void btnAdd_IN_orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdd_IN_orderMouseClicked

        String partNo;
        String Qty;
        int QtyIntValue;

        if (comboPartNo.getSelectedItem() == null || txtQty.getText() == null || txtQty.getText().isEmpty()
                || !txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Data Not Valid", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            try {

                partNo = comboPartNo.getSelectedItem().toString();
                Qty = txtQty.getText();
                QtyIntValue = Integer.parseInt(Qty);
                stmt = conn.createStatement();
                String sql2 = "INSERT INTO orders (itemId,itemQty) VALUES ('" + partNo + "', '" + Qty + "');";
                stmt.executeUpdate(sql2);

            } catch (SQLException ex) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
            }

            showOrderRecords(tblOrder);
            configOrderTbl();

            String newADdedData;
            for (int i = 0; i < tblOrder.getRowCount(); i++) {
                newADdedData = (String) tblOrder.getModel().getValueAt(i, 0);
                if (newADdedData.equals(comboPartNo.getSelectedItem().toString())) {
                    tblOrder.setRowSelectionInterval(i, i);
                    tblOrder.scrollRectToVisible(tblOrder.getCellRect(i, 0, true));
                    break;
                }

            }

        }

        comboPartNo.setSelectedItem(null);
        txtQty.setText("");
        lblVehicle_In_Order.setText("");
        lblDiscriptionIn_order.setText("");
    }//GEN-LAST:event_btnAdd_IN_orderMouseClicked

    private void btnUpdate_IN_orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdate_IN_orderMouseClicked

        String partNo;
        String Qty;
        int QtyIntValue;

        if (comboPartNo.getSelectedItem() == null || txtQty.getText() == null || txtQty.getText().isEmpty()
                || !txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Data Not Valid", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            int yesOrNo = JOptionPane.showConfirmDialog(this, "Do You want to Update this Order ?");
            if (yesOrNo == JOptionPane.YES_OPTION) {

                try {
                    partNo = comboPartNo.getSelectedItem().toString();
                    Qty = txtQty.getText();
                    QtyIntValue = Integer.parseInt(Qty);
                    stmt = conn.createStatement();
                    String sql = "UPDATE orders SET itemQty = '" + Qty + "' WHERE itemId = '" + partNo + "';";
                    stmt.executeUpdate(sql);
                    showOrderRecords(tblOrder);
                    configOrderTbl();

                } catch (SQLException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }

                String newADdedData;
                for (int i = 0; i < tblOrder.getRowCount(); i++) {
                    newADdedData = (String) tblOrder.getModel().getValueAt(i, 0);
                    if (newADdedData.equals(comboPartNo.getSelectedItem().toString())) {
                        tblOrder.setRowSelectionInterval(i, i);
                        tblOrder.scrollRectToVisible(tblOrder.getCellRect(i, 0, true));
                        break;
                    }

                }

            } else if (yesOrNo == JOptionPane.NO_OPTION) {

            }

            btnAdd_IN_order.setVisible(true);
            //tblOrder.clearSelection();
            comboPartNo.setSelectedItem(null);
            lblVehicle_In_Order.setText("");
            lblDiscriptionIn_order.setText("");
            txtQty.setText("");
            btnUpdate_IN_itemAdd.setVisible(false);

            //txtPartNo.setEnabled(true);
        }
    }//GEN-LAST:event_btnUpdate_IN_orderMouseClicked

    private void btnDelete_IN_orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelete_IN_orderMouseClicked

        int rowIndex = tblOrder.getSelectedRow();
        int selectedRowCount = tblOrder.getSelectedRowCount();
        if (rowIndex < 0) {

            JOptionPane.showMessageDialog(null, "Select the part you want to delete");
        } else if (selectedRowCount > 1) {
            JOptionPane.showMessageDialog(null, "Multiple selection Not acceptable", "Error", JOptionPane.ERROR_MESSAGE);
            btnAdd_IN_order.setVisible(true);
            tblOrder.clearSelection();
            comboPartNo.setSelectedItem(null);
            lblVehicle_In_Order.setText("");
            lblDiscriptionIn_order.setText("");
            txtQty.setText("");
            btnUpdate_IN_order.setVisible(false);
            btnUpdate_IN_order.setVisible(false);
        } else {

            int yesOrNo = JOptionPane.showConfirmDialog(this, "Do You want to Delete this part ?");
            if (yesOrNo == JOptionPane.YES_OPTION) {
                try {
                    rowIndex = tblOrder.getSelectedRow();
                    int colmnIndex = 0;
                    String selectedPart = (String) tblOrder.getModel().getValueAt(rowIndex, colmnIndex);
                    stmt = conn.createStatement();
                    String sql = "DELETE FROM orders WHERE itemId = '" + selectedPart + "';";

                    stmt.executeUpdate(sql);

                    JOptionPane.showMessageDialog(null, "Deletion Successfull...");
                    btnAdd_IN_order.setVisible(true);
                    tblOrder.clearSelection();
                    comboPartNo.setSelectedItem(null);
                    lblVehicle_In_Order.setText("");
                    lblDiscriptionIn_order.setText("");
                    txtQty.setText("");
                    btnUpdate_IN_order.setVisible(false);
                    btnUpdate_IN_order.setVisible(false);

                    showOrderRecords(tblOrder);
                    configOrderTbl();

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(this, e);
                }
            } else if (yesOrNo == JOptionPane.NO_OPTION) {

                btnAdd_IN_order.setVisible(true);
                tblOrder.clearSelection();
                comboPartNo.setSelectedItem(null);
                lblVehicle_In_Order.setText("");
                lblDiscriptionIn_order.setText("");
                txtQty.setText("");
                btnUpdate_IN_order.setVisible(false);
                btnUpdate_IN_order.setVisible(false);

                showOrderRecords(tblItem);
                configOrderTbl();
            } else {

            }
        }
    }//GEN-LAST:event_btnDelete_IN_orderMouseClicked

    private void btnRefresh_IN_orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefresh_IN_orderMouseClicked

        btnAdd_IN_order.setVisible(true);
        tblOrder.clearSelection();
        comboPartNo.setSelectedItem(null);
        lblVehicle_In_Order.setText("");
        lblDiscriptionIn_order.setText("");
        txtQty.setText("");

        btnUpdate_IN_order.setVisible(false);
        btnDelete_IN_order.setVisible(false);

        showOrderRecords(tblItem);
        configOrderTbl();

    }//GEN-LAST:event_btnRefresh_IN_orderMouseClicked

    private void btnPrint_IN_orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrint_IN_orderMouseClicked
        String printSQl = "SELECT o.*, i.*\n"
                + "FROM orders AS o\n"
                + "INNER JOIN items AS i ON o.itemId = i.itemId\n"
                + "ORDER BY\n"
                + "    CASE WHEN SUBSTRING(o.itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END,\n"
                + "    SUBSTRING(o.itemId, 1, 2),\n"
                + "    CAST(SUBSTRING(o.itemId, 3) AS UNSIGNED);";

        try {
            if (txtPartNo.getText().isEmpty()) {

                printSQl = "SELECT o.*, i.*\n"
                        + "FROM orders AS o\n"
                        + "INNER JOIN items AS i ON o.itemId = i.itemId\n"
                        + "ORDER BY\n"
                        + "    CASE WHEN SUBSTRING(o.itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END,\n"
                        + "    SUBSTRING(o.itemId, 1, 2),\n"
                        + "    CAST(SUBSTRING(o.itemId, 3) AS UNSIGNED);";

            } else {

                String No = txtSerach.getText(); //itm

                String itm = removeLeadingZeros(No);

                if (!No.equals("")) {

                    try {
                        printSQl = "SELECT *\n"
                                + "FROM orders\n"
                                + "RIGHT JOIN items ON orders.itemId = items.itemId\n"
                                + "WHERE orders.itemId LIKE '%\" + No + \"%'\n"
                                + "ORDER BY\n"
                                + "    CASE WHEN SUBSTRING(orders.itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END,\n"
                                + "    SUBSTRING(orders.itemId, 1, 2),\n"
                                + "    CAST(SUBSTRING(orders.itemId, 3) AS UNSIGNED);";

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            //D:\Standalone project Backup\Interface\MLT 8.2.24\src\Reports\report1.jrxml
            //JasperDesign jdesign = JRXmlLoader.load("D:\\Standalone project Backup\\Interface\\MLT 8.2.24\\src\\Reports\\tableReport.jrxml");
            //JasperDesign jdesign = JRXmlLoader.load("src\\Reports\\tableReport.jrxml");
            JasperDesign jdesign = JRXmlLoader.load(PathSet.getOrderListPath());
            String quary = printSQl;

            JRDesignQuery updateJRDesignQuery = new JRDesignQuery();
            updateJRDesignQuery.setText(quary);

            jdesign.setQuery(updateJRDesignQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jReport, null, conn);

            JasperViewer.viewReport(jprint);
        } catch (Exception e) {
            System.out.println(e);
        }


    }//GEN-LAST:event_btnPrint_IN_orderMouseClicked

    private void tblOrderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblOrderFocusLost

    }//GEN-LAST:event_tblOrderFocusLost

    private void txtSerachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSerachKeyReleased

        try {
            String id = txtSerach.getText();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM orders ORDER BY "
                    + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                    + "SUBSTRING(itemId, 1, 2), "
                    + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                stmt = conn.createStatement();
                String sqlSelect = "SELECT * FROM orders WHERE itemId LIKE '%" + id + "%' ORDER BY "
                        + "CASE WHEN SUBSTRING(itemId, 1, 2) REGEXP '^[a-zA-Z]+$' THEN 0 ELSE 1 END, "
                        + "SUBSTRING(itemId, 1, 2), "
                        + "CAST(SUBSTRING(itemId, 3) AS UNSIGNED)";   //'" + userName + "';";
                ResultSet res = stmt.executeQuery(sqlSelect);
                tblOrder.setModel(ResultSetConverter.resultSetToTableModel(res));
                configOrderTbl();
            } else {
                showOrderRecords(tblItem);
                configOrderTbl();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_txtSerachKeyReleased

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAddButtonLBL;
    private javax.swing.JLabel btnAddButtonLBL1;
    private javax.swing.JPanel btnAdd_IN_itemAdd;
    private javax.swing.JPanel btnAdd_IN_order;
    private javax.swing.JLabel btnCloseLbl;
    private javax.swing.JPanel btnClosePnl;
    private javax.swing.JLabel btnComunicationLbl;
    private javax.swing.JPanel btnComunicationPnl;
    private javax.swing.JLabel btnCustomerLbl;
    private javax.swing.JPanel btnCustomerPnl;
    private javax.swing.JPanel btnDBConnectionTestPnl;
    private javax.swing.JLabel btnDeleteButtonLBL;
    private javax.swing.JLabel btnDeleteButtonLBL1;
    private javax.swing.JPanel btnDelete_IN_itemAdd;
    private javax.swing.JPanel btnDelete_IN_order;
    private javax.swing.JLabel btnHomeLbl;
    private javax.swing.JPanel btnHomePnl;
    private javax.swing.JLabel btnInventoryLbl;
    private javax.swing.JPanel btnInventoryPnl;
    private javax.swing.JLabel btnInvoiceLbl;
    private javax.swing.JPanel btnInvoicePnl;
    private javax.swing.JLabel btnMinimizeLbl;
    private javax.swing.JPanel btnMinimizePnl;
    private javax.swing.JLabel btnNotificationLabel;
    private javax.swing.JPanel btnNotificationPnl;
    private javax.swing.JPanel btnPrint_IN_itemAdd;
    private javax.swing.JPanel btnPrint_IN_order;
    private javax.swing.JLabel btnRefreshButtonLBL;
    private javax.swing.JLabel btnRefreshButtonLBL1;
    private javax.swing.JLabel btnRefreshButtonLBL2;
    private javax.swing.JLabel btnRefreshButtonLBL3;
    private javax.swing.JPanel btnRefresh_IN_itemAdd;
    private javax.swing.JPanel btnRefresh_IN_order;
    private javax.swing.JLabel btnReturnLbl;
    private javax.swing.JPanel btnReturnPnl;
    private javax.swing.JLabel btnSettingsLbl;
    private javax.swing.JPanel btnSettingsPnl;
    private javax.swing.JPanel btnSharedFolderPnl;
    private javax.swing.JLabel btnSupplierLbl;
    private javax.swing.JPanel btnSupplierPnl;
    private javax.swing.JLabel btnUpdateButtonLBL;
    private javax.swing.JLabel btnUpdateButtonLBL1;
    private javax.swing.JPanel btnUpdate_IN_itemAdd;
    private javax.swing.JPanel btnUpdate_IN_order;
    private javax.swing.JLabel btnUserLbl;
    private javax.swing.JPanel btnUserPnl;
    private javax.swing.JLabel btnsharedFolderLabel;
    private javax.swing.JComboBox<String> comboPartNo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lblDatabaseConnectionStatusIcon;
    private javax.swing.JLabel lblDatabseConnectionStatus;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDiscriptionIn_order;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblVehicle_In_Order;
    private javax.swing.JPanel pnlBtnContainer;
    private javax.swing.JPanel pnlCustomer;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JPanel pnlInventory;
    private javax.swing.JPanel pnlInvoice;
    private javax.swing.JTabbedPane pnlModuleContainer;
    private javax.swing.JPanel pnlNavigation;
    private javax.swing.JPanel pnlReturn;
    private javax.swing.JPanel pnlSupplier;
    private javax.swing.JPanel pnlUser;
    private javax.swing.JTable tblItem;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextField txtDiscription;
    private javax.swing.JTextField txtPartNo;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtSerach;
    private javax.swing.JTextField txtVehicle;
    // End of variables declaration//GEN-END:variables

}
