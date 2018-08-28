package Handyman;

public class AdminMessaging {
    private int message_id,sender_id,recipient_id;
    private String sender_name,recipient,message,date_sent,status,sender_type;

    public AdminMessaging(int message_id, int sender_id, int recipient_id, String sender_name, String recipient, String message, String date_sent, String status, String sender_type) {
        this.message_id = message_id;
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.sender_name = sender_name;
        this.recipient = recipient;
        this.message = message;
        this.date_sent = date_sent;
        this.status = status;
        this.sender_type = sender_type;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
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

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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
