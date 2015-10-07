package es.udc.lbd.rie.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "water_source")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class WaterSource extends WaterNode {
	
	@Enumerated(EnumType.STRING)
	public SourceType type;

	@Override
	public String toString() {
		return "WaterSource [id=" + id + ", location=" + location + ", type=" + type + "]";
	}
	
}
