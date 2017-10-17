package models;

public class Edge {
    private Vertex from;
    private Vertex to;
    private int weigth;

    public Edge(Vertex from, Vertex to, int weigth) {
        this.from = from;
        this.to = to;
        this.weigth = weigth;
    }

    public Vertex fromVertex(){
        return from;
    }

    public Vertex toVertex(){
        return to;
    }

    public boolean isBetween(Vertex from, Vertex to){
        return (this.from.equals(from) && this.to.equals(to));
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }
}
