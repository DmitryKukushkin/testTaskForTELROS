package com.ds.kukushkin.service;

import com.ds.kukushkin.entity.Role;
import com.ds.kukushkin.exception.ServiceException;

public interface RoleService {

    Role getRoleByName(String roleName) throws ServiceException;
}
