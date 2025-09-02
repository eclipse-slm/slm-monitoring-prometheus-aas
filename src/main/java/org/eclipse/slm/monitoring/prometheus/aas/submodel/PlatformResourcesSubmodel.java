package org.eclipse.slm.monitoring.prometheus.aas.submodel;

import org.eclipse.digitaltwin.aas4j.v3.model.*;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultKey;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultProperty;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultReference;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSubmodel;

public class PlatformResourcesSubmodel extends DefaultSubmodel {

    public static final String OSID = "OS";
    public static final String PROARCID = "ProArc";
    public static final String MEMTOTALID = "SizOfTheRam";
    public static final String ALLOCATEDMEMORYID = "AllocatedMemory";
    public static final String ALLOCATEDMEMORYHISTOGRAMID = "AllocatedMemoryHistogramm";
    public static final String CPULOADID = "CPULoad";
    public static final String CPULOADHISTOGRAMID = "CPULoadHistogram";
    public static final String SUBMODEL_ID_SHORT = "PlatformResources";
    public static final Reference SEMANTIC_ID = new DefaultReference.Builder()
            .type(ReferenceTypes.EXTERNAL_REFERENCE)
            .keys(new DefaultKey.Builder()
                        .type(KeyTypes.SUBMODEL)
                        .value("https://eclipse.dev/slm/aas/sm/PlatformResources").build()
            ).build();

    public PlatformResourcesSubmodel(String id) {
        this.setId(id);
        this.setIdShort(SUBMODEL_ID_SHORT);
        this.setSemanticId(SEMANTIC_ID);
    }

    public void setOS(String os) {
        var osProp = new DefaultProperty.Builder()
                .idShort(OSID)
                .value(os)
                .build();
        osProp.setSemanticId(new DefaultReference.Builder().keys(new DefaultKey.Builder()
                                .type(KeyTypes.CONCEPT_DESCRIPTION)
                                .value("0173-1#02-AAP917#003").build()).build());

        this.submodelElements.add(osProp);
    }

    public void setProArc(String proArc) {
        var proArcProp = new DefaultProperty.Builder()
                .idShort(PROARCID)
                .value(proArc)
                .build();

        proArcProp.setSemanticId(new DefaultReference.Builder().keys(new DefaultKey.Builder()
                                    .type(KeyTypes.CONCEPT_DESCRIPTION)
                                    .value("0173-1#02-BAB457#005").build()).build());

        this.submodelElements.add(proArcProp);
    }

    public void setMemTotal(float memTotalKB) {
        var memTotalKBProp = new DefaultProperty.Builder()
                .idShort(MEMTOTALID)
                .value(String.valueOf(memTotalKB))
                .build();
        memTotalKBProp.setSemanticId(new DefaultReference.Builder().keys(new DefaultKey.Builder()
                                        .type(KeyTypes.CONCEPT_DESCRIPTION)
                                        .value("0173-1#02-AAC856#008").build()).build());

        this.submodelElements.add(memTotalKBProp);
    }

    public void setAllocatedMemory(float allocatedMemory) {
        var allocatedMemoryProp = new DefaultProperty.Builder()
                .idShort(ALLOCATEDMEMORYID)
                .value(String.valueOf(allocatedMemory))
                .build();
        allocatedMemoryProp.setSemanticId(new DefaultReference.Builder().keys(new DefaultKey.Builder()
                                            .type(KeyTypes.CONCEPT_DESCRIPTION)
                                            .value("https://example.com/ids/cd/4263_6190_2122_1110").build()).build());

        this.submodelElements.add(allocatedMemoryProp);
    }

    public void setAllocatedMemoryHistogram(String jsonArray) {
        var allocatedMemory = new DefaultProperty.Builder()
                .idShort(ALLOCATEDMEMORYHISTOGRAMID)
                .value(jsonArray)
                .build();
        allocatedMemory.setSemanticId(new DefaultReference.Builder()
                                        .type(ReferenceTypes.EXTERNAL_REFERENCE)
                                        .keys(new DefaultKey.Builder()
                                            .type(KeyTypes.PROPERTY)
                                            .value("http://iese.fraunhofer.de/prop_jsonarray").build()
                                        ).build());

        this.submodelElements.add(allocatedMemory);
    }

    public void setCPULoad(float cpuLoad) {
        var cpuLoadProp = new DefaultProperty.Builder()
                .idShort(CPULOADID)
                .value(String.valueOf(cpuLoad))
                .build();
        cpuLoadProp.setSemanticId(new DefaultReference.Builder().keys(new DefaultKey.Builder()
                                    .type(KeyTypes.CONCEPT_DESCRIPTION)
                                    .value("https://example.com/ids/cd/2224_6190_2122_7300").build()).build());

        this.submodelElements.add(cpuLoadProp);
    }

    public void setCPULoadHistogram(String jsonArray) {
        var cpuLoadHistogramProp = new DefaultProperty.Builder()
                .idShort(CPULOADHISTOGRAMID)
                .value(jsonArray)
                .build();
        cpuLoadHistogramProp.setSemanticId(new DefaultReference.Builder()
                                            .type(ReferenceTypes.EXTERNAL_REFERENCE)
                                            .keys(new DefaultKey.Builder()
                                                    .type(KeyTypes.PROPERTY)
                                                    .value("http://iese.fraunhofer.de/prop_jsonarray").build()
                                            ).build());

        this.submodelElements.add(cpuLoadHistogramProp);
    }
}
