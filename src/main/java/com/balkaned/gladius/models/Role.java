package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Role {

    @Id
    private Integer idRole;
    private String desRole;
    private String flgest;
    private String usuCreaRole;
    private String usuModRole;
    private Date fecCreaRole;
    private Date fecModRole;

}
