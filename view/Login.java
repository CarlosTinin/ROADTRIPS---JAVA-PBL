package view;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.accessibility.*;

public class Login extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Login() {
		this.initComponents();
		this.setLocationRelativeTo(null);
	}
	
	private void initComponents() {
		JPanel main_content = new JPanel(new BorderLayout());
		main_content.setBackground(new java.awt.Color(0, 0, 0));
		main_content.setSize(10, 10);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 153));
        setBounds(new java.awt.Rectangle(0, 200, 500, 500));
        
        main_content.add(new JLabel("Hello World"), BorderLayout.CENTER);
        
        this.addImpl(main_content, accessibleContext, NORMAL);
	}
	
	public static void main(String args[]) {
		
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
	}

}
