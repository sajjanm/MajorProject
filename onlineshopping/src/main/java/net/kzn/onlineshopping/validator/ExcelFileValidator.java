package net.kzn.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.kzn.shoppingbackend.dto.ExcelFile;

public class ExcelFileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ExcelFile.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ExcelFile excelFile = (ExcelFile) target;
		if(excelFile.getFile() == null || excelFile.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Please select a file to upload!");
			return;
		}
		System.out.println("Inside the validator file== THe  file contect type is : "+ excelFile.getFile().getContentType());
		if(! (excelFile.getFile().getContentType().equals("application/vnd.ms-excel") || 
				excelFile.getFile().getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) 
			 )
			{
				errors.rejectValue("file", null, "Please select an .xls or .xlsx file to upload!");
				return;	
			}

	}

}