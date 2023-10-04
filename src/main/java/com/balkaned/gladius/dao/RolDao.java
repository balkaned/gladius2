package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Role;
import java.util.List;

public interface RolDao {
    public List<Role> listarRoles();
    public void insertarRole(Role rol);

}
