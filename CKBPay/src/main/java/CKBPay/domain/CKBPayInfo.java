package CKBPay.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Yarss
 * Date: 28.07.11
 */
public class CKBPayInfo {
    private static final Map<String, OperatorInfo> operatorInfoList = new HashMap<String, OperatorInfo>();
    static {
        operatorInfoList.put("TMOB", new OperatorInfo("0000004","J&ATMOB", "981" /*"142"**//*"161"*//*"1284"*/));
        operatorInfoList.put("MTEL", new OperatorInfo("0000003","J&AMTEL", "161"/*"142"*//*"1302"*/));
    }
    private String userID;
    private String transactionID;
    private String invoiceNo;
    private String transactionType;
    private String rteMethod;
    private String operatorID;
    private String distributerID;
    private String terminalID;
    private String terminalName;
    private String subscriberID;
    private String amount;
    private String processorID;
    private String dealerID;
    private String dateTime;

    public CKBPayInfo(String operatorCode) {
        //this.operatorID = operatorID;
        OperatorInfo op = operatorInfoList.get(operatorCode);

        this.userID = "JANDA";//"EXTERN";
        this.transactionType = /*"Q";*/"T";
        this.rteMethod = "L";
        this.processorID = "CKB";

        this.operatorID = op.getOperatorID();
        this.distributerID = op.getDistributerID();
        this.dealerID = op.getDealerID();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getRteMethod() {
        return rteMethod;
    }

    public void setRteMethod(String rteMethod) {
        this.rteMethod = rteMethod;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public String getDistributerID() {
        return distributerID;
    }

    public void setDistributerID(String distributerID) {
        this.distributerID = distributerID;
    }

    public String getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getSubscriberID() {
        return subscriberID;
    }

    public void setSubscriberID(String subscriberID) {
        this.subscriberID = subscriberID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProcessorID() {
        return processorID;
    }

    public void setProcessorID(String processorID) {
        this.processorID = processorID;
    }

    public String getDealerID() {
        return dealerID;
    }

    public void setDealerID(String dealerID) {
        this.dealerID = dealerID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}

class OperatorInfo {
    private String operatorID;
    private String distributerID;
    private String dealerID;

    public OperatorInfo(String operatorID, String distributerID, String dealerID) {
        this.operatorID = operatorID;
        this.distributerID = distributerID;
        this.dealerID = dealerID;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public String getDistributerID() {
        return distributerID;
    }

    public void setDistributerID(String distributerID) {
        this.distributerID = distributerID;
    }

    public String getDealerID() {
        return dealerID;
    }

    public void setDealerID(String dealerID) {
        this.dealerID = dealerID;
    }
}