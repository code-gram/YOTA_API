package com.yash.yotaapi.util;

import com.yash.yotaapi.constants.QuestionLevelTypes;
import com.yash.yotaapi.entity.Questions;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Questions> convertExcelToListOfQuestion(InputStream inputStream) {

        List<Questions> questions = new ArrayList<>();
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

                Questions ques = new Questions();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cId) {
                        case 0:
//                            ques.setId(Long.valueOf(cell.getStringCellValue()));
                            break;
                        case 1:
                            ques.setQuestionTitle(cell.getStringCellValue());
                            break;
                        case 2:
                            ques.setCorrectAnswer(cell.getStringCellValue());
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
                            ques.setQuestionLevel(QuestionLevelTypes.valueOf(cell.getStringCellValue()));
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