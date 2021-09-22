import acm.graphics.GLabel;

public class ConstantAngularAcceleration extends AFormula{
    private double aa;  // Angular acceleration
    private double t;   // Time
    private double fav; // Final Angular Velocity
    private double iav; // Initial Angular Velocity
    private final String[] varList= {
            "α (rad/s\u00B2)","t (s)","Final ω (rad/s)",
            "Initial ω (rad/s)"};
    public final int numOfMissingVar = 4;

    // Getters and Setters
    public double getAA() {
        return aa;
    }
    public void setAA(double aa) { this.aa = aa; }

    public double getT() {
        return t;
    }
    public void setT(double t) {
        this.t = t;
    }

    public double getFAV() {
        return fav;
    }
    public void setFAV(double fav) {
        this.fav = fav;
    }

    public double getIAV() {
        return iav;
    }
    public void setIAV(double iav) {
        this.iav = iav;
    }

    @Override
    public String getVariable(int index) {
        return varList[index];
    }

    @Override
    public String setVariable(String var, double value) {
        String returnValue;
        if(var.equals("α (rad/s\u00B2)")){
            setAA(value);
            returnValue = String.valueOf(getAA());
        }
        else if(var.equals("t (s)")){
            setT(value);
            returnValue = String.valueOf(getT());
        }
        else if(var.equals("Final ω (rad/s)")){
            setFAV(value);
            returnValue = String.valueOf(getFAV());
        }
        else{ // for when var.equals("Initial ω (rad/s)")
            setIAV(value);
            returnValue = String.valueOf(getIAV());
        }
        return returnValue;
    }

    @Override
    public String generateFormula(String choice) {
        if(choice.equals("1")){
            aa = findAA(fav,iav,t);
            unknown = String.valueOf(aa);
        }
        else if(choice.equals("2")){
            t = findT(fav,iav,aa);
            unknown = String.valueOf(t);
        }
        else if(choice.equals("3")){
            fav = findFAV(iav,aa,t);
            unknown = String.valueOf(fav);
        }
        else if(choice.equals("4")){
            iav = findIAV(fav,aa,t);
            unknown = String.valueOf(iav);
        }
        else{
            unknown = "";
        }
        return unknown;
    }

    // Shows the formula menu
    @Override
    public void missingFormulaMenu() {
        // adds the name of the equation
        option = new GLabel("Constant Angular Acceleration",20,20);
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

    // For Calculating a variable - two decimal places
    public double findAA(double userFAV, double userIAV, double userT)
    {
        aa = ((userFAV - userIAV)/userT);
        temp = Math.round(aa*100.0)/100.0;
        return temp;
    }

    public double findT(double userFAV, double userIAV, double userAA)
    {
        t = ((userFAV - userIAV)/userAA);
        temp = Math.round(t*100.0)/100.0;
        return temp;
    }

    public double findFAV(double userIAV, double userAA, double userT)
    {
        fav = userIAV + (userAA*userT);
        temp = Math.round(fav*100.0)/100.0;
        return temp;
    }

    public double findIAV(double userFAV, double userAA, double userT)
    {
        iav = userFAV + (userAA*userT);
        temp = Math.round(iav*100.0)/100.0;
        return temp;
    }
}
