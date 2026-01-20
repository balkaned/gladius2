package com.balkaned.gladius.roles.Infrastructure;

import com.balkaned.gladius.roles.Domain.Role;
import com.balkaned.gladius.roles.Domain.Rolesxopciones;

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

    public void eliminarRole(Role rol);

    public void eliminarRolesxopciones(Rolesxopciones rolxopc);

}
