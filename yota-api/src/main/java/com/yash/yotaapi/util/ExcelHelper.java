package com.yash.yotaapi.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.yash.yotaapi.domain.ClientQuestion;
import com.yash.yotaapi.domain.Nomination;
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
			XSSFSheet sheet = workbook.getSheetAt(0);	
			
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
						break;
					case 1:
						ques.setQuestion(cell.getStringCellValue());
						break;
					case 2:
						ques.setQuestionLevel(cell.getStringCellValue());
						break;
					case 3:
						ques.setOption_A(cell.getStringCellValue());
						break;
					case 4:
						ques.setOption_B(cell.getStringCellValue());
						break;
					case 5:
						ques.setOption_C(cell.getStringCellValue());
						break;
					case 6:
						ques.setOption_D(cell.getStringCellValue());
						break;
					case 7:
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

	public static List<ClientQuestion> convertExcelToListOfClientQuestion(InputStream inputStream) {

		List<ClientQuestion> questions = new ArrayList<>();

		try {
			XSSFWorkbook wb = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = wb.getSheetAt(0);

			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row row = iterator.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cells = row.iterator();
				int cId = 0;

				ClientQuestion ques = new ClientQuestion();

				while (cells.hasNext()) {
					Cell cell = cells.next();
					switch (cId) {
					case 0:
						ques.setClientId(cell.getStringCellValue());
						break;
					case 1:
						ques.setTechnologyId(cell.getStringCellValue());
						break;
					case 2:
						ques.setClientQuestion(cell.getStringCellValue());
						break;
					case 3:
						ques.setAnswer(cell.getStringCellValue());
						break;
					case 4:
						ques.setLevel(cell.getStringCellValue());
						break;
					default:
						break;
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
	
	public static List<Nomination> convertExcelToListOfNomination(InputStream inputStream) {

		List<Nomination> nominations = new ArrayList<>();
		System.out.println("check2");

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);	
			
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row row = iterator.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cells = row.iterator();
				int cId = 0;

				Nomination nomination = new Nomination();

				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cId) {
					case 0:
						nomination.setEmployeeId((long)cell.getNumericCellValue());
						break;
					case 1:
						nomination.setEmployeeName(cell.getStringCellValue());
						break;
					case 2:
						nomination.setEmployeeEmail(cell.getStringCellValue());
						break;
					default: break;
					}
					cId++;
				}
				nominations.add(nomination);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nominations;
	}
}
