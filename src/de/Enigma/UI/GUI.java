package de.Enigma.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.Enigma.Core.Main;

/**
 * @brief GUI Klasse für die grafische Darstellung des Programmes
 * @details Wird durch die Main Klasse gestartet und bildet die Schnittstelle zum Benutzer, dieser interagiert mit ihr
 *          und kann somit die Kernfunktionen triggern.
 *          Die GUI ist nicht dynamisch und hat nicht den Anspruch eine gute und ansehnliche GUI zu sein, sie dient nur
 *          dem Zweck der Kommunikation mit dem Nutzer.
 *
 *          Die GUI enthält Komponenten die den Nutzer teilweise führen und teilweise auch bestimmte Dinge ausprobieren
 *          lassen, der Nutzer ist aber dennoch daran gebunden, dass die GUI ihn einschränkt.
 */
public class GUI {

    private final String FONT = "Segoe UI Semibold";
    private final int TEXT_SIZE = 21;
    private final int COMPONENT_DISTANCE = 20;
    private final int COMPONENT_HEIGHT = 40;
    private final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 12;
    private final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / 12;
    private final int WINDOW_WIDTH = SCREEN_WIDTH * 8;
    private final int WINDOW_HEIGHT = SCREEN_HEIGHT * 8;

    private final JFrame FRM_ENIGMA_GUI = new JFrame();
    private final HintTextField TXT_FD_TEXT = new HintTextField("Zu ver- / entschlüsselnden Text eingeben");
    private final HintTextField TXT_FD_KEY = new HintTextField("U-AAA-XXX-X1;Y1-X2;Y2-...-X10;Y10");
    private final JLabel LBL_TEXT = new JLabel("Text:");
    private final JLabel LBL_KEY = new JLabel("Schlüssel:");
    private final JTextArea TXT_DEVELOPED_BY = new JTextArea();
    private final JSeparator SEPARATOR = new JSeparator();
    private final ButtonGroup BUTTON_GROUP = new ButtonGroup();
    private final JButton BTN_CANCEL = new JButton("Cancel");
    private final JButton BTN_START = new JButton("O K");
    private final JButton BTN_CHOOSE_FILE = new JButton("Datei");
    private final JRadioButton RDBTN_DECRYPT = new JRadioButton("Entschlüsseln");
    private final JRadioButton RDBTN_ENCRYPT = new JRadioButton("Verschlüsseln");
    private final JProgressBar PROGRESSBAR = new JProgressBar();
    private final JLabel BACKGROUND = new JLabel("");

    private Main main;
    private JFileChooser FILE_CHOOSER;


    public GUI(Main m) {
        main = m;
        initialize();
    }

    //Zeigt das Fenster
    public void show() {
        FRM_ENIGMA_GUI.setVisible(true);
    }

    //Verbirgt das Fenster
    public void hide() {
        FRM_ENIGMA_GUI.setVisible(false);
    }

    //Initiiert Labels
    private void initLabels() {
        LBL_TEXT.setFont(new Font(FONT, Font.PLAIN, TEXT_SIZE));
        LBL_TEXT.setForeground(Color.BLACK);
        LBL_TEXT.setBounds(COMPONENT_DISTANCE, COMPONENT_DISTANCE, 100, COMPONENT_HEIGHT);

        addComponent(LBL_TEXT);

        LBL_KEY.setFont(new Font(FONT, Font.PLAIN, TEXT_SIZE));
        LBL_KEY.setForeground(Color.BLACK);
        LBL_KEY.setBounds(COMPONENT_DISTANCE, 80, 100, COMPONENT_HEIGHT);

        addComponent(LBL_KEY);
    }

