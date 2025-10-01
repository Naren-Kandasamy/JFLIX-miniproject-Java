package SERVICES;

import CONTENT.Content;
import CONTENT.Genre;
// import .ContentRepository;
import EXCEPTIONS.ContentNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private final ContentRepository contentRepository;

    public Library(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public void addContent(Content content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        contentRepository.add(content);
    }

    public boolean removeContent(String contentId) {
        return contentRepository.remove(contentId);
    }

    public List<Content> getAllContent() {
        return contentRepository.getAll();
    }

    public List<Content> searchByTitle(String title) {
        return contentRepository.getAll().stream()
                .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Content> searchByGenre(Genre genre) {
        return contentRepository.getAll().stream()
                .filter(c -> c.getGenre() == genre)
                .collect(Collectors.toList());
    }
    
    public Content findById(String contentId) throws ContentNotFoundException{
        return contentRepository.findByID(contentId);
    }
}
