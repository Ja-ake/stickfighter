package engine;

import com.jme3.input.controls.ActionListener;
import java.util.ArrayList;

public class InputListener implements ActionListener {

    private final ArrayList<String> down = new ArrayList();
    private final ArrayList<String> pressed = new ArrayList();
    private final ArrayList<String> released = new ArrayList();

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

    public void clear() {
        pressed.clear();
        released.clear();
    }

    public InputPacket createInputPacket() {
        return new InputPacket(down, pressed, released);
    }
}
