package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

public class customerFactory {

    public static Customer buildCustomer(String firstName, String lastName,
                                         String email, String password) {

        if (Helper.isNullorEmpty(firstName) || Helper.isNullorEmpty(lastName) ||
                Helper.isNullorEmpty(email) || Helper.isNullorEmpty(password)) {
            return null;
        }

        if (!Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        return new Customer.Builder()
                .SetfirstName(firstName)
                .SetlastName(lastName)
                .SetEmail(email)
                .SetPassword(password)
                .SetRole("USER")
                .build();
    }
}
