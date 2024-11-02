import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class Utilitarios {
    public static void setButtonGroup(String actionCommand, ButtonGroup buttonGroup ){
        Enumeration<AbstractButton> elements = buttonGroup.getElements();
        while (elements.hasMoreElements()){
            AbstractButton button = (AbstractButton)elements.nextElement();
            if(button.getActionCommand().equals(actionCommand)){
                button.setSelected(true);
            }
        }
    }
}