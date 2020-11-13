package com.example.a006.bean;

import com.example.a006.R;

import java.util.ArrayList;

public class GoodsInfo {
    public long rowid; // 行号
    public int sn; // 序号
    public String name; // 名称
    public String desc; // 描述
    public float price; // 价格
    public String thumb_path; // 小图的保存路径
    public String pic_path; // 大图的保存路径
    public int thumb; // 小图的资源编号
    public int pic; // 大图的资源编号

    public GoodsInfo() {
        rowid = 0L;
        sn = 0;
        name = "";
        desc = "";
        price = 0;
        thumb_path = "";
        pic_path = "";
        thumb = 0;
        pic = 0;
    }

    // 声明一个手机商品的名称数组
    private static String[] mNameArray = {
            "华为 Huawei Mate 40 Pro", "华为 HUAWEI Mate 30", "iPhone 11", "iPhone 11 Max Pro", "iPhone 11 Pro", "iPhone 12","iPhone 12 Mini","iPhone 12 Max Pro"
    };
    // 声明一个手机商品的描述数组
    private static String[] mDescArray = {
            "华为 Huawei Mate 40 Pro 6GB+128GB ",
            "华为 HUAWEI Mate 30 6GB+128GB ",
            "iPhone 11 256GB ",
            "iPhone 11 Max Pro 256GB",
            "iPhone 11 Pro 256GB 256GB",
            "iPhone 12 256GB",
            "iPhone 12 Mini 256GB",
            "iPhone 12 Max Pro 256GB"
    };
    // 声明一个手机商品的价格数组
    private static float[] mPriceArray = {8099, 6399, 5850, 8399, 8150, 7599,6799,13299};
    // 声明一个手机商品的小图数组
    private static int[] mThumbArray = {
            R.drawable.huawei_mate_40_pro, R.drawable.huawei_mate_30_pro, R.drawable.iphone11,
            R.drawable.iphone_11_max_pro, R.drawable.iphone11pro, R.drawable.iphone_12,
            R.drawable.iphone_12_mini,R.drawable.iphone_12_max_pro
    };
    // 声明一个手机商品的大图数组
    private static int[] mPicArray = {
            R.drawable.huawei_mate_40_pro, R.drawable.huawei_mate_30_pro, R.drawable.iphone11,
            R.drawable.iphone_11_max_pro, R.drawable.iphone11pro, R.drawable.iphone_12,
            R.drawable.iphone_12_mini,R.drawable.iphone_12_max_pro
    };

    // 获取默认的手机信息列表
    public static ArrayList<GoodsInfo> getDefaultList() {
        ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo info = new GoodsInfo();
            info.name = mNameArray[i];
            info.desc = mDescArray[i];
            info.price = mPriceArray[i];
            info.thumb = mThumbArray[i];
            info.pic = mPicArray[i];
            goodsList.add(info);
        }
        return goodsList;
    }
}