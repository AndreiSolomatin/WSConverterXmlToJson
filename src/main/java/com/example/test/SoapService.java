
package com.example.test;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 *
 */
@WebService(name = "SoapService", targetNamespace = "http://services.example.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SoapService {


    /**
     *
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "SendWBS")
    @WebResult(partName = "return")
    @Action(input = "http://services.example.com/SoapService/SendWBSRequest", output = "http://services.example.com/SoapService/SendWBSResponse")
    public String sendWBS(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0);

    /**
     *
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetInfo")
    @WebResult(partName = "return")
    @Action(input = "http://services.example.com/SoapService/GetInfoRequest", output = "http://services.example.com/SoapService/GetInfoResponse")
    public String getInfo();

}