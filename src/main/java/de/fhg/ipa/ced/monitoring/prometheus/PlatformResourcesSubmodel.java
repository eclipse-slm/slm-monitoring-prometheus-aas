package de.fhg.ipa.ced.monitoring.prometheus;

import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyType;
import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;

import java.util.Collections;
import java.util.Map;

public class PlatformResourcesSubmodel extends Submodel {

    public static final String OSID = "OS";
    public static final String PROARCID = "ProArc";
    public static final String MEMTOTALID = "SizOfTheRam";
    public static final String ALLOCATEDMEMORYID = "AllocatedMemory";
    public static final String ALLOCATEDMEMORYHISTOGRAMID = "AllocatedMemoryHistogramm";
    public static final String CPULOADID = "CPULoad";
    public static final String CPULOADHISTOGRAMID = "CPULoadHistogram";
    public static final String SUBMODELID = "PlatformResources";
    public static final Reference SEMANTICID = new Reference(Collections.singletonList(new Key(KeyElements.CONCEPTDESCRIPTION, false, "https://fabos.io/PlattformResources", KeyType.IRI)));

    public PlatformResourcesSubmodel(Identifier identifier) {
        this(SUBMODELID, identifier);
    }

    public PlatformResourcesSubmodel(String idShort, Identifier identifier) {
        super(idShort, identifier);
        setSemanticId(SEMANTICID);
    }

    public void setOS(Property os) {
        this.addSubmodelElement(os);
    }

    public void setOS(String os) {
        Property osProp = new Property(OSID, os);
        osProp.setSemanticId(new Reference(new Key(KeyElements.CONCEPTDESCRIPTION, false, "0173-1#02-AAP917#003", IdentifierType.IRDI)));
        setOS(osProp);
    }

    @SuppressWarnings("unchecked")
    public Property getOS() {
        return Property.createAsFacade((Map<String, Object>) getSubmodelElement(OSID));
    }

    public void setProArc(Property proArc) {
        this.addSubmodelElement(proArc);
    }

    public void setProArc(String proArc) {
        Property proArcProp = new Property(PROARCID, proArc);
        proArcProp.setSemanticId(new Reference(new Key(KeyElements.CONCEPTDESCRIPTION, false, "0173-1#02-BAB457#005", IdentifierType.IRDI)));
        setProArc(proArcProp);
    }

    @SuppressWarnings("unchecked")
    public Property getProArc() {
        return Property.createAsFacade((Map<String, Object>) getSubmodelElement(PROARCID));
    }

    public void setMemTotal(Property memTotal) {
        this.addSubmodelElement(memTotal);
    }

    public void setMemTotal(float memTotalKB) {
        Property memTotalKBProp = new Property(MEMTOTALID, memTotalKB);
        memTotalKBProp.setSemanticId(new Reference(new Key(KeyElements.CONCEPTDESCRIPTION, false, "0173-1#02-AAC856#008", IdentifierType.IRDI)));
        setMemTotal(memTotalKBProp);
    }

    @SuppressWarnings("unchecked")
    public Property getMemTotal() {
        return Property.createAsFacade((Map<String, Object>) getSubmodelElement(MEMTOTALID));
    }

    public void setAllocatedMemory(Property allocatedMemory) {
        this.addSubmodelElement(allocatedMemory);
    }

    public void setAllocatedMemory(Float allocatedMemory) {
        Property allocatedMemoryProp = new Property(ALLOCATEDMEMORYID, allocatedMemory);
        allocatedMemoryProp.setSemanticId(new Reference(new Key(KeyElements.CONCEPTDESCRIPTION, true, "https://example.com/ids/cd/4263_6190_2122_1110", IdentifierType.IRI)));
        setAllocatedMemory(allocatedMemoryProp);
    }

    @SuppressWarnings("unchecked")
    public Property getAllocatedMemoryHistogramm() {
        return Property.createAsFacade((Map<String, Object>) getSubmodelElement(ALLOCATEDMEMORYHISTOGRAMID));
    }

    public void setAllocatedMemoryHistogram(Property allocatedMemoryHistogram) {
        this.addSubmodelElement(allocatedMemoryHistogram);
    }

    public void setAllocatedMemoryHistogram(String JSONArray) {
        Property allocatedMemory = new Property(ALLOCATEDMEMORYHISTOGRAMID, JSONArray);
        allocatedMemory.setSemanticId(new Reference(new Key(KeyElements.CONCEPTDESCRIPTION, true, "http://iese.fraunhofer.de/prop_jsonarray", IdentifierType.IRI)));
        setAllocatedMemoryHistogram(allocatedMemory);
    }

    @SuppressWarnings("unchecked")
    public Property getAllocatedMemory() {
        return Property.createAsFacade((Map<String, Object>) getSubmodelElement(ALLOCATEDMEMORYID));
    }

    public void setCPULoad(Property cpuLoad) {
        this.addSubmodelElement(cpuLoad);
    }

    public void setCPULoad(float cpuLoad) {
        Property cpuLoadProp = new Property(CPULOADID, cpuLoad);
        cpuLoadProp.setSemanticId(new Reference(new Key(KeyElements.CONCEPTDESCRIPTION, true, "https://example.com/ids/cd/2224_6190_2122_7300", IdentifierType.IRI)));
        setCPULoad(cpuLoadProp);
    }

    @SuppressWarnings("unchecked")
    public Property getCPULoad() {
        return Property.createAsFacade((Map<String, Object>) getSubmodelElement(CPULOADID));
    }

    public void setCPULoadHistogram(Property cpuLoadHistogram) {
        this.addSubmodelElement(cpuLoadHistogram);
    }

    public void setCPULoadHistogram(String JSONArray) {
        Property cpuLoadHistogramProp = new Property(CPULOADHISTOGRAMID, JSONArray);
        cpuLoadHistogramProp.setSemanticId(new Reference(new Key(KeyElements.CONCEPTDESCRIPTION, true, "http://iese.fraunhofer.de/prop_jsonarray", IdentifierType.IRI)));
        setCPULoadHistogram(cpuLoadHistogramProp);
    }

    @SuppressWarnings("unchecked")
    public Property getCPULoadHistogram() {
        return Property.createAsFacade((Map<String, Object>) getSubmodelElement(CPULOADHISTOGRAMID));
    }
}
