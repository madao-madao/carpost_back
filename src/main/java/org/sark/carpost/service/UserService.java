package org.sark.carpost.service;

import org.sark.carpost.dto.ProfileEditResponseDTO;
import org.sark.carpost.dto.ProfileUpdateRequestDTO;
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

    public ProfileEditResponseDTO getUserProfileDTObyId(Long id) {
        //TODO: брать id основываясь на security config
        Optional<UserEntity> userEntity = userRepository.findById(id);
        ProfileEditResponseDTO profileEditResponseDTO = new ProfileEditResponseDTO();
        if(userEntity.isPresent()){
            profileEditResponseDTO.setName(userEntity.get().getName());
            profileEditResponseDTO.setNumber(userEntity.get().getPhoneNumber());
            profileEditResponseDTO.setDrivingLicense(userEntity.get().getDrivingLicense());
            profileEditResponseDTO.setIssueDate(userEntity.get().getIssueDate());
        }
        logger.info(profileEditResponseDTO.toString());
        return profileEditResponseDTO;
    }
    public void addUser(RegisterRequestDTO registerRequestDTO) {
        UserEntity user = new UserEntity();
        user.setLogin(registerRequestDTO.getLogin());
        user.setName(registerRequestDTO.getName());
        user.setPassword(registerRequestDTO.getPassword());
        user.setPhoneNumber(registerRequestDTO.getPhoneNumber());
        user = userRepository.save(user);
        logger.info("Adding user: " + user);
    }
    public void updateProfile(ProfileUpdateRequestDTO profileUpdateRequestDTO, Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent()) {
            UserEntity userEntity = user.get();
            userEntity.setDrivingLicense(profileUpdateRequestDTO.getDrivingLicense());
            userEntity.setIssueDate(profileUpdateRequestDTO.getIssueDate());
            userEntity.setName(profileUpdateRequestDTO.getName());
            userEntity.setPhoneNumber(profileUpdateRequestDTO.getPhoneNumber());
            userRepository.save(userEntity);
        }
    }
}
