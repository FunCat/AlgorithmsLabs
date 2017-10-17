package models;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    private Graph graph;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
    }

    @Test
    public void addVertexTest_whenGraphAddVertex() {
        assertEquals(true, graph.addVertex("1"));
        assertEquals(1, graph.countVertices());
        assertEquals(new Vertex("1"), graph.getVertex("1"));
    }

    @Test
    public void addVertexTest_whenGraphNotAddVertex() {
        assertEquals(true, graph.addVertex("1"));
        assertEquals(false, graph.addVertex("1"));
        assertEquals(1, graph.countVertices());
        assertEquals(new Vertex("1"), graph.getVertex("1"));
    }

    @Test
    public void hasVertexTest_whenGraphHasVertex() {
        graph.addVertex("1");
        assertEquals(true, graph.hasVertex("1"));
    }

    @Test
    public void hasVertexTest_whenGraphHasNotVertex() {
        graph.addVertex("2");
        assertEquals(false, graph.hasVertex("1"));
    }

    @Test
    public void removeVertexTest_whenGraphRemoveVertex() {
        graph.addVertex("1");
        assertEquals(true, graph.removeVertex("1"));
        assertEquals(0, graph.countVertices());
    }

    @Test
    public void removeVertexTest_whenGraphNotRemoveVertex() {
        graph.addVertex("2");
        assertEquals(false, graph.removeVertex("1"));
        assertEquals(1, graph.countVertices());
    }

    @Test
    public void getVertexTest_whenGraphReturnVertex() {
        graph.addVertex("1");
        assertEquals(new Vertex("1"), graph.getVertex("1"));
    }

    @Test
    public void getVertexTest_whenGraphNotReturnVertex() {
        graph.addVertex("2");
        assertEquals(null, graph.getVertex("1"));
    }

    @Test
    public void addEdgeTest_whenGraphAddEdge() {
        graph.addVertex("1");
        graph.addVertex("2");
        assertEquals(true, graph.addEdge("1", "2"));
    }

    @Test
    public void addEdgeTest_whenGraphHasAlreadyThisEdge() {
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addEdge("1", "2");
        assertEquals(false, graph.addEdge("1", "2"));
    }

    @Test
    public void hasEdgeTest_whenGraphHasEdge() {
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addEdge("1", "2");
        assertEquals(true, graph.hasEdge("1", "2"));
    }

    @Test
    public void hasEdgeTest_whenGraphHasNotEdge() {
        graph.addVertex("1");
        graph.addVertex("2");
        assertEquals(false, graph.hasEdge("1", "2"));
    }

    @Test
    public void removeEdgeTest_whenGraphRemoveEdge() {
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addEdge("1", "2");
        assertEquals(true, graph.removeEdge("1", "2"));
    }

    @Test
    public void removeEdgeTest_whenGraphNotRemoveEdge() {
        graph.addVertex("1");
        graph.addVertex("2");
        assertEquals(false, graph.removeEdge("1", "2"));
    }

    @Test
    public void countVertexTest() {
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        assertEquals(3, graph.countVertices());
    }

    @Test
    public void countEdgesTest() {
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("1", "4");
        graph.addEdge("2", "4");
        graph.addEdge("4", "3");
        assertEquals(5, graph.countEdges());
    }

    @Test
    public void adjacentVertexTest() {
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("1", "4");
        assertEquals(true, Arrays.deepEquals(Arrays.asList(new Vertex("2"),
            new Vertex("3"),
            new Vertex("4")).toArray(),
            graph.adjacentVertex("1").toArray()));
    }

    @Test
    public void degreeVertexTest() {
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addEdge("1", "2");
        graph.addEdge("1", "3");
        graph.addEdge("1", "4");
        assertEquals(3, graph.degreeVertex("1"));
    }

    @Test
    public void findShortestPathTest(){
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addEdge("1", "2", 2);
        graph.addEdge("1", "3", 2);
        graph.addEdge("1", "4", 10);
        graph.addEdge("2", "3", 3);
        graph.addEdge("3", "4", 1);
        System.out.println(graph.findShortestPath("1", "4"));
    }
}