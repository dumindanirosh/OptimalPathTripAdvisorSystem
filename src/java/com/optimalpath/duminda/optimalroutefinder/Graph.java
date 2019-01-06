package com.optimalpath.duminda.optimalroutefinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class Graph {
	private final List<Vertex> nodes;
	private final List<Edge> edges;
	private Set<Vertex> settledNodes;
	private Set<Vertex> unSettledNodes;
	private Map<Vertex, Vertex> predecessors= new HashMap<Vertex, Vertex>();
	private Map<Vertex, Double> distance;
	private Vertex sourceVertex;

	public Graph(List<Vertex> vertexes, List<Edge> edges) {
		this.nodes = new ArrayList<Vertex>(vertexes);
		this.edges = new ArrayList<Edge>(edges);
	}

	public List<Vertex> getVertexes() {
		return nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void execute(Vertex source) {
		this.sourceVertex = source;
		settledNodes = new HashSet<Vertex>();
		unSettledNodes = new HashSet<Vertex>();
		distance = new HashMap<Vertex, Double>();
		predecessors = new HashMap<Vertex, Vertex>();
		distance.put(source, 0.0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Vertex node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Vertex node) {
		List<Vertex> adjacentNodes = getNeighbors(node);
		for (Vertex target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDistance(node, target)) {
				distance.put(target,
						getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	private List<Vertex> getNeighbors(Vertex node) {
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
					&& !isSettled(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	private Vertex getMinimum(Set<Vertex> vertexes) {
		Vertex minimum = null;
		for (Vertex vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Vertex vertex) {
		return settledNodes.contains(vertex);
	}

	private double getShortestDistance(Vertex destination) {
		Double d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	public double getDistance(Vertex node, Vertex target) {
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
					&& edge.getDestination().equals(target)) {
				return edge.getDistance();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public Path getPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex step = target;
		// Check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);

		if (path != null && path.size()>0) {
			Path pathWay = new Path();
			pathWay.setSource(this.sourceVertex);
			pathWay.setDestination(target);
			pathWay.setPoints(path);
			pathWay.setDistance(getDistance(path));

			return pathWay;
		} else {
			return null;
		}
	}

	public int getDistance(List<Vertex> path) {
		if (path != null && path.size() > 0) {
			int distance = 0;
			for (int i = 0; i < path.size() - 1; i++) {
				distance += getDistance(path.get(i), path.get(i + 1));
			}
			return distance;
		} else {
			return -1;
		}
	}

}
