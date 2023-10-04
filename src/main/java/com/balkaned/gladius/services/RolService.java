package com.balkaned.gladius.services;
;
import com.balkaned.gladius.beans.Role;
import java.util.List;

public interface RolService {

    public List<Role> listarRoles();
    public void insertarRole(Role rol);

}
