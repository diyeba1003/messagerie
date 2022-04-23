package com.univangers.messagerie.fileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author etud
 */
public class MailObject {

    @Getter
    @Setter
    private InfoPersonne from;
    

    @Getter
    @Setter
    private Date sentDate;

    @Getter
    @Setter
    private Date receivedDate;

    @Getter
    @Setter
    private String subject;

    @Getter
    @Setter
    private String content;


    @Getter
    @Setter
    private String fonction;

    @Getter
    @Setter
    private List<InfoPersonne> to;

    @Getter
    @Setter
    private List<InfoPersonne> cc;

    @Getter
    @Setter
    private List<InfoPersonne> bcc;

    @Getter
    @Setter
    private List<AttachFile> fileList;

    public MailObject() {
        this.to = new ArrayList<>();
        this.cc = new ArrayList<>();
        this.bcc = new ArrayList<>();
        this.fileList = new ArrayList<>();
    }

    
}
