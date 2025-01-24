package com.example.real_chat.service.command;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.global.exception.RootClientAlreadyDeletedException;
import com.example.real_chat.repository.RootClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RootClientCommandServiceImpl implements RootClientCommandService {

    private final RootClientRepository rootClientRepository;

    @Override
    public Long addRootClient(RootClient rootClient) {
        rootClientRepository.save(rootClient);
        return rootClient.getId();
    }

    @Override
    public void deleteRootClient(Long id) {
        RootClient client = getRootClientOrThrow(id);
        if (client.isDeleted()) throw new RootClientAlreadyDeletedException("ID " + id + "의 RootClient는 이미 삭제되었습니다.");
        else client.delete();
    }

    @Override
    public void updateRootClient(Long rootClientId, String id, String password, String name) {
        RootClient rootClient = getRootClientOrThrow(rootClientId);

        id = getUpdatedValue(id, rootClient.getClientId());
        password = getUpdatedValue(password, rootClient.getClientPassword());
        name = getUpdatedValue(name, rootClient.getClientName());

        rootClient.update(id, password, name);
    }

    private RootClient getRootClientOrThrow(Long id) {
        return rootClientRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Root Client not found with id : " + id)
    ) ;
}

    private String getUpdatedValue(String newValue, String currentValue) {
        return Optional.ofNullable(newValue)
                .filter(s -> !s.isBlank())
                .orElse(currentValue);
    }
}
