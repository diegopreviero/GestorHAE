
SELECT * FROM APP.ALUNO ORDER BY NOME FETCH FIRST 100 ROWS ONLY;


SELECT * FROM APP.CURSO FETCH FIRST 100 ROWS ONLY;

DELETE FROM APP.CURSO;
INSERT INTO APP.CURSO (ID, NOME, STATUSATIVO) VALUES (1, 'DIREITO', TRUE);
INSERT INTO APP.CURSO (ID, NOME, STATUSATIVO) VALUES (2, 'PEDAGOGIA', TRUE);
INSERT INTO APP.CURSO (ID, NOME, STATUSATIVO) VALUES (3, 'ENGENHARIA FLORESTAL', TRUE);
INSERT INTO APP.CURSO (ID, NOME, STATUSATIVO) VALUES (4, 'FILOSOFIA', TRUE);

DELETE FROM APP.ALUNO;
INSERT INTO APP.ALUNO (CURSANDOTG, NOME, STATUSATIVO, CURSO_ID) VALUES (TRUE, 'DIEGO', TRUE, 2);
INSERT INTO APP.ALUNO (CURSANDOTG, NOME, STATUSATIVO, CURSO_ID) VALUES (TRUE, 'CARLA', TRUE, 2);
INSERT INTO APP.ALUNO (CURSANDOTG, NOME, STATUSATIVO, CURSO_ID) VALUES (TRUE, 'THIAGO', TRUE, 2);
INSERT INTO APP.ALUNO (CURSANDOTG, NOME, STATUSATIVO, CURSO_ID) VALUES (TRUE, 'JOAO PAULO', TRUE, 2);
INSERT INTO APP.ALUNO (CURSANDOTG, NOME, STATUSATIVO, CURSO_ID) VALUES (TRUE, 'ANDERSON', TRUE, 2);

