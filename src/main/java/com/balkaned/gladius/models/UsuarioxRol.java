package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UsuarioxRol {

    @Id
    private Integer iexcodusu;
    private String iexdesusu;
    private Integer iexcodcia;
    private String iexdescia;
    private Integer iexcodrol;
    private String iexdesrol;
    private Integer iexcodtra;

}
