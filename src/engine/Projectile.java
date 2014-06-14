/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import com.jme3.math.Vector3f;

/**
 *
 * @author Jake
 */
public class Projectile extends Entity {
    protected boolean affectedByGravity;
    
    public Projectile() {
        super();
        affectedByGravity = false;
    }
    
    public void update(double tpf) {
        if (affectedByGravity) velocity = velocity.add((new Vector3f(gravity)).mult((float)tpf));
        position = position.add((new Vector3f(velocity)).mult((float)tpf));
    }
    
    public void setAffectedByGravity(boolean affected) {
        affectedByGravity = affected;
    }
}
