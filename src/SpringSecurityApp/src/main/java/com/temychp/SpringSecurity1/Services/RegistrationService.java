package com.temychp.SpringSecurity1.Services;

import com.temychp.SpringSecurity1.models.Person;
import com.temychp.SpringSecurity1.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void register(Person person) {

        peopleRepository.save(person);
    }


}







