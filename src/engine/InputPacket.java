package engine;

import java.util.ArrayList;

public class InputPacket {

    private final ArrayList<String> down;
    private final ArrayList<String> pressed;
    private final ArrayList<String> released;

    public InputPacket(ArrayList<String> down, ArrayList<String> pressed, ArrayList<String> released) {
        this.down = new ArrayList(down);
        this.pressed = new ArrayList(pressed);
        this.released = new ArrayList(released);
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
