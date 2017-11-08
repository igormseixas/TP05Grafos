import java.util.LinkedList;

public class Digraph {
    public int[ ][ ] digraph;
    public int[ ] parent;

    /**
     *
     * @param length
     */
    public Digraph(int length ){
        this.digraph = new int[length][length];

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
        boolean visited[ ] = new boolean[digraph.length];
        this.parent = new int[digraph.length];

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
     * @param source
     * @param terminal
     * @return
     */
    public int FordFulkerson (int source , int terminal) {

        int fluxoMaximo = 0;

        while(BFS(source,terminal)) {
            int numeroMuitoGrande = Integer.MAX_VALUE;

            for(int i = terminal; i != source; i = parent[i]) {
                numeroMuitoGrande = Math.min(numeroMuitoGrande,digraph[parent[i]][i]);
            }

            for(int i = terminal; i != source; i = parent[i]) {
                digraph[parent[i]][i] -= numeroMuitoGrande;
                digraph[i][parent[i]] += numeroMuitoGrande;
            }

            fluxoMaximo += numeroMuitoGrande;
        }

        return fluxoMaximo;
    }

    /**
     *
     * @param args
     */
    public static void main(String[ ] args){
        Digraph g = new Digraph(4);

        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 4);


        System.out.println(g.BFS(0,3));
        System.out.println(g.BFS(2,1));
        System.out.println(g.BFS(0,2));
        System.out.println(g.BFS(2,3));

        int graph[][] =new int[][] {
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        g.digraph = graph;
        System.out.println("Max Flow "+ g.FordFulkerson(0,5));
    }
}
