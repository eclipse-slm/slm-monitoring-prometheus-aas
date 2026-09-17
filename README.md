# Eclipse SLM - Prometheus AAS

Prometheus-based monitoring adapter providing an Asset Administration Shell (AAS) API for the Eclipse SLM project.

## Overview

This project exposes Prometheus monitoring metrics through an AAS (Asset Administration Shell) interface, enabling integration of Prometheus-collected metrics into Industry 4.0 environments using the AAS standard.

## Features

- Exposes Prometheus metrics via AAS Submodel API
- RESTful HTTP endpoints for metric retrieval
- Integration with Eclipse BaSyx components
- Support for platform resource monitoring

## Technology Stack

- **Java 18+**
- **Spring Boot 3.x**
- **Eclipse BaSyx** (Submodel Repository)
- **aas4j** for AAS model handling

## Build

```bash
mvn clean install
```

## Configuration

The application can be configured via `application.properties` or `application.yml`. Key configurations include:

- Server port
- BaSyx endpoint settings
- Consul service discovery

## License

SPDX-License-Identifier: MIT

Eclipse Public License - v 2.0: https://www.eclipse.org/legal/epl-2.0/
