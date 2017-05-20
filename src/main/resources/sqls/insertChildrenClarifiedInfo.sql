insert into children_clarified_info (child_id, birth_certificate_presence, first_name, last_name, middle_name, birth_date,
                                     address, birth_place, occupation)
values (
  1, --childID
  true,'FirstnameC', 'LastnameC', 'MiddlenameC', to_date('2007-09-01', 'YYYY-MM-DD'),'address123', 'birth_place123', 'occupation123');