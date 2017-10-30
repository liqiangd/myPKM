package com.pkm.webservice.cxf.restful;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name="TX_HEADER")
public class TX_HEADER {
    private String SYS_HDR_LEN;         //报文头长度
    private String SYS_PKG_VRSN;        //报文格式版本号
    private String SYS_TTL_LEN;         //报文总长度
    private String SYS_REQ_SEC_ID;      //发起方安全节点编号
    private String SYS_SND_SEC_ID;      //发送方安全节点编号
    private String SYS_TX_CODE;         //服务名
    private String SYS_TX_VRSN;         //服务版本号
    private String SYS_TX_TYPE;         //服务种类
    private String SYS_RESERVED;        //预留
    private String SYS_EVT_TRACE_ID;    //全局事件跟踪号
    private String SYS_SND_SERIAL_NO;   //子交易序号
    private String SYS_PKG_TYPE;        //应用报文格式类型
    private String SYS_MSG_LEN;         //应用报文长度
    private String SYS_IS_ENCRYPTED;    //应用报文是否加密
    private String SYS_ENCRYPT_TYPE;    //应用报文加密方式
    private String SYS_COMPRESS_TYPE;   //应用报文压缩方式
    private String SYS_EMB_MSG_LEN;     //捎带报文长度
    private String SYS_REQ_TIME;        //发起方交易时间
    private String SYS_TIME_LEFT;       //交易剩余处理时间
    private String SYS_PKG_STS_TYPE;    //报文状态类型
    public String getSYS_HDR_LEN() {
        return SYS_HDR_LEN;
    }
    public void setSYS_HDR_LEN(String sYS_HDR_LEN) {
        SYS_HDR_LEN = sYS_HDR_LEN;
    }
    public String getSYS_PKG_VRSN() {
        return SYS_PKG_VRSN;
    }
    public void setSYS_PKG_VRSN(String sYS_PKG_VRSN) {
        SYS_PKG_VRSN = sYS_PKG_VRSN;
    }
    public String getSYS_TTL_LEN() {
        return SYS_TTL_LEN;
    }
    public void setSYS_TTL_LEN(String sYS_TTL_LEN) {
        SYS_TTL_LEN = sYS_TTL_LEN;
    }
    public String getSYS_REQ_SEC_ID() {
        return SYS_REQ_SEC_ID;
    }
    public void setSYS_REQ_SEC_ID(String sYS_REQ_SEC_ID) {
        SYS_REQ_SEC_ID = sYS_REQ_SEC_ID;
    }
    public String getSYS_SND_SEC_ID() {
        return SYS_SND_SEC_ID;
    }
    public void setSYS_SND_SEC_ID(String sYS_SND_SEC_ID) {
        SYS_SND_SEC_ID = sYS_SND_SEC_ID;
    }
    public String getSYS_TX_CODE() {
        return SYS_TX_CODE;
    }
    public void setSYS_TX_CODE(String sYS_TX_CODE) {
        SYS_TX_CODE = sYS_TX_CODE;
    }
    public String getSYS_TX_VRSN() {
        return SYS_TX_VRSN;
    }
    public void setSYS_TX_VRSN(String sYS_TX_VRSN) {
        SYS_TX_VRSN = sYS_TX_VRSN;
    }
    public String getSYS_TX_TYPE() {
        return SYS_TX_TYPE;
    }
    public void setSYS_TX_TYPE(String sYS_TX_TYPE) {
        SYS_TX_TYPE = sYS_TX_TYPE;
    }
    public String getSYS_RESERVED() {
        return SYS_RESERVED;
    }
    public void setSYS_RESERVED(String sYS_RESERVED) {
        SYS_RESERVED = sYS_RESERVED;
    }
    public String getSYS_EVT_TRACE_ID() {
        return SYS_EVT_TRACE_ID;
    }
    public void setSYS_EVT_TRACE_ID(String sYS_EVT_TRACE_ID) {
        SYS_EVT_TRACE_ID = sYS_EVT_TRACE_ID;
    }
    public String getSYS_SND_SERIAL_NO() {
        return SYS_SND_SERIAL_NO;
    }
    public void setSYS_SND_SERIAL_NO(String sYS_SND_SERIAL_NO) {
        SYS_SND_SERIAL_NO = sYS_SND_SERIAL_NO;
    }
    public String getSYS_PKG_TYPE() {
        return SYS_PKG_TYPE;
    }
    public void setSYS_PKG_TYPE(String sYS_PKG_TYPE) {
        SYS_PKG_TYPE = sYS_PKG_TYPE;
    }
    public String getSYS_MSG_LEN() {
        return SYS_MSG_LEN;
    }
    public void setSYS_MSG_LEN(String sYS_MSG_LEN) {
        SYS_MSG_LEN = sYS_MSG_LEN;
    }
    public String getSYS_IS_ENCRYPTED() {
        return SYS_IS_ENCRYPTED;
    }
    public void setSYS_IS_ENCRYPTED(String sYS_IS_ENCRYPTED) {
        SYS_IS_ENCRYPTED = sYS_IS_ENCRYPTED;
    }
    public String getSYS_ENCRYPT_TYPE() {
        return SYS_ENCRYPT_TYPE;
    }
    public void setSYS_ENCRYPT_TYPE(String sYS_ENCRYPT_TYPE) {
        SYS_ENCRYPT_TYPE = sYS_ENCRYPT_TYPE;
    }
    public String getSYS_COMPRESS_TYPE() {
        return SYS_COMPRESS_TYPE;
    }
    public void setSYS_COMPRESS_TYPE(String sYS_COMPRESS_TYPE) {
        SYS_COMPRESS_TYPE = sYS_COMPRESS_TYPE;
    }
    public String getSYS_EMB_MSG_LEN() {
        return SYS_EMB_MSG_LEN;
    }
    public void setSYS_EMB_MSG_LEN(String sYS_EMB_MSG_LEN) {
        SYS_EMB_MSG_LEN = sYS_EMB_MSG_LEN;
    }
    public String getSYS_REQ_TIME() {
        return SYS_REQ_TIME;
    }
    public void setSYS_REQ_TIME(String sYS_REQ_TIME) {
        SYS_REQ_TIME = sYS_REQ_TIME;
    }
    public String getSYS_TIME_LEFT() {
        return SYS_TIME_LEFT;
    }
    public void setSYS_TIME_LEFT(String sYS_TIME_LEFT) {
        SYS_TIME_LEFT = sYS_TIME_LEFT;
    }
    public String getSYS_PKG_STS_TYPE() {
        return SYS_PKG_STS_TYPE;
    }
    public void setSYS_PKG_STS_TYPE(String sYS_PKG_STS_TYPE) {
        SYS_PKG_STS_TYPE = sYS_PKG_STS_TYPE;
    }
    
    
}
