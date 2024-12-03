package org.example.lab4.cpu;

public class CPU implements Handle {
    protected int[] reg;
    protected int[] memory;
    public CPU() {
        reg = new int[] {0, 0, 0, 0};
        memory = new int[128];
    }
    private int print() {
        System.out.printf("%d %d %d %d\n", reg[0], reg[1], reg[2], reg[3]);
        return 1;
    }
    private int ld(int m, int r) {
        reg[r] = memory[m];
        return 1;
    }
    private int st(int r, int m) {
        memory[m] = reg[r];
        return 1;
    }
    private int init(int a, int r) {
        reg[r] = a;
        return 1;
    }
    private int mv(int r1, int r2) {
        reg[r2] = reg[r1];
        return 1;
    }
    private int add(int r1, int r2) {
        reg[r1] = reg[r1] + reg[r2];
        return 1;
    }
    private int sub(int r1, int r2) {
        reg[r1] = reg[r1] - reg[r2];
        return 1;
    }
    private int mul() {return 1;}
    public int exec(Process command) {
        switch(command.com) {
            case "LD":
                return ld(command.reg[0], command.reg[1]);
            case "ST":
                return st(command.reg[0], command.reg[1]);
            case "INIT":
                return init(command.reg[0], command.reg[1]);
            case "MOVE":
                return mv(command.reg[0], command.reg[1]);
            case "ADD":
                return add(command.reg[0], command.reg[1]);
            case "SUB":
                return sub(command.reg[0], command.reg[1]);
            case "MUL":
                return mul();
            case "SHOW":
                return print();
            default:
                return 1;
        }
    }
    public int[] regInfo() {
        return reg;
    }
}

