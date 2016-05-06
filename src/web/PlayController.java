package org.jymf.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;
import org.jymf.entity.UserRecord;
import org.jymf.service.*;
import org.jymf.service.impl.UserServiceImpl;
import org.jymf.utils.*;
import org.jymf.utils.drawaward.CigConfigBox;
import org.jymf.utils.drawaward.CigConfigCarton;
import org.jymf.utils.drawaward.CigDrawBox;
import org.jymf.utils.drawaward.CigDrawCarton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 主处理类,所有的路径都在这个类中处理
 * @author Zhang
 * @version 0.1
 */
@Controller
@RequestMapping(value = "/play")
public class PlayController {

    private final int QrCodeSize = 430;
    private final int BoxOrCartonIndex = 6;

    private static Logger logger = LoggerFactory.getLogger(PlayController.class);
    @Autowired
    private LabelInfoService lablInfoService;
    @Autowired
    private LabelIndexService labelIndexSer;
    @Autowired
    private UserRecordService userRecordService;
    @Autowired
    private UserService userSer;
    @Autowired
    private PlayService playSer;
    @Autowired
    private CigDrawBox cigDrawBox;
    @Autowired
    private CigDrawCarton cigDrawCarton;

    @RequestMapping(value = "/go/{pathName}", method = {RequestMethod.POST, RequestMethod.GET})
    public String gotoPage(@PathVariable("pathName") String pathName) {
        return "/play/" + pathName;
    }


    /**
     * 用于对数据进行处理后引导到抽奖初始页面
     * @param request 请求的HttpRequest对象
     * @return 根据相应的逻辑跳到相应页面
     */
    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String gotoIndex(HttpServletRequest request) {
        String allString;
        try {
            //数据验证
            allString = checkData(request);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            return "/error/introduction";
        }
        allString = allString.replaceAll("\r", "");//由于客户端传来的数据中含有换行符,先去掉再嵌入前端页面
        allString = allString.replaceAll("\n", "");
        request.setAttribute("allString", allString);
        return "/play/index";
    }

    /**
     * gotoIndex函数的测试版本,传入的数据无需经过checkdata,便于本地做测试,其它逻辑同gotoIndex函数
     * @param request
     * @return
     */
    @RequestMapping(value = "/test/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String testGotoIndex(HttpServletRequest request) {
        String allString;
        try {
            allString = checkDataTest(request);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            return "/error/introduction";
        }
        allString = allString.replaceAll("\r", "");
        allString = allString.replaceAll("\n", "");
        request.setAttribute("allString", allString);
        return "/play/index";
    }

