package com.yash.yotaapi.services.IServices;

import com.yash.yotaapi.dto.TechnologyDto;

import java.util.List;

public interface ITechnologyService {

    TechnologyDto addTechnology(TechnologyDto technology);

    List<TechnologyDto> fetchAllTechnology();

}