    //Initiiert alle TextFields / Die Eingabefelder
    private void initEdits() {
        //Text Edit
        TXT_FD_TEXT.setFont(new Font(FONT, Font.PLAIN, TEXT_SIZE));
        TXT_FD_TEXT.setBounds(COMPONENT_DISTANCE * 6 + 10, COMPONENT_DISTANCE, WINDOW_WIDTH - 460, COMPONENT_HEIGHT);
        TXT_FD_TEXT.setColumns(10);
        TXT_FD_TEXT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                BTN_START.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (TXT_FD_TEXT.getText().equals("")) BTN_START.setEnabled(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        addComponent(TXT_FD_TEXT);
        //Key Edit
        TXT_FD_KEY.setFont(new Font(FONT, Font.PLAIN, TEXT_SIZE));
        TXT_FD_KEY.setBounds(COMPONENT_DISTANCE * 6 + 10, 80, WINDOW_WIDTH - 460, COMPONENT_HEIGHT);
        TXT_FD_KEY.setColumns(10);

        addComponent(TXT_FD_KEY);
    }

    //Stellt das Hintergrundbild ein
    private void initBackGround() {
        //"The Line"
        SEPARATOR.setBounds(COMPONENT_DISTANCE, 240, WINDOW_WIDTH - 50, 3);

        addComponent(SEPARATOR);
        //BackGround image
        BACKGROUND.setIcon(new ImageIcon(new ImageIcon(GUI.class.getResource("/res/bg.jpg")).getImage().getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT, Image.SCALE_SMOOTH)));
        BACKGROUND.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        addComponent(BACKGROUND);
    }

    private void initButtons() {
        //File chooser button
        BTN_CHOOSE_FILE.setFont(new Font(FONT, Font.PLAIN, TEXT_SIZE));
        BTN_CHOOSE_FILE.setBounds(WINDOW_WIDTH - 300, COMPONENT_DISTANCE, 270, COMPONENT_HEIGHT);
        BTN_CHOOSE_FILE.addActionListener(e -> btnChooseFileClicked());

        addComponent(BTN_CHOOSE_FILE);
        //Start En/Decryption button
        BTN_START.setFont(new Font(FONT, Font.PLAIN, TEXT_SIZE));
        BTN_START.setEnabled(false);
        BTN_START.grabFocus();
        BTN_START.setBounds(WINDOW_WIDTH - 300, WINDOW_HEIGHT - 110, 270, COMPONENT_HEIGHT);
        BTN_START.addActionListener(e -> btnOkClicked());

        addComponent(BTN_START);
        //Cancel Button
        BTN_CANCEL.setFont(new Font(FONT, Font.PLAIN, TEXT_SIZE));
        BTN_CANCEL.setBounds(WINDOW_WIDTH - 620, WINDOW_HEIGHT - 110, 270, COMPONENT_HEIGHT);
        BTN_CANCEL.addActionListener(e -> btnCancelClicked());

        addComponent(BTN_CANCEL);
    }

    //Stellt alle RadioButtons ein
    private void initRadioButtons() {
        //Encrypt RdBtn
        RDBTN_ENCRYPT.setForeground(SystemColor.textText);
        RDBTN_ENCRYPT.setFont(new Font(FONT, Font.PLAIN, TEXT_SIZE));
        RDBTN_ENCRYPT.setContentAreaFilled(false);
        RDBTN_ENCRYPT.setSelected(true);
        RDBTN_ENCRYPT.setBounds(COMPONENT_DISTANCE, 140, WINDOW_WIDTH / 2, COMPONENT_HEIGHT);

        BUTTON_GROUP.add(RDBTN_ENCRYPT);

        addComponent(RDBTN_ENCRYPT);

        //Decrypt RdBtn
        RDBTN_DECRYPT.setForeground(SystemColor.textText);
        RDBTN_DECRYPT.setFont(new Font(FONT, Font.PLAIN, TEXT_SIZE));
        RDBTN_DECRYPT.setBounds(COMPONENT_DISTANCE, 180, WINDOW_WIDTH / 2, COMPONENT_HEIGHT);
        RDBTN_DECRYPT.setContentAreaFilled(false);

        BUTTON_GROUP.add(RDBTN_DECRYPT);

        addComponent(RDBTN_DECRYPT);
    }

    //Erzeugt alle "Labels", bzw. Beschreibungen
    private void initTexts() {
        TXT_DEVELOPED_BY.setOpaque(false);
        TXT_DEVELOPED_BY.setFont(new Font(FONT, Font.BOLD, TEXT_SIZE));
        TXT_DEVELOPED_BY.setEditable(false);
        TXT_DEVELOPED_BY.setHighlighter(null);
        TXT_DEVELOPED_BY.setForeground(Color.WHITE);
        TXT_DEVELOPED_BY.setBackground(SystemColor.menu);
        TXT_DEVELOPED_BY.setText("Developed by: \r\nSovietware Corp.\r\n\r\nLisa Binkert\r\nNikolai Klatt\r\nOliver Seiler");
        TXT_DEVELOPED_BY.setBounds(COMPONENT_DISTANCE, WINDOW_HEIGHT - 235, 240, 300);
        TXT_DEVELOPED_BY.setColumns(10);

        addComponent(TXT_DEVELOPED_BY);
    }

