CREATE TABLE STAFFAPP.CONFIRMATION_TOKEN
(
  ID            NUMBER(19)                      NOT NULL,
  CONFIRMED_AT  TIMESTAMP(6),
  CREATED_AT    TIMESTAMP(6)                    NOT NULL,
  EXPIRES_AT    TIMESTAMP(6)                    NOT NULL,
  TOKEN         VARCHAR2(255 CHAR)              NOT NULL,
  APP_USER_ID   NUMBER(19)                      NOT NULL
)
TABLESPACE STAFFAPP_DAT
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


ALTER TABLE STAFFAPP.CONFIRMATION_TOKEN ADD (
  PRIMARY KEY
  (ID)
  USING INDEX
    TABLESPACE STAFFAPP_DAT
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                PCTINCREASE      0
                BUFFER_POOL      DEFAULT
               )
  ENABLE VALIDATE);


--  There is no statement for index STAFFAPP.SYS_C00198923.
--  The object is created when the parent object is created.

ALTER TABLE STAFFAPP.CONFIRMATION_TOKEN ADD (
  CONSTRAINT FKO9FL25WQYH7W7MNFKDQEN1RCM
  FOREIGN KEY (APP_USER_ID)
  REFERENCES STAFFAPP.APP_USER (ID)
  ENABLE VALIDATE);
