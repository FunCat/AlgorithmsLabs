package models;

import interfaces.IGraph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph implements IGraph {

    private Map<String, Vertex> graphMap;

    public Graph() {
        graphMap = new HashMap<>();
    }

    @Override
    public boolean addVertex(String label) {
        if (graphMap.containsKey(label)) {
            return false;
        }
        graphMap.put(label, new Vertex(label));
        return true;
    }

    @Override
    public Vertex getVertex(String label) {
        if (graphMap.containsKey(label)) {
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
        if (!graphMap.containsKey(label)) {
            return false;
        }
        Vertex removed = getVertex(label);
        graphMap.values().forEach(vertex -> vertex.removeEdge(removed));

        graphMap.remove(label);
        return true;
    }

    @Override
    public boolean addEdge(String from, String to) {
        if (hasEdge(from, to)) {
            return false;
        }
        Vertex v1 = getVertex(from);
        Vertex v2 = getVertex(to);
        return v1.addEdge(v2, 0);
    }

    @Override
    public boolean addEdge(String from, String to, int weight) {
        if (hasEdge(from, to)) {
            return false;
        }
        Vertex v1 = getVertex(from);
        Vertex v2 = getVertex(to);
        return v1.addEdge(v2, weight);
    }

    @Override
    public boolean hasEdge(String from, String to) {
        Vertex fromV = getVertex(from);
        Vertex toV = getVertex(to);
        return fromV.hasEdge(toV);
    }

    @Override
    public boolean removeEdge(String from, String to) {
        if (!hasEdge(from, to)) {
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

    @Override
    public void resetGraph() {
        graphMap.values().forEach(vertex -> {
            vertex.setVisited(false);
            vertex.setParent(null);
        });
    }

    @Override
    public List<Vertex> findShortestPath(String from, String to) {
        if(!hasVertex(from) || !hasVertex(to)){
            return null;
        }
        resetGraph();

        Queue<Vertex> queue = new LinkedList<>();
        Vertex start = getVertex(from);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex first = queue.remove();
            first.setVisited(true);
            first.getEdges().forEach(edge -> {
                Vertex neighbour = edge.toVertex();
                if (!neighbour.isVisited()) {
                    int totalWeigth = first.getCountWeight() + edge.getWeigth();
                    if(totalWeigth < neighbour.getCountWeight()) {
                        neighbour.setParent(first);
                        neighbour.setCountWeight(totalWeigth);
                    }
                    queue.add(neighbour);
                }
            });
        }

        List<Vertex> path = new ArrayList<>();
        // trace path back from end vertex to start
        Vertex end = getVertex(to);
        while (end != null && !end.equals(start)) {
            path.add(end);
            end = end.getParent();
        }
        // if end is null, node not found
        if (end == null) {
            return null;
        }
        else {
            path.add(start);
            Collections.reverse(path);
        }
        return path;
    }
}
