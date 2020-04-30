package com.ydh.yudemo.common;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

public class CopyFileUtil {
    // 复制文件 相比较下面那种速度更快些。
    public static boolean copyFile(String source, String target) {
        Log.e("开始时间：", System.currentTimeMillis() + "");

        source = source.replace("\\", "/");
        target = target.replace("\\", "/");

        File source_file = new File(source);
        File target_file = new File(target);

        FileChannel in = null;
        FileChannel out = null;
        if (!source_file.exists() || !source_file.isFile()) {
            return false;
        }
        File parent = target_file.getParentFile();
        // 创建目标文件路径文件夹
        if (!parent.exists()) {
            parent.mkdirs();
        }
        // 判断目标文件是否存在
        if (target_file.exists()) {
            target_file.delete();
        }
        try {
            FileInputStream inStream = null;
            FileOutputStream outStream = null;
            try {
                // 创建目标文件
                if (!target_file.exists()) {
                    target_file.createNewFile();
                }
                inStream = new FileInputStream(source_file);
                outStream = new FileOutputStream(target_file);
                in = inStream.getChannel();
                out = outStream.getChannel();
                in.transferTo(0, in.size(), out);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inStream != null)
                    inStream.close();
                if (in != null)
                    in.close();
                if (outStream != null)
                    outStream.close();
                if (out != null)
                    out.close();

            }
        } catch (IOException e) {
            return false;
        }
        Log.e("结束时间：", System.currentTimeMillis() + "");
        if (!target_file.exists()) {
            return false;
        } else if (source_file.length() != target_file.length()) {
            return false;
        } else {
            return true;
        }
    }

    public static void copyFile2(String oldPath, String newPath) {
        Log.e("开始时间：", System.currentTimeMillis() + "");
        try {
            File oldFile = new File(oldPath);
            //文件存在时
            if (oldFile.exists()) {
                //读入原文件
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while (inStream.read(buffer) != -1) {
                    fs.write(buffer);
                }
                inStream.close();
                fs.close();
                Log.e("结束时间：", System.currentTimeMillis() + "");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
