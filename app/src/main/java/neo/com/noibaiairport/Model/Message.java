package neo.com.noibaiairport.Model;

public class Message {
    private String mID;
    private String mMessage;
    private User mSender;
    private long mTime;

    public Message(String mID, String mMessage, User mSender, long mTime) {
        this.mID = mID;
        this.mMessage = mMessage;
        this.mSender = mSender;
        this.mTime = mTime;
    }

    public Message() {
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public User getmSender() {
        return mSender;
    }

    public void setmSender(User mSender) {
        this.mSender = mSender;
    }

    public long getmTime() {
        return mTime;
    }

    public void setmTime(long mTime) {
        this.mTime = mTime;
    }
}
