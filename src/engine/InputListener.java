package engine;

import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import java.util.ArrayList;
import java.util.HashMap;

public class InputListener implements ActionListener, AnalogListener {

    private final ArrayList<String> down = new ArrayList();
    private final ArrayList<String> pressed = new ArrayList();
    private final ArrayList<String> released = new ArrayList();
    private final HashMap<String, Float> downValues = new HashMap();

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if (isPressed) {
            pressed.add(name);
            down.add(name);
        } else {
            released.add(name);
            down.remove(name);
        }
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        if (downValues.containsKey(name)) {
            downValues.put(name, value + downValues.get(name));
        } else {
            downValues.put(name, value);
        }
    }

    public void clear() {
        pressed.clear();
        released.clear();
        downValues.clear();
    }

    public InputPacket createInputPacket() {
        return new InputPacket(down, pressed, released, downValues);
    }
}
