package com.solr.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class GetData {
	//solr 服务器地址  
    public static final String solrServerUrl = "http://localhost:8080/solr";  
    //solrhome下的core  
    public static final String solrCroeHome = "solr_csv";  
    
//	public static void main(String[] args) throws IOException {
//		URL url = new URL(
//				"http://localhost:8080/solr/my_solr/select?indent=on&q=*:*&wt=json");
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		InputStream is = connection.getInputStream();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//		String line;
//		while((line = reader.readLine()) != null) {
//			System.out.println(line);
//		}
//	}
    public static void main(String[] args) throws SolrServerException, IOException {
		SolrClient client = getSolrClient();
		 SolrQuery params = new SolrQuery();
		 params.set("q", "title:cc");
		 QueryResponse response = client.query(params);
		 SolrDocumentList results = response.getResults();
		 
		 System.out.println("size -> " + results.size());
		 for(int i = 0; i < results.size(); i ++) {
			 System.out.println("id -> " +results.get(i).get("id"));
			 System.out.println("title -> " +results.get(i).get("title"));
			 System.out.println("content -> " +results.get(i).get("content"));
		 }
		 client.deleteById("1");
		 //client.add(docs);
	}
	
	 public static SolrClient getSolrClient(){  
	        return new HttpSolrClient(solrServerUrl+"/"+solrCroeHome);  
	    } 
}
