package duan.sportify.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import duan.sportify.entities.Authorized;

public interface AuthorizedDAO extends JpaRepository<Authorized, Integer> {
	@Query(value = """
                       SELECT * FROM sportify.authorized\r
                       where username like :username""", nativeQuery = true)
	Authorized findAllAuthorized(String username);
	// lay quyên account
	@Query(value = """
                       SELECT 'Admin' AS description,\r
                              COALESCE((SELECT roleid FROM authorized WHERE username LIKE %:username% AND roleid LIKE '%R01%'), 'dont') AS roleid,\r
                              COALESCE((SELECT authorizedid FROM authorized WHERE username LIKE %:username% AND roleid LIKE '%R01%'), 'dont') as id\r
                       UNION ALL\r
                       SELECT 'Staff' AS description,\r
                              COALESCE((SELECT roleid FROM authorized WHERE username LIKE %:username% AND roleid LIKE '%R02%'), 'dont') AS roleid,\r
                              COALESCE((SELECT authorizedid FROM authorized WHERE username LIKE %:username% AND roleid LIKE '%R02%'), 'dont') as id\r
                       UNION ALL\r
                       SELECT 'User' AS description,\r
                              COALESCE((SELECT roleid FROM authorized WHERE username LIKE %:username% AND roleid LIKE '%R03%'), 'dont') AS roleid,\r
                              COALESCE((SELECT authorizedid FROM authorized WHERE username LIKE %:username% AND roleid LIKE '%R03%'), 'dont') as id;""", nativeQuery = true)
	List<Object[]> getRoleByUsername(@Param("username") String username);
}