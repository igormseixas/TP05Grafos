import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Dijkstra.Element;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSourceDGS;

/**
 *
 *     B-(1)-C
 *    /       \
 *  (1)       (10)
 *  /           \
 * A             F
 *  \           /
 *  (1)       (1)
 *    \       /
 *     D-(1)-E
 */

public class DijkstraTest {
    static String my_graph =
            "DGS004\n"
                    + "my 0 0\n"
                    + "an A \n"
                    + "an B \n"
                    + "an C \n"
                    + "an D \n"
                    + "an E \n"
                    + "an F \n"
                    + "ae AB A B weight:1 \n"
                    + "ae AD A D weight:1 \n"
                    + "ae BC B C weight:1 \n"
                    + "ae CF C F weight:10 \n"
                    + "ae DE D E weight:1 \n"
                    + "ae EF E F weight:1 \n"
            ;

    public static void main(String[] args) throws IOException {
        Graph graph = new DefaultGraph("Dijkstra Test");
        ByteArrayInputStream bs = new ByteArrayInputStream(my_graph.getBytes());

        FileSourceDGS source = new FileSourceDGS();
        source.addSink(graph);
        source.readAll(bs);

        Dijkstra dijkstra = new Dijkstra(Element.edge, "weight", "A");
        dijkstra.init(graph);
        dijkstra.compute();

        System.out.println(dijkstra.getShortestPath(graph.getNode("F")));
    }
}