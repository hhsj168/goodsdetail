package com.yju.app.shihui.detail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.yju.app.R;
import com.yju.app.base.BaseFragment;
import com.yju.app.shihui.welfare.bean.BannerEntity;
import com.yju.app.utils.FileUtils;
import com.yju.app.utils.JsonUtils;
import com.yju.app.widget.banner.BannerConfig;
import com.yju.app.widget.banner.BannerView;
import com.yju.app.widget.dampscrollview.FixWebView;
import com.yju.app.widget.dampscrollview.TopElasticScrollView;
import com.yju.app.widget.dampscrollview.ScrollViewWrapper;
import com.yju.app.widget.dampscrollview.util.OnScrollViewChangeListener;
import com.yju.app.widget.timecount.CountDownView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品详情的商品介绍页
 */
public class ProductFragment extends BaseFragment {

    @BindView(R.id.detail_banner)
    BannerView detailBanner;
    @BindView(R.id.scrollViewWrapper)
    ScrollViewWrapper scrollViewWrapper;
    @BindView(R.id.countdown_view)
    CountDownView countdownView;

    @BindView(R.id.elastic_scrollview)
    TopElasticScrollView scrollView;
    private OnScrollTabChangeListener listener = null;
    private long testTime = (2 * 24 * 3600 + 10 * 3600 + 18 * 60 + 53) * 1000;

    @BindView(R.id.web)
    FixWebView webView;


    @BindView(R.id.tab_1)
    TextView tab_1;

    @BindView(R.id.tab_2)
    TextView tab_2;

    @BindView(R.id.elastic_scrollview_web)
    TopElasticScrollView elastic_scrollview_web;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_layout, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initBanner();
        initScrooll();
        initData();

        webView.setWebViewClient(new WebViewClient());


        webView.loadUrl("http://www.jianshu.com/");
        tab_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToWeb(v);
            }
        });

        tab_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToProperty(v);
            }
        });
    }

    private void initData() {
        countdownView.setTime(testTime);
        countdownView.start();
    }

    private void initBanner() {
        String banner = FileUtils.readAssert(getActivity(), "banner.txt");
        final BannerEntity entity = JsonUtils.parseJson(banner, BannerEntity.class);
        if (entity != null && entity.banner != null && entity.banner.size() > 0) {
            List<String> bannerList = new ArrayList<>();
            for (int i = 0; i < entity.banner.size(); i++) {
                bannerList.add(entity.banner.get(i).pic);
            }
            detailBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            detailBanner.setAutoPlayEnable(true);
            detailBanner.setImages(bannerList);
            detailBanner.setDelayTime(5000);
            detailBanner.setOnBannerClickListener(new BannerView.OnBannerClickListener() {
                @Override
                public void OnBannerClick(View view, int position) {

                }
            });
        }
    }

    private void initScrooll() {
        listener = (OnScrollTabChangeListener) getActivity();
        scrollViewWrapper.setOnScrollChangeListener(new OnScrollViewChangeListener() {
            @Override
            public void change(int tabId) {
                if (listener != null) {
//                    listener.currentTab(tabId);
                }
            }
        });
        scrollView.setParentScrollView(scrollViewWrapper);

        elastic_scrollview_web.setParentScrollView(scrollViewWrapper);


    }

    //滚动的时候TabLayout的id切换
    public interface OnScrollTabChangeListener {
        void currentTab(int tabId);
    }


    public void changeToWeb(View view) {
        elastic_scrollview_web.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }


    public void changeToProperty(View view) {
        elastic_scrollview_web.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
