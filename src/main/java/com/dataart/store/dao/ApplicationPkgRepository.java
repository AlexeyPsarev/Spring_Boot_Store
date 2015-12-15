package com.dataart.store.dao;

import com.dataart.store.domain.ApplicationPkg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationPkgRepository extends PagingAndSortingRepository<ApplicationPkg, Integer>
{
	public ApplicationPkg findByPkgName(String name);
	public Page<ApplicationPkg> findByCategory(String category, Pageable request);
	public Long countByCategory(String category);
}
