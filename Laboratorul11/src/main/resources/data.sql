
Create table evenimente
(
    id        INT auto_increment,
    denumire varchar(255) not null,
    locatie   varchar(255) not null,
    data      DATE         not null,
    timp       TIME         not null,
    pret      float        not null,
    primary key (id)
);

INSERT INTO evenimente (denumire, locatie, data, timp, pret) VALUES ('Gala de Balet', 'Opera Națională', '2023-05-10', '20:00', 120);
INSERT INTO evenimente (denumire, locatie, data, timp, pret) VALUES ('Festival Jazz', 'Parcul Central', '2023-06-15', '17:00', 75);
INSERT INTO evenimente (denumire, locatie, data, timp, pret) VALUES ('Expozitie de Artă', 'Muzeul de Artă', '2023-07-20', '10:00', 30);
INSERT INTO evenimente (denumire, locatie, data, timp, pret) VALUES ('Lansare Carte', 'Librăria Centrală', '2023-08-25', '16:00', 20);
INSERT INTO evenimente (denumire, locatie, data, timp, pret) VALUES ('Maraton Caritabil', 'Stadionul Național', '2023-09-10', '08:00', 50);

