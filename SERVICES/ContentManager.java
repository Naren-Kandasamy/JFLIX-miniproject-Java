package SERVICES;
import CONTENT.Content;
import EXCEPTIONS.*;

import java.util.*;
import java.util.stream.Collectors;

public class ContentManager {
    private final ContentRepository content_repository;

    ContentManager(ContentRepository content_repository){
        this.content_repository = content_repository;
    }

    public void add_content(Content content) throws DuplicateContentException{
        if(content_repository.exists(content.getID())){
            throw new DuplicateContentException("Content with id "+content.getID()+" already exists");
        }

        content_repository.add(content);
    }

    public void remove_content(String id) throws ContentNotFoundException{
        if(!content_repository.exists(id)){
            throw new ContentNotFoundException("Content with id "+id+" not found");
        }

        content_repository.remove(id);
    }

    public Content findByID(String id) throws ContentNotFoundException{
        Content c = content_repository.findByID(id);
        if(c == null){
            throw new ContentNotFoundException();
        }
        
        return c;
    }

    public List<Content> find_by_type(Class <? extends Content> type) throws ContentNotFoundException{
        List<Content> result = content_repository.getAll().stream()
        .filter(type::isInstance).
        collect(Collectors.toList());

        if(result.isEmpty()){
            throw new ContentNotFoundException("Content of type "+type+" not found");
        }

        return result;
    }

    public List<Content> getAllContent(){
        return content_repository.getAll();
    }
}