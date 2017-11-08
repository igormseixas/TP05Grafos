import java.util.LinkedList;

public class Digraph {
    public int[ ][ ] digraph;
    public int[ ] parent;
    public int n; // number of vertex

    /**
     *
     * @param length
     */
    public Digraph(int length ){
        this.digraph = new int[length][length];
        this.n = 0;

        for(int i=0; i<length;i++){
            for(int j=0; j<length; j++){
                this.digraph[i][j] = 0;
            }
        }
    }

    /**
     *
     * @param source
     * @param terminal
     * @param weight
     */
    public void addEdge(int source, int terminal, int weight) {
        this.digraph[source][terminal] = weight;
        n++;
    }

    /**
     *
     * @param source
     * @param terminal
     * @return visited[terminal]
     */
    public boolean BFS (int source, int terminal){
        //Mark all the vertices as not visited
        int j = 0;
        boolean visited[ ] = new boolean[n];
        this.parent = new int[n];

        // BFS Queue
        LinkedList<Integer> queue = new LinkedList<>( );

        //Mark source as visited
        visited[source] = true;
        this.parent[source] = -1;
        queue.add(source);

        while (queue.size( ) != 0){
            //Dequeue a vertex and print to check
            int u = queue.poll( );

            //Start BFS with adjacent neighbors
            for(int i=0; i<digraph.length; i++){
                if(visited[i]==false && digraph[u][i] > 0){
                    queue.add(i);
                    parent[i] = u;
                    visited[i] = true;
                }
            }
        }

        return (visited[terminal]); // if visited was reached return true
    }

    /**
     *
     * @param args
     */
    public static void main(String[ ] args){
        Digraph g = new Digraph(4);

        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 2, 4);
        g.addEdge(2, 0, 5);
        g.addEdge(2, 3, 6);
        g.addEdge(3, 3, 1);

        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");

        System.out.println(g.BFS(0,3));
        System.out.println(g.BFS(2,1));
        System.out.println(g.BFS(0,2));
        System.out.println(g.BFS(2,3));
    }
}
