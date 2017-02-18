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

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class delta{

	public static HashMap<String, ArrayList<Integer>> mapRef;
	public static HashMap<String, ArrayList<Integer>> mapRefES;
	public static HashMap<String, ArrayList<Integer>> mapRefVT;
	public static ArrayList<String> loggers = new ArrayList<>();
	public static void main(String[] args){
		
		mapRef = new HashMap<>();
		mapRefES = new HashMap<>();
		mapRefVT = new HashMap<>();
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
            readExcel("C:/Users/amitagarwal3/eclipseprojects/RefTableUtility/RefTableUtility/src/com/refTable/readexcel/TestExcelSSP.xlsx",conn,stmt);
            

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

	public static void readExcel(String fileName,Connection conn,Statement stmt){
		
		try
		{
			//System.out.println("In Read Excel");
			System.out.println("Before FileInputStream");
			StringBuffer finalLoggers = new StringBuffer();
			/*FileInputStream file = new FileInputStream(new File(fileName));
			System.out.println("After FileInputStream");
			//Create Workbook instance holding reference to .xlsx file
			System.out.println("Before XSSFWorkbook");*/
			
			File file = new File(fileName);
			
			FileInputStream inputStream = new FileInputStream(file);
			//OPCPackage opcPackage = OPCPackage.open(file.getPath());
			OPCPackage opcPackage = OPCPackage.open(inputStream);
			//OPCPackage.open(xlsxFile.getPath(), PackageAccess.READ);
			XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
			
			/*org.apache.poi.openxml4j.opc.OPCPackage opc = org.apache.poi.openxml4j.opc.OPCPackage.open(filename); 
			org.apache.poi.xssf.usermodel.XSSFWorkbook wb = new org.apache.poi.xssf.usermodel.XSSFWorkbook(opc);*/ 
			
			
			//Workbook workbook = WorkbookFactory.create(new FileInputStream(file.getPath()));
			//Workbook wb = WorkbookFactory.create(new FileInputStream(filePath));
			
			//XSSFWorkbook workbook = new XSSFWorkbook(file);
			System.out.println("After XSSFWorkbook");
			//Get first/desired sheet from the workbook
			System.out.println("Before XSSFSheet");
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			System.out.println("After XSSFSheet");
			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
			int count = 0;
			while (rowIterator.hasNext())
			{
				//count++;
				boolean toWrite = true;
				String finalStr="";
				Row row = rowIterator.next();
				String rpTableName = "";
				String desRpTable = "";
				//System.out.println("row num ::  "+row.getRowNum());
				StringBuffer sqlRtTableFieldBuffer = new StringBuffer();
				StringBuffer sqlRtTableFieldValuesBuffer = new StringBuffer();
				//RT_FIELD_VALUES
				//For each row, iterate through all the columns
				if(row.getRowNum() < 5){
					continue;
				}
				count++;
				/*if(row.getRowNum() > 7)
					break;*/
				Iterator<Cell> cellIterator = row.cellIterator();
				List<String> strList = null;
				
				
				
				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					//Check the cell type and format accordingly
					//System.out.println("cell.getColumnIndex() ::  "+cell.getColumnIndex());
					//System.out.println(cell.getStringCellValue());
					/*if(cell.getColumnIndex() == 3){
						//desRpTable = cell.getStringCellValue();
						desRpTable = formatter.formatCellValue(cell);
					}*/
					
					
					
					if(cell.getColumnIndex()==0)
					{
						boolean here = false;
						//////////////////////////////////
						//rpTableName = cell.getStringCellValue();
						rpTableName = formatter.formatCellValue(cell);
						//System.out.println("RPTABLENAME: "+rpTableName);
						
						if(!mapRef.containsKey(rpTableName)){
							System.out.println(rpTableName+": This ref table is not currently there in SSP DB BUT PRESENT IN EXCEL");
							finalLoggers.append(rpTableName+": This ref table is not currently there in SSP DB BUT PRESENT IN EXCEL");
							finalLoggers.append("\n");
							toWrite = false;
							loggers.add(count-1, rpTableName+": This ref table is not currently there in SSP DB BUT PRESENT IN EXCEL");
							here = true;
							/*XSSFRow rowhead = sheet.createRow((short) matcher++);
							rowhead.createCell(0).setCellValue("CD");
							rowhead.createCell(1).setCellValue("ACTIVE");
							rowhead.createCell(2).setCellValue("SORT");
							rowhead.createCell(3).setCellValue("ENGLISH DESCRIPTION");*/
							
							break;
						}
						
						if(mapRef.containsKey(rpTableName) && mapRef.get(rpTableName).size() > 1){
							System.out.println(rpTableName+": This ref table has more than one lkup field id currently there in SSP DB");
							finalLoggers.append(rpTableName+": This ref table has more than one lkup field id currently there in SSP DB");
							finalLoggers.append("\n");
							loggers.add(count-1, rpTableName+": This ref table has more than one lkup field id currently there in SSP DB. Needs Verification ");
							here = true;
							toWrite=false;
							//break;
						}
						
						//Delete from LKUP where LKUP_GRP_FLD_ID = @LKUP_GRP_FLD_ID
						
						XSSFSheet sheetInner = workbook.getSheet(formatter.formatCellValue(cell));
						if(sheetInner == null){
							System.out.println(rpTableName + " This sheet is not there in excel");
							finalLoggers.append(rpTableName + " This sheet is not there in excel");
							finalLoggers.append("\n");
							loggers.add(count-1, rpTableName + " This sheet is not there in excel");
							toWrite = false;
							here = true;
							break;
						}
						
						if(!here){
							loggers.add(count-1, "");
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
						
						String sql = "select count(*) from LKUP where LKUP_GRP_FLD_ID in "+tempBuff.toString()+" AND ( ";
						//System.out.println("Starting SQL is : "+sql);
						StringBuffer tempBuffer = new StringBuffer();
						StringBuffer tempBufferCD = new StringBuffer();
						StringBuffer tempBufferAct = new StringBuffer();
						StringBuffer tempBufferSort = new StringBuffer();
						while (rowIteratorInner.hasNext())
						{
							
							Row rowInner = rowIteratorInner.next();
							//System.out.println("row num Inner::  "+rowInner.getRowNum()+" i value : "+i);
							//For each row, iterate through all the columns
							if(rowInner.getRowNum() == 5){
								matcher = 5;
							}
							if(rowInner.getRowNum() <= 4){
								continue;
							}
							//System.out.println("Matcher: "+matcher);
							if(matcher == rowInner.getRowNum()){
								//System.out.println("In if");
								matcher++;
							} 
							else if(matcher != rowInner.getRowNum()){
								//System.out.println("In else");
								break;
							}
							/*i++;*/
							Iterator<Cell> cellIteratorInner = rowInner.cellIterator();
							
							int j = 0;
							
							boolean toNotInsertRow = false;
								
							j = 0;
							//INSERT INTO [LKUP]([LKUP_GRP_FLD_ID],[LKUP_CD],[CD_ACTV_FLG],[SORT_ORD],[UPDT_DT],[LKUP_DSC]) 
							//VALUES (@LKUP_GRP_FLD_ID[i],'ER', 'Y', 1, GETDATE(), N'Error')
							
							
							
							boolean insertReq = true;
							
							
							
							while (cellIteratorInner.hasNext() && j < 4)
							{
									
								//(LKUP_DSC = 'American Indian/Alaska Native' AND LKUP_CD = 'NAT')
									Cell cellInner = cellIteratorInner.next();
									//System.out.println("Cell Value: "+cellInner.getStringCellValue());
									String cValue = formatter.formatCellValue(cellInner);
									if(cellInner.getColumnIndex() == 0){ 
										if(cValue == null || cValue.length() == 0){
											//sqlRtTableFieldValuesBuffer.append("'L"+String.valueOf(i)+"',");
											insertReq = false;
											
										}
										else{
											//sqlRtTableFieldValuesBuffer.append("'"+cValue+"',");
											tempBuffer.append(" ( LKUP_CD = '"+cValue.trim().replaceAll("'", "''")+"' AND ");
											insertReq=true;
										}
										
									}
									if(cellInner.getColumnIndex() == 1){ 
										if(cValue == null || cValue.length() == 0){
											insertReq = false;
											
										}
										else{
											//tempBuffer.append(" (LKUP_DSC = '"+cValue.replaceAll("'", "''")+"' AND ");
											
											tempBuffer.append(" CD_ACTV_FLG = '"+cValue.trim().replaceAll("'", "''")+"' AND ");
											insertReq=true;
											
										}
										
									}
									
									
									if(cellInner.getColumnIndex() == 2){ 
										if(cValue == null || cValue.length() == 0){
											insertReq=false;
										}
										else{
											//tempBufferSort.append(""+cValue.replaceAll("'", "''")+",");
											
											tempBuffer.append(" SORT_ORD = "+cValue.trim().replaceAll("'", "''")+" AND ");
											
											insertReq=true;
										}
										
									}
									
									if(cellInner.getColumnIndex() == 3){ 
										if(cValue == null || cValue.length() == 0){
											insertReq=false;
										}
										else{
											
											tempBuffer.append(" LKUP_DSC = '"+cValue.trim().replaceAll("'", "''")+"' ) OR");
											
											//tempBuffer.append("'"+cValue.replaceAll("'", "''")+"',");
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
						
						//sql = sql+tempBuffer.toString();
						String lkupdsc = tempBuffer.toString().substring(0, tempBuffer.toString().length()-2)+" );";
						/*String sort = tempBufferSort.toString().substring(0, tempBufferSort.toString().length()-1)+" ) AND CD_ACTV_FLG IN ( ";
						String active = tempBufferAct.toString().substring(0, tempBufferAct.toString().length()-1)+" ) AND LKUP_CD IN ( ";
						String code = tempBufferCD.toString().substring(0, tempBufferCD.toString().length()-1)+" );";*/
						//System.out.println(sql);
						sql = sql+lkupdsc;
						finalLoggers.append(sql);
						finalLoggers.append("\n");
						System.out.println(sql);
						
						/*sql = sql.substring(0, sql.length()-1);
						sql = sql+" ); ";*/
						//System.out.println("SQL FOR REFTABLE '"+rpTableName+"' is: "+sql);
						
			            
						
			            StringBuffer sb = new StringBuffer();
			            /*System.out.println("Value of i is : "+i);*/
			            
			            try{
			            	ResultSet rs = stmt.executeQuery(sql);
			            	if(rs.next()){
				            	//System.out.println("Value of rs.getInt(0) is :" +rs.getInt(1) +" : and Value of i is: "+i); 
				            	if(rs.getInt(1) >= i){
				            		//notInDB,inDBMultipleRTField,notInSync,inSync
				            		//inSync.add("The Ref Tabel ' "+rpTableName+" ' is in sync with the DB and Excel Both Properly");
				            		loggers.add(count-1, "Synced");
				            		System.out.println("The Ref Tabel ' "+rpTableName+" ' is in sync with the DB and Excel Both Properly" );
				            		finalLoggers.append("The Ref Tabel ' "+rpTableName+" ' is in sync with the DB and Excel Both Properly");
									finalLoggers.append("\n");
				            	}
				            	else{
				            		//notInDB,inDBMultipleRTField,notInSync,inSync
				            		//notInSync.add("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly");
				            		loggers.add(count-1, "Discrepencies");
				            		System.out.println("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly" );
				            		finalLoggers.append("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly");
									finalLoggers.append("\n");
				            	}
				            	
				            }
				            else{
				            	loggers.add(count-1, "Discrepencies");
				            	//notInSync.add("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly");
				            	System.out.println("Ref Tabel ' "+rpTableName+" ' having issue. Check manually for safety" );
				            	finalLoggers.append("Ref Tabel ' "+rpTableName+" ' having issue. Check manually for safety");
								finalLoggers.append("\n");
				            }
			            }
			            catch(SQLException e){
			            	loggers.add(count-1, "Discrepencies");
			            	//notInDB,inDBMultipleRTField,notInSync,inSync
			            	//notInSync.add("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly");
			            	System.out.println("SQL EXCEPTION FOR THIS TABLE: ' "+rpTableName+" ' having issue. Check manually for safety" );
			            	finalLoggers.append("SQL EXCEPTION FOR THIS TABLE: ' "+rpTableName+" ' having issue. Check manually for safety");
							finalLoggers.append("\n");
			            }
			            catch(Exception e){
			            	loggers.add(count-1, "Discrepencies");
			            	//notInSync.add("Ref Tabel ' "+rpTableName+" ' is Not in sync with the DB and Excel Both Properly");
			            	System.out.println("EXCEPTION FOR THIS TABLE: ' "+rpTableName+" ' having issue. Check manually for safety" );
			            	
			            	finalLoggers.append("EXCEPTION FOR THIS TABLE: ' "+rpTableName+" ' having issue. Check manually for safety");
							finalLoggers.append("\n");
			            	
			            }
						
						
						
						// write idhar
						matcher = matcher+2;
						if(toWrite){
						XSSFRow rowhead = sheetInner.createRow((short) matcher++);
						rowhead.createCell(0).setCellValue("CD");
						rowhead.createCell(1).setCellValue("ACTIVE");
						rowhead.createCell(2).setCellValue("SORT");
						rowhead.createCell(3).setCellValue("ENGLISH DESCRIPTION");
						
						
						StringBuffer tempBuffs = new StringBuffer();
			            tempBuffs.append("(");
						for(int l = 0; l < mapRef.get(rpTableName).size();l++){
							if(l == (mapRef.get(rpTableName).size()-1)){
								tempBuffs.append(String.valueOf(mapRef.get(rpTableName).get(l)));
							}else{
								tempBuffs.append(String.valueOf(mapRef.get(rpTableName).get(l))+",");
							}
							
						}
						//System.out.println("Here");
						tempBuffs.append(") order by LKUP_GRP_FLD_ID ;");
						
						
						String sqls = "select * from LKUP where LKUP_GRP_FLD_ID in "+tempBuff.toString();
			            //select LKUP_GRP_FLD_ID from LKUP_GRP_FLD where lang_cd='EN' and lkup_grp_cd IN ('TASC')
			            ResultSet rs = stmt.executeQuery(sqls);
			            StringBuffer sbs = new StringBuffer();
			            
			            //INSERT INTO [LKUP]([LKUP_GRP_FLD_ID],[LKUP_CD],[CD_ACTV_FLG],[SORT_ORD],[LKUP_DSC],[UPDT_DT]) values(
						while(rs.next()){
							/*System.out.println("HERE2");*/
							/*System.out.println("HERE 3"+rs.getInt("LKUP_GRP_FLD_ID"));*/
							
							XSSFRow rowin = sheetInner.createRow((short) matcher++);
							rowin.createCell(0).setCellValue(rs.getString("LKUP_CD"));
							rowin.createCell(1).setCellValue(rs.getString("CD_ACTV_FLG"));
							rowin.createCell(2).setCellValue(String.valueOf(rs.getInt("SORT_ORD")));
							rowin.createCell(3).setCellValue(rs.getString("LKUP_DSC"));
							
						}
							// if toWriteEnd
						}
						

						
						
					}
					
					
					
					
					
				}
				
				/*XSSFRow rowhead = sheetInner.createRow((short) matcher++);
				rowhead.createCell(0).setCellValue("CD");
				
				XSSFCell */
				//rowhead.createCell(0).setCellValue("CD");
				row.createCell(1).setCellValue(loggers.get(count-1));
				/*if(cell.getColumnIndex()==1){
					cell.setCellValue(loggers.get(count-1));
				}*/
				
				
				
				/*if(toWrite){
					
					String path = "C:/Users/amitagarwal3/eclipseprojects/RefTableUtility/RefTableUtility/src/com/refTable/readexcel/";
					File f = new File(path+rpTableName+".sql");
					DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
					out.writeBytes(finalStr);
					out.close();
				}*/
			
		}
			
			FileOutputStream fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
			opcPackage.close();
			fileOut.close();

			/*java.io.FileOutputStream fileOut = new java.io.FileOutputStream(filename); 
			wb.write(fileOut); opc.close(); fileOut.close();
			*/
			
			//file.close();
			
			String path = "C:/Users/amitagarwal3/eclipseprojects/RefTableUtility/RefTableUtility/src/com/refTable/readexcel/";
			File f = new File(path+"loggers"+".txt");
			DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
			out.writeBytes(finalLoggers.toString());
			out.close();
			
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
		
}
	

