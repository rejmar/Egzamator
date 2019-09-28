--ROLES:
INSERT INTO egzamator.role (name) VALUES("ROLE_STUDENT");
INSERT INTO egzamator.role (name) VALUES("ROLE_TEACHER");
INSERT INTO egzamator.role (name) VALUES("ROLE_ADMIN");

-- subject:
INSERT INTO egzamator.subject(name) VALUES ("IT");
INSERT INTO egzamator.subject(name) VALUES ("Mathematics");
INSERT INTO egzamator.subject(name) VALUES ("Physics");
INSERT INTO egzamator.subject(name) VALUES ("Mechanics");
INSERT INTO egzamator.subject(name) VALUES ("Control Theory");

--student_subject:
INSERT INTO egzamator.student_subject (student_id, subject_id) VALUES (1,1)

--teacher_subject:
INSERT INTO egzamator.teacher_subject (teacher_id, subject_id) values (1,1)


--question:
INSERT INTO egzamator.question (id, ans_a, ans_b, ans_c, ans_d, correct_answer, description, test_id) VALUES (1, "1", "4", "5", "6", "d", "Result of 2+2*2", 1);
INSERT INTO egzamator.question (id, ans_a, ans_b, ans_c, ans_d, correct_answer, description, test_id) VALUES (2, "1 Byte = 8 bits", "1 bit = 8 Bytes", "1 Byte = 1 bit", "1 Byte = 1 bit", "a", "Which answer is true", 1)

--test:
INSERT INTO egzamator.test (name, mark_id, subject_id) VALUES ("IT_TEST_1", 1, 1);
INSERT INTO egzamator.test (name, mark_id, subject_id) VALUES ("IT_TEST_2", 2, 1);
INSERT INTO egzamator.test (name, mark_id, subject_id) VALUES ("MATHEMATICS_TEST_1", 3, 2);
INSERT INTO egzamator.test (name, mark_id, subject_id) VALUES ("MATHEMATICS_TEST_2", 4, 2);

--answer:
INSERT INTO `egzamator`.`answer`
(`answer`,
`question_id`,
`student_id`,
`mark_id`)
VALUES
(<{answer: }>,
<{question_id: }>,
<{student_id: }>,
<{mark_id: }>);


INSERT INTO egzamator.question (id, ans_a, ans_b, ans_c, ans_d, correct_answer, description, test_id) VALUES (1, "1", "4", "5", "6", "d", "Result of 2+2*2", 1);
INSERT INTO egzamator.question (id, ans_a, ans_b, ans_c, ans_d, correct_answer, description, test_id) VALUES (2, "1 Byte = 8 bits", "1 bit = 8 Bytes", "1 Byte = 1 bit", "1 Byte = 1 bit", "a", "Which answer is true", 1);


INSERT INTO egzamator.test (id, name, subject_id) VALUES (1,"IT_TEST_1", 1);

INSERT INTO egzamator.student_subject (student_id, subject_id) VALUES (1,1);

INSERT INTO egzamator.SUBJECT (id, name) VALUES(1, 'IT');

INSERT INTO egzamator.teacher (user_id) VALUES(1);
INSERT INTO egzamator.teacher_subject (teacher_id, subject_id) values (1,1);


INSERT INTO `egzamator`.`question`
(`id`,
`ans_a`,
`ans_b`,
`ans_c`,
`ans_d`,
`correct_answer`,
`description`,
`test_id`)
VALUES
(<{id: }>,
<{ans_a: }>,
<{ans_b: }>,
<{ans_c: }>,
<{ans_d: }>,
<{correct_answer: }>,
<{description: }>,
<{test_id: }>);

