package de.Enigma.UI;


import de.Enigma.Util.Enums;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Nikolai Klatt
 * @brief Klasse für custom JTextFields
 * @details Die Klasse erzeugt spezielle JTextFields, welche einen Hint haben und die Eingabe beschränken.\n
 * Der erlaubte Input für TEXT ist:\n
 * - A-Z\n
 * - '.' ',' '!' '?' ' '\n
 * Der erlaubte Input für KEY ist:\n
 * - A-Z\n
 * - 1-5\n
 */
class HintTextField extends JTextField implements FocusListener, KeyListener {

    private static final long serialVersionUID = -5418099739080562425L;
    private final String hint;
    private boolean showingHint;

    /**
     * @param name - Ojektname der zur Unterscheidung dienen soll
     * @param hint - Text der angezeigt werden soll, wenn im TextField nichts steht
     * @param font - Schriftart, die gesetzt werden soll
     * @brief Konstruktor die ein HintTextField erzeugt
     * @details Der Konstruktor setzt alle Parameter für die Objekte.
     * @see javax.swing.JTextField
     */
    HintTextField(final String name, final String hint, final Font font) {
        super(hint);
        //setzt die generellen Eigenschaften des Textfeldes
        this.hint = hint;
        this.showingHint = true;
        super.setName(name);
        super.setFont(font);
        //deaktiviert copy/paste
        super.setTransferHandler(null);
        super.addFocusListener(this);
        super.addKeyListener(this);
        super.setColumns(10);
        setForeground(Color.GRAY);
    }

    /**
     * @param e - Event Parameter
     * @brief Methode des FocusListeners
     * @details Wird aufgerufen, wenn das TextField fokusiert wird, das heißt, wenn der Curser zum schreiben im Feld ist.
     */
    @Override
    public void focusGained(FocusEvent e) {
        //deaktiviert den Hint
        if (this.getText().isEmpty()) {
            super.setText("");
            setForeground(Color.BLACK);
            showingHint = false;
        }
    }

    /**
     * @param e - Event Parameter
     * @brief Methode des FocusListeners
     * @details Wird aufgerufen, wenn das TextField den Fokus verliert.
     */
    @Override
    public void focusLost(FocusEvent e) {
        //reaktiviert den Hint
        if (this.getText().isEmpty()) {
            setHint();
            showingHint = true;
        }
    }

    /**
     * @brief Methode, die den Hint setzt
     */
    void setHint() {
        //setzt den Hint und alle damit verbundenen Eigenschaften
        super.setText(hint);
        setForeground(Color.GRAY);
        showingHint = true;
    }

    /**
     * @return Liefert den eingegebenen Text zurück
     * @brief Methode, die den Text zurückgibt
     */
    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }

    /**
     * @param e - KeyEvent Parameter
     * @brief Methode des KeyListeners
     * @details Wird aufgerufen, wenn ein Buchstabe oder Zeichen eingegeben wird. Wenn das Zeichen nicht erlaubt ist, wird es verworfen.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if (!checkValidInput(e) || (this.getName().equals("KEY") && this.getText().length() >= 49)) {
            //Zeichen wird verworfen
            e.consume();
        } else {
            e.setKeyChar(Character.toUpperCase(e.getKeyChar()));
        }

    }

    /**
     * @param e - KeyEvent Parameter
     * @brief Methode des KeyListeners
     * @details Setzt die Sonderzeichen im Key TextField, sofern manuell getippt wird.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (this.getName().equals("KEY") && checkValidInput(e)) {
            int c = this.getText().length();
            String txt = this.getText();
            switch (c) {
                //an diesen Stellen soll ein "-" in den Key gesetzt werden
                case 1:
                case 5:
                case 9:
                case 13:
                case 17:
                case 21:
                case 25:
                case 29:
                case 33:
                case 37:
                case 41:
                case 45:
                    txt += "-";
                    break;
                //an diesen Stellen soll ein ";" in den Key gesetzt werden
                case 11:
                case 15:
                case 19:
                case 23:
                case 27:
                case 31:
                case 35:
                case 39:
                case 43:
                case 47:
                    txt += ";";
            }
            this.setText(txt);
        }
    }

    /**
     * @param e - KeyEvent Parameter
     * @brief Methode des KeyListeners
     * @details nicht benötigt
     */
    @Override
    public void keyReleased(KeyEvent e) {
        //EMPTY
    }

    /**
     * @param e - KeyEvent Parameter
     * @return Gibt zurück, ob der Input gültig ist
     * @brief Methode, die checkt, ob ein Zeichen erlaubt ist, oder nicht
     */
    private boolean checkValidInput(KeyEvent e) {
        char c = Character.toUpperCase(e.getKeyChar());
        //Buchstaben
        for (Enums.EAlphabet eAlphabet : Enums.EAlphabet.values()) {
            if (c == eAlphabet.getAsChar())
                return true;
        }
        //Sonderzeichen
        if (this.getName().equals("TEXT")) {
            return c == ' ' || c == '.' || c == ',' || c == '?' || c == '!';
        } else return c == '1' || c == '2' || c == '3' || c == '4' || c == '5';
    }

    /**
     * @return Gibt Aussage darüber, ob der Hint angezeigt wird, oder nicht
     * @brief Methode, die showingHint zurückgibt
     */
    boolean isShowingHint() {
        return showingHint;
    }

    /**
     * @param showingHint - Gibt Aussage darüber, ob der Hint angezeigt werden soll, oder nicht
     * @brief Methode, die showingHint setzt
     */
    void setShowingHint(boolean showingHint) {
        this.showingHint = showingHint;
    }
}