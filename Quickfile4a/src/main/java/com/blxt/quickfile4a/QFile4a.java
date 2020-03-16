package com.blxt.quickfile4a;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;


import com.blxt.qfile.QFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class QFile4a extends QFile {

    /**
     * android 常用系统路径
     */
    public static class PATH {
        public static String SDPath = Environment.getExternalStorageDirectory().getPath();

        public static String getAppFilesPath(Context context) {
            return context.getFilesDir().getPath();
        }

        public static String getAppCachePath(Context context) {
            return context.getCacheDir().getPath();
        }
    }

    /**
     * android Assets 文件操作
     */
    public static class Assets {

        /**
         * 复制Asstes内文件,
         *
         * @param context
         * @param sourceFileName 原文件, 路径如 path/file.txt
         * @param targetFile     目标文件
         * @return
         */
        public static boolean copyFile4Asstes(Context context, String sourceFileName, File targetFile) {
            InputStream input = null;
            OutputStream output = null;

            try {
                File fp = targetFile.getParentFile();
                if (!fp.exists()) {
                    fp.mkdir();
                }
                if (!targetFile.exists()) {
                    targetFile.createNewFile();
                }

                input = context.getAssets().open(sourceFileName);
                output = new FileOutputStream(targetFile);
                int temp;
                while ((temp = input.read()) != -1) {
                    output.write(temp);
                }
                input.close();
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                CloseableClose(input);
                CloseableClose(output);
            }
            return true;
        }

        /**
         * 从Asstes 读取字符串
         *
         * @param context
         * @param fileName 目标文件, 路径如 path/file.txt
         * @return
         */
        public static String getStr4Assets(Context context, String fileName) {
            String strRes = "";
            InputStreamReader in = null;

            try {
                in = new InputStreamReader(context.getAssets().open(fileName));
                BufferedReader bufferedReader = new BufferedReader(in);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    strRes = strRes + line + "\n";
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception exception) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return strRes;
        }

    }

    /**
     * 检查SD是否存在
     *
     * @return
     */
    public static boolean checkSaveLocationExists() {
        boolean status;
        String sDCardStatus = Environment.getExternalStorageState();

        if (sDCardStatus.equals("mounted")) {
            status = true;
        } else {
            status = false;
        }
        return status;
    }


    /**
     * 获取SD可用大小
     *
     * @return
     */
    public static long getFreeDiskSpace() {
        String status = Environment.getExternalStorageState();
        long freeSpace = 0L;
        if (status.equals("mounted")) {
            try {
                File path = Environment.getExternalStorageDirectory();
                StatFs stat = new StatFs(path.getPath());
                long blockSize = stat.getBlockSize();
                long availableBlocks = stat.getAvailableBlocks();
                freeSpace = availableBlocks * blockSize / 1024L;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return -1L;
        }
        return freeSpace;
    }

}
