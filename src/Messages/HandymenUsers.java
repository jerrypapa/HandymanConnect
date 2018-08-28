package Messages;

public class HandymenUsers {
    private int id,sender_id,recipient_id;
    private String sender_name,recipient_name,message,date_sent,status,sender_type;

    public HandymenUsers() { }

    public HandymenUsers(int id, int sender_id, int recipient_id, String sender_name, String recipient_name, String message, String date_sent, String status, String sender_type) {
        this.id = id;
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.sender_name = sender_name;
        this.recipient_name = recipient_name;
        this.message = message;
        this.date_sent = date_sent;
        this.status = status;
        this.sender_type = sender_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(int recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(String date_sent) {
        this.date_sent = date_sent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender_type() {
        return sender_type;
    }

    public void setSender_type(String sender_type) {
        this.sender_type = sender_type;
    }
}
