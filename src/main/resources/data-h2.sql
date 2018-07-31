
INSERT INTO auth_detail VALUES (1, 'GM12345678', '2167abb34859bf0bdd5e2a62702f399ffa972646fb4c3f975cb194fe4eecd34d', TRUE, NOW(),NOW(),"SYSTEM","SYSTEM");

INSERT INTO authorities VALUES (1, 'CREATE', 'User can create data with this privilage.',NOW(),NOW(),"SYSTEM","SYSTEM");
INSERT INTO authorities VALUES (2, 'UPDATE', 'User can update data with this privilage.',NOW(),NOW(),"SYSTEM","SYSTEM");
INSERT INTO authorities VALUES (3, 'READ', 'User can read data with this privilage.',NOW(),NOW(),"SYSTEM","SYSTEM");
INSERT INTO authorities VALUES (4, 'DELETE', 'User can delete data with this privilage.',NOW(),NOW(),"SYSTEM","SYSTEM");

insert into users_authorities values(1,1);
insert into users_authorities values(1,2);
insert into users_authorities values(1,3);
insert into users_authorities values(1,4);

insert into bank_detail values(1,'1234567890','SBI','SBI123','address1','cgdpk6894f','image.png',null,NOW(),NOW(),"SYSTEM","SYSTEM");

insert into profile values(1,'Arif','Muhammad','superadmin','MALE',NOW(),'Director','MORADABAD','122016','niet.skc@gmail.com','8630515042',
'111111111111','Gurgaon Haryana','image.png',null,NOW(),NOW(),"SYSTEM","SYSTEM");

insert into node values (12345678,'LEFT',null,null,null,1,1,1,0,1,NOW(),NOW(),"SYSTEM","SYSTEM");

