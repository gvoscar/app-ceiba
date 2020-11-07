package co.com.ceiba.mobile.pruebadeingreso.rest;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.pojos.Post;
import co.com.ceiba.mobile.pruebadeingreso.pojos.User;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Servicio REST de API publica JSONPlaceholder
 * https://jsonplaceholder.typicode.com/
 */
public interface JSONPlaceholderService {

    @GET(JSONPlaceholderEndpoint.GET_USERS)
    Call<List<User>> getUsers();

    /**
     * Obtener usuarios
     *
     * @return Lista de usuarios
     */
    @GET(JSONPlaceholderEndpoint.GET_USERS)
    Observable<List<User>> users();

    /**
     * Obtener usuario
     *
     * @param userId ID de usuario
     * @return Usuario
     */
    @GET(JSONPlaceholderEndpoint.GET_USER_BY_ID)
    Observable<User> usersById(@Path("userId") String userId);

    @GET(JSONPlaceholderEndpoint.GET_USER_BY_ID)
    Call<User> getUserById(@Path("userId") String userId);

    /**
     * Obtener todas las publicaciones
     *
     * @return Lista de publicaciones
     */
    @GET(JSONPlaceholderEndpoint.GET_POSTS)
    Observable<List<Post>> posts();

    /**
     * Obtener publicacion
     *
     * @param postId ID de publicacion
     * @return Publicacion
     */
    @GET(JSONPlaceholderEndpoint.GET_POST_BY_ID)
    Observable<Post> postById(@Path("postId") String postId);

    /**
     * Obtener todas las publicaciones del usuario
     *
     * @param userId ID de usuario
     * @return Lista de publicaciones del usuario
     */
    @GET(JSONPlaceholderEndpoint.GET_ALL_USER_POSTS)
    Observable<List<Post>> allUserPosts(@Path("userId") String userId);

    /**
     * Obtener publicaciones del usuario
     *
     * @param userId ID de usuario
     * @return Lista de publicaciones del usuario
     */
    @GET(JSONPlaceholderEndpoint.GET_POST_USER)
    Observable<List<Post>> postsUser(@Path("userId") String userId);
}
