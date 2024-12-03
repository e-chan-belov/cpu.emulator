package org.example.lab4;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import org.example.lab4.cpu.AlgoProg;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.example.lab4.cpu.Process;

public class CommandComponent extends HBox {
    TextField com, r0, r1;
    Process command;
    int index;
    ControlModule parent;

    public CommandComponent(Process temp, int i, ControlModule parent_) {
        super(10);
        command = temp;
        parent = parent_;
        index = i;
        setPrefHeight(46);
        setAlignment(Pos.CENTER);

        Button del = new Button("X");
        del.setOnAction(_ -> {
            parent.pop(this);
        });
        del.setTextFill(Color.RED);
        HBox.setMargin(del, new Insets(0, 0, 0, 10));

        Button left = new Button("<");
        left.setOnAction(_ -> {
            parent.swap(index, index - 1);
        });
        Button right = new Button(">");
        right.setOnAction(_ -> {
            parent.swap(index, index + 1);
        });

        com = new TextField("NOPE");
        com.setOnKeyTyped(_ -> {
            command.com = com.getText();
        });
        com.setPrefWidth(56);
        HBox.setMargin(com, new Insets(0, 0, 0, 40));

        r0 = new TextField();
        r0.setOnKeyTyped(_ -> {
            if (r0.getText() != "" && r0.getText() != " ") {
                command.reg[0] = Integer.parseInt(r0.getText());
            }
        });
        r0.setPrefWidth(52);
        HBox.setMargin(r0, new Insets(0, 0, 0, 130));
        r1 = new TextField();
        r1.setOnKeyTyped(_ -> {
            if (r1.getText() != "" && r1.getText() != " ") {
                command.reg[1] = Integer.parseInt(r1.getText());
            }
        });
        r1.setPrefWidth(52);


        getChildren().addAll(del, left, right, com, r0, r1);
    }
    public Process getCommand() {
        return command;
    }
    public int getIndex() {
        return index;
    }
    public void decrease() {
        index--;
    }
    public void remember() {
        com.setText(command.com);
        r0.setText(String.valueOf(command.reg[0]));
        r1.setText(String.valueOf(command.reg[1]));
    }
    public void colorRed() {
        setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
    }
    public void colorWhite() {
        setBackground(null);
    }
}
