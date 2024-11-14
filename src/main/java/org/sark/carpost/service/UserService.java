package org.sark.carpost.service;

import org.sark.carpost.dto.ProfileEditResponseDTO;
import org.sark.carpost.dto.RegisterRequestDTO;
import org.sark.carpost.entity.UserEntity;
import org.sark.carpost.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public ProfileEditResponseDTO getUserProfileInfoForEdit(){
        //TODO: брать id основываясь на security config
        Optional<UserEntity> userEntity = userRepository.findById(13);
        ProfileEditResponseDTO profileEditResponseDTO = new ProfileEditResponseDTO();
        if(userEntity.isPresent()){
            profileEditResponseDTO.setName(userEntity.get().getName());
            profileEditResponseDTO.setNumber(userEntity.get().getPhone_number());
            profileEditResponseDTO.setDriving_license(userEntity.get().getDriving_license());
            profileEditResponseDTO.setIssue_date(userEntity.get().getIssue_date());
        }
        logger.info(profileEditResponseDTO.toString());
        return profileEditResponseDTO;
    }

    public void addUser(RegisterRequestDTO registerRequestDTO) {
        UserEntity user = new UserEntity();
        user.setLogin(registerRequestDTO.getLogin());
        user.setName(registerRequestDTO.getName());
        user.setPassword(registerRequestDTO.getPassword());
        user.setPhone_number(registerRequestDTO.getPhone_number());
        logger.info("Adding user: " + user);
        userRepository.save(user);
    }
}
