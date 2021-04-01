package com.zyb.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelRead {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\60549\\Desktop\\result2.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file.getPath());
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        XSSFWorkbook work = readExcel();

        XSSFSheet biaozhun = work.getSheetAt(0);
        XSSFSheet xianyou = work.getSheetAt(1);
        XSSFSheet niding = work.getSheetAt(2);

        int biaozhunSheetLength = biaozhun.getLastRowNum();
        int xianyouSheetLength = xianyou.getLastRowNum();
        int nidingSheetLength = niding.getLastRowNum();

        XSSFRow biaozhunRow = null;
        XSSFRow xianyouRow = null;
        XSSFRow nidingRow = null;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= biaozhunSheetLength; i++) {
            biaozhunRow = biaozhun.getRow(i);

            //代码类别
            XSSFCell biaozhunCodeCell = biaozhunRow.getCell(2);
            String codeType = biaozhunCodeCell.getStringCellValue();
            //标准代码个数
            XSSFCell code = biaozhunRow.getCell(6);

            int biaozhunValueCount = (int) code.getNumericCellValue();

            Map<String, String> xianyouMapEnum = new HashMap<>();
            Map<String, String> xianyouMapEnumValue = new HashMap<>();
            for (int j = 0; j <= xianyouSheetLength; j++) {
                xianyouRow = xianyou.getRow(j);

                //拟定标准代码类别(新)
                String xianyouCodeType = xianyouRow.getCell(2).getStringCellValue();
                //枚举值(现状)
                String xianyouEnum = xianyouRow.getCell(3).getStringCellValue();
                //枚举值描述(现状)
                String xianyouEnumValue = xianyouRow.getCell(4).getStringCellValue();
                if (codeType.equals(xianyouCodeType)) {

                    xianyouMapEnum.put(xianyouEnum, null);
                    xianyouMapEnumValue.put(xianyouEnumValue, null);
                }
            }

            Map<String, String> nidingMapEnum = new HashMap<>();
            Map<String, String> nidingMapEnumValue = new HashMap<>();
            for (int j = 0; j <= nidingSheetLength; j++) {
                nidingRow = niding.getRow(j);
                //代码类别(标准)
                String nidingCodeType = nidingRow.getCell(0).getStringCellValue();
                //枚举值
                String nidingEnum = nidingRow.getCell(1).getStringCellValue();
                //枚举值描述
                String nidingEnumValue = nidingRow.getCell(2).getStringCellValue();

                if (codeType.equals(nidingCodeType)) {
                    nidingMapEnum.put(nidingEnum, null);
                    nidingMapEnumValue.put(nidingEnumValue, null);
                }
            }
            int xianyouValueCount = xianyouMapEnum != null ? xianyouMapEnum.size() : 0;
            int nidingValuecount = nidingMapEnum != null ? nidingMapEnum.size() : 0;
            int xianyouValue = xianyouMapEnumValue != null ? xianyouMapEnumValue.size() : 0;
            int nidingValue = nidingMapEnumValue != null ? nidingMapEnumValue.size() : 0;
            if (biaozhunValueCount != xianyouValueCount || biaozhunValueCount != nidingValuecount) {
                sb.append("否");
                sb.append("  各系统代码清单: " + biaozhunValueCount + "个  AIC数据现状及与标准对照关系 :" + xianyouValueCount + "个  AIC拟定标准清单 : " + nidingValuecount + "个");
                sb.append("\r\n");
            } else {
                if (biaozhunValueCount != xianyouValue || biaozhunValueCount != nidingValue) {
                    sb.append("否");
                    sb.append("  AIC数据现状及与标准对照关系 与 AIC拟定标准清单 枚举值不相等: ");
                    sb.append("\r\n");
                } else {
                    sb.append("是");
                    sb.append("\r\n");
                }

            }

        }
        bos.write(sb.toString().getBytes(), 0, sb.toString().getBytes().length);
        bos.flush();
        bos.close();
    }


    /**
     * 读取EXCEL
     *
     * @return
     */
    public static XSSFWorkbook readExcel() {
        XSSFWorkbook xs = null;
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\60549\\Desktop\\test.xlsx"));
            xs = new XSSFWorkbook(file);
        } catch (Exception e) {
            System.out.println("文件未找到");
        }
        return xs;
    }
}
