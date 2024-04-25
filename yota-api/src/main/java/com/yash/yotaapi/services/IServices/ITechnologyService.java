package com.yash.yotaapi.services.IServices;

import java.util.List;

import com.yash.yotaapi.dto.TechnologyDto;
import com.yash.yotaapi.entity.Technology;

public interface ITechnologyService {

	public TechnologyDto addTechnology(String technology);

	public List<TechnologyDto> fetchAllTechnology();

}
