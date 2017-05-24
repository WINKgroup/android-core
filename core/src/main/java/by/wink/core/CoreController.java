package by.wink.core;

import by.wink.core.rest.CoreRest;
import by.wink.core.uielements.VolleyUi;

/**
 * Created by zoid23 on 23/05/17.
 */

public abstract class CoreController<R extends CoreRest> {

    private VolleyUi view;
    protected R rest;

    public CoreController (VolleyUi view, Class<R> restClass){
        this.view = view;
        try {
            this.rest = restClass.getConstructor(VolleyUi.class).newInstance(view);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("invalid CoreRest.class passed to a CoreController, please use by.wink.core.rest.CoreRest class");
        }
    }


    public interface ParsedResponse<T> {
        void onSuccess(T t);
        void onError(String error);
    }
}
