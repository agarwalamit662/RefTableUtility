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


public class test {

	public static HashMap<String, Integer> mapRef;
	public static String fileReadLocation = "";
	public static String fileWriteLocation = "";
	public static void main(String[] args){
		fileWriteLocation = "C:/Users/amitagarwal3/eclipseprojects/RefTableUtility/RefTableUtility/src/com/refTable/readexcel/";
		fileReadLocation = "C:/Users/amitagarwal3/eclipseprojects/RefTableUtility/RefTableUtility/src/com/refTable/readexcel/SummaryType.xlsx";
		mapRef = new HashMap<>();
		Connection conn = null;
        Statement stmt = null;

        try {

            String dbURL = "jdbc:sqlserver://usmumpurchase1:44033";
            String user = "iewp_ee_dev";
            String pass = "iewp_ee_dev";
            conn = DriverManager.getConnection(dbURL, user, pass);
            //System.out.println("Conn "+conn);
            stmt = conn.createStatement();
            String sql = "select NAME,FIELD_ID from RT_field_domain";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                  
            	mapRef.put(rs.getString(1), rs.getInt(2));
            	
            }
            
            /*for (Map.Entry<String, Integer> entry : mapRef.entrySet())
            {
                //System.out.println(entry.getKey() + "/" + entry.getValue());
            }*/
            System.out.println("Before Reading");
            readExcel(fileReadLocation);
            

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
					if(cell.getColumnIndex() == 2){
						//desRpTable = cell.getStringCellValue();
						desRpTable = formatter.formatCellValue(cell);
					}
					
					if(cell.getColumnIndex()==0)
					{
						//////////////////////////////////
						//rpTableName = cell.getStringCellValue();
						rpTableName = formatter.formatCellValue(cell);
						System.out.println("RPTABLENAME: "+rpTableName);
						
						String tempString = "DELETE FROM RT_FIELD_VALUES WHERE REF_TABLE_FIELD_ID "
								+ "IN (SELECT REF_TABLE_FIELD_ID FROM RT_TABLE_FIELD WHERE "
								+ "REF_TABLE_ID IN "
								+ "(SELECT REF_TABLE_ID FROM RT_TABLE WHERE "
								+ "NAME = '"+formatter.formatCellValue(cell)+"' ))\n"
								+ "DELETE FROM RT_TABLE_FIELD WHERE REF_TABLE_ID "
								
								+ "IN (SELECT REF_TABLE_ID FROM RT_TABLE WHERE NAME = '"+formatter.formatCellValue(cell)+"' )\n"
								
								+ "DELETE FROM RT_TABLE WHERE NAME = '"+formatter.formatCellValue(cell)+"'\n"
										
								+ "DECLARE @ref_table_id bigint \n \n";
						
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
						int noOfColumns = 0;
						while (rowIteratorInner.hasNext())
						{
							
							Row rowInner = rowIteratorInner.next();
							//System.out.println("row num Inner::  "+rowInner.getRowNum()+" i value : "+i);
							//For each row, iterate through all the columns
							if(rowInner.getRowNum() == 10){
								matcher = 10;
							}
							if(rowInner.getRowNum() <= 9){
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
							if(i==1){
							while (cellIteratorInner.hasNext())
							{
								
								Cell cellInner = cellIteratorInner.next();
								//System.out.println("Cell Value: "+cellInner.getStringCellValue());
								String cValue = formatter.formatCellValue(cellInner);
								if(cValue != null && cValue.length() > 0){
									noOfColumns++;
									j++;
									bfColumns.append(",@ref_table_field_id_"+String.valueOf(j)+" bigint\n");
									bfColumnsRe.append("set @ref_table_field_id_"+String.valueOf(j)+" = NEXT VALUE FOR RT_TABLE_FIELD_1SQ\n");
									sqlRtTableFieldBuffer.append("INSERT INTO [dbo].[RT_TABLE_FIELD]([REF_TABLE_FIELD_ID],[REF_TABLE_ID],[FIELD_ID],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_"+String.valueOf(j)+",@ref_table_id  ,"+String.valueOf(mapRef.get(cValue.toUpperCase()))+",'Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_TABLE_FIELD_0SQ,'2999-12-31 00:00:00')\n");
									if(!mapRef.containsKey(cValue.toUpperCase())){
										System.out.println("The Ref Table: "+rpTableName+" Column : "+cValue+" is not in the RT Table Field ");
									}
								}
							}
							}
							//System.out.println(noOfColumns);
							boolean toNotInsertRow = false;
							if(i != 1 ){
								
								
								j = 0;
								while (cellIteratorInner.hasNext())
								{
									
									Cell cellInner = cellIteratorInner.next();
									//System.out.println("Cell Value: "+cellInner.getStringCellValue());
									String cValue = formatter.formatCellValue(cellInner);
									
									if(cellInner.getColumnIndex() == 0 && (cValue == null || cValue.length() == 0)){
										toNotInsertRow = true;
										break;
									}
									if((cValue != null && cValue.length() > 0) || j < noOfColumns){
										j++;
										cValue = cValue.replaceAll("'", "''");
										sqlRtTableFieldValuesBuffer.append("INSERT INTO [dbo].[RT_FIELD_VALUES]([REF_TABLE_FIELD_ID],[FIELD_ROW_ID],[FIELD_VALUE],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[ARCHIVE_DT]) VALUES (@ref_table_field_id_"+String.valueOf(j)+" ,@ref_field_row_id_"+String.valueOf(i-1)+" ,'"+cValue+"','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_FIELD_VALUES_0SQ,'2999-12-31 00:00:00')\n");
									}
								}
								if(!toNotInsertRow){
									rowIterBuffer.append(",@ref_field_row_id_"+String.valueOf(i-1)+" bigint\n");
									rowIterBufferInner.append("set @ref_field_row_id_"+String.valueOf(i-1)+"  = NEXT VALUE FOR RT_FIELD_VALUES_1SQ\n");
								}
							}
							if(toNotInsertRow)
								break;
						}
						
						String colBufferStr = bfColumns.toString();
						String rowBufferStr = rowIterBuffer.toString();
						
						String st1 = "set @ref_table_id = NEXT VALUE FOR RT_TABLE_1SQ\n";
						
						String colBufferStrInner = bfColumnsRe.toString();
						String rowBufferStrInner = rowIterBufferInner.toString();
						
						finalStr = tempString+"\n"+colBufferStr+"\n"+rowBufferStr+"\n"+st1+"\n"
								+ colBufferStrInner+"\n"+rowBufferStrInner;
						
						
						
						
						
						//workbook.getSheet(cell.getStringCellValue())
						
					}
					
					
					
					
					
				}
				
				String rtTableString = "INSERT INTO [dbo].[RT_TABLE]([REF_TABLE_ID],[TABLE_ID],[VERSION],[NAME],[DESCRIPTION],[COMMENTS],[STATUS],[CREATE_USER_ID],[UPDATE_USER_ID],[CREATE_DT],[UPDATE_DT],[UNIQUE_TRANS_ID],[EFF_BEGIN_DT],[EFF_END_DT],[ARCHIVE_DT]) VALUES "
						+ "(@ref_table_id ,NEXT VALUE FOR RT_TABLE_3SQ,1,"
						+ "'"
						+ rpTableName
						+ "'"
						+ ",'"
						+ desRpTable.replaceAll("'", "''")
						+ "','"
						+ desRpTable.replaceAll("'", "''")
						+ "','A','Amit.Agarwal','Amit.Agarwal',GETDATE(),GETDATE(),NEXT VALUE FOR RT_TABLE_0SQ,GETDATE(),NULL,'2999-12-31 00:00:00')";
				
				String transStart = "BEGIN TRANSACTION\n\nBEGIN TRY\n\n";
				String transEnd = "END TRY\n\nBEGIN CATCH\n\nselect ERROR_MESSAGE() AS ERRORMESSAGE, ERROR_LINE() AS ErrorLine\nIF @@TRANCOUNT > 0  ROLLBACK TRANSACTION\n\nEND CATCH\n\nIF @@TRANCOUNT >0  COMMIT TRANSACTION\n";
				
				finalStr = transStart+finalStr+"\n"+rtTableString+"\n\n"+sqlRtTableFieldBuffer+"\n\n"+sqlRtTableFieldValuesBuffer+"\n\n"+transEnd;
				
				if(toWrite){
				
					String path = fileWriteLocation;
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
	

