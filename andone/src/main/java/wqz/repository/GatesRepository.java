package wqz.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wqz.domain.Gates;

@Repository
public interface GatesRepository extends PagingAndSortingRepository<Gates, Integer>{
	Gates findByName(String name);
	@Modifying      
    @Transactional  
    // @Param
    @Query("update Gates gates set gates.name=:qName, gates.address=:qAddress, gates.status=:qStatus where gates.id=:qId")
    public void updateGates(@Param("qName") String name, @Param("qAddress") String address,
                           @Param("qStatus") String status, @Param("qId") Integer id);
}
