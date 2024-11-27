package org.sark.carpost.service;

import org.sark.carpost.dto.ProfileEditResponseDTO;
import org.sark.carpost.dto.ProfileResponseDTO;
import org.sark.carpost.dto.ProfileUpdateRequestDTO;
import org.sark.carpost.dto.RegisterRequestDTO;
import org.sark.carpost.entity.CarEntity;
import org.sark.carpost.entity.UserEntity;
import org.sark.carpost.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public ProfileEditResponseDTO getUserProfileForEditDTO(Long id) {
        //TODO: брать id основываясь на security config
        Optional<UserEntity> userEntity = userRepository.findById(id);
        ProfileEditResponseDTO profileEditResponseDTO = new ProfileEditResponseDTO();
        if(userEntity.isPresent()){
            profileEditResponseDTO.setName(userEntity.get().getName());
            profileEditResponseDTO.setPhoneNumber(userEntity.get().getPhoneNumber());
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
    public ProfileResponseDTO getUserProfileDTO(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: 13"));
        ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO();
        profileResponseDTO.setName(userEntity.getName());
        profileResponseDTO.setPhoneNumber(userEntity.getPhoneNumber());
        profileResponseDTO.setDrivingLicense(userEntity.getDrivingLicense());
        profileResponseDTO.setCars(userEntity.getCars());
        // Получаем дату issueDate из объекта userEntity (тип Date)
        Date userIssueDate = userEntity.getIssueDate();
        // Преобразуем Date в LocalDate (извлекаем только дату без времени)
        LocalDate localIssueDate = userIssueDate.toInstant()
                                                .atZone(ZoneId.systemDefault())// Учитываем системную временную зону
                                                .toLocalDate();
        // Получаем сегодняшнюю дату в формате LocalDate
        LocalDate today = LocalDate.now();
        // Вычисляем разницу между localIssueDate и сегодняшней датой (в виде периодов: лет, месяцев, дней)
        Period period = Period.between(localIssueDate, today);
        profileResponseDTO.setExperience(period.getYears());
        return profileResponseDTO;
    }



}
