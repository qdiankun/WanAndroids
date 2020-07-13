package com.me.slone.wan.network.response;

import com.me.slone.wan.network.exception.ApiException;
import com.me.slone.wan.network.exception.CustomException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * Author：diankun
 * Time：20-7-13 下午4:04
 * Description: 对返回的数据进行处理，区分异常的情况。
 */
public class ResponseTransformer {

    public static <T> ObservableTransformer<Response<T>, T> handleResult() {
        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction<>())
                .flatMap(new ResponseFunction<>());
    }


    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param <T>
     */
    private static class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends Response<T>>> {

        @Override
        public ObservableSource<? extends Response<T>> apply(Throwable throwable) throws Exception {
            return Observable.error(CustomException.handleException(throwable));
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param <T>
     */
    private static class ResponseFunction<T> implements Function<Response<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(Response<T> tResponse) throws Exception {
            int code = tResponse.getErrorCode();
            String message = tResponse.getErrorMsg();
            if (code == ResponseCode.OK_CODE) {
                return Observable.just(tResponse.getData());
            } else {
                return Observable.error(new ApiException(code, message));
            }
        }
    }
}
