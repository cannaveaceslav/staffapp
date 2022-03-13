
CREATE TABLE STAFFAPP.APP_EMPLOYEE
(
  ID                 NUMBER(19)                 NOT NULL,
  BIRTHDAY           DATE                       NOT NULL,
  CREATED_AT         TIMESTAMP(6)               DEFAULT SYSDATE,
  EMAIL              VARCHAR2(255 CHAR)         NOT NULL,
  ENABLED            NUMBER(1),
  FIRST_NAME         VARCHAR2(255 CHAR)         NOT NULL,
  IMAGE              VARCHAR2(255 CHAR),
  LAST_NAME          VARCHAR2(255 CHAR)         NOT NULL,
  MODIFIED_AT        TIMESTAMP(6),
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


ALTER TABLE STAFFAPP.APP_EMPLOYEE ADD (
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


--  There is no statement for index STAFFAPP.SYS_C008013.
--  The object is created when the parent object is created.