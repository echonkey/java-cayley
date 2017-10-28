package cayley.http.model;

import java.util.ArrayList;
import java.util.List;

public class Statement {
	private List<String> statements = null;

	public Statement() {
		super();
		statements = new ArrayList<String>();
	}
	
	protected void push(String statement){
		this.statements.add(statement);
	}
	
	protected void pushMethodWithListOfStrings(String method, List<String> params){
		StringBuilder builder = new StringBuilder();
		String paramString = "";
		if(null != params && !params.isEmpty()){
			for(String param:params){
				builder.append(String.format("\"%s\"", param));
				builder.append(",");
			}
			paramString = builder.substring(0, builder.length()-1);
		}
		String conditions = String.format("%s(%s)", method, paramString);
		this.push(conditions);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(null != statements && !statements.isEmpty()){
			for(String stmt:statements){
				builder.append(stmt);
				builder.append(".");
			}
			return builder.substring(0, builder.length()-1);
		}
		return null;
	}
}
