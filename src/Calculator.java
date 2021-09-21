import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class Calculator extends GraphicsProgram {
    // Global Variable for Equation 1
    FinalPositionFormula e1 = new FinalPositionFormula();
    // Global Variable for Equation 2
    FinalVelocityFormula e2 = new FinalVelocityFormula();
    // Global Variable for Equation 3
    ConstantAngularAcceleration e3 = new ConstantAngularAcceleration();

    // Main calculator variable
    CalculatorLayout calc = new CalculatorLayout(680);
    // Object variable
    GObject object;
    // Input string
    String input;
    // Operands
    String Operand1, Operand2;
    // Possible datatypes
    int int1, int2;
    double db1, db2;
    // Characters
    char opBuffer;
    // Previous Equation
    String eqBuffer = "";
    // Previous Choice in Equation
    String varChoice;
    // Index for variable array in formula classes
    int missingVarIndex=0;
    // Previous Command Used is Next
    boolean wasNext = false;

    boolean firstNext = true;

    public void run() {
        setSize(calc.getWIDTH(),calc.getHEIGHT());
        addMouseListeners();
        add(calc);
        initBooleans();
    }

    // from https://stackabuse.com/java-check-if-string-is-a-number/
    private static boolean isNumeric(String string) {
        int intValue;

//        System.out.println(String.format("Parsing string: \"%s\"", string));

        if(string == null || string.equals("")) {
//            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
//            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

    public void mouseClicked(MouseEvent e) {
        object = calc.getElementAt(e.getX(),e.getY());
        if (object instanceof MyButton) {
            input = ((MyButton) object).getText();
            processButton();
        }
    }

    public void operate(int x, int y){
        if(opBuffer == '+'){
            Operand1 = Integer.toString(MathHandler.add(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == '-'){
            Operand1 = Integer.toString(MathHandler.subtract(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == '÷'){
            Operand1 = Double.toString(MathHandler.divide(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == 'x'){
            Operand1 = Integer.toString(MathHandler.multiply(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
    }

    public void operate(double x, double y){
        if(opBuffer == '+'){
            Operand1 = Double.toString(MathHandler.add(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == '-'){
            Operand1 = Double.toString(MathHandler.subtract(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == '÷'){
            Operand1 = Double.toString(MathHandler.divide(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == 'x'){
            Operand1 = Double.toString(MathHandler.multiply(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
    }

    // Flags
    Boolean isDeletable;
    Boolean wasNumber;
    Boolean wasOperator;
    Boolean performOperator;
    Boolean firstInstance = true;
    Boolean secondInstance = false;
    Boolean nInstance;
    Boolean wasEquals;

    private void initBooleans(){
        isDeletable = false;
        wasNumber = false;
        wasOperator = false;
        performOperator = false;
        nInstance = false;
        wasEquals = false;
        firstNext = true;
        Operand1 = "";
        Operand2 = "";
        opBuffer = ' ';
    }

    private void performE1(int missingVar){
        if(missingVarIndex==(missingVar-1)){
            missingVarIndex++;
        }
        remove(e1);

        // Is done after inputting the last variable after pressing next
        if(missingVarIndex==e1.numOfMissingVar){
            calc.clearMemoDisplay();
            calc.clearNumBuffer();
            calc.setMainDisplay("0");
            calc.setMemoDisplay(e1.getVariable(missingVar-1) + ": " + e1.generateFormula(String.valueOf(missingVar)));
            missingVarIndex = 0;
        }
        else{
            calc.clearMemoDisplay();
            calc.clearNumBuffer();
            calc.setMainDisplay("0");
            calc.setMemoDisplay(e1.getVariable(missingVarIndex) + ": ");
            missingVarIndex++;
        }
    }

    private void performE2(int missingVar){
        if(missingVarIndex==(missingVar-1)){
            missingVarIndex++;
        }
        remove(e2);
        if(missingVarIndex==e2.numOfMissingVar){
            calc.clearMemoDisplay();
            calc.clearNumBuffer();
            calc.setMainDisplay("0");
            calc.setMemoDisplay(e2.getVariable(missingVar-1) + ": " + e2.generateFormula(String.valueOf(missingVar)));
            missingVarIndex = 0;
        }
        else{
            calc.clearMemoDisplay();
            calc.clearNumBuffer();
            calc.setMainDisplay("0");
            calc.setMemoDisplay(e2.getVariable(missingVarIndex) + ": ");
            missingVarIndex++;
        }
    }

    private void performE3(int missingVar){
        if(missingVarIndex==(missingVar-1)){
            missingVarIndex++;
        }
        remove(e2);
        if(missingVarIndex==e3.numOfMissingVar){
            calc.clearMemoDisplay();
            calc.clearNumBuffer();
            calc.setMainDisplay("0");
            calc.setMemoDisplay(e3.getVariable(missingVar-1) + ": " + e3.generateFormula(String.valueOf(missingVar)));
            missingVarIndex = 0;
        }
        else{
            calc.clearMemoDisplay();
            calc.clearNumBuffer();
            calc.setMainDisplay("0");
            calc.setMemoDisplay(e3.getVariable(missingVarIndex) + ": ");
            missingVarIndex++;
        }
    }

    // Is used for removing all formula menus before placing a new one as well as clearing both memo and main display
    private void resetFormulaMenu()
    {
        missingVarIndex = 0;
        calc.clearMemoDisplay();
        calc.clearMainDisplay();
        remove(e1);
        remove(e2);
        remove(e3);
//        remove(e4);
//        remove(e5);
//        remove(e6);
    }

    private void processButton(){
        int[] givenValues = new int[5];
        // If its the starter ones
        // I. Handle special cases: Clear Element, Clear All, and  Delete

        if(input.equals("E1 ")){
            resetFormulaMenu();
            e1.missingFormulaMenu();
            add(e1);
            eqBuffer = input;
        }

        if(input.equals("E2 ")){
            resetFormulaMenu();
            e2.missingFormulaMenu();
            add(e2);
            eqBuffer = input;
        }

        if(input.equals("E3 ")){
            resetFormulaMenu();
            e3.missingFormulaMenu();
            add(e3);
            eqBuffer = input;
        }
//        if(input.equals("E4 ")){
//            remove(e1);
//            missingVarIndex = 0;
//            calc.clearMemoDisplay();
//            calc.clearMainDisplay();
//            e4.missingFormulaMenu();
//            add(e4);
//            eqBuffer = input;
//        }

        //        if(input.equals("E5 ")){
//            remove(e1);
//            missingVarIndex = 0;
//            calc.clearMemoDisplay();
//            calc.clearMainDisplay();
//            e5.missingFormulaMenu();
//            add(e5);
//            eqBuffer = input;
//        }

        //        if(input.equals("E6 ")){
//            remove(e1);
//            missingVarIndex = 0;
//            calc.clearMemoDisplay();
//            calc.clearMainDisplay();
//            e6.missingFormulaMenu();
//            add(e6);
//            eqBuffer = input;
//        }

        if (input.equals("CE ")) {
            // clears main display
            calc.clearMainDisplay();
            System.out.println("Clear Element");
            wasNext = false;
            return;
        }
        if (input.equals("C")) {
            missingVarIndex = 0;
            calc.clearMainDisplay();
            calc.clearMemoDisplay();
            // Remove display in e1 memo
            remove(e1);
            remove(e2);
            initBooleans();
            firstInstance = true;
            secondInstance = false;
            System.out.println("Clear Called");
            wasNext = false;
            return;
        }
        if (input.equals("⌫") && isDeletable) {
            calc.delOneMain();
            System.out.println("Delete Called");
            return;
        }
        // II. Handle Number Inputs;
        if(isNumeric(input)){
            // To know which equation should be used to compute
            if((eqBuffer.equals("E1 ") || eqBuffer.equals("E2 ") || eqBuffer.equals("E3 ") || eqBuffer.equals("E4 ") || eqBuffer.equals("E5 ") || eqBuffer.equals("E6 ")) && missingVarIndex == 0){
                varChoice = input;
                wasNext = false;
            }

// After pressing next - UPDATE: the setting of the value is put in the operator input "next" instead
//            if(wasNext){
//                if(eqBuffer.equals("E1 ")){
//                    e1.setVariable(e1.getVariable(missingVarIndex-1),Double.parseDouble(input));
//                    calc.setMainDisplay(input);
//                    calc.setMemoDisplay(input);
//                }
//                if(eqBuffer.equals("E2 ")){
//                    e2.setVariable(e2.getVariable(missingVarIndex-1),Double.parseDouble(input));
//                    calc.setMainDisplay(input);
//                    calc.setMemoDisplay(input);
//                }
//            }
            if(wasOperator){
                calc.clearMainDisplay();
                calc.setMainDisplay("0");
            }
            if(wasEquals){
                calc.clearMemoDisplay();
                initBooleans();
                firstInstance = true;
                secondInstance = false;
            }

            wasNext = false;
            wasOperator = false;
            wasNumber = true;
            wasEquals = false;
            isDeletable = true;
            calc.setMainDisplay(input.charAt(0));
            return;
        }
        if(input.equals(".")){
            if(!calc.getMainDisplay().contains(".")){
                calc.setMainDisplay(input.charAt(0));
            }
        }
        if(input.equals("±")){
            calc.negateElement();
        }

        // III. Operator Inputs;
        if(input.equals("Next ")){
            if(wasNumber){
                if(eqBuffer.equals("E1 ")){
                    if(!firstNext)
                        e1.setVariable(e1.getVariable(missingVarIndex-1),Double.parseDouble(calc.getMainDisplay()));
                    performE1(Integer.parseInt(varChoice));
                }
                else if(eqBuffer.equals("E2 ")){
                    if(!firstNext)
                        e2.setVariable(e2.getVariable(missingVarIndex-1),Double.parseDouble(calc.getMainDisplay()));
                    performE2(Integer.parseInt(varChoice));
                }
                else if(eqBuffer.equals("E3 ")){
                    if(!firstNext)
                        e3.setVariable(e3.getVariable(missingVarIndex-1),Double.parseDouble(calc.getMainDisplay()));
                    performE3(Integer.parseInt(varChoice));
                }
//                else if(eqBuffer.equals("E4 ")){
//                    performE4(Integer.parseInt(varChoice));
//                }
//                else if(eqBuffer.equals("E5 ")){
//                    performE5(Integer.parseInt(varChoice));
//                }
//                else if(eqBuffer.equals("E6 ")){
//                    performE6(Integer.parseInt(varChoice));
//                }
            }
            wasNext=true;
            firstNext = false;
        }

        if(input.equals("=") || input.equals("+") || input.equals("-") || input.equals("÷") || input.equals("x")){
            // If previous input was a operator
            if(wasOperator){
                calc.delOneMemo();
                performOperator = false;
            }
            // If previous input was a number
            if(wasNumber){
                if(nInstance){
                    Operand2 = calc.getMainDisplay();
                }
                if(secondInstance){
                    nInstance = true;
                    secondInstance = false;
                    Operand2 = calc.getMainDisplay();
                }
                if(firstInstance){
                    secondInstance = true;
                    firstInstance = false;
                    Operand1 = calc.getMainDisplay();
                }
                calc.setMemoDisplay(calc.getMainDisplay());
            }
            else{
                if(firstInstance){
                    secondInstance = true;
                    firstInstance = false;
                    calc.setMemoDisplay("0");
                    Operand1 = calc.getMainDisplay();
                }
            }
            if(wasOperator && input.equals("=")){
                performOperator = true;
            }
            // If equals is called and there was an operator to be performed
            if(performOperator && input.equals("=")){
                calc.clearMemoDisplay();
                if(secondInstance){
                    calc.setMemoDisplay(Operand1+opBuffer+Operand1);
                }
                else{
                    calc.setMemoDisplay(Operand1+opBuffer+Operand2);
                }

            }
            // If Operator should be performed
            if(performOperator){
                if(secondInstance){
                    Operand2 = Operand1;
                }

                System.out.println(Operand1 + " " + Operand2);
                if(!Operand1.contains(".") && !Operand2.contains(".")){
                    int1 = Integer.parseInt(Operand1);
                    int2 = Integer.parseInt(Operand2);
                    operate(int1,int2);
                }
                else{
                    db1 = Double.parseDouble(Operand1);
                    db2 = Double.parseDouble(Operand2);
                    operate(db1,db2);
                }
            }
            // If x or / is called
            if((input.equals("x") || input.equals("÷")) && !firstInstance){
                calc.clearMemoDisplay();
                calc.setMemoDisplay(Operand1);
            }

            calc.setMemoDisplay(input);
            if(!input.equals("=")){
                opBuffer = input.charAt(0);
                wasEquals = false;
            }

            else{
                wasEquals = true;
            }
            isDeletable = false;
            wasNumber = false;
            wasOperator = true;
            performOperator = true;
        }
    }
}