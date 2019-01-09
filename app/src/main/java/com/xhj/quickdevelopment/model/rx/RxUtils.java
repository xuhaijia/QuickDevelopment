package com.xhj.quickdevelopment.model.rx;



import com.xhj.quickdevelopment.entity.BaseEntity;
import com.xhj.quickdevelopment.entity.HttpResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.logging.Logger;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**

 * @Description:Rx处理
 */
public class RxUtils {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程 统一处理线程
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }, BackpressureStrategy.BUFFER);
    }


    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<List<T>> createData(final List<T> t) {
        return Flowable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<HttpResponse<T>, T> handleResult() {
        return httpResponseFlowable ->
                httpResponseFlowable.flatMap((Function<HttpResponse<T>, Flowable<T>>) httpResponse -> {
                    if (httpResponse.getCode().equals("0000")) {
                        if (httpResponse.getResult() != null) {
                            return createData(httpResponse.getResult());
                        } else {
                            return createData((T) new BaseEntity());
                        }
//						return Flowable.error(new ApiException(httpResponse.getCode(), httpResponse.getMessage()));
                    } else {
                        if ("0003".equals(httpResponse.getCode())) {
                        }
                        return Flowable.error(new ApiException(httpResponse.getCode(), httpResponse.getMessage()));
                    }
                });
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<HttpResponse<T>, T> handleResultStep() {
        return httpResponseFlowable ->
                httpResponseFlowable.flatMap((Function<HttpResponse<T>, Flowable<T>>) httpResponse -> {
                    if (httpResponse.getCode().equals("0000")) {
                        if (httpResponse.getResult() != null) {
                            return createData(httpResponse.getResult());
                        } else {
                            return createData((T) new BaseEntity());
                        }
                    } else {
                        return Flowable.error(new ApiException(httpResponse.getCode(), httpResponse.getMessage()));
                    }
                });
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<HttpResponse<List<T>>, List<T>> handleListResult() {
        return httpResponseFlowable ->
                httpResponseFlowable.flatMap((Function<HttpResponse<List<T>>, Flowable<List<T>>>) httpResponse -> {
                    if (httpResponse.getCode().equals("0000")) {
                        if (httpResponse.getResult() != null) {
                            return createData(httpResponse.getResult());
                        }
                        return Flowable.error(new ApiException(httpResponse.getCode(), httpResponse.getMessage()));
                    } else {
                        return Flowable.error(new ApiException(httpResponse.getCode(), httpResponse.getMessage()));
                    }
                });
    }
}
