/**
* @author rodrigo
*/

package edu.ifrs.tpack;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import edu.ifrs.tpack.service.Controller;

@ApplicationPath("/service")
/**
* Comentário sobre a classe
*/
public class TpackApplication extends Application {

    @Override
    /**
     * Comentário sobre o método
     */
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(Controller.class);
        resources.add(CORSFilter.class);
        return resources;
    }

}