    //Stellt das Fenster ein
    private void initFrame() {
        //Frame
        FRM_ENIGMA_GUI.setFont(new Font(FONT, Font.PLAIN, TEXT_SIZE));
        String TITLE = "Enigma V2";
        FRM_ENIGMA_GUI.setTitle(TITLE);
        FRM_ENIGMA_GUI.setResizable(false);
        FRM_ENIGMA_GUI.setBounds(SCREEN_WIDTH * 2, SCREEN_HEIGHT * 2, WINDOW_WIDTH, WINDOW_HEIGHT);
        FRM_ENIGMA_GUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FRM_ENIGMA_GUI.getContentPane().setLayout(null);

        PROGRESSBAR.setBounds(COMPONENT_DISTANCE, 280, WINDOW_WIDTH - 50, COMPONENT_HEIGHT - 10);

        addComponent(PROGRESSBAR);
    }

    private void initFileChooser() {
        FILE_CHOOSER = new JFileChooser(main.getFileHandler().getPATH());
        FILE_CHOOSER.setFileFilter(new FileNameExtensionFilter("EnigmaFiles (.enigma)", "enigma"));
    }


    /**
     * Initialize the contents of the frame.
     */
    //Baut die GUI zusammen
    private void initialize() {
        initFrame();
        initLabels();
        initEdits();
        initButtons();
        initRadioButtons();
        initTexts();
        initBackGround();
        initFileChooser();
    }

    private void addComponent(Component component) {
        FRM_ENIGMA_GUI.getContentPane().add(component);
    }

    //Wird ausgeführt, wenn der Cancel Button gedrückt wird
    private void btnCancelClicked() {
        System.out.println("BTN_CANCEL Clicked");
        BTN_CANCEL.setText("Cancel");
        setGUIElementsEnabled(true);
        TXT_FD_TEXT.setHint();
        TXT_FD_KEY.setHint();
        RDBTN_ENCRYPT.setSelected(true);
        RDBTN_DECRYPT.setSelected(false);
        BTN_START.setEnabled(false);
        PROGRESSBAR.setIndeterminate(false);
        PROGRESSBAR.setValue(0);
        PROGRESSBAR.setString("");
    }

    //Wird ausgeführt, wenn der OK Button gedrückt wird
    private void btnOkClicked() {
        setGUIElementsEnabled(false);
        PROGRESSBAR.setIndeterminate(true);
        PROGRESSBAR.setStringPainted(true);


        if (RDBTN_ENCRYPT.isSelected()) PROGRESSBAR.setString("Verschlüsselung läuft...");
        else PROGRESSBAR.setString("Entschlüsselung läuft...");

        main.btnOkClicked(TXT_FD_TEXT.getText(), TXT_FD_KEY.getText(), RDBTN_ENCRYPT.isSelected());
    }

    //Wird ausgeführt, wenn der "Datei" Button gedrückt wird
    private void btnChooseFileClicked() {
        System.out.println("File chooser button clicked");
        FILE_CHOOSER.showOpenDialog(FRM_ENIGMA_GUI);
        //prüft, ob eine Datei ausgewählt wurde
        //falls nicht wird sie auch nicht behandelt
        if (FILE_CHOOSER.getSelectedFile() != null) {
            File enigmaFile = FILE_CHOOSER.getSelectedFile();
            System.out.println(enigmaFile.getName());
            //TODO
            //handle chosen file
            //setText: TEXT in TXT_FD_TEXT , KEY in TXT_FD_KEY
        }
    }

    public void onFinished() {
        if (RDBTN_ENCRYPT.isSelected()) PROGRESSBAR.setString("Verschlüsselung abgeschlossen");
        else PROGRESSBAR.setString("Entschlüsselung abgeschlossen");
        PROGRESSBAR.setIndeterminate(false);
            PROGRESSBAR.setValue(100);
            BTN_CANCEL.setText("Again");
    }

    private void setGUIElementsEnabled(boolean enabled) {
        TXT_FD_TEXT.setEnabled(enabled);
        TXT_FD_KEY.setEnabled(enabled);
        BTN_START.setEnabled(enabled);
        BTN_CHOOSE_FILE.setEnabled(enabled);
        RDBTN_ENCRYPT.setEnabled(enabled);
        RDBTN_DECRYPT.setEnabled(enabled);
    }
}
