package calculator.deque;

import calculator.element.Element;
import calculator.element.operand.Operand;
import calculator.element.operaor.Operator;
import calculator.element.operaor.OperatorFactory;

import java.util.*;

public class ElementDeque {
    Deque<Element> elements = new ArrayDeque<>();

    public ElementDeque(String[] splitInput) {
        if (splitInput == null)
            throw new IllegalArgumentException("splitInput is null");

        for (int index = 0; index < splitInput.length; index++) {
            addElement(splitInput[index], index);
        }
    }

    private void addElement(String element, int index) {
        if(isOperandPosition(index)) {
            addOperand(element);
        } else {
            addOperator(element);
        }
    }

    private void addOperand(String element) {
        int intValue = Integer.parseInt(element);
        elements.addLast(new Operand(intValue));
    }

    private void addOperator(String element) {
        Operator operator = OperatorFactory.valueOf(element);
        elements.addLast(operator);
    }


    private boolean isOperandPosition(int index) {
        return index % 2 == 0;
    }

    public void addResult(Element operand) {
        elements.addFirst(operand);
    }

    public Operand popOperand() {
        return (Operand) elements.pop();
    }

    public Operator popOperator() {
        return (Operator) elements.pop();
    }

    public boolean isQuitCalculate() {
        return elements.size() <= 1;
    }

    public int getValue() {
        return popOperand().getValue();
    }

}
