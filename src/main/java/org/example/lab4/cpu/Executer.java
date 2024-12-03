package org.example.lab4.cpu;

public class Executer {
    static public void run(Handle cpu, AlgoProg prog) {
        for (Process instruction : prog) {
            prog.setFlag(cpu.exec(instruction));
        }
    }
}
