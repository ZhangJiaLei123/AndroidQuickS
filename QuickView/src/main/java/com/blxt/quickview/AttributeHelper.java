package com.blxt.quickview;

import android.content.Context;
import android.util.AttributeSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AttributeHelper {
    public static final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";
    private Context mContext;
    private AttributeSet mAttrs;

    public AttributeHelper(Context context, AttributeSet attrs) {
        this.mContext = context;
        this.mAttrs = attrs;
    }


    public boolean hasAttr(String attribute) {
        return (getValue(attribute) != null);
    }


    public String getValue(String attribute) {
        if (this.mAttrs == null) {
            return null;
        }

        String string = this.mAttrs.getAttributeValue("http://schemas.android.com/apk/res/android", attribute);

        return string;
    }


    public int getInt(String attribute, int def) {
        if (this.mAttrs == null) {
            return def;
        }

        String string = this.mAttrs.getAttributeValue("http://schemas.android.com/apk/res/android", attribute);

        if (string == null) {
            return def;
        }

        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(string);
        string = m.replaceAll("").trim();

        try {
            return Integer.parseInt(string);
        } catch (Exception exception) {


            return def;
        }
    }

    public int getColor(String attribute, int def) {
        String string = this.mAttrs.getAttributeValue("http://schemas.android.com/apk/res/android", attribute);

        if (string == null || string.isEmpty()) {
            return def;
        }

        int color = 0;
        if (string.startsWith("@")) {
            color = Integer.parseInt(string.substring(1, string.length()));
            return getColor(color);
        }
        if (string.startsWith("#")) {
            int backRes = Integer.parseInt(string.substring(3, string.length()), 16);
            return backRes | 0xFF000000;
        }

        return 0;
    }


    public int getResId(String attribute, int def) {
        String string = getValue(attribute);


        if (string == null) {
            return def;
        }

        if (!string.startsWith("@")) {
            return def;
        }
        int index = string.indexOf("/");
        if (index < 0) {
            string = string.substring(1);
            try {
                return Integer.parseInt(string);
            } catch (Exception e) {
                return def;
            }
        }

        String name = string.substring(index + 1);
        String type = string.substring(1, index);

        if (type.equals("mipmap")) {
            return CPResourceUtil.getMipmapId(this.mContext, name);
        }
        if (type.equals("drawable")) {
            return CPResourceUtil.getDrawableId(this.mContext, name);
        }
        if (type.equals("layout")) {
            return CPResourceUtil.getLayoutId(this.mContext, name);
        }
        if (type.equals("string")) {
            return CPResourceUtil.getStringId(this.mContext, name);
        }
        if (type.equals("style")) {
            return CPResourceUtil.getStyleId(this.mContext, name);
        }
        if (type.equals("id")) {
            return CPResourceUtil.getId(this.mContext, name);
        }
        if (type.equals("color")) {
            return CPResourceUtil.getColorId(this.mContext, name);
        }
        if (type.equals("array")) {
            return CPResourceUtil.getArrayId(this.mContext, name);
        }
        return def;
    }


    public String getString(String attribute, String def) {
        String string = getValue(attribute);

        if (string == null) {
            return def;
        }

        if (string.startsWith("@")) {
            int resid = getResId(attribute, 0);
            if (resid == 0) {
                return def;
            }

            return this.mContext.getResources().getString(resid);
        }

        return string;
    }


    public String[] getTextArray(String attribute) {
        String string = getValue(attribute);

        if (string != null && string.startsWith("@")) {
            return this.mContext.getResources().getStringArray(
                    Integer.parseInt(string.substring(1)));
        }
        return null;
    }


    public boolean getBoolean(String attribute, boolean def) {
        String string = getValue(attribute);

        if (string == null) {
            return def;
        }

        if (string != null && string.startsWith("@")) {
            return this.mContext.getResources().getBoolean(
                    Integer.parseInt(string.substring(1)));
        }
        return Boolean.parseBoolean(string);
    }


    public int getColor(int id) {
        return this.mContext.getResources().getColor(id);
    }
}


