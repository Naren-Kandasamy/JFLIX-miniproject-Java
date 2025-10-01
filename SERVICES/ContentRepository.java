package SERVICES;

import CONTENT.Content;
import EXCEPTIONS.ContentNotFoundException;

import java.util.*;

public class ContentRepository implements Repository<Content>{
    private Map<String,Content> contentMap;

    public ContentRepository(){
        this.contentMap = new HashMap<>();
    }

    @Override
    public void add(Content content){
        contentMap.put(content.getID(),content); // Adds the content to content mapping 
    }

    @Override   
    public boolean remove(String id) {
        if(!contentMap.containsKey(id)){
            return false;  // ✅ Content not found, return false
        }
        contentMap.remove(id);
        return true;  // ✅ Successfully removed, return true
    }

    @Override
    public Content findByID(String id) throws ContentNotFoundException{
        Content c = contentMap.get(id);
        if(c == null){
            throw new ContentNotFoundException();
        }

        return c;
    }

    @Override
    public boolean exists(String id){
        return contentMap.containsKey(id);
    }
    
    @Override
    public List<Content> getAll() {
        return new ArrayList<>(contentMap.values());
    }
}