package hu.molnard.model;

public class TexturedModel
{
    private RawModel rawModel;
    private ModelTexture modelTexture;

    public TexturedModel(RawModel rawModel, ModelTexture mt) {this.rawModel = rawModel; this.modelTexture = mt;}

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getModelTexture() {
        return modelTexture;
    }
}
