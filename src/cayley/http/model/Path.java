package cayley.http.model;

import java.util.Collection;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class Path extends Statement{
	public Path(Graph graph){
		super();
		this.push(graph.toString());
	}
	
	public Path out(Object predictPath, Object tags) {
		return this.bounds("Out", predictPath, tags);
	}
	public Path in(Object predictPath, Object tags) {
		return this.bounds("In", predictPath, tags);
	}
	public Path both(Object predictPath, Object tags) {
		return this.bounds("Both", predictPath, tags);
	}
	public Path is(List<String> nodeIds) {
		this.pushMethodWithListOfStrings("Is", nodeIds);
		return this;
	}
	
	public Path has(String predicate, String object) {
		this.push(String.format("Has(\"%s\", \"%s\")", predicate, object));
		return this;
	}
	
	public Path tag(List<String> tags) {
		this.pushMethodWithListOfStrings("Tag", tags);
		return this;
	}
	public Path back(String tag) {
		this.push(String.format("Back(\"%s\")", tag));
		return this;
	}
	public Path save(String predicate, String object) {
		this.push(String.format("Save(\"%s\", \"%s\")", predicate, object));
		return this;
	}
	public Path intersect(Vertex query) {
		this.push(String.format("Intersect(%s)", query.toString()));
		return this;
	}
	
	public Path union(Vertex query) {
		this.push(String.format("Union(%s)", query.toString()));
		return this;
	}
	
	public Path follow(Morphism morphism){
		this.push(String.format("Follow(%s)", morphism.toString()));
		return this;
	}
	
	public Path followR(Morphism morphism){
		this.push(String.format("FollowR(%s)", morphism.toString()));
		return this;
	}
	
	protected Path bounds(String method, Object predictPath, Object tags){
		if(null == predictPath && null == tags){
			this.push(String.format("%s()", method));
		}else if (null == tags) {
			this.push(String.format("%s(%s)", method, this.formatInputBounds(predictPath)));
		}else {
			this.push(String.format("%s(%s,%s)", method, 
					this.formatInputBounds(predictPath), this.formatInputBounds(tags)));
		}
		return this;
	}

	private String formatInputBounds(Object value) {
		if (null == value) {
			return "";
		}
		if(value instanceof Collection<?> || value instanceof String[]){
			return JSON.toJSONString(value);
		}
		
		if(value instanceof String){
			return String.format("\"%s\"", value);
		}
		return "";
	}
}
