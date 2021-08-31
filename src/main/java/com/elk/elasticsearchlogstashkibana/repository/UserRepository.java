package com.elk.elasticsearchlogstashkibana.repository;

import com.elk.elasticsearchlogstashkibana.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
