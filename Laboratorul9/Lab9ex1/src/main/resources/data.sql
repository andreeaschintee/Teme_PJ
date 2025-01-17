
 Create table masini
 (
     nr_inmatriculare varchar(255) not null,
     marca            varchar(255) not null,
     anul_fabricatiei int          not null,
     culoare          varchar(255) not null,
     nr_km            int          not null,
     primary key (nr_inmatriculare)
 );

    INSERT INTO masini (nr_inmatriculare, marca, anul_fabricatiei, culoare, nr_km)
    VALUES
        ('TM33EEE', 'Ford', 2008, 'Alb', 7000),
        ('MM67FFF', 'BMW', 2000, 'Negru', 90000),
        ('B699YYY', 'Fiat', 2015, 'Aurie', 15000),
        ('BV22DEN', 'Dacia', 2010, 'Rosu', 80000),
        ('TM31RWW', 'Volkswagen', 2019, 'Albastru', 60000);