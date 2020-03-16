package com.blxt.quicktools.system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class Memory {
    private static final String TAG = Memory.class.getName();


    private static final String MEM_INFO_PATH = "/proc/meminfo";


    public static final String MEMTOTAL = "MemTotal";

    public static final String MEMFREE = "MemFree";


    public static long getMemoryAll(Context context) {
        String path = Environment.getDataDirectory().getPath();
        StatFs statFs = new StatFs(path);
        long blockSize = statFs.getBlockSize();
        long totalBlocks = statFs.getBlockCount();
        long availableBlocks = statFs.getAvailableBlocks();
        long useBlocks = totalBlocks - availableBlocks;

        long rom_length = totalBlocks * blockSize;

        return rom_length;
    }

    public static String getTotalMemory(Context context) { return getMemInfoIype(context, "MemTotal"); }

    public static String getMemoryFree(Context context) { return getMemInfoIype(context, "MemFree"); }


    public static String getMemInfoIype(Context context, String type) {
        try {
            FileReader fileReader = new FileReader("/proc/meminfo");
            BufferedReader bufferedReader = new BufferedReader(fileReader, 4096);
            String str = null; do {  }
            while ((str = bufferedReader.readLine()) != null &&
                            !str.contains(type));



            bufferedReader.close();


            String[] array = str.split("\\s+");

            int length = Integer.valueOf(array[1]).intValue() * 1024;
            return Formatter.formatFileSize(context, length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    public static long getSdSize() {
        File sdcardDir = Environment.getExternalStorageDirectory();

        StatFs statFs = new StatFs(sdcardDir.getPath());
        long blockSize = statFs.getBlockSizeLong();

        long totalSize = statFs.getBlockCountLong();

        return blockSize * totalSize;
    }

    public static long getSdAvailableSize() {
        String sdcard = Environment.getExternalStorageState();
        String state = "mounted";
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        if (sdcard.equals(state)) {


            long blockSize = statFs.getBlockSize();

            long blockavailable = statFs.getAvailableBlocks();

            long blockavailableTotal = blockSize * blockavailable / 1000L / 1000L;
            return blockSize * blockavailable;
        }

        return -1L;
    }

    @SuppressLint({"NewApi"})
    public static long getRomSize() {
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize = statFs.getBlockSizeLong();
        long tatalBlocks = statFs.getBlockCountLong();
        return blockSize * tatalBlocks;
    }

    @SuppressLint({"NewApi"})
    public static long getRomAvailableSize() {
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize = statFs.getBlockSizeLong();
        long availableBlocks = statFs.getAvailableBlocksLong();
        return blockSize * availableBlocks;
    }

    public static String getSizeStr(Context context, long size) { return Formatter.formatFileSize(context, size); }

    public static String getSizeStr2(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024L) {
            fileSizeString = df.format(fileS) + "B";
        } else if (fileS < 1048576L) {
            fileSizeString = df.format(fileS / 1024.0D) + "KB";
        } else if (fileS < 1073741824L) {
            fileSizeString = df.format(fileS / 1048576.0D) + "MB";
        } else {
            fileSizeString = df.format(fileS / 1.073741824E9D) + "G";
        }
        return fileSizeString;
    }
}
