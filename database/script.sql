CREATE USER poketra SUPERUSER LOGIN PASSWORD 'poketra';
CREATE DATABASE poketra WITH OWNER poketra;

CREATE TABLE look(
    idLook      serial PRIMARY KEY,
    nomLook     VARCHAR(30)
);


CREATE TABLE matiere(
    idMatiere       serial PRIMARY key,
    nomMatiere      VARCHAR(30)
);

create table LookMatiere(
    idLookMatiere       serial PRIMARY KEY,
    idMatiere           int REFERENCES matiere (idMatiere),
    idLook              int REFERENCES look (idLook)
);