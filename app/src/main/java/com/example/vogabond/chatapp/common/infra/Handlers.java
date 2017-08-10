package commoon.ui.viewpager.infra;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;

import java.util.HashMap;


/**
 * Created by yang。先森 on 2017/8/9 0009.
 */

public final class Handlers {
    public static final String DEFAULT_TAG="Defaut";

    private static Handler instance;

    public static synchronized Handler sharedInstance(){
        if (instance == null){
            instance = new Handler();
        }
        return instance;
    }
    public static Handler shaerdHandle;


    public static final Handler shaerdHandle(Context context) {

        if (shaerdHandle ==null){
            shaerdHandle = new Handler(context.getMainLooper());
        }
        return shaerdHandle;
    }

    public static final Handler newHandler(Context context){
        return new Handler(context.getMainLooper());
    }
    private Handlers(){

    }
    public final Handler newHandler(){
        return newHandler(DEFAULT_TAG);
    }

    public final Handler newHandler(String tag) {
        return new Handler(getHandlerThread(tag).getLooper());
    }
    private final HashMap<String,HandlerThread> threads = new HashMap<String, HandlerThread>();

    private HandlerThread getHandlerThread(String tag) {
        HandlerThread handlerThread = null;

        synchronized (threads){
            handlerThread = threads.get(tag);

            if (handlerThread == null){
                handlerThread = new HandlerThread(nameOfTag(tag));

                handlerThread.start();

                threads.put(tag,handlerThread);
            }
        }

        return handlerThread;
    };

    private final static String nameOfTag(String tag){
        return "HT-" + (TextUtils.isEmpty(tag) ? DEFAULT_TAG :tag) ;
    }

}
