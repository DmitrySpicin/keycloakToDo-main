package com.example.demo.service;

import com.example.demo.custom.RegisterUser;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.expression.spel.ast.Assign;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

public class KeycloakChangeRoleService {

    public void changeRole(UUID userUuid) {

        String serverUrl = "http://localhost:8080/";
        String realm = "master";
        // idm-client needs to allow "Direct Access Grants: Resource Owner Password Credentials Grant"
        String clientId = "login-app";
        String clientSecret = "WZ9AiOgQXOyUTXF6WGKrsF5ZAekxjrGm";

        // User "idm-admin" needs at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"
        Keycloak keycloak = KeycloakBuilder.builder() //
                .serverUrl(serverUrl) //
                .realm(realm) //
                .grantType(OAuth2Constants.PASSWORD) //
                .clientId("admin-cli") //
                .clientSecret(clientSecret) //
                .username("admin") //
                .password("admin") //
                .build();

        // Define user
//        UserRepresentation user = new UserRepresentation();
//        user.setEnabled(true);
//        user.setUsername(users.getUsername());
//        user.setFirstName(users.getFirstName());
//        user.setLastName(users.getLastName());
//        user.setEmail(users.getEmail());
//        user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

        // Get realm
        RealmResource realmResource = keycloak.realm(realm);
//        UsersResource usersRessource = (UsersResource) realmResource.users().get("363f85b7-ea44-4592-be1f-80f8b34a3ffc");
        UserResource userResource = realmResource.users().get(String.valueOf(userUuid));

        // Create user (requires manage-users role)
//        Response response = usersRessource.create(user);
//        System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
//        System.out.println(response.getLocation());
//        String userId = CreatedResponseUtil.getCreatedId(response);
//
//        System.out.printf("User created with userId: %s%n", userId);
//
//        // Define password credential
//        CredentialRepresentation passwordCred = new CredentialRepresentation();
//        passwordCred.setTemporary(false);
//        passwordCred.setType(CredentialRepresentation.PASSWORD);
//        passwordCred.setValue("test");
//
//        UserResource userResource = usersRessource.get(userId);
//
//        // Set password credential
//        userResource.resetPassword(passwordCred);

////        // Get realm role "tester" (requires view-realm role)
        RoleRepresentation verifiedRealmRole = realmResource.roles()//
                .get("USER_VERIFIED").toRepresentation();
        RoleRepresentation userRealmRole = realmResource.roles()
                        .get("user").toRepresentation();
////        // Assign realm role tester to user
        userResource.roles().realmLevel() //
                .add(Arrays.asList(verifiedRealmRole));
        userResource.roles().realmLevel()
                .remove(Arrays.asList(userRealmRole));
////        // Get client
//        ClientRepresentation app1Client = realmResource.clients() //
        userResource.roles().clientLevel("");
//                .findByClientId("login-app").get(0);
//        app1Client.getRegistrationAccessToken();
//
//        // Get client level role (requires view-clients role)
//        RoleRepresentation userClientRole = realmResource.clients().get("363f85b7-ea44-4592-be1f-80f8b34a3ffc")
//                .roles().get("user").toRepresentation();
//
//        Assign client level role to user
//        userResource.roles()
//                .clientLevel("363f85b7-ea44-4592-be1f-80f8b34a3ffc").add(Arrays.asList(userClientRole));

        // Send password reset E-Mail
        // VERIFY_EMAIL, UPDATE_PROFILE, CONFIGURE_TOTP, UPDATE_PASSWORD, TERMS_AND_CONDITIONS
//        usersRessource.get(userId).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));

        // Delete User
//        userResource.remove();
    }
}
