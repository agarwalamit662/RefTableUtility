package com.refTable.mukesh;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;

public class TestUtility {
	
	public static String basePath = "C:/Users/amitagarwal3/eclipseprojects/RefTableUtility/RefTableUtility/src/com/refTable/mukesh/";
	public static Map<String, ArrayList<String>> keyValue = new HashMap<>();
	public static Map<String, ArrayList<String>> keyValueWithCargo = new HashMap<>();
	public static Map<String, ArrayList<String>> keyValueWithCargoFW = new HashMap<>();
	public static Map<String, ArrayList<String>> keyValueWithCargoIE = new HashMap<>();
	
	public static void main(String[] args) throws IOException, JSONException {
		// TODO Auto-generated method stub

		File fin = new File(basePath+"fast4jCustomDAOsList.properties");
		FileInputStream fis = new FileInputStream(fin);
		 
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	 /* 
	  * 
	  * insert into fast4j_tables(id_fast4j_tables, function_name, 
	  * fast4j_key, fast4j_value, table_type, active_sw, create_dt, 
	  * create_user_id, 
	  * update_dt, update_user_id) 
	  * values(@id_DcCaseCardholderDAO, 
	  * 'Common', 
	  * 'gov.state.nextgen.common.cargo.custom.DcCaseCardholder', 
	  * 'gov.state.nextgen.common.dao.custom.DcCaseCardholderDAO', 
	  * 2,
	  *  'Y', 
	  *  current_timestamp, 
	  *  'Amit.Agrawal', 
	  *  current_timestamp, 
	  *  'Amit.Agrawal')
	  * 
	  * */
		String line = null;
		while ((line = br.readLine()) != null) {
			if(line != null && line.trim().length() > 0 && !line.contains("#")){
				String[] arr = line.split("=");
				ArrayList<String> dbCols = new ArrayList<>(10);
				dbCols.add("@id_table_key");
				dbCols.add("'Common'");
				dbCols.add("'"+arr[0].trim().toString()+"'");
				dbCols.add("'"+arr[1].trim().toString()+"'");
				dbCols.add("0");
				dbCols.add("'Y'");
				dbCols.add("current_timestamp");
				dbCols.add("'Amit.Agrawal'");
				dbCols.add("current_timestamp");
				dbCols.add("'Amit.Agrawal'");
				
				ArrayList<String> dbColsFW = new ArrayList<>(10);
				dbColsFW.add("@id_table_key");
				dbColsFW.add("'FW'");
				dbColsFW.add("'"+arr[0].trim().toString()+"'");
				dbColsFW.add("'"+arr[1].trim().toString()+"'");
				dbColsFW.add("0");
				dbColsFW.add("'Y'");
				dbColsFW.add("current_timestamp");
				dbColsFW.add("'Amit.Agrawal'");
				dbColsFW.add("current_timestamp");
				dbColsFW.add("'Amit.Agrawal'");
				
				
				ArrayList<String> dbColsIE = new ArrayList<>(10);
				dbColsIE.add("@id_table_key");
				dbColsIE.add("'IE'");
				dbColsIE.add("'"+arr[0].trim().toString()+"'");
				dbColsIE.add("'"+arr[1].trim().toString()+"'");
				dbColsIE.add("0");
				dbColsIE.add("'Y'");
				dbColsIE.add("current_timestamp");
				dbColsIE.add("'Amit.Agrawal'");
				dbColsIE.add("current_timestamp");
				dbColsIE.add("'Amit.Agrawal'");
				
				keyValue.put(arr[0].trim().toString()+"Cargo", dbCols);
				keyValueWithCargo.put(arr[0].trim().toString()+"Cargo", dbCols);
				keyValueWithCargoFW.put(arr[0].trim().toString()+"Cargo", dbColsFW);
				keyValueWithCargoIE.put(arr[0].trim().toString()+"Cargo", dbColsIE);
			}
		}
	 
		br.close();
		
		//System.out.println(keyValue);
		
		/*PropertyMapper mp = new PropertyMapper();
		FwPropertyMapper fmp = new FwPropertyMapper();
		IePropertyMapper imp = new IePropertyMapper();*/
		
		PropertyMapperXML mp = new PropertyMapperXML();
		FwPropertyMapperXML fmp = new FwPropertyMapperXML();
		IePropertyMapperXML imp = new IePropertyMapperXML();
		
		StringBuffer buff = new StringBuffer();
		
		buff.append("DECLARE @id_table_key bigint\n ");
		
		
    	
    	for(Map.Entry<String, ArrayList<String>> entryIn : keyValue.entrySet()){
			
    		buff.append("set @id_table_key = NEXT VALUE FOR fast4j_tables_0sq \n ");
    		buff.append("\n");
    		buff.append("insert into fast4j_tables(id_fast4j_tables, function_name, fast4j_key, fast4j_value, table_type, active_sw, create_dt, create_user_id, update_dt, update_user_id) values( ");
    		
    		for(int k = 0; k < entryIn.getValue().size(); k++){
				if(k== (entryIn.getValue().size()-1)){
					buff.append(entryIn.getValue().get(k)+" ) ");
				}
				else{
					buff.append(entryIn.getValue().get(k)+" , ");
				}
			}
        	
        	buff.append("\n");
			
    		
		}
    	
    	//String path = "C:/Users/amitagarwal3/eclipseprojects/RefTableUtility/RefTableUtility/src/com/refTable/mukesh/";
		File f = new File(basePath+"noentries"+".txt");
		DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
		out.writeBytes(buff.toString());
		out.close();
    	
	}

}
