package io.github.aguglia.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.aguglia.mapper.SharedUserMapper;
import io.github.aguglia.service.TaskSharedService;

@Service
public class TaskSharedServiceImpl implements TaskSharedService {

	@Autowired
    private SharedUserMapper sharedUserMapper;
	
	@Override
	public List<String> ShareUser(String TaskID) {
		List<String> IDList = sharedUserMapper.SharedUserID(TaskID);
		List<String> NameList = new ArrayList<String>();
		for(String ID : IDList) {
			NameList.add(sharedUserMapper.SharedUserName(ID));
		}
		return NameList;
	}

}
