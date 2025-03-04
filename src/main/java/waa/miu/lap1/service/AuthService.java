package waa.miu.lap1.service;

import waa.miu.lap1.entity.request.LoginRequest;
import waa.miu.lap1.entity.request.RefreshTokenRequest;
import waa.miu.lap1.entity.response.LoginResponse;

public interface AuthService {
    LoginResponse authenticate(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
