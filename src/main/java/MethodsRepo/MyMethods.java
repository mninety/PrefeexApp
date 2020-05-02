package MethodsRepo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.json.Json;

import StartPrefeexApp.VariableModule;
import io.appium.java_client.android.AndroidDriver;


public class MyMethods {
	static Logger logMyMethods = Logger.getLogger(MyMethods.class.getName());
	static int RowCount=1;
	static int initvalue=0;
	static String init="";
	
	static String headerhtml="<!DOCTYPE html>\r\n" + 
			"<html lang=\"en\">\r\n" + 
			"\r\n" + 
			"<head>\r\n" + 
			"\r\n" + 
			"    <!-- Required meta tags -->\r\n" + 
			"    <meta charset=\"utf-8\">\r\n" + 
			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n" + 
			"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\r\n" + 
			"    <title>Prefeex Test Report</title>\r\n" + 
			"    <!-- Bootstrap CSS -->\r\n" + 
			"    <link rel=\"stylesheet\" href=\"./dist/css/bootstrap.min.css\">\r\n" + 
			"\r\n" + 
			"    <style>\r\n" + 
			"        .Successful {\r\n" + 
			"            background-color: #14733B; color: #fff;\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"        .Failed {\r\n" + 
			"            background-color: #B8423E; color: #fff;\r\n" + 
			"        }\r\n" + 
			"        td[rowspan] {\r\n" + 
			"            background: #ffffff;\r\n" + 
			"        }\r\n" + 
			"    </style>\r\n" + 
			"</head>\r\n" + 
			"\r\n" + 
			"<body>\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"    <div class=\" container-fluid\">\r\n" + 
			"        <div class=\"row\">\r\n" + 
			"            <div class=\"col-12\">\r\n" + 
			"                <h1>Test Report</h1>\r\n" + 
			"                <div class=\" table-responsive\">\r\n" + 
			"                    <table class=\"table  table-bordered\">\r\n" + 
			"                        <thead>\r\n" + 
			"                            <tr >\r\n" + 
			"                                <th scope=\"col\">API Name</th>\r\n" + 
			"                                <th scope=\"col\">API Cases</th>\r\n" + 
			"                                <th scope=\"col\">End Point</th>\r\n" + 
			"                                <th scope=\"col\">Method Type</th>\r\n" + 
			"                                <th scope=\"col\">Body Input</th>\r\n" + 
			"                                <th style=\"width: 10%;\" scope=\"col\">Header</th>\r\n" + 
			"                                <th scope=\"col\">Output JSON</th>\r\n" + 
			"                                <th scope=\"col\">JSON Path</th>\r\n" + 
			"                                <th scope=\"col\">Path Value</th>\r\n" + 
			"                                <th scope=\"col\">HTTP Code</th>\r\n" + 
			"                                <th scope=\"col\">Execution Time</th>\r\n" + 
			"                                <th scope=\"col\">Test Result</th>\r\n" + 
			"                            </tr>\r\n" + 
			"                        </thead>" +
			"                        <tbody>";
	static String footerhtml="                        </tbody>\r\n" + 
			"                    </table>\r\n" + 
			"                </div>\r\n" + 
			"            </div>\r\n" + 
			"        </div>\r\n" + 
			"    </div>\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"    <!-- Optional JavaScript -->\r\n" + 
			"    <!-- jQuery first, then Popper.js, then Bootstrap JS -->\r\n" + 
			"    <script src=\"./dist/js/jquery.min.js\"></script>\r\n" + 
			"    <script src=\"./dist/js/popper.js\"></script>\r\n" + 
			"    <script src=\"./dist/js/bootstrap.min.js\"></script>\r\n" + 
			"</body>\r\n" + 
			"\r\n" + 
			"</html>";
	
	
	public static void FolderCreateAction(File T) {
        if(!T.exists()){  
        	if(T.mkdir()){ 
        		//System.out.println("directory is  created"); 
        		}
        	}  
        else { 
        	//System.out.println("directory exist");  
        	}
	}
	
    public static String getCurrentDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateobject = new SimpleDateFormat("ddMMyyyy-HHmmss");
        return dateobject.format(date);
    }
    
