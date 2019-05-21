package net.kzn.onlineshopping.service;

import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Product;

@Component
public class ExcelService {
	
	@Autowired
	private ProductDAO productDAO;

	Row row;
	
    public String convertFromExcel(MultipartFile file) {

        List<Product> productLists = new ArrayList<>();
        
        try {        	
        	Workbook workbook;
            String lowerCaseFileName = file.getOriginalFilename().toLowerCase();
            if (lowerCaseFileName.endsWith(".xlsx")) {
            	workbook = new XSSFWorkbook(file.getInputStream());
            } else {
            	workbook = new HSSFWorkbook(file.getInputStream());
            }
            
            Sheet sheet = null;
            for (Sheet s : workbook) {
                if (s.getSheetName().equalsIgnoreCase("Product_Sample")) {
                    sheet = s;
                }
            }
            if (sheet == null) {
            	return "The title of sheet was not matched";
            }

            Iterator< Row> rowIterator = sheet.iterator();
            Map<String, Integer> map = new HashMap<>();

            if (rowIterator.hasNext()) {
                row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cellTitle = cellIterator.next();
                    System.out.println("printing cell value++ "+cellTitle.getStringCellValue());
                    switch (cellTitle.getStringCellValue().trim()) {
                        case "NAME":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "BRAND":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "PRICE":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "QUANTITY":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "EXPIRE":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "DESCRIPTION":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "SKU":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "Category":
                        	map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                        	break;
                    }
                }
            }
            while (rowIterator.hasNext()) {
                Product newProduct = new Product();
                row = rowIterator.next();
                Iterator< Cell> cellIterator = row.cellIterator();
                DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        if (cell.getColumnIndex() == entry.getValue()) {
                            setValueForClass(newProduct, entry.getKey(), (String) formatter.formatCellValue(cell));
                        }
                    }
                }
                // set other value of filled ...
                newProduct.setActive(true);
                newProduct.setSupplierId(1);
                
                productLists.add(newProduct);
                System.out.println("the new size of list is "+productLists.size());
            }

        } catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println("the size of items that were found are :: " +productLists.size());
        
        if (save(productLists) == 0) {
            return productLists.size() + " number of items were inserted/ updated successfully";
        } else {
            return "Something went wrong. Please try again later!";

        }

    }

    private void setValueForClass(Product product, String fieldName, String fieldValue) throws ParseException {
    	System.out.println(fieldValue + "  is the field name  "+ fieldValue.getClass());
        switch (fieldName.trim()) {               
            case "NAME":
            	product.setName((String) fieldValue);
                break;
            case "BRAND":
                product.setBrand((String) fieldValue);
                break;
            case "PRICE":
            	product.setUnitPrice(Double.parseDouble(fieldValue));
                break;
            case "QUANTITY":
            	product.setQuantity( Integer.parseInt(fieldValue));
                break;
            case "EXPIRE":
            	System.out.println("so called date is   "+fieldValue);
                product.setExpireDate(new SimpleDateFormat("yyyy/dd/MM").parse(fieldValue) );
                break;
            case "DESCRIPTION":
                product.setDescription((String) fieldValue);
                break;
            case "SKU":
                product.setSku((String) fieldValue);
                break;
            case "Category":
            	product.setCategoryId(Integer.parseInt(fieldValue));
            	break;
        }
    }

    private int save(List<Product> products) {
        try {
            for (Product p : products) {
                Product tempProduct = null;
                tempProduct = productDAO.getBySku(p.getSku());
                if (tempProduct == null) {
                	System.out.println(p.getSku() + " getting created");
                    productDAO.add(p);
                } else {
                	System.out.println(p.getSku() + " getting updated");
                	productDAO.update(tempProduct);
                }
            }
            System.out.println("Updating Excel File Completed! ");
            return 0;
        } catch (Exception e) {
            System.out.println("Error Saving Excel File Details To Database");
            e.printStackTrace();
            return 1;
        }
    }

	
	
}
