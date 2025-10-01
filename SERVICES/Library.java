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

    // ✅ Add new content
    public void addContent(Content content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        contentRepository.add(content);
    }

    // ✅ Remove content by ID
    public boolean removeContent(String contentId) {
        return contentRepository.remove(contentId);
    }

    // ✅ Get all content
    public List<Content> getAllContent() {
        return contentRepository.getAll();
    }

    // ✅ Search by title (case-insensitive)
    public List<Content> searchByTitle(String title) {
        return contentRepository.getAll().stream()
                .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    // ✅ Search by genre
    public List<Content> searchByGenre(Genre genre) {
        return contentRepository.getAll().stream()
                .filter(c -> c.getGenre() == genre)
                .collect(Collectors.toList());
    }

    // ✅ Find by ID
    public Content findById(String contentId) throws ContentNotFoundException{
        return contentRepository.findByID(contentId);
    }
}
