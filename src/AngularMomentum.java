import acm.graphics.GLabel;

public class AngularMomentum extends AFormula{
    private double l;  // Angular Momentum
    private double m;   // Mass
    private double v; // Velocity
    private double d; // Distance
    private double a; // Angle
    private final String[] varList= {
            "L (Kg m\u00B2/s\u00B2)","m (Kg)","v (m/s)",
            "d (m)", "angle (\u00B0)"};
    public final int numOfMissingVar = 5;

    @Override
    public String getVariable(int index) {
        return null;
    }

    @Override
    public String setVariable(String var, double value) {
        return null;
    }

    @Override
    public String generateFormula(String choice) {
        return null;
    }

    @Override
    public void missingFormulaMenu() {
        for (int i = 0; i < varList.length; i++) {
            if(i<3) {
                option = new GLabel("[" + (i+1) + "] " + varList[i],20,20+30*i);
            }
            else{
                option = new GLabel("[" + (i+1) + "] " + varList[i],220,-70+30*i);
            }
            option.setFont(FONT);
            add(option);
        }
    }
}
