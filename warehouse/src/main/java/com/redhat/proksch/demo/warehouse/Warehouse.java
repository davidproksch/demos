package com.redhat.proksch.demo.warehouse;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
public class Warehouse extends AbstractVerticle {

    private static String foo;
    private static void Warehouse() {
	foo = "bar";
    }
    private String message() {
	String t = System.getenv("mysql_user");
	String s = "<h1>Hello " + foo + " from Warehouse!</h1>";
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
    	router.get("/all/").handler(rc -> rc.response().end(message()));
    	router.get("/:name").handler(rc -> rc.response().end("Hello " + rc.pathParam("name")));

    vertx.createHttpServer()
      .requestHandler(router::accept)
      .listen(8080);
  }
}
