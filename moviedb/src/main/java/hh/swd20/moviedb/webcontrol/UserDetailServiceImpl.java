package hh.swd20.moviedb.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.swd20.moviedb.domain.User;
import hh.swd20.moviedb.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final UserRepository userrepository;

	@Autowired
	public UserDetailServiceImpl(UserRepository userrepository) {
		this.userrepository = userrepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	User currentuser = userrepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, currentuser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(currentuser.getRole()));
        return user;
    }  
}