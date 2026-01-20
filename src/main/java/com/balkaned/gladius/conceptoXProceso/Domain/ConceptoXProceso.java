package com.balkaned.gladius.conceptoXProceso.Domain;

import com.balkaned.gladius.util.CapitalizarCadena;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ConceptoXProceso {

	@Id
	private Integer procodpro;
	private String procodcon;
	private String coodescon;
	private String procodconpdt;
	private String proflgbol;
	private Integer proorden;
	private Double nro_asignacion;
	private String protipcon;
	private String prodescustom;
	private String convar;
	private String tip_ingreso;
	private String flg_pry_5ta;
	private String flg_des_5ta_mes;
	private String flg_ess_reg;
	private String flg_ess_pesq;
	private String flg_ess_agrac;
	private String flg_ess_sctr;
	private String flg_extra_solid;
	private String flg_fondo_art;
	private String flg_apo_senati;
	private String flg_onp;
	private String flg_afp;
	private String flg_fond_compl_jub;
	private String flg_esp_pens_pesq;
	private String flg_5ta;
	private String flg_ess_seg_pen;
	private String flg_cont_asis_previs;
	private String flg_promediable;
	private String flg_agrupable;
	private Integer nro_meses_prom_atras;
	private String coocodforvar;

	public void setCoodescon(String coodescon) {
		CapitalizarCadena cap = new CapitalizarCadena();
		this.coodescon = cap.letras(coodescon);
	}
}
