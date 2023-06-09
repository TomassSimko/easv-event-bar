package com.event_bar_easv.gui.models.user;


import com.event_bar_easv.be.user.AppUser;
import com.event_bar_easv.bll.services.interfaces.IUserService;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel implements IUserModel{

    private final IUserService userService;

    private ObservableList<AppUser> allUsersObservableList;


    @Inject
    public UserModel(IUserService userService) {
        this.userService = userService;
        this.allUsersObservableList = getAllUsers();
    }

    @Override
    public ObservableList<AppUser> getAllUsers() {
        allUsersObservableList = FXCollections.observableArrayList(userService.getAllUsers());
        return allUsersObservableList;
    }

    @Override
    public AppUser createUser(AppUser movie) {
        return null;
    }

    @Override
    public AppUser getUserByEmail(String customerName) {
        return allUsersObservableList
                .stream()
                .filter(appUser -> appUser.getEmail().equals(customerName))
                .findFirst().orElse(null);
    }

    @Override
    public int createCustomerService(AppUser appUser) {
        return userService.createCustomer(appUser);
    }
}
