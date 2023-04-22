package lab_example_rest;

import lab_example_rest.resources.ExampleAPIResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author manos
 */
@javax.ws.rs.ApplicationPath("example")
public class exampleApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();
        set.add(new ExampleAPIResource());
        return set;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(lab_example_rest.resources.ExampleAPIResource.class);
    }
}
