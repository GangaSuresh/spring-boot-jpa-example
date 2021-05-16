package com.jpa.test.bootjpaexample.repositories;

import com.jpa.test.bootjpaexample.entities.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Predicate;


public interface UserRepository extends CrudRepository<User,Integer>,JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, Integer> {
    public List<User> findByName(String name);
    public List<User> findByCity(String city);
    public List<User> findByNameAndCity(String name,String city);
    public List<User> findByNameLike(String pattern);
    List<User> findAllByStatusLike(String pattern, Pageable pageable);

    @Query("select u from User u")
    public  List<User> getAllUsers();

    //native sql
    @Query(value = "select * from user",nativeQuery = true)
    public  List<User> getUsers();

    @Query("select u from User u where u.name=:n")
    public  List<User> getUserByName(@Param("n") String name);

    @Query("select u from User u where u.name=:n and u.city=:c")
    public  List<User> getUserByNameAndCity(@Param("n") String name,@Param("c") String city);


}
