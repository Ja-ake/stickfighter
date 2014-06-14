package jake.engine;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import jake.engine.exceptions.InvalidStateException;
import jake.engine.states.LoadingState;
import jake.engine.states.MenuState;
import jake.engine.states.RoomState;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jake
 */
public class GameControl extends SimpleApplication {
    private Map<String, State> states;
    private State state;
    
    public GameControl() {
        states = new HashMap();
        states.put("Room", new RoomState());
        states.put("Menu", new MenuState());
        states.put("Loading", new LoadingState());
    }
    
    public static void main(String[] args) {
        GameControl app = new GameControl();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        try {
        setCurrentState("Room");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        
    }
    
    @Override
    public void simpleRender(RenderManager rm) {
        
    }
    
    public void setCurrentState(String sn) throws InvalidStateException {
        State st = states.get(sn);
        if (st != null) {
            state = st;
        } else {
            throw new InvalidStateException();
        }
    }
}
