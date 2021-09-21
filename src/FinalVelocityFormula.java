import acm.graphics.GLabel;

public class FinalVelocityFormula extends AFormula{
    private double Vf;
    private double V0;
    private double a;
    private double Xf;
    private double X0;
    private final String[] varList= {
            "Vf","V0","a",
            "Xf","X0"};
    public final int numOfMissingVar = 5;

    @Override
    public String getVariable(int index) {
        return varList[index];
    }

    @Override
    public String setVariable(String var, double value) {
        String returnValue;
        if(var.equals("Vf")){
            setVf(value);
            returnValue = String.valueOf(getVf());
        }
        else if(var.equals("V0")){
            setV0(value);
            returnValue = String.valueOf(getV0());
        }
        else if(var.equals("a")){
            setA(value);
            returnValue = String.valueOf(getA());
        }
        else if(var.equals("Xf")){
            setXf(value);
            returnValue = String.valueOf(getXf());
        }
        else{
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
        for (int i = 0; i < 5; i++) {
            if(i<3) {
                option = new GLabel("[" + (i+1) + "] " + varList[i],20,20+30*i);
            }
            else{
                option = new GLabel("[" + (i+1) + "] " + varList[i],130,-70+30*i);
            }
            option.setFont(FONT);
            add(option);
        }
    }

    public double findVF(double userV0,double userA,double userXf,double userX0){
        Vf = Math.sqrt((userV0*userV0) + (2*userA*(userXf - userX0)));
        return Vf;
    }

    public double findV0(double userVf,double userA,double userXf,double userX0){
        V0 = Math.sqrt((userVf*userVf) - (2*userA*(userXf - userX0)));
        return V0;
    }

    public double findA(double userVf,double userV0,double userXf,double userX0){
        a = ((userVf*userVf)-(userV0*userV0))/(2*(userXf-userX0));
        return a;
    }

    public double findXF(double userVf,double userV0,double userA,double userX0){
        Xf = ((userVf*userVf)-(userV0*userV0)+(2*userA*userX0))/(2*userA);
        return Xf;
    }

    public double findX0(double userVf,double userV0,double userA,double userXf){
        X0 = ((2*userA*userXf)-(userVf*userVf)+(userV0*userV0))/(2*userA);
        return X0;
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


