package de.fhg.ipa.ced.monitoring.prometheus;

import org.eclipse.basyx.submodel.restapi.SubmodelProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RestController
@RequestMapping("/shells")
public class AASRestController {
    private final PlatformResourcesSubmodelFactory submodelFactory;

    AASRestController(
            @Autowired PlatformResourcesSubmodelFactory submodelFactory) {
        this.submodelFactory = submodelFactory;
    }

    @RequestMapping(value = "/{id}/aas/submodels/PlatformResources/**", method = RequestMethod.GET)
    public ResponseEntity<Object> platformResourcesSubmodelEndpoint(
            @PathVariable(name = "id") String id,
            HttpServletRequest request) {
        var path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        var restOfPath = path.replace("/shells/" + id + "/aas/submodels/PlatformResources", "");
        if (!restOfPath.startsWith("/")) {
            restOfPath = "/" + restOfPath;
        }
        if (!restOfPath.startsWith("/submodel")) {
            restOfPath = "/submodel" + restOfPath;
        }
        var submodel = submodelFactory.createSubmodel(id);
        var submodelProvider = new SubmodelProvider(submodel);

        return ResponseEntity.ok(submodelProvider.getValue(restOfPath));

    }

    @RequestMapping(value = "/{id}/aas/submodels", method = RequestMethod.GET)
    public ResponseEntity<Object> submodelsEndpoint(
            @PathVariable(name = "id") String id) {
        var submodel = submodelFactory.createSubmodel(id);

        return ResponseEntity.ok(Collections.singleton(submodel));

    }
}
