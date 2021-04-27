package com.example.whatsapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.whatsapp.Adapters.StatusFragmentAdapter;
import com.example.whatsapp.Users.User;
import java.util.ArrayList;

public class StatusFragment extends Fragment {

    private static final String TAG = "StatusFragment";
    private NestedScrollView nestedScrollView;

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        nestedScrollView = view.findViewById(R.id.nestedScroll);
        // This is to show that we are using Menu in this fragment
        setHasOptionsMenu(true);

        RecyclerView recyclerView = view.findViewById(R.id.statusRecyclerVIew);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        ArrayList<User> usersList = new ArrayList<>();
        usersList.add(new User(R.drawable.text_icon,"My Status","","",0));
        for(int i = 0; i < 15 ; i++) {
            usersList.add(new User
                    (R.drawable.text_icon, "Sonu", "",
                            "Today, 3:56 am", 0));
        }

        recyclerView.setAdapter(new StatusFragmentAdapter(getContext(),usersList));
        return view;
    }

    // This ensures that when ever we open this fragment we are on the top of the Recycler View
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setToolbar(false);
        nestedScrollView.scrollTo(0,0);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);
        // we hide the New Chat menu from here.
        menu.findItem(R.id.menu_newChat).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_newSearch){
            // Change the status bar color to black when search icon is clicked
            ((MainActivity)getActivity()).changeStatusBarColor("#000000");
            // this hides the Toolbar and TabLayout , and displays Search bar View.
            ((MainActivity)getActivity()).setToolbar(true);
            // This hides the Icon for specific Type search {Like Photo search, Video Search}
            ((MainActivity)getActivity()).showSearchOptionInSearchView(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}