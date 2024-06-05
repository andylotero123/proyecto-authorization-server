package com.cursos.api.authorizationserver.service;

import com.cursos.api.authorizationserver.exception.ObjectNotFoundException;
import com.cursos.api.authorizationserver.mapper.ClientAppMapper;
import com.cursos.api.authorizationserver.persistence.entity.security.ClientApp;
import com.cursos.api.authorizationserver.persistence.repository.security.ClientAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisteredClientService implements RegisteredClientRepository {

    @Autowired
    private ClientAppRepository clientAppRepository;

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        ClientApp clientApp = clientAppRepository.findByClientId(id)
                .orElseThrow(() -> new ObjectNotFoundException("Client not found"));
        return ClientAppMapper.toRegisteredClient(clientApp);
        //luego para mapear la entidad AppClient con la clase RegisteredClient se llama
        // al Mapper ClentAppMapper con el método toRegisteredClientId y le paso el objeto
        // clientApp
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return this.findById(clientId);
    }
}
