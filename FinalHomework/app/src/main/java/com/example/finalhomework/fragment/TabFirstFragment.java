package com.example.finalhomework.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.finalhomework.R;
import com.example.finalhomework.adapter.RecyclerGridAdapter;
import com.example.finalhomework.adapter.RecyclerStaggeredAdapter;
import com.example.finalhomework.bean.GoodsInfo;
import com.example.finalhomework.constant.ImageList;
import com.example.finalhomework.util.Utils;
import com.example.finalhomework.widget.BannerPager;
import com.example.finalhomework.widget.SpacesItemDecoration;

public class TabFirstFragment extends Fragment implements BannerPager.BannerClickListener,SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG="TabFirstFragment";
    protected View mView;//声明一个视图对象
    protected Context mContext;
    private SwipeRefreshLayout srl_simple; // 声明一个下拉刷新布局对象

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        mContext = getActivity();
        mView=inflater.inflate(R.layout.fragment_tab_first,container,false);

        // 从布局文件中获取名叫banner_pager的横幅轮播条
        initRecyclerGrid(); // 初始化网格布局的循环视图
        initRecyclerStaggered(); // 初始化瀑布流布局的循环视图
        BannerPager banner = mView.findViewById(R.id.banner_pager);
        // 获取横幅轮播条的布局参数
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) banner.getLayoutParams();
        params.height = (int) (Utils.getScreenWidth(mContext) * 250f / 640f);
        // 设置横幅轮播条的布局参数
        banner.setLayoutParams(params);
        // 设置横幅轮播条的广告图片队列
        banner.setImage(ImageList.getDefault());
        // 设置横幅轮播条的广告点击监听器
        banner.setOnBannerListener(this);
        // 开始广告图片的轮播滚动
        banner.start();

        // 从布局文件中获取名叫srl_simple的下拉刷新布局
        srl_simple = mView.findViewById(R.id.srl_simple);
        // 给srl_simple设置下拉刷新监听器
        srl_simple.setOnRefreshListener(this);
        // 设置下拉刷新布局的进度圆圈颜色
        srl_simple.setColorSchemeResources(
                R.color.red, R.color.orange, R.color.green, R.color.blue);

        return mView;
    }

    // 一旦在下拉刷新布局内部往下拉动页面，就触发下拉监听器的onRefresh方法
    public void onRefresh() {
        // 延迟若干秒后启动刷新任务
        mHandler.postDelayed(mRefresh, 2000);
    }

    private Handler mHandler = new Handler(); // 声明一个处理器对象
    // 定义一个刷新任务
    private Runnable mRefresh = new Runnable() {
        @Override
        public void run() {
            // 结束下拉刷新布局的刷新动作
            srl_simple.setRefreshing(false);
        }
    };

    @Override
    // 一旦点击了广告图，就回调监听器的onBannerClick方法
    public void onBannerClick(int position) {
        String desc = String.format("您点击了第%d张图片",position+1);
        Toast.makeText(mContext,desc,Toast.LENGTH_SHORT).show();
    }

    // 初始化网格布局的循环视图
    private void initRecyclerGrid() {
        // 从布局文件中获取名叫rv_grid的循环视图
        RecyclerView rv_grid =  mView.findViewById(R.id.rv_grid);
        // 创建一个垂直方向的网格布局管理器
        GridLayoutManager manager = new GridLayoutManager(mContext, 5);
        // 设置循环视图的布局管理器
        rv_grid.setLayoutManager(manager);
        // 构建一个市场列表的网格适配器
        RecyclerGridAdapter adapter = new RecyclerGridAdapter(mContext, GoodsInfo.getDefaultGrid());
        // 设置网格列表的点击监听器
        adapter.setOnItemClickListener(adapter);
        // 设置网格列表的长按监听器
        adapter.setOnItemLongClickListener(adapter);
        // 给rv_grid设置市场网格适配器
        rv_grid.setAdapter(adapter);
        // 设置rv_grid的默认动画效果
        rv_grid.setItemAnimator(new DefaultItemAnimator());
        // 给rv_grid添加列表项之间的空白装饰
        rv_grid.addItemDecoration(new SpacesItemDecoration(1));
    }

    // 初始化瀑布流布局的循环视图
    private void initRecyclerStaggered() {
        // 从布局文件中获取名叫rv_staggered的循环视图
        RecyclerView rv_staggered = mView.findViewById(R.id.rv_staggered);
        // 创建一个垂直方向的瀑布流布局管理器
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                3, RecyclerView.VERTICAL);
        // 设置循环视图的布局管理器
        rv_staggered.setLayoutManager(manager);
        // 构建一个服装列表的瀑布流适配器
        RecyclerStaggeredAdapter adapter = new RecyclerStaggeredAdapter(mContext, GoodsInfo.getDefaultStag());
        // 设置瀑布流列表的点击监听器
        adapter.setOnItemClickListener(adapter);
        // 设置瀑布流列表的长按监听器
        adapter.setOnItemLongClickListener(adapter);
        // 给rv_staggered设置服装瀑布流适配器
        rv_staggered.setAdapter(adapter);
        // 设置rv_staggered的默认动画效果
        rv_staggered.setItemAnimator(new DefaultItemAnimator());
        // 给rv_staggered添加列表项之间的空白装饰
        rv_staggered.addItemDecoration(new SpacesItemDecoration(3));
    }
}
