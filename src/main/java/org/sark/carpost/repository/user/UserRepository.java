package org.sark.carpost.repository.user;

import org.sark.carpost.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Long> {
}
