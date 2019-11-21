import java.sql.SQLOutput;
import java.util.*;

import javafx.util.Pair;

public class Graph {
    private ArrayList<Vertex> Vertices = new ArrayList<>();

    public Vertex addvertex(String id) {
        Vertex newvertex = new Vertex(id);
        Vertices.add(newvertex);
        return newvertex;
    }

    public void addvertex(Vertex v) {
        Vertices.add(v);
    }

    public Vertex getvertex(String s) {
        for (Vertex v : Vertices) {
            if (v.Name == s)
                return v;
        }
        return null;
    }

    public void newedge(Vertex from, Vertex to, int dist, int tim) {
        Edge newedge = new Edge(from, to);
        newedge.distance = dist;
        newedge.time = tim;
    }

    public Pair<Integer, Map<Vertex, Vertex>> ShortestTime(Vertex source, Vertex zink) {
        Map<Vertex, Vertex> PredecessorMap = new HashMap<>(); //denne skal opdateres heletiden
        Map<Vertex, Integer> timeMap = new HashMap<>();
        Map<Vertex, Integer> timeMapDisplay = new HashMap<>();
        // Here we initialize the arrays we are going to use
        Vertex vertex;
        for (Vertex v : Vertices) { //O(v)
            timeMap.put(v, 1000);
            PredecessorMap.put(v, null);
        }

        // here we set the source vertex,
        timeMap.put(source, 0);

        for (int count = 0; count < Vertices.size(); count++) { //O(v)

            vertex = getmin1(timeMap); //o(v*v)
            //System.out.println(vertex.getOutEdges().size());
            for (int i = 0; i < vertex.getOutEdges().size(); i++) { //O(v*e)

                if (vertex.getOutEdges().get(i).time + timeMap.get(vertex)
                        < timeMap.get(vertex.getOutEdges().get(i).getTovertex())) {
                    timeMap.put(vertex.getOutEdges().get(i).getTovertex(), vertex.getOutEdges().get(i).time + timeMap.get(vertex));
                    PredecessorMap.put(vertex.getOutEdges().get(i).getTovertex(), vertex);
                }
            }
            int theRemoved = timeMap.put(vertex, -1);
            timeMapDisplay.put(vertex, theRemoved);
            //}
            //System.out.println(DistanceMapDisplay.keySet());
        }
        return (new Pair<Integer, Map<Vertex, Vertex>>(timeMapDisplay.get(zink), PredecessorMap));
    }

    public Pair<Integer, Map<Vertex, Vertex>> ShortestDistance(Vertex source, Vertex zink) {
        Map<Vertex, Vertex> PredecessorMap = new HashMap<>(); //denne skal opdateres heletiden
        Map<Vertex, Integer> DistanceMap = new HashMap<>();
        Map<Vertex, Integer> DistanceMapDisplay = new HashMap<>();
        // initialize arrays
        Vertex vertex;
        for (Vertex v : Vertices) {
            DistanceMap.put(v, 1000);
            PredecessorMap.put(v, null);
        }

        DistanceMap.put(source, 0);

        for (int count = 0; count < Vertices.size(); count++) {

            vertex = getmin(DistanceMap);
            for (int i = 0; i < vertex.getOutEdges().size(); i++) {

                if (vertex.getOutEdges().get(i).distance + DistanceMap.get(vertex)
                        < DistanceMap.get(vertex.getOutEdges().get(i).getTovertex())) {
                    DistanceMap.put(vertex.getOutEdges().get(i).getTovertex(), vertex.getOutEdges().get(i).distance + DistanceMap.get(vertex));
                    PredecessorMap.put(vertex.getOutEdges().get(i).getTovertex(), vertex);
                }
            }
            int theRemoved = DistanceMap.put(vertex, -1);
            DistanceMapDisplay.put(vertex, theRemoved);
            //}
            //System.out.println(DistanceMapDisplay.keySet());
        }
        return (new Pair<Integer, Map<Vertex, Vertex>>(DistanceMapDisplay.get(zink), PredecessorMap));
    }


    public Vertex getmin(Map<Vertex, Integer> qmap) {

        int value = 1000;
        Vertex vertex = null;
        //Her looper vi igennem hele distancemap og retriever den vertex som er kortest fra source vertex
        for (Map.Entry<Vertex, Integer> entry : qmap.entrySet()) { //o(v)

            if (entry.getValue() < value && entry.getValue() != -1) {
                value = entry.getValue();
                vertex = entry.getKey();
            }
        }
        // Your code
        return vertex;
    }

    public Vertex getmin1(Map<Vertex, Integer> qmap) {

        int value = 1000;
        Vertex vertex = null;
        //Her looper vi igennem hele distancemap og retriever den vertex som er kortest fra source vertex
        for (Map.Entry<Vertex, Integer> entry : qmap.entrySet()) {

            if (entry.getValue() < value && entry.getValue() != -1) {
                value = entry.getValue();
                vertex = entry.getKey();
            }
        }
        // Your code
        return vertex;
    }

}


class Vertex {
    public String Name;
    public ArrayList<Edge> OutEdges = new ArrayList<>();

    public Vertex(String id) {
        Name = id;
    }

    public void addOutEdge(Edge outedge) {
        OutEdges.add(outedge);
    }

    public ArrayList<Edge> getOutEdges() {
        return OutEdges;
    }
}

class Edge {
    private Vertex fromvertex;
    private Vertex tovertex;
    public int distance = 0;
    public int time = 0;

    public Vertex getTovertex() {
        return tovertex;
    }

    public Vertex getFromvertex() {
        return fromvertex;
    }

    public Edge(Vertex from, Vertex to) {
        fromvertex = from;
        tovertex = to;
        fromvertex.addOutEdge(this);
        //If not directional
        tovertex.addOutEdge(this);
    }
}
