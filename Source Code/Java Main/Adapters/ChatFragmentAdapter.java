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

public class ChatFragmentAdapter extends RecyclerView.Adapter<ChatFragmentAdapter.Holder> {
    /**
     * {@usersList} Holds the List of users in status fragment it includes users Friends status,
     *  and users Image that is to be shown as first View in Status Fragment
     * */
    private final ArrayList<User> usersList;

    public ChatFragmentAdapter (ArrayList<User> usersList){
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layout= R.layout.chat_item_layout;
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        User user = usersList.get(position);
        holder.setChatView(user.getProfilePicture(),user.getHeader(),user.getFooter(),user.getTime(),user.getUnreadText());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        CircleImageView profileImage;
        TextView headerTv;
        TextView footerTv;
        TextView timeTv;
        TextView unreadTextTv;
        public Holder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.ChatDisplayImage);
            headerTv = itemView.findViewById(R.id.group_Person_Name);
            footerTv = itemView.findViewById(R.id.summaryText);
            timeTv = itemView.findViewById(R.id.chat_time);
            unreadTextTv = itemView.findViewById(R.id.pending_chat);
        }

        private void setChatView(int profileImageId, String header, String footer, String time, int unreadText){
            profileImage.setImageResource(profileImageId);
            headerTv.setText(header);
            footerTv.setText(footer);
            timeTv.setText(time);
            if (unreadText == 0){
                unreadTextTv.setVisibility(View.INVISIBLE);
            }else{
                unreadTextTv.setText(String.valueOf(unreadText));
            }
        }
    }
}
