/*
 * Draws and manages all of the elements that the 
 * specific renderer is supposed to handle.
 */
package engine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jake
 */
public abstract class Renderer {

    protected List<Element> elements;

    public Renderer() {
        elements = new ArrayList();
    }

    public void update(float tpf) {
        for (Element e : elements) {
            e.update(tpf);
        }
    }

    public List<Element> getElements() {
        return elements;
    }
}
