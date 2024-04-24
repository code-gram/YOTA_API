package com.yash.yotaapi.services.IServices;

import com.yash.yotaapi.dto.TechnologyDto;

import java.util.List;

public interface ITechnologyService {

    TechnologyDto addTechnology(TechnologyDto technology);

    TechnologyDto findTechnologyById(Long techId);

    List<TechnologyDto> fetchAllTechnology();

}
