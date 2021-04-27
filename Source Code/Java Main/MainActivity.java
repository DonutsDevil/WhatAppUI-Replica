    package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.whatsapp.Adapters.FragmentAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private FragmentAdapter fragmentAdapter;
    private View searchBarView;
    private Toolbar toolbar;
    private LinearLayout topSearchOption;
    private LinearLayout bottomSearchOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        setSupportActionBar(toolbar);
        fragmentAdapter = new FragmentAdapter(this,searchBarView,this);
        viewPager2.setAdapter(fragmentAdapter);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0: {
                        View view = (View) LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_text_layout, null);
                        TextView textView = view.findViewById(R.id.tab_text);
                        View notify = view.findViewById(R.id.notify_status);
                        notify.setBackground(null);
                        textView.setText("CHAT");
                        tab.setCustomView(view);
                        Log.d(TAG, "onConfigureTab: "+fragmentAdapter.getItemId(1));
                        viewPager2.setCurrentItem(position);
                        break;
                    }
                    case 1: {
                        View view = (View) LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_text_layout, null);
                        TextView textView = view.findViewById(R.id.tab_text);
                        TextView notifyCount = view.findViewById(R.id.tab_icon_text);
                        notifyCount.setBackground(null);
                        textView.setText("STATUS");
                        tab.setCustomView(view);
                        break;
                    }
                    case 2: {
                        View view = (View) LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_text_layout, null);
                        TextView textView = view.findViewById(R.id.tab_text);
                        View notify = view.findViewById(R.id.notify_status);
                        TextView notifyCount = view.findViewById(R.id.tab_icon_text);
                        notifyCount.setText("3");
                        notify.setBackground(null);
                        textView.setText("CALL");
                        tab.setCustomView(view);
                        break;
                    }
                }
            }
        }
        );
        searchBarViewClicks();
        tabLayoutMediator.attach();
    }

    private void initializeView(){
        toolbar = findViewById(R.id.toolbar);
        searchBarView = findViewById(R.id.search_bar);
        viewPager2 = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        topSearchOption = searchBarView.findViewById(R.id.topSearchTool);
        bottomSearchOption = searchBarView.findViewById(R.id.bottomSearchTool);
    }

    @Override
    public void onBackPressed() {
        if (searchBarView.getVisibility() == View.VISIBLE){
            setToolbar(false);
        }else {
            super.onBackPressed();
        }
    }

    public void setToolbar(boolean isSearchViewEnable){
        if (!isSearchViewEnable){
            searchBarView.setVisibility(View.GONE);
            ChatFragment.setSearchIconInEditText(null);
            changeStatusBarColor("#005047");
            toolbar.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
        }else{
            searchBarView.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
        }
    }

    public void searchBarViewClicks(){
        searchBarView.findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToolbar(false);
            }
        });
    }

    public void showSearchOptionInSearchView(boolean isVisible){
        if (isVisible){
            topSearchOption.setVisibility(View.VISIBLE);
            bottomSearchOption.setVisibility(View.VISIBLE);
        }else{
            topSearchOption.setVisibility(View.GONE);
            bottomSearchOption.setVisibility(View.GONE);
        }
    }

    public void changeStatusBarColor(String color){
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(color));
    }

}