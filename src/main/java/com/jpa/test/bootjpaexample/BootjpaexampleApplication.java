package com.jpa.test.bootjpaexample;

import com.jpa.test.bootjpaexample.entities.User;
import com.jpa.test.bootjpaexample.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@SpringBootApplication
public class BootjpaexampleApplication {

    public static void main(String[] args) {

        ApplicationContext context=SpringApplication.run(BootjpaexampleApplication.class, args);
        UserRepository userRepository=context.getBean(UserRepository.class);
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

        


    }

}
