package org.jymf.utils.drawaward;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 盒抽奖配置文件类
 * @author Zhang
 * @version 0.1
 */
public class CigConfigBox {

    private static Logger logger = LoggerFactory.getLogger(CigConfigBox.class);

    public static int lablenum = -1;
    public static int gap = -1;
    public static int ratio = -1;
    public static int luckynum = -1;

    /**
     * 用于加载盒标配置文件
     */
    public static void reload(){
        try {
            InputStream inputStream = CigConfigBox.class.getResourceAsStream("/cigconfig.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            lablenum = Integer.parseInt(properties.getProperty("lablenum_box"));
            gap = Integer.parseInt(properties.getProperty("gap_box"));
            ratio = Integer.parseInt(properties.getProperty("ratio_box"));
            luckynum = Integer.parseInt(properties.getProperty("luckynum_box"));
            logger.info("加载box配置文件成功 lablenum:{}  gap:{}  ratio:{}  luckynum{}",lablenum,gap,ratio,luckynum);
        } catch (IOException e) {
            logger.error("加载cigconfig.properties配置文件失败");
            e.printStackTrace();
        }
    }

    /**
     * 载入系统时同时加载配置文件
     */
    static {
        reload();
    }
}
