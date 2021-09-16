package hu.molnard.entity;

import org.lwjgl.util.vector.Vector3f;

public class Camera
{
    private Vector3f location;
    private float FOV, NEAR_PLANE, FAR_PLANE, pitch, yaw, roll;

    public Camera(Vector3f location, float FOV, float NEAR_PLANE, float FAR_PLANE)
    {
        this.location = location;
        this.FOV = FOV;
        this.NEAR_PLANE = NEAR_PLANE;
        this.FAR_PLANE = FAR_PLANE;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getFAR_PLANE() {
        return FAR_PLANE;
    }

    public float getFOV() {
        return FOV;
    }

    public float getNEAR_PLANE() {
        return NEAR_PLANE;
    }

    public Vector3f getLocation() {
        return location;
    }

    public float getPitch() {
        return pitch;
    }

    public float getRoll() {
        return roll;
    }

    public float getYaw() {
        return yaw;
    }
}
