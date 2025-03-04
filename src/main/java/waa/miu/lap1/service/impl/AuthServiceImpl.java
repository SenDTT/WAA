package waa.miu.lap1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import waa.miu.lap1.entity.request.LoginRequest;
import waa.miu.lap1.entity.request.RefreshTokenRequest;
import waa.miu.lap1.entity.response.LoginResponse;
import waa.miu.lap1.service.AuthService;
import waa.miu.lap1.helper.JwtUtil;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());
        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
        return new LoginResponse(accessToken, refreshToken);
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        if (jwtUtil.validateToken(refreshTokenRequest.getRefreshToken()) && jwtUtil.isRefreshToken(refreshTokenRequest.getRefreshToken())) {
            if (jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken())) {
                final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
                return new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            } else {
                log.info("Refresh token expired");
                return new LoginResponse(refreshTokenRequest.getAccessToken(), refreshTokenRequest.getRefreshToken());
            }
        } else {
            log.warn("Refresh token expired");
            return new LoginResponse();
        }
    }
}
