package test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class testsspSelectQuery{

	public static HashMap<String, ArrayList<Integer>> mapRef;
	public static ArrayList<String> notInDB = new ArrayList<>();
	public static ArrayList<String> inDBMultipleRTField = new ArrayList<>();
	public static ArrayList<String> notInSync = new ArrayList<>();
	public static ArrayList<String> inSync = new ArrayList<>();
	
	
	public static void main(String[] args){
		
		mapRef = new HashMap<>();
		Connection conn = null;
        Statement stmt = null;

        try {

            //String dbURL = "jdbc:sqlserver://usmumaxshah4-r:44033";
        	String dbURL = "jdbc:sqlserver://10.12.88.113:44033";
            //10.12.88.113,44033
            String user = "ssp_ee_dev";
            String pass = "ssp_ee_dev";
            conn = DriverManager.getConnection(dbURL, user, pass);
            //System.out.println("Conn "+conn);
            stmt = conn.createStatement();
            String sql = "select LKUP_GRP_CD,LKUP_GRP_FLD_ID from LKUP_GRP_FLD where lang_cd='EN'";
            //select LKUP_GRP_FLD_ID from LKUP_GRP_FLD where lang_cd='EN' and lkup_grp_cd IN ('TASC')
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            while(rs.next()){
                  if(mapRef.containsKey(rs.getString(1))){
                	  tempList = (ArrayList)mapRef.get(rs.getString(1));
                	  tempList.add(tempList.size(), rs.getInt(2));
                	  mapRef.put(rs.getString(1),tempList );
                  }
                  else{
                	  tempList = new ArrayList<Integer>();
                	  tempList.add(rs.getInt(2));
                	  mapRef.put(rs.getString(1),tempList );
                  }
            }
            
           
            
            for (Map.Entry<String, ArrayList<Integer>> entry : mapRef.entrySet())
            {
                if(entry.getValue().size() > 1){
                	System.out.println(entry.getKey() + "/" + entry.getValue());
            	}
            }
            System.out.println("Before Reading");
            readExcel("C:/Users/amitagarwal3/eclipseprojects/RefTableUtility/RefTableUtility/src/com/refTable/readexcel/TestExcelSSP.xlsx");
          //notInDB,inDBMultipleRTField,notInSync,inSync
            System.out.println("\n\n\n\n");
            for(int i = 0 ; i < notInDB.size();i++){
            	System.out.println(notInDB.get(i));
            }
            System.out.println("\n\n\n\n");
            
            for(int i = 0 ; i < inDBMultipleRTField.size();i++){
            	
            	System.out.println(inDBMultipleRTField.get(i));
            
            }
            
            System.out.println("\n\n\n\n");
            
            for(int i = 0 ; i < notInSync.size();i++){
            	
            	System.out.println(notInSync.get(i));
            
            }
            
            System.out.println("\n\n\n\n");
            
            for(int i = 0 ; i < inSync.size();i++){
            	
            	System.out.println(inSync.get(i));
            
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

	public static void readExcel(String fileName){
		
		try
		{
			//System.out.println("In Read Excel");
			System.out.println("Before FileInputStream");
			/*FileInputStream file = new FileInputStream(new File(fileName));
			System.out.println("After FileInputStream");
			//Create Workbook instance holding reference to .xlsx file
			System.out.println("Before XSSFWorkbook");*/
			
			String dbURL = "jdbc:sqlserver://10.12.88.113:44033";
            //10.12.88.113,44033
            String user = "ssp_ee_dev";
            String pass = "ssp_ee_dev";
            Connection conn = DriverManager.getConnection(dbURL, user, pass);
            Statement stmt = conn.createStatement();
			
			File file = new File(fileName);
			OPCPackage opcPackage = OPCPackage.open(file.getPath(),PackageAccess.READ);
			//OPCPackage.open(xlsxFile.getPath(), PackageAccess.READ);
			XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
			
			
			//XSSFWorkbook workbook = new XSSFWorkbook(file);
			System.out.println("After XSSFWorkbook");
			//Get first/desired sheet from the workbook
			System.out.println("Before XSSFSheet");
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			System.out.println("After XSSFSheet");
			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
			
			while (rowIterator.hasNext())
			{
				boolean toWrite = true;
				String finalStr="";
				Row row = rowIterator.next();
				String rpTableName = "";
				String desRpTable = "";
			
				//RT_FIELD_VALUES
				//For each row, iterate through all the columns
				if(row.getRowNum() < 6){
					continue;
				}
				Iterator<Cell> cellIterator = row.cellIterator();
				List<String> strList = null;
				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					if(cell.getColumnIndex() == 3){
						
						desRpTable = formatter.formatCellValue(cell);
					}
					
					if(cell.getColumnIndex()==0)
					{
						//////////////////////////////////
						
						rpTableName = formatter.formatCellValue(cell);
						
						
						if(!mapRef.containsKey(rpTableName)){
							//System.out.println(rpTableName+": This ref table is not currently there in SSP DB BUT PRESENT IN EXCEL");
							//notInDB,inDBMultipleRTField,notInSync,inSync
							notInDB.add(rpTableName+": This ref table is not currently there in SSP DB BUT PRESENT IN EXCEL");
							toWrite = false;
							break;
						}
						
						if(mapRef.containsKey(rpTableName) && mapRef.get(rpTableName).size() > 1){
							//System.out.println(rpTableName+": This ref table has more than one lkup field id currently there in SSP DB");
							//notInDB,inDBMultipleRTField,notInSync,inSync
							inDBMultipleRTField.add(rpTableName+": This ref table has more than one lkup field id currently there in SSP DB");
							//toWrite=false;
							//break;
						}
						
						//Delete from LKUP where LKUP_GRP_FLD_ID = @LKUP_GRP_FLD_ID
						
						XSSFSheet sheetInner = workbook.getSheet(formatter.formatCellValue(cell));
						if(sheetInner == null){
							System.out.println(rpTableName + " This sheet is not there in excel");
							
							toWrite = false;
							break;
						}
						//Iterate through each rows one by one
						Iterator<Row> rowIteratorInner = sheetInner.iterator();
						int i = 0;
						int matcher = 0;
						
						StringBuffer tempBuff = new StringBuffer();
			            tempBuff.append("(");
						for(int k = 0; k < mapRef.get(rpTableName).size();k++){
							if(k == (mapRef.get(rpTableName).size()-1)){
								tempBuff.append(String.valueOf(mapRef.get(rpTableName).get(k)));
							}else{
								tempBuff.append(String.valueOf(mapRef.get(rpTableName).get(k))+",");
							}
							
						}
						//System.out.println("Here");
						tempBuff.append(") ");
								//+ "order by LKUP_GRP_FLD_ID ");
						
						
						String sql = "select count(*) from LKUP where LKUP_GRP_FLD_ID in "+tempBuff.toString()+" AND LKUP_DSC in (";
						//System.out.println("Starting SQL is : "+sql);
						StringBuffer tempBuffer = new StringBuffer();
						while (rowIteratorInner.hasNext())
						{
							
							Row rowInner = rowIteratorInner.next();
							
							if(rowInner.getRowNum() == 12){
								matcher = 12;
							}
							if(rowInner.getRowNum() <= 11){
								continue;
							}
							
							if(matcher == rowInner.getRowNum()){
								
								matcher++;
							} 
							else if(matcher != rowInner.getRowNum()){
								
								break;
							}
							
							Iterator<Cell> cellIteratorInner = rowInner.cellIterator();
							
							int j = 0;
							
							boolean toNotInsertRow = false;
								
							j = 0;
							
							boolean insertReq = false;
							
							while (cellIteratorInner.hasNext() && j < 4)
							{
									
									
									Cell cellInner = cellIteratorInner.next();
									
									String cValue = formatter.formatCellValue(cellInner);
									
									if(cellInner.getColumnIndex() == 3){ 
										if(cValue == null || cValue.length() == 0){
											insertReq=false;
										}
										else{
											tempBuffer.append("'"+cValue.replaceAll("'", "''")+"',");
											insertReq=true;
										}
										
									}
									
									j++;
								}
							
							if(insertReq){	
								i++;
							}
							else{
								break;
							}
							
						}
						
						
						
						sql = sql+tempBuffer.toString();
						//System.out.println(sql);
						sql = sql.substring(0, sql.length()-1);
						sql = sql+" ); ";
						//System.out.println("SQL FOR REFTABLE '"+rpTableName+"' is: "+sql);
						
			            
						
			            StringBuffer sb = new StringBuffer();
			            /*System.out.println("Value of i is : "+i);*/
			            
			            try{
			            	ResultSet rs = stmt.executeQuery(sql);
			            	if(rs.next()){
				            	//System.out.println("Value of rs.getInt(0) is :" +rs.getInt(1) +" : and Value of i is: "+i); 
				            	if(rs.getInt(1) >= i){
				            		//notInDB,inDBMultipleRTField,notInSync,inSync
				            		inSync.add("The Ref Tabel ' "+rpTableName+" ' is in sync with the DB and Excel Both Properly");
				            		//System.out.println("The Ref Tabel ' "+rpTableName+" ' is in sync with the DB and Excel Both Properly" );
				            	}
				            	else{
				            		//notInDB,inDBMultipleRTField,notInSync,inSync
				            		notInSync.add("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly");
				            		//System.out.println("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly" );
				            	}
				            	
				            }
				            else{
				            	notInSync.add("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly");
				            	//System.out.println("Ref Tabel ' "+rpTableName+" ' having issue. Check manually for safety" );
				            }
			            }
			            catch(SQLException e){
			            	//notInDB,inDBMultipleRTField,notInSync,inSync
			            	notInSync.add("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly");
			            	//System.out.println("SQL EXCEPTION FOR THIS TABLE: ' "+rpTableName+" ' having issue. Check manually for safety" );
			            }
			            catch(Exception e){
			            	notInSync.add("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly");
			            	//System.out.println("EXCEPTION FOR THIS TABLE: ' "+rpTableName+" ' having issue. Check manually for safety" );
			            }
			            
			            /*if(rs.next()){
			            	System.out.println("Value of rs.getInt(0) is :" +rs.getInt(1)); 
			            	if(rs.getInt(1) >= i){
			            		System.out.println("The Ref Tabel ' "+rpTableName+" ' is in sync with the DB and Excel Both Properly" );
			            	}
			            	else{
			            		System.out.println("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly" );
			            	}
			            	
			            }
			            else{
			            	System.out.println("Ref Tabel ' "+rpTableName+" ' having issue. Check manually for safety" );
			            }*/
			            
			            
						
					}
					
				}
			
		}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
		
}
	

