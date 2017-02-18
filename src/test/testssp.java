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


public class testssp{

	public static HashMap<String, ArrayList<Integer>> mapRef;
	public static HashMap<String, ArrayList<Integer>> mapRefES;
	public static HashMap<String, ArrayList<Integer>> mapRefVT;
	
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
            
            sql = "select LKUP_GRP_CD,LKUP_GRP_FLD_ID from LKUP_GRP_FLD where lang_cd='ES'";
            //select LKUP_GRP_FLD_ID from LKUP_GRP_FLD where lang_cd='EN' and lkup_grp_cd IN ('TASC')
            rs = stmt.executeQuery(sql);
            tempList = new ArrayList<Integer>();
            while(rs.next()){
                  if(mapRefES.containsKey(rs.getString(1))){
                	  tempList = (ArrayList)mapRefES.get(rs.getString(1));
                	  tempList.add(tempList.size(), rs.getInt(2));
                	  mapRefES.put(rs.getString(1),tempList );
                  }
                  else{
                	  tempList = new ArrayList<Integer>();
                	  tempList.add(rs.getInt(2));
                	  mapRefES.put(rs.getString(1),tempList );
                  }
            }
            
            sql = "select LKUP_GRP_CD,LKUP_GRP_FLD_ID from LKUP_GRP_FLD where lang_cd='VT'";
            //select LKUP_GRP_FLD_ID from LKUP_GRP_FLD where lang_cd='EN' and lkup_grp_cd IN ('TASC')
            rs = stmt.executeQuery(sql);
            tempList = new ArrayList<Integer>();
            while(rs.next()){
                  if(mapRefVT.containsKey(rs.getString(1))){
                	  tempList = (ArrayList)mapRefVT.get(rs.getString(1));
                	  tempList.add(tempList.size(), rs.getInt(2));
                	  mapRefVT.put(rs.getString(1),tempList );
                  }
                  else{
                	  tempList = new ArrayList<Integer>();
                	  tempList.add(rs.getInt(2));
                	  mapRefVT.put(rs.getString(1),tempList );
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
				//System.out.println("row num ::  "+row.getRowNum());
				StringBuffer sqlRtTableFieldBuffer = new StringBuffer();
				StringBuffer sqlRtTableFieldValuesBuffer = new StringBuffer();
				//RT_FIELD_VALUES
				//For each row, iterate through all the columns
				if(row.getRowNum() < 6){
					continue;
				}
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
					if(cell.getColumnIndex() == 3){
						//desRpTable = cell.getStringCellValue();
						desRpTable = formatter.formatCellValue(cell);
					}
					
					if(cell.getColumnIndex()==0)
					{
						//////////////////////////////////
						//rpTableName = cell.getStringCellValue();
						rpTableName = formatter.formatCellValue(cell);
						//System.out.println("RPTABLENAME: "+rpTableName);
						
						if(!mapRef.containsKey(rpTableName)){
							System.out.println(rpTableName+": This ref table is not currently there in SSP DB BUT PRESENT IN EXCEL");
							toWrite = false;
							break;
						}
						
						if(mapRef.containsKey(rpTableName) && mapRef.get(rpTableName).size() > 1){
							System.out.println(rpTableName+": This ref table has more than one lkup field id currently there in SSP DB");
							toWrite=false;
							break;
						}
						
						//Delete from LKUP where LKUP_GRP_FLD_ID = @LKUP_GRP_FLD_ID
						
						String tempString = "Delete from LKUP where LKUP_GRP_FLD_ID = "+String.valueOf(mapRef.get(rpTableName).get(0))+" ;";
						String tempStringES = "Delete from LKUP where LKUP_GRP_FLD_ID = "+String.valueOf(mapRefES.get(rpTableName).get(0))+" ;";		
						String tempStringVT = "Delete from LKUP where LKUP_GRP_FLD_ID = "+String.valueOf(mapRefVT.get(rpTableName).get(0))+" ;";
						XSSFSheet sheetInner = workbook.getSheet(formatter.formatCellValue(cell));
						StringBuffer rowIterBuffer = new StringBuffer();
						StringBuffer rowIterBufferInner = new StringBuffer();
						StringBuffer bfColumns = new StringBuffer();
						StringBuffer bfColumnsRe = new StringBuffer();
						if(sheetInner == null){
							System.out.println(rpTableName + " This sheet is not there in excel");
							toWrite = false;
							break;
						}
						//Iterate through each rows one by one
						Iterator<Row> rowIteratorInner = sheetInner.iterator();
						int i = 0;
						int matcher = 0; 
						while (rowIteratorInner.hasNext())
						{
							
							Row rowInner = rowIteratorInner.next();
							//System.out.println("row num Inner::  "+rowInner.getRowNum()+" i value : "+i);
							//For each row, iterate through all the columns
							if(rowInner.getRowNum() == 12){
								matcher = 12;
							}
							if(rowInner.getRowNum() <= 11){
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
							i++;
							Iterator<Cell> cellIteratorInner = rowInner.cellIterator();
							
							int j = 0;
							
							boolean toNotInsertRow = false;
								
							j = 0;
							//INSERT INTO [LKUP]([LKUP_GRP_FLD_ID],[LKUP_CD],[CD_ACTV_FLG],[SORT_ORD],[UPDT_DT],[LKUP_DSC]) 
							//VALUES (@LKUP_GRP_FLD_ID[i],'ER', 'Y', 1, GETDATE(), N'Error')
							
							StringBuffer tempBuffer = new StringBuffer();
							StringBuffer tempBufferES = new StringBuffer();
							StringBuffer tempBufferVT = new StringBuffer();
							boolean insertReq = true;
							tempBuffer.append("INSERT INTO [LKUP]([LKUP_GRP_FLD_ID],[LKUP_CD],[CD_ACTV_FLG],[SORT_ORD],[LKUP_DSC],[UPDT_DT]) values(");
							tempBuffer.append(String.valueOf(mapRef.get(rpTableName).get(0))+",");
							
							tempBufferES.append("INSERT INTO [LKUP]([LKUP_GRP_FLD_ID],[LKUP_CD],[CD_ACTV_FLG],[SORT_ORD],[LKUP_DSC],[UPDT_DT]) values(");
							tempBufferES.append(String.valueOf(mapRefES.get(rpTableName).get(0))+",");
							
							tempBufferVT.append("INSERT INTO [LKUP]([LKUP_GRP_FLD_ID],[LKUP_CD],[CD_ACTV_FLG],[SORT_ORD],[LKUP_DSC],[UPDT_DT]) values(");
							tempBufferVT.append(String.valueOf(mapRefVT.get(rpTableName).get(0))+",");
							
							/*sqlRtTableFieldValuesBuffer.append("INSERT INTO [LKUP]([LKUP_GRP_FLD_ID],[LKUP_CD],[CD_ACTV_FLG],[SORT_ORD],[UPDT_DT],[LKUP_DSC]) values(");
							sqlRtTableFieldValuesBuffer.append(String.valueOf(mapRef.get(rpTableName).get(0))+",");*/
							while (cellIteratorInner.hasNext() && j < 4)
							{
									
									
									Cell cellInner = cellIteratorInner.next();
									//System.out.println("Cell Value: "+cellInner.getStringCellValue());
									String cValue = formatter.formatCellValue(cellInner);
									if(cellInner.getColumnIndex() == 0){ 
										if(cValue == null || cValue.length() == 0){
											//sqlRtTableFieldValuesBuffer.append("'L"+String.valueOf(i)+"',");
											insertReq = false;
											tempBuffer.append("'L"+String.valueOf(i)+"',");
											tempBufferES.append("'L"+String.valueOf(i)+"',");
											tempBufferVT.append("'L"+String.valueOf(i)+"',");
										}
										else{
											//sqlRtTableFieldValuesBuffer.append("'"+cValue+"',");
											tempBuffer.append("'"+cValue+"',");
											tempBufferES.append("'"+cValue+"',");
											tempBufferVT.append("'"+cValue+"',");
											insertReq=true;
										}
										
									}
									if(cellInner.getColumnIndex() == 1){ 
										if(cValue == null || cValue.length() == 0){
											insertReq = false;
											//sqlRtTableFieldValuesBuffer.append("'A"+String.valueOf(i)+"',");
											tempBuffer.append("'A"+String.valueOf(i)+"',");
											tempBufferES.append("'A"+String.valueOf(i)+"',");
											tempBufferVT.append("'A"+String.valueOf(i)+"',");
										}
										else{
											insertReq=true;
											//sqlRtTableFieldValuesBuffer.append("'"+cValue+"',");
											tempBuffer.append("'"+cValue+"',");
											tempBufferES.append("'"+cValue+"',");
											tempBufferVT.append("'"+cValue+"',");
										}
										
									}
									
									
									if(cellInner.getColumnIndex() == 2){ 
										if(cValue == null || cValue.length() == 0){
											//sqlRtTableFieldValuesBuffer.append("'S"+String.valueOf(i)+"',");
											tempBuffer.append(String.valueOf(9000+i)+",");
											tempBufferES.append(String.valueOf(9000+i)+",");
											tempBufferVT.append(String.valueOf(9000+i)+",");
											insertReq=false;
										}
										else{
											//sqlRtTableFieldValuesBuffer.append(cValue+",");
											tempBuffer.append(cValue+",");
											tempBufferES.append(cValue+",");
											tempBufferVT.append(cValue+",");
											insertReq=true;
										}
										
									}
									
									if(cellInner.getColumnIndex() == 3){ 
										if(cValue == null || cValue.length() == 0){
											//sqlRtTableFieldValuesBuffer.append("'S"+String.valueOf(i)+"',");
											tempBuffer.append("N'"+"NO DESC"+"',");
											tempBufferES.append("N'"+"<ES>NO DESC"+"',");
											tempBufferVT.append("N'"+"<VT>NO DESC"+"',");
											insertReq=false;
										}
										else{
											//sqlRtTableFieldValuesBuffer.append(cValue+",");
											tempBuffer.append("N'"+cValue+"',");
											tempBufferES.append("N'<ES>"+cValue+"',");
											tempBufferVT.append("N'<VT>"+cValue+"',");
											insertReq=true;
										}
										
									}
									
									j++;
								}
							tempBuffer.append("GETDATE());\n");
							tempBufferES.append("GETDATE());\n");
							tempBufferVT.append("GETDATE());\n");
							if(insertReq){	
								sqlRtTableFieldValuesBuffer.append(tempBuffer.toString());
								sqlRtTableFieldValuesBuffer.append(tempBufferES.toString());
								sqlRtTableFieldValuesBuffer.append(tempBufferVT.toString());
							}
							else{
								break;
							}
							
							}
						
						/*String colBufferStr = bfColumns.toString();
						String rowBufferStr = rowIterBuffer.toString();
						
						String st1 = "set @ref_table_id = NEXT VALUE FOR RT_TABLE_1SQ\n";
						
						String colBufferStrInner = bfColumnsRe.toString();
						String rowBufferStrInner = rowIterBufferInner.toString();
						
						finalStr = tempString+"\n"+colBufferStr+"\n"+rowBufferStr+"\n"+st1+"\n"
								+ colBufferStrInner+"\n"+rowBufferStrInner;*/
						
						finalStr = tempString+"\n"+tempStringES+"\n"+tempStringVT+"\n";
						
						
						
						//workbook.getSheet(cell.getStringCellValue())
						
					}
					
					
					
					
					
				}
				
				/*String rtTableString = "INSERT INTO [dbo].[RT_TABLE]([REF_TABLE_ID],[TABLE_ID],[VERSION],[NAME],[DESCRIPTION],[COMMENTS],[STATUS],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[EFF_BEGIN_DT],[EFF_END_DT],[ARCHIVE_DT]) VALUES "
						+ "(@ref_table_id ,NEXT VALUE FOR RT_TABLE_3SQ,1,"
						+ "'"
						+ rpTableName
						+ "'"
						+ ",'"
						+ desRpTable
						+ "','"
						+ desRpTable
						+ "','A','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_TABLE_0SQ,GETDATE(),NULL,'2999-12-31 00:00:00')";*/
				
				String transStart = "BEGIN TRANSACTION\n\nBEGIN TRY\n\n";
				String transEnd = "END TRY\n\nBEGIN CATCH\n\nselect ERROR_MESSAGE() AS ERRORMESSAGE, ERROR_LINE() AS ErrorLine\nIF @@TRANCOUNT > 0  ROLLBACK TRANSACTION\n\nEND CATCH\n\nIF @@TRANCOUNT >0  COMMIT TRANSACTION\n";
				
				//finalStr = transStart+finalStr+"\n"+rtTableString+"\n\n"+sqlRtTableFieldBuffer+"\n\n"+sqlRtTableFieldValuesBuffer+"\n\n"+transEnd;
				finalStr = transStart+"\n"+finalStr+"\n"+sqlRtTableFieldValuesBuffer.toString()+"\n\n"+transEnd;
				if(toWrite){
				
					String path = "C:/Users/amitagarwal3/eclipseprojects/RefTableUtility/RefTableUtility/src/com/refTable/readexcel/";
					File f = new File(path+rpTableName+".sql");
					DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
					out.writeBytes(finalStr);
					out.close();
				}
			
		}
			
			//file.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
		
}
	

