package com.solr.request;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class MySolrClient {
	
	public static final String solrServerUrl = "http://192.168.0.103:8080/solr";
	
	public static final String solrCroeHome = "demo";

	public static void main(String[] args) throws SolrServerException, IOException {

		long start = System.currentTimeMillis();
		SolrClient client = getSolrClient();
		SolrQuery params = new SolrQuery();
		params.set("q", "*:*");
		params.set("rows", "6313");
		params.set("start", "0");
		QueryResponse response = client.query(params);
		SolrDocumentList results = response.getResults();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < results.size(); i++) {
			sb.append(results.get(i).get("序号"));
			sb.append(results.get(i).get("姓名"));
			sb.append(results.get(i).get("国家"));
			sb.append(results.get(i).get("单位"));
			sb.append(results.get(i).get("职称"));
			sb.append(results.get(i).get("总发文"));
			sb.append(results.get(i).get("总引用"));
			sb.append(results.get(i).get("H指数"));
			sb.append(results.get(i).get("联系方式"));
			sb.append(results.get(i).get("个人主页"));
			sb.append(results.get(i).get("纬度"));
			sb.append(results.get(i).get("经度"));
			sb.append(results.get(i).get("位置代码"));
		}

		long end = System.currentTimeMillis();
		long total = (end - start) / 1000;
		System.err.println(total / 60 + "M" + total % 60 +"s," + "共" + results.size());
		

	}

	public static SolrClient getSolrClient() {
		return new HttpSolrClient(solrServerUrl + "/" + solrCroeHome);
	}
}
