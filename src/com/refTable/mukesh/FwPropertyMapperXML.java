package com.refTable.mukesh;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class FwPropertyMapperXML {
	
	
   
	public static Map<String,Map<String,ArrayList<String>>> innerMap = new HashMap<>();
	
	public FwPropertyMapperXML()throws IOException{
   
      try {	
         File inputFile = new File(TestUtility.basePath+"GenDAO_resources/FW_PropertyColumnMapper.xml");
         DocumentBuilderFactory dbFactory 
            = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         NodeList nList = doc.getElementsByTagName("property");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               String classes = eElement.getAttribute("class");
               
               Map<String,ArrayList<String>> fromToMap = new HashMap<>();
				
               innerMap.put(classes, fromToMap);
               
               NodeList nListInner = eElement.getElementsByTagName("map");
               
               
               for (int tempInn = 0; tempInn < nListInner.getLength(); tempInn++) {
                  Node nNodeInn = nListInner.item(tempInn);
                  
                  if (nNodeInn.getNodeType() == Node.ELEMENT_NODE) {
                     Element eElementInn = (Element) nNodeInn;
                     
                     ArrayList<String> arrList = new ArrayList<>(9);
 					arrList.add("next value for fast4j_table_columns_0sq");
 					arrList.add("@id_table_key");
 					String from = eElementInn.getAttribute("from");
 					arrList.add("'"+from+"'");
 					String to = eElementInn.getAttribute("to");
 					arrList.add("'"+to+"'");
 					arrList.add("'N'");
 					arrList.add("current_timestamp");
 					arrList.add("'Amit.Agrawal'");
 					arrList.add("current_timestamp");
 					arrList.add("'Amit.Agrawal'");
 					fromToMap.put(from, arrList);
                     
                     }
               }
               
            }
         }
      } catch (Exception e) {
    	  System.out.println("Exception 1: "+e.toString());
         e.printStackTrace();
      }
      
      try {	
          File inputFile = new File(TestUtility.basePath+"GenDAO_resources/FW_AuditMapper.xml");
          DocumentBuilderFactory dbFactory 
             = DocumentBuilderFactory.newInstance();
          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
          Document doc = dBuilder.parse(inputFile);
          doc.getDocumentElement().normalize();
          NodeList nList = doc.getElementsByTagName("property");
          for (int temp = 0; temp < nList.getLength(); temp++) {
             Node nNode = nList.item(temp);
             
             if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String classes = eElement.getAttribute("class");
                
                if(TestUtility.keyValueWithCargoFW.containsKey(classes)){
					
					TestUtility.keyValueWithCargoFW.get(classes).set(4, "1");
				}
                
             }
          }
       } catch (Exception e) {
          e.printStackTrace();
       }
      
      try {	
          File inputFile = new File(TestUtility.basePath+"GenDAO_resources/FW_BaseMapper.xml");
          DocumentBuilderFactory dbFactory 
             = DocumentBuilderFactory.newInstance();
          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
          Document doc = dBuilder.parse(inputFile);
          doc.getDocumentElement().normalize();
          NodeList nList = doc.getElementsByTagName("property");
          for (int temp = 0; temp < nList.getLength(); temp++) {
             Node nNode = nList.item(temp);
             
             if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String classes = eElement.getAttribute("class");
                
                if(TestUtility.keyValueWithCargoFW.containsKey(classes)){
					
					TestUtility.keyValueWithCargoFW.get(classes).set(4, "2");
					NodeList nListInner = eElement.getElementsByTagName("map");
		               
		               
		               for (int tempInn = 0; tempInn < nListInner.getLength(); tempInn++) {
		                  Node nNodeInn = nListInner.item(tempInn);
		                  
		                  if (nNodeInn.getNodeType() == Node.ELEMENT_NODE) {
		                     Element eElementInn = (Element) nNodeInn;
		                    
		                     String from = eElementInn.getAttribute("from");
		 					
		 					String to = eElementInn.getAttribute("to");
		                     
		                     Map<String,ArrayList<String>> inrMap = innerMap.get(classes);
		 					inrMap.get(from).set(4, "'Y'");
		                     
		 					
		                     }
		                  }
					
					
					
					
				}
			}
                
             }
          
       } catch (Exception e) {
          e.printStackTrace();
       }
      
      
      int l = 0;
		
		StringBuffer buff = new StringBuffer();
		
		


		buff.append("DECLARE @id_table_key bigint\n ");
		
		for (Map.Entry<String, ArrayList<String>> entry : TestUtility.keyValueWithCargoFW.entrySet())
      {
          
			if(entry.getValue().size() > 1){
				
				/*buff.append("\n");
				buff.append("set @id_table_key = NEXT VALUE FOR fast4j_tables_0sq \n ");
				buff.append("\n");
				l++;
				buff.append("insert into fast4j_tables(id_fast4j_tables, function_name, fast4j_key, fast4j_value, table_type, active_sw, create_dt, create_user_id, update_dt, update_user_id) values( ");*/
				
				//System.out.println(entry.getKey() + "/" + entry.getValue());
          	
				
				StringBuffer buffInner = new StringBuffer();
          	if(innerMap.get(entry.getKey())!=null){
          	for (Map.Entry<String, ArrayList<String>> entryIn : innerMap.get(entry.getKey()).entrySet())
              {
                  if(entryIn.getValue().size() > 1){
                  	buffInner.append("insert into fast4j_table_columns(id_fast4j_table_columns, id_fast4j_tables, property_name, column_name, primary_key, create_dt, create_user_id, update_dt, update_user_id) values( ");
                  	//System.out.println(entryIn.getKey() + "/" + entryIn.getValue());
                  	
                  	for(int k = 0; k < entryIn.getValue().size(); k++){
  						if(k== (entryIn.getValue().size()-1)){
  							buffInner.append(entryIn.getValue().get(k)+" ) ");
  						}
  						else{
  							buffInner.append(entryIn.getValue().get(k)+" , ");
  						}
  					}
                  	buffInner.append("\n");
                  	
              	}
                  
              	}
          		
          	buff.append("\n");
				buff.append("set @id_table_key = NEXT VALUE FOR fast4j_tables_0sq \n ");
				buff.append("\n");
				l++;
				buff.append("insert into fast4j_tables(id_fast4j_tables, function_name, fast4j_key, fast4j_value, table_type, active_sw, create_dt, create_user_id, update_dt, update_user_id) values( ");
          	
          	for(int k = 0; k < entry.getValue().size(); k++){
					if(k== (entry.getValue().size()-1)){
						buff.append(entry.getValue().get(k)+" ) ");
					}
					else{
						buff.append(entry.getValue().get(k)+" , ");
					}
				}
          	
          	buff.append("\n");
  			buff.append(buffInner.toString()+"\n");
          	
  			TestUtility.keyValue.remove(entry.getKey());
  			
          	}
          	/*else{
          		if(TestUtility.keyValueWithCargo.containsKey(entry.getKey())){
  					
  					TestUtility.keyValueWithCargo.get(entry.getKey()).set(5, "'N'");
  					
  					
  					
  				}
          	}*/
          	
          	
          }
			
			
          /*if(l==30)
          	break;*/
      }
		
		
		
		File f = new File(TestUtility.basePath+"fw"+".txt");
		DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
		out.writeBytes(buff.toString());
		out.close();
      
   }
}
