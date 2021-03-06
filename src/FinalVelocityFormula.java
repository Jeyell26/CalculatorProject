import acm.graphics.GLabel;

public class FinalVelocityFormula extends AFormula{
    private double Vf;
    private double V0;
    private double a;
    private double Xf;
    private double X0;
    private final String[] varList= {
            "Vf (m/s)","V0 (m/s)","a (m/s\u00B2)",
            "Xf (m)","X0 (m)"};
    public final int numOfMissingVar = 5;

    @Override
    public String getVariable(int index) {
        return varList[index];
    }

    @Override
    public String setVariable(String var, double value) {
        String returnValue;
        if(var.equals("Vf (m/s)")){
            setVf(value);
            returnValue = String.valueOf(getVf());
        }
        else if(var.equals("V0 (m/s)")){
            setV0(value);
            returnValue = String.valueOf(getV0());
        }
        else if(var.equals("a (m/s\u00B2)")){
            setA(value);
            returnValue = String.valueOf(getA());
        }
        else if(var.equals("Xf (m)")){
            setXf(value);
            returnValue = String.valueOf(getXf());
        }
        else{ // X0 (m)
            setX0(value);
            returnValue = String.valueOf(getX0());
        }
        return returnValue;
    }

    @Override
    public String generateFormula(String choice) {
        if(choice.equals("1")){
            Vf = findVF(V0,a,Xf,X0);
            unknown = String.valueOf(Vf);
        }
        else if(choice.equals("2")){
            V0 = findV0(Vf,a,Xf,X0);
            unknown = String.valueOf(V0);
        }
        else if(choice.equals("3")){
            a = findA(Vf,V0,Xf,X0);
            unknown = String.valueOf(a);
        }
        else if(choice.equals("4")){
            Xf = findXF(Vf,V0,a,X0);
            unknown = String.valueOf(Xf);
        }
        else if(choice.equals("5")){
            X0 = findX0(Vf,V0,a,Xf);
            unknown = String.valueOf(X0);
        }
        else{
            unknown = "";
        }
        return unknown;
    }

    @Override
    public void missingFormulaMenu() {
        // adds the name of the equation
        option = new GLabel("Final Velocity Formula",20,20);
        option.setFont(FONT);
        add(option);

        for (int i = 0; i < varList.length; i++) {
            if(i<2) {
                option = new GLabel("[" + (i+1) + "] " + varList[i],20,45+30*i);
            }
            else{
                option = new GLabel("[" + (i+1) + "] " + varList[i],190,-15+30*i);
            }
            option.setFont(FONT);
            add(option);
        }
    }

    public double findVF(double userV0,double userA,double userXf,double userX0){
        Vf = Math.sqrt((userV0*userV0) + (2*userA*(userXf - userX0)));
        temp = Math.round(Vf*100.0)/100.0;
        return temp;
    }

    public double findV0(double userVf,double userA,double userXf,double userX0){
        V0 = Math.sqrt((userVf*userVf) - (2*userA*(userXf - userX0)));
        temp = Math.round(V0*100.0)/100.0;
        return temp;
    }

    public double findA(double userVf,double userV0,double userXf,double userX0){
        a = ((userVf*userVf)-(userV0*userV0))/(2*(userXf-userX0));
        temp = Math.round(a*100.0)/100.0;
        return temp;
    }

    public double findXF(double userVf,double userV0,double userA,double userX0){
        Xf = ((userVf*userVf)-(userV0*userV0)+(2*userA*userX0))/(2*userA);
        temp = Math.round(Xf*100.0)/100.0;
        return temp;
    }

    public double findX0(double userVf,double userV0,double userA,double userXf){
        X0 = ((2*userA*userXf)-(userVf*userVf)+(userV0*userV0))/(2*userA);
        temp = Math.round(X0*100.0)/100.0;
        return temp;
    }

    public FinalVelocityFormula(){

    }

    public double getVf() {
        return Vf;
    }

    public void setVf(double vf) {
        Vf = vf;
    }

    public double getV0() {
        return V0;
    }

    public void setV0(double v0) {
        V0 = v0;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
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
}


