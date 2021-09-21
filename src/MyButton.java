/*
 * File: MyButton.java
 * ---------------------
 * This class is a custom button class for a sample calculator app implementation
 *  Author: Cobalt mkc
 *  Date modified: July 22, 2019
 *
 */


import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;


public class MyButton extends GCompound {

    private static final String FONT = "SansSerif-bold-48";
    private GRect key;
    private GLabel keyText;

    public MyButton(double x, double y, double width, double height, String text) {
        key = new GRect(x, y, width, height);
        key.setFilled(true);
        key.setColor(Color.GRAY);

        keyText = new GLabel(text);
        keyText.setColor(Color.WHITE);
        if(text.equals("Next ")){
            key.setFillColor(Color.DARK_GRAY);
            add(key);
            keyText.setFont("SansSerif-bold-38");
            add(keyText, x + 5 + getCenterX(width, keyText.getWidth()),
                    y + getCenterY(height, keyText.getAscent()));
        }

        else if(text.equals("0") || text.equals("1") || text.equals("2") || text.equals("3") || text.equals("4") || text.equals("5") || text.equals("6") || text.equals("7") || text.equals("8") || text.equals("9") || text.equals("±") || text.equals(".")){
            key.setFillColor(Color.BLACK);
            add(key);
            keyText.setFont(FONT);
            add(keyText, x + getCenterX(width, keyText.getWidth()),
                    y + getCenterY(height, keyText.getAscent()));
        }

        else if(text.equals("=")){
            Color customOrange = new Color(0xF87217);
            key.setFillColor(customOrange);
            add(key);
            keyText.setFont(FONT);
            add(keyText, x + getCenterX(width, keyText.getWidth()),
                    y + getCenterY(height, keyText.getAscent()));
        }

        else{
            key.setFillColor(Color.DARK_GRAY);
            add(key);
            keyText.setFont(FONT);
            add(keyText, x + getCenterX(width, keyText.getWidth()),
                    y + getCenterY(height, keyText.getAscent()));
        }
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