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
select p.idpoketra,t.nomType, tl.nomTaille from poketra p
join typePoketra t on t.idType=p.idType
join taille tl on tl.idTaille=p.idTaille;

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

select p.idpoketra, ty.nomType,t.nomtaille 
from poketra p 
join typepoketra ty on ty.idtype = p.idtype 
join taille t on p.idtaille = t.idtaille 
where p.idpoketra=1 order by p.idpoketra desc;

select pm.idPoketra, t.nomType from poketraMatiere pm
join poketra po on po.idPoketra = pm.idPoketra
join TypePoketra t on t.idType = po.idType group by pm.idPoketra,t.nomType;


CREATE TABLE Fabrication(
    idFabrication serial PRIMARY key,
    idPoketra int REFERENCES Poketra(idPoketra),
    Quantite double precision
);

CREATE TABLE Matiere_en_stock(
    idMatiere_en_stock serial PRIMARY key,
    idMatiere int REFERENCES matiere(idMatiere),
    Quantite_en_stok double precision,
    dates Date
);

CREATE TABLE Sortie(
    idSortie serial PRIMARY key,
    idMatiere int REFERENCES matiere(idMatiere),
    Quantite double precision,
    dates Date
);

create or replace view etat_de_stock as
 select (coalesce(sum(ms.quantite_en_stok),0)-coalesce((Select sum(sortie.quantite) from sortie where idmatiere = ms.idmatiere),0)) valeur,ms.idmatiere idmatiere 
from matiere_en_stock ms  group by ms.idmatiere ;

select pm.idpoketramatiere,pm.idPoketra, t.nomType from poketraMatiere pm
join poketra po on po.idPoketra = pm.idPoketra
join TypePoketra t on t.idType = po.idType group by pm.idPoketra,t.nomType,pm.idpoketramatiere;

create view affichageEtat_de_stock as 
select e.*,m.nommatiere from etat_de_stock e
join matiere m on m.idmatiere = e.idmatiere 

create table Personne(
    idPersonne serial PRIMARY key,
    NomPersonne VARCHAR(50),
    Tache VARCHAR(50)
);

create table Poketra_Maker(
    idPoketra_Maker serial PRIMARY key,
    idPoketra int REFERENCES Poketra(idPoketra),
    idPersonne int REFERENCES Personne(idPersonne),
    idFabrication   int REFERENCES fabrication(idfabrication),
    dure double precision
);

create table Poketra_Prix(
    idPoketra_Prix serial PRIMARY key,
    idPoketra int REFERENCES Poketra(idPoketra),
    Prix_Poketra double precision
    
);

create table Personne_Karama(
    idPersonne_Karama serial PRIMARY key,
    idPersonne int REFERENCES Personne(idPersonne),
    Karama double precision  
);

create OR REPLACE view poketramakerprix as 
select pok.idPoketra_Maker , pok.idPoketra, pok.idPersonne , pok.dure , pers.idPersonne_Karama ,  pers.Karama , dure*Karama AS Durekarama
from Poketra_Maker pok join Personne_Karama pers 
on
 pok.idPersonne = pers.idPersonne;

select idpoketra,sum(durekarama) from poketramakerprix group by idpoketra; 


-- Maka date Max ao amle poste --
create view PosteMaxDate as
 select * from poste p where date =(select max(date) from poste po where p.nom=po.nom);  

create view SalairePoste as
select *,(karama* (select salaire from salaire order by idsalaire desc limit 1)) salaire from posteMaxDate; 