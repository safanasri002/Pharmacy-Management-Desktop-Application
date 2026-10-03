-- Script PostgreSQL pour la base pharmacie_db
-- (converti depuis le script MySQL d'origine)
--
-- Utilisation avec psql :
--   1) Se connecter à une base existante (ex: postgres) et créer la base :
--      psql -U postgres -c "CREATE DATABASE pharmacie_db ENCODING 'UTF8';"
--   2) Exécuter ce script sur la base pharmacie_db :
--      psql -U postgres -d pharmacie_db -f pharmacie_db.sql

DROP TABLE IF EXISTS achat;
DROP TABLE IF EXISTS medicament_chimique;
DROP TABLE IF EXISTS medicament_homeopathique;
DROP TABLE IF EXISTS medicament;
DROP TABLE IF EXISTS appareil_medical;
DROP TABLE IF EXISTS client;

CREATE TABLE client (
  id_client      SERIAL PRIMARY KEY,
  nom            VARCHAR(100),
  prenom         VARCHAR(100),
  solde_fidelite INTEGER
);

CREATE TABLE appareil_medical (
  id_appareil_medical SERIAL PRIMARY KEY,
  nom                 VARCHAR(100),
  prix                DOUBLE PRECISION,
  date_expiration     DATE,
  quantite            INTEGER
);

CREATE TABLE medicament (
  code            SERIAL PRIMARY KEY,
  nom             VARCHAR(100) NOT NULL,
  categorie       VARCHAR(100),
  type            VARCHAR(50),
  prix            DOUBLE PRECISION,
  numserie        INTEGER,
  date_expiration DATE,
  quantite        INTEGER
);

CREATE TABLE medicament_chimique (
  code                INTEGER PRIMARY KEY,
  composante_chimique VARCHAR(100),
  age                 INTEGER,
  CONSTRAINT fk_chimique FOREIGN KEY (code) REFERENCES medicament (code) ON DELETE CASCADE
);

CREATE TABLE medicament_homeopathique (
  code   INTEGER PRIMARY KEY,
  plante VARCHAR(100),
  CONSTRAINT fk_homeo FOREIGN KEY (code) REFERENCES medicament (code) ON DELETE CASCADE
);

CREATE TABLE achat (
  id_achat           SERIAL PRIMARY KEY,
  date_achat          DATE,
  montant             DOUBLE PRECISION,
  id_client           INTEGER REFERENCES client (id_client),
  id_medicament       INTEGER REFERENCES medicament (code),
  id_appareil_medical INTEGER REFERENCES appareil_medical (id_appareil_medical)
);

CREATE INDEX idx_achat_id_client ON achat (id_client);
CREATE INDEX idx_achat_id_medicament ON achat (id_medicament);
CREATE INDEX idx_achat_id_appareil_medical ON achat (id_appareil_medical);
