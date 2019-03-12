package shortpath1.pathfinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Graph 
{
    private List<Vertex> vertexes = new ArrayList<Vertex>();

    public Graph() {}

    public Graph(Collection<Vertex> vertexes) 
    {
        this.vertexes.addAll(vertexes);
    }

    public List<Vertex> getVertexes() 
    {
        return vertexes;
    }

    public void addVertex(Vertex vertex)
    {
        vertexes.add(vertex);
    }
    
}