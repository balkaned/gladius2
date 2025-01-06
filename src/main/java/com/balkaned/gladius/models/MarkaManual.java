package com.balkaned.gladius.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class MarkaManual {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private String iexcodfec;
    private String iexfecmarkas;
    private String iexdesusu;
    private String iexfeccrea;
}
