package com.xshop.common.utils;

import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * excel 工具类
 * @program: xshop 
 * @Date: 2018/9/7 16:02 
 * @Author: xuhao
 * @Description:
 **/
public class ExcelOperateUtil {

    /**
     * 去读excel文件内容
     * @param is
     * @param fileType
     * @return
     */
    public static Map<String,List<List<String>>> readExcel(InputStream is,String fileType) {
//        String fileType = path.substring(path.lastIndexOf(".") + 1);
        // return a list contains many list

        Map<String,List<List<String>>> excelContent = new HashedMap();
        //读取excel文件
        try {
            //获取工作薄
            Workbook wb = null;
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                return null;
            }

            int sheetNum = wb.getNumberOfSheets();
            //获取每个Sheet表
            for (int i = 0; i < sheetNum; i++) {
                //读取第一个工作页sheet
                Sheet sheet = wb.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                //每个sheet下的内容
                List<List<String>> lists = new ArrayList();
                //第一行为标题
                for (Row row : sheet) {
                    ArrayList<String> list = new ArrayList<String>();
                    for (Cell cell : row) {
                        //根据不同类型转化成字符串
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        list.add(cell.getStringCellValue());
                    }
                    lists.add(list);
                }

                excelContent.put(sheetName,lists);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return excelContent;
    }


    /**
     * 创建excel文件
     * @param excelContent excel内容
     * @param isHeader 是否有表头
     * @return
     * @throws IOException
     */
    public static Workbook creatExcel(Map<String,List<List<String>>> excelContent,Boolean isHeader) throws IOException {
        //创建新的工作薄
        Workbook wb = new HSSFWorkbook();
        if(Objects.isNull(excelContent) || excelContent.size() < 1){
            return wb;
        }
        for (Map.Entry<String, List<List<String>>> entity : excelContent.entrySet()) {
            String sheetName = entity.getKey();
            List<List<String>> lists = entity.getValue();
            // 创建第一个sheet（页），并命名
            Sheet sheet = wb.createSheet(sheetName);

            // 手动设置列宽,第一个参数表示要为第几列设；第二个参数表示列的宽度，n为列高的像素数。
            for(int i=0;i<lists.get(0).size();i++){
                sheet.setColumnWidth((short) i, (short) (35.7 * 150));
            }

            if(lists == null || lists.size() == 0){
                return wb;
            }

            // 创建两种单元格格式
            CellStyle cs = wb.createCellStyle();
            CellStyle cs2 = wb.createCellStyle();

            // 创建两种字体
            Font f = wb.createFont();
            Font f2 = wb.createFont();

            // 创建第一种字体样式（用于列名）
            f.setFontHeightInPoints((short) 10);
            f.setColor(IndexedColors.BLACK.getIndex());
            f.setBoldweight(Font.BOLDWEIGHT_BOLD);

            // 创建第二种字体样式（用于值）
            f2.setFontHeightInPoints((short) 10);
            f2.setColor(IndexedColors.BLACK.getIndex());

            // 设置第一种单元格的样式（用于列名）
            cs.setFont(f);
            cs.setBorderLeft(CellStyle.BORDER_THIN);
            cs.setBorderRight(CellStyle.BORDER_THIN);
            cs.setBorderTop(CellStyle.BORDER_THIN);
            cs.setBorderBottom(CellStyle.BORDER_THIN);
            cs.setAlignment(CellStyle.ALIGN_CENTER);

            // 设置第二种单元格的样式（用于值）
            cs2.setFont(f2);
            cs2.setBorderLeft(CellStyle.BORDER_THIN);
            cs2.setBorderRight(CellStyle.BORDER_THIN);
            cs2.setBorderTop(CellStyle.BORDER_THIN);
            cs2.setBorderBottom(CellStyle.BORDER_THIN);
            cs2.setAlignment(CellStyle.ALIGN_CENTER);
            //遍历每行数据
            for(int i = 0; i < lists.size(); i++){
                // 创建一行
                Row row = sheet.createRow((short) i);

                //设置表头和内容
                for(int j=0;j<lists.get(i).size();j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue(lists.get(i).get(j));
                    if(i == 0 && isHeader){
                        cell.setCellStyle(cs);
                    }else {
                        cell.setCellStyle(cs2);
                    }
                }
            }
        }

        return wb;
    }
}
