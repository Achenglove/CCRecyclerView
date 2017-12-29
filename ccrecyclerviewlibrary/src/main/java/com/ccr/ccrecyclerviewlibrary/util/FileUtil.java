package com.ccr.ccrecyclerviewlibrary.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

//import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件操作
 *
 * @author zhangyb@ifenguo.com
 * @createDate 2015年3月10日
 */
public class FileUtil {

    /**
     * 读取文件为byte[]
     *
     * @param filePath
     * @return
     */
    public static byte[] readFile(String filePath) {
        final File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        try {
            InputStream stream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            stream.read(buffer);
            stream.close();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存文件
     *
     * @param filePath
     * @param data
     */
    public static void saveFile(String filePath, byte[] data) {
        File targetFile = new File(filePath);
        FileOutputStream osw;
        try {
            if (!targetFile.exists()) {
                targetFile.createNewFile();
                osw = new FileOutputStream(targetFile);
                osw.write(data);
                osw.close();
            } else {
                osw = new FileOutputStream(targetFile, true);
                osw.write(data);
                osw.flush();
                osw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件拷贝
     *
     * @param prefile
     * @param newfile
     */
    public static int copyFile(String prefile, String newfile) {
        try {
            InputStream fosfrom = new FileInputStream(prefile);
            OutputStream fosto = new FileOutputStream(newfile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return 0;

        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * asset文件夹读取文�?
     *
     * @param context
     * @param path
     * @return
     */
    public static String readTxtFromAsset(Context context, String path) {
        Resources resource = context.getResources();
        AssetManager am = resource.getAssets();
        InputStream is = null;
        String content = "";
        try {
            is = am.open(path);
            int length = is.available();
            byte[] buffer = new byte[length];
            is.read(buffer);
//            content = EncodingUtils.getString(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 从assets目录中复制整个文件夹内容
     *
     * @param context Context 使用CopyFiles类的Activity
     * @param oldPath String 原文件路径 如：/aa
     * @param newPath String 复制后路径 如：xx:/bb/cc
     */
    public static void copyFilesFassets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);// 获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {// 如果是目录
                File file = new File(newPath);
                file.mkdirs();// 如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
            } else {// 如果是文件
                LogUtil.i("msg", "oldPath--" + oldPath);
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取 buffer字节
                    fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
                }
                fos.flush();// 刷新缓冲区
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 如果捕捉到错误则通知UI线程
        }
    }

    /**
     * 从sd卡读文件
     *
     * @param path
     * @return
     */
    public static String readTxtFromSd(String path) {
        String content = "";
        InputStream stream = null;
        final File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        try {
            stream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            stream.read(buffer);
//            content = EncodingUtils.getString(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件�?
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文�?
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔�?
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则�?�?
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文�?包括子目�?
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文�?
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目�?
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        return dirFile.delete();
    }

}
