package com.platform.service;

import java.util.List;
import java.util.Map;

public interface ShiroService {

    List<Map<String,List>> getRoles(int uid);
}
