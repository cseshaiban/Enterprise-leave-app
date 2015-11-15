package com.incture.leaveme.data;

/**
 * Created by Mohammed on 11/10/2015.
 */
public class ApprovalData {

    int photo;
    String name,email,sdate,smonth,syear,sday,edate,emonth,eyear,eday,ssession,esession,leavetype,description,responseId;

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public ApprovalData(int photo,String name,String email,String sdate,String smonth,String syear,String sday,String ssession,
                                                           String edate,String emonth,String eyear,String eday,String esession,
                         String leavetype,String description,String res){
        this.photo=photo;
        this.name= name;
        this.email=email;

        this.sdate=sdate;
        this.smonth=smonth;
        this.syear=syear;
        this.sday=sday;
        this.ssession=ssession;

        this.edate=edate;
        this.emonth=emonth;
        this.eyear=eyear;
        this.eday=eday;
        this.esession=esession;

        this.leavetype=leavetype;
        this.description=description;

        this.responseId=res;

    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getSmonth() {
        return smonth;
    }

    public void setSmonth(String smonth) {
        this.smonth = smonth;
    }

    public String getSyear() {
        return syear;
    }

    public void setSyear(String syear) {
        this.syear = syear;
    }

    public String getSday() {
        return sday;
    }

    public void setSday(String sday) {
        this.sday = sday;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getEmonth() {
        return emonth;
    }

    public void setEmonth(String emonth) {
        this.emonth = emonth;
    }

    public String getEyear() {
        return eyear;
    }

    public void setEyear(String eyear) {
        this.eyear = eyear;
    }

    public String getEday() {
        return eday;
    }

    public void setEday(String eday) {
        this.eday = eday;
    }

    public String getSsession() {
        return ssession;
    }

    public void setSsession(String ssession) {
        this.ssession = ssession;
    }

    public String getEsession() {
        return esession;
    }

    public void setEsession(String esession) {
        this.esession = esession;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
