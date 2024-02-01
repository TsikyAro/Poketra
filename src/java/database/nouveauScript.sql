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
select pm.*,p_m.prix,p_m.date,(pm.quantite*p_m.prix)montant from poketramatiere pm 
join last_price p_m on p_m.idmatiere = pm.idmatiere;

-- prix masokarena matiere --
create or replace view masokarena as 
select idpoketra,sum(montant) from prixpoketra group by idpoketra;  

--personne EMploye--- 
create view PersonneEmploye as
select pk.karama,emp.* from personne_karama pk join employe emp on emp.idpersonne = pk.idpersonne; 

-- personne poketra maker --
create view  poketramakerpersonne as
select pm.* ,pe.karama,pe.idemploye,pe.date,(pe.karama*pm.dure)montantkarama from poketra_maker pm join personneemploye pe  on pe.idpersonne = pm.idpersonne; 

--masokarena employe --
create or replace view masokarenapersonne as
select idpoketra ,sum(montantkarama) karamatotal from poketramakerpersonne group by idpoketra;

-- masokarena total --
create or replace view masokarena_total as
select mm.idpoketra , (mM.sum+mP.karamatotal) masokarena from masokarena mM join masokarenapersonne mP on mm.idpoketra=mp.idpoketra ;

--benefice --
create  or replace view benefice as
select mt.idpoketra ,( p.prix_poketra-mt.masokarena ) benefice from masokarena_total mt join poketra_prix p on p.idpoketra = mt.idpoketra; 
-- MAMOKA PRIX --
create  or replace view ensemble as
select pr.*,t.nomType, tl.nomTaille, m.nomMatiere   
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



create table Poste(
    idPoste     serial PRIMARY KEY,
    nom         varchar(30),
    annee       int,
    karama      int,
    date        date
);
create table Employe(
    idEmploye  serial PRIMARY KEY,
    idPersonne int REFERENCES Personne(idPersonne),
    date        date
);
create table Salaire(
    idSalaire       serial PRIMARY KEY,
    salaire         double precision
);

-- Ajouter une colonne 'email' de type VARCHAR à la table 'utilisateurs'
ALTER TABLE fabrication
ADD COLUMN dateFabrication date;

ALTER TABLE salaire
ADD COLUMN datesfin date;


create table panier(
    idPanier serial primary key,
    idVente int references vente (idvente),
    idPoketra int references poketra (idpoketra),
    quantite int
);

create table vente(
    idVente serial primary key,
    idClient  int references  Client (idClient),
    dates   timestamp
);
-- panier prix--
create or replace  view  panierPrix as 
select p.*,pp.prix_poketra from  panier p join poketra_prix pp on p.idpoketra = pp.idpoketra; 


create or replace view panierVente as 
select p.*,v.idclient,v.dates from panier p join vente v on p.idvente = v.idvente;

-- facturation --
create or replace view facturation as
select p.*,v.idclient, v.dates from panierPrix p join vente v on v.idvente = p.idvente;

--etat_de stock-
create view etat_de_stockFab as 
 select (coalesce(sum(ms.quantite),0)-coalesce((Select sum(paniervente.quantite) from paniervente where idpoketra = ms.idpoketra),0)) valeur,ms.idpoketra idpoketra 
from fabrication ms  group by ms.idpoketra ;

--V_Statistic Achat Total--
create view  V_AchatTotal as 
SELECT c.genre,
       COALESCE(SUM(pv.quantite), 0) AS nombre_achats
FROM client c
LEFT JOIN paniervente pv ON c.idclient = pv.idclient
LEFT JOIN poketra p ON pv.idpoketra = p.idpoketra
GROUP BY c.genre;

--V_statistic Par PRoduit --
create view V_ProduitTotal as
SELECT c.genre,
       p.idpoketra,
       COALESCE(SUM(pv.quantite), 0) AS nombre_achats
FROM client c
CROSS JOIN poketra p  
LEFT JOIN paniervente pv ON c.idclient = pv.idclient AND pv.idpoketra = p.idpoketra
GROUP BY c.genre, p.idpoketra;


--V_restePoketra_en STock--
create or replace view  V_reste_poketra as
select t.nomType,ta.nomtaille,e.idpoketra,e.valeur from etat_de_stockfab e 
join poketra on e.idpoketra =poketra.idpoketra 
join typepoketra t on poketra.idtype = t.idtype 
join taille ta on ta.idtaille = poketra.idtaille;  


















