import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // In a real application, you would fetch user details from a database
        return new User("admin", "$2a$12$Qe8ZL5H/RuysX7R9FxZ3fuJngA.P3Xz0kRyMZCUIgxHcwl4P2P1cC", new ArrayList<>());
    }
}
