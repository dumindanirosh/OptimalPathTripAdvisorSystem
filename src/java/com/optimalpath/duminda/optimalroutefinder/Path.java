package com.optimalpath.duminda.optimalroutefinder;

import java.util.List;

public class Path {
	private List<Vertex> pathPoints;
	private Vertex source;
	private Vertex destination;
	private int distance;

	public void setPoints(List<Vertex> pathPoints) {
		this.pathPoints = pathPoints;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public void setDestination(Vertex destination) {
		this.destination = destination;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public List<Vertex> getPoints() {
		return pathPoints;
	}

	public Vertex getSource() {
		return source;
	}

	public Vertex getDestination() {
		return destination;
	}

	public int getDistance() {
		return distance;
	}

	public void print() {
		System.out.println("From: " + source.getName());
		System.out.println("To: " + destination.getName());
		System.out.println("Distance: " + distance);
		System.out.println("Points: ");
		if (pathPoints != null) {
			for(int i = 0; i < pathPoints.size();i++){
				if (i==0) {
					System.out.print(pathPoints.get(i));
				} else {
					System.out.print(" -> " + pathPoints.get(i));
				}
			}
		}
	}
}
