package com.training.aem.core.bean;

import com.training.aem.core.services.UserGroupCheckService;
import lombok.Data;

@Data
public class UserGroupCheckEntity {
    private String currentUserId;
    private boolean isAuthorContentGroupMember;

    public UserGroupCheckEntity(){

    }

//    public UserGroupCheckEntity(String currentUserId, boolean isAuthorCntentGroupMember) {
//        this.currentUserId = currentUserId;
//        this.isAuthorCntentGroupMember = isAuthorCntentGroupMember;
//    }
//
//    public String getCurrentUserId() {
//        return currentUserId;
//    }
//
//    public void setCurrentUserId(String currentUserId) {
//        this.currentUserId = currentUserId;
//    }
//
//    public boolean isAuthorCntentGroupMember() {
//        return isAuthorCntentGroupMember;
//    }


}
