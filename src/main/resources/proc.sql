delimiter $$
CREATE PROCEDURE `update_tree_payment`(in sponsorId bigint(25),OUT param_sp_success TINYINT)
BEGIN
DECLARE exit handler for sqlexception  begin   rollback;   end;
DECLARE exit handler for sqlwarning begin   rollback;   end;
SET param_sp_success = 0;
START TRANSACTION;
CREATE TEMPORARY TABLE tempparentchild
SELECT T2.id, T2.child_count
FROM (
    SELECT
        @r AS _id,
        (SELECT @r := parent_node FROM node WHERE id = _id) AS parent_node,
        @l := @l + 1 AS lvl
    FROM
        (SELECT @r := sponsorId, @l := 0) vars,
        node h
    WHERE @r <> 0) T1
JOIN node T2
ON T1._id = T2.id
ORDER BY T1.lvl DESC;

insert into wallet 
(type,node,detail,amount,trans_type,
trans_date,trans_status,created_on,updated_on,
created_by,updated_by)
select 'MAIN',t.id,'detail demo',900.0,
'CREDIT',NOW(),'PENDING',NOW(),NOW(),"SYSTEM","SYSTEM"
from tempparentchild t
where (t.child_count+1) not in (2,6,10,14,18,22,26)
and (((t.child_count+1) = 3) or (((t.child_count+1) % 2) = 0));

update node set child_count = (child_count+1) 
where id in (select id from tempparentchild);

drop table tempparentchild;
SET param_sp_success = 1;
commit;
END$$

