import acm.graphics.*;
import acm.graphics.GCompound;

public abstract class AFormula extends GCompound {
    protected String unknown;
    protected String FONT = "SansSerif-bold-24";
    protected GLabel option;

    public abstract String getVariable(int index);

    public abstract String setVariable(String var, double value);

    public abstract String generateFormula(String choice);

    public abstract void missingFormulaMenu();
}
