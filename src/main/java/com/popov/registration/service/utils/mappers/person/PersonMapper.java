package com.popov.registration.service.utils.mappers.person;

import com.popov.registration.service.entity.person.Person;
import com.popov.registration.service.entity.person.dto.PersonDTO;
import com.popov.registration.service.entity.person.etc.Role;
import com.popov.registration.service.entity.person.etc.Status;
import com.popov.registration.service.utils.mappers.person.qualifires.ToDefaultRole;
import com.popov.registration.service.utils.mappers.person.qualifires.ToDefaultStatus;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "role", qualifiedBy = ToDefaultRole.class)
    @Mapping(target = "status", qualifiedBy = ToDefaultStatus.class)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Person toPerson(PersonDTO personDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePersonFromDto(Person personFrom, @MappingTarget Person personTo);


    @ToDefaultRole
    static Role setRole(Role role) {
        return Role.USER;
    }

    @ToDefaultStatus
    static Status setStatus(Status status) {
        return Status.ACTIVE;
    }
}
