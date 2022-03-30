CREATE TABLE STAFFAPP.APP_EMPLOYEE_HIST
(
  ID                 NUMBER(19)                 NOT NULL,
  BIRTHDAY           TIMESTAMP(6)               NOT NULL,
  CREATED_AT         TIMESTAMP(6)               DEFAULT SYSDATE,
  DELETED_AT         TIMESTAMP(6),
  EMAIL              VARCHAR2(255 CHAR)         NOT NULL,
  EMPLOYEE_ID        NUMBER(19),
  ENABLED            NUMBER(1),
  FIRST_NAME         VARCHAR2(255 CHAR)         NOT NULL,
  IMAGE              VARCHAR2(255 CHAR),
  LAST_NAME          VARCHAR2(255 CHAR)         NOT NULL,
  APP_COMPANY_ID     NUMBER(19)                 NOT NULL,
  APP_DEPARTMENT_ID  NUMBER(19)                 NOT NULL,
  APP_LOCATION_ID    NUMBER(19)
)

TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING
NOCOMPRESS
NOCACHE;


ALTER TABLE STAFFAPP.APP_EMPLOYEE_HIST ADD (
  PRIMARY KEY
  (ID)
  USING INDEX
    TABLESPACE USERS
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               )
  ENABLE VALIDATE);
