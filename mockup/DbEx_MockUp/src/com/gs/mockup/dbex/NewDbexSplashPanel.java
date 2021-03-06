/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gs.mockup.dbex;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author 50120C1509
 */
public class NewDbexSplashPanel extends javax.swing.JPanel {

    private Image backgroundImage;
    
    /**
     * Creates new form NewDbexSplashPanel
     */
    public NewDbexSplashPanel() {
        ImageIcon imageIcon = new ImageIcon(
                getClass().getResource("/images/dbex-splash-background.png")
                );
        backgroundImage = imageIcon.getImage();
        Dimension size = new Dimension(backgroundImage.getWidth(null), 
                backgroundImage.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        initComponents();
    }

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        versionLabel = new javax.swing.JLabel();
        loadStatusLabel = new javax.swing.JLabel();
        copyRightLabel = new javax.swing.JLabel();
        splashProgressBar = new javax.swing.JProgressBar();
        backgroundLabel = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        versionLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        versionLabel.setForeground(new java.awt.Color(204, 255, 204));
        versionLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        versionLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/helium.png"))); // NOI18N
        add(versionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 90, 30));

        loadStatusLabel.setText("jLabel3");
        add(loadStatusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 510, -1));

        copyRightLabel.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        copyRightLabel.setForeground(new java.awt.Color(255, 255, 255));
        copyRightLabel.setText("<html>\n<body>\n<b>D</b>atabase <b>E</b>xplorer (DbEx) is a free tool for multiple database management. Created and managed by Sabuj Das.<br/>\nPlease visit <a href=\"http://code.google.com/p/database-explorer\">DbEx</a> for more information.\n</body>\n</html>");
        add(copyRightLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 266, 510, 30));

        splashProgressBar.setIndeterminate(true);
        add(splashProgressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 249, 510, 10));

        backgroundLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dbex-splash-background.png"))); // NOI18N
        add(backgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 300));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLabel;
    private javax.swing.JLabel copyRightLabel;
    private javax.swing.JLabel loadStatusLabel;
    private javax.swing.JProgressBar splashProgressBar;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables
}
