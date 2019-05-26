package de.Enigma.UI;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import de.Enigma.Util.Enums;

@SuppressWarnings("serial")
/**
 * @brief
 */
class HintTextField extends JTextField implements FocusListener, KeyListener {

    private final String hint;
    private boolean showingHint;

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

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");
            setForeground(Color.BLACK);
            showingHint = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            setHint();
            showingHint = true;
        }
    }

    void setHint() {
        super.setText(hint);
        setForeground(Color.GRAY);
        showingHint = true;
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (!checkValidInput(e))
            e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private boolean checkValidInput(KeyEvent e) {
        char c = Character.toUpperCase(e.getKeyChar());
        for (Enums.EAlphabet eAlphabet : Enums.EAlphabet.values()) {
            if (c == eAlphabet.getAsChar())
                return true;
        }
        if (e.getComponent().getName().equals("TEXT")) {
            return c == ' ';
        } else return c == '-' || c == ';' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5';
    }

    void setShowingHint(boolean showingHint) {
        this.showingHint = showingHint;
    }
}