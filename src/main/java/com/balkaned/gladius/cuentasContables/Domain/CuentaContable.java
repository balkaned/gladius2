package com.balkaned.gladius.cuentasContables.Domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CuentaContable {

    @Id
    private String iexccodcta;
    private String iexdescta;
    private String desdet;
    private Integer iexcodcia;
    private String iextipocta;
    private String destipcta;
    private String iexusucrea;
    private String iexfeccrea;
    private String iexusumod;
    private String iexfecmod;

}
