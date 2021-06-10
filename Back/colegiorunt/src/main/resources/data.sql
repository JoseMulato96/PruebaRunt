insert into colegios(id_colegio,nombre) values(1,'Del Olimpo');


insert into cursos(id_curso,grado,salon,id_colegio) values(1,10,'A',1);
insert into cursos(id_curso,grado,salon,id_colegio) values(2,10,'B',1);
insert into cursos(id_curso,grado,salon,id_colegio) values(3,11,'A',1);
insert into cursos(id_curso,grado,salon,id_colegio) values(4,11,'B',1);

insert into profesores(id_profesor,nombre) values(1,'Némesis');
insert into profesores(id_profesor,nombre) values(2,'Príapo');
insert into profesores(id_profesor,nombre) values(3,'Iris');


insert into estudiantes(id_estudiante,nombre,id_curso) values(1,'Afrodita',1);
insert into estudiantes(id_estudiante,nombre,id_curso) values(2,'Apolo',1);
insert into estudiantes(id_estudiante,nombre,id_curso) values(3,'Ares',1);
insert into estudiantes(id_estudiante,nombre,id_curso) values(4,'Artemisa',2);
insert into estudiantes(id_estudiante,nombre,id_curso) values(5,'Atenea',2);
insert into estudiantes(id_estudiante,nombre,id_curso) values(6,'Dionisio',2);
insert into estudiantes(id_estudiante,nombre,id_curso) values(7,'Hefesto',3);
insert into estudiantes(id_estudiante,nombre,id_curso) values(8,'Hera',3);
insert into estudiantes(id_estudiante,nombre,id_curso) values(9,'Hermes',4);
insert into estudiantes(id_estudiante,nombre,id_curso) values(10,'Hades',4);
insert into estudiantes(id_estudiante,nombre,id_curso) values(11,'Poseidón',4);
insert into estudiantes(id_estudiante,nombre,id_curso) values(12,'Zeus',4);

insert into asignaturas(id_asignatura,nombre,id_profesor) values(1,'Matemáticas',1);
insert into asignaturas(id_asignatura,nombre,id_profesor) values(2,'Español',2);
insert into asignaturas(id_asignatura,nombre,id_profesor) values(3,'Ingles básico',3);
insert into asignaturas(id_asignatura,nombre,id_profesor) values(4,'Ingles avanzado',3);
insert into asignaturas(id_asignatura,nombre,id_profesor) values(5,'Pre Icfes',1);

--Matematicas
insert into cursos_has_asignaturas(id_asignatura,id_curso) values(1,1);
insert into cursos_has_asignaturas(id_asignatura,id_curso) values(1,2);
insert into cursos_has_asignaturas(id_asignatura,id_curso) values(1,3);
insert into cursos_has_asignaturas(id_asignatura,id_curso) values(1,4);

--Español
insert into cursos_has_asignaturas(id_asignatura,id_curso) values(2,1);
insert into cursos_has_asignaturas(id_asignatura,id_curso) values(2,2);

--Ingles Basico
insert into cursos_has_asignaturas(id_asignatura,id_curso) values(3,1);

--Ingles Avanzado
insert into cursos_has_asignaturas(id_asignatura,id_curso) values(4,2);

--PreIcfes
insert into cursos_has_asignaturas(id_asignatura,id_curso) values(5,3);
insert into cursos_has_asignaturas(id_asignatura,id_curso) values(5,4);