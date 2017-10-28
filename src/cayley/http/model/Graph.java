package cayley.http.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class Graph extends Statement{
	public Graph(){
		super();
		this.push("graph");
	}
	
	public Vertex vertex(List<String> nodeIds){
		this.pushMethodWithListOfStrings("Vertex", nodeIds);
		return new Vertex(this);
	}
	
	public Vertex v(List<String> nodeIds){
		return vertex(nodeIds);
	}
	
	public Morphism morphism(){
		this.push("Morphism()");
		return new Morphism(this);
	}
	
	public Morphism m() {
		return morphism();
	}
	
	public Graph emit(JSONObject data) {
		this.push(String.format("Emit(%s)", data.toJSONString()));
		return this;
	}
	
}
