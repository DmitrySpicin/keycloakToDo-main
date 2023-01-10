//package com.example.demo.service;
//
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.keycloak.admin.client.resource.UserResource;
//import org.keycloak.authentication.AuthenticationFlowContext;
//import org.keycloak.email.EmailException;
//import org.keycloak.email.EmailTemplateProvider;
//import org.keycloak.email.freemarker.FreeMarkerEmailTemplateProvider;
//import org.keycloak.events.Event;
//import org.keycloak.models.RealmModel;
//import org.keycloak.models.UserModel;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.keycloak.sessions.AuthenticationSessionModel;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//@Data
//@RequiredArgsConstructor
//public class FargotPassword implements SSOManager {
//
//
//
//    public boolean updatePassword(String userId, String password) {
//        UserResource user = ApiUtil.findUserByUsernameId(testRealm(), "john-doh@localhost");
//        List<CredentialRepresentation> creds = user.credentials();
//        CredentialRepresentation credPasswd = creds.get(0);
//
//        // Remove password
//        user.removeCredential(credPasswd.getId());
//
//        // Restore password
//        credPasswd.setValue("password");
//        user.resetPassword(credPasswd);
//    }
//
//}
