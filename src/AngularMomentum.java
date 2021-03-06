import acm.graphics.GLabel;

public class AngularMomentum extends AFormula{
    private double l;   // Angular Momentum
    private double m;   // Mass
    private double v;   // Velocity
    private double d;   // Distance
    private double a;   // Angle
    private final String[] varList= {
            "L (Kg m\u00B2/s\u00B2)","m (Kg)","v (m/s)",
            "d (m)", "angle (\u00B0)"};
    public final int numOfMissingVar = 5;

    // Getters and Setters
    public double getL() {
        return l;
    }
    public void setL(double l) { this.l = l; }

    public double getM() {
        return m;
    }
    public void setM(double m) {
        this.m = m;
    }

    public double getV() {
        return v;
    }
    public void setV(double v) {
        this.v = v;
    }

    public double getD() {
        return d;
    }
    public void setD(double d) {
        this.d = d;
    }

    public double getA() {
        return a;
    }
    public void setA(double a) {
        this.a = a;
    }

    @Override
    public String getVariable(int index) {
        return varList[index];
    }

    @Override
    public String setVariable(String var, double value) {
        String returnValue;
        if(var.equals("L (Kg m\u00B2/s\u00B2)")){
            setL(value);
            returnValue = String.valueOf(getL());
        }
        else if(var.equals("m (Kg)")){
            setM(value);
            returnValue = String.valueOf(getM());
        }
        else if(var.equals("v (m/s)")){
            setV(value);
            returnValue = String.valueOf(getV());
        }
        else if(var.equals("d (m)")){
            setD(value);
            returnValue = String.valueOf(getD());
        }
        else { // for when var.equals("angle (°)")
            setA(value);
            returnValue = String.valueOf(getA());
        }
        return returnValue;
    }

    // For Calculating a variable - two decimal places
    public double findL(double userM, double userV, double userD, double userA)
    {

        l = userM*userV*userD*(Math.sin(userA));
        temp = Math.round(l*100.0)/100.0;
        return temp;
    }

    public double findM(double userL, double userV, double userD, double userA)
    {
        m = userL/(userV*userD*Math.sin(userA));
        temp = Math.round(m*100.0)/100.0;
        return temp;
    }

    public double findV(double userL, double userM, double userD, double userA)
    {
        v = userL/(userM*userD*Math.sin(userA));
        temp = Math.round(v*100.0)/100.0;
        return temp;
    }

    public double findD(double userL, double userM, double userV, double userA)
    {
        d = userL/(userM*userV*Math.sin(userA));
        temp = Math.round(d*100.0)/100.0;
        return temp;
    }

    public double findA(double userL, double userM, double userV, double userD)
    {
        a = Math.asin(userL/(userM*userV*userD));
        temp = Math.round(a*100.0)/100.0;
        return temp;
    }

    @Override
    public String generateFormula(String choice)
    {
        if(choice.equals("1")){
            l = findL(m,v,d,a);
            unknown = String.valueOf(l);
        }
        else if(choice.equals("2")){
            m = findM(l,v,d,a);
            unknown = String.valueOf(m);
        }
        else if(choice.equals("3")){
            v = findV(l,m,d,a);
            unknown = String.valueOf(v);
        }
        else if(choice.equals("4")){
            d = findD(l,m,v,a);
            unknown = String.valueOf(d);
        }
        else if(choice.equals("5")){
            a = findA(l,m,v,d);
            unknown = String.valueOf(a);
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
