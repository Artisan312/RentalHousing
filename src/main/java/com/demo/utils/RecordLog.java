package com.demo.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

@Component
public class RecordLog {
    private static final String DATE_PATTERN_FULL = "yyyy-MM-dd HH:mm";//日期格式
    private static Logger logger = Logger.getLogger("日志");
    private FileHandler fileHandler = null;
    public RecordLog(){
        logger.setLevel(Level.INFO);
        try {
            File file = new File("src/log/log");//文件路径
            if(file.exists()) {
                file.delete();
            }//如果文件存在就删除
            file.createNewFile();//创立新文件
            fileHandler = new FileHandler("src/log/log.",true);
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return "[ " + getCurrentDateStr(DATE_PATTERN_FULL) + " - Level:"
                            + record.getLevel().getName().substring(0, 1) + " ]-" + record.getMessage() + "\n";
                }
            });
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentDateStr(String pattern) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 记录异常信息
     * @param e 异常
     */
    public void read(Exception e) {
        StringBuffer emsg = new StringBuffer();
        if(e!=null){
            StackTraceElement st = e.getStackTrace()[0];
            String exclass = st.getClassName();
            String method = st.getMethodName();
            emsg.append("[类:" + exclass + "]调用"+ method + "时在第" + st.getLineNumber()
                    + "行代码处发生异常!异常类型:" + e.toString()+"\r\n");
        }
        logger.info(emsg.toString());
    }

    /**
     * 记录错误信息
     * @param e 错误
     */
    public void readAssertion(AssertionError e) {
        StringBuffer emsg = new StringBuffer();
        if(e!=null){
            StackTraceElement st = e.getStackTrace()[0];
            String exclass = st.getClassName();
            String method = st.getMethodName();
            emsg.append("[类:" + exclass + "]调用"+ method + "时在第" + st.getLineNumber()
                    + "行代码处发生错误!错误类型:" + e.toString()+"\r\n");
        }
        logger.info(emsg.toString());
    }

    /**
     * 记录动作
     * @param str  记录信息
     */
    public void readAction(String str) {
        logger.info(str);
    }

}


