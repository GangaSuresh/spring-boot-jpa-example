package com.jpa.test.bootjpaexample;

import com.jpa.test.bootjpaexample.Specifications.Filter;
import com.jpa.test.bootjpaexample.Specifications.QueryOperator;
import com.jpa.test.bootjpaexample.entities.Phone;
import com.jpa.test.bootjpaexample.entities.PhoneType;
import com.jpa.test.bootjpaexample.entities.User;
import com.jpa.test.bootjpaexample.Specifications.UserSpecs;
import com.jpa.test.bootjpaexample.repositories.PhoneRepository;
import com.jpa.test.bootjpaexample.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;
import java.util.function.Consumer;

import static com.jpa.test.bootjpaexample.Specifications.UserSpecs.*;
import static com.jpa.test.bootjpaexample.Specifications.UserSpecs.getUserByNameSpec;

@SpringBootApplication
public class BootjpaexampleApplication {

    public static void main(String[] args) {

        ApplicationContext context=SpringApplication.run(BootjpaexampleApplication.class, args);
        UserRepository userRepository=context.getBean(UserRepository.class);
        PhoneRepository phoneRepository=context.getBean(PhoneRepository.class);
        //////////////// WRITE /////////////////////////////////////////////////////////////////////////////////////////
//        User user=new User();
//        user.setName("Ganga Suresh");
//        user.setCity("Trivandrum");
//        user.setStatus("hi there");
//        //userRepository.save(user);
//
//
//        User user1=new User();
//        user1.setName("Ram ko");
//        user1.setCity("Delhi");
//        user1.setStatus("yooo");
//        //userRepository.save(user1);
//
//        User user2=new User();
//        user2.setName("Nina");
//        user2.setCity("Punjab");
//        user2.setStatus("hello");
//        //userRepository.save(user2);
////
////        //multiple users
//        List<User> users=List.of(user,user1,user2);
//        Iterable<User> result=userRepository.saveAll(users);
//
//
//        result.forEach(us->{
//            System.out.println(us);
//        });

        /////////////////UPDATE////////////////////////////////////////////////////////////////////////////////////////////

//        //update use with id 89
//        Optional<User> optional=userRepository.findById(89);
//        User user =optional.get();
//        user.setStatus("today is Sunday");
//        User result =userRepository.save(user);
//        System.out.println(result);


        ////////////////////// READ //////////////////////////////////////////////////////////////////////////////////////
        //one user
//        Optional<User> optional=userRepository.findById(89);
//        User user =optional.get();
//        System.out.println(user);
        //all users
//        Iterable<User> itr=userRepository.findAll();
//        Iterator<User> itrator=itr.iterator();
//        //or
//        while (itrator.hasNext()){
//            User user=itrator.next();
//            System.out.println(user);
//        }
//        //or
//        itr.forEach(new Consumer<User>() {
//            @Override
//            public void accept(User user) {
//                System.out.println(user);
//            }
//        });
//        //or
//        itr.forEach(user -> System.out.println(user));

        ///////////DELETE //////////////////////////////////////////////////////////////////////////////////////////////
       //by id
        //userRepository.deleteById(89);
       //by iterable
//        Iterable<User> allusers= userRepository.findAll();
//        allusers.forEach(user -> System.out.println(user));
//        userRepository.deleteAll(allusers);

        //////////////////////// custom finder methods///////////////////////////////////////////////////////////////////
//        List<User> users=userRepository.findByName("Ganga Suresh");
//        users.forEach(user -> System.out.println(user));
//        List<User> users=userRepository.findByNameLike("%in%");
//        users.forEach(user -> System.out.println(user));


        //////////////////// JPQL queries ///////////////////////////////////////////////////////////////////////////////
//        List<User> users=userRepository.getAllUsers();
//        users.forEach(user -> System.out.println(user));

//              List<User> users=userRepository.getUserByName("Ganga Suresh");
//       users.forEach(user -> System.out.println(user));

//         List<User> users=userRepository.getUserByNameAndCity("Ganga Suresh","Trivandrum");
//         users.forEach(user -> System.out.println(user));

//        //native sql query
//      List<User> users=userRepository.getUsers();
//      users.forEach(user -> System.out.println(user));

///////////////////////////////// SPECIFICATIONS //////////////////////////////////////////////////////////////////////////
//    List<User> users=userRepository.findAll(getUserByNameSpec("Ganga Suresh"));
//        users.forEach(user -> System.out.println(user));
//
//        List<Phone> phone=phoneRepository.findAll(getPhoneByTypeSpec(PhoneType.Cell));
//        phone.forEach(phone1 -> System.out.println(phone1));

//        List<User> users1=userRepository.findAll(getUserByPhoneTypeSpec(PhoneType.Home).and(getUserByNameSpec("Nina")));
//        users1.forEach(user -> System.out.println(user));
//        List<User> users=userRepository.findAll(getEmployeesByNameAndPhoneTypeSpec("Ganga Suresh",PhoneType.Cell));
//        users.forEach(user -> System.out.println(user));

        ///////////////////////////////PAGABLE and sort ////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        Pageable firstPageWithTwoElements = PageRequest.of(0, 2, Sort.by("city").descending());
//
//        Pageable secondPageWithFiveElements = PageRequest.of(1, 5);
//        List<User> users=userRepository.findAllByStatusLike("h%",firstPageWithTwoElements);
//        users.forEach(user -> System.out.println(user));
//
//        Iterable<User> allusers= userRepository.findAll(Sort.by("city"));
//        allusers.forEach(user -> System.out.println(user));

/////////////////////////////////////////////////// FILTER ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        Filter categories =new Filter();
        categories.setField("city");
        categories.setOperator(QueryOperator.Equals);
        List<String> ct= Arrays.asList("Trivandrum","Delhi");
        categories.setValues(ct);
        categories.setValue("Trivandrum");

        Filter lowRange =new Filter();
        lowRange.setField("name");
        lowRange.setOperator(QueryOperator.Like);
        lowRange.setValue("G%");


        List<Filter> filters = new ArrayList<>();
        filters.add(lowRange);
        //filters.add(categories);
        System.out.println("Filters");
        List<User> users=userRepository.findAll( getSpecificationFromFilters(filters));
        users.forEach(user -> System.out.println(user));



    }

}
