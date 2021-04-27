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
import com.example.whatsapp.Adapters.CallFragmentAdapter;
import com.example.whatsapp.Users.User;
import java.util.ArrayList;


public class CallFragment extends Fragment {

    private NestedScrollView nestedScrollView;
    private ArrayList<User> usersList;
    public CallFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_call, container, false);
        nestedScrollView = view.findViewById(R.id.nestedScroll);
        // This method tells Fragment that we are using a Menu for this Fragment
        setHasOptionsMenu(true);

        RecyclerView recyclerView = view.findViewById(R.id.call_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

       usersList = new ArrayList<>();

       fillDummyListData(R.drawable.call_received,R.drawable.ic_baseline_videocam_24,2);
       fillDummyListData(R.drawable.call_received,R.drawable.call,2);
       fillDummyListData(R.drawable.call_made,R.drawable.call,3);
       fillDummyListData(R.drawable.call_made,R.drawable.ic_baseline_videocam_24,3);

        recyclerView.setAdapter(new CallFragmentAdapter(usersList));
        recyclerView.setHasFixedSize(true);

        return view;
    }

    // This will ensure the we are on top of the Recycler view when ever we enter the fragment
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setToolbar(false);
        nestedScrollView.scrollTo(0,0);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);
        //This hides the new Chat menu as we dont need this it Call Fragment
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

    /**
     * @PARAM callIndicator is to indicate whether user received call or he made the call.
     * @PARAM callType is to indicate whether call made was Video or Audio Call
     * @PARAM count is used to indicate how many users needs to be added in the {@usersList}
     * */
    private void fillDummyListData(int callIndicator, int callType, int count){
        for(int i=  0 ; i < count ; i++) {
            usersList.add(new User(R.drawable.text_icon, "Kaka",
                    " (2) Yesterday, 11:10 pm", callIndicator, callType));
        }
    }
}