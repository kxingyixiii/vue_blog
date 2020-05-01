package com.kxingyi.blog.testExcelHandler;

import com.kxingyi.blog.excel.entity.ExportParams;
import com.kxingyi.blog.excel.handler.ExcelExportHandler;
import com.kxingyi.blog.mapper.TypeMapper;
import com.kxingyi.blog.pojo.Type;
import com.kxingyi.blog.utils.model.Page;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest
public class testExcel {
    @Autowired
    private TypeMapper typeMapper;
    @Test
    public void  testExcelExport(){

        Page page = new Page();
        page.setCurrentPage(1);
        page.setPageSize(50);
        page.setSortMethod("asc");
        page.setSortColumn("typeBlogCount");

        List<Type> all = typeMapper.getAll(page);
        ExcelExportHandler excelExportHandler = new ExcelExportHandler();
        ExportParams exportParams = new ExportParams("博客分类", "二级标题", "博客分类sheet", true);
        Workbook sheet = excelExportHandler.createSheet(exportParams, Type.class, all);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File("D:"+File.separator +"type.xls"));
            sheet.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
