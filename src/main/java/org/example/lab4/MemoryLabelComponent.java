package org.example.lab4;

import javafx.scene.control.Label;

public class MemoryLabelComponent extends Label {
    int index;
    int value;

    public MemoryLabelComponent(int i) {
        index = i;
        value = 0;
        setText(i + ":" + value + " ");
    }

    int getValue() {
        return value;
    }
    void setValue(int a) {
        value = a;
        setText(index + ":" + value + " ");
    }
}
