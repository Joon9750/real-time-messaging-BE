package com.example.real_chat.service.query;

import com.example.real_chat.entity.rootclient.RootClient;
import com.example.real_chat.repository.RootClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RootClientQueryServiceImpl implements RootClientQueryService {

    private final RootClientRepository rootClientRepository;

    @Override
    public RootClient getRootClient(Long id) {
        return rootClientRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Root client not found with id : " + id)
        );
    }
}
