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

    @Getter @Setter
    private String from;;

    @Getter @Setter
    private Date sentDate;
    
    @Getter @Setter
    private Date receivedDate;

    @Getter @Setter
    private String subject;

   @Getter @Setter
    private String content;

    @Getter @Setter
    private String nom;

    @Getter @Setter
    private List<String> to;

    @Getter @Setter
    private List<String> cc;

    @Getter @Setter
    private List<String> bcc;
    
     @Getter @Setter
    private List<String> fileList;
     
     public MailObject() {
        this.to = new ArrayList<>();
        this.cc = new ArrayList<>();
        this.bcc = new ArrayList<>();
        this.fileList= new ArrayList<>();
    }
}
