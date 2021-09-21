import acm.graphics.GLabel;

public class FinalPositionFormula extends AFormula{
    private double Xf;
    private double X0;
    private double V0;
    private double t;
    private double a;
    private final String[] varList= {
            "Xf (m)","X0 (m)","V0 (m/s)",
            "t (s)","a (m/s\u00B2)"};
    public final int numOfMissingVar = 5;

    @Override
    public String generateFormula(String choice) {
        if(choice.equals("1")){
            Xf = findXF(X0,V0,t,a);
            unknown = String.valueOf(Xf);
        }
        else if(choice.equals("2")){
            X0 = findX0(Xf,V0,t,a);
            unknown = String.valueOf(X0);
        }
        else if(choice.equals("3")){
            V0 = findV0(Xf,X0,t,a);
            unknown = String.valueOf(V0);
        }
        else if(choice.equals("4")){
            t = findT(Xf,X0,V0,a);
            unknown = String.valueOf(t);
        }
        else if(choice.equals("5")){
            a = findA(Xf,X0,V0,t);
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
        option = new GLabel("Final Position Formula",20,20);
        option.setFont(FONT);
        add(option);

        for (int i = 0; i < varList.length; i++) {
            if(i<2) {
                option = new GLabel("[" + (i+1) + "] " + varList[i],20,45+30*i);
            }
            else{
                option = new GLabel("[" + (i+1) + "] " + varList[i],170,-15+30*i);
            }
            option.setFont(FONT);
            add(option);
        }
    }

    public double findXF(double userX0,double userV0,double userT,double userA){
        Xf = userX0 + userV0*userT + ((userA*userT*userT)/2);
        return Xf;
    }

    public double findX0(double userXf,double userV0,double userT,double userA){
        X0 = userXf - userV0*userT - ((userA*userT*userT)/2);
        return X0;
    }

    public double findV0(double userXf,double userX0,double userT,double userA){
        V0 = (userXf - userX0 - ((userA*userT*userT)/2))/userT;
        return V0;
    }

    public double findT(double userXf,double userX0,double userV0,double userA){
        double discriminant = (userV0*userV0)-(2*(userA)*(userX0-userXf));
        t = ((-userV0)+Math.sqrt(discriminant))/userA;
        if(t>0){
            return t;
        }
        else{
            t = ((-userV0)-Math.sqrt(discriminant))/userA;
            return t;
        }
    }

    public double findA(double userXf,double userX0,double userV0,double userT){
        a = (2*(userXf - userX0 - (userV0*userT)))/(userT*userT);
        return a;
    }

    @Override
    public String getVariable(int index){
        return varList[index];
    }

    @Override
    public String setVariable(String var, double value){
        String returnValue;
        if(var.equals("Xf (m)")){
            setXf(value);
            returnValue = String.valueOf(getXf());
        }
        else if(var.equals("X0 (m)")){
            setX0(value);
            returnValue = String.valueOf(getX0());
        }
        else if(var.equals("V0 (m/s)")){
            setV0(value);
            returnValue = String.valueOf(getV0());
        }
        else if(var.equals("t (s)")){
            setT(value);
            returnValue = String.valueOf(getT());
        }
        else{ // a (m/s²)
            setA(value);
            returnValue = String.valueOf(getA());
        }
        return returnValue;
    }

    public double getXf() {
        return Xf;
    }

    public void setXf(double xf) {
        Xf = xf;
    }

    public double getX0() {
        return X0;
    }

    public void setX0(double x0) {
        X0 = x0;
    }

    public double getV0() {
        return V0;
    }

    public void setV0(double v0) {
        V0 = v0;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }
}

