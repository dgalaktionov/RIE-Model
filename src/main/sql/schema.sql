
DROP TABLE IF EXISTS water_node CASCADE;
CREATE TABLE water_node (
	id bigserial,
	location Geometry(Point),
	
	CONSTRAINT water_node_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS water_source CASCADE;
CREATE TABLE water_source (
	id bigint,
	"type" varchar(255),
	
	CONSTRAINT water_source_pk PRIMARY KEY (id),
	CONSTRAINT water_source_fk_water_node FOREIGN KEY (id) REFERENCES water_node (id)
);
