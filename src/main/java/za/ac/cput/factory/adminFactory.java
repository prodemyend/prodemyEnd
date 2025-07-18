
package za.ac.cput.factory;

import za.ac.cput.domain.Admin;
import za.ac.cput.util.Helper;

public class adminFactory {
    public static Admin buildAdmin(String firstName, String lastName,
                                   String email, String password, String confirmPassword) {

        if (Helper.isNullorEmpty(firstName) || Helper.isNullorEmpty(lastName) || Helper.isNullorEmpty(email)
                || Helper.isNullorEmpty(password) || Helper.isNullorEmpty(confirmPassword)) {
            return null;
        }
        if (!Helper.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }


        return new Admin.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setConfirmPassword(confirmPassword)
                .setRole("ADMIN")
                .build();
    }
}

