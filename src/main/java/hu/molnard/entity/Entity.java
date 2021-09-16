package hu.molnard.entity;

import hu.molnard.model.TexturedModel;
import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.Vector3f;

public class Entity
{
    private Vector3f position;
    private TexturedModel texturedModel;

    private float rotX;
    private float rotY;
    private float rotZ;
    private float scale;

    public Entity() {}

    public Entity(Vector3f position, TexturedModel texturedModel, Vector3f rotation, float scale) {
        this.position = position;
        this.texturedModel = texturedModel;
        this.rotX = rotation.x;
        this.rotY = rotation.y;
        this.rotZ = rotation.z;
        this.scale = scale;
    }

    public void increasePosition(float dX, float dY, float dZ)
    {
        this.position.x += dX;
        this.position.y += dY;
        this.position.z += dZ;
    }

    public void increaseRotation(float dX, float dY, float dZ)
    {
        this.rotX += dX;
        this.rotY += dY;
        this.rotZ += dZ;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public TexturedModel getTexturedModel() {
        return texturedModel;
    }

    public void setTexturedModel(TexturedModel texturedModel) {
        this.texturedModel = texturedModel;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
