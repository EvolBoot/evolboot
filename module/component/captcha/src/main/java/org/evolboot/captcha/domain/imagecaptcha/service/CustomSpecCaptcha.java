package org.evolboot.captcha.domain.imagecaptcha.service;


import com.wf.captcha.base.Captcha;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * png格式验证码
 * Created by 王帆 on 2018-07-27 上午 10:08.
 */
public class CustomSpecCaptcha extends Captcha {

    private static final String[] FONT_NAMES = new String[]{"actionj.ttf", "epilog.ttf", "fresnel.ttf", "headache.ttf", "lexo.ttf", "prefix.ttf", "progbot.ttf", "ransom.ttf", "robot.ttf", "scandal.ttf"};

    private final static Font FONT;

    static {
        try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Captcha.class.getResourceAsStream("/" + FONT_NAMES[0]))).deriveFont(Font.BOLD, 32);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CustomSpecCaptcha() {
    }

    public CustomSpecCaptcha(int width, int height) {
        this();
        setWidth(width);
        setHeight(height);
    }

    public CustomSpecCaptcha(int width, int height, int len) {
        this(width, height);
        setLen(len);
    }

    public CustomSpecCaptcha(int width, int height, int len, Font font) {
        this(width, height, len);
        setFont(font);
    }

    @Override
    public Font getFont() {
        return FONT;
    }

    /**
     * 生成验证码
     *
     * @param out 输出流
     * @return 是否成功
     */
    @Override
    public boolean out(OutputStream out) {
        return graphicsImage(textChar(), out);
    }

    @Override
    public String toBase64() {
        return toBase64("data:image/png;base64,");
    }

    /**
     * 生成验证码图形
     *
     * @param strs 验证码
     * @param out  输出流
     * @return boolean
     */
    private boolean graphicsImage(char[] strs, OutputStream out) {
        try {
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) bi.getGraphics();
            // 填充背景
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, width, height);
            // 抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 画干扰圆
            drawOval(2, g2d);
            // 画干扰线
            g2d.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
            drawBesselLine(1, g2d);
            // 画字符串
            g2d.setFont(getFont());
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int fW = width / strs.length;  // 每一个字符所占的宽度
            int fSp = (fW - (int) fontMetrics.getStringBounds("W", g2d).getWidth()) / 2;  // 字符的左右边距
            for (int i = 0; i < strs.length; i++) {
                g2d.setColor(color());
                int fY = height - ((height - (int) fontMetrics.getStringBounds(String.valueOf(strs[i]), g2d).getHeight()) >> 1);  // 文字的纵坐标
                g2d.drawString(String.valueOf(strs[i]), i * fW + fSp + 3, fY - 3);
            }
            g2d.dispose();
            ImageIO.write(bi, "png", out);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}