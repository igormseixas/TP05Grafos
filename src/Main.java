import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {



    public static void main (String[]args){
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        try{
            String str;
            String[] edge;
            int isDigraph, n, source, terminal, weight;
            // this can be ignored knowing that the graph is always a digraph
            isDigraph = Integer.valueOf(io.readLine());

            n = Integer.valueOf(io.readLine());

            Digraph network = new Digraph(n);

            //Retrieving Source and adding the first Edge
            edge = io.readLine().split(",");
            source = Integer.valueOf(edge[0]);
            network.addEdge(source, Integer.valueOf(edge[1]), Integer.valueOf(edge[2]));//First Edge

            //Retrieving the Terminal and Adding the second Edge
            edge = io.readLine().split(",");
            terminal = Integer.valueOf(edge[0]);
            network.addEdge(terminal, Integer.valueOf(edge[1]), Integer.valueOf(edge[2]));//Second Edge

            for ( str = io.readLine(); !str.equals("FIM"); str = io.readLine()){
                edge = str.split(",");
                network.addEdge(Integer.valueOf(edge[0]), Integer.valueOf(edge[1]), Integer.valueOf(edge[2]));
            }

            System.out.println("Max Flow: " + network.FordFulkerson(source,terminal));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
