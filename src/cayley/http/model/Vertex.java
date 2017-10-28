package cayley.http.model;

public class Vertex extends Path{

	public Vertex(Graph graph) {
		super(graph);
	}
	
	public Vertex all(){
		this.push("All()");
		return this;
	}
	
	public Vertex getLimit(Integer limit){
		this.push(String.format("GetLimit(%d)", limit));
		return this;
	}
}
