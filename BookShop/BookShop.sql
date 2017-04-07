CREATE DATABASE BetAgain;

GRANT ALTER, SELECT,INSERT,UPDATE,DELETE,CREATE,DROP, INDEX
           ON BetAgain.*
           TO spq@'%'
           IDENTIFIED BY 'spq';

GRANT ALTER, SELECT,INSERT,UPDATE,DELETE,CREATE,DROP, INDEX
           ON BetAgain.*
           TO spq@localhost
           IDENTIFIED BY 'spq';