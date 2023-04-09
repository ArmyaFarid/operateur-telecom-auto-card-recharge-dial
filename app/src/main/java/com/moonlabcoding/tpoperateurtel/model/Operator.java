package com.moonlabcoding.tpoperateurtel.model;

public class Operator {

    private String mOperatorName;
    private String mOperatorFirstDigits;
    private String mOperatorFormule;

    private String mOperatorHexaColor;

    private String mOperatorSoldeFormule;
    private int mOperatorCodeLength;

    public Operator(String operatorFirstDigits, String operatorFormule,String operatorHexaColor, int operatorCOdeLength) {
        mOperatorFirstDigits = operatorFirstDigits;
        mOperatorFormule = operatorFormule;
        mOperatorCodeLength = operatorCOdeLength;
        mOperatorName = operatorHexaColor;
    }

    public Operator(String operatorName, String operatorFirstDigits, String operatorFormule, String operatorSoldeFormule,String operatorHexaColor, int operatorCodeLength) {
        mOperatorName = operatorName;
        mOperatorFirstDigits = operatorFirstDigits;
        mOperatorFormule = operatorFormule;
        mOperatorSoldeFormule = operatorSoldeFormule;
        mOperatorHexaColor = operatorHexaColor;
        mOperatorCodeLength = operatorCodeLength;
    }

    public String getOperatorName() {
        return mOperatorName;
    }

    public String getOperatorFirstDigits() {
        return mOperatorFirstDigits;
    }

    public String getOperatorFormule() {
        return mOperatorFormule;
    }

    public int getOperatorCodeLength() {
        return mOperatorCodeLength;
    }

    public String getOperatorSoldeFormule() {
        return mOperatorSoldeFormule;
    }

    public String getOperatorHexaColor() {
        return mOperatorHexaColor;
    }
}
