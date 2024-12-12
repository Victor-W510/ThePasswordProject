import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Main {
    static JPanel topp = new JPanel();
    static JPanel bottom = new JPanel();
    static JFrame jFrame = new JFrame();
    static String theText = "";
    static JCheckBox jCheckBoxLenght, jCheckBoxUppercase, jCheckBoxNumber, jCheckBoxSymbols;

    public static void main(String[] args) {

        //Create a JFrame

        jFrame.setSize(300,300);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLayout(new GridLayout(2,1));
        upper();
        downer();
        jFrame.add(topp);
        jFrame.add(bottom);

        jFrame.setVisible(true);

        //mouse over textfield will produce a text
        //password.setToolTipText("hello");

    }
    private static void upper(){

        // Code for the upper layer of the window
        // With the four criteria for a good password
        // I did add them to a arrayList to get a cleaner code

        topp.setLayout(new GridLayout(4,1));

        jCheckBoxLenght = new JCheckBox("least 12 characters");
        jCheckBoxUppercase = new JCheckBox("Uppercase letter");
        jCheckBoxNumber = new JCheckBox("Numbers");
        jCheckBoxSymbols = new JCheckBox("Symbols");

        ArrayList <JCheckBox> list = new ArrayList<>();
        list.add(jCheckBoxLenght);
        list.add(jCheckBoxUppercase);
        list.add(jCheckBoxNumber);
        list.add(jCheckBoxSymbols);

        for (JCheckBox checkBox:list){
            checkBox.setFont(new Font("Courier", Font.ITALIC,15));
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panel.add(checkBox);
            topp.add(panel);
        }

    }
    private static void downer(){

        // Now here came the slightly longer and slightly messier part
        // I used a KeyListener to keep track of what the input is

        JLabel jl = new JLabel("Password: ");
        JTextField passwordField = new JTextField(theText,15);

        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e)  {

                if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE && !theText.isEmpty()) {
                    theText = theText.substring(0,theText.length()-1);
                    if (jCheckBoxLenght.isSelected() && theText.length() <= 12) jCheckBoxLenght.doClick();
                    if (!theText.matches(".*[A-Z].*") && jCheckBoxUppercase.isSelected()) jCheckBoxUppercase.doClick();
                    if (!theText.matches(".*\\d.*") && jCheckBoxNumber.isSelected()) jCheckBoxNumber.doClick();
                    if (!theText.matches(".*\\W") && jCheckBoxSymbols.isSelected()) jCheckBoxSymbols.doClick();

                    if (passwordField.getText().isEmpty()){
                        jCheckBoxLenght.setSelected(false);
                        jCheckBoxSymbols.setSelected(false);
                        jCheckBoxNumber.setSelected(false);
                        jCheckBoxUppercase.setSelected(false);
                    }


                }
                else if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE){
                    theText += e.getKeyChar();
                    if (theText.length() >= 12 && !jCheckBoxLenght.isSelected()) jCheckBoxLenght.doClick();
                    if (theText.matches(".*[A-Z].*") && !jCheckBoxUppercase.isSelected()) jCheckBoxUppercase.doClick();
                    if (theText.matches(".*[0-9].*") && !jCheckBoxNumber.isSelected()) jCheckBoxNumber.doClick();
                    if (theText.matches(".*\\W") && !jCheckBoxSymbols.isSelected()) jCheckBoxSymbols.doClick();

                }


            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        bottom.add(jl);
        bottom.add(passwordField);
    }
}
