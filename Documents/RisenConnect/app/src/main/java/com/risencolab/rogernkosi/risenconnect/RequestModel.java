package com.risencolab.rogernkosi.risenconnect;

/**
 * Created by empirestate on 6/23/16.
 */
public class RequestModel {

    private String requestID;
    private String memRid;
    private String memSid;
    private String senderUsername;
    private String requestStatus;
    private String requestContent;

    public RequestModel(String requestID, String memRid, String memSid, String senderUsername, String requestStatus, String requestContent) {
        this.requestID = requestID;
        this.memRid = memRid;
        this.memSid = memSid;
        this.senderUsername = senderUsername;
        this.requestStatus = requestStatus;
        this.requestContent = requestContent;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getMemRid() {
        return memRid;
    }

    public void setMemRid(String memRid) {
        this.memRid = memRid;
    }

    public String getMemSid() {
        return memSid;
    }

    public void setMemSid(String memSid) {
        this.memSid = memSid;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }
}
