package cayley.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cayley.http.client.CayleyClient;
import cayley.http.model.Graph;
import cayley.http.model.Path;
import cayley.http.model.Quad;

public class Corpus {
	
	public void readCorpus(String filepath){
		if(null == filepath || filepath.isEmpty())
			return;
		File file = new File(filepath);
		if(!file.exists() || !file.canRead())
			return;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			int i = 0;
			List<Quad> quads = new ArrayList<Quad>();
			Long start = System.currentTimeMillis();
			while(null != (line = reader.readLine())){
				String[] triples = line.split(" ");
				Quad quad = new Quad(triples[0], triples[1], triples[2], null);
				quads.add(quad);
				i++;
				System.out.println(line);
				if(i % 100 == 0){
					CayleyClient.getInstance().writeQuads(quads);
					Long end = System.currentTimeMillis();
					System.out.println(String.format("write %d quads, time elapse: %d ms", i, (end - start)));
					quads.clear();
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void testQuery() {
		Graph graph = new Graph();
		List<String> nodeIds = new ArrayList<>();
		nodeIds.add("<http://www.wikidata.org/entity/Q1>");
		Path path = graph.vertex(nodeIds).out("<http://www.w3.org/2000/01/rdf-schema#label>", "label");
		path = path.both("<http://www.w3.org/2000/01/rdf-schema#label>", "label");
		JSONObject query = CayleyClient.getInstance().query(path);
		System.out.println(query);
	}
	
	public static void main(String[] args) {
		Corpus corpus = new Corpus();
//		corpus.readCorpus("I:\\Data\\wikidata-terms.nt");
		corpus.testQuery();
	}

}
