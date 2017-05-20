CREATE OR REPLACE FUNCTION child_initial_insert(
  _firstName varchar(255),
  _lastName  varchar(255),
  _middleName varchar(255),
  _birthDate date,
  _district  integer,
  _personalRecordCode varchar(255),
  _entranceDate  date,
  _isBirthCertificatePresent boolean,
  _clarifiedFirstName  varchar(255),
  _clarifiedLastName  varchar(255),
  _clarifiedMiddleName  varchar(255),
  _clarifiedBirthDate date,
  _address  varchar(255),
  _birthPlace  varchar(255),
  _occupation  varchar(255)
)
  RETURNS integer AS
$BODY$
DECLARE inserted_child_id integer;
BEGIN
  INSERT INTO children_basic_info(first_name, last_name, middle_name, birth_date, district_id, personal_record_code, entrance_date)
  VALUES(_firstName, _lastName, _middleName, _birthDate, _district, _personalRecordCode, _entranceDate)
  RETURNING child_id INTO inserted_child_id;

  insert into children_clarified_info(child_id, birth_certificate_presence, first_name, last_name, middle_name, birth_date,
                                      address, birth_place, occupation)
  values (inserted_child_id, _isBirthCertificatePresent, _clarifiedFirstName, _clarifiedLastName, _clarifiedMiddleName, _clarifiedBirthDate,
          _address, _birthPlace, _occupation);

  RETURN inserted_child_id;
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;

/*
select child_initial_insert(
    'firstb',
    'lastb',
    'middleb',
    to_date('2007-09-01', 'YYYY-MM-DD'),
    3,
    'personal code',
    to_date('2007-09-02', 'YYYY-MM-DD'),
    false,
    'firstbccccc',
    'lastbccccc',
    'middlebccccc',
    to_date('2007-09-03', 'YYYY-MM-DD'),
    'addresss',
    'birth_addresss',
    'occupatuion');

*/