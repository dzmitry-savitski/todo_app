package by_.gsu.epamlab.todoapp.service;

import by_.gsu.epamlab.todoapp.entities.Task;

/**
 * Provides image-useful methods.
 */
public interface ImageService {
    /**
     * Receives image from db.
     * Image will be received only if this method called by user with given id
     * (user must be authenticated in AuthManager).
     *
     * @return image data or empty array
     */
    byte[] getTaskImage(int userId, int taskId);

    /**
     * Generates image-specified token.
     * Must be unique for every image.
     */
    int getNoCacheToken(Task task);
}
