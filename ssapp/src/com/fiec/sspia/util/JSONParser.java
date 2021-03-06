package com.fiec.sspia.util;


import java.io.*;
import java.util.List;
 

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;

import com.fiec.sspia.buff.Tag;

import android.util.Log;
 
public class JSONParser {
 
    static InputStream is = null;
    static JSONObject json = null;
    static String aux2 = null;
 
    public JSONParser() {
 
    }
    
    public String makeHttpRequestPOST(String url, String method, List<NameValuePair> params1){
    	try{
	    	if(method == "POST"){
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpPost httpPost = new HttpPost(url);
	            httpPost.setEntity(new UrlEncodedFormEntity(params1));
	
	            HttpResponse httpResponse = httpClient.execute(httpPost);
	            HttpEntity httpEntity = httpResponse.getEntity();
	            is = httpEntity.getContent();
	            convertResult();  	            
	        }
    	}catch (UnsupportedEncodingException e) {
	    	Log.w(Tag._TAG, e.toString());
	    } catch (ClientProtocolException e) {
	    	Log.w(Tag._TAG, e.toString());
	    } catch (IOException e) {
	    	Log.w(Tag._TAG, e.toString());
	    }
    	return aux2;
    }
    
    public JSONObject makeHttpRequest(String url, String method){
    	json = null;
        try {
 
            if(method == "GET"){
                DefaultHttpClient httpClient = new DefaultHttpClient();
                //String paramString = URLEncodedUtils.format(params1, "utf-8");
                //url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
 
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();   
                convertResult();  
            }          
            
 
        } catch (UnsupportedEncodingException e) {
        	 Log.w(Tag._TAG, e.toString());
        } catch (ClientProtocolException e) {
        	Log.w(Tag._TAG, e.toString());
        } catch (IOException e) {
        	Log.w(Tag._TAG,e.toString());
        }
		return sendResult();
    }
 
    public JSONObject makeHttpRequest(String url, String method,
            List<NameValuePair> params1) {
    	json = null;
        try {
 
            if(method == "GET"){
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params1, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
 
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();   
                convertResult();  
            }          
            
 
        } catch (UnsupportedEncodingException e) {
        	 Log.w(Tag._TAG, e.toString());
        } catch (ClientProtocolException e) {
        	Log.w(Tag._TAG, e.toString());
        } catch (IOException e) {
        	Log.w(Tag._TAG,e.toString());
        }
		return sendResult();
    }
        

    private void convertResult(){
    	try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            String line;
            StringBuilder sb = new StringBuilder();
            String aux = ""; 
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            aux = sb.toString();
            aux2 = aux;
            is.close();
            reader.close();
           
        } catch (Exception e) {
            Log.e(Tag._TAG, "Error converting result " + e.toString());
        }
    }
    
    private JSONObject sendResult(){
    	try {
        	json = new JSONObject(aux2);
        } catch (Exception e) {
            Log.e(Tag._TAG, "Error parsing data " + e.toString());
        }
 
        return json;
    }
}