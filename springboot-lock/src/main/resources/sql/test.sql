
CREATE TABLE IF NOT EXISTS employee (
  id      INTEGER NOT NULL,
  money   INTEGER,
  version INTEGER,
  PRIMARY KEY (id)
)ENGINE = INNODB;

INSERT INTO employee VALUE (1, 0, 1);