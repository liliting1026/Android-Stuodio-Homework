package com.example.finalhomework;


import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.finalhomework.adapter.GoodsAdapter;
import com.example.finalhomework.bean.CartInfo;
import com.example.finalhomework.bean.GoodsInfoGoods;
import com.example.finalhomework.database.CartDBHelper;
import com.example.finalhomework.database.GoodsDBHelper;
import com.example.finalhomework.util.DateUtil;
import com.example.finalhomework.util.SharedUtil;

import java.util.ArrayList;

public class SearchResultActvity extends AppCompatActivity implements View.OnClickListener,GoodsAdapter.addCartListener {
    private static final String TAG = "SearchResultActvity";
    private TextView tv_search_result;
    private int mCount;
    private GoodsDBHelper mGoodsHelper;
    private CartDBHelper mCartHelper;
    private ArrayList<GoodsInfoGoods>mSearchArrary=new ArrayList<GoodsInfoGoods>();
    private GridView gv_channel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_actvity);

        // 从布局文件中获取名叫tl_result的工具栏
        Toolbar tl_result = findViewById(R.id.tl_result);
        // 设置工具栏的背景
        tl_result.setBackgroundResource(R.color.blue_light);
        // 设置工具栏的标志图片
        tl_result.setLogo(R.drawable.ic_app);
        // 设置工具栏的标题文字
        tl_result.setTitle("搜索结果页");
        // 设置工具栏的导航图标
        tl_result.setNavigationIcon(R.drawable.ic_back);
        // 使用tl_result替换系统自带的ActionBar
        setSupportActionBar(tl_result);
        gv_channel=findViewById(R.id. gv_channel);
        doSearchQuery(getIntent(), gv_channel);
    }

    // 解析搜索请求页面传来的搜索信息，并据此执行搜索查询操作
    private void doSearchQuery(Intent intent,GridView gv_channel) {
        if (intent != null) {
            // 如果是通过ACTION_SEARCH来调用，即为搜索框来源
            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                // 获取额外的搜索数据
                Bundle bundle = intent.getBundleExtra(SearchManager.APP_DATA);
                String value = bundle.getString("hi");
                // 获取实际的搜索文本
                String queryString = intent.getStringExtra(SearchManager.QUERY);

                mGoodsHelper=GoodsDBHelper.getInstance(this,1);
                mGoodsHelper.openWriteLink();

                mSearchArrary=mGoodsHelper.query(String.format("name like %s%s%s","'%",queryString,"%'"));
                if(mSearchArrary.size()>0)
                {
                    GoodsAdapter adapter =new GoodsAdapter(this,mSearchArrary,this);
                    gv_channel.setAdapter(adapter);
                    gv_channel.setOnItemClickListener(adapter);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 从menu_null.xml中构建菜单界面布局
        getMenuInflater().inflate(R.menu.menu_null, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { // 点击了工具栏左边的返回箭头
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume()
    {

        super.onResume();
        mCount=Integer.parseInt(SharedUtil.getIntance(this).readShared("count","0"));
        mGoodsHelper=GoodsDBHelper.getInstance(this,1);
        mGoodsHelper.openReadLink();
        mCartHelper=CartDBHelper.getInstance(this,1);
        mCartHelper.openWriteLink();

    }
    @Override
    protected  void onPause()
    {
        super.onPause();
        mGoodsHelper.closeLink();
        mCartHelper.closeLink();

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void addToCart(long goods_id) {


        mCount++;
        SharedUtil.getIntance(this).writeShared("count",""+mCount);
        CartInfo info=mCartHelper.queryByGoodsId(goods_id);
        if(info!=null)
        {
            info.count++;
            info.update_time= DateUtil.getNowDateTime("");
            mCartHelper.update(info);
        }
        else
        {

            info=new CartInfo();
            info.goods_id=goods_id;
            info.count=1;
            info.update_time=DateUtil.getNowDateTime("");
            mCartHelper.insert(info);



        }

    }

}