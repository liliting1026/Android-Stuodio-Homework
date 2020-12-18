package com.example.finalhomework.bean;

import com.example.finalhomework.R;

import java.util.ArrayList;

public class GoodsInfoGoods {
    public long rowid; // 行号
    public int sn; // 序号
    public String name; // 名称
    public String desc; // 描述
    public float price; // 价格
    public String thumb_path; // 小图的保存路径
    public String pic_path; // 大图的保存路径
    public int thumb; // 小图的资源编号
    public int pic; // 大图的资源编号

    public GoodsInfoGoods() {
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

    // 声明一个手表商品的名称数组
    private static String[] mNameArray = {
            "唇釉", "口红", "洗面奶", "香水", "气垫", "护手霜","眼影","面霜"
    };
    // 声明一个手表商品的描述数组
    private static String[] mDescArray = {
            "CHANEL 香奈儿炫亮魅力印记唇釉 哑光雾面口红",
            "CHANEL 香奈儿可可小姐炫光唇膏 coco滋润口红",
            "CHANEL 香奈儿柔和净肤泡沫面乳 洗面奶温和洁净",
            "CHANEL 香奈儿蔚蓝男士淡香水 bleu木质香",
            "CHANEL 香奈儿气垫水粉底SPF25/PA++果冻气垫",
            "CHANEL 香奈儿护手霜（滋润）鹅蛋 鹅卵石滋润保湿",
            "CHANEL 香奈儿米色时尚眼部彩盘 白桃盘 限定眼影",
            "CHANEL 香奈儿山茶花润泽微精华乳霜 面霜保湿滋润"
    };
    // 声明一个手表手机商品的价格数组
    private static float[] mPriceArray = {330, 330, 435, 690, 360, 520,620,840};
    // 声明一个手表商品的小图数组
    private static int[] mThumbArray = {
            R.drawable.cy_s,R.drawable.kh_s, R.drawable.xmn_s,
            R.drawable.xs_s, R.drawable.qd_s, R.drawable.hss_s,
            R.drawable.yy_s,R.drawable.ms_s
    };
    // 声明一个手表商品的大图数组
    private static int[] mPicArray = {
            R.drawable.cy, R.drawable.kh, R.drawable.xmn,
            R.drawable.xs, R.drawable.qd, R.drawable.hss,
            R.drawable.yy,R.drawable.ms
    };

    // 获取默认的手表信息列表
    public static ArrayList<GoodsInfoGoods> getDefaultList() {
        ArrayList<GoodsInfoGoods> goodsList = new ArrayList<GoodsInfoGoods>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfoGoods info = new GoodsInfoGoods();
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