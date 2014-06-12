/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import com.jme3.math.Vector3f;
import static java.lang.Math.*;

/**
 *
 * @author Jake
 */
public abstract class MathEx {
    public static Vector3f sphericalToRectangular(Vector3f spherical) {
        double y = sin(spherical.y) * spherical.x;
        double x = cos(spherical.z) * y;
        double z = sin(spherical.z) * y;
        return new Vector3f((float)x, (float)y, (float)z);
    }
    
    public static Vector3f rectangularToSpherical(Vector3f rectangular) {
        double r = rectangular.length();
        rectangular = rectangular.normalize();
        double theta = acos(rectangular.z);
        double phi = atan(rectangular.y / rectangular.x);
        return new Vector3f((float)r, (float)theta, (float)phi);
    }
}
