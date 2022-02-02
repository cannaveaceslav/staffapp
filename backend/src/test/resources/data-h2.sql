CREATE TABLE APP_COMPANY
(
    ID           NUMBER(19) primary key,
    COMPANY_NAME VARCHAR(255),
    CREATED_AT   TIMESTAMP(6),
    DESCRIPTION  VARCHAR(255),
    ENABLED      NUMBER(1),
    MODIFIED_AT  TIMESTAMP(6)
);

CREATE TABLE APP_LOCATION
(
    ID              NUMBER(19) primary key,
    AVAILABLE       NUMBER(1),
    CREATED_AT      TIMESTAMP(6),
    DESCRIPTION     VARCHAR2(255 CHAR),
    LOCATION_NUMBER NUMBER(19),
    MODIFIED_AT     TIMESTAMP(6),
    APP_EMPLOYEE_ID NUMBER(19) null
);

INSERT INTO APP_LOCATION (id, available, created_at, description, location_number, APP_EMPLOYEE_ID)
values (1001, 1, sysdate, 1, 1001, null);

INSERT INTO APP_LOCATION (id, available, created_at, description, location_number, APP_EMPLOYEE_ID)
values (1002, 1, sysdate, 1, 1002, null);

