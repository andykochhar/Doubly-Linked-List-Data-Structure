public class Node {
    protected Album data;
    protected Node next;
    protected Node previous;

    public Node()
    {
        this.data = new Album();
        this.next = null;
        this.previous = null;
    }

    public Node(Album data)
    {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    @Override
    public String toString()
    {
        return data.toString();
    }
}
