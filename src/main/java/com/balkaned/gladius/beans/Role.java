package com.balkaned.gladius.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Integer idRole;
    private String desRole;
    private String flgest;
    private String usuCreaRole;
    private String usuModRole;
    private Date fecCreaRole;
    private Date fecModRole;
}
