import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class MyButtonFormula extends GCompound {

    private static final String FONT = "SansSerif-bold-48";
    private GRect key;
    private GLabel keyText;

    public MyButtonFormula(double x, double y, double width, double height, String text) {
        key = new GRect(x, y, width, height);
        keyText = new GLabel(text);
        keyText.setFont(FONT);

        add(key);
        add(keyText, x + getCenterX(width, keyText.getWidth()),
                y + getCenterY(height, keyText.getAscent()));
    }

    public String getText() {
        return keyText.getLabel();
    }

    private double getCenterX(double width, double labelWidth) {
        return (width - labelWidth) / 2.0f;
    }

    private double getCenterY(double height, double ascent) {
        return (ascent + (height - ascent) / 2.0f);
    }

    public void setText(String text) {
        keyText.setLabel(text);
    }
}
