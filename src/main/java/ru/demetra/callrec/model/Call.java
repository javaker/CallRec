package ru.demetra.callrec.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JavaBean domain object that represents a Call
 *
 * @author Vyacheslav Y.
 * @version 2.1
 */
@Entity
@Table(name = "cdr")
public class Call {
    @Id
    @Column(name = "uniqueid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "calldate")
    private Date callDate;
    @Column(name = "src")
    private String src;
    @Column(name = "dst")
    private String dst;
    @Column(name = "billsec")
    private int billsec;
    @Column(name = "recordingfile")
    private String recordingfile;
    @Column(name = "disposition")
    private String disposition;

    /**
     *  Filter nanoseconds
     */
    public String getCallDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(callDate);
    }

    /**
     *  Filter nanoseconds
     */
    public void setCallDate(String callDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            this.callDate = simpleDateFormat.parse(callDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public int getBillsec() {
        return billsec;
    }

    public void setBillsec(int billsec) {
        this.billsec = billsec;
    }

    public String getRecordingfile() {
        return recordingfile;
    }

    public void setRecordingfile(String recordingfile) {
        this.recordingfile = recordingfile;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }
}

