package br.com.compartilhai.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.compartilhai.model.Usuario;
import br.com.compartilhai.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional <Usuario> user = usuarioRepository.findByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException(email + " não encontrado."));
		
		return user.map(UserDetailsImpl :: new).get();
	}
}
