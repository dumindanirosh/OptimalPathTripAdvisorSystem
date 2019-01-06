package com.optimalpath.duminda.optimalroutefinder;

public class Edge {

    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private final double distance;
    private double time;

    public Edge(String id, Vertex source, Vertex destination, double distance) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.distance = distance;

    }

    public String getId() {
        return id;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }

    public double getDistance() {
        return distance;
    }

    /**
     * @return the time
     */
    public double getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
