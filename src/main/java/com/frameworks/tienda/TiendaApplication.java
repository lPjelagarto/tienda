package com.frameworks.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.frameworks.tienda.model.Rol;
import com.frameworks.tienda.model.Usuario;
import com.frameworks.tienda.repository.UsuarioRepository;

@SpringBootApplication
public class TiendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaApplication.class, args);
	}

	@Bean
	CommandLineRunner cargarUsuarios(UsuarioRepository repository, PasswordEncoder encoder) {
		return args -> {
			crearUsuario(repository, encoder, "admin", "admin123", Rol.ADMIN);
			crearUsuario(repository, encoder, "vendedor", "vend123", Rol.VENDEDOR);
			crearUsuario(repository, encoder, "cliente", "cli123", Rol.CLIENTE);
		};
	}

	private void crearUsuario(UsuarioRepository repository, PasswordEncoder encoder,
							  String username, String password, Rol rol) {
		if (repository.findByUsername(username).isEmpty()) {
			repository.save(new Usuario(username, encoder.encode(password), rol));
		}
	}

}
