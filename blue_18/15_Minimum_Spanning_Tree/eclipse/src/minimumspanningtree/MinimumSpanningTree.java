package minimumspanningtree;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree {
	public static class Node implements Comparable<Node> {
		Integer id;
		Integer dist;
		
		private Node(Integer id, Integer dist) {
			this.id = id;
			this.dist = dist;
		}
		
		public static Node createInstance(Integer id, Integer dist) {
			return new Node(id, dist);
		}
		
		@Override
		public int compareTo(Node other) {
			return this.dist.compareTo(other.dist);
		}
	}	
	
	public MinimumSpanningTree(List<List<Node>> graph) {
		this.graph = graph;
		int numberOfNode = graph.size();
		
		distance = new int[numberOfNode];
		path = new int[numberOfNode];
		visited = new boolean[numberOfNode];
		priorityQueue = new PriorityQueue<>();
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(path, -1);
		Arrays.fill(visited, false);
	}
	
	private int[] distance;
	private int[] path;
	private boolean[] visited;
	private PriorityQueue<Node> priorityQueue;
	private List<List<Node>> graph;
	
	public void prim(int beginNodeIndex) {
		priorityQueue.add(new Node(beginNodeIndex, 0));
		distance[beginNodeIndex] = 0;
		
		while (!priorityQueue.isEmpty()) {
			Node currentNode = priorityQueue.poll();
			
			visited[currentNode.id] = true;
			
			for (Node neighbor : graph.get(currentNode.id)) {
				if (!visited[neighbor.id] && neighbor.dist < distance[neighbor.id]) {
					distance[neighbor.id] = neighbor.dist;
					priorityQueue.add(new Node(neighbor.id,  neighbor.dist));
					path[neighbor.id] = currentNode.id;
				}
			}
		}
	}
}
