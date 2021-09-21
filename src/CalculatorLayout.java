/*
 * File: CalculatorLayout.java
 * ---------------------
 *  This class is the layout class for a sample calculator app implementation
 *  Author: Cobalt mkc
 *  Date modified: July 22, 2019
 *
 */

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

import java.awt.*;

public class CalculatorLayout extends GCompound {

    private final int WIDTH = 400;
    private final int HEIGHT = 825;
    private static final int NROWS = 7;     /* Number of rows    */
    private static final int NCOLS = 4;     /* Number of columns */
    private static final int MARGIN = 20;
    private static final char[] labels = {
            ' ', ' ', ' ', ' ',
            ' ', ' ', ' ', ' ',
            ' ', 'C', '⌫', '÷',
            '7', '8', '9', 'x',
            '4', '5', '6', '-',
            '1', '2', '3', '+',
            '±', '.', '0', '='};

    private GLabel memoDisplay;
    private GLabel mainDisplay;
    private StringBuilder memoBuffer;
    private StringBuilder numBuffer;
    private static final String MAIN_FONT = "SansSerif-bold-48";
    private static final String MEMO_FONT = "SansSerif-bold-28";


    public CalculatorLayout(double height) {
        double sqSize = height / (NROWS + 1);
        numBuffer = new StringBuilder();
        clearNumBuffer();
        mainDisplay = new GLabel(numBuffer.toString(), MARGIN, MARGIN + 125);
        mainDisplay.setFont(MAIN_FONT);

        memoBuffer = new StringBuilder();
        memoDisplay = new GLabel("", MARGIN, 2 * MARGIN);
        memoDisplay.setFont(MEMO_FONT);

        // Background
        GRect bg = new GRect(WIDTH,HEIGHT);
        bg.setFilled(true);
        bg.setFillColor(Color.gray);
        add(bg);

        add(mainDisplay);
        add(memoDisplay);

        int count = 0;
        for (int i = 1; i <= NROWS; i++) {
            for (int j = 0; j < NCOLS; j++) {
                double x = MARGIN + j * sqSize;
                double y = i * sqSize - MARGIN + 90;
                MyButton myButton;
                if (count == 8) {
                    myButton = new MyButton(x, y, sqSize, sqSize, "CE" + labels[count++]);
                }
                else if(count == 0){
                    myButton = new MyButton(x, y, sqSize, sqSize, "E1" + labels[count++]);
                }
                else if(count == 1){
                    myButton = new MyButton(x, y, sqSize, sqSize, "E2" + labels[count++]);
                }
                else if(count == 2){
                    myButton = new MyButton(x, y, sqSize, sqSize, "E3" + labels[count++]);
                }
                else if(count == 3){
                    myButton = new MyButton(x, y, sqSize, sqSize, "E4" + labels[count++]);
                }
                else if(count == 4){
                    myButton = new MyButton(x, y, sqSize, sqSize, "E5" + labels[count++]);
                }
                else if(count == 5){
                    myButton = new MyButton(x, y, sqSize, sqSize, "E6" + labels[count++]);
                }
                else if(count == 6){
                    myButton = new MyButton(x, y, sqSize, sqSize, "Next" + labels[count++]);
                }
                else{
                    myButton = new MyButton(x, y, sqSize, sqSize, "" + labels[count++]);
                }
                add(myButton);
            }
        }
    }

    /* Sample Polymorphic Methods */
    public void setMemoDisplay(char symbol) {
        memoBuffer.append(symbol);
        memoDisplay.setLabel(memoBuffer.toString());
    }

    public void setMemoDisplay(String input) {
        memoBuffer.append(input);
        memoDisplay.setLabel(memoBuffer.toString());
    }

    /* Sample Polymorphic Methods */
    // Appends/Creates a Main Display
    public void setMainDisplay(char symbol) {
        if (numBuffer.length() > 0 && numBuffer.charAt(0) == '0') { // Do not append on initial zero
            numBuffer.setCharAt(0, symbol);
        } else {
            numBuffer.append(symbol);
        }
        mainDisplay.setLabel(numBuffer.toString());
    }

    public void setMainDisplay(String input) {
        mainDisplay.setLabel(input);
    }


    public String getMainDisplay() {
        return numBuffer.toString();
    }

    public void clearMainDisplay() {
        clearNumBuffer();
        mainDisplay.setLabel("0");
    }

    public void clearMemoDisplay() {
        clearMemoBuffer();
        memoDisplay.setLabel("");
    }

    // Basically the main display
    public void clearNumBuffer() {
        numBuffer.setLength(1);
        numBuffer.setCharAt(0, '0');
    }

    // Basically the memory display
    public void clearMemoBuffer() {
        memoBuffer.setLength(0);
    }

    // Gets rid of a character at memory
    public void clearMemoElement(char operation) {
        int position = memoBuffer.lastIndexOf("" + operation);
        memoBuffer.setLength(position + 1);
        memoDisplay.setLabel(memoBuffer.toString());
    }

    // NegatesElement??? Reformatted so it only edits main
    public void negateElement() {
        if (numBuffer.charAt(0) != '-') {
            numBuffer.insert(0, '-');
        } else {
            numBuffer.deleteCharAt(0);
        }
        mainDisplay.setLabel(numBuffer.toString());
    }

    // Deletes one character at main and menu
    // !Not Useful!
    public void deleteOneCharacter() {
        if (memoBuffer.length() == 0 || numBuffer.length() == 0) {
            return;
        }
        memoBuffer.setLength(memoBuffer.length() - 1);
        numBuffer.setLength(numBuffer.length() - 1);
        memoDisplay.setLabel(memoBuffer.toString());
        mainDisplay.setLabel(numBuffer.toString());
    }

    public void delOneMemo(){
        if (memoBuffer.length() == 0) {
            return;
        }
        memoBuffer.setLength(memoBuffer.length() - 1);
        memoDisplay.setLabel(memoBuffer.toString());
    }

    public void delOneMain(){
        if (numBuffer.length() == 0){
            return;
        }
        numBuffer.setLength(numBuffer.length() - 1);
        mainDisplay.setLabel(numBuffer.toString());
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}