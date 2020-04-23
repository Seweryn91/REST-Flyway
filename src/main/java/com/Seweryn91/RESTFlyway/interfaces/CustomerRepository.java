package com.Seweryn91.RESTFlyway.interfaces;

import com.Seweryn91.RESTFlyway.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
