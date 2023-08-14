package com.yash.yotaapi.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.yash.yotaapi.domain.Question;

public class ExcelHelper {

	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();
		return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}

	public static List<Question> convertExcelToListOfQuestion(InputStream inputStream) {

		List<Question> questions = new ArrayList<>();
		System.out.println("check2");

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheet("questionData");	
			


			int rowNumber = 0;
//			Iterator<Row> iterator = sheet.iterator();
			Iterator<Row> iterator = sheet.iterator();
			
			while (iterator.hasNext()) {
				Row row = iterator.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cells = row.iterator();
				int cId = 0;

				Question ques = new Question();

				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cId) {
					case 0:
						ques.setQuestionType(cell.getStringCellValue());
						break;
					case 1:
						ques.setAnswerType(cell.getStringCellValue());
						break;
					case 2:
						ques.setQuestionLevel(cell.getStringCellValue());
						break;
					case 3:
						ques.setQuestion(cell.getStringCellValue());
						break;
					case 4:
						ques.setA(cell.getStringCellValue());
						break;
					case 5:
						ques.setB(cell.getStringCellValue());
						break;
					case 6:
						ques.setC(cell.getStringCellValue());
						break;
					case 7:
						ques.setD(cell.getStringCellValue());
						break;
					case 8:
						ques.setCorrectAnswer(cell.getStringCellValue());
						break;
					default: break;
					}
					cId++;
				}
				questions.add(ques);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}
}
