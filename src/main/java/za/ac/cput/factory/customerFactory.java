package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

public class customerFactory {

    public static Customer buildCustomer(String firstName,
                                         String lastName,
                                         String email,
                                         String password,
                                         String contactNumber) {

        if (Helper.isNullorEmpty(firstName) || Helper.isNullorEmpty(lastName) || !Helper.isValidEmail(email) || Helper.isNullorEmpty(password) || Helper.isNullorEmpty(contactNumber)) {
            return null;
        }

        return new Customer.Builder()
                .SetFirstName(firstName)
                .SetLastName(lastName)
                .SetEmail(email)
                .SetPassword(password)
                .SetContactNumber(contactNumber)
                .SetRole("USER")
                .build();
    }
}