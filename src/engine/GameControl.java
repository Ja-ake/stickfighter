/*
 * God class, controls update loop and window initialization.
 */
package engine;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.renderer.RenderManager;
import engine.states.MenuAppState;
import engine.states.RoomAppState;
import java.util.HashMap;

/**
 *
 * @author Jake
 */
public class GameControl extends SimpleApplication {

    private InputListener inputListener;
    private InputPacket inputPacket;

    public static void main(String[] args) {
        GameControl app = new GameControl();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setEnabled(false);

        initKeys();

        getStateManager().attach(new MenuAppState());
        getStateManager().attach(new RoomAppState());

        getStateManager().getState(MenuAppState.class).setEnabled(false);
        getStateManager().getState(RoomAppState.class).setEnabled(true);
    }

    @Override
    public void simpleUpdate(float tpf) {
        inputManager.setCursorVisible(false);
        inputPacket = inputListener.createInputPacket();
        inputListener.clear();
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }

    public InputPacket getInputPacket() {
        return inputPacket;
    }

    private void initKeys() {
        HashMap<String, Trigger> map = new HashMap();

        map.put("Move Forward", new KeyTrigger(KeyInput.KEY_W));
        map.put("Move Left", new KeyTrigger(KeyInput.KEY_A));
        map.put("Move Back", new KeyTrigger(KeyInput.KEY_S));
        map.put("Move Right", new KeyTrigger(KeyInput.KEY_D));

        map.put("Look Up", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        map.put("Look Down", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        map.put("Look Left", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        map.put("Look Right", new MouseAxisTrigger(MouseInput.AXIS_X, false));

        for (String s : map.keySet()) {
            inputManager.addMapping(s, map.get(s));
        }

        inputListener = new InputListener();
        inputManager.addListener(inputListener, map.keySet().toArray(new String[0]));

        inputPacket = inputListener.createInputPacket();
    }
}
