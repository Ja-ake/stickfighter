/*
 * Manages the state of the game, global information, and what renderers are
 * supposed to be drawing on the screen at any given time
 */

package jake.engine;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.BulletAppState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jake
 */
public class State extends BulletAppState {
    protected List<Renderer> renderers;
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
    }
    
    public SimpleApplication getApp() {
        return (SimpleApplication) app;
    }
    
    /**
     * 
     * @return
     * Returns a list of Renderers that are of type "type"
     */
    public List<Renderer> getRenderers(Class type) {
        List<Renderer> renderersToReturn = new ArrayList();
        for (Renderer r : renderers) {
            if (type.isInstance(r)) {
                renderersToReturn.add(r);
            }
        }
        return renderersToReturn;
    }
    /**
     * 
     * @return 
     * Returns a list of Elements of type "type"
     */
    public List<Element> getElements(Class type) {
        List<Element> elementsToReturn = new ArrayList();
        for (Renderer r : renderers) {
            for (Element e : r.getElements()) {
                if (type.isInstance(e)) {
                    elementsToReturn.add(e);
                }
            }
        }
        return elementsToReturn;
    }
    /**
     * 
     * @param element
     * @param renderer
     * @return 
     * Returns a list of Elements of type "element" 
     * that are in a renderer of type "renderer"
     */
    public List<Element> getElements(Class element, Class renderer) {
        List<Element> elementsToReturn = new ArrayList();
        for (Renderer r : renderers) {
            if (renderer.isInstance(r)) {
                for (Element e : r.getElements()) {
                    if (element.isInstance(e)) {
                        elementsToReturn.add(e);
                    }
                }
            }
        }
        return elementsToReturn;
    }
    
    public AppStateManager getStateManager() {
        return stateManager;
    }
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        for (Renderer r : renderers) {
            r.update(tpf);
        }
    }
}
