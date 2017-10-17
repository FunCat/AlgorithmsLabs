package models;

import interfaces.IGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph implements IGraph {

    private Map<String, Vertex> graphMap;

    public Graph() {
        graphMap = new HashMap<>();
    }

    @Override
    public boolean addVertex(String label) {
        if(graphMap.containsKey(label)) {
            return false;
        }
        graphMap.put(label, new Vertex(label));
        return true;
    }

    @Override
    public Vertex getVertex(String label) {
        if(graphMap.containsKey(label)){
            return graphMap.get(label);
        }
        return null;
    }

    @Override
    public boolean hasVertex(String label) {
        return graphMap.containsKey(label);
    }

    @Override
    public boolean removeVertex(String label) {
        if(!graphMap.containsKey(label)) {
            return false;
        }
        Vertex removed = getVertex(label);
        graphMap.values().forEach(vertex -> vertex.removeEdge(removed));

        graphMap.remove(label);
        return true;
    }

    @Override
    public boolean addEdge(String from, String to) {
        if(hasEdge(from, to)){
            return false;
        }
        Vertex v1 = getVertex(from);
        Vertex v2 = getVertex(to);
        return v1.addEdge(v2, 0);
    }

    @Override
    public boolean hasEdge(String from, String to) {
        Vertex fromV = getVertex(from);
        Vertex toV = getVertex(to);
        return fromV.hasEdge(toV);
    }

    @Override
    public boolean removeEdge(String from, String to) {
        if(!hasEdge(from, to)){
            return false;
        }
        return getVertex(from).removeEdge(getVertex(to));
    }

    @Override
    public int countVertices() {
        return graphMap.size();
    }

    @Override
    public int countEdges() {
        return graphMap.values().stream().mapToInt(Vertex::getEdgesCount).sum();
    }

    @Override
    public List<Vertex> adjacentVertex(String label) {
        return getVertex(label).getEdges().stream().map(Edge::toVertex).collect(Collectors.toList());
    }

    @Override
    public int degreeVertex(String label) {
        return getVertex(label).getEdgesCount();
    }
}
