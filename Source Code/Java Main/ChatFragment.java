package com.example.whatsapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
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
import android.widget.EditText;
import com.example.whatsapp.Adapters.ChatFragmentAdapter;
import com.example.whatsapp.Users.User;
import java.util.ArrayList;


public class ChatFragment extends Fragment implements View.OnClickListener {


    private Context context;
    public static View searchBarView;
    private NestedScrollView nestedScrollView;
    public ChatFragment() {
        // Required empty public constructor
    }

    public ChatFragment(View searchBarView,Context context){
        this.context = context;
        ChatFragment.searchBarView = searchBarView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        initializeView(view);
        // this is to tell fragment that we are using menu
        setHasOptionsMenu(true);
        // searching which Item s click in order to be search in the search bar View.
        searchFor(searchBarView);

        RecyclerView recyclerView = view.findViewById(R.id.chat_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        ArrayList<User> usersList = new ArrayList<>();
        for(int i = 0 ; i < 15 ; i++) {
            usersList.add(new User
                    (R.drawable.text_icon, "Real Estate Advisor  Bangalor", "Bhavani: tried move in.. it's super...",
                            "9:04 AM", 3));
        }

        recyclerView.setAdapter(new ChatFragmentAdapter(usersList));
        return view;
    }

    // We listen to search bar TExtView icon for specific Type of data and than add it in Search bar
    private void searchFor(View searchBarView) {
    searchBarView.findViewById(R.id.photo_search).setOnClickListener(this);
    searchBarView.findViewById(R.id.video_search).setOnClickListener(this);
    searchBarView.findViewById(R.id.links_search).setOnClickListener(this);
    searchBarView.findViewById(R.id.gifs_search).setOnClickListener(this);
    searchBarView.findViewById(R.id.audio_search).setOnClickListener(this);
    searchBarView.findViewById(R.id.document_search).setOnClickListener(this);
    }


    private void initializeView(View view) {
        nestedScrollView = view.findViewById(R.id.nestedScroll);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_newSearch){
            ((MainActivity)getActivity()).setToolbar(true);
            ((MainActivity)getActivity()).showSearchOptionInSearchView(true);
        }
        return super.onOptionsItemSelected(item);
    }

    // This will ensure to be on the top of the List whenever we enter this fragment and we set
    // the Default AppbarLayout
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setToolbar(false);
        nestedScrollView.scrollTo(0,0);
    }

    // whenever a Search TextView is clicked we get the clicked view Drawable and set it in searchBox
    @Override
    public void onClick(View v) {
        setSearchIconInEditText(getDrawableForClickedItem(v.getId()));
    }

    // get Clicked TextView Drawable
    private Drawable getDrawableForClickedItem(int id){
        Resources res = context.getResources();
        switch (id){
            case R.id.photo_search : return ResourcesCompat.getDrawable(res,R.drawable.photo_search,null);
            case R.id.video_search : return ResourcesCompat.getDrawable(res,R.drawable.ic_baseline_videocam_24,null);
            case R.id.links_search : return ResourcesCompat.getDrawable(res,R.drawable.link_search,null);
            case R.id.gifs_search : return ResourcesCompat.getDrawable(res,R.drawable.gif_search,null);
            case R.id.audio_search : return ResourcesCompat.getDrawable(res,R.drawable.audio_search,null);
            case R.id.document_search : return ResourcesCompat.getDrawable(res,R.drawable.document_search,null);
        }
        return null;
    }

    // Set clicked TextView Drawable as EditText start Drawable
    public static void setSearchIconInEditText(Drawable searchIcon) {
        EditText searchTextET = searchBarView.findViewById(R.id.search_box);
        searchTextET.setCompoundDrawablesWithIntrinsicBounds(searchIcon,null,null,null);
        // this is set in order to remove any typed char in text view when we go back or press back
        if (searchIcon == null){
            searchTextET.setText("");
        }
    }
}