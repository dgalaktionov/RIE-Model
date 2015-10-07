package es.udc.lbd.rie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.Point;

@Entity
@Table(name = "water_node")
@Inheritance(strategy=InheritanceType.JOINED)
public class WaterNode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public Point location;

	@Override
	public String toString() {
		return "WaterNode [id=" + id + ", location=" + location + "]";
	}

	
}
