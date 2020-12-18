package com.example.finalhomework.bean;

import com.example.finalhomework.R;

import java.util.ArrayList;

public class GoodsInfo {
    public int pic_id;
    public String title;
    public String desc;
    public boolean bPressed;
    public int id;
    private static int seq = 0;

    public GoodsInfo(int pic_id, String title, String desc) {
        this.pic_id = pic_id;
        this.title = title;
        this.desc = desc;
        this.bPressed = false;
        this.id = this.seq;
        this.seq++;
    }


    private static int[] gridImageArray = {R.drawable.pic_01, R.drawable.pic_02, R.drawable.pic_03
            , R.drawable.pic_04, R.drawable.pic_05, R.drawable.pic_06, R.drawable.pic_07
            , R.drawable.pic_08, R.drawable.pic_09, R.drawable.pic_10};
    private static String[] gridTitleArray = {"母婴专区", "营养保健", "美妆护肤", "服饰鞋帽",
            "限时优惠", "成人奶粉", "生鲜美食", "家居生活", "保税专区", "新品体验"};

    public static ArrayList<GoodsInfo> getDefaultGrid() {
            ArrayList<GoodsInfo> gridArray = new ArrayList<GoodsInfo>();
            for (int i = 0; i < gridImageArray.length; i++) {
                gridArray.add(new GoodsInfo(gridImageArray[i], gridTitleArray[i], null));
            }
            return gridArray;
    }



    private static int[] stagImageArray = {R.drawable.skirt01, R.drawable.skirt02, R.drawable.skirt03
            , R.drawable.skirt04, R.drawable.skirt05, R.drawable.skirt06};
    private static String[] stagTitleArray = {"促销价", "惊爆价", "跳楼价", "白菜价", "清仓价", "割肉价"};

    public static ArrayList<GoodsInfo> getDefaultStag() {
        ArrayList<GoodsInfo> stagArray = new ArrayList<GoodsInfo>();
        for (int i = 0; i < stagImageArray.length; i++) {
            stagArray.add(new GoodsInfo(stagImageArray[i], stagTitleArray[i], null));
        }
        return stagArray;
    }





}
