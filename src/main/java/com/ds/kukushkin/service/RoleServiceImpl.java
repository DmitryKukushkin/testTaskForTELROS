package com.ds.kukushkin.service;

import com.ds.kukushkin.entity.Role;
import com.ds.kukushkin.exception.ServiceErrorCode;
import com.ds.kukushkin.exception.ServiceException;
import com.ds.kukushkin.repository.RoleRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {

    RoleRepo roleRepo;

    @Transactional
    @Override
    public Role getRoleByName(String roleName) throws ServiceException {
        return roleRepo.findByName(roleName)
                .orElseThrow(()-> new ServiceException(ServiceErrorCode.ERROR_CODE_NOT_FOUND));
    }
}
