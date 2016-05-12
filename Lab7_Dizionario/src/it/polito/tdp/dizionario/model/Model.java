package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.dizionario.db.DizionarioDAO;

public class Model {

	private DizionarioDAO dao; 
	private SimpleGraph<String, DefaultEdge> wordGraph; 
	private int lunghezza;
	
	public Model(){
		this.dao = new DizionarioDAO();
		this.wordGraph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		lunghezza = -1;
	}
	
	public void createGraph(int lunghezza){
		this.lunghezza = lunghezza;
		List<String> nodi = dao.loadParole(lunghezza);
		Graphs.addAllVertices(wordGraph, nodi);
		for(String s:nodi){
//			for(int i=0; i<lunghezza; i++){
//				for(String s2:dao.viciniVicini(s.substring(0, i)+"%"+s.substring(i+1, lunghezza))){					
//					if(!wordGraph.containsEdge(s, s2) && s.compareTo(s2)!=0)
//						wordGraph.addEdge(s, s2);
//				}				
				
//			}
			for(String s2:nodi){
				if(!wordGraph.containsEdge(s, s2) && this.check(s, s2))
					wordGraph.addEdge(s, s2);
			}
		}
	}
	
	public boolean check(String s1, String s2){
		int l = s1.length();
		int count=0;
		for(int i=0; i<l; i++){
			if(s1.charAt(i)!=s2.charAt(i))
				count++;
			if(count>1)
				return false;
		}
		if(count==0)
			return false;
		return true;
	}
	
	public List<String> getVicini(String parola){
		List<String> vicini = new ArrayList<>();
		for(String s:Graphs.neighborListOf(wordGraph, parola))
			vicini.add(s);
		return vicini;
	}
	
	public List<String> getConnessi(String parola){
		// visita in profondità
		List<String> connessi = new ArrayList<>();
		GraphIterator<String, DefaultEdge> visit = new DepthFirstIterator<String, DefaultEdge> (wordGraph, parola);
		while(visit.hasNext())
			connessi.add(visit.next());
		return connessi;
	}
	
	public SimpleGraph<String, DefaultEdge> getGraph(){
		return wordGraph;
	}
	
	public int getLen(){
		return lunghezza;
	}

	public boolean findParola(String s){
		return wordGraph.containsVertex(s);
	}
	
	public List<String> getCammino(String s1, String s2) {
		if(!findParola(s1) || !findParola(s2)) // almeno una delle due parole non esiste nel grafo
			return null;
//		DijkstraShortestPath<String, DefaultEdge> dijkstra = new DijkstraShortestPath<String, DefaultEdge>(wordGraph, s1, s2);
//		GraphPath<String, DefaultEdge> path = dijkstra.getPath();
//		if(path==null)
//			return null;
//		return Graphs.getPathVertexList(path);
		FloydWarshallShortestPaths<String, DefaultEdge> floyd = new FloydWarshallShortestPaths<String, DefaultEdge>(wordGraph);
		GraphPath<String, DefaultEdge> path = floyd.getShortestPath(s1, s2);
		if(path==null)
			return null;
		return Graphs.getPathVertexList(path);
	}
	
//	public static void main(String[] args){
//		Model m = new Model();
//		m.createGraph(3);
//		System.out.println(m.getVicini("aah"));
//		System.out.println(m.getConnessi("aah"));
//	}
	
}
