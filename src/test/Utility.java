package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Utility {

	public static HashMap<String, Integer> mapRef;
	
	public static void main(String[] args){
		
		
		
		mapRef = new HashMap<>();
		Connection conn = null;
        Statement stmt = null;

        try {

            // Change this DB Url When Needed URL AND PORT ONLY
        	String dbURL = "jdbc:sqlserver://10.12.88.113:44033";
        	//String dbURL = "jdbc:sqlserver://usmumpurchase3:9080";
           // String dbURL = "jdbc:sqlserver://localhost:53344";
            // DBNAME
        	String user = "ssp_ee_dev";
            //DBPASSWORD
        	String pass = "ssp_ee_dev";
            conn = DriverManager.getConnection(dbURL, user, pass);
            
            System.out.println("Please enter widget id ex : EEDEV-1840");
            
            Scanner sc = new Scanner(System.in);
            
            String widgetId = sc.nextLine();
            
            System.out.println("Please enter page id");
            
            String pageId = sc.nextLine();
            System.out.println("Do you want to Insert ERROR Messages Y or N for your Widget");
            String msgId = "";
            String msgYorN = sc.nextLine();
            
            if(msgYorN.equalsIgnoreCase("Y")){
            	System.out.println("Insert Messages ID's comma Separated Ex. 1234,5678,4567");
            	StringBuffer bf = new StringBuffer();
            	bf.append("(");
            	String[] msgIds = sc.nextLine().split(",");
            	for(int k=0; k<msgIds.length;k++){
            		if(k == msgIds.length-1)
            			bf.append("'"+msgIds[k]+"')");
            		else{	
            			bf.append("'"+msgIds[k]+"',");
            		}
            	}
            	msgId = bf.toString();
            }
            
            
            System.out.println("Do you want to Extra Display Text Messages Like yOu use in Listview Y or N");
            String dsplyId = "";
            String dsplYorN = sc.nextLine();
            
            if(dsplYorN.equalsIgnoreCase("Y")){
            	System.out.println("Insert Display ID's comma Separated Ex. 1234,5678,4567");
            	StringBuffer bf = new StringBuffer();
            	bf.append("(");
            	String[] dIds = sc.nextLine().split(",");
            	for(int k=0; k<dIds.length;k++){
            		if(k == dIds.length-1)
            			bf.append("'"+dIds[k]+"')");
            		else{	
            			bf.append("'"+dIds[k]+"',");
            		}
            	}
            	dsplyId = bf.toString();
            }
            
            //System.out.println("Conn "+conn);
            stmt = conn.createStatement();
            //String pageId = "ABPRD";
            
            String extraDisPlayId = "('','')";
            String sql1 = "select * from PAGE_ELE where PAGE_ID = '"+pageId+"'";
            String sql2 = "select * from DPLY_TXT where TXT_ID in (select txt_id from page_ele where page_id='"+pageId+"')";
            String sql3 = "select * from ELE_DPLY where ELE_ID in (select ELE_ID from page_ele where page_id='"+pageId+"')";
            String sql4 = "select * from PAGE where PAGE_ID = '"+pageId+"'";
            String sql5 = "select * from MSG where MSG_ID in "+msgId;
            String sql6 = "select * from DPLY_TXT where TXT_ID in "+dsplyId;
            
            StringBuffer sb1 = new StringBuffer();
            
            sb1.append("delete from PAGE_ELE where PAGE_ID = '"+pageId+"';");
            sb1.append("\n\r");
            ResultSet rs = stmt.executeQuery(sql1);
            while(rs.next()){
            	
            	StringBuffer innerLoop = new StringBuffer();
            	String tempString = "INSERT INTO PAGE_ELE VALUES("; 
            	innerLoop.append(tempString);
            	innerLoop.append("'"+rs.getString("PAGE_ID").replaceAll("'", "''")+"'");
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("ELE_ID")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("TXT_ID")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("RULE_ID")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("MAX_LTH")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("FS_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("MA_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("TF_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("CC_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("WC_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("CS_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("AG_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("MH_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("TE_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("EA_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("SA_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("AR_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("SC_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("FP_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("BG_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("DC_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("AM_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("FT_IND")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("KC_IND")));
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("APP_TYP").replaceAll("'", "''"))+"'");
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("CSS_IND")));
            	innerLoop.append(");");
            	sb1.append(innerLoop.toString()+"\n");
            }
            
            sb1.append("\n\r");
            sb1.append("delete from DPLY_TXT where TXT_ID in (select txt_id from page_ele where page_id='"+pageId+"');");
            sb1.append("\n\r");
            
            rs = stmt.executeQuery(sql2);
            while(rs.next()){
            	
            	
                StringBuffer innerLoop = new StringBuffer();
            	String tempString = "INSERT INTO DPLY_TXT VALUES("; 
            	innerLoop.append(tempString);
            	innerLoop.append(String.valueOf(rs.getInt("TXT_ID")));
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("LANG_CD").replaceAll("'", "''"))+"'");
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("CMT_TXT").replaceAll("'", "''"))+"'");
            	innerLoop.append(",");
            	innerLoop.append("N'"+String.valueOf(rs.getString("TXT_DSC").replaceAll("'", "''"))+"'");
            	innerLoop.append(");");
            	sb1.append(innerLoop.toString()+"\n");
            }
            
            
            sb1.append("\n\r");
            sb1.append("delete from ELE_DPLY where ELE_ID in (select ELE_ID from page_ele where page_id='"+pageId+"');");
            sb1.append("\n\r");
            
            rs = stmt.executeQuery(sql3);
            while(rs.next()){
            	
            	
            	
                StringBuffer innerLoop = new StringBuffer();
            	String tempString = "INSERT INTO ELE_DPLY VALUES("; 
            	innerLoop.append(tempString);
            	innerLoop.append(String.valueOf(rs.getInt("ELE_ID")));
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("ELE_SIZE")));
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("HTML_NAM").replaceAll("'", "''"))+"'");
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("ELE_DSC").replaceAll("'", "''"))+"'");
            	innerLoop.append(");");
            	sb1.append(innerLoop.toString()+"\n");
            }
            
            sb1.append("\n\r");
            sb1.append("delete from PAGE where PAGE_ID = '"+pageId+"';");
            sb1.append("\n\r");
            
            rs = stmt.executeQuery(sql4);
            while(rs.next()){
            	
                StringBuffer innerLoop = new StringBuffer();
            	String tempString = "INSERT INTO PAGE VALUES("; 
            	innerLoop.append(tempString);
            	innerLoop.append("'"+rs.getString("PAGE_ID").replaceAll("'", "''")+"'");
            	innerLoop.append(",");
            	innerLoop.append(String.valueOf(rs.getInt("PROG_PCT")));
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("HELP_PAGE_URL_ADR").replaceAll("'", "''"))+"'");
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("PAGE_DSC").replaceAll("'", "''"))+"'");
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("PAGE_URL_ADR").replaceAll("'", "''"))+"'");
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("AUTH_REQ_SW").replaceAll("'", "''"))+"'");
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("PGM_RULE_REQ_SW").replaceAll("'", "''"))+"'");
            	innerLoop.append(");");
            	sb1.append(innerLoop.toString()+"\n");
            }
            
            if(!msgId.equalsIgnoreCase("")){
            
            	sb1.append("\n\r");
            	sb1.append("delete from MSG where MSG_ID in "+msgId+";");
            	sb1.append("\n\r");
            
            
            	rs = stmt.executeQuery(sql5);
            	while(rs.next()){
            	
            	
            		StringBuffer innerLoop = new StringBuffer();
            		String tempString = "INSERT INTO MSG VALUES("; 
            		innerLoop.append(tempString);
            		innerLoop.append("'"+String.valueOf(rs.getString("MSG_ID").replaceAll("'", "''"))+"'");
            		innerLoop.append(",");
            		innerLoop.append("'"+String.valueOf(rs.getString("LANG_CD").replaceAll("'", "''"))+"'");
            		innerLoop.append(",");
            		innerLoop.append(String.valueOf(rs.getInt("SVR_CD")));
            		innerLoop.append(",");
            		innerLoop.append("N'"+String.valueOf(rs.getString("MSG_DSC").replaceAll("'", "''"))+"'");
	            	innerLoop.append(");");
	            	sb1.append(innerLoop.toString()+"\n");
            	}
            
            	sb1.append("\n\r");
            
            }
            
            if(!dsplyId.equalsIgnoreCase("")){
            
            sb1.append("\n\r");
            sb1.append("delete from DPLY_TXT where TXT_ID in "+dsplyId+" ;");
            sb1.append("\n\r");
            
            rs = stmt.executeQuery(sql6);
            while(rs.next()){
            	
            	StringBuffer innerLoop = new StringBuffer();
            	String tempString = "INSERT INTO DPLY_TXT VALUES("; 
            	innerLoop.append(tempString);
            	innerLoop.append(String.valueOf(rs.getInt("TXT_ID")));
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("LANG_CD").replaceAll("'", "''"))+"'");
            	innerLoop.append(",");
            	innerLoop.append("'"+String.valueOf(rs.getString("CMT_TXT").replaceAll("'", "''"))+"'");
            	innerLoop.append(",");
            	innerLoop.append("N'"+String.valueOf(rs.getString("TXT_DSC").replaceAll("'", "''"))+"'");
            	innerLoop.append(");");
            	sb1.append(innerLoop.toString()+"\n");
            }
            
            }
            
            
            System.out.println(sb1.toString()+"\n\r");
            
            String outPut = new String(sb1.toString().getBytes("UTF-8"), "ISO-8859-1");
            
            String tmpPath = System.getProperty("user.dir");
           // tmpPath.replaceAll("\\","/");
            System.out.println(tmpPath);
            
            Date date = new Date();
            StringBuffer fNameBuff = new StringBuffer();
            SimpleDateFormat sf = new SimpleDateFormat("MMddyyyy");
            String fDate = sf.format(date);
            fNameBuff.append(fDate);
            fNameBuff.append("_DMLScripts_");
            fNameBuff.append(widgetId);
            fNameBuff.append(".sql");
            String fName = fNameBuff.toString();
            File fDir = new File(tmpPath+"\\src\\"+widgetId);
			fDir.mkdir();
            File f = new File(fDir.getAbsolutePath()+"/"+fName);
            DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
            out.writeBytes(outPut);
			//out.writeBytes(sb1.toString());
			out.close();
            
            

        } catch (SQLException | FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	
		
}
	

