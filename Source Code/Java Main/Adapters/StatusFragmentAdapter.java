package com.example.whatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.whatsapp.R;
import com.example.whatsapp.Users.User;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class StatusFragmentAdapter extends RecyclerView.Adapter<StatusFragmentAdapter.Holder> {
    /**
     *
     * {@usersList} Holds the List of users in status fragment it includes users Friends status,
     * and users Image that is to be shown as first View in Status Fragment
     * @mUseMyStatusLayout It is to identify when we have to use user Status View if true inflate
     * users View else Users friends status View.
     * @VIEW_TYPE_MY_STATUS and @VIEW_TYPE_OTHERS_STATUS UNIQUE ID to identify Users View and UsersStatusView
     * */
    private final ArrayList<User> usersList;
    private final boolean mUseMyStatusLayout;
    private static final int VIEW_TYPE_MY_STATUS = 0;
    private static final int VIEW_TYPE_OTHERS_STATUS = 1;

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && mUseMyStatusLayout){
            return VIEW_TYPE_MY_STATUS;
        }else{
            return VIEW_TYPE_OTHERS_STATUS;
        }
    }

    public StatusFragmentAdapter (Context context,ArrayList<User> usersList){
        this.usersList = usersList;
        mUseMyStatusLayout = context.getResources().getBoolean(R.bool.use_my_status_layout);
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layout;
        switch (viewType){
            case VIEW_TYPE_MY_STATUS : {
                layout = R.layout.my_status_item_layout;
                break;
            }
            case VIEW_TYPE_OTHERS_STATUS : {
                layout = R.layout.status_item_layout;
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        User user = usersList.get(position);
        int viewType = getItemViewType(position);
        if (viewType == VIEW_TYPE_OTHERS_STATUS){
            holder.SetStatusView(user.getProfilePicture(),user.getHeader(),user.getTime());
        }

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        CircleImageView profileImage;
        TextView headerTv;
        TextView timeTv;
        public Holder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.ChatDisplayImage);
            headerTv = itemView.findViewById(R.id.group_Person_Name);
            timeTv = itemView.findViewById(R.id.summaryText);
        }
        private void SetStatusView(int imageId, String header, String time){
            profileImage.setImageResource(imageId);
            headerTv.setText(header);
            timeTv.setText(time);
        }
    }
}
