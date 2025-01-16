--  Lock Database
UPDATE buildLedgerSchema.databasechangeloglock SET `LOCKED` = 1, LOCKEDBY = 'xx_kns_xx (94.155.232.30)', LOCKGRANTED = NOW() WHERE ID = 1 AND `LOCKED` = 0;

--  Release Database Lock
UPDATE buildLedgerSchema.databasechangeloglock SET `LOCKED` = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

