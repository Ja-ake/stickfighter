/*
 * SphericalCoords (should be SphericalCoordinates or SphericalVector)
 */
package engine.util;

public class SphericalCoords {

    public final float r, t, p;

    public SphericalCoords(float r, float t, float p) {
        this.r = r;
        this.t = t;
        this.p = p;
    }

    public SphericalCoords addP(float f) {
        return new SphericalCoords(r, t, p + f);
    }

    public SphericalCoords addT(float f) {
        return new SphericalCoords(r, t + f, p);
    }

    public SphericalCoords setP(float f) {
        return new SphericalCoords(r, t, f);
    }

    public SphericalCoords setT(float f) {
        return new SphericalCoords(r, f, p);
    }
}
