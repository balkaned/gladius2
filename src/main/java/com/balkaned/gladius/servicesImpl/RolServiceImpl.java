package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Role;
import com.balkaned.gladius.dao.RolDao;
import com.balkaned.gladius.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    RolDao dao;

    public List<Role> listarRoles(){return dao.listarRoles();}
    public void insertarRole(Role rol){dao.insertarRole(rol);}

}
