package com.demo.utils;

import com.qiniu.util.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class myUtils {
    /**
     * 判断对象中属性值是否全为空
     *
     * @param object
     * @return
     */
    public static boolean checkObjAllFieldsIsNull(Object object) {
        if (null == object) {
            return true;
        }

        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);

                System.out.print(f.getName() + ":");
                System.out.println(f.get(object));

                if (f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
    public static byte[] getBase64(String path){
        File file = new File(path);
        byte[] base64 = null;
        try {
            BufferedImage image = ImageIO.read(file);
            Integer width = image.getWidth();
            Integer height = image.getHeight();
            System.out.println("宽：" + width + " 高:"+height);

            //输出流
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", stream);
            base64 = Base64.encode(stream.toByteArray(),16);//toByteArray()
        } catch (IOException  e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return base64;
    }

}
