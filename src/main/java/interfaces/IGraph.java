package interfaces;

import models.Vertex;

import java.util.List;

public interface IGraph {

    boolean addVertex(String label);
    Vertex getVertex(String label);
    boolean hasVertex(String label);
    boolean removeVertex(String label);
    int degreeVertex(String label);

    boolean addEdge(String from, String to);
    boolean addEdge(String from, String to, int weight);
    boolean hasEdge(String from, String to);
    boolean removeEdge(String from, String to);

    void resetGraph();

    int countVertices();
    int countEdges();

    List<Vertex> adjacentVertex(String label);
    List<Vertex> findShortestPath(String from, String to);
}
