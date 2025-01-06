package com.balkaned.gladius.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class TurnoMarks {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private String iexcodfecha;
    private String iexfechamarks;
    private String iexflgmanual;

}
