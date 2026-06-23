package rais.gamedev.fromscratch.level;

public class Node {
    public int x, y;
    public Node parent;
    public double g, h, f;

    public Node(int x, int y, Node parent, double g, double h) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.g = g;
        this.h = h;
        this.f = this.g + this.h;
    }
}