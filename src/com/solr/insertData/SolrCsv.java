package com.solr.insertData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

public class SolrCsv {
	//solr 服务器地址  
    public static final String solrServerUrl = "http://localhost:8080/solr";  
    //solrhome下的core  
    public static final String solrCroeHome = "my_solr";  
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("C:\\Users\\Yehua\\Desktop\\data.csv")));
		SolrClient client = getSolrClient();
		List<SolrInputDocument> solrDocs = new ArrayList<SolrInputDocument>();
		
		String line;
		int count = 0;
		while ( (line = br.readLine()) != null ) {
			String[] info = line.split(";");
			if(count != 0) {
				SolrInputDocument doc = new SolrInputDocument();  
	            doc.addField("id", info[0]);  
	            doc.addField("name", info[1]);  
	            doc.addField("isbn", info[2]);
	            doc.addField("description", info[3]);
	            doc.addField("publish_date", info[4]);
	            solrDocs.add(doc);  			
			}
			count ++;
		}
		try {  
            client.add(solrDocs);  
            client.commit();  
        } catch (SolrServerException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  

	}
	 public static SolrClient getSolrClient(){  
	        return new HttpSolrClient(solrServerUrl+"/"+solrCroeHome);  
	    }  
}
