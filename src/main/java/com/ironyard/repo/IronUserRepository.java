package com.ironyard.repo;

import com.ironyard.data.IronUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jasonskipper on 10/31/16.
 */
public interface IronUserRepository extends PagingAndSortingRepository<IronUser,Long>{

    IronUser findByUsernameAndPassword(String username, String password);


}
