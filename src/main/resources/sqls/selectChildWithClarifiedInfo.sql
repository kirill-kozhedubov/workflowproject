select basic.child_id child_id, basic.first_name bfirstn, basic.last_name blastn, basic.middle_name bmiddlen,
       basic.birth_date bbirth, basic.district_id district, basic.personal_record_code code, basic.entrance_date entrance_date,

       clarified.birth_certificate_presence certificate, clarified.first_name cfirstn, clarified.last_name clastn, clarified.middle_name cmiddlen,
       clarified.birth_date cbirth, clarified.address address, clarified.birth_place birth_place, clarified.occupation occupation

from children_basic_info basic
  join children_clarified_info clarified on basic.child_id = clarified.child_id;