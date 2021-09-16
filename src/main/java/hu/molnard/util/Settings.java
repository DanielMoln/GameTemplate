package hu.molnard.util;

import hu.molnard.entity.Camera;
import org.lwjgl.util.vector.Vector3f;

public class Settings
{
    private Camera camera = new Camera(new Vector3f(0,10,0), 70, 0.01f, 1000);

    private Vector3f SkyColor = new Vector3f(255f, 0.255f, 0.255f);
    private Vector3f lightColor = new Vector3f(1f, 1f, 1f);
    private Vector3f lightPosition = new Vector3f(0, 200, 50);

    public Settings() {}

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setSkyColor(Vector3f skyColor) {
        SkyColor = skyColor;
    }

    public void setLightColor(Vector3f lightColor) {
        this.lightColor = lightColor;
    }

    public void setLightPosition(Vector3f lightPosition) {
        this.lightPosition = lightPosition;
    }

    public Camera getCamera() {
        return camera;
    }

    public Vector3f getLightColor() {
        return lightColor;
    }

    public Vector3f getLightPosition() {
        return lightPosition;
    }

    public Vector3f getSkyColor() {
        return SkyColor;
    }
}
