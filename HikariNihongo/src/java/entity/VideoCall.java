/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class VideoCall {
    private int callID;
    private User caller;
    private User receiver;
    private Date callStart;
    private Date callEnd;

    public int getCallID() { return callID; }
    public void setCallID(int callID) { this.callID = callID; }

    public User getCaller() { return caller; }
    public void setCaller(User caller) { this.caller = caller; }

    public User getReceiver() { return receiver; }
    public void setReceiver(User receiver) { this.receiver = receiver; }

    public Date getCallStart() { return callStart; }
    public void setCallStart(Date callStart) { this.callStart = callStart; }

    public Date getCallEnd() { return callEnd; }
    public void setCallEnd(Date callEnd) { this.callEnd = callEnd; }
}
