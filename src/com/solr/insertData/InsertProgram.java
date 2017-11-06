package com.solr.insertData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

public class InsertProgram {
	//solr ��������ַ  
    public static final String solrServerUrl = "http://localhost:8080/solr";  
    //solrhome�µ�core  
    public static final String solrCroeHome = "my_solr";  
    //����������ѯ�ֶ�  
    public static String[] docs = {"Solr��һ����������ҵ������Ӧ�÷�����",  
                                    "�������ṩ������Web-service��API�ӿ�",  
                                    "�û�����ͨ��http����",  
                                     "����������������ύһ����ʽ��XML�ļ���������",  
                                    "Ҳ����ͨ��Http Get���������������",  
                                    "���õ�XML��ʽ�ķ��ؽ��"};  
    public static void main(String[] args) {  
        SolrClient client = getSolrClient();  
        int i=0;  
        List<SolrInputDocument> solrDocs = new ArrayList<SolrInputDocument>();  
        for (String content : docs) {  
            SolrInputDocument doc = new SolrInputDocument();  
            doc.addField("id", i++);  
            doc.addField("content_test", content);  
            solrDocs.add(doc);  
        }  
        try {  
            client.add(solrDocs); 
            //client.deleteByQuery("*:*");
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
