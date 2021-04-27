package com.example.whatsapp.Users;
/**
 *  Class for creating Dummy users for MainActivity
 *  This class is used by Recycler View to inflate itself with the User data
 *  this class is used in ChatFragment, StatusFragment and CallFragment
 * */
public class User {
    // This is the Title of the Chat Fragment eg: College
    private String mHeader;
    // this is the most recent Text for the title;
    private String mFooter;
    // time when most recent message was sent
    private String mTime;
    // Image id for the Profile picture
    private int mProfilePicture;
    // use to display the circle for un Read Text
    private int mUnreadText;
    // to identify whether call was made or received by the user
    private int callIndicatorImageId;
    // to identify whether it was a video call or a Audio Call
    private int callTypeImageId;
    // used in ChatFragment and Status Fragment
    public User(int profilePicture, String header, String footer, String time, int unreadText){
        mProfilePicture = profilePicture;
        mHeader = header;
        mFooter = footer;
        mTime = time;
        mUnreadText = unreadText;
    }
    // Used in Call Fragment
    public User(int profilePicture, String header, String time, int callIndicatorImageId, int callTypeImageId){
        mProfilePicture = profilePicture;
        mHeader = header;
        mTime = time;
        this.callIndicatorImageId = callIndicatorImageId;
        this.callTypeImageId = callTypeImageId;
    }

    public int getCallIndicatorImageId() {
        return callIndicatorImageId;
    }


    public int getCallTypeImageId() {
        return callTypeImageId;
    }


    public String getHeader() {
        return mHeader;
    }


    public String getFooter() {
        return mFooter;
    }


    public String getTime() {
        return mTime;
    }


    public int getProfilePicture() {
        return mProfilePicture;
    }


    public int getUnreadText() {
        return mUnreadText;
    }

}
