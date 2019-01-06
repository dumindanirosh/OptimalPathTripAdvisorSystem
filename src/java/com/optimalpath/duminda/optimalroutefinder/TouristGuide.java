package com.optimalpath.duminda.optimalroutefinder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TouristGuide {
	private Graph graph;
	private List<Vertex> places;
	private Vertex sourceVertex;
	
	public TouristGuide(Graph graph) {
		this.graph = graph;
		this.places = new ArrayList<Vertex>();
	}

	public void setSource(Vertex source) {
		this.sourceVertex = source;
		graph.execute(source);
	}

	public void markPalceToVisit(Vertex place) {
		places.add(place);
	}

	public Path getOptimalPath(Vertex destination) {
		Comparator<Path> comparator = new PathDistanceComparator();
		PriorityQueue<Path> queue = new PriorityQueue<Path>(places.size(),
				comparator);
		for (Vertex place : places) {
			Path path = graph.getPath(place);
			if (path != null) {
				queue.add(path);
			} else {
				System.out.println("Invalid places have been selected");
				System.exit(0);
			}
		}
		Graph g = new Graph(graph.getVertexes(), graph.getEdges());
		List<Vertex> optimalPath = new ArrayList<Vertex>();
		optimalPath.add(this.sourceVertex);
		int totalDistance = 0;
		while(!queue.isEmpty()) {
			Path temp = queue.remove();
			
			g.execute(optimalPath.get(optimalPath.size()-1));
			Path tempShort = g.getPath(temp.getDestination());
			optimalPath.remove(optimalPath.size()-1);
			optimalPath.addAll(tempShort.getPoints());
			totalDistance += tempShort.getDistance();
		}
		
		
		g.execute(optimalPath.get(optimalPath.size()-1));
		Path tempShort = g.getPath(destination);
		optimalPath.remove(optimalPath.size()-1);
		optimalPath.addAll(tempShort.getPoints());
		totalDistance += tempShort.getDistance();
		
				
		Path bestPath = new Path();
		bestPath.setSource(this.sourceVertex);
		bestPath.setDestination(destination);
		bestPath.setPoints(optimalPath);
		bestPath.setDistance(totalDistance);
		return bestPath;
	}

	private class PathDistanceComparator implements Comparator<Path> {
		@Override
		public int compare(Path pathOne, Path pathTwo) {
			if (pathOne.getDistance() < pathTwo.getDistance()) {
				return -1;
			} else if (pathOne.getDistance() > pathTwo.getDistance()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
