package com.phoenix.Phoenix.service;

import com.phoenix.Phoenix.dao.AdministratorsRepository;
import com.phoenix.Phoenix.dto.account.AdministratorsRowDTO;
import com.phoenix.Phoenix.dto.account.InsertAdministratorsDTO;
import com.phoenix.Phoenix.dto.account.UpdateAdministratorsDTO;
import com.phoenix.Phoenix.entity.Administrators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministratorsService {
    @Autowired
    private AdministratorsRepository administratorsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<AdministratorsRowDTO> getRowAdmin(Integer page){
        var pageable = PageRequest.of(page-1, 10, Sort.by("username"));
        return administratorsRepository.getRow(pageable);
    }

    public UpdateAdministratorsDTO getUpdateAdministrator(String username){
        var entity = administratorsRepository.findById(username).get();
        var dto = new UpdateAdministratorsDTO(
                entity.getUsername(),
                entity.getPassword(),
                entity.getJobTitle()
        );
        return dto;
    }

    public UpdateAdministratorsDTO updateAdministrator(UpdateAdministratorsDTO dto){
        dto.setPassword("indocyber");
        var hashPassword = passwordEncoder.encode(dto.getPassword());
        var entity = new Administrators(
                dto.getUsername(),
                hashPassword,
                dto.getJobTitle()
        );
        var response = administratorsRepository.save(entity);
        var responseDto = new UpdateAdministratorsDTO(
                response.getUsername(),
                response.getPassword(),
                response.getJobTitle()
        );
        return responseDto;
    }

    public Administrators insertAdministrator(InsertAdministratorsDTO dto){
        dto.setPassword("indocyber");
        var hashPassword = passwordEncoder.encode(dto.getPassword());
        var entity = new Administrators(
                dto.getUsername(),
                hashPassword,
                dto.getJobTitle()
        );
        return administratorsRepository.save(entity);
    }
}
