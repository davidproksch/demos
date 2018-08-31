package com.redhat.proksch.demo.warehouseaccess;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * A simple Camel REST DSL route that implements the greetings service.
 * 
 */
@Component
public class CamelRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        // @formatter:off
        restConfiguration()
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Warehouse Access REST API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .apiProperty("base.path", "camel/")
                .apiProperty("api.path", "/")
                .apiProperty("host", "")
//                .apiProperty("schemes", "")
                .apiContextRouteId("doc-api")
            .component("servlet")
            .bindingMode(RestBindingMode.json);
        
//
	rest("/warehouses/all").description("Get all Warehouses")
	    .get()
	    .to("direct:getWarehouses");

	from("direct:getWarehouses")
            .to("log:com.redhat.proksch?level=DEBUG")
	    .to("jetty://http://warehouse-00-mbodemo.6923.rh-us-east-1.openshiftapps.com/all?bridgeEndpoint=true")
	    .removeHeaders("CamelHttp*")
	    .to("direct:processWeather");
//
        rest("/hello/").description("Greetings to {name}")
            .get("/{name}").outType(Greetings.class)
                .route().routeId("greeting-api")
                .to("direct:processWeather");

        from("direct:processWeather").description("Take the list of warehouses and get weather information for each one")
            .streamCaching()
            .to("bean:warehousesService?method=getWarehouses");     


        from("direct:greetingsImpl").description("Greetings REST service implementation route")
            .streamCaching()
            .to("bean:greetingsService?method=getGreetings");     
        // @formatter:on
    }

}
