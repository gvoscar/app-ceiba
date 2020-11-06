package co.com.ceiba.mobile.pruebadeingreso.rest;

/**
 * Configuracion de RUTAS de punto final.
 */
public class JSONPlaceholderEndpoint {
    public static final String URL_BASE = "https://jsonplaceholder.typicode.com/";

    public static final String GET_USERS = "users";
    public static final String GET_USER_BY_ID = "users/{userId}";

    public static final String GET_POSTS = "posts";
    public static final String GET_POST_BY_ID = "posts/{postId}";

    public static final String GET_POST_USER = "users/{userId}/posts";
    public static final String GET_ALL_USER_POSTS = "users/{userId}/todos";
}
