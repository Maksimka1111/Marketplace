package com.example.AuthService.models;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;


public enum Role {
    USER, SELLER, ADMIN
}
