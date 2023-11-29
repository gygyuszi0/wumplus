BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "high_score" (
	"player_name"	TEXT NOT NULL,
	"won_game"	INTEGER NOT NULL DEFAULT 0,
	PRIMARY KEY("player_name")
);
CREATE TABLE IF NOT EXISTS "saved_player" (
	"name"	TEXT NOT NULL,
	"pos_x"	INTEGER NOT NULL,
	"pos_y"	INTEGER NOT NULL,
	"dir"	TEXT NOT NULL,
	"num_arrow"	INTEGER NOT NULL,
	"have_gold"	BLOB NOT NULL,
	PRIMARY KEY("name")
);
CREATE TABLE IF NOT EXISTS "saved_map" (
	"name"	TEXT NOT NULL,
	"map"	TEXT NOT NULL,
	"dim"	INTEGER NOT NULL,
	PRIMARY KEY("name")
);
INSERT INTO "high_score" VALUES ('John Doe',100);
INSERT INTO "high_score" VALUES ('teszt',2);
INSERT INTO "saved_player" VALUES ('teszt',0,0,'N',3,0);
INSERT INTO "saved_map" VALUES ('teszt','WW_
WP_
_GU
',3);
COMMIT;
