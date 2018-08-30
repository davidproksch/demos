package com.redhat.proksch.demo.warehouse;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
public class Warehouse extends AbstractVerticle {
    private String message() {
	String s = System.getenv("mysql-demo-user");
	String t = System.getenv("mysql_user");
	s = "<h1>Hello " + s + ":" + t + " from Warehouse!</h1>";
	return(s);
    }

    @Override
    public void start(Future<Void> fut) {
        vertx
            .createHttpServer()
            .requestHandler(r ->
                r.response()
                 .end(message()))
                 //.end(" <h1>Hello from Warehouse!</h1> "))
            .listen(8080, result -> {
                if (result.succeeded()) {
                    fut.complete();
                } else {
                    fut.fail(result.cause());
                }
            });
    }
}
