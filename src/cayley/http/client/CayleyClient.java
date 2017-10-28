package cayley.http.client;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cayley.http.model.Quad;
import cayley.http.model.Statement;
import cayley.http.util.HttpPostUtil;

public class CayleyClient {
	protected String query_url = null;
	protected String write_url = null;
	protected String delete_url = null;
	private static CayleyClient INSTANCE = null;
	public static CayleyClient getInstance(){
		if(null == INSTANCE){
			synchronized (CayleyClient.class) {
				if(null == INSTANCE){
					INSTANCE = new CayleyClient();
				}
			}
		}
		return INSTANCE;
	}
	
	private CayleyClient() {
		File f = new File("cayley.properties");
		if(!f.exists()){
			f = new File("conf/cayley.properties");
		}
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(f));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		String ip = "", port = "", version = "";
		ip = (null == props ? ip : props.getProperty("ip"));
		port = (null == props ? port : props.getProperty("port"));
		version = (null == props ? version : props.getProperty("version", "v1"));
		this.query_url = String.format("%s/api/%s/query/gizmo", ip+":"+port, version);
		this.write_url = String.format("%s/api/%s/write", ip+":"+port, version);
		this.delete_url = String.format("%s/api/%s/delete", ip+":"+port, version);
	}
	
	public JSONObject writeQuads(List<Quad> quads) {
		if(null == quads || quads.isEmpty())
			return null;
		JSONArray quadArray = new JSONArray();
		for(Quad quad:quads){
			JSONObject quadObject = (JSONObject) JSON.toJSON(quad);
			quadArray.add(quadObject);
		}
		String result = HttpPostUtil.sendPost(write_url, quadArray);
		return JSON.parseObject(result);
	}
	
	public JSONObject deleteQuads(List<Quad> quads) {
		if(null == quads || quads.isEmpty())
			return null;
		JSONArray quadArray = new JSONArray();
		for(Quad quad:quads){
			JSONObject quadObject = (JSONObject) JSON.toJSON(quad);
			quadArray.add(quadObject);
		}
		String result = HttpPostUtil.sendPost(delete_url, quadArray);
		return JSON.parseObject(result);
	}
	
	public JSONObject query(Statement statement) {
		if(null == statement)
			return null;
		String result = HttpPostUtil.sendPost(query_url, statement.toString());
		return JSON.parseObject(result);
	}
}
