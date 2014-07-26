/*
 * Useful math stuff.
 */
package engine.util;

import com.jme3.math.Vector3f;
import static java.lang.Math.*;

public abstract class MathEx {

    public static Vector3f sphericalToRectangular(SphericalCoords sph) {
        double y = sph.r * cos(sph.p);
        double x = sph.r * sin(sph.p) * cos(sph.t);
        double z = sph.r * sin(sph.p) * sin(sph.t);
        return new Vector3f((float) x, (float) y, (float) z);
    }

    public static SphericalCoords rectangularToSpherical(Vector3f rect) {
        double r = rect.length();
        double t = atan2(rect.y, rect.x);
        double p = acos(rect.z / rect.length());
        return new SphericalCoords((float) r, (float) t, (float) p);
    }
}
