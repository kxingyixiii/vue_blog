package com.kxingyi.blog.exception;
/**
 *@QualifiedClassName:
 *@ClassName:数据导出异常
 *@Description:TODO
 *@Author:byliu
 *@Date:2020/4/21 16:01
 *@Paramter:
 *@Return:
*/
import com.kxingyi.blog.enums.ResultEnum;

public class DataExportException extends  DataAcessException {
    public DataExportException(ResultEnum resultEnum) {
        super(resultEnum);
    }

    public DataExportException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum, throwable);
    }

    public DataExportException(Integer errorCode, String msg) {
        super(errorCode, msg);
    }

    public DataExportException(String msg) {
        super(msg);
    }

    public DataExportException(Throwable throwable) {
        super(throwable);
    }

    public DataExportException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
