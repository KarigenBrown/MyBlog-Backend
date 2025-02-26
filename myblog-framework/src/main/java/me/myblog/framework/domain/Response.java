package me.myblog.framework.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.myblog.framework.enums.ResponseStatusEnum;

@Data
// @Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Response<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Response<T> ok(T data) {
        /* return new Response<T>()
                .setCode(ResponseStatusEnum.SUCCESS.getCode())
                .setMessage(ResponseStatusEnum.SUCCESS.getMessage())
                .setData(data); */

        Response<T> response = new Response<>();
        response.setCode(ResponseStatusEnum.SUCCESS.getCode());
        response.setMsg(ResponseStatusEnum.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }

    public static Response<Object> ok() {
        return Response.ok(null);
    }

    public static Response<String> error(ResponseStatusEnum responseStatusEnum) {
        /* return new Response<>()
                .setCode(responseStatusEnum.getCode())
                .setMessage(responseStatusEnum.getMessage()); */

        return error(responseStatusEnum.getCode(), responseStatusEnum.getMessage());
    }

    public static Response<String> error(Integer code, String message) {
        Response<String> response = new Response<>();
        response.setCode(code);
        response.setMsg(message);
        return response;
    }
}
