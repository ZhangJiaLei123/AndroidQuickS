package com.blxt.quickfile4a;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import androidx.annotation.NonNull;

import com.blxt.qfile.QFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class QFile4a extends QFile {

    public static class PATH
    {
        public static String SDPath = Environment.getExternalStorageDirectory().getPath();

        public static String getAppFilesPath(Context context) { return context.getFilesDir().getPath(); }

        public static String getAppCachePath(Context context) { return context.getCacheDir().getPath(); }
    }

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
    public static File getCacheFile(@NonNull Context context, @NonNull String fileName) {
        File savedir = null;
        if (Environment.getExternalStorageState().equals("mounted")) {
            savedir = new File(context.getExternalCacheDir(), fileName);
        }

        if (savedir == null) {
            savedir = new File(context.getCacheDir(), fileName);
        }

        if (!savedir.exists()) {
            savedir.mkdirs();
        }
        return savedir;
    }

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

}
