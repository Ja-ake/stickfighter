package engine;

import java.util.ArrayList;
import java.util.HashMap;

public class InputPacket {

    private final ArrayList<String> down;
    private final ArrayList<String> pressed;
    private final ArrayList<String> released;
    private final HashMap<String, Float> downValues;

    public InputPacket(ArrayList<String> down, ArrayList<String> pressed, ArrayList<String> released, HashMap<String, Float> downValues) {
        this.down = new ArrayList(down);
        this.pressed = new ArrayList(pressed);
        this.released = new ArrayList(released);
        this.downValues = new HashMap(downValues);
    }

    public float getValue(String name) {
        if (!downValues.containsKey(name)) {
            return 0;
        }
        return downValues.get(name);
    }

    public boolean isDown(String name) {
        return down.contains(name);
    }

    public boolean isPressed(String name) {
        return pressed.contains(name);
    }

    public boolean isReleased(String name) {
        return released.contains(name);
    }
}
