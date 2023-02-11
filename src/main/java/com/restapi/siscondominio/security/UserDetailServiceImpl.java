package com.restapi.siscondominio.security;

import com.restapi.siscondominio.control.persistence.entities.CtrUsuario;
import com.restapi.siscondominio.control.persistence.repositories.CtrUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private CtrUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String cedula) throws UsernameNotFoundException {
        CtrUsuario usuario= usuarioRepository.findOneByusuCedula(cedula)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario "+cedula+"no existe"));

        return new UserDetailsImpl(usuario);
    }
}
