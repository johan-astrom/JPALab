package com.johanastrom.integration;

import com.johanastrom.users.User;

import java.util.List;

public interface UserDAO {


    /**
     * Persists a User object to the database.
     * @param user
     *        The User object to be persisted.
     * @return True if the User object was successfully persisted,
     *         false if something went wrong (probably by attempting to insert an already existing ssn)
     */
    boolean create(User user);

    /**
     * Finds one user based on the unique User ssn, or users ID column. If no user exists, returns null.
     * @param ssn
     *        The unique id for a User object.
     * @return The User object with the corresponding SSN.
     */
    User getOne(String ssn);

    /**
     * Returns all User objects with the matching search value.
     * The searchValue is case-insensitive and matches any substring of the {@param field} value.
     * If none are found, returns an empty list and prints a message.
     * @param field
     *        The User field, or users column, to query for the specified search value.
     * @param searchValue
     *        The value to be matched.
     * @return A list of all users corresponding to matching search value.
     */
    List<User> get(String field, String searchValue);

    /**
     * Returns all the User objects in the "users" table.
     * Returns an empty list and prints a message if the table is empty.
     * @return A List of all the users in the database.
     */
    List<User> getAll();

    /**
     * Updates the specified field of a User object. Requires the ssn, or users ID, to be specified.
     * Returns the old value, or null if no value was found.
     * @param ssn
     *        The unique id for a User object.
     * @param field
     *        The field, or database column, to be updated.
     * @param newValue
     *        The new value to be inserted.
     * @return
     *        The value of the specified field before update.
     */
    String update(String ssn, String field, String newValue);

    /**
     * Deletes the User object from the database.
     * @param ssn
     *        The unique id for a User object.
     * @return True if the User object was successfully removed.
     */
    boolean delete(String ssn);









}
