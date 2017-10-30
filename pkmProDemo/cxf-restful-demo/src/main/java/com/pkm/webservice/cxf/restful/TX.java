package com.pkm.webservice.cxf.restful;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TX")
public class TX {
    private TX_HEADER TX_HEADER;
    private TX_BODY TX_BODY;
//    private TX_EMB TX_EMB;
    
//    @XmlElement(name="TX_HEADER")
    public TX_HEADER getTX_HEADER() {
        return TX_HEADER;
    }
    public void setTX_HEADER(TX_HEADER tX_HEADER) {
        TX_HEADER = tX_HEADER;
    }
    
//    @XmlElement(name="TX_BODY")
    public TX_BODY getTX_BODY() {
        return TX_BODY;
    }
    public void setTX_BODY(TX_BODY tX_BODY) {
        TX_BODY = tX_BODY;
    }
////    @XmlElement(name="TX_EMB")
//    public TX_EMB getTX_EMB() {
//        return TX_EMB;
//    }
//    public void setTX_EMB(TX_EMB tX_EMB) {
//        TX_EMB = tX_EMB;
//    }
    
}
