package main.java.com.rp.automation.supporters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private String filePath;
	private FileInputStream fip;
	private static Workbook workbook;
	private static Sheet sheet;
	private Row row;
	private Cell cell;
	private String value;
	static String content[][] = null;
	static FileInputStream file = null;
	//static XSSFWorkbook workbook = null;
	//static XSSFSheet sheet = null;

	public ExcelReader(String filePath) throws EncryptedDocumentException, IOException, InvalidFormatException
	{
		this.filePath = filePath;
		fip = new FileInputStream(filePath);
		workbook = WorkbookFactory.create(fip);
	}
	
	public Sheet getExcelSheet(String how,String sheetNameORIndex) {
		if (workbook!=null) {
			if (how.equalsIgnoreCase("sheetName")) {
				sheet = workbook.getSheet(sheetNameORIndex);
			}
			else
			{
				int index = Integer.parseInt(sheetNameORIndex);
				sheet = workbook.getSheetAt(index);
			}
		}
		return sheet;
	}
	
	public String getSingleCellData(String how,String sheetNameORIndex,int rowNum,int cellNum) {
		sheet = getExcelSheet(how, sheetNameORIndex);
		if (sheet!= null) {
			row = sheet.getRow(rowNum);
			cell = row.getCell(cellNum);
			if (cell.getCellType() == cell.CELL_TYPE_STRING) {
				value = cell.getStringCellValue();
			}
			else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				value = cell.getNumericCellValue()+"";
			}
			else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
				value = cell.getBooleanCellValue()+"";
			}
		}
		return value;
	}
	
	public List<String> getRowData(String how,String sheetNameORIndex,int rowNum) {
		sheet = getExcelSheet(how, sheetNameORIndex);
		row = sheet.getRow(rowNum);
		List<String> rowData = new ArrayList<>();
		
		for(int i=0; i<=row.getLastCellNum(); i++)
		{
			cell = row.getCell(i);
			if (cell.getCellType() == cell.CELL_TYPE_STRING) {
				value = cell.getStringCellValue();
			}
			else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				value = cell.getNumericCellValue()+"";
			}
			else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
				value = cell.getBooleanCellValue()+"";
			}
			rowData.add(value);
		}
		return rowData;
	}
	
	public List<String> getSheetData(String how,String sheetNameORIndex) {
		List<String> sheetData = new ArrayList<>();
		sheet = getExcelSheet(how, sheetNameORIndex);
		if (sheet!=null) {
			for(int i=0; i<=sheet.getLastRowNum(); i++)
			{
				row = sheet.getRow(i);
				if (row!=null) {
					for(int j=0; j<row.getLastCellNum(); j++)
					{
						cell = row.getCell(j);
						if (cell.getCellType() == cell.CELL_TYPE_STRING) {
							value = cell.getStringCellValue();
						}
						else if (cell.getCellType() ==cell.CELL_TYPE_NUMERIC) {
							value = cell.getNumericCellValue()+"";
						}
						else if (cell.getCellType() ==cell.CELL_TYPE_BOOLEAN) {
							value = cell.getBooleanCellValue()+"";
						}
						sheetData.add(value);
					}
				}
			}
		}
		return sheetData;
	}
	public Set<String> getSheetUniqData(String how,String sheetNameORIndex) {
		List<String> sheetData = getSheetData(how, sheetNameORIndex);
		Set<String> uniqueData = new HashSet<String>(sheetData);
		return uniqueData;
	}
	
	public void writeData(String how,String sheetNameORIndex,int rowNum,int cellNum,String data) throws IOException {
		sheet = getExcelSheet(how, sheetNameORIndex);
		row = sheet.getRow(rowNum);
		cell = row.createCell(cellNum);
		cell.setCellValue(data);
		FileOutputStream fop = new FileOutputStream(filePath);
		workbook.write(fop);
	}
	
	
	/**
	 * This function is defined to read content from spreadsheet. This will return
	 * content only and will not return headers.
	 * 
	 * @param fileName
	 *            - Complete path of file.
	 * @param sheetName
	 *            - Sheet from where data needs to be retrieved.
	 * @return The content from spreadsheet @ in case of error.
	 */
	public static String[][] Read_Excel(String fileName, String sheetName) {
		try {
			file = new FileInputStream(new File(fileName));

			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
			int rowNum = sheet.getLastRowNum();
			workbook.close();
			content = Read_Excel(fileName, sheetName, rowNum);
			return content;
		} catch (FileNotFoundException e) {
			throw new main.java.com.rp.automation.customisedexception.FrameworkException("File " + fileName + " not found for reading.");
		} catch (IOException e) {
			throw new main.java.com.rp.automation.customisedexception.FrameworkException("Exception occured while reading " + fileName);
		} catch (Exception e) {
			throw new main.java.com.rp.automation.customisedexception.FrameworkException("Unknown Exception while reading " + fileName + "&" + sheetName + "---"
					+ e.getClass() + "---" + e.getMessage());
		}
	}

	/**
	 * This function is defined to read content from spreadsheet till particular row
	 * number. This will return content only and will not return headers.
	 * 
	 * @param fileName
	 *            - Complete path of file.
	 * @param sheetName
	 *            - Sheet from where data needs to be retrieved.
	 * @param rowNum
	 *            - RowNumber till data needs to be retrieved.
	 * @return The content from spreadsheet @ in case of error.
	 */
	public static String[][] Read_Excel(String fileName, String sheetName, int rowNum) {
		try {
			DataFormatter df = new DataFormatter();
			String content[][] = null;
			FileInputStream file = null;
			XSSFWorkbook workbook = null;
			XSSFSheet sheet = null;
			int colNum = 0;

			file = new FileInputStream(new File(fileName));

			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
			colNum = sheet.getRow(0).getLastCellNum();
			content = new String[rowNum][colNum];

			for (int i = 0; i < rowNum; i++) {
				XSSFRow row = sheet.getRow(i + 1);
				for (int j = 0; j < colNum; j++) {
					XSSFCell cell = row.getCell(j);
					String value;
					if (cell != null) {
						// value = cell.getStringCellValue();
						value = df.formatCellValue(row.getCell(j));

						content[i][j] = value;
					} else {
						content[i][j] = "";
					}
				}
			}

			workbook.close();
			return content;
		} catch (FileNotFoundException e) {
			throw new main.java.com.rp.automation.customisedexception.FrameworkException("File " + fileName + " not found for reading.");
		} catch (IOException e) {
			throw new main.java.com.rp.automation.customisedexception.FrameworkException("Exception occured while reading " + fileName);
		} catch (Exception e) {
			throw new main.java.com.rp.automation.customisedexception.FrameworkException("Unknown Exception while reading " + fileName + "&" + sheetName + "---"
					+ e.getClass() + "---" + e.getMessage());
		}

	}

	
	public static Map readExcelFile(String fileName,String sheetName)
	{
		
		Map<String,Map> map=new HashMap();
		try {
			DataFormatter df = new DataFormatter();
			String content[][] = null;
			FileInputStream file = null;
			XSSFWorkbook workbook = null;
			XSSFSheet sheet = null;
			int colNum = 0;
			file = new FileInputStream(new File(fileName));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
			int rowNum=	sheet.getLastRowNum();
		
			colNum = sheet.getRow(0).getLastCellNum();
			content = new String[rowNum][colNum];

			for (int i = 0; i < rowNum; i++) {
				Map<String,String> innerMap =new HashMap();
				XSSFRow row = sheet.getRow(i + 1);
				for (int j = 0; j < colNum; j++) {
					
					XSSFCell cell = row.getCell(j);
					String value;
					if (cell != null) {
						value = df.formatCellValue(row.getCell(j));
						innerMap.put(df.formatCellValue(sheet.getRow(0).getCell(j)),value);
						
					} else {
						innerMap.put(df.formatCellValue(sheet.getRow(0).getCell(j)),"");
					}
					
				}
				map.put("Data of Row"+String.valueOf(i), innerMap);	
				}

			workbook.close();
			return map;
		} catch (FileNotFoundException e) {
			throw new main.java.com.rp.automation.customisedexception.FrameworkException("File " + fileName + " not found for reading.");
		} catch (IOException e) {
			throw new main.java.com.rp.automation.customisedexception.FrameworkException("Exception occured while reading " + fileName);
		} catch (Exception e) {
			throw new main.java.com.rp.automation.customisedexception.FrameworkException("Unknown Exception while reading " + fileName + "&" + sheetName + "---"
					+ e.getClass() + "---" + e.getMessage());
		}


		
	}
	
	
	
	/**
	 * 
	 * This is to write logs/messages in excel file.
	 * 
	 * @param fileName
	 *            - Complete path of file where error needs to be logged.
	 * @param sheetName
	 *            - Sheet Name from the file, where message needs to be logged.
	 * @param rowNum
	 *            - Row Number where message needs to be written.
	 * @param colNum
	 *            - Column Number where message needs to be written.
	 * @param Message
	 *            - Message/Log @ in case of error.
	 */
	public static void Write_Excel(String fileName, String sheetName, int rowNum, int colNum, String Message)
			throws FileNotFoundException, IOException {
		try {
			FileInputStream file = new FileInputStream(new File(fileName));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFRow row = null;
			XSSFSheet sheet = null;
			try {
				sheet = workbook.getSheet(sheetName);
			} catch (NullPointerException e) {
				sheet = workbook.createSheet(sheetName);
			}
			try {
				row = sheet.getRow(rowNum);
			} catch (NullPointerException e) {
				row = sheet.createRow(rowNum);
			}
			if (row == null) {
				row = sheet.createRow(rowNum);
			}
			row.createCell(colNum).setCellValue(Message);
			file.close();
			FileOutputStream outFile = new FileOutputStream(new File(fileName));
			workbook.write(outFile);
			outFile.flush();
			outFile.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet spreadsheet = workbook.createSheet(sheetName);
			FileOutputStream out = new FileOutputStream(new File(fileName));
			workbook.write(out);
			out.close();
			Write_Excel(fileName, sheetName, rowNum, colNum, Message);
			// throw new com.rameshsot.automation.customisedexception.FrameworkException("File " + fileName + " not found for writing.");
		} catch (IOException e) {
			throw new main.java.com.rp.automation.customisedexception.FrameworkException("Exception occured while writing on " + fileName);
		} catch (Exception e) {
			throw new main.java.com.rp.automation.customisedexception.FrameworkException("Unknown Exception while writing on " + fileName + "&" + sheetName + "---"
					+ e.getClass() + "---" + e.getMessage());
		}

	}


	
}
