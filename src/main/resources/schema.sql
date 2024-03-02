CREATE TABLE BABY_NAME_INFORMATION (
                                        id INTEGER auto_increment,
                                       name VARCHAR(100) UNIQUE ,
                                       origin VARCHAR(2000),
                                       meaning VARCHAR(2000),
                                       similar_names VARCHAR(2000)
);
