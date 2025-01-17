
Create table carti(
                      id INT  auto_increment,
                      Autorul varchar(255) not null,
                      Titlul varchar(255) not null,
                      primary key (id)
);

INSERT INTO carti (Autorul, Titlul) VALUES ('Lucian Blaga', 'Poemele Luminii');
INSERT INTO carti (Autorul, Titlul) VALUES ('George Bacovia', 'Plumb');
INSERT INTO carti (Autorul, Titlul) VALUES ('Tudor Arghezi', 'Flori de Mucigai');
INSERT INTO carti (Autorul, Titlul) VALUES ('Liviu Rebreanu', 'Ion');
INSERT INTO carti (Autorul, Titlul) VALUES ('Marin Preda', 'Morometii');
