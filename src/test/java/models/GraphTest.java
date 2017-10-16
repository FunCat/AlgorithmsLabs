package models;

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
    public void countVertexTest(){
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        assertEquals(3, graph.countVertices());
    }

    @Test
    public void addVertexTest_whenGraphAddVertex(){
        assertEquals(true, graph.addVertex("1"));
        assertEquals(1, graph.countVertices());
        assertEquals(new Vertex("1"), graph.getVertex("1"));
    }

    @Test
    public void addVertexTest_whenGraphNotAddVertex(){
        assertEquals(true, graph.addVertex("1"));
        assertEquals(false, graph.addVertex("1"));
        assertEquals(1, graph.countVertices());
        assertEquals(new Vertex("1"), graph.getVertex("1"));
    }

    @Test
    public void hasVertexTest_whenGraphHasVertex(){
        graph.addVertex("1");
        assertEquals(true, graph.hasVertex("1"));
    }

    @Test
    public void hasVertexTest_whenGraphHasNotVertex(){
        graph.addVertex("2");
        assertEquals(false, graph.hasVertex("1"));
    }

    @Test
    public void removeVertex_whenGraphRemoveVertex(){
        graph.addVertex("1");
        assertEquals(true, graph.removeVertex("1"));
        assertEquals(0, graph.countVertices());
    }

    @Test
    public void removeVertex_whenGraphNotRemoveVertex(){
        graph.addVertex("2");
        assertEquals(false, graph.removeVertex("1"));
        assertEquals(1, graph.countVertices());
    }
}