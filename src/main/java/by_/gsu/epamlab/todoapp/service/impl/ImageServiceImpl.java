package by_.gsu.epamlab.todoapp.service.impl;

import by_.gsu.epamlab.todoapp.db.TaskDAO;
import by_.gsu.epamlab.todoapp.entities.Task;
import by_.gsu.epamlab.todoapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("imageService")
public class ImageServiceImpl implements ImageService {
    @Autowired
    TaskDAO taskDAO;

    @Override
    public byte[] getTaskImage(int userId, int taskId) {
        return taskDAO.getImage(userId, taskId);
    }

    @Override
    public int getNoCacheToken(Task task) {
        final byte[] image = task.getImage();
        return image == null ? 0 : image.length;
    }
}
