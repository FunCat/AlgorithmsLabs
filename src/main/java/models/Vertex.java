package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Vertex {

    private String label;
    private List<Edge> edges;
    private boolean isVisited;


    public Vertex(String label) {
        this.label = label;
        this.edges = new ArrayList<>();
        this.isVisited = false;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public boolean addEdge(Vertex newVertex, int weight){
        if(hasEdge(newVertex)){
            return false;
        }
        return this.edges.add(new Edge(this, newVertex, weight));
    }

    public int getEdgesCount(){
        return edges.size();
    }

    public boolean hasEdge(Vertex vertex) {
        return findEdge(vertex).isPresent();
    }

    public boolean removeEdge(Vertex vertex){
        Optional<Edge> edge = findEdge(vertex);
        return edge.map(edge1 -> edges.remove(edge1)).orElse(false);
    }

    private Optional<Edge> findEdge(Vertex vertex){
        return edges.stream().filter(edge -> edge.isBetween(this, vertex)).findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (isVisited != vertex.isVisited) return false;
        if (label != null ? !label.equals(vertex.label) : vertex.label != null) return false;
        return edges != null ? edges.equals(vertex.edges) : vertex.edges == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (edges != null ? edges.hashCode() : 0);
        result = 31 * result + (isVisited ? 1 : 0);
        return result;
    }
}
