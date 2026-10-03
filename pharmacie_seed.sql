-- Données de test pour pharmacie_db (PostgreSQL)
-- À exécuter APRÈS pharmacie_db.sql, sur la base pharmacie_db :
--   psql -U postgres -d pharmacie_db -f pharmacie_seed.sql
-- (ou coller le contenu dans le Query Tool de pgAdmin, connecté sur pharmacie_db)

-- ---------- Clients fidèles ----------
INSERT INTO client (id_client, nom, prenom, solde_fidelite) VALUES
  (1, 'Bennani',  'Yasmine', 120),
  (2, 'El Amrani','Karim',   45),
  (3, 'Idrissi',  'Sara',    0),
  (4, 'Tazi',     'Mehdi',   75),
  (5, 'Chraibi',  'Nadia',   200);

-- ---------- Médicaments (table parent) ----------
INSERT INTO medicament (code, nom, categorie, type, prix, numserie, date_expiration, quantite) VALUES
  (1, 'Doliprane 500mg',   'Antalgique',    'chimique',      25.50, 10001, '2027-03-01', 150),
  (2, 'Aspirine 300mg',    'Antalgique',    'chimique',      18.00, 10002, '2026-11-15', 80),
  (3, 'Amoxicilline 500mg','Antibiotique',  'chimique',      42.00, 10003, '2026-12-20', 60),
  (4, 'Ventoline',         'Respiratoire',  'chimique',      55.00, 10004, '2027-01-10', 30),
  (5, 'Ibuprofène 400mg',  'Anti-inflammatoire','chimique',  21.00, 10005, '2026-10-05', 100),
  (6, 'Arnica Montana 9CH','Homéopathie',   'homeopathique', 32.00, 10006, '2027-06-01', 40),
  (7, 'Oscillococcinum',   'Homéopathie',   'homeopathique', 38.50, 10007, '2027-02-15', 55),
  (8, 'Sedatif PC',        'Homéopathie',   'homeopathique', 29.00, 10008, '2026-09-30', 25);

-- ---------- Sous-types ----------
INSERT INTO medicament_chimique (code, composante_chimique, age) VALUES
  (1, 'Paracétamol',    0),
  (2, 'Acide acétylsalicylique', 0),
  (3, 'Amoxicilline',   0),
  (4, 'Salbutamol',     0),
  (5, 'Ibuprofène',     0);

INSERT INTO medicament_homeopathique (code, plante) VALUES
  (6, 'Arnica'),
  (7, 'Anas barbariae'),
  (8, 'Passiflore');

-- ---------- Appareils médicaux ----------
INSERT INTO appareil_medical (id_appareil_medical, nom, prix, date_expiration, quantite) VALUES
  (1, 'Tensiomètre électronique', 350.00, NULL, 12),
  (2, 'Thermomètre infrarouge',   180.00, NULL, 20),
  (3, 'Nébuliseur',                420.00, NULL, 8),
  (4, 'Oxymètre de pouls',        150.00, NULL, 15);

-- ---------- Achats d'exemple ----------
INSERT INTO achat (date_achat, montant, id_client, id_medicament) VALUES
  ('2026-09-15', 20.40, 1, 1),
  ('2026-09-20', 14.40, 2, 2),
  ('2026-09-25', 33.60, 1, 3);

INSERT INTO achat (date_achat, montant, id_client, id_appareil_medical) VALUES
  ('2026-09-18', 350.00, 3, 1),
  ('2026-09-22', 180.00, 4, 2);

-- ---------- Resynchronise les séquences SERIAL ----------
-- (nécessaire car les ids ci-dessus sont insérés explicitement)
SELECT setval('client_id_client_seq', (SELECT COALESCE(MAX(id_client), 1) FROM client));
SELECT setval('medicament_code_seq', (SELECT COALESCE(MAX(code), 1) FROM medicament));
SELECT setval('appareil_medical_id_appareil_medical_seq', (SELECT COALESCE(MAX(id_appareil_medical), 1) FROM appareil_medical));
SELECT setval('achat_id_achat_seq', (SELECT COALESCE(MAX(id_achat), 1) FROM achat));
