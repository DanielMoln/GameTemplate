package hu.molnard.model;

public class ModelTexture
{
    private int id;
    private boolean hasTransparency, useFakeLighting;

    public ModelTexture(int id) { this.id = id; }

    public void setHasTransparency(boolean hasTransparency) {
        this.hasTransparency = hasTransparency;
    }

    public void setUseFakeLighting(boolean useFakeLighting) {
        this.useFakeLighting = useFakeLighting;
    }

    public int getId() {
        return id;
    }

    public boolean isHasTransparency() {
        return hasTransparency;
    }
}
