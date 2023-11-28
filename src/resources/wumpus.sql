BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "saved_state" (
	"player_name"	TEXT NOT NULL,
	"player_pos_x"	INTEGER NOT NULL,
	"player_pos_y"	INTEGER NOT NULL,
	"player_dir"	TEXT NOT NULL,
	"player_num_arrow"	INTEGER NOT NULL,
	"player_have_gold"	INTEGER NOT NULL,
	PRIMARY KEY("player_name")
);
CREATE TABLE IF NOT EXISTS "high_score" (
	"player_name"	TEXT NOT NULL,
	"won_game"	INTEGER NOT NULL DEFAULT 0,
	PRIMARY KEY("player_name")
);
COMMIT;
