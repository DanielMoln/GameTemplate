package hu.molnard.model;

public class RawModel
{
    private int id, vertexCount;

    public RawModel(int id, int vc) { this.id = id; this.vertexCount = vc; }

    public int getId() {
        return id;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
