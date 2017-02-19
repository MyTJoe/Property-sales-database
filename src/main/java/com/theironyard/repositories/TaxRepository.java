package com.theironyard.repositories;

import com.theironyard.entities.TaxRecords;
import org.springframework.data.repository.CrudRepository;

public interface TaxRepository extends CrudRepository<TaxRecords, Integer> {
    TaxRecords findByCategory(String Category);
}
