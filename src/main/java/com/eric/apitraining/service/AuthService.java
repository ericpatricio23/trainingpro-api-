package com.eric.apitraining.service;

import com.eric.apitraining.dto.auth.AuthResponseDTO;
import com.eric.apitraining.dto.auth.LoginRequestDTO;
import com.eric.apitraining.dto.auth.RegisterRequestDTO;
import com.eric.apitraining.entity.Usuario;
import com.eric.apitraining.repository.UsuarioRepository;
import com.eric.apitraining.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository repository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    // CADASTRO ======

    public AuthResponseDTO register(RegisterRequestDTO dto) {

        if (repository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));

        repository.save(usuario);

        String token = jwtService.gerarToken(usuario);

        return new AuthResponseDTO(token, usuario.getNome(), usuario.getEmail());
    }


    // LOGIN =====
    public AuthResponseDTO login(LoginRequestDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
        );

        Usuario usuario = repository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String token = jwtService.gerarToken(usuario);

        return new AuthResponseDTO(token, usuario.getNome(), usuario.getEmail());
    }
}