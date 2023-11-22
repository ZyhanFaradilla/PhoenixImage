package com.phoenix.Phoenix.service;

import com.phoenix.Phoenix.dao.AdministratorsRepository;
import com.phoenix.Phoenix.dao.GuestRepository;
import com.phoenix.Phoenix.dto.account.ChangePasswordAdministratorDTO;
import com.phoenix.Phoenix.dto.guest.ChangePasswordGuestDTO;
import com.phoenix.Phoenix.dto.guest.RegisterGuestDTO;
import com.phoenix.Phoenix.entity.Guests;
import com.phoenix.Phoenix.utility.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GuestService implements UserDetailsService {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private AdministratorsRepository administratorsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean isUsernameGuestExist(String username){
        var totalUsername = guestRepository.countExistingUsername(username);
        return (totalUsername > 0);
    }

    public Boolean isUsernameAdministratorExist(String username){
        var totalUsername = administratorsRepository.countExistingUsername(username);
        return (totalUsername > 0);
    }

    public void registerGuest(RegisterGuestDTO dto){
        var entity = new Guests();
        entity.setUsername(dto.getUsername());
        var hashPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashPassword);
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setGender(dto.getGender());
        entity.setCitizenship(dto.getCitizenship());
        entity.setIdNumber(dto.getIdNumber());
        guestRepository.save(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            var guest = guestRepository.findById(username).get();
            if(guest == null){
                throw new UsernameNotFoundException("User tidak ditemukan.");
            } else {
                var userDetail = new ApplicationUserDetails(guest);
                return userDetail;
            }
        } catch (Exception exception){
            var administrator = administratorsRepository.findById(username).get();
            if(administrator == null){
                throw new UsernameNotFoundException("User tidak ditemukan.");
            } else {
                var userDetail = new ApplicationUserDetails(administrator);
                return userDetail;
            }
        }
    }

    public Boolean checkUsernamePasswordGuest(String username, String password){
        var isAuthenticated = false;
        var entity = guestRepository.findById(username).get();
        if(entity != null){
            var hashPassword = entity.getPassword();
            isAuthenticated = passwordEncoder.matches(password, hashPassword);
        }
        return isAuthenticated;
    }

    public Boolean checkUsernamePasswordAdministrator(String username, String password){
        var isAuthenticated = false;
        var entity = administratorsRepository.findById(username).get();
        if(entity != null){
            var hashPassword = entity.getPassword();
            isAuthenticated = passwordEncoder.matches(password, hashPassword);
        }
        return isAuthenticated;
    }

    public void changePasswordAdministrator(ChangePasswordAdministratorDTO dto){
        var entity = administratorsRepository.findById(dto.getUsername()).get();
        var hashEncode = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashEncode);
        administratorsRepository.save(entity);
    }

    public void changePasswordGuest(ChangePasswordGuestDTO dto){
        var entity = guestRepository.findById(dto.getUsername()).get();
        var hashEncode = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashEncode);
        guestRepository.save(entity);
    }

}
