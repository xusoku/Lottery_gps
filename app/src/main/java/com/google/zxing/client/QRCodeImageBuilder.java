package com.google.zxing.client;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

/**
 * 二维码生成builder
 *
 * @author wujiajun
 */
public class QRCodeImageBuilder {

    private Bitmap logoBitmap;
    private Bitmap targetBitmap;

    /**
     * 设置logo
     *
     * @param logo logo图片
     * @return QRCodeImageBuilder
     */
    public QRCodeImageBuilder setLogo(Bitmap logo) {
        logoBitmap = logo;
        return this;
    }

    /**
     * 设置logo
     *
     * @param logo      logo图标
     * @param halfWidth 放缩的宽度
     * @return QRCodeImageBuilder
     */
    public QRCodeImageBuilder setLogo(Bitmap logo, int halfWidth) {
        Matrix m = new Matrix();
        float sx = (float) 2 * halfWidth / logo.getWidth();
        float sy = (float) 2 * halfWidth / logo.getHeight();
        m.setScale(sx, sy);
        logoBitmap = Bitmap.createBitmap(logo, 0, 0, logo.getWidth(), logo.getHeight(), m, false);
        return this;
    }

    /**
     * 设置圆形logo
     *
     * @return QRCodeImageBuilder
     */
    public QRCodeImageBuilder setLogoCircle() {
        if (logoBitmap == null) {
            return this;
        }
        int width = logoBitmap.getWidth();
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(logoBitmap, 0, 0, paint);
        logoBitmap = target;
        return this;
    }

    /**
     * 设置圆形logo外边白色圈
     *
     * @param space 宽度
     * @return QRCodeImageBuilder
     */
    public QRCodeImageBuilder setLogoCircleSpace(int space) {
        if (logoBitmap == null) {
            return this;
        }
        int width = logoBitmap.getWidth() + space * 2;
        int height = logoBitmap.getHeight() + space * 2;
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.save();
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, width, height, paint);
        paint.setXfermode(null);
        canvas.restore();
        canvas.drawBitmap(logoBitmap, space, space, paint);
        logoBitmap = target;
        return this;
    }

    /**
     * 合并二维码
     *
     * @param qrBitmap 二维码图片
     * @return QRCodeImageBuilder
     */
    public QRCodeImageBuilder mergeBitmap(Bitmap qrBitmap) {
        if (logoBitmap == null) {
            return this;
        }
        Bitmap target = Bitmap.createBitmap(qrBitmap.getWidth(), qrBitmap.getHeight(), qrBitmap.getConfig());
        Canvas canvas = new Canvas(target);
        canvas.drawBitmap(qrBitmap, new Matrix(), null);

        int left = (qrBitmap.getWidth() - logoBitmap.getWidth()) / 2;
        int top = (qrBitmap.getHeight() - logoBitmap.getHeight()) / 2;
        canvas.drawBitmap(logoBitmap, left, top, null);
        targetBitmap = target;
        return this;
    }

    /**
     * 开启多彩的世界
     *
     * @param src 要展示的图片
     * @param qr  二维码（作为掩码）
     * @return QRCodeImageBuilder
     */
    public QRCodeImageBuilder createBgQrCode(Bitmap src, Bitmap qr) {
        int width = qr.getWidth();
        int height = qr.getHeight();
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.save();
        canvas.drawBitmap(qr, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(src, new Rect(0, 0, src.getWidth(), src.getHeight()), new Rect(0, 0, width, height), paint);
        paint.setXfermode(null);
        canvas.restore();
        targetBitmap = target;
        return this;
    }

    /**
     * 设置背景
     *
     * @param bg   背景图片
     * @param left 二维码组合图片的x起点
     * @param top  二维码组合图片的y起点
     * @return QRCodeImageBuilder
     */
    public QRCodeImageBuilder setBg(Bitmap bg, int left, int top) {
        Canvas canvas = new Canvas(bg);
        canvas.drawBitmap(targetBitmap, left, top, null);
        targetBitmap = bg;
        return this;
    }

    /**
     * 获取目标图片
     *
     * @return 目标图片
     */
    public Bitmap getTargetBitmap() {
        return targetBitmap;
    }

}
