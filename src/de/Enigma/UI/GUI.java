package de.Enigma.UI;

import de.Enigma.Core.Log;
import de.Enigma.Core.Main;
import de.Enigma.Util.FileHandler;
import de.Enigma.Util.Util;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Nikolai Klatt, Oliver Seiler
 * @brief GUI Klasse für die grafische Darstellung des Programmes
 * @details Wird durch die Main Klasse gestartet und bildet die Schnittstelle zum Benutzer, dieser interagiert mit ihr
 * und kann somit die Kernfunktionen triggern.
 * Die GUI ist nicht dynamisch und hat auch nicht den Anspruch eine gute und ansehnliche GUI zu sein, sie dient nur
 * dem Zweck der Kommunikation mit dem Nutzer.
 * <p>
 * Die GUI enthält Komponenten die den Nutzer teilweise führen und teilweise auch bestimmte Dinge ausprobieren
 * lassen, der Nutzer ist aber dennoch daran gebunden, dass die GUI ihn einschränkt.
 */
public class GUI {

    //wichtige Konstanten
    private final int COMPONENT_DISTANCE = 20;
    private final int COMPONENT_HEIGHT = 40;
    private final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 12;
    private final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / 12;
    private final int WINDOW_WIDTH = SCREEN_WIDTH * 8;
    private final int WINDOW_HEIGHT = SCREEN_HEIGHT * 8;
    private final Font FONT = new Font("Segoe UI Semibold", Font.PLAIN, 21);

    //GUI elemente
    private final JFrame FRM_ENIGMA_GUI = new JFrame("Enigma V2");

    private final Image BACKGROUND_IMG = new ImageIcon(GUI.class.getResource("/res/bg_enigma.png")).getImage().getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT, Image.SCALE_SMOOTH);
    private final ImageIcon FRAME_ICON = new ImageIcon(GUI.class.getResource("/res/icon.png"));
    
    private final HintTextField TF_TEXT = new HintTextField("TEXT", "Zu ver- / entschlüsselnden Text eingeben", FONT);
    private final HintTextField TF_KEY = new HintTextField("KEY", "U-AAA-XXX-X1;Y1-X2;Y2-X3;Y3-X4;Y4-X5;Y5-X6;Y6-X7;Y7-X8;Y8-X9;Y9-X10;Y10", FONT);

    private final JLabel LBL_TEXT = new JLabel("Text:");
    private final JLabel LBL_KEY = new JLabel("Schlüssel:");
    private final JTextArea TXT_DEVELOPED_BY = new JTextArea();

    private final JSeparator SEPARATOR = new JSeparator();

    private final JButton BTN_CANCEL = new JButton("Cancel");
    private final JButton BTN_START = new JButton("O K");
    private final JButton BTN_CHOOSE_FILE = new JButton("Datei einlesen");
    private final JButton BTN_CREATE_KEY = new JButton("Schlüssel erzeugen");

    private final JCheckBox CHBX_REAL_ENIGMA = new JCheckBox("Simuliere echte Enigma");
    private final JCheckBox CHBX_TOGGLE_LOGGING = new JCheckBox("Logging deaktivieren");

    private final ButtonGroup BUTTON_GROUP = new ButtonGroup();
    private final JRadioButton RDBTN_DECRYPT = new JRadioButton("Entschlüsseln");
    private final JRadioButton RDBTN_ENCRYPT = new JRadioButton("Verschlüsseln");

    private final JProgressBar PROGRESSBAR = new JProgressBar();
    private final JLabel BACKGROUND = new JLabel("");

    private JFileChooser FILE_CHOOSER;

    //Assoziation mit der Main
    private Main main;


    /**
     * @param m - Assoziation mit der Main, dadurch kann die GUI Methoden der Main aufrufen
     * @brief Konstruktor der GUI
     * @details Der Konstruktor der GUI initialisiert alle Elemente der GUI
     * @see de.Enigma.Core.Main
     */
    public GUI(Main m) {
        main = m;
        initialize();
        FRM_ENIGMA_GUI.setVisible(true);
        //Fokus auf den Button, damit der Hint in TextField angezeigt wird
        BTN_CREATE_KEY.requestFocus();

        Log.getLogger().i(getClass().getName() + ".initialize", "GUI initialisiert");
    }

    /**
     * @brief Methode zum Initialisieren aller Elemente der GUI
     * @details Diese Methode ruft andere Methoden auf, in denen die Elemente der GUI initialisiert werden.
     * Dadurch wird die GUI "zusammengebaut"
     */
    private void initialize() {
        initFrame();
        initLabels();
        initTextfields();
        initButtons();
        initCheckBoxes();
        initRadioButtons();
        initProgressBar();
        initTextArea();
        initBackGround();
        initFileChooser();
    }

    /**
     * @brief Methode, die das JFrame baut
     * @details Das Fenster soll sich dynamisch an die Auflösung anpassen: Es wird im Bildschirm zentriert.\n
     * Komponente:\n
     * - FRM_ENIGMA_GUI:  JFrame für die GUI Elemente\n
     */
    private void initFrame() {
        FRM_ENIGMA_GUI.setFont(FONT);
        FRM_ENIGMA_GUI.setResizable(false);
        FRM_ENIGMA_GUI.setBounds(SCREEN_WIDTH * 2, SCREEN_HEIGHT * 2, WINDOW_WIDTH, WINDOW_HEIGHT);
        FRM_ENIGMA_GUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Log.getLogger().i(getClass().getName() + ".initFrame", "Frame initialisiert");
    }

    /**
     * @brief Methode, die JLabels baut
     * @details Die JLabels stehen vor den TextFields und dienen zur Orientierung des Nutzers.
     * Sie sollen zeigen, was man in die TextFields schreiben soll.\n
     * Komponenten:\n
     * - LBL_TEXT:  Label für das TextField TF_TEXT\n
     * - LBL_KEY:   Label für das TextField TF_KEY\n
     */
    private void initLabels() {
        LBL_TEXT.setFont(FONT);
        LBL_TEXT.setForeground(Color.WHITE);
        LBL_TEXT.setBounds(COMPONENT_DISTANCE, COMPONENT_DISTANCE, 100, COMPONENT_HEIGHT);

        addComponent(LBL_TEXT);

        LBL_KEY.setFont(FONT);
        LBL_KEY.setForeground(Color.WHITE);
        LBL_KEY.setBounds(COMPONENT_DISTANCE, 80, 100, COMPONENT_HEIGHT);

        addComponent(LBL_KEY);

        Log.getLogger().i(getClass().getName() + ".initLabels", "Labels initialisiert");
    }

    /**
     * @brief Methode, die die Textfields baut
     * @details Die Textfields sind in diesem Falle von JTextField abgeleitete HintTextFields, um das Arbeiten mit den Hints und dem CharacterFilter zu vereinfachen.\n
     * Komponenten:\n
     * - TF_TEXT:  TextField für den Text\n
     * - TF_KEY:   TextField für den Schlüssel\n
     * @see de.Enigma.UI.HintTextField
     */
    private void initTextfields() {
        //Der DocumentListener hört darauf, ob ein Zeichen in das 'Text' und 'Schlüssel' Textfield eingegeben wurde, das soll sicherstellen,
        //dass keine Ver- / Entschlüsselung ohne Text oder Schlüssel angefangen werden kann.

        TF_TEXT.setBounds(COMPONENT_DISTANCE * 6 + 10, COMPONENT_DISTANCE, WINDOW_WIDTH - 460, COMPONENT_HEIGHT);
        TF_TEXT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!TF_KEY.isShowingHint())
                    BTN_START.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (TF_TEXT.getText().equals("") || TF_KEY.getText().equals("")) BTN_START.setEnabled(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        addComponent(TF_TEXT);

        TF_KEY.setBounds(COMPONENT_DISTANCE * 6 + 10, 80, WINDOW_WIDTH - 460, COMPONENT_HEIGHT);
        //Zeigt einen Tooltip, damit man sieht, was  die einzelnen Elemente des Schlüssels bedeuten
        TF_KEY.setToolTipText("<html>U-AAA-XXX-X1;Y1-X2;Y2-X3;Y3-X4;Y4-X5;Y5-X6;Y6-X7;Y7-X8;Y8-X9;Y9-X10;Y10<br>" +
                "U:     Umkehrwalze - Erlaubter Input: A,B,C<br>" +
                "AAA:   Walzenordnung - Erlaubter Input: 1,2,3,4,5<br>" +
                "XXX:   Walzenstellung: - Erlaubter Input: A-Z<br>" +
                "X1;Y1: Steckbrett X1 wird zu Y1 - Erlaubter Input: A-Z</html>");
        TF_KEY.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!TF_TEXT.isShowingHint() && TF_KEY.getText().length() >= 9)
                    BTN_START.setEnabled(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (TF_KEY.getText().equals("") || TF_TEXT.getText().equals("")) BTN_START.setEnabled(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        addComponent(TF_KEY);

        Log.getLogger().i(getClass().getName() + ".initTextfields", "TextFields initialisiert");
    }

    /**
     * @brief Methode, die die Buttons baut
     * @details Die Buttons dienen der Kommunikation des Nutzers mit der Anwendung.\n
     * Komponenten:\n
     * - BTN_CHOOSE_FILE:  Button, um eine Datei auszuwählen\n
     * - BTN_CREATE_KEY:   Button, der einen Schlüssel erzeugt\n
     * - BTN_START:        Button, um den Vorgang zu starten\n
     * - BTN_CANCEL:       Button zum Abbrechen des Vorgangs\n
     */
    private void initButtons() {
        //File chooser button
        BTN_CHOOSE_FILE.setFont(FONT);
        BTN_CHOOSE_FILE.setBounds(WINDOW_WIDTH - 300, COMPONENT_DISTANCE, 270, COMPONENT_HEIGHT);
        BTN_CHOOSE_FILE.addActionListener(e -> btnChooseFileClicked());

        addComponent(BTN_CHOOSE_FILE);

        //CreateKey button
        BTN_CREATE_KEY.setFont(FONT);
        BTN_CREATE_KEY.setBounds(WINDOW_WIDTH - 300, 80, 270, COMPONENT_HEIGHT);
        BTN_CREATE_KEY.addActionListener(e -> btnCreateKeyClicked());

        addComponent(BTN_CREATE_KEY);

        //Start En/Decryption button
        //standardmäßig auf disabled, damit man die Ver- / Entschlüsselung nicht sofort starten kann
        BTN_START.setFont(FONT);
        BTN_START.setEnabled(false);
        BTN_START.grabFocus();
        BTN_START.setBounds(WINDOW_WIDTH - 300, WINDOW_HEIGHT - 110, 270, COMPONENT_HEIGHT);
        BTN_START.addActionListener(e -> btnOkClicked());

        addComponent(BTN_START);

        //Cancel Button
        BTN_CANCEL.setFont(FONT);
        BTN_CANCEL.setBounds(WINDOW_WIDTH - 620, WINDOW_HEIGHT - 110, 270, COMPONENT_HEIGHT);
        BTN_CANCEL.addActionListener(e -> btnCancelClicked());

        addComponent(BTN_CANCEL);

        Log.getLogger().i(getClass().getName() + ".initButtons", "Buttons initialisiert");
    }

    /**
     * @brief Methode, die die Checkbox baut
     * @details Die CheckBox dient dazu, die Textverarbeitung wie bei der echten Enigma ausführen zu lassen.\n
     * Komponenten:\n
     * - CHBX_REAL_ENIGMA:  CheckBox\n
     */
    private void initCheckBoxes() {
        CHBX_REAL_ENIGMA.setBounds(WINDOW_WIDTH - 300, 140, 270, COMPONENT_HEIGHT);
        CHBX_REAL_ENIGMA.setContentAreaFilled(false);
        CHBX_REAL_ENIGMA.setFont(FONT);
        CHBX_REAL_ENIGMA.setForeground(Color.WHITE);

        addComponent(CHBX_REAL_ENIGMA);

        CHBX_TOGGLE_LOGGING.setBounds(WINDOW_WIDTH - 300, 180, 270, COMPONENT_HEIGHT);
        CHBX_TOGGLE_LOGGING.setContentAreaFilled(false);
        CHBX_TOGGLE_LOGGING.setFont(FONT);
        CHBX_TOGGLE_LOGGING.setForeground(Color.WHITE);

        addComponent(CHBX_TOGGLE_LOGGING);
    }

    /**
     * @brief Methode, die die RadioButtons baut
     * @details Die RadioButtons dienen der Einstellung, ob Ver- oder Entschlüsselt wird.\n
     * Komponenten:\n
     * - RDBTN_ENCRYPT:  RadioButton, um die Verschlüsselung einzustellen\n
     * - RDBTN_DECRYPT:  RadioButton, um die Entschlüsselunmg einzustellen\n
     * - BUTTON_GROUP:   ButtonGroup, um die Einstellung zu managen\n
     */
    private void initRadioButtons() {
        //Encrypt RdBtn
        RDBTN_ENCRYPT.setForeground(SystemColor.textText);
        RDBTN_ENCRYPT.setFont(FONT);
        RDBTN_ENCRYPT.setContentAreaFilled(false);
        RDBTN_ENCRYPT.setSelected(true);
        RDBTN_ENCRYPT.setForeground(Color.WHITE);
        RDBTN_ENCRYPT.setBounds(COMPONENT_DISTANCE, 140, WINDOW_WIDTH / 2, COMPONENT_HEIGHT);

        BUTTON_GROUP.add(RDBTN_ENCRYPT);

        addComponent(RDBTN_ENCRYPT);

        //Decrypt RdBtn
        RDBTN_DECRYPT.setForeground(SystemColor.textText);
        RDBTN_DECRYPT.setFont(FONT);
        RDBTN_DECRYPT.setForeground(Color.WHITE);
        RDBTN_DECRYPT.setBounds(COMPONENT_DISTANCE, 180, WINDOW_WIDTH / 2, COMPONENT_HEIGHT);
        RDBTN_DECRYPT.setContentAreaFilled(false);

        BUTTON_GROUP.add(RDBTN_DECRYPT);

        addComponent(RDBTN_DECRYPT);

        Log.getLogger().i(getClass().getName() + "initRadioButtons", "RadioButtons initialisiert");
    }

    /**
     * @brief Methode, die die ProgressBar baut
     * @details Die ProgressBar ist ein Indikator dafür, ob eine Ver- oder Entschlüsselung läuft.\n
     * Komponenten:\n
     * - PROGRESSBAR:  ProgressBar, die den Progress anzeigt\n
     */
    private void initProgressBar() {
        PROGRESSBAR.setBounds(COMPONENT_DISTANCE, 280, WINDOW_WIDTH - 50, COMPONENT_HEIGHT - 10);

        addComponent(PROGRESSBAR);

        Log.getLogger().i(getClass().getName() + ".initProgressBar", "ProgressBar initialisiert");
    }

    /**
     * @brief Methode, die die TextArea baut
     * @details Die TextArea zeigt die Entwickler an.\n
     * Komponenten:\n
     * - TXT_DEVELOPED_BY:  TextArea, die Text anzeigt\n
     */
    private void initTextArea() {
        TXT_DEVELOPED_BY.setOpaque(false);
        TXT_DEVELOPED_BY.setFont(FONT);
        TXT_DEVELOPED_BY.setEditable(false);
        TXT_DEVELOPED_BY.setHighlighter(null);
        TXT_DEVELOPED_BY.setForeground(Color.WHITE);
        TXT_DEVELOPED_BY.setBackground(SystemColor.menu);
        TXT_DEVELOPED_BY.setText("Developed by: \r\nBEadvTIM-Team\r\n\r\nLisa Binkert\r\nNikolai Klatt\r\nOliver Seiler");
        TXT_DEVELOPED_BY.setBounds(COMPONENT_DISTANCE, WINDOW_HEIGHT - 235, 240, 300);
        TXT_DEVELOPED_BY.setColumns(10);

        addComponent(TXT_DEVELOPED_BY);

        Log.getLogger().i(getClass().getName() + ".initTextArea", "TextArea initialisiert");
    }

    /**
     * @brief Methode, die den Hintergrund und den Separator baut
     * @details Komponenten:\n
     * - SEPARATOR:     Linie über der ProgressBar\n
     * - BACKGROUND:    JLabel mit Icon\n
     */
    private void initBackGround() {
        //"The Line"
        SEPARATOR.setBounds(COMPONENT_DISTANCE, 240, WINDOW_WIDTH - 50, 3);

        addComponent(SEPARATOR);
        //Icon
        FRM_ENIGMA_GUI.setIconImage(FRAME_ICON.getImage());
        
        //Hintergrundbild
        BACKGROUND.setIcon(new ImageIcon(BACKGROUND_IMG));
        BACKGROUND.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        addComponent(BACKGROUND);

        Log.getLogger().i(getClass().getName() + ".initBackGround", "Background initialisiert");
    }

    /**
     * @brief Methode, die den FileChooser baut
     * @details Komponenten:\n
     * - FILE_CHOOSER:     Linie über der ProgressBar\n
     */
    private void initFileChooser() {
        FILE_CHOOSER = new JFileChooser(FileHandler.getFileHandler().getHOME());
        FILE_CHOOSER.setFileFilter(new FileNameExtensionFilter("EnigmaFiles (*.enigma)", "enigma"));

        Log.getLogger().i(getClass().getName() + ".initFileChooser", "FileChooser initialisiert");
    }

    /**
     * @param component - Komponente, welche hinzugefügt werden soll
     * @brief HelperMethode, die das Hinzufügen einer Komponente in das Frame erleichtert
     */
    private void addComponent(Component component) {
        FRM_ENIGMA_GUI.getContentPane().add(component);
    }

    /**
     * @param currVal - zu setzender Wert
     * @brief Methode zum Updaten der ProgressBar
     * @details Realisiert durch ein Runnable, das den Main Thread nicht behindert.
     */
    public void updateProgressBar(int currVal) {
        SwingUtilities.invokeLater(() -> {
            PROGRESSBAR.setValue(currVal);
            if (RDBTN_ENCRYPT.isSelected()) {
                PROGRESSBAR.setString("Verschlüsselung läuft: " + currVal + "/" + PROGRESSBAR.getMaximum());
            } else {
                PROGRESSBAR.setString("Entschlüsselung läuft: " + currVal + "/" + PROGRESSBAR.getMaximum());
            }
        });
    }

    /**
     * @param maxVal - maximaler Wert der ProgressBar
     * @brief Methode zum Setzen des maximalen Werts der ProgressBar
     */
    public void setProgressBarMax(int maxVal) {
        PROGRESSBAR.setMaximum(maxVal);
    }

    /**
     * @brief Methode, die die Routine hinter dem Cancel Button ausführt
     * @details Der Cancel Button soll folgende Dinge tun können:
     * - Beendet den Ver- bzw. Entschlüsselungsprozess\n
     * - Setzt die GUI nach Abschluss des Ver- bzw. Entschlüsselungsprozesses zurück\n
     */
    private void btnCancelClicked() {
        if (BTN_CANCEL.getText().equals("Again")) {
            FileHandler.getFileHandler().createNewCurrentDirectory();
            FileHandler.getFileHandler().makeLogFile();
            Util.resetPlugBoardList();
        }
        BTN_CANCEL.setText("Cancel");
        BTN_START.setText("O K");
        main.cancelThread();
        Log.getLogger().setLoggingDisabled(false);
        setGUIElementsEnabled(true);
        TF_TEXT.setText("");
        TF_KEY.setText("");
        TF_TEXT.setHint();
        TF_KEY.setHint();
        RDBTN_ENCRYPT.setSelected(true);
        RDBTN_DECRYPT.setSelected(false);
        BTN_START.setEnabled(false);
        PROGRESSBAR.setValue(0);
        PROGRESSBAR.setString("");

        Log.getLogger().i(getClass().getName() + ".btnCancelClicked", "BTN_CANCEL Clicked");
    }

    /**
     * @brief Methode, die die Routine hinter dem Create Key Button ausführt
     * @details Der Create Key Button soll einen zufällig erzeugten Schlüssel in das Key TextField eintragen
     */
    private void btnCreateKeyClicked() {
        TF_KEY.setShowingHint(false);
        TF_KEY.setText(Util.getNewRandomKey());
        TF_KEY.setForeground(Color.BLACK);

        Log.getLogger().i(getClass().getName() + ".btnCreateKeyClicked", "BTN_CREATE_KEY Clicked");
    }

    /**
     * @brief Methode, die die Routine hinter dem Ok Button ausführt
     * @details Der Ok Button soll folgende Dinge tun können:
     * - Startet den Ver- bzw. Entschlüsselungsprozess\n
     * - Deaktiviert die GUI bei Start des Ver- bzw. Entschlüsselungsprozesses \n
     * - Startet die ProgressBar\n
     */
    private void btnOkClicked() {
        //duale  Funktion des O K / Again Buttons
        if (BTN_START.getText().equals("O K")) {
            setGUIElementsEnabled(false);
            PROGRESSBAR.setStringPainted(true);

            if (RDBTN_ENCRYPT.isSelected()) {
                PROGRESSBAR.setString("Verschlüsselung läuft...");
                FileHandler.getFileHandler().setMetaData(0, "Typ: Verschlüsselung");
            } else {
                PROGRESSBAR.setString("Entschlüsselung läuft...");
                FileHandler.getFileHandler().setMetaData(0, "Typ: Entschlüsselung");
            }
            //kann das Logging ausschalten
            if (CHBX_TOGGLE_LOGGING.isSelected()) {
                Log.getLogger().w(getClass().getName() + ".setLoggingDisabled", "LOGGING WURDE DEAKTIVIERT - ES WERDEN KEINE WEITEREN LOGS GESCHRIEBEN");
                Log.getLogger().setLoggingDisabled(CHBX_TOGGLE_LOGGING.isSelected());
            }
            main.btnOkClicked(TF_TEXT.getText(), TF_KEY.getText(), CHBX_REAL_ENIGMA.isSelected(), RDBTN_DECRYPT.isSelected());

        } else {
            //ermöglicht es durch einen Klick, in das Verzeichnis mit den Dateien zu gelangen
            try {
                Desktop.getDesktop().open(new File(FileHandler.getFileHandler().getCurrentHome()));
            } catch (IOException e) {
                e.printStackTrace();
                Log.getLogger().e(getClass().getName() + ".btnOkClicked", "Error während des Versuchs das Verzeichnis: " + FileHandler.getFileHandler().getHOME() + " zu öffnen");
            }
        }
        Log.getLogger().i(getClass().getName() + ".btnOkClicked", "BTN_START Clicked");
    }

    /**
     * @brief Methode, die die Routine hinter dem Datei Button ausführt
     * @details Der Datei Button soll einen OpenFileDialog öffnen, mit dem man *.enigma Files einlesen kann
     * und der Nutzer so eine frühere Ver- bzw. Entschlüsselung wiederholen kann.
     */
    private void btnChooseFileClicked() {
        FILE_CHOOSER.showOpenDialog(FRM_ENIGMA_GUI);
        //prüft, ob eine Datei ausgewählt wurde
        //falls nicht wird sie auch nicht behandelt
        if (FILE_CHOOSER.getSelectedFile() != null) {
            File enigmaFile = FILE_CHOOSER.getSelectedFile();
            //setzt den TEXT
            TF_TEXT.setShowingHint(false);
            TF_TEXT.setForeground(Color.BLACK);
            if (RDBTN_ENCRYPT.isSelected())
                TF_TEXT.setText(FileHandler.getFileHandler().inputFromFile(enigmaFile, "clearText"));
            else TF_TEXT.setText(FileHandler.getFileHandler().inputFromFile(enigmaFile, "encodedText"));
            //setzt den KEY
            TF_KEY.setShowingHint(false);
            TF_KEY.setText(FileHandler.getFileHandler().inputFromFile(enigmaFile, "key"));
            TF_KEY.setForeground(Color.BLACK);
        }
        Log.getLogger().i(getClass().getName() + ".btnChooseFileClicked", "BTN_CHOOSE_FILE Clicked");
    }

    /**
     * @brief Methode, die die Routine nachdem der Ver- bzw. Entschlüsselungsprozess erfolgreich beendet wurde ausführt
     * @details Der Routine soll die Progressbar so verändern, dass sie zum gewählten Prozess das Richtige anzeigt.
     */
    public void onFinished() {
        if (RDBTN_ENCRYPT.isSelected()) PROGRESSBAR.setString("Verschlüsselung abgeschlossen");
        else PROGRESSBAR.setString("Entschlüsselung abgeschlossen");
        PROGRESSBAR.setIndeterminate(false);
        PROGRESSBAR.setValue(PROGRESSBAR.getMaximum());
        BTN_CANCEL.setText("Again");
        BTN_START.setEnabled(true);
        BTN_START.setText("Datei Anzeigen");

        Log.getLogger().i(getClass().getName() + ".onFinished", "onFinished aufgerufen: Prozess beendet");
    }

    /**
     * @param enabled - Ob die Komponenten aktiviert oder deaktiviert werden sollen
     * @brief Methode, die die GUI Elemente deaktiviert
     * @details Es werden nur die Elemente deaktiviert, die der Nutzer während des Prozesses nicht verändern können soll.
     */
    private void setGUIElementsEnabled(boolean enabled) {
        TF_TEXT.setEnabled(enabled);
        TF_KEY.setEnabled(enabled);
        BTN_START.setEnabled(enabled);
        BTN_CHOOSE_FILE.setEnabled(enabled);
        BTN_CREATE_KEY.setEnabled(enabled);
        CHBX_REAL_ENIGMA.setEnabled(enabled);
        CHBX_TOGGLE_LOGGING.setEnabled(enabled);
        RDBTN_ENCRYPT.setEnabled(enabled);
        RDBTN_DECRYPT.setEnabled(enabled);

        Log.getLogger().i(getClass().getName() + ".setGUIElementsEnabled", "GUI Elements enabled set to: " + enabled);
    }
}