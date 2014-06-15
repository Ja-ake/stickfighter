/*
 * God class, controls update loop and window initialization.
 */

package jake.engine;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import jake.engine.states.MenuState;
import jake.engine.states.RoomState;

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
        getStateManager().attach(new MenuState());
        getStateManager().attach(new RoomState());
        
        getStateManager().getState(MenuState.class).setEnabled(false);
        getStateManager().getState(RoomState.class).setEnabled(true);
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        
    }
    
    @Override
    public void simpleRender(RenderManager rm) {
        
    }
}
