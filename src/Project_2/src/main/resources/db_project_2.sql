
CREATE TABLE Person(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(100) NOT NULL UNIQUE,
    year_of_birth int NOT NULL
);


CREATE TABLE Book(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    person_id int REFERENCES Person(id) ON DELETE SET NULL,
    name varchar(100) NOT NULL,
    author varchar(100) NOT NULL,
    year_of_production int,
    date_of_rent TIMESTAMP
);


INSERT INTO Person(name, year_of_birth)
VALUES
('������ ���� ��������', 1970),
('������ ���� ��������', 1960),
('�������� ������� ����������', 1989),
('������� ������ ���������', 1995),
('������� ���� ���������', 1975);

INSERT INTO Book(name, author, year_of_production)
VALUES
('���� ��', '������� ������', 1991),
('������ � �������', '������� ������', 1996),
('��������� ����� ��������', '������� ������', 2004),
('������ ����', '������ ����', 2004),
('������', '������ ����', 1977),
('��������������', '������ ����', 1978),
('�����������', '��� �������', 1899),
('�������', '��� �������', 1852),
('����������', '��� �������', 1854),
('������', '��� �������', 1857);

select * from Person;

select * from Book;

drop table Person;
drop table Book;