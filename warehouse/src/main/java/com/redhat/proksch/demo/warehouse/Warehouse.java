package com.redhat.proksch.demo.warehouse;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
public class Warehouse extends AbstractVerticle {
    private String message() {
	String t = System.getenv("mysql_user");
	s = "<h1>Hello " + s + ":" + t + " from Warehouse!</h1>";
	return(s);
    }

    @Override
    public void start(Future<Void> fut) {
	/*
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
	*/
Vertx vertx = Vertx.vertx();

    Router router = Router.router(vertx);
    router.get("/").handler(rc -> rc.response().end("Hello"));
    router.get("/:name").handler(rc -> rc.response().end("Hello " + rc.pathParam("name")));

    vertx.createHttpServer()
      .requestHandler(router::accept)
      .listen(8080);
  }
    }
}
