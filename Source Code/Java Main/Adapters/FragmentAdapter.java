package com.example.whatsapp.Adapters;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.whatsapp.CallFragment;
import com.example.whatsapp.ChatFragment;
import com.example.whatsapp.StatusFragment;

/**
 * Class to link ViewPager2 with Fragments we have created
 * */
public class FragmentAdapter extends FragmentStateAdapter {
    // Used to have the MainActivity context in ChatFragment
    private final Context context;
    // Use to have reference of the SearchBar View
    private final View searchBarView;

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity, View searchBarView, Context context) {
        super(fragmentActivity);
        this.context = context;
        this.searchBarView = searchBarView;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new ChatFragment(searchBarView,context);
            case 1 : return new StatusFragment();
        }
        return new CallFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
