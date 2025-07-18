package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ContactSupport;
import za.ac.cput.repository.contactSupportRepository;

import java.util.List;

@Service
public class ContactSupportService implements IContactSupportService {

    private final contactSupportRepository contactSupportRepository;


    @Autowired
    public ContactSupportService(contactSupportRepository contactSupportRepository) {
        this.contactSupportRepository = contactSupportRepository;
    }



    public ContactSupport create(ContactSupport contactSupport) {
        return contactSupportRepository.save(contactSupport);
    }


    public ContactSupport read(Long messageId) {
        return contactSupportRepository.findById(messageId).orElse(null);
    }


    public ContactSupport update(ContactSupport contactSupport) {
        return contactSupportRepository.save(contactSupport);
    }


    public void delete(long messageId) {
        contactSupportRepository.deleteById(messageId);
    }


    public List<ContactSupport> getAll() {
        return contactSupportRepository.findAll();
    }
}
