package com.yash.yotaapi.util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class PaginationUtility {
	public static <T> Page<T> paginate(List<T> list, Integer pageNumber, Integer pageSize) {
		int start = (int) PageRequest.of(pageNumber - 1, pageSize).getOffset();
		int end = Math.min((start + PageRequest.of(pageNumber - 1, pageSize).getPageSize()), list.size());
		if (start > list.size()) {
			throw new IllegalArgumentException("Page number is greater than total pages.");
		}
		List<T> subList = list.subList(start, end);
		return new PageImpl<>(subList, PageRequest.of(pageNumber - 1, pageSize), list.size());
	}
}
