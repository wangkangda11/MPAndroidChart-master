package com.xxmassdeveloper.mpchartexample.view.viewbeen;

public class Wsbyinfo {

    /**
     * fProvinceName : 陕西
     * fMaximumOutput : 1108.9706
     * fMinimumOutput : 7306.0293
     * fActualOutput : 7306.0293
     */
    private String fProvinceName;
    private double fMaximumOutput;
    private double fMinimumOutput;
    private double fActualOutput;

    public Wsbyinfo(String fProvinceName) {
        this.fProvinceName = fProvinceName;
    }

    public Wsbyinfo(String fProvinceName, double fMaximumOutput, double fMinimumOutput, double fActualOutput) {
        this.fProvinceName = fProvinceName;
        this.fMaximumOutput = fMaximumOutput;
        this.fMinimumOutput = fMinimumOutput;
        this.fActualOutput = fActualOutput;
    }

    public String getFProvinceName() {
        return fProvinceName;
    }

    public void setFProvinceName(String fProvinceName) {
        this.fProvinceName = fProvinceName;
    }

    public double getFMaximumOutput() {
        return fMaximumOutput;
    }

    public void setFMaximumOutput(double fMaximumOutput) {
        this.fMaximumOutput = fMaximumOutput;
    }

    public double getFMinimumOutput() {
        return fMinimumOutput;
    }

    public void setFMinimumOutput(double fMinimumOutput) {
        this.fMinimumOutput = fMinimumOutput;
    }

    public double getFActualOutput() {
        return fActualOutput;
    }

    public void setFActualOutput(double fActualOutput) {
        this.fActualOutput = fActualOutput;
    }
}
