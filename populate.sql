#students
INSERT INTO student (city, dob, email, fname, gender, lname, residency, state, street, zip)
VALUES ('Athens', '1996-07-23', 'melanie@gmail.com', 'Melanie', 'F', 'Lee', 'Citizen', 'GA', '817 Peachtree Rd', 45678);

INSERT INTO student (city, dob, email, fname, gender, lname, residency, state, street, zip)
VALUES ('Athens', '1995-09-03', 'adam@gmail.com', 'Adam', 'M', 'King', 'Citizen', 'GA', '314 Pike Street', 89067);

INSERT INTO student (city, dob, email, fname, gender, lname, residency, state, street, zip)
VALUES ('Thompson', '1990-04-27', 'susan@gmail.com', 'Susan', 'F', 'George', 'Visa', 'GA', '192 Aptitude Way', 19281);

INSERT INTO student (city, dob, email, fname, gender, lname, residency, state, street, zip)
VALUES ('Lawrenceville', '1997-06-17', 'nirav@gmail.com', 'Nirav', 'M', 'Ilango', 'National', 'GA', '2773 Database Lane', 31341);



#departments
INSERT INTO department (dname, description)
VALUES ('CSCI', 'Department of Computer Science, Franklin College of Arts and Sciences');

INSERT INTO department (dname, description)
VALUES ('MATH', 'Department of Mathematics, Franklin College of Arts and Sciences');

INSERT INTO department (dname, description)
VALUES ('ELEE', 'Department of Electrical Engineering, College of Engineering');

INSERT INTO department (dname, description)
VALUES ('MECH', 'Department of Mechanical Engineering, College of Engineering');



#professors
INSERT INTO professor (fname, lname, dob, gender, email, street, city, state, zip, did)
VALUES ('John', 'Miller', '1965-09-18', 'M', 'jam@cs.uga.edu', '111 Database Way', 'Athens', 'GA', 30605, 1);

INSERT INTO professor (fname, lname, dob, gender, email, street, city, state, zip, did)
VALUES ('Khaled', 'Alweshah', '1980-01-28', 'M', 'alweshah@uga.edu', '876 Electricity Ave', 'Athens', 'GA', 30605, 3);

INSERT INTO professor (fname, lname, dob, gender, email, street, city, state, zip, did)
VALUES ('Hamid', 'Arabnia', '1970-10-30', 'M', 'arabnia@cs.uga.edu', '555 Computer Way', 'Athens', 'GA', 30605, 1);



#courses
INSERT INTO course (cnumber, cname, credits, description, semester, did, pid)
VALUES ('6370', 'Database Design', 4, 'Introduction to Relational Algebra, SQL, 3NF, and CNF', 'Fall 2017', 1, 1);

INSERT INTO course (cnumber, cname, credits, description, semester, did, pid)
VALUES ('6210', 'Linear Systems', 3, 'Introduction to Laplace and Fourier analysis', 'Fall 2017', 3, 2);

INSERT INTO course (cnumber, cname, credits, description, semester, did, pid)
VALUES ('6530', 'Intro to Robotics', 4, 'Introduction to probabilist theory and application', 'Spring 2018', 1, 2);

INSERT INTO course (cnumber, cname, credits, description, semester, did, pid)
VALUES ('6710', 'Computers', 4, 'All about computers', 'Fall 2017', 2, 1);



#registration
INSERT INTO student_course_grade(cid, sid, grade)
VALUES (1, 1, '81');

INSERT INTO student_course_grade(cid, sid, grade)
VALUES (1, 2, '89');

INSERT INTO student_course_grade(cid, sid, grade)
VALUES (1, 3, '76');

INSERT INTO student_course_grade(cid, sid, grade)
VALUES (2, 1, '90');

INSERT INTO student_course_grade(cid, sid, grade)
VALUES (2, 2, '87');

INSERT INTO student_course_grade(cid, sid, grade)
VALUES (2, 3, '96');

INSERT INTO student_course_grade(cid, sid, grade)
VALUES (2, 4, '79');

INSERT INTO student_course_grade(cid, sid, grade)
VALUES (3, 1, '99');

INSERT INTO student_course_grade(cid, sid, grade)
VALUES (3, 2, '67');

INSERT INTO student_course_grade(cid, sid, grade)
VALUES (3, 3, '85');



#majors
INSERT INTO student_department_major(did, sid, major)
VALUES (4, 1, 'Computer Science');

INSERT INTO student_department_major(did, sid, major)
VALUES (3, 1, 'Electrical Engineering');

INSERT INTO student_department_major(did, sid, major)
VALUES (2, 3, 'Applied Mathematics');

INSERT INTO student_department_major(did, sid, major)
VALUES (3, 2, 'Computer Systems Engineering');

INSERT INTO student_department_major(did, sid, major)
VALUES (1, 4, 'Agricultural Engineering');

