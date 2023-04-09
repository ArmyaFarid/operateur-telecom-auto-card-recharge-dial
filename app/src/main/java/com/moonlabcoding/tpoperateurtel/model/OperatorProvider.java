package com.moonlabcoding.tpoperateurtel.model;

import java.util.List;

public class OperatorProvider {
    private List<Operator> mOperatorList;

    public OperatorProvider(List<Operator> operatorList) {
        mOperatorList = operatorList;
    }

    public Operator getOperator(String operatorFirstDigit){
        for (Operator operator : mOperatorList) {
            if(operator.getOperatorFirstDigits().equals(operatorFirstDigit)) {
                return operator;
            }
        }
        return null;
    }
}
