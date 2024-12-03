package org.example.lab4;

import javafx.scene.Node;
import org.example.lab4.cpu.AlgoProg;
import org.example.lab4.cpu.Factory;
import org.example.lab4.cpu.Handle;
import org.example.lab4.cpu.Process;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ControlModule extends VBox {
    Handle cpu;
    AlgoProg program;

    int index;

    ArrayList<CommandComponent> list;
    HelloApplication caller;

    public ControlModule(int a, HelloApplication caller_) {
        super(a);
        cpu = Factory.build();
        program = new AlgoProg();
        index = 0;
        list = new ArrayList<>();
        caller = caller_;
    }

    public void addCommand() {
        Process temp = new Process("NOPE");
        program.add(temp);
        CommandComponent tempComp = new CommandComponent(temp, program.len() - 1, this);
        getChildren().add(tempComp);
        for (Node temp1 : getChildren()) {
        }
        list.add(tempComp);
    }

    public void pop(CommandComponent caller_) {
        int i = caller_.getIndex();
        program.pop(i);
        for (int j = i + 1; j < list.size(); j++) {
            list.get(j).decrease();
        }
        list.remove(i);
        getChildren().remove(caller_);
    }

    public void reset() {
        if (index - program.getFlag() >= 0 && index - program.getFlag() < list.size()) {
            list.get(index - program.getFlag()).colorWhite();
        }
        index = 0;

        program.setFlag(1);
    }

    public ArrayList<Map.Entry<String, Long>> getStatistic() {
        return program.maxStatistic();
    }

    public void exec() {
        if (index >= 0 && index < program.len()) {
            Process temp = program.get(index);
            list.get(index).colorRed();
            if (index - program.getFlag() >= 0 && index - program.getFlag() < list.size()) {
                list.get(index - program.getFlag()).colorWhite();
            }
            int flag = cpu.exec(temp);
            program.setFlag(flag);
            index += program.getFlag();


            if (temp.com.equals("ST")) {
                caller.update(temp.reg[0], temp.reg[1]);
            } else {
                caller.resetColorMemory();
            }
            caller.update(cpu.regInfo());
        }
    }

    public void swap(int i1, int i2) {
        if (i1 < 0 || i2 < 0 || i1 >= list.size() || i2 >= list.size()) {
            return;
        }
        program.swap(i1, i2);
        list.get(i1).remember();
        list.get(i2).remember();
    }
}
