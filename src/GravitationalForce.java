import acm.graphics.GLabel;

public class GravitationalForce extends AFormula{
    private double f;   //Force
    private double g = 6.674*Math.pow(10,-11);   //Gravitational constant
    private double m1;  //Mass 1
    private double m2;  //Mass 2
    private double r;   // distance between objects
    private final String[] varList= {
            "F (N)","m1 (Kg)","m2 (Kg)",
            "r (m)"};
    public final int numOfMissingVar = 4;

    // Getters and Setters
    public double getF() {
        return f;
    }
    public void setF(double f) { this.f = f; }

    public double getM1() {
        return m1;
    }
    public void setM1(double m1) {
        this.m1 = m1;
    }

    public double getM2() {
        return m2;
    }
    public void setM2(double m2) {
        this.m2 = m2;
    }

    public double getR() {
        return r;
    }
    public void setR(double r) {
        this.r = r;
    }

    public String getVariable(int index) {
        return varList[index];
    }

    @Override
    public String setVariable(String var, double value) {
        String returnValue;
        if(var.equals("F (N)")){
            setF(value);
            returnValue = String.valueOf(getF());
        }
        else if(var.equals("m1 (Kg)")){
            setM1(value);
            returnValue = String.valueOf(getM1());
        }
        else if(var.equals("m2 (Kg)")){
            setM2(value);
            returnValue = String.valueOf(getM2());
        }
        else { // if var.equals("r (m)")
            setR(value);
            returnValue = String.valueOf(getR());
        }
        return returnValue;
    }

    // For Calculating a variable - two decimal places
    public double findF(double m1, double m2, double r)
    {
        f = g*((m1*m2)/Math.pow(r,2));
        return f;
    }

    public double findM1(double f, double m2, double r)
    {
        m1 = (f*Math.pow(r,2))/(g*m2);
        return m1;
    }

    public double findM2(double f, double m1, double r)
    {
        m2 = (f*Math.pow(r,2))/(g*m1);
        return m2;
    }

    public double findR(double f, double m1, double m2)
    {
        r = Math.sqrt(g*((m1*m2)/f));
        return r;
    }

    @Override
    public String generateFormula(String choice)
    {
        if(choice.equals("1")){
            f = findF(m1,m2,r);
            unknown = String.valueOf(f);
        }
        else if(choice.equals("2")){
            m1 = findM1(f,m2,r);
            unknown = String.valueOf(m1);
        }
        else if(choice.equals("3")){
            m2 = findM2(f,m1,r);
            unknown = String.valueOf(m2);
        }
        else if(choice.equals("4")){
            r = findR(f,m1,m2);
            unknown = String.valueOf(r);
        }
        else{
            unknown = "";
        }
        return unknown;
    }

    @Override
    public void missingFormulaMenu() {
        // adds the name of the equation
        option = new GLabel("Angular Momentum",20,20);
        option.setFont(FONT);
        add(option);

        for (int i = 0; i < varList.length; i++) {
            if(i<2) {
                option = new GLabel("[" + (i+1) + "] " + varList[i],20,45+30*i);
            }
            else{
                option = new GLabel("[" + (i+1) + "] " + varList[i],210,-15+30*i);
            }
            option.setFont(FONT);
            add(option);
        }
    }
}
