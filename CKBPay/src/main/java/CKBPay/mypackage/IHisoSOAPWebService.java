/**
 * IHisoSOAPWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package CKBPay.mypackage;

public interface IHisoSOAPWebService extends java.rmi.Remote {
    public java.lang.String helloWorld(java.lang.String param1) throws java.rmi.RemoteException;
    public java.lang.String topup(java.lang.String cUserID, java.lang.String cTransactionID, java.lang.String cInvoiceNo, java.lang.String cTransactionType, java.lang.String cRteMethod, java.lang.String cOperatorID, java.lang.String cDistributerID, java.lang.String cTerminalID, java.lang.String cTerminalName, java.lang.String cSubscriberID, int iAmount, java.lang.String cProcessorID, java.lang.String iDiler, java.lang.String dDateTime) throws java.rmi.RemoteException;
    public java.lang.String rTopup(java.lang.String cUserID, java.lang.String cTransactionID, java.lang.String cInvoiceNo, java.lang.String cTransactionType, java.lang.String cRteMethod, java.lang.String cOperatorID, java.lang.String cDistributerID, java.lang.String cTerminalID, java.lang.String cTerminalName, java.lang.String cSubscriberID, int iAmount, java.lang.String cProcessorID, java.lang.String iDiler, java.lang.String dDateTime) throws java.rmi.RemoteException;
}
