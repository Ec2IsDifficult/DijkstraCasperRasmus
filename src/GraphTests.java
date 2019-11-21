import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class GraphTests {

    public static void main(String[] args) {
        // Create graph
        GraphTests TestGraph= new GraphTests();
        Graph g = TestGraph.MakeSmallGraph();
        Vertex source = g.getvertex("A");
        Vertex zink = g.getvertex("F");
        Pair<Integer, Map<Vertex, Vertex>> results=g.ShortestDistance(source, zink);
        Vertex current =zink;
        ArrayList<Vertex> Path= new ArrayList<>();
        Path.add(zink);
        while ((current != source) && (results.getValue().get(current)!=null))
        {
            current=results.getValue().get(current);
            Path.add(0,current);
        }
        for(Vertex v : Path) {
            System.out.print(v.Name);
            if (v != zink)
                System.out.print("->");
        }

        System.out.println(" ");
        Pair<Integer, Map<Vertex, Vertex>> results1=g.ShortestTime(source, zink);
        Vertex current1 =zink;
        ArrayList<Vertex> Path1= new ArrayList<>();
        Path1.add(zink);
        while ((current1 != source) && (results1.getValue().get(current1)!=null))
        {
            current1=results1.getValue().get(current1);
            Path1.add(0,current1);
        }
        for(Vertex v : Path1) {
            System.out.print(v.Name);
            if (v != zink)
                System.out.print("->");
        }


    }
    public Graph MakeSmallGraph()
    {
        Graph mygraph= new Graph();
        final Vertex A = mygraph.addvertex("A");
        final Vertex B = mygraph.addvertex("B");
        final Vertex C = mygraph.addvertex("C");
        final Vertex D = mygraph.addvertex("D");
        final Vertex E = mygraph.addvertex("E");
        final Vertex F = mygraph.addvertex("F");
        //vi skal finde både distance og time
        mygraph.newedge(A,B,1,2);
        mygraph.newedge(A,C, 5,1);
        mygraph.newedge(A,D, 4,6);
        mygraph.newedge(B,C, 3,2);
        mygraph.newedge(B,D, 2,3);
        mygraph.newedge(B,E, 2,4);
        mygraph.newedge(C,F, 1,8);
        mygraph.newedge(C,E, 2,2);
        mygraph.newedge(D,F, 2,7);
        mygraph.newedge(E,F, 3,6);

        return mygraph;

    }
}

