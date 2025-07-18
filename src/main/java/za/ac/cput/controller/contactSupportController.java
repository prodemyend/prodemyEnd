package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ContactSupport;
import za.ac.cput.service.ContactSupportService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class contactSupportController {

    private final ContactSupportService contactSupportService;

    @Autowired
    public contactSupportController(ContactSupportService contactSupportService) {
        this.contactSupportService = contactSupportService;
    }

    @PostMapping("/contact-support")
    public ContactSupport submitContactSupport(@RequestBody ContactSupport contactSupport) {
        return contactSupportService.create(contactSupport);
    }

    @GetMapping("/ping")
    public String ping() {
        return "Backend is running and accessible!";
    }

    @GetMapping("/allSupportMessages")
    public List<ContactSupport> getAllSupportMessages() {
        return contactSupportService.getAll();
    }

    @GetMapping("/read/{id}")
    public ContactSupport readContactSupport(@PathVariable Long id) {
        return contactSupportService.read(id);
    }

    @PostMapping("/update")
    public ContactSupport updateContactSupport(@RequestBody ContactSupport contactSupport) {
        return contactSupportService.update(contactSupport);
    }
}
