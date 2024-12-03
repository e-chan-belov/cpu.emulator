package org.example.lab4;

import org.example.lab4.cpu.Process;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class HelloApplication extends Application {
    ControlModule InstructionBox;
    Label[] reg;
    ArrayList<MemoryLabelComponent> OperMem;
    VBox CommandRate;

    @Override
    public void start(Stage stage) throws IOException {

        GridPane root = new GridPane();

        // Columns
        ColumnConstraints ProgramColumn = new ColumnConstraints();
        ProgramColumn.setPercentWidth(67);
        ColumnConstraints InfoColumn = new ColumnConstraints();
        InfoColumn.setPercentWidth(33);
        root.getColumnConstraints().addAll(ProgramColumn, InfoColumn);

        //Rows
        RowConstraints LabelRow = new RowConstraints();
        LabelRow.setPercentHeight(20);
        RowConstraints Row1 = new RowConstraints();
        Row1.setPercentHeight(30);
        RowConstraints Row2 = new RowConstraints();
        Row2.setPercentHeight(50);
        root.getRowConstraints().addAll(LabelRow, Row1, Row2);

        //Pane for control
        FlowPane ControlPane = new FlowPane();
        ControlPane.setAlignment(Pos.CENTER);

        Button ADD = new Button("ADD NEW COMMAND");
        FlowPane.setMargin(ADD, new Insets(10, 10, 10, 10));
        ADD.setOnAction( _ -> addNewCommand() );

        Button RESET = new Button("RESET PROGRAM");
        FlowPane.setMargin(RESET, new Insets(10, 10, 10, 10));
        RESET.setOnAction( _ -> resetProgram());

        Button NEXT = new Button("EXECUTE NEXT COMMAND");
        FlowPane.setMargin(NEXT, new Insets(10, 10, 10, 10));
        NEXT.setOnAction( _ -> execNextCommand());

        ControlPane.getChildren().addAll(ADD, RESET, NEXT);
        root.add(ControlPane, 0, 0);

        //Scroll for instructions
        InstructionBox = new ControlModule(10, this);
        ScrollPane ProgramScroll = new ScrollPane(InstructionBox);
        root.add(ProgramScroll, 0, 1, 1, 2);

        //Registers info
        reg = new Label[] {new Label("0"), new Label("0"), new Label("0"), new Label("0")};
        GridPane RegInf = new GridPane();

        ColumnConstraints R0 = new ColumnConstraints();
        R0.setPercentWidth(25);
        ColumnConstraints R1 = new ColumnConstraints();
        R1.setPercentWidth(25);
        ColumnConstraints R2 = new ColumnConstraints();
        R2.setPercentWidth(25);
        ColumnConstraints R3 = new ColumnConstraints();
        R3.setPercentWidth(25);

        RowConstraints RegInfoLabel = new RowConstraints();
        RegInfoLabel.setPercentHeight(33);
        RowConstraints RegNames = new RowConstraints();
        RegNames.setPercentHeight(33);
        RowConstraints Regs = new RowConstraints();
        Regs.setPercentHeight(34);

        RegInf.getColumnConstraints().addAll(R0, R1, R2, R3);
        RegInf.getRowConstraints().addAll(RegInfoLabel, RegNames, Regs);
        root.add(RegInf, 1, 0);

        Label labelofregistersname = new Label("Registers");
        labelofregistersname.setAlignment(Pos.CENTER);
        RegInf.add(labelofregistersname,0, 0);

        RegInf.add(new Label("R0"), 0, 1);
        RegInf.add(new Label("R1"), 1, 1);
        RegInf.add(new Label("R2"), 2, 1);
        RegInf.add(new Label("R3"), 3, 1);

        RegInf.add(reg[0], 0, 2);
        RegInf.add(reg[1], 1, 2);
        RegInf.add(reg[2], 2, 2);
        RegInf.add(reg[3], 3, 2);



        // Memory info
        OperMem = new ArrayList<>();
        FlowPane MemoryPane = new FlowPane();
        for (int i = 0; i < 128; i++) {
            OperMem.add(new MemoryLabelComponent(i));
            MemoryPane.getChildren().add(OperMem.getLast());
        }
        root.add(MemoryPane, 1, 1);



        // Частота появления инструкций
        CommandRate = new VBox();
        root.add(CommandRate, 1, 2);



        Scene scene = new Scene(root, 1000, 800);
        stage.setScene(scene);

        stage.setTitle("Assembly");

        stage.show();
    }

    void addNewCommand() {
        InstructionBox.addCommand();
        updateRate();
    }
    void resetProgram() {
        InstructionBox.reset();
        updateRate();
    }
    void execNextCommand() {
        InstructionBox.exec();
        updateRate();
    }

    void update(int[] NewReg) {
        for (int i = 0; i < 4; i++) {
            if (Integer.parseInt(reg[i].getText()) != NewReg[i]) {
                reg[i].setTextFill(Color.RED);
            }
            else {
                reg[i].setTextFill(Color.BLACK);
            }
            reg[i].setText(String.valueOf(NewReg[i]));
        }
    }

    void update(int a, int index) {

        if (OperMem.get(index).getValue() != Integer.parseInt(reg[a].getText())) {
            OperMem.get(index).setTextFill(Color.RED);
        }
        OperMem.get(index).setValue(Integer.parseInt(reg[a].getText()));
    }

    void updateRate() {
        CommandRate.getChildren().removeAll(CommandRate.getChildren());
        for (Map.Entry<String, Long> temp : InstructionBox.getStatistic()) {
            CommandRate.getChildren().add(new Label(temp.getKey() + ":" + temp.getValue()));
        }
    }

    void resetColorMemory() {
        for (MemoryLabelComponent temp : OperMem) {
            temp.setTextFill(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}