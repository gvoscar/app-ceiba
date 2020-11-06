package co.com.ceiba.mobile.pruebadeingreso.features.users.interactors;

import co.com.ceiba.mobile.pruebadeingreso.features.users.repositories.UsersRepository;
import co.com.ceiba.mobile.pruebadeingreso.features.users.repositories.UsersRepositoryImpl;

public class UsersInteractorImpl implements UsersInteractor {

    private UsersRepository repository;

    public UsersInteractorImpl(UsersRepository repository) {
        // repository = new UsersRepositoryImpl();
        this.repository = repository;
    }

    @Override
    public void getData() {
        this.repository.getData();
    }
}
