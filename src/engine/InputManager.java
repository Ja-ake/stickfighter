package engine;

import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import java.util.ArrayList;

public class InputManager implements ActionListener, AnalogListener {

    private final ArrayList<String> pressed = new ArrayList();
    private final ArrayList<String> released = new ArrayList();
    private final ArrayList<String> down = new ArrayList();
    
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (isPressed) {
            pressed.add(name);
        } else {
            pressed.remove(name);
        }
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        down.add(name);
    }
    
    
    
}
