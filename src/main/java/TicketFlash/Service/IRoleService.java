package TicketFlash.Service;

import TicketFlash.Model.Role;

import java.util.List;

public interface IRoleService {
    public Role saveRole(Role role);

    public List<Role> getAllRoles();

    public Role getRoleById(Long id);
}
