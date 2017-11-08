import org.graphstream.graph.Graph;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.scalags.graph.MultiGraph;

import java.util.LinkedList;

public class AnimatedDigraph {
    public int[ ][ ] digraph;
    public int[ ] parent;
    Graph displayGraph;

    /**
     *
     * @param length
     */
    public AnimatedDigraph(int length ){
        this.digraph = new int[length][length];

        for(int i=0; i<length;i++){
            for(int j=0; j<length; j++){
                this.digraph[i][j] = 0;
            }
        }

        //Building Graphic Interface
        System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        displayGraph = new MultiGraph("Digraph");
        displayGraph.addAttribute("ui.stylesheet", "url('stylesheet')");
        displayGraph.addAttribute("ui.quality");
        displayGraph.addAttribute("ui.antialias");
        displayGraph.display();
    }

    /**
     *
     * @param source
     * @param terminal
     * @param weight
     */
    public void addEdge(int source, int terminal, int weight) {

        this.digraph[source][terminal] = weight;

        try{
            displayGraph.addNode(""+source);
            displayGraph.getNode(""+source).addAttribute("ui.label", source);
            sleep();
        } catch (IdAlreadyInUseException iaiue){

        }

        try{
            displayGraph.addNode(""+terminal);
            displayGraph.getNode(""+terminal).addAttribute("ui.label", terminal);
            sleep();
        } catch (IdAlreadyInUseException iaiue){

        }

        try{
            displayGraph.addEdge(""+source+terminal,""+source,""+terminal,true);
            displayGraph.getEdge(""+source+terminal).setAttribute("weight",weight);
            displayGraph.getEdge(""+source+terminal).addAttribute("ui.label", weight);
            sleep();
        }catch (IdAlreadyInUseException iaiue){

        }

        System.out.println(""+source+terminal);
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

        //Color the Source and terminal

        displayGraph.getNode(""+source).setAttribute("ui.class", "marked");
        sleep();
        displayGraph.getNode(""+terminal).setAttribute("ui.class", "marked");

        int fluxoMaximo = 0;

        while(BFS(source,terminal)) {
            int numeroMuitoGrande = Integer.MAX_VALUE;

            for(int i = terminal; i != source; i = parent[i]) {
                numeroMuitoGrande = Math.min(numeroMuitoGrande,digraph[parent[i]][i]);
                System.out.println(""+numeroMuitoGrande);
            }

            for(int i = terminal; i != source; i = parent[i]) {
                digraph[parent[i]][i] -= numeroMuitoGrande;
                digraph[i][parent[i]] += numeroMuitoGrande;
                System.out.println(""+numeroMuitoGrande);
            }

            fluxoMaximo += numeroMuitoGrande;
        }

        return fluxoMaximo;
    }

    /**
     *
     */
    protected static void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }

    /**
    protected String styleSheet =
            "node {" +
                    "	fill-color: black;" +
                    "}" +
                    "node.marked {" +
                    "	fill-color: red;" +
                    "}";
     */

    /**
     *
     * @param args
     */
    public static void main(String[ ] args){
        AnimatedDigraph g = new AnimatedDigraph(4);

        AnimatedDigraph g2 = new AnimatedDigraph(4);
        g2.addEdge(0,1,10);
        g2.addEdge(0,2,10);
        g2.addEdge(1,2,1);
        g2.addEdge(1,3,10);
        g2.addEdge(2,3,10);

        System.out.println("Max Flow "+ g2.FordFulkerson(0,3));
    }
}
