package com.balkaned.gladius.models;

import lombok.*;

import java.util.Date;

@Data
public class Role {

    private Integer idRole;
    private String desRole;
    private String flgest;
    private String usuCreaRole;
    private String usuModRole;
    private Date fecCreaRole;
    private Date fecModRole;
}
