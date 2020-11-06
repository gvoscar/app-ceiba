package co.com.ceiba.mobile.pruebadeingreso.features.loader.interactors;

import co.com.ceiba.mobile.pruebadeingreso.features.loader.repositories.LoaderRepository;
import co.com.ceiba.mobile.pruebadeingreso.features.loader.repositories.LoaderRepositoryImpl;

public class LoaderInteractorImpl implements LoaderInteractor {

    private static final String TAG = LoaderInteractorImpl.class.getSimpleName();
    private LoaderRepository mRepository;

    public LoaderInteractorImpl() {
        mRepository = new LoaderRepositoryImpl();
    }

    @Override
    public void checkDataRealm() {
        mRepository.checkDataRealm();
    }
}
