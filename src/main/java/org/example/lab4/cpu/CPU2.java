package org.example.lab4.cpu;

public class CPU2 extends CPU {
    public CPU2() {
        super();
    }
    private int div(int r1, int r2) {
        reg[r1] = reg[r1] / reg[r2];
        return 1;
    }
    private int jump() {
        return reg[3];
    }
    private int jumpeq(int r1, int r2) {
        if (reg[r1] == reg[r2]) {
            return reg[3];
        }
        return 1;
    }
    private int nope() {
        return 1;
    }
    private int jumpgreq(int r1, int r2) {
        if (reg[r1] >= reg[r2]) {
            return reg[3];
        }
        return 1;
    }
    private int inc(int r) {
        reg[r] += 1;
        return 1;
    }
    private int mul(int r1, int r2) {
        reg[r1] = reg[r1] * reg[r2];
        return 1;
    }
    public int exec(Process command) {
        switch(command.com) {
            case "MUL":
                return mul(command.reg[0], command.reg[1]);
            case "DIV":
                return div(command.reg[0], command.reg[1]);
            case "JE":
                return jumpeq(command.reg[0], command.reg[1]);
            case "JGE":
                return jumpgreq(command.reg[0], command.reg[1]);
            case "NOPE":
                return nope();
            case "INC":
                return inc(command.reg[0]);
            case "JMP":
                return jump();
            default:
                return super.exec(command);
        }
    }
}
