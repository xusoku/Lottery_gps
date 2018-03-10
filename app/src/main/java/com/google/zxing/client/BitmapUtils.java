
package com.google.zxing.client;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * **************************************************************** 文件名称 :
 * BitmapUtils.java 作 者 : hudongsheng 创建时间 : 2014-7-9 下午3:19:42 文件描述 : bitmap工具类
 * 版权声明 : Copyright 2011 © 江苏钱旺智能系统有限公司 修改历史 : 2014-7-9 1.00 初始版本
 */
public class BitmapUtils {

    /**
     * sd卡路径
     */
    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    /**
     * 图片压缩缓存路径
     */
    public final static String ICON_PIC_DIR = SDCARD_PATH+"/Lottery" + "/pic";

    /**
     * 图片缩略图路径
     */
    public final static String ICON_THUMB_DIR = SDCARD_PATH+"/Lottery"+ "/thumbnail";
    /**
     * 图片压缩到指定大小
     *
     * @param data 图片字节数组
     * @param size 大小kb
     * @return 压缩后图片字节数组
     */
    public static Bitmap getBitmap(byte[] data, float size) {
        if (data == null || data.length == 0) {
            return null;
        }
        BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        decodeOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, decodeOptions);

        decodeOptions.inJustDecodeBounds = false;
        decodeOptions.inSampleSize = 2;
        Bitmap tempBitmap = BitmapFactory.decodeByteArray(data, 0, data.length,
                decodeOptions);
        if (tempBitmap != null) {
            byte[] result = bitmapToByte(tempBitmap);
            if (result.length / 1024 <= size) {
                return tempBitmap;
            }
            if (!tempBitmap.isRecycled()) {
                tempBitmap.recycle();
                tempBitmap = null;
            }
            return getBitmap(result, size);
        }
        return tempBitmap;
    }

    /**
     * 图片压缩到指定大小
     *
     * @param fileName 图片路径
     * @param size     大小kb
     * @return 压缩后图片字节数组
     */
    public static Bitmap getBitmap(String fileName, float size) {
        return getBitmap(bitmapToByte(fileName), size);
    }

    /**
     * 图片压缩到指定宽高
     *
     * @param data 图片字节数组
     * @return 压缩后图片字节数组
     */
    public static Bitmap getBitmap(byte[] data, int mMaxWidth, int mMaxHeight) {
        if (data == null || data.length == 0) {
            return null;
        }
        BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        Bitmap bitmap = null;
        if (mMaxWidth == 0 && mMaxHeight == 0) {
            decodeOptions.inPreferredConfig = Config.RGB_565;
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length,
                    decodeOptions);
        } else {
            // If we have to resize this image, first get the natural bounds.
            decodeOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, decodeOptions);
            int actualWidth = decodeOptions.outWidth;
            int actualHeight = decodeOptions.outHeight;

            // Then compute the dimensions we would ideally like to decode to.
            int desiredWidth = getResizedDimension(mMaxWidth, mMaxHeight,
                    actualWidth, actualHeight);
            int desiredHeight = getResizedDimension(mMaxHeight, mMaxWidth,
                    actualHeight, actualWidth);

            // Decode to the nearest power of two scaling factor.
            decodeOptions.inJustDecodeBounds = false;
            // TODO(ficus): Do we need this or is it okay since API 8 doesn't
            // support it?
            // decodeOptions.inPreferQualityOverSpeed =
            // PREFER_QUALITY_OVER_SPEED;
            decodeOptions.inSampleSize = findBestSampleSize(actualWidth,
                    actualHeight, desiredWidth, desiredHeight);
            Bitmap tempBitmap = BitmapFactory.decodeByteArray(data, 0,
                    data.length, decodeOptions);

            // If necessary, scale down to the maximal acceptable size.
            if (tempBitmap != null
                    && (tempBitmap.getWidth() > desiredWidth || tempBitmap
                    .getHeight() > desiredHeight)) {
                bitmap = Bitmap.createScaledBitmap(tempBitmap, desiredWidth,
                        desiredHeight, true);
                tempBitmap.recycle();
            } else {
                bitmap = tempBitmap;
            }
        }
        return bitmap;
    }

    /**
     * 图片压缩到指定宽高
     *
     * @param fileName 图片路径
     * @return 压缩后图片字节数组
     */
    public static Bitmap getBitmap(String fileName, int mMaxWidth,
                                   int mMaxHeight) {
        return getBitmap(bitmapToByte(fileName), mMaxWidth, mMaxHeight);
    }

    /**
     * 图片压缩到指定大小
     *
     * @param data 图片字节数组
     * @param size 大小kb
     * @return 压缩后图片字节数组
     */
    public static byte[] compressBitmap(byte[] data, float size) {
        byte[] result = null;
        if (data == null) {
            return result;
        }
        // 如果图片本身的大小已经小于这个大小了，就没必要进行压缩
        if (data.length / 1024 <= size) {
            return data;
        }
        BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        decodeOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, decodeOptions);

        decodeOptions.inJustDecodeBounds = false;
        decodeOptions.inSampleSize = 2;
        Bitmap tempBitmap = BitmapFactory.decodeByteArray(data, 0, data.length,
                decodeOptions);
        if (tempBitmap != null) {
            result = bitmapToByte(tempBitmap);
            if (!tempBitmap.isRecycled()) {
                tempBitmap.recycle();
                tempBitmap = null;
            }
            if (result.length / 1024 > size) {
                return compressBitmap(result, size);
            }
        }
        return result;
    }

    /**
     * 图片压缩到指定大小
     *
     * @param fileName 图片路径
     * @param size     大小kb
     * @return 压缩后图片字节数组
     */
    public static byte[] compressBitmap(String fileName, float size) {
        return compressBitmap(bitmapToByte(fileName), size);
    }

    /**
     * 图片文件转byte数组
     *
     * @param fileName 图片路径
     * @return 图片字节数组
     */
    public static byte[] bitmapToByte(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(fileName);
        return bitmapToByte(bitmap);
    }

    /**
     * 图片转byte数组
     *
     * @return 图片字节数组
     */
    public static byte[] bitmapToByte(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    private static int findBestSampleSize(int actualWidth, int actualHeight,
                                          int desiredWidth, int desiredHeight) {
        double wr = (double) actualWidth / desiredWidth;
        double hr = (double) actualHeight / desiredHeight;
        double ratio = Math.min(wr, hr);
        float n = 1.0f;
        while ((n * 2) <= ratio) {
            n *= 2;
        }

        return (int) n;
    }

    private static int getResizedDimension(int maxPrimary, int maxSecondary,
                                           int actualPrimary, int actualSecondary) {
        // If no dominant value at all, just return the actual.
        if (maxPrimary == 0 && maxSecondary == 0) {
            return actualPrimary;
        }

        // If primary is unspecified, scale primary to match secondary's scaling
        // ratio.
        if (maxPrimary == 0) {
            double ratio = (double) maxSecondary / (double) actualSecondary;
            return (int) (actualPrimary * ratio);
        }

        if (maxSecondary == 0) {
            return maxPrimary;
        }

        double ratio = (double) actualSecondary / (double) actualPrimary;
        int resized = maxPrimary;
        if (resized * ratio > maxSecondary) {
            resized = (int) (maxSecondary / ratio);
        }
        return resized;
    }

    public static Bitmap parseBitmap(String filePath, int maxWidth,
                                     int maxHeight) {
        File bitmapFile = new File(filePath);

        int angle = readPictureDegree(filePath);

        if (!bitmapFile.exists() || !bitmapFile.isFile()) {
            return null;
        }

        BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        decodeOptions.inInputShareable = true;
        decodeOptions.inPurgeable = true;
        // decodeOptions.inPreferredConfig = Config.RGB_565;
        Bitmap bitmap = null;
        if (maxWidth == 0 && maxHeight == 0) {

            bitmap = BitmapFactory.decodeFile(bitmapFile.getAbsolutePath(),
                    decodeOptions);
        } else {
            // If we have to resize this image, first get the natural bounds.
            decodeOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(bitmapFile.getAbsolutePath(),
                    decodeOptions);
            int actualWidth = decodeOptions.outWidth;
            int actualHeight = decodeOptions.outHeight;

            // Then compute the dimensions we would ideally like to decode to.
            int desiredWidth = getResizedDimension(maxWidth, maxHeight,
                    actualWidth, actualHeight);
            int desiredHeight = getResizedDimension(maxHeight, maxWidth,
                    actualHeight, actualWidth);

            // Decode to the nearest power of two scaling factor.
            decodeOptions.inJustDecodeBounds = false;
            decodeOptions.inSampleSize = findBestSampleSize(actualWidth,
                    actualHeight, desiredWidth, desiredHeight);
            Bitmap tempBitmap = BitmapFactory.decodeFile(
                    bitmapFile.getAbsolutePath(), decodeOptions);
            // If necessary, scale down to the maximal acceptable size.
            if (tempBitmap != null
                    && (tempBitmap.getWidth() > desiredWidth || tempBitmap
                    .getHeight() > desiredHeight)) {
                bitmap = Bitmap.createScaledBitmap(tempBitmap, desiredWidth,
                        desiredHeight, true);
                tempBitmap.recycle();
            } else {
                bitmap = tempBitmap;
            }

        }

        return rotaingImageView(angle, bitmap);
    }

    public static Bitmap parseBitmap(ContentResolver cr, Uri originalUri, int maxWidth,
                                     int maxHeight) throws Exception {
        InputStream input = cr.openInputStream(originalUri);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        options.inPreferredConfig = Config.RGB_565;
        options.inJustDecodeBounds = true;
//        options.outHeight = maxHeight;
//        options.outWidth = maxWidth;
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, options);
        input.close();
        return bitmap;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        if (angle == 0 || bitmap == null) {
            return bitmap;
        }

        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return resizedBitmap;
    }


    /**
     * 上传图片如果不是原图上传，需要压缩
     *
     * @param filePath
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    public static String createUploadBitmap(String filePath, String dir,
                                            String mark, int maxWidth, int maxHeight) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }

        int degree = readPictureDegree(filePath);
        // step1.计算最佳压缩比 inSampleSize=1 不用压缩
        BitmapFactory.Options decodeOptions = createOptions(filePath, maxWidth,
                maxHeight);
        if (decodeOptions == null) {
            return null;
        }

        if (decodeOptions.inSampleSize == 1 && degree == 0) {
            return filePath;
        }

        File bitmapFile = new File(filePath);
        String fileName = bitmapFile.getName();
        String fileExtension = ".jpg";
        String uploadFilePath = null;
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex > 0) {
            uploadFilePath = dir + "/" + fileName.substring(0, lastDotIndex)
                    + mark + fileExtension;
        } else {
            uploadFilePath = dir + "/" + fileName + mark;
        }

        // step2.判断否存在相同的图片，如果存在直接返回
        File uploadFile = new File(uploadFilePath);
        if (uploadFile.exists()) {
            return uploadFilePath;
        }

        // step3.压缩图片
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, decodeOptions);
        if (bitmap == null) {
            return filePath;
        }

        // step4.旋转角度
        if (degree != 0) {
            bitmap = rotaingImageView(degree, bitmap);
        }

        // step5.将压缩的图片存入sdcard
        saveBitmapToSD(uploadFilePath, bitmap);
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }

        return uploadFilePath;
    }

    /**
     * 保存图片到sd卡中
     *
     * @param filePath 保存的路径
     * @param bitmap   源文件
     * @return
     */
    public static boolean saveBitmapToSD(String filePath, Bitmap bitmap) {
        FileOutputStream fos = null;
        boolean isSuccess = false;
        try {
            fos = new FileOutputStream(filePath);
            if (null != fos) {
                isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                        fos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }

    public static BitmapFactory.Options createOptions(String filePath,
                                                      int maxWidth, int maxHeight) {
        File bitmapFile = new File(filePath);

        if (!bitmapFile.exists() || !bitmapFile.isFile()) {
            return null;
        }

        BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        decodeOptions.inInputShareable = true;
        decodeOptions.inPurgeable = true;
        decodeOptions.inDither = false;
        // decodeOptions.inPreferredConfig = Config.RGB_565;
        if (maxWidth == 0 && maxHeight == 0) {

            decodeOptions.inJustDecodeBounds = false;
            decodeOptions.inSampleSize = 1;
        } else {
            // If we have to resize this image, first get the natural bounds.
            decodeOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(bitmapFile.getAbsolutePath(),
                    decodeOptions);
            int actualWidth = decodeOptions.outWidth;
            int actualHeight = decodeOptions.outHeight;

            // Then compute the dimensions we would ideally like to decode to.
            int desiredWidth = getResizedDimension(maxWidth, maxHeight,
                    actualWidth, actualHeight);
            int desiredHeight = getResizedDimension(maxHeight, maxWidth,
                    actualHeight, actualWidth);

            // Decode to the nearest power of two scaling factor.
            decodeOptions.inJustDecodeBounds = false;
            decodeOptions.inSampleSize = findBestSampleSize(actualWidth,
                    actualHeight, desiredWidth, desiredHeight);
        }

        return decodeOptions;
    }

    public static File getFileFromMediaUri(Context ac, Uri uri) {
        if (uri.getScheme().toString().compareTo("content") == 0) {
            ContentResolver cr = ac.getContentResolver();
            Cursor cursor = cr.query(uri, null, null, null, null);// 根据Uri从数据库中找
            if (cursor != null) {
                cursor.moveToFirst();
                String filePath = cursor.getString(cursor.getColumnIndex("_data"));// 获取图片路径
                cursor.close();
                if (filePath != null) {
                    return new File(filePath);
                }
            }
        } else if (uri.getScheme().toString().compareTo("file") == 0) {
            return new File(uri.toString().replace("file://", ""));
        }
        return null;
    }


    //4.4及以上系统使用这个方法处理图片
    @TargetApi(19)
    public static Bitmap handleImageOnKitKat(Context context, Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(context, uri)) {
            //如果是document类型的Uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];  //解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(context, contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果不是document类型的Uri,则使用普通方式处理
            imagePath = getImagePath(context, uri, null);
        }
        return getImage(imagePath);
    }

    //4.4以下系统使用这个方法处理图片
    public static Bitmap handleImageBeforeKitKat(Context context, Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(context, uri, null);
        return getImage(imagePath);
    }

    public static String getImagePath(Context context, Uri uri, String selection) {
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //对bitmap进行质量压缩
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    //传入图片路径，返回压缩后的bitmap
    public static Bitmap getImage(String srcPath) {
        if (TextUtils.isEmpty(srcPath))  //如果图片路径为空 直接返回
            return null;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }
}
