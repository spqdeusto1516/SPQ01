DROP SCHEMA  IF EXISTS bookshop;
DROP USER IF EXISTS 'spq'@'%';
CREATE SCHEMA bookshop;
CREATE USER IF NOT EXISTS 'spq'@'%' IDENTIFIED BY 'spq';
GRANT ALL ON bookshop.* TO 'spq'@'%';
