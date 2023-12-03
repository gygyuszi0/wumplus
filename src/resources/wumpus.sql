BEGIN TRANSACTION;
DROP TABLE IF EXISTS "high_score";
CREATE TABLE IF NOT EXISTS "high_score" (
	"player_name"	TEXT NOT NULL,
	"won_game"	INTEGER NOT NULL DEFAULT 0,
	PRIMARY KEY("player_name")
);
DROP TABLE IF EXISTS "saved_map";
CREATE TABLE IF NOT EXISTS "saved_map" (
	"name"	TEXT NOT NULL,
	"map"	TEXT NOT NULL,
	"dim"	INTEGER NOT NULL,
	PRIMARY KEY("name")
);
DROP TABLE IF EXISTS "saved_player";
CREATE TABLE IF NOT EXISTS "saved_player" (
	"name"	TEXT NOT NULL,
	"pos_x"	INTEGER NOT NULL,
	"pos_y"	INTEGER NOT NULL,
	"dir"	TEXT NOT NULL,
	"num_arrow"	INTEGER NOT NULL,
	"have_gold"	NUMERIC NOT NULL,
	"base_x"	INTEGER NOT NULL,
	"base_y"	INTEGER NOT NULL,
	PRIMARY KEY("name")
);
COMMIT;
