/*
 * God class, controls update loop and window initialization.
 */
package engine;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
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
        inputListener.clear();
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }

    public InputListener getInputListener() {
        return inputListener;
    }

    private void initKeys() {
        HashMap<String, Integer> keyMap = new HashMap();

        keyMap.put("Move Forward", KeyInput.KEY_W);
        keyMap.put("Move Left", KeyInput.KEY_A);
        keyMap.put("Move Back", KeyInput.KEY_S);
        keyMap.put("Move Right", KeyInput.KEY_D);

        for (String s : keyMap.keySet()) {
            inputManager.addMapping(s, new KeyTrigger(keyMap.get(s)));
        }

        inputListener = new InputListener();
        inputManager.addListener(inputListener, keyMap.keySet().toArray(new String[0]));
    }
}
