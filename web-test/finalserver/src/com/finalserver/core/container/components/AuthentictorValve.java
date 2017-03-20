package com.finalserver.core.container.components;

import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

/**
 * Created by Administrator on 2017-03-20.
 */
public class AuthentictorValve implements Valve {
    Realm realm=null;

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void invoke(Request request, Response response, ValveContext valveContext) {
        if (true){

        }else{
            valveContext.involveNext(request,response);
        }
    }
}
