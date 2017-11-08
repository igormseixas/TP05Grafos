import org.graphstream.algorithm.flow.FordFulkersonAlgorithm;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.layout.Eades84Layout;

public class Tutorial1 {
    protected static void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }
    public static void main(String args[]) {
        //Graph graph = new SingleGraph("Tutorial 1");
        Graph graph = new MultiGraph("Tutorial 1");

        System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph.addAttribute("ui.stylesheet", "url('style.css')");
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.display();

        graph.addNode("S");
        //graph.getNode("S").addAttribute( "ui.hide" );
        //graph.getNode("S").removeAttribute( "ui.hide" );
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("T");
        graph.addEdge("SA", "S", "A");
        graph.addEdge("SB", "S", "B");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("AT", "A", "T");
        graph.addEdge("BT", "B", "T");
        graph.getEdge("SA").setAttribute("weight",10.0);
        graph.getEdge("SB").setAttribute("weight",10.0);
        graph.getEdge("AB").setAttribute("weight",10.0);
        graph.getEdge("AT").setAttribute("weight",10.0);
        graph.getEdge("BT").setAttribute("weight",10.0);


        FordFulkersonAlgorithm ff = new FordFulkersonAlgorithm();
        ff.init(graph,"S","A");
        ff.setAllCapacities(20.0);
        ff.compute();
        //System.out.println(""+ff.getFlow("S","A"));
    }
}