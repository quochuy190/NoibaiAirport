package neo.com.noibaiairport.Presenter;

import android.content.Context;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created ${29/5/2018}
 * @updated ${Date}
 * @modified by
 * @updated on ${Date}
 * @since 1.0
 */
public abstract class BasePresenter {
    protected Context context;

    public BasePresenter(Context context) {
        this.context = context;
    }
}
