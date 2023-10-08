package com.balkaned.gladius.services;
;
import com.balkaned.gladius.beans.Role;
import com.balkaned.gladius.beans.Rolesxopciones;
import java.util.List;

public interface RolService {

    public List<Role> listarRoles();
    public void insertarRole(Role rol);
    public List<Rolesxopciones> listarRolesxOpcion(Integer codrol);
    public void insertarRolesxopciones(Rolesxopciones rolxopc);
    public Role getRole(Role codrol);
    public void actualizarRole(Role rol);
    public void actualizarRolesxopciones(Rolesxopciones rolxopc);
    public Rolesxopciones getRolesxopciones(Rolesxopciones rolxopc);

}
