package com.ironyard.repo;

import com.ironyard.data.Permission;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jasonskipper on 10/31/16.
 */
public interface PermissionRepository extends PagingAndSortingRepository<Permission, Long>{
}