	public static void FileBackupAction() {
        

        File fileexcel = new File(VariableModule.driverlog+"/InputFiles");
		String currentDate = getCurrentDate();
        FolderCreateAction(fileexcel);
        ExcelFileCreateAction();
	}
	
	public static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    }catch(Exception e)
	    {
	    	
	    }

	    if(is != null)
	    {
	        is.close();
	        os.close();
	    }
	}
	
	public static void ExcelFileDeleteAction() {
		
		File source= new File(VariableModule.driverlog+"/InputFiles/GoogleSheet/APIReportdownload.xlsx");
		File dest = new File(VariableModule.driverlog+"/InputFiles/GoogleSheet/APIReportdownloadOutput-"+MyMethods.getCurrentDate()+".xlsx");
		try {
			MyMethods.copyFileUsingStream(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
        if(source.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
        
	}
	public static void ExcelFileCreateAction() {
			 try {
					String currentDate = getCurrentDate();
					int randomValue = (int )(Math.random() * 500 + 1);
			        File oldName = new File(VariableModule.driverlog+"/Reports/ReportAPI.xlsx");
			        File newName = new File(VariableModule.driverlog+"/Reports/ReportAPI"+'-'+currentDate+'-'+randomValue+".xlsx");
			        oldName.renameTo(newName);
				XSSFWorkbook workbook = new XSSFWorkbook();
				FileOutputStream out = new FileOutputStream(new File(VariableModule.driverlog+"/Reports/ReportAPI.xlsx"));
				XSSFSheet Spreadsheet = workbook.createSheet("ReportAPI-"+currentDate);
				XSSFRow header = Spreadsheet.createRow(0);
				//XSSFCellStyle style = workbook.createCellStyle();
				header.createCell(0).setCellValue("Feature");
			    header.createCell(1).setCellValue("Test Case");
			    header.createCell(2).setCellValue("Testing Result");
			    header.createCell(3).setCellValue("Execution Time");
			    header.createCell(4).setCellValue("Message");
			    header.createCell(5).setCellValue("Input Paramters");
	/*			    style.setBorderTop(BorderStyle.THIN);
			    style.setBorderBottom(BorderStyle.MEDIUM);
			    style.setBorderLeft(BorderStyle.MEDIUM);
			    style.setBorderRight(BorderStyle.MEDIUM);*/
				workbook.write(out);
				out.close();
			}
			catch(Exception e) {
				System.out.println(e);
				
			}
		}
	public static void Sleep(long milis)
	{
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void PrintMe(String str1, String str2)
	{
		System.out.println(str1+str2);
	}

	public static void ExcelFileWriteAction(String feature, String testcase, String result, String date, String message, String param, int noofCase) {
		
		if(result!="") { //Cases added
		       

			XSSFCell cell = null; 
		
				//System.out.println("Writer Enabled"+RowCount);
				try {
					
					
					
					FileInputStream fIPS= new FileInputStream(VariableModule.driverlog+"/Reports/MobileAppReport.xlsx");
					XSSFWorkbook workbook = new XSSFWorkbook(fIPS);
					XSSFSheet worksheet = workbook.getSheetAt(0);
					
					fIPS.close();
					FileOutputStream out = new FileOutputStream(new File(VariableModule.driverlog+"/Reports/MobileAppReport.xlsx"));
					//XSSFSheet worksheet = workbook.getSheetAt(0);
			        XSSFCellStyle headerStyle = workbook.createCellStyle();
			        Font headerFont = workbook.createFont();
					XSSFRow row = worksheet.createRow(RowCount);

				       //indexOf return -1 if String does not contain specified word
				       if(result.indexOf("Failed") != -1){
				           //System.err.printf("Yes '%s' contains word 'Failed' %n" , result);
					        //font.setColor(IndexedColors.RED.getIndex());
					        //style.setFont(font);
							row.createCell(0).setCellValue(feature);
						    //header.createCell(1).setCellValue(result);
						    cell = row.createCell(1);
						    cell.setCellValue(testcase);
						    
						    
						    cell = row.createCell(2);
						    cell.setCellValue(result);
						    
						    cell = row.createCell(3);
						    cell.setCellValue(date);
						    
						    cell = row.createCell(4);
						    cell.setCellValue(message);
						    
						    cell = row.createCell(5);
						    cell.setCellValue(param);
					        //headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					        
					        //headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
						    //headerFont.setColor(IndexedColors.RED.getIndex());
					        headerStyle.setFont(headerFont);
					        
					        
				       }
				       else if(result.indexOf("null") != -1){
				           //System.err.printf("Yes '%s' contains word 'null' %n" , result);
					        //font.setColor(IndexedColors.RED.getIndex());
					        //style.setFont(font);
							row.createCell(0).setCellValue(feature);
						    //header.createCell(1).setCellValue(result);
						    cell = row.createCell(1);
						    cell.setCellValue("");

						    cell = row.createCell(2);
						    cell.setCellValue(result);
						    
						    cell = row.createCell(3);
						    cell.setCellValue(date);
						    
						    cell = row.createCell(4);
						    cell.setCellValue(message);
						    
						    cell = row.createCell(5);
						    cell.setCellValue(param);
					        //headerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
					        headerFont.setColor(IndexedColors.BLUE.getIndex());
					        //headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
					        headerStyle.setFont(headerFont);
					        
					        
				       }
				       else {
							row.createCell(0).setCellValue(feature);
						    //header.createCell(1).setCellValue(result);
						    cell = row.createCell(1);
						    cell.setCellValue(testcase);

						    cell = row.createCell(2);
						    cell.setCellValue(result);
						    
						    cell = row.createCell(3);
						    cell.setCellValue(date);
						    
						    cell = row.createCell(4);
						    cell.setCellValue(message);
						    
						    cell = row.createCell(5);
						    cell.setCellValue(param);
				       }
				    cell.setCellStyle(headerStyle);
				    
				    if(noofCase>1)
				    {
				    	initvalue++;
				    	init=init+String.valueOf(RowCount)+",";
					    if(noofCase==initvalue)
					    {
					    	String[] initstr=init.split(",");
					    	//MyMethods.PrintMe("Row1: ", String.valueOf(RowCount));
					    	worksheet.addMergedRegion(new CellRangeAddress(Integer.valueOf(initstr[0]),RowCount,0,0));
					    	initvalue=0;
					    	init="";
					    }
				    }
					workbook.write(out);
					out.close();
					
					
				} catch (IOException e) {
		
					e.printStackTrace();
				}
				//MyMethods.PrintMe("Row2: ", String.valueOf(RowCount));
				RowCount++;
		}
		else //Module name added
		{
			
			RowCount=RowCount+3;
			//System.out.println("Writer Enabled with null"+RowCount);
			XSSFCell cell = null; 
			try {
				FileInputStream fIPS= new FileInputStream(VariableModule.driverlog+"/WebDriver/Reports/ReportAPI.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fIPS);
				XSSFSheet worksheet = workbook.getSheetAt(0);
				
				fIPS.close();
				
				FileOutputStream out = new FileOutputStream(new File(VariableModule.driverlog+"/WebDriver/Reports/ReportAPI.xlsx"));
				XSSFRow row = worksheet.createRow(RowCount);
				cell = row.createCell(0);
				//cell = row.createCell(1);

				worksheet.addMergedRegion(new CellRangeAddress(RowCount,RowCount,0,1));
			    cell.setCellValue(testcase);
				workbook.write(out);
				out.close();
				
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			RowCount=RowCount+3;
		}
	}

	public static void ExcelFileWriteforAPI(String[] output, String sheetName, int rowno, String testresult) {
		
		if(output[0]!="") { //Cases added
		       

			XSSFCell cell = null; 
			FileInputStream fIPS;
			FileOutputStream out;
			XSSFWorkbook workbook;
				//System.out.println("Writer Enabled"+RowCount);
				try {
					
					if(VariableModule.testingMode.equals("1")) //local
					{
						fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/APIReport.xlsx");
						workbook = new XSSFWorkbook(fIPS);
						out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles/APIReport.xlsx"));
					}
					else if(VariableModule.testingMode.equals("2")) //google
					{
						fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/GoogleSheet/APIReportdownload.xlsx");
						workbook = new XSSFWorkbook(fIPS);
						out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles/GoogleSheet/APIReportdownload.xlsx"));
					}
					else //html
					{
						fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/HTML/APIReportHTML.xlsx");
						workbook = new XSSFWorkbook(fIPS);
						out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles//HTML/APIReportHTML.xlsx"));
					}
					
					//FileInputStream fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/APIReport.xlsx");
					
					//XSSFSheet worksheet = workbook.getSheetAt(0);
					XSSFSheet worksheet = workbook.getSheet(sheetName);
					
					fIPS.close();
					//FileOutputStream out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles/APIReport.xlsx"));
					//XSSFSheet worksheet = workbook.getSheetAt(0);
			        XSSFCellStyle headerStyle = workbook.createCellStyle();
			        Font headerFont = workbook.createFont();
					XSSFRow row = worksheet.getRow(rowno);

					row.getCell(6).setCellValue(output[0]);
					row.getCell(9).setCellValue(output[1]);
					row.getCell(10).setCellValue(MyMethods.getCurrentDate());
					row.getCell(11).setCellValue(testresult);
				       //indexOf return -1 if String does not contain specified word

				    //cell.setCellStyle(headerStyle);
				    
					workbook.write(out);
					out.close();
					
					
				} catch (IOException e) {
		
					e.printStackTrace();
				}

		}

	}

	public static void ExcelFileWriteforSummary(String apiName, int rowno, int noofsuccess, int nooffailed) {

			XSSFCell cell = null; 
			FileInputStream fIPS;
			FileOutputStream out;
			XSSFWorkbook workbook;
				//System.out.println("Writer Enabled"+RowCount);
				try {
					
					if(VariableModule.testingMode.equals("1")) //local
					{
						fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/APIReport.xlsx");
						workbook = new XSSFWorkbook(fIPS);
						out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles/APIReport.xlsx"));
					}
					else if(VariableModule.testingMode.equals("2")) //google
					{
						fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/GoogleSheet/APIReportdownload.xlsx");
						workbook = new XSSFWorkbook(fIPS);
						out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles/GoogleSheet/APIReportdownload.xlsx"));
					}
					else //html
					{
						fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/HTML/APIReportHTML.xlsx");
						workbook = new XSSFWorkbook(fIPS);
						out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles//HTML/APIReportHTML.xlsx"));
					}
					
					
					//FileInputStream fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/APIReport.xlsx");
					
					//XSSFSheet worksheet = workbook.getSheetAt(0);
					XSSFSheet worksheet = workbook.getSheet("Summary");
					
					fIPS.close();
					//FileOutputStream out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles/APIReport.xlsx"));
					//XSSFSheet worksheet = workbook.getSheetAt(0);
			        XSSFCellStyle headerStyle = workbook.createCellStyle();
			        Font headerFont = workbook.createFont();
			        System.out.println("Total Found Rows: "+worksheet.getPhysicalNumberOfRows());
					XSSFRow row = worksheet.createRow(worksheet.getPhysicalNumberOfRows());

					row.createCell(0).setCellValue(apiName);
					row.createCell(1).setCellValue(rowno);
					row.createCell(2).setCellValue(noofsuccess);
					row.createCell(3).setCellValue(nooffailed);
				       //indexOf return -1 if String does not contain specified word

				    //cell.setCellStyle(headerStyle);
				    
					workbook.write(out);
					out.close();
					
					
				} catch (IOException e) {
		
					e.printStackTrace();
				}


	}

	public static void ExcelFileEmptyforSummary(String testingMode, int noofrows) {

		FileInputStream fIPS;
		XSSFWorkbook workbook;
		FileOutputStream out;
		try {

			if(VariableModule.testingMode.equals("1")) //local
			{
				fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/APIReport.xlsx");
				workbook = new XSSFWorkbook(fIPS);
				out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles/APIReport.xlsx"));
			}
			else if(VariableModule.testingMode.equals("2")) //google
			{
				fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/GoogleSheet/APIReportdownload.xlsx");
				workbook = new XSSFWorkbook(fIPS);
				out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles/GoogleSheet/APIReportdownload.xlsx"));
			}
			else //html
			{
				fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/HTML/APIReportHTML.xlsx");
				workbook = new XSSFWorkbook(fIPS);
				out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles//HTML/APIReportHTML.xlsx"));
			}
			
			
			XSSFSheet worksheet = workbook.getSheet("Summary");

				for (int i = noofrows; i >= 1; i--) 
				{
					worksheet.removeRow(worksheet.getRow(i));
				}
			    
				workbook.write(out);
				out.close();
				fIPS.close();
				
				
			} catch (IOException e) {
	
				e.printStackTrace();
			}


}

	public static int ExcelRowCount(String testingMode)
	{
		int notNullCount = 0;
		FileInputStream fIPS;
		XSSFWorkbook workbook;
		FileOutputStream out;
		try {

			if(VariableModule.testingMode.equals("1")) //local
			{
				fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/APIReport.xlsx");
				workbook = new XSSFWorkbook(fIPS);
				out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles/APIReport.xlsx"));
			}
			else if(VariableModule.testingMode.equals("2")) //google
			{
				fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/GoogleSheet/APIReportdownload.xlsx");
				workbook = new XSSFWorkbook(fIPS);
				out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles/GoogleSheet/APIReportdownload.xlsx"));
			}
			else //html
			{
				fIPS= new FileInputStream(VariableModule.driverlog+"/InputFiles/HTML/APIReportHTML.xlsx");
				workbook = new XSSFWorkbook(fIPS);
				out = new FileOutputStream(new File(VariableModule.driverlog+"/InputFiles//HTML/APIReportHTML.xlsx"));
			}
			
			
			XSSFSheet worksheet = workbook.getSheet("Summary");
			
			
			//Sheet sheet = wb.getSheetAt(0);
			for (Row row : worksheet) {
			    for (Cell cell : row) {
			        if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
			            if (cell.getCellType() != Cell.CELL_TYPE_STRING ||
			                cell.getStringCellValue().length() > 0) {
			                notNullCount++;
			                break;
			            }
			        }
			    }
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notNullCount;
	}
	
	@SuppressWarnings("null")
	public static String[][] ExcelFileReadAction(String fileName, String sheetName)
	{
		String input="";
		String[][] data=null;
		FileInputStream fs;
		XSSFWorkbook wb;
		try {
			
			if(VariableModule.testingMode.equals("1")) //local
			{
				fs = new FileInputStream(VariableModule.driverlog+"/InputFiles/"+fileName);
				wb = new XSSFWorkbook(fs);
			}
			else if(VariableModule.testingMode.equals("2")) //google
			{
				fs = new FileInputStream(VariableModule.driverlog+"/InputFiles/GoogleSheet/"+fileName);
				wb = new XSSFWorkbook(fs);
			}
			else //html
			{
				fs = new FileInputStream(VariableModule.driverlog+"/InputFiles/HTML/"+fileName);
				wb = new XSSFWorkbook(fs);
			}
			
		    XSSFSheet sheet = wb.getSheet(sheetName);
		    XSSFRow row;
		    XSSFCell cell;

		    int rows = sheet.getPhysicalNumberOfRows();
		    int noofrow=rows-1;
		    MyMethods.PrintMe("Rows: ", String.valueOf(rows));
		    
		    data= new String[rows-1][6];

		    for(int r = 0; r < rows; r++) {
		    	if(r>0)
		    	{
			        row = sheet.getRow(r);
			        if(row != null) {
			        	
			        	data[r-1][0]=row.getCell((short)2).toString(); //end point
			        	data[r-1][1]=row.getCell((short)3).toString(); //method Type
			        	data[r-1][2]=row.getCell((short)4).toString(); //body input
			        	data[r-1][4]=row.getCell((short)5).toString(); //header
			        	data[r-1][5]=row.getCell((short)7).toString(); //JSON path
			        	data[r-1][3]=row.getCell((short)8).toString(); //path value

			            
			        }
		    	}
		    }
		    //System.out.println("Input Data: "+input);
		    fs.close();
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
		return data;
	}
	
	
	//@SuppressWarnings("null")
	@SuppressWarnings("resource")
	public static String[] ExcelFileSheetData(String fileName)
	{
		String input="";
		String[] data=null;
		try {
			FileInputStream fs=null;
			XSSFWorkbook wb=null;
			if(VariableModule.testingMode.equals("1"))
			{
				fs = new FileInputStream(VariableModule.driverlog+"/InputFiles/"+fileName);
				wb = new XSSFWorkbook(fs);
			}
			else if(VariableModule.testingMode.equals("2"))
			{
				fs = new FileInputStream(VariableModule.driverlog+"/InputFiles/GoogleSheet/"+fileName);
				wb = new XSSFWorkbook(fs);
			}
			else
			{
				fs = new FileInputStream(VariableModule.driverlog+"/InputFiles/HTML/"+fileName);
				wb = new XSSFWorkbook(fs);
			}
		    
		    VariableModule.sheetno = wb.getNumberOfSheets();
		    data = new String[VariableModule.sheetno];
            // for each sheet in the workbook
            for (int i = 0; i < VariableModule.sheetno; i++) {

                //System.out.println("Sheet name: " + wb.getSheetName(i));
                data[i]=wb.getSheetName(i);
            }


		    //System.out.println("Input Data: "+input);
		    fs.close();
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}
		return data;
	}
	
	public static String HTMLMakerforAll(String body, XSSFWorkbook wb, int i)
	{
		XSSFSheet sheet = wb.getSheet(wb.getSheetName(i));
		String[] apiName=wb.getSheetName(i).split("-");
	    XSSFRow row;
	    XSSFCell cell;

	    int rows = sheet.getPhysicalNumberOfRows();
	    
	    
	    String span="";
	    String resulthtml="";
	    for(int r = 0; r < rows; r++) {
	    	if(r>0)
	    	{
	    		if(r==1)
	    		{
	    			span="                                <td rowspan=\""+(rows-1)+"\">"+apiName[0]+"</td>\r\n";
	    		}
	    		else
	    		{
	    			span="";
	    		}

		        row = sheet.getRow(r);
		        
	    		String result= row.getCell((short)11).toString();
	    		if(result.equals("Successful"))
	    		{
	    			resulthtml="                            <tr class=\"Successful\">\r\n";
	    		}
	    		else
	    		{
	    			resulthtml="                            <tr class=\"Failed\">\r\n";
	    		}
	    		
		        if(row != null) {
		        	body= body+ resulthtml + span + 
		        			"                                <td>"+row.getCell((short)1).toString()+"</td>\r\n" + 
		        			"                                <td>"+row.getCell((short)2).toString()+"</td>\r\n" + 
		        			"                                <td>"+row.getCell((short)3).toString()+"</td>\r\n" + 
		        			"                                <td><pre>"+row.getCell((short)4).toString()+"</pre></td>\r\n" + 
		        			"                                <td style=\"width: 10%;\">"+row.getCell((short)5).toString()+"</td>\r\n" + 
		        			"                                <td><pre>"+row.getCell((short)6).toString()+"</pre></td>\r\n" + 
		        			"                                <td>"+row.getCell((short)7).toString()+"</td>\r\n" + 
		        			"                                <td>"+row.getCell((short)8).toString()+"</td>\r\n" + 
		        			"                                <td>"+row.getCell((short)9).toString()+"</td>\r\n" + 
		        			"                                <td>"+row.getCell((short)10).toString()+"</td>\r\n" + 
		        			"                                <td>"+result+"</td>\r\n" + 
		        			"                            </tr>";
		            
		        }
	    	}
	    }
	    
	    return body;
	}
	
	
	@SuppressWarnings("resource")
	public static void ExceltoHTML(String fileName)
	{

		
		try {
			
			String input="";
		    String body="";
			FileInputStream fs = new FileInputStream(VariableModule.driverlog+"/InputFiles/HTML/"+fileName);
		    XSSFWorkbook wb = new XSSFWorkbook(fs);
		    
			if(VariableModule.isAllAPITest.equals("1"))
			{
				


	            for (int i = 0; i < VariableModule.sheetno; i++) {

	                //System.out.println("Sheet name: " + wb.getSheetName(i));
    				if(!wb.getSheetName(i).equals("Summary"))
    				{

    					body=HTMLMakerforAll(body,wb,i);
    					
    				}

	            }
	            
			}
			
			
			else //isAllAPITest=2
			{
				
				String[] apiArray=null;
				String apiArray1=VariableModule.readVariable("apiArray", 1);
				//System.out.println("Array: "+apiArray1);
				if(apiArray1.contains(","))
				{
    				apiArray=apiArray1.split(",");

    	            for (int i = 0; i < wb.getNumberOfSheets(); i++) {

    	            	for(int j=0;j<apiArray.length;j++)
    	            	{
    	            		if(wb.getSheetName(i).equals(apiArray[j]))
    	            		{
    	            			body=HTMLMakerforAll(body, wb, i);
    	        				
    	            			
    	        				
    	            		}
    	            		else
    	            		{
    	            			continue;
    	            		}
    	            	}
        				
        				
    	            }
    	            
        			
				}
				else
				{
					//System.out.println("Sheet Name: "+apiArray1);
					
		            for (int i = 0; i < wb.getNumberOfSheets(); i++) {

		            		if(wb.getSheetName(i).equals(apiArray1))
		            		{
		            			
		            			body=HTMLMakerforAll(body, wb, i);
		        				break;
		        				
		            		}
		            		else
		            		{
		            			continue;
		            		}
	    				
	    				
		            }
		            
				}
				

			    
			}

            fs.close();
		    writing(headerhtml+body+footerhtml);
			
		} catch(Exception ioe) {
		    ioe.printStackTrace();
		}

		
	}
	
    @SuppressWarnings("resource")
	public static void writing(String wget) {
        try {
        	
			File source= new File(VariableModule.driverlog+"/InputFiles/HTML/index.html");
			File dest = new File(VariableModule.driverlog+"/InputFiles/HTML/index-"+MyMethods.getCurrentDate()+".html");
			try {
				MyMethods.copyFileUsingStream(source, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
            //Whatever the file path is.
        	File WFile = new File(VariableModule.driverlog+"/InputFiles/HTML/index.html");
        	//File WFile = new File(file_name);
            FileWriter fw1 = new FileWriter(WFile, false);
            fw1.flush();
            System.out.println("File truncated");
            
        	FileWriter fw = new FileWriter(WFile,true);
/*            FileOutputStream is = new FileOutputStream(WFile);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            Writer w = new BufferedWriter(osw);*/
        	
        	if(wget!=null)
        	{
        	fw.write("\n"+wget+"\n");
        	}
            //fw.write(wget);
            //fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file Output.txt");
        }
    }
    
    
    
	public static String JsonBuilder(HashMap<String, String> chash)
	{
		//System.out.println("Size: "+chash.size());
		
		JSONObject jo = new JSONObject();
		
	    for (String i : chash.keySet()) {
	    	  jo.put(i, chash.get(i));
	    	}
	    
	    return jo.toString();
	}
	

	public static String[] getAPI(String apiurl, int header, String hdata) throws Exception {

		String[] myArray= new String[2];
		try {
			apiurl = VariableModule.adminURL+apiurl;

			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(apiurl);

			// add request header
			//request.addHeader("User-Agent", USER_AGENT);
			if(header==2)
			{
				request.addHeader("Authorization", hdata);
			}
			HttpResponse response = client.execute(request);

			//System.out.println("\nSending 'GET' request to URL : " + apiurl);
			myArray[1]= String.valueOf(response.getStatusLine().getStatusCode());
			//System.out.println("Response Code : " + myArray[1]);
			               

			BufferedReader rd = new BufferedReader(
			               new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

			myArray[0]=result.toString();
	        
	        
			MyMethods.PrintMe("API Response: ", myArray[0]);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return myArray;
	}


	public static String[] postAPI(String apiurl, String input, int header, String hdata)
	{
				String result = "";
				boolean connected = false;
				String[] myArray= new String[2];
				try {
					apiurl = VariableModule.adminURL+apiurl;
	
					URL url = new URL(apiurl);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					
					//conn.setConnectTimeout(5000);
					conn.setRequestProperty("Content-Type", "application/json");
					if(header==2)
					{
						conn.setRequestProperty("Authorization", hdata);
					}
					conn.setDoOutput(true);
					conn.setDoInput(true);
					conn.setRequestMethod("POST");
					conn.connect();
	
					OutputStream os = conn.getOutputStream();
					os.write(input.getBytes("UTF-8"));
					os.close();
					
					int res=conn.getResponseCode();
					//System.out.println("Resp: "+res);
					
					/*
					if(res!=200)
					{
						result="HTTP Response Code: "+String.valueOf(res);
						MyMethods.PrintMe("API Response: ", result);
					}
					*/
					
					
			        switch (conn.getResponseCode()) 
			        {
			            case HttpURLConnection.HTTP_OK:
			            	//System.out.println(" **OK**");
			                connected = true;
			                break; // fine, go on
			            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
			            	//System.out.println(" **gateway timeout**");
			                break;// retry
			            case HttpURLConnection.HTTP_UNAVAILABLE:
			            	//System.out.println("**unavailable**");
			                break;// retry, server is unstable
			            default:
			            	//System.out.println(" **unknown response code**.");
			                break; // abort
			        }
			        
			        System.out.println("Connection: "+connected);
					
			        // read the response
			        if(res==200)
			        {
						InputStream in = new BufferedInputStream(conn.getInputStream());
						result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
						in.close();
			        }
			        else
			        {
				        InputStream error= new BufferedInputStream(conn.getErrorStream());
				        result = org.apache.commons.io.IOUtils.toString(error, "UTF-8");
				        
				        error.close();
			        }
			        
			        myArray[0]=result;
			        myArray[1]=String.valueOf(res);
			        
					MyMethods.PrintMe("API Response: ", result);
	
					
					
					conn.disconnect();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					//System.err.println("Problem in executing web url");
					//e.printStackTrace();
					
				}
	
	            return myArray;
	    }

	public static String[] putAPI(String apiurl, String input, int header, String hdata) throws IOException {
		
		String result="";
		boolean connected = false;
		String[] myArray= new String[2];
		
		try {
			apiurl = VariableModule.adminURL+apiurl;
	
			URL url = new URL(apiurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Content-Type", "application/json");
			if(header==2)
			{
				conn.setRequestProperty("Authorization", hdata);
			}
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("PUT");
	
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes("UTF-8"));
			os.close();
	
			int res=conn.getResponseCode();
			//System.out.println("Resp: "+res);
			
			switch (conn.getResponseCode()) 
			{
			    case HttpURLConnection.HTTP_OK:
			    	//System.out.println(" **OK**");
			        connected = true;
			        break; // fine, go on
			    case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
			    	//System.out.println(" **gateway timeout**");
			        break;// retry
			    case HttpURLConnection.HTTP_UNAVAILABLE:
			    	//System.out.println("**unavailable**");
			        break;// retry, server is unstable
			    default:
			    	//System.out.println(" **unknown response code**.");
			        break; // abort
			}
			
			System.out.println("Connection: "+connected);
			
			// read the response
			if(res==200)
			{
				InputStream in = new BufferedInputStream(conn.getInputStream());
				result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
				in.close();
			}
			else
			{
			    InputStream error= new BufferedInputStream(conn.getErrorStream());
			    result = org.apache.commons.io.IOUtils.toString(error, "UTF-8");
			    
			    error.close();
			}
			myArray[0]=result;
			myArray[1]=String.valueOf(res);
			
			MyMethods.PrintMe("API Response: ", result);
			
			conn.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    return myArray;
	}

	

}
