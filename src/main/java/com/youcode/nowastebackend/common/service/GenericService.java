package com.youcode.nowastebackend.common.service;

import com.youcode.nowastebackend.dto.PagedResponse;
import org.springframework.data.domain.Pageable;

public interface GenericService<RequestDTO, ResponseDTO, ID> {
    ResponseDTO save(RequestDTO requestDto);

    ResponseDTO update(ID id, RequestDTO requestDto);

    ResponseDTO findById(ID id);

    PagedResponse<ResponseDTO> findAll(Pageable pageable);

    void deleteById(ID id);
}
