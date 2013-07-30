/**
 * IHisoSOAPWebServiceserviceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package CKBPay.mypackage;

public class IHisoSOAPWebServiceserviceLocator extends org.apache.axis.client.Service implements IHisoSOAPWebServiceservice {

    public IHisoSOAPWebServiceserviceLocator() {
    }


    public IHisoSOAPWebServiceserviceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IHisoSOAPWebServiceserviceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IHisoSOAPWebServicePort
    private java.lang.String IHisoSOAPWebServicePort_address = "http://25.65.176.1:1024/soap/IHisoSOAPWebService";

    public java.lang.String getIHisoSOAPWebServicePortAddress() {
        return IHisoSOAPWebServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IHisoSOAPWebServicePortWSDDServiceName = "IHisoSOAPWebServicePort";

    public java.lang.String getIHisoSOAPWebServicePortWSDDServiceName() {
        return IHisoSOAPWebServicePortWSDDServiceName;
    }

    public void setIHisoSOAPWebServicePortWSDDServiceName(java.lang.String name) {
        IHisoSOAPWebServicePortWSDDServiceName = name;
    }

    public IHisoSOAPWebService getIHisoSOAPWebServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IHisoSOAPWebServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIHisoSOAPWebServicePort(endpoint);
    }

    public IHisoSOAPWebService getIHisoSOAPWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            IHisoSOAPWebServicebindingStub _stub = new IHisoSOAPWebServicebindingStub(portAddress, this);
            _stub.setPortName(getIHisoSOAPWebServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIHisoSOAPWebServicePortEndpointAddress(java.lang.String address) {
        IHisoSOAPWebServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (IHisoSOAPWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                IHisoSOAPWebServicebindingStub _stub = new IHisoSOAPWebServicebindingStub(new java.net.URL(IHisoSOAPWebServicePort_address), this);
                _stub.setPortName(getIHisoSOAPWebServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("IHisoSOAPWebServicePort".equals(inputPortName)) {
            return getIHisoSOAPWebServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "IHisoSOAPWebServiceservice");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "IHisoSOAPWebServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IHisoSOAPWebServicePort".equals(portName)) {
            setIHisoSOAPWebServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
