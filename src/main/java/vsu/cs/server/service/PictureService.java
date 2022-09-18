package vsu.cs.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.cs.server.model.Picture;
import vsu.cs.server.model.User;
import vsu.cs.server.repository.PictureRepository;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public void add(Picture value) {
        this.pictureRepository.save(value);
    }

    public void remove(Picture value) {
        this.pictureRepository.delete(value);
    }

    public void removeByUser(User user) {
        for (Picture p : getAll()) {
            if (p.getCreator().equals(user)) {
                pictureRepository.delete(p);
                String path = "user-photos/" + p.getCreator().getId() + "/" + p.getUrl();
                new File(path).delete();
            }
        }
    }

    public List<Picture> getAll() {
        List<Picture> list = this.pictureRepository.findAll();
        list.sort(Comparator.comparing(Picture::getCreationDate));
        Collections.reverse(list);
        return list;
    }

    public List<Picture> getAllPublic() {
        List<Picture> list = new LinkedList<>();
        for (Picture p : getAll()) {
            if (p.getPublic()) {
                list.add(p);
            }
        }
        return list;
    }

    public List<Picture> getByRole(boolean isUser, boolean isAdmin, int v) {
        if (isAdmin) {
            return getAll();
        }
        if (isUser) {
            return getAllPublic();
        }
        return getLast(v);
    }

    public List<Picture> getByUser(Long id) {
        List<Picture> list = new LinkedList<>();
        for (Picture p : getAll()) {
            if (p.getCreator().getId().equals(id)) {
                list.add(p);
            }
        }
        return list;
    }

    public List<Picture> getByPicture(Long id) {
        Picture picture = getById(id);
        List<Picture> list = new LinkedList<>();
        for (Picture p : getAllPublic()) {
            if (p.getSource() != null && p.getSource().equals(picture)) {
                list.add(p);
            }
        }
        return list;
    }

    public List<Picture> getLast(int v) {
        List<Picture> list = getAllPublic();
        if (v > list.size()) {
            return list;
        }
        return list.subList(list.size() - v, list.size());
    }

    public Picture getById(Long id) {
        return this.pictureRepository.getById(id);
    }


}
