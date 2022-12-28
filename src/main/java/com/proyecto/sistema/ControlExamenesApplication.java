package com.proyecto.sistema;

import com.proyecto.sistema.dto.RolDTO;
import com.proyecto.sistema.dto.UsuarioDTO;
import com.proyecto.sistema.exceptions.ResourceFoundException;
import com.proyecto.sistema.mapper.RolMapper;
import com.proyecto.sistema.mapper.UsuarioMapper;
import com.proyecto.sistema.model.Rol;
import com.proyecto.sistema.model.Usuario;
import com.proyecto.sistema.service.IRolService;
import com.proyecto.sistema.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControlExamenesApplication  implements CommandLineRunner {

	@Autowired
	private IRolService rolService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private UsuarioMapper usuarioMapper;

	public static void main(String[] args) {
		SpringApplication.run(ControlExamenesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			RolDTO rolAdmin = new RolDTO("ROLE_ADMIN");
			RolDTO rolProfesor = new RolDTO("ROLE_PROFESOR");
			RolDTO rolAlumno = new RolDTO("ROLE_ALUMNO");

			rolService.guardarRol(rolAdmin);
			rolService.guardarRol(rolProfesor);
			rolService.guardarRol(rolAlumno);

			UsuarioDTO usuarioAdmin = new UsuarioDTO();
			usuarioAdmin.setNombre("SoyAdmin");
			usuarioAdmin.setApellido("SoyAdmin");
			usuarioAdmin.setUsername("admin");
			usuarioAdmin.setPassword("admin");
			usuarioAdmin.setEmail("admin@gmail.com");
			usuarioAdmin.setTelefono("987654321");

			usuarioService.guardarUsuarioAdmin(usuarioAdmin);
		}catch (ResourceFoundException e){
			e.printStackTrace();
		}


	}
}
