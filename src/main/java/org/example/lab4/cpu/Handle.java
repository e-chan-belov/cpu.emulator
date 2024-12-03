package org.example.lab4.cpu;

public interface Handle {
    int exec(Process command);
    int[] regInfo();
}
