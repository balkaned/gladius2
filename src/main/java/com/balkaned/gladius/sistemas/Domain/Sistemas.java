package com.balkaned.gladius.sistemas.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Sistemas {

    @Id
    private Integer iexcodsys;
    private String iexdessys;
    private String iexactiondefault;

}
