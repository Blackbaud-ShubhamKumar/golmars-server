package com.marse.martian.repos.base;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marse.martian.entities.Node;

@Repository
public interface NodeRepository extends BaseRepository<Node, Long> {

	String GET_ALL_PARENTS = "SELECT T2.id, T2.child_count" + "FROM (" + " SELECT" + "@r AS _id,"
			+ "        (SELECT @r := parent_node FROM node WHERE id = _id) AS parent_node,"
			+ "        @l := @l + 1 AS lvl" + "    FROM" + "        (SELECT @r := :sponsorId, @l := 0) vars,"
			+ "        node h" + "    WHERE @r <> 0) T1" + "JOIN node T2" + "ON T1._id = T2.id"
			+ "ORDER BY T1.lvl DESC;";

	String INSERT_QUERY = "insert into wallet (type,node,detail,amount,trans_type,trans_date,trans_status,created_on,updated_on,created_by,updated_by)"
			+ "					select 'MAIN',t.id,'detail demo',900.0, 'CREDIT',NOW(),'PENDING',NOW(),NOW(),'SYSTEM','SYSTEM'"
			+ "					from tempparentchild t where (t.child_count+1) not in (2,6,10,14,18,22,26) and (((t.child_count+1) = 3) or (((t.child_count+1) % 2) = 0));";

	String UPDATE_QUERY = "update node set child_count = (child_count+1)  where id in (:ids);";

	@Procedure(procedureName = "update_tree_payment")
	public int updateTreeNodesChildCountAndProcessPayment(@Param("sponsorId") Long sponsorId);

	@Query(nativeQuery = true, value = GET_ALL_PARENTS)
	public Optional<Object[]> getNodeToIncrementChildCount(@Param("sponsorId") Long sponsorId);
	
	@Query(nativeQuery = true, value = UPDATE_QUERY)
	public void getNodeToIncrementChildCount(@Param("ids") Object[] ids);

}
