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
 * - '-' ';'\n
 * - 1-5\n
 */
@SuppressWarnings("serial")
class HintTextField extends JTextField implements FocusListener, KeyListener {

    private final String hint;
    private boolean showingHint;

    /**
     * @param name Ojektname der zur Unterscheidung dienen soll
     * @param hint Text der angezeigt werden soll, wenn im TextField nichts steht
     * @param font Schriftart, die gesetzt werden soll
     * @brief Konstruktor die ein HintTextField erzeugt
     * @details Der Konstruktor setzt alle Parameter für die Objekte.
     * @see javax.swing.JTextField
     */
    HintTextField(final String name, final String hint, final Font font) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.setName(name);
        super.setFont(font);
        super.addFocusListener(this);
        super.addKeyListener(this);
        super.setColumns(10);
        setForeground(Color.GRAY);
    }

    /**
     * @param e Event Parameter
     * @brief Methode des FocusListeners
     * @details Wird aufgerufen, wenn das TextField fokusiert wird, das heißt, wenn der Curser zum schreiben im Feld ist.
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");
            setForeground(Color.BLACK);
            showingHint = false;
        }
    }

    /**
     * @param e Event Parameter
     * @brief Methode des FocusListeners
     * @details Wird aufgerufen, wenn das TextField den Fokus verliert.
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            setHint();
            showingHint = true;
        }
    }

    /**
     * @brief Methode, die den Hint setzt
     */
    void setHint() {
        super.setText(hint);
        setForeground(Color.GRAY);
        showingHint = true;
    }

    /**
     * @brief Methode, die den Text zurückgibt
     */
    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }

    /**
     * @param e KeyEvent Parameter
     * @brief Methode des KeyListeners
     * @details Wird aufgerufen, wenn ein Buchstabe oder Zeichen eingegeben wird. Wenn das Zeichen nicht erlaubt ist, wird es verworfen.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if (!checkValidInput(e))
            e.consume();
    }

    /**
     * @param e KeyEvent Parameter
     * @brief Methode des KeyListeners
     * @details nicht benötigt
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * @param e KeyEvent Parameter
     * @brief Methode des KeyListeners
     * @details nicht benötigt
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * @param e KeyEvent Parameter
     * @brief Methode, die checkt, ob ein Zeichen erlaubt ist, oder nicht
     */
    private boolean checkValidInput(KeyEvent e) {
        char c = Character.toUpperCase(e.getKeyChar());
        for (Enums.EAlphabet eAlphabet : Enums.EAlphabet.values()) {
            if (c == eAlphabet.getAsChar())
                return true;
        }
        if (e.getComponent().getName().equals("TEXT")) {
            return c == ' ' || c == '.' || c == ',' || c == '?' || c == '!';
        } else return c == '-' || c == ';' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5';
    }

    /**
     * @param showingHint Gibt Aussage darüber, ob der Hint angezeigt werden soll, oder nicht
     * @brief Methode, die showingHint setzt
     */
    void setShowingHint(boolean showingHint) {
        this.showingHint = showingHint;
    }

    /**
     * @return Gibt Aussage darüber, ob der Hint angezeigt wird, oder nicht
     * @brief Methode, die showingHint zurückgibt
     */
    boolean isShowingHint() {
        return showingHint;
    }
}