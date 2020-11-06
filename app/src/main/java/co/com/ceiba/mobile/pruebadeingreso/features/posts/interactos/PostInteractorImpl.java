package co.com.ceiba.mobile.pruebadeingreso.features.posts.interactos;

import co.com.ceiba.mobile.pruebadeingreso.features.posts.repositories.PostRepository;
import co.com.ceiba.mobile.pruebadeingreso.features.posts.repositories.PostRepositoryImpl;

public class PostInteractorImpl implements PostInteractor {

    private PostRepository repository;

    public PostInteractorImpl() {
        this.repository = new PostRepositoryImpl();
    }

    @Override
    public void getAllUserPosts(String userId) {
        this.repository.getAllUserPosts(userId);
    }


}
