package com.training.aem.core.services;

import java.util.List;

public interface AuthorGroupService {
    List<String> isUserInGroup(String userName, String groupName);
}
