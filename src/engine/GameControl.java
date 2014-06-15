/*
 * God class, controls update loop and window initialization.
 */
package engine;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import engine.states.MenuAppState;
import engine.states.RoomAppState;

/**
 *
 * @author Jake
 */
public class GameControl extends SimpleApplication {

    public static void main(String[] args) {
        GameControl app = new GameControl();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        getStateManager().attach(new MenuAppState());
        getStateManager().attach(new RoomAppState());

        getStateManager().getState(MenuAppState.class).setEnabled(false);
        getStateManager().getState(RoomAppState.class).setEnabled(true);
    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
}
