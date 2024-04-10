package com.yash.yotaapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.yash.yotaapi.domain.AssociateDetails;
import com.yash.yotaapi.domain.ClientQuestion;
import com.yash.yotaapi.domain.NewAssociateDetail;
import com.yash.yotaapi.domain.Nomination;
import com.yash.yotaapi.domain.Question;
import com.yash.yotaapi.domain.Training;

public class ExcelHelper {

	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();
		return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}

	
	public static List<Question> convertExcelToListOfQuestion(InputStream inputStream) {
	 
			List<Question> questions = new ArrayList<>();

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

					Question ques = new Question();

					while (cells.hasNext()) {

						Cell cell = cells.next();

						switch (cId) {

						case 0:

							ques.setQuestion(cell.getStringCellValue());

							break;

						case 1:

							ques.setOption_A(cell.getStringCellValue());

							break;

						case 2:

							ques.setOption_B(cell.getStringCellValue());

							break;

						case 3:

							ques.setOption_C(cell.getStringCellValue());

							break;

						case 4:

							ques.setOption_D(cell.getStringCellValue());

							break;

						case 5:

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

		List<Nomination> nominatonList = new ArrayList<>();
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
				nominatonList.add(nomination);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nominatonList;
	}
	public static List<NewAssociateDetail> convertExcelToListOfAssociates(InputStream inputStream,Training training) {

		List<NewAssociateDetail> associateList = new ArrayList<>();
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

				NewAssociateDetail assDetails = new NewAssociateDetail();

				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cId) {
					case 0:
						assDetails.setEmployeeId((long)cell.getNumericCellValue());
						break;
					case 1:
						assDetails.setEmployeeName(cell.getStringCellValue());
						break;
					case 2:
						assDetails.setEmployeeEmailId(cell.getStringCellValue());
						break;
					case 3:
						assDetails.setEmployeePassword(cell.getStringCellValue());
						break;
					default: break;
					}
					cId++;
				}
				assDetails.setTraining(training);
				associateList.add(assDetails);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return associateList;
	}
	
	public static List<Training> convertExcelToListOfTrainings(InputStream inputStream) {

		List<Training> trainingList = new ArrayList<>();
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

				Training training = new Training();

				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cId) {
					case 0:
						training.setTrainingName(cell.getStringCellValue());
						break;
					case 1:
						training.setAssignedTo(cell.getStringCellValue());
						break;
					case 2:
//						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Define your date format
//                        Date startDate = dateFormat.parse(cell.getDateCellValue());
                        training.setStartDate(cell.getDateCellValue());
						break;
					case 3:
//						SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd"); // Define your date format
//                        Date endDate = dateFormate.parse(cell.getStringCellValue());
	                     training.setEndDate(cell.getDateCellValue());
	                     break;
					case 4:
						training.setAssociateCount((long)0);
						break;
					case 5:
						training.setStatus(cell.getStringCellValue());
						break;
					case 6:
						training.setChangeRequestStatus(cell.getStringCellValue());
						break;
					default: break;
					}
					cId++;
				}
				trainingList.add(training);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return trainingList;
	}
}
