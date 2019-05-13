package de.Enigma.UI;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {

	private final JFrame frmEnigmaGUI = new JFrame();
	private final JTextField txtFdText = new JTextField();
	private final JTextField txtFdKey = new JTextField();
	private final JTextArea txtDevelopedBy = new JTextArea();
	private final JSeparator separator = new JSeparator();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JButton btnCancel = new JButton("Cancer");
	private final JButton btnStart = new JButton("O K");
	private final JButton btnChooseFile = new JButton("Datei");
	private final JRadioButton rdbtnDecrypt = new JRadioButton("Entschl\u00FCsseln");
	private final JRadioButton rdbtnEncrypt = new JRadioButton("Verschl\u00FCsseln");
	private final JProgressBar progressBar = new JProgressBar();
	private final JLabel background = new JLabel("");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.show();
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
	
	public void show() {
		frmEnigmaGUI.setVisible(true);
	}
	public void hide() {
		frmEnigmaGUI.setVisible(false);
	}

	private void initEdits() {
		//Text Edit
		txtFdText.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		txtFdText.setBounds(10, 11, 534, 20);
		txtFdText.setColumns(10);
		
		addComponent(txtFdText);
		//Key Edit
		txtFdKey.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		txtFdKey.setBounds(10, 42, 534, 20);
		txtFdKey.setColumns(10);
		
		addComponent(txtFdKey);
	}
	private void initBackGround() {
		//"The Line"
		separator.setBounds(10, 73, 633, 2);
		
		addComponent(separator);
		//BackGround image
		background.setIcon(new ImageIcon(GUI.class.getResource("/res/bg.jpg")));
		background.setBounds(0, 0, 661, 355);
		
		addComponent(background);
	}
	private void initButtons() {
		//File chooser button
		btnChooseFile.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		btnChooseFile.setBounds(554, 10, 89, 23);
		
		addComponent(btnChooseFile);
		//Start En/Decryption button
		btnStart.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		btnStart.setBounds(455, 311, 89, 23);
		
		addComponent(btnStart);
		//Cancel Button
		btnCancel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		btnCancel.setBounds(554, 311, 89, 23);
		
		addComponent(btnCancel);
	}
	private void initRadioButtons() {
		//Decrypt RdBtn
		rdbtnDecrypt.setForeground(SystemColor.textText);
		rdbtnDecrypt.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		rdbtnDecrypt.setBounds(10, 149, 109, 23);
		rdbtnDecrypt.setContentAreaFilled(false);
		
		buttonGroup.add(rdbtnDecrypt);
		
		addComponent(rdbtnDecrypt);
		//Encrypt RdBtn
		rdbtnEncrypt.setForeground(SystemColor.textText);
		rdbtnEncrypt.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		rdbtnEncrypt.setContentAreaFilled(false);
		rdbtnEncrypt.setSelected(true);
		rdbtnEncrypt.setBounds(10, 123, 109, 23);
		
		buttonGroup.add(rdbtnEncrypt);
		
		addComponent(rdbtnEncrypt);
	}
	private void initTexts() {
		txtDevelopedBy.setOpaque(false);
		txtDevelopedBy.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		txtDevelopedBy.setEditable(false);
		txtDevelopedBy.setForeground(SystemColor.text);
		txtDevelopedBy.setBackground(SystemColor.menu);
		txtDevelopedBy.setText("Developed by: \r\nSovietware Corp.\r\n\r\nLisa Binkert\r\nNikolai Klatt\r\nOliver Seiler");
		txtDevelopedBy.setBounds(10, 244, 240, 100);
		txtDevelopedBy.setColumns(10);
		
		addComponent(txtDevelopedBy);
	}
	private void initFrame() {
		//Frame
		frmEnigmaGUI.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		frmEnigmaGUI.setTitle("Enigma v2");
		frmEnigmaGUI.setResizable(false);
		frmEnigmaGUI.setBounds(100, 100, 667, 384);
		frmEnigmaGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEnigmaGUI.getContentPane().setLayout(null);
		//Loading bar
		progressBar.setBounds(10, 102, 633, 14);
		
		addComponent(progressBar);
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	//DONE: Mach des gscheit...
	private void initialize() {
		initFrame();
		initEdits();
		initButtons();
		initRadioButtons();
		initTexts();
		initBackGround();
	}
	
	private void addComponent(Component component) {
		frmEnigmaGUI.getContentPane().add(component);
	}
}
