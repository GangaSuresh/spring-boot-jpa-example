package com.jpa.test.bootjpaexample.Specifications;

import com.jpa.test.bootjpaexample.entities.Phone;
import com.jpa.test.bootjpaexample.entities.PhoneType;
import com.jpa.test.bootjpaexample.entities.User;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.metamodel.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;


public class UserSpecs {
    public static Specification<User> getUserByNameSpec(String name) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate = criteriaBuilder.equal(root.get("name"), name);
                return equalPredicate;
            }
        };
    }
    public static Specification<User> getUserByNameLikeSpec(String name) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate = criteriaBuilder.like(root.get("name"), "%"+name+"%");
                return equalPredicate;
            }
        };
    }
    public static Specification<Phone> getPhoneByTypeSpec(PhoneType phoneType) {
        return new Specification<Phone>() {
            @Override
            public Predicate toPredicate(Root<Phone> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate equalPredicate = criteriaBuilder.equal(root.get("type"), phoneType);
                return equalPredicate;
            }
        };
    }

    public static Specification<User> getUserByPhoneTypeSpec(PhoneType phoneType) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<User, Phone> phoneJoin = root.join("phones");
                Predicate equalPredicate = criteriaBuilder.equal(phoneJoin.get("type"), phoneType);
                return equalPredicate;
            }
        };
    }
    public static Specification<User> getEmployeesByNameOrPhoneTypeSpec(String name,PhoneType phoneType) {
        return where(getUserByNameSpec(name))
                .or(getUserByPhoneTypeSpec(phoneType));
    }

    public static Specification<User> getEmployeesByNameAndPhoneTypeSpec(String name, PhoneType phoneType) {
        return where(getUserByNameSpec(name))
                .and(getUserByPhoneTypeSpec(phoneType));
    }

    public static Specification<User> getEmployeeByNotNameSpec(String name) {
        return Specification.not(getUserByNameSpec(name));
    }

    public static Specification<User> test(PhoneType phoneType) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join<User, Phone> phoneJoin = root.join("phones");
                return  criteriaBuilder.greaterThan(phoneJoin.get("type"), phoneType);
            }
        };
    }


    ///////////////////////////////FILTER/////////////////////////
    private static Specification<User> createSpecification(Filter input) {
        switch (input.getOperator()){

            case Equals:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case Not_Equals:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(input.getField()),
                                castToRequiredType(root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case Greater_Than:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(input.getField()),
                                (Number) castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case Less_Than:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(input.getField()),
                                (Number) castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValue()));

            case Like:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(input.getField()),
                                "%"+input.getValue()+"%");

            case In:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(input.getField()))
                                .value(castToRequiredType(
                                        root.get(input.getField()).getJavaType(),
                                        input.getValues()));

            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }
    private static Object castToRequiredType(Class fieldType, String value) {
        if(fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if(fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if(Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        }
        return null;
    }

    private static Object castToRequiredType(Class fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }

    public static Specification<User> getSpecificationFromFilters(List<Filter> filter){
        Specification<User> specification = where(null);
        for (Filter input : filter) {
            specification = specification.and(createSpecification(input));

        }
        return specification;
    }
}
