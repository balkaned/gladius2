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

    Integer idRole;
    String desRole;
    String flgest;
    String usuCreaRole;
    String usuModRole;
    Date fecCreaRole;
    Date fecModRole;
}
