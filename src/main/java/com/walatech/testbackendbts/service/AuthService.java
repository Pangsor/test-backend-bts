package com.walatech.testbackendbts.service;

import com.walatech.testbackendbts.payload.LoginDto;
import com.walatech.testbackendbts.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
