package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Admin;
import za.ac.cput.repository.adminRepository;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    private final adminRepository repository;

    @Autowired
    public AdminService(adminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Admin create(Admin admin) {
        Admin newAdmin = new Admin.Builder()
                .setId(admin.getId())
                .setFirstName(admin.getFirstName())
                .setLastName(admin.getLastName())
                .setEmail(admin.getEmail())
                .setPassword(admin.getPassword())
                .setRole("ADMIN")
                .build();

        return repository.save(newAdmin);
    }

    @Override
    public Admin read(Long adminId) {
        return repository.findById(adminId).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        return repository.findById(admin.getId())
                .map(existingAdmin -> {
                    Admin updatedAdmin = new Admin.Builder()
                            .setId(existingAdmin.getId())
                            .setFirstName(admin.getFirstName() != null ? admin.getFirstName() : existingAdmin.getFirstName())
                            .setLastName(admin.getLastName() != null ? admin.getLastName() : existingAdmin.getLastName())
                            .setEmail(admin.getEmail() != null ? admin.getEmail() : existingAdmin.getEmail())
                            .setPassword(admin.getPassword() != null && !admin.getPassword().isEmpty()
                                    ? admin.getPassword()
                                    : existingAdmin.getPassword())
                            .setRole(admin.getRole() != null ? admin.getRole() : existingAdmin.getRole())
                            .build();

                    return repository.save(updatedAdmin);
                })
                .orElseThrow(() -> new RuntimeException("Admin with ID " + admin.getId() + " does not exist."));
    }

    @Override
    public void delete(long adminId) {
        if (!repository.existsById(adminId)) {
            throw new RuntimeException("Admin with ID " + adminId + " does not exist.");
        }
        repository.deleteById(adminId);
    }


    @Override
    public List<Admin> getAll() {
        return repository.findAll();
    }

    public String verify(Admin admin) {
        Admin foundAdmin = repository.findByEmail(admin.getEmail());

        if (foundAdmin != null && foundAdmin.getPassword().equals(admin.getPassword())) {
            return "success";
        } else {
            return "fail";
        }
    }


}
