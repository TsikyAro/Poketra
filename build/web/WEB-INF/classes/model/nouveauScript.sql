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
CREATE TABLE typePoketra(
    idType       serial PRIMARY key,
    nomType      VARCHAR(30)
);

CREATE TABLE taille(
    idTaille       serial PRIMARY key,
    nomTaille      VARCHAR(30)
);

CREATE TABLE POKETRA(
    idPoketra   serial Primary Key,
    idType       int references typePoketra (idType),
    idTaille    int references taille(idTaille)
);


CREATE TABLE POKETRAMATIERE(
    idPoketraMatiere    serial Primary key,
    idPoketra   int references Poketra (idPoketra),
    idMatiere   int references  Matiere(idMatiere),
    Quantite    double precision
);
CREATE TABLE Prix_Matiere(
    idprixdate serial PRIMARY key,
    idmatiere int REFERENCES matiere(idMatiere),
    prix double precision,
    date Date   
);
-- Requete --
SELECT PM.*,m.nommatiere FROM poketramatiere pm 
join matiere m on m.idmatiere = pm.idmatiere; 

-- VIEW --
-- AFFICHER POKETRA -- 
CREATE view Affiche as
select  l.idLookMatiere ,m.nommatiere,lo.nomlook from LookMatiere l
join matiere m on m.idMatiere=l.idMatiere 
join Look lo on lo.idLook = l.idLook ;

-- AFFICHER POKETRA2 -- 
CREATE VIEW AffichePoketra as
select t.nomType, tl.nomTaille, m.nomMatiere, p.quantiter,a.nomLook from poketra p
join typePoketra t on t.idType=p.idType
join taille tl on tl.idTaille=p.idTaille
join matiere m on m.idMatiere=p.idmatiere
join affiche a on a.nomMatiere = m.nomMatiere;


-- prix date farany --
create view last_price as 
SELECT idprixdate, idmatiere,prix,date
FROM prix_matiere p1
WHERE 
date = (
        SELECT MAX(p2.date)
        FROM prix_matiere p2
        WHERE p1.idmatiere = p2.idmatiere
    );
-- prixpoketra--
create or replace view prixPoketra as
select pm.*,p_m.prix,p_m.date from poketramatiere pm 
join last_price p_m on p_m.idmatiere = pm.idmatiere;
-- MAMOKA PRIX --
create view ensemble as
select pr.*,t.nomType, tl.nomTaille, m.nomMatiere, a.nomLook   
from prixPoketra pr
join Poketra po on po.idpoketra=pr.idpoketra
join typePoketra t on t.idType=po.idType
join taille tl on tl.idTaille=po.idTaille
join matiere m on m.idMatiere=pr.idMatiere
join affiche a on a.nomMatiere = m.nomMatiere; 
-- prix mamoka affichage --
create view prix_total as
select idpoketra,sum(quantite*prix) prix_total,nomtype,nomtaille from ensemble group by idpoketra,nomtype,nomtaille; 

CREATE VIEW BENEFICE AS 
select pmp.idpoketra,sum(pmp.durekarama) somme,pt.prix_total,pt.nomtype,pt.nomtaille from POKETRAMAKERPRIX pmp join prix_total pt on pt.idpoketra = pmp.idpoketra group by pmp.idpoketra,pt.prix_total,pt.nomtype,pt.nomtaille;  

SELECT * FROM PRIX_TOTAL;
 SELECT * FROM POKETRAMAKERPRIX;
 SELECT * FROM POKETRA_PRIX;




create table Personne(
    idPersonne serial PRIMARY key,
    NomPersonne VARCHAR(50),
    Tache VARCHAR(50)
);

create table Poketra_Maker(
    idPoketra_Maker serial PRIMARY key,
    idPoketra int REFERENCES Poketra(idPoketra),
    idPersonne int REFERENCES Personne(idPersonne),
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


create table Poste (
    idPoste  serial PRIMARY key,
    NomPoste varchar(50);
    Hierachie int not null
);

create table SalairePoste(
    idSalairePoste serial PRIMARY key,
    idPoste int REFERENCES Poste(idPoste),
    Salaire Double precision,
    Dates Date
);

create table PersonnePoste(
    idPersonnePoste serial PRIMARY key,
    idPersonne int REFERENCES Personne(idPersonne),
    idPoste int REFERENCES Poste(idPoste),
    Debut Date,
    Fin Date
);


create OR REPLACE view info_pers_poste as
 select per.NomPersonne  , Personnepost.Debut , post.NomPoste , post.idPoste
 from PersonnePoste Personnepost 
 join Personne  per on  Personnepost.idPersonne = per.idPersonne
 join Poste post on Personnepost.idPoste = post.idPoste;

create OR REPLACE view liste_finale as
select NomPersonne  , Debut as DATEDEBU , NomPoste , sal.Salaire , sal.Dates
from info_pers_poste p 
join SalairePoste sal on p.idPoste = sal.idPoste;

create table client(
    idClient serial primary key,
    nomClient varchar(50),
    genre int
);

create table Vente (
idVente serial primary key,
idPoketra int references Poketra(idPoketra),
nombreProduit int not null,
idClient int references Client(idClient)
);



create or replace View nomPoketra_idPoketra as 
select p.idPoketra , p.idType , t.nomType , tail.nomTaille from POKETRA p 
join typePoketra t on p.idType = t.idType
join taille tail on p.idTaille = tail.idTaille; 


create View Vente_clinet as
select V.idPoketra , V.nombrepoketra , C.nomClient , C.genre from Vente V left join client C on V.idClient = C.idClient;


create view  vente_clinet_nomPoketra as
select Np.idPoketra ,  Np.nomType , Np.nomTaille , coalesce(sum(Vc.nombrepoketra), 0) as SummeVentePoketra , Vc.genre 
from nomPoketra_idPoketra Np 
left join Vente_clinet Vc on Np.idPoketra = Vc.idPoketra
GROUP BY Np.idPoketra ,  Np.nomType , Np.nomTaille , Vc.genre ;


select genre , sum(SummeVentePoketra) from vente_clinet_nomPoketra group by genre;


