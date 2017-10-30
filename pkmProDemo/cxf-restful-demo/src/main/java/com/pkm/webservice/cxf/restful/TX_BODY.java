package com.pkm.webservice.cxf.restful;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name="TX_BODY")
public class TX_BODY {
    private String TXN_INSID;                //交易机构编号
    private String TXN_ITT_CHNL_ID;         //交易发起渠道编号
    private String TXN_ITT_CHNL_CGY_CODE;   //交易发起渠道类别
    private String TXN_DT;                  //交易日期
    private String TXN_TM;                  //交易时间
    private String TXN_STFF_ID;             //交易人员编号
    private String MULTI_TENANCY_ID;        //多实体标识
    private String LNG_ID;                  //语言标识
    
//    @XmlElement(name="TXN_INSID")
    public String getTXN_INSID() {
        return TXN_INSID;
    }
    public void setTXN_INSID(String tXN_INSID) {
        TXN_INSID = tXN_INSID;
    }
//    @XmlElement(name="TXN_ITT_CHNL_ID")
    public String getTXN_ITT_CHNL_ID() {
        return TXN_ITT_CHNL_ID;
    }
    public void setTXN_ITT_CHNL_ID(String tXN_ITT_CHNL_ID) {
        TXN_ITT_CHNL_ID = tXN_ITT_CHNL_ID;
    }
//    @XmlElement(name="TXN_ITT_CHNL_CGY_CODE")
    public String getTXN_ITT_CHNL_CGY_CODE() {
        return TXN_ITT_CHNL_CGY_CODE;
    }
    public void setTXN_ITT_CHNL_CGY_CODE(String tXN_ITT_CHNL_CGY_CODE) {
        TXN_ITT_CHNL_CGY_CODE = tXN_ITT_CHNL_CGY_CODE;
    }
//    @XmlElement(name="TXN_DT")
    public String getTXN_DT() {
        return TXN_DT;
    }
    public void setTXN_DT(String tXN_DT) {
        TXN_DT = tXN_DT;
    }
//    @XmlElement(name="TXN_TM")
    public String getTXN_TM() {
        return TXN_TM;
    }
    public void setTXN_TM(String tXN_TM) {
        TXN_TM = tXN_TM;
    }
//    @XmlElement(name="TXN_STFF_ID")
    public String getTXN_STFF_ID() {
        return TXN_STFF_ID;
    }
    public void setTXN_STFF_ID(String tXN_STFF_ID) {
        TXN_STFF_ID = tXN_STFF_ID;
    }
//    @XmlElement(name="MULTI_TENANCY_ID")
    public String getMULTI_TENANCY_ID() {
        return MULTI_TENANCY_ID;
    }
    public void setMULTI_TENANCY_ID(String mULTI_TENANCY_ID) {
        MULTI_TENANCY_ID = mULTI_TENANCY_ID;
    }
//    @XmlElement(name="LNG_ID")
    public String getLNG_ID() {
        return LNG_ID;
    }
    public void setLNG_ID(String lNG_ID) {
        LNG_ID = lNG_ID;
    }
    
}
