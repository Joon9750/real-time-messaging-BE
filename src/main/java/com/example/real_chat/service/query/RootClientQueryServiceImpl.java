package com.example.real_chat.service.query;

import com.example.real_chat.entity.rootClient.RootClient;
import com.example.real_chat.repository.RootClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RootClientQueryServiceImpl implements RootClientQueryService {

    private final RootClientRepository rootClientRepository;

    @Override
    public RootClient getRootClient(Long id) {
        return rootClientRepository.findById(id).orElseThrow();
    }
}
