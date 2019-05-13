package de.Enigma.UI;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;

public class GUI {

	private JFrame frmEnigmaV;
	private JTextField txtFdText;
	private JTextField txtFdKey;
	private ButtonGroup buttonGroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmEnigmaV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		buttonGroup = new ButtonGroup();
		frmEnigmaV = new JFrame();
		frmEnigmaV.setTitle("Enigma v2");
		frmEnigmaV.setResizable(false);
		frmEnigmaV.setBounds(100, 100, 667, 384);
		frmEnigmaV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEnigmaV.getContentPane().setLayout(null);
		
		txtFdText = new JTextField();
		txtFdText.setBounds(10, 11, 534, 20);
		frmEnigmaV.getContentPane().add(txtFdText);
		txtFdText.setColumns(10);
		
		JButton btnChooseFile = new JButton("Datei");
		btnChooseFile.setBounds(554, 10, 89, 23);
		frmEnigmaV.getContentPane().add(btnChooseFile);
		
		txtFdKey = new JTextField();
		txtFdKey.setBounds(10, 42, 534, 20);
		frmEnigmaV.getContentPane().add(txtFdKey);
		txtFdKey.setColumns(10);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 102, 633, 14);
		frmEnigmaV.getContentPane().add(progressBar);
		
		JRadioButton rdbtnDecrypt = new JRadioButton("Entschl\u00FCsseln");
		rdbtnDecrypt.setForeground(SystemColor.textText);
		rdbtnDecrypt.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnDecrypt.setBounds(10, 149, 109, 23);
		rdbtnDecrypt.setContentAreaFilled(false);
		buttonGroup.add(rdbtnDecrypt);
		frmEnigmaV.getContentPane().add(rdbtnDecrypt);
		
		JRadioButton rdbtnEncrypt = new JRadioButton("Verschl\u00FCsseln");
		rdbtnEncrypt.setForeground(SystemColor.textText);
		rdbtnEncrypt.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnEncrypt.setContentAreaFilled(false);
		rdbtnEncrypt.setSelected(true);
		rdbtnEncrypt.setBounds(10, 123, 109, 23);
		buttonGroup.add(rdbtnEncrypt);
		frmEnigmaV.getContentPane().add(rdbtnEncrypt);
		
		JButton btnStart = new JButton("O K");
		btnStart.setBounds(455, 311, 89, 23);
		frmEnigmaV.getContentPane().add(btnStart);
		
		JButton btnCancel = new JButton("Cancer");
		btnCancel.setBounds(554, 311, 89, 23);
		frmEnigmaV.getContentPane().add(btnCancel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 73, 633, 2);
		frmEnigmaV.getContentPane().add(separator);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(GUI.class.getResource("/res/bg.jpg")));
		background.setBounds(0, 0, 661, 355);
		frmEnigmaV.getContentPane().add(background);
	}
}
