package am.azaryan.car.service;

import am.azaryan.car.dto.AuthRequest;
import am.azaryan.car.dto.AuthResponse;

public interface AuthService {

    AuthResponse register(AuthRequest authRequest);

    AuthResponse login(AuthRequest authRequest);
}

