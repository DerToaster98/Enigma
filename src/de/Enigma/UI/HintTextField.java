package de.Enigma.UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class HintTextField extends JTextField implements FocusListener {

    private final String hint;
    private boolean showingHint;

    HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
        setForeground(Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText("");
            setForeground(Color.BLACK);
            showingHint = false;
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            setHint();
            showingHint = true;
        }
    }

    public void setHint() {
        super.setText(hint);
        setForeground(Color.GRAY);
        showingHint = true;
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
}