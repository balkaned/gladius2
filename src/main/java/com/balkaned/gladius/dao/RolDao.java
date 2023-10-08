package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Role;
import com.balkaned.gladius.beans.Rolesxopciones;
import java.util.List;

public interface RolDao {
    public List<Role> listarRoles();
    public void insertarRole(Role rol);
    public List<Rolesxopciones> listarRolesxOpcion(Integer codrol);
    public void insertarRolesxopciones(Rolesxopciones rolxopc);
    public Role getRole(Role codrol);
    public void actualizarRole(Role rol);
    public Rolesxopciones getRolesxopciones(Rolesxopciones rolxopc);
    public void actualizarRolesxopciones(Rolesxopciones rolxopc);

}
