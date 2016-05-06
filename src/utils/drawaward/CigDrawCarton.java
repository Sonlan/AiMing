package org.jymf.utils.drawaward;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Properties;
import java.util.Random;

/**
 * 条抽奖记录文件处理类
 * @author Zhang
 * @version 0.1
 */
@Service("cigdrawcarton")
public class CigDrawCarton {

    private static Logger logger = LoggerFactory.getLogger(CigDrawCarton.class);
    public static final int STATUS_WIN_NOTGET = 0;
    public static final int STATUS_WIN_GET = 1;
    public static final int STATUS_WIN_NOTGET_OOD = 2;
    public static final int STATUS_NOTWIN = 3;

    public static final String recordFilePath = System.getProperty("catalina.base")+"/logs/jymfplay/record/cigawardcarton.properties";


    public static int count = -1;
    public static Boolean isAwardAvailable = null;

    //初始加载配置文件
    static {
        try {
            Properties properties = new Properties();
            logger.info(System.getProperty("user.dir"));
            logger.info(recordFilePath);
            InputStream inputStream = new FileInputStream(recordFilePath);
            //InputStream resourceAsStream = CigDrawBox.class.getResourceAsStream("/cigawardcarton.properties");
            properties.load(inputStream);
            count = Integer.parseInt(properties.getProperty("count"));
            String isAwardAvailableString = properties.getProperty("isAwardAvailable");
            if (isAwardAvailableString.equals("true")){
                isAwardAvailable = true;
            } else if(isAwardAvailableString.equals("false")){
                isAwardAvailable = false;
            } else {
                throw new IOException();
            }
        } catch (Exception e) {
            logger.error("加载record文件失败");
            e.printStackTrace();
        }
    }

    /**
     * 将count和isAwardAvailable同步到记录文件
     */
    public void update(){
        try {
            Properties properties = new Properties();
            properties.put("isAwardAvailable",isAwardAvailable.toString());
            properties.put("count",count+"");
            //String path = this.getClass().getResource("/cigawardbox.properties").getPath();
            OutputStream outputStream = new FileOutputStream(new File(recordFilePath));
            properties.store(outputStream,"JYMF");
            logger.info("更新记录文件  count:{},isAwardAvailable:{}",count,isAwardAvailable);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean randomDraw(){
        Random random = new Random();
        int target = random.nextInt(CigConfigCarton.ratio);
        if (target == 0){
            return true;
        }
        return false;
    }

    /**
     * 主抽奖逻辑
     * @return 是否中奖
     */
    public boolean drawMain(){
        synchronized (CigDrawCarton.class){
            count++;
            if (count > CigConfigCarton.lablenum){
                update();
                return false;
            }

            if (count% CigConfigCarton.gap == 1){
                isAwardAvailable = true;
            }

            if (isAwardAvailable){
                if (count% CigConfigCarton.gap == CigConfigCarton.luckynum){
                    logger.info("获奖  命中luckyNum  {}",count);
                    isAwardAvailable = false;
                    update();
                    return true;
                }

                if (randomDraw()){
                    logger.info("获奖 {}",count);
                    isAwardAvailable = false;
                    update();
                    return true;
                } else {
                    update();
                    return false;
                }

            } else {
                update();
                return false;
            }
        }
    }
}
