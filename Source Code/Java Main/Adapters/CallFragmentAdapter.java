package com.example.whatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.whatsapp.R;
import com.example.whatsapp.Users.User;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class CallFragmentAdapter extends RecyclerView.Adapter<CallFragmentAdapter.Holder> {
    /**
     * {@usersList} Holds the List of users in status fragment it includes users Friends status,
     *  and users Image that is to be shown as first View in Status Fragment
     * */
    private final ArrayList<User> usersList;

    public CallFragmentAdapter (ArrayList<User> usersList){
        this.usersList = usersList;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layout = R.layout.call_item_layout;
        View view = LayoutInflater.from(context).inflate(layout,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        User user = usersList.get(position);
        holder.setCallView(user.getProfilePicture(),user.getHeader(),user.getTime(),user.getCallIndicatorImageId(),user.getCallTypeImageId());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        CircleImageView profileImage;
        TextView callerNameHeaderTv;
        TextView timeTv;
        ImageView call_nature; // incoming or outgoing
        ImageView callType; // video call or voice call
        public Holder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.ChatDisplayImage);
            callerNameHeaderTv = itemView.findViewById(R.id.group_Person_Name);
            timeTv = itemView.findViewById(R.id.time_summary_text);
            call_nature = itemView.findViewById(R.id.call_indicator);
            callType = itemView.findViewById(R.id.call_type_indicator);
        }

        private void setCallView(int imageId, String header, String time, int callIndicator, int callTypeIndicator){
            profileImage.setImageResource(imageId);
            callerNameHeaderTv.setText(header);
            timeTv.setText(time);
            call_nature.setImageResource(callIndicator);
            callType.setImageResource(callTypeIndicator);
        }
    }
}
