package org.jymf.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by zhangyuanan on 16/1/15.
 */
public class QRCode {

    public static byte[]encode(String context,int width,int height,String format) throws WriterException,IOException{


        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   // 内容所使用字符集编码

        BitMatrix bitMatrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, width, height, hints);
        // 生成二维码
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"gif",out);

        return out.toByteArray();
    }
}