    /**
     * 用于中奖后将data中数据生成二维码,显示在页面中
     * @param request
     * @param response HttpResponse实例,将生成的二维码图片数据返回
     * @throws IOException
     * @throws WriterException
     */
    @RequestMapping(value = "/qrCode", method = {RequestMethod.POST, RequestMethod.GET})
    public void qrcode(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, WriterException {

        String data = request.getParameter("data");

        response.setContentType("image/jpg");
        logger.info("使用数据: {} 生成二维码", data);
        byte[] img = QRCode.encode(data, QrCodeSize, QrCodeSize, "jpg");

        OutputStream stream = response.getOutputStream();
        stream.write(img);
        stream.flush();
        stream.close();

    }

    /**
     * 抽奖相关
     * @param allString 包含了playid,labelid,,userid
     * @param request
     * @return
     */
    @RequestMapping(value = "/drawAward", method = {RequestMethod.POST, RequestMethod.GET})
    public String drawAward(@RequestParam(value = "allString") String allString, HttpServletRequest request) {
        UserRecord userRecord = JsonUtil.toObject(allString, UserRecord.class);
        String userid = userRecord.getUserid();
        String labelid = userRecord.getLabelid();
        String playid = userRecord.getPlayid() + "";
        boolean isWin = false;
         //判断labelid的第七位是否为1和8,不是的话进入错误页面
        if (labelid.charAt(BoxOrCartonIndex) == '1') {
            isWin = cigDrawBox.drawMain();
        } else if (labelid.charAt(BoxOrCartonIndex) == '8') {
            isWin = cigDrawCarton.drawMain();
        } else {
            return "/error/introduction";
        }


        if (isWin) {
            userRecord.setState(CigDrawBox.STATUS_WIN_NOTGET);
            userRecordService.insertUserRecord(userRecord);
            request.setAttribute("userid", userid);
            request.setAttribute("labelid", labelid);
            request.setAttribute("playid", playid);
            logger.info("labelId:{}  中奖", labelid);
            return "/play/yes";
        } else {
            userRecord.setState(CigDrawBox.STATUS_NOTWIN);
            userRecordService.insertUserRecord(userRecord);
            logger.info("labelId:{}  未中奖", labelid);
            return "/play/no";
        }
    }

    /**
     * 兑奖相关
     * @param request
     * @return
     */
    @RequestMapping(value = "/expiry", method = {RequestMethod.POST, RequestMethod.GET})
    public String expiry(HttpServletRequest request) {
        String allString;
        try {
            allString = checkData(request);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            return "/error/introduction";
        }
        JSONObject jsonObject = JSONObject.fromObject(allString);

        String userId = jsonObject.getString("userid");
        String labelId = jsonObject.getString("labelid");
        String playId = jsonObject.getString("playid");

        int status = userRecordService.getStateByLabelId(labelId);
        System.out.println(status);

        if (status == CigDrawBox.STATUS_WIN_GET) {
            return "/play/expiry_used";
        }
        if (status == CigDrawBox.STATUS_WIN_NOTGET_OOD) {
            return "/play/expiry_odd";
        }
        int playIdInt = Integer.parseInt(playId);
        if (!playSer.isIndate(playIdInt)) {
            userRecordService.updateStateByLabelId(CigDrawBox.STATUS_WIN_NOTGET_OOD, labelId);
            return "/play/expiry_odd";
        } else {
            userRecordService.updateStateByLabelId(CigDrawBox.STATUS_WIN_GET, labelId);
            return "/play/expiry_succ";
        }
    }

    @RequestMapping(value = "/test/expiry", method = {RequestMethod.POST, RequestMethod.GET})
    public String testExpiry(HttpServletRequest request) {
        String allString;
        try {
            allString = checkDataTest(request);
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            return "/error/introduction";
        }
        JSONObject jsonObject = JSONObject.fromObject(allString);

        String userId = jsonObject.getString("userid");
        String labelId = jsonObject.getString("labelid");
        String playId = jsonObject.getString("playid");

        int status = userRecordService.getStateByLabelId(labelId);
        System.out.println(status);
        if (status == CigDrawBox.STATUS_WIN_GET) {
            return "/play/expiry_used";
        }
        if (status == CigDrawBox.STATUS_WIN_NOTGET_OOD) {
            return "/play/expiry_odd";
        }
        int playIdInt = Integer.parseInt(playId);
        if (!playSer.isIndate(playIdInt)) {
            userRecordService.updateStateByLabelId(CigDrawBox.STATUS_WIN_NOTGET_OOD, labelId);
            return "/play/expiry_odd";
        } else {
            userRecordService.updateStateByLabelId(CigDrawBox.STATUS_WIN_GET, labelId);
            return "/play/expiry_succ";
        }
    }

    /**
     * 用于动态加载抽奖配置文件
     * @param request
     * @return
     */
    @RequestMapping(value = "/test/reload", method = {RequestMethod.POST, RequestMethod.GET})
    public String reload(final HttpServletRequest request) {
        CigConfigBox.reload();
        CigConfigCarton.reload();
        request.setAttribute("message", "OK");
        return "/error/mobile";
    }


    /**
     * 对request中的data和sign做数据处理.
     * @param request
     * @return 处理后的data数据
     * @throws Exception
     */
    private String checkData(final HttpServletRequest request) throws Exception {

        String data = request.getParameter("data");
        String sign = request.getParameter("sign");

        if (null == data || null == sign || "".equals(data.trim()) || "".equals(sign.trim())) {
            throw new Exception("非法请求");
        }

        JYHexString hex = new JYHexString();
        MD5keyBean md5 = new MD5keyBean();

        if (sign.equals(md5.getkeyBeanofStr(RSAUtil.publicKey.substring(0, 10)))) {
            // 私钥解密
            byte[] resultByte = null;
            try {
                resultByte = RSAUtil.decryptByPrivateKey(hex.hexStringToBytes(data), RSAUtil.privateKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String resultStr = new String(resultByte, "UTF-8");
            return resultStr;

        } else {
            logger.warn("一次非法请求  ip:{}", request.getRemoteHost());
            throw new Exception("非法签名");
        }
    }

    private String checkDataTest(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        return data;
    }
}
