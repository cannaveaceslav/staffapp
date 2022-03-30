CREATE OR REPLACE TRIGGER STAFFAPP.TAUIER_APP_EMPLOYEE
    before update or insert ON STAFFAPP.APP_EMPLOYEE
    for each row
declare

begin

    if (:new.APP_LOCATION_ID is not null) then
        DBMS_OUTPUT.PUT_LINE('OLD ID: '||:old.APP_LOCATION_ID);
        DBMS_OUTPUT.PUT_LINE('NEW ID: '||:new.APP_LOCATION_ID);
        update STAFFAPP.APP_LOCATION a
        set a.APP_EMPLOYEE_ID=:new.ID
        where a.ID=:new.APP_LOCATION_ID;
        --set AVAILABLE=false
        update STAFFAPP.APP_LOCATION a
        set a.AVAILABLE=0
        where a.ID=:new.APP_LOCATION_ID;
    end if;

end TAUIER_APP_EMPLOYEE;
/
