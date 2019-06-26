package com.example.entity;

public class Status {
    public enum StateCode {
        /**
         * 成功响应
         */
        SUCCESS(200),
        /**
         * 服务器理解请求客户端的请求，但是拒绝执行此请求
         */
        Forbidden(403),
        /**
         * 无法根据客户端的请求找到资源
         */
        NOT_FOUND(404),
        /**
         * 未知错误
         */
        DEFAULT(0000);
        private Integer code;

        StateCode(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }
}
