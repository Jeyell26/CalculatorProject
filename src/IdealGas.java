import acm.graphics.GLabel;

public class IdealGas extends AFormula{
    private double p; // Pressure
    private double v; // Volume
    private double n; // moles of gas
    private double r = 0.0821; // constant
    private double t; // temperature
    private final String[] varList= {
            "P (atm)","V (L)","n (moles)",
            "T (K)"};
    public final int numOfMissingVar = 4;

    // Getters and Setters
    public double getP() {
        return p;
    }
    public void setP(double p) { this.p = p; }

    public double getV() {
        return v;
    }
    public void setV(double v) {
        this.v = v;
    }

    public double getN() {
        return n;
    }
    public void setN(double n) {
        this.n = n;
    }

    public double getT() {
        return t;
    }
    public void setT(double t) {
        this.t = t;
    }

    public String getVariable(int index) {
        return varList[index];
    }

    @Override
    public String setVariable(String var, double value) {
        String returnValue;
        if(var.equals("P (atm)")){
            setP(value);
            returnValue = String.valueOf(getP());
        }
        else if(var.equals("V (L)")){
            setV(value);
            returnValue = String.valueOf(getV());
        }
        else if(var.equals("n (moles)")){
            setN(value);
            returnValue = String.valueOf(getN());
        }
        else { // if var.equals("T (K)")
            setT(value);
            returnValue = String.valueOf(getT());
        }
        return returnValue;
    }

    // For Calculating a variable - two decimal places
    public double findP(double v, double n, double t)
    {
        p = (n*r*t)/v;
        temp = Math.round(p*100.0)/100.0;
        return temp;
    }

    public double findV(double p, double n, double t)
    {
        v = (n*r*t)/p;
        temp = Math.round(v*100.0)/100.0;
        return temp;
    }

    public double findN(double p, double v, double t)
    {
        n = (p*v)/(r*t);
        temp = Math.round(n*100.0)/100.0;
        return temp;
    }

    public double findT(double p, double v, double n)
    {
        t = (p*v)/(n*r);
        temp = Math.round(t*100.0)/100.0;
        return temp;
    }

    @Override
    public String generateFormula(String choice)
    {
        if(choice.equals("1")){
            p = findP(v,n,t);
            unknown = String.valueOf(p);
        }
        else if(choice.equals("2")){
            v = findV(p,n,t);
            unknown = String.valueOf(v);
        }
        else if(choice.equals("3")){
            n = findN(p,v,t);
            unknown = String.valueOf(n);
        }
        else if(choice.equals("4")){
            t = findT(p,v,n);
            unknown = String.valueOf(t);
        }
        else{
            unknown = "";
        }
        return unknown;
    }

    @Override
    public void missingFormulaMenu() {
        // adds the name of the equation
        option = new GLabel("Ideal Gas",20,20);
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
