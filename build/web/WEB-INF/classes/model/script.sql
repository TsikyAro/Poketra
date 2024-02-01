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

CREATE view Affiche as
select  l.idLookMatiere ,m.nommatiere,lo.nomlook from LookMatiere l
join matiere m on m.idMatiere=l.idMatiere 
join Look lo on lo.idLook = l.idLook 

CREATE VIEW AffichePoketra as
select t.nomType, tl.nomTaille, m.nomMatiere, p.quantiter,a.nomLook from poketra p
join typePoketra t on t.idType=p.idType
join taille tl on tl.idTaille=p.idTaille
join matiere m on m.idMatiere=p.idMatiere
join affiche a on a.nomMatiere = m.nomMatiere;

CREATE TABLE typePoketra(
    idType       serial PRIMARY key,
    nomType      VARCHAR(30)
);

CREATE TABLE taille(
    idTaille       serial PRIMARY key,
    nomTaille      VARCHAR(30)
);

CREATE TABLE Poketra(
    idPoketra   serial PRIMARY key,
    idType      int REFERENCES typePoketra(idType),
    idTaille    int REFERENCES taille(idTaille),
    idMatiere    int REFERENCES matiere(idMatiere),
    quantiter    double precision

);
CREATE TABLE Pridate(
idprixdate serial PRIMARY key,
idmatiere int REFERENCES matiere(idMatiere),
prix double precision,
date Date   
);

create view prixPoketra as
select pr.*,t.nomType, tl.nomTaille, m.nomMatiere, po.quantiter,a.nomLook   
from Pridate pr
join Poketra po on po.idMatiere=pr.idmatiere
join typePoketra t on t.idType=po.idType
join taille tl on tl.idTaille=po.idTaille
join matiere m on m.idMatiere=po.idMatiere
join affiche a on a.nomMatiere = m.nomMatiere;

create table Vente (
idVente serial primary key,
idProduit int references produit(idPoketra),
nombreProduit int not null,
idClient int references Client(idClient)
);

create table client(
    idClient serial primary key,
    nomClient varchar(50);
    genre int
);


