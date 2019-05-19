package wqz.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import wqz.domain.GateStatus;

@Repository
public interface GateStatusRepository extends PagingAndSortingRepository<GateStatus, Integer>{

	Page<GateStatus> findByName(String name,Pageable pageable);
}
