package SERVICES;

import USER.User;
import EXCEPTIONS.UserNotFoundException;
import UTILS.Logger;

import java.io.*;
import java.util.*;

public class UserRepository implements Repository<User> {
    private static final String DATA_DIRECTORY = "data/";
    private Map<String, User> userMap;

    public UserRepository() {
        this.userMap = new HashMap<>();
        loadAllUsers();
    }

    private void loadAllUsers() {
        File dir = new File(DATA_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File[] files = dir.listFiles((d, name) -> name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    User user = (User) ois.readObject();
                    userMap.put(user.getID(), user);
                    Logger.log("Loaded user from file: " + file.getName());
                } catch (IOException | ClassNotFoundException e) {
                    Logger.log("Error loading user from file: " + file.getName());
                }
            }
        }
    }

    private void saveUserToFile(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_DIRECTORY + user.getID() + ".ser"))) {
            oos.writeObject(user);
            Logger.log("Saved user to file: " + user.getID() + ".ser");
        } catch (IOException e) {
            Logger.log("Error saving user to file: " + user.getID());
        }
    }

    private void deleteUserFile(String id) {
        File file = new File(DATA_DIRECTORY + id + ".ser");
        if (file.exists()) {
            if (file.delete()) {
                Logger.log("Deleted user file: " + id + ".ser");
            } else {
                Logger.log("Error deleting user file: " + id + ".ser");
            }
        }
    }

    @Override
    public void add(User user) {
        userMap.put(user.getID(), user);
        saveUserToFile(user);
        Logger.log("Added user: " + user.getID());
    }

    @Override
    public boolean remove(String id) {
        if (!userMap.containsKey(id)) {
            return false;
        }
        userMap.remove(id);
        deleteUserFile(id);
        Logger.log("Removed user: " + id);
        return true;
    }

    @Override
    public User findByID(String id) throws UserNotFoundException {
        User user = userMap.get(id);
        if (user == null) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
        return user;
    }

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(userMap.values());
    }
}
