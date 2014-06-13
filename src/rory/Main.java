package rory;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends SimpleApplication {
    
    public static void main(String[] args) {
        Logger.getLogger("").setLevel(Level.WARNING);
        Main app = new Main();
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        stateManager.attach(new GameAppState());
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        
    }
    
    @Override
    public void simpleRender(RenderManager rm) {
        
    }
}