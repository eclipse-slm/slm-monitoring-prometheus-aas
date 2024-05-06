package org.eclipse.slm.monitoring.prometheus.aas.api;

import org.eclipse.digitaltwin.aas4j.v3.model.*;
import org.eclipse.digitaltwin.basyx.core.pagination.CursorResult;
import org.eclipse.digitaltwin.basyx.core.pagination.PaginationInfo;
import org.eclipse.digitaltwin.basyx.http.pagination.Base64UrlEncodedCursor;
import org.eclipse.digitaltwin.basyx.http.pagination.PagedResult;
import org.eclipse.digitaltwin.basyx.http.pagination.PagedResultPagingMetadata;
import org.eclipse.digitaltwin.basyx.pagination.GetSubmodelElementsResult;
import org.eclipse.digitaltwin.basyx.submodelservice.value.SubmodelElementValue;
import org.eclipse.digitaltwin.basyx.submodelservice.value.SubmodelValueOnly;
import org.eclipse.slm.monitoring.prometheus.aas.submodel.PlatformResourcesSubmodelFactory;
import org.eclipse.slm.monitoring.prometheus.aas.exceptions.MethodNotImplementedException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-20T09:38:58.667119080Z[GMT]")
@RestController
public class MultiSubmodelServiceHTTPApiController implements MultiSubmodelServiceHTTPApi {

	private static final PaginationInfo NO_LIMIT_PAGINATION_INFO = new PaginationInfo(0, null);
	private PlatformResourcesSubmodelFactory submodelFactory;

	public MultiSubmodelServiceHTTPApiController(PlatformResourcesSubmodelFactory service) {
		this.submodelFactory = service;
	}

	@Override
	public ResponseEntity<Void> deleteSubmodelElementByPath(String resourceId, String idShortPath) {
		throw new MethodNotImplementedException();
	}

	@Override
	public ResponseEntity<PagedResult> getAllSubmodelElements(String resourceId, Integer limit, Base64UrlEncodedCursor cursor, String level, String extent) {
		if (limit == null) {
			limit = 100;
		}

		String decodedCursor = "";
		if (cursor != null) {
			decodedCursor = cursor.getDecodedCursor();
		}

		PaginationInfo pInfo = new PaginationInfo(limit, decodedCursor);
		CursorResult<List<SubmodelElement>> submodelElements = submodelFactory.getSubmodelService(resourceId).getSubmodelElements(pInfo);

		GetSubmodelElementsResult paginatedSubmodelElement = new GetSubmodelElementsResult();

		String encodedCursor = getEncodedCursorFromCursorResult(submodelElements);

		paginatedSubmodelElement.setResult(submodelElements.getResult());
		paginatedSubmodelElement.setPagingMetadata(new PagedResultPagingMetadata().cursor(encodedCursor));

		return new ResponseEntity<>(paginatedSubmodelElement, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Submodel> getSubmodel(String resourceId, String level, String extent) {
		Submodel submodel = submodelFactory.getSubmodelService(resourceId).getSubmodel();

		return new ResponseEntity<>(submodel, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SubmodelElement> getSubmodelElementByPath(String resourceId, String idShortPath, Integer limit,
																	Base64UrlEncodedCursor cursor, String level, String extent) {
		SubmodelElement submodelElement = submodelFactory.getSubmodelService(resourceId).getSubmodelElement(idShortPath);

		return new ResponseEntity<SubmodelElement>(submodelElement, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SubmodelElementValue> getSubmodelElementByPathValueOnly(String resourceId, String idShortPath,
																				  Integer limit, Base64UrlEncodedCursor cursor,
																				  String level, String extent) {
		SubmodelElementValue submodelElementValue = submodelFactory.getSubmodelService(resourceId).getSubmodelElementValue(idShortPath);

		return new ResponseEntity<>(submodelElementValue, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Submodel> getSubmodelMetadata(String resourceId, String level) {
		Submodel submodel = submodelFactory.getSubmodelService(resourceId).getSubmodel();
		submodel.setSubmodelElements(null);

		return new ResponseEntity<>(submodel, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SubmodelValueOnly> getSubmodelValueOnly(String resourceId, String level, String extent) {
		SubmodelValueOnly result = new SubmodelValueOnly(submodelFactory.getSubmodelService(resourceId).getSubmodelElements(NO_LIMIT_PAGINATION_INFO).getResult());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> patchSubmodelElementByPathValueOnly(String resourceId, String idShortPath,
																	SubmodelElementValue body, Integer limit,
																	Base64UrlEncodedCursor cursor, String level) {
		throw new MethodNotImplementedException();
	}

	@Override
	public ResponseEntity<SubmodelElement> postSubmodelElement(String resourceId, SubmodelElement body, String level) {
		throw new MethodNotImplementedException();
	}

	@Override
	public ResponseEntity<SubmodelElement> postSubmodelElementByPath(String resourceId, String idShortPath, SubmodelElement body) {
		throw new MethodNotImplementedException();
	}

	@Override
	public ResponseEntity<Void> putSubmodelElementByPath(String resourceId, String idShortPath, SubmodelElement body, String level) {
		throw new MethodNotImplementedException();
	}

	@Override
	public ResponseEntity<Void> patchSubmodelValueOnly(String resourceId, List<SubmodelElement> body, String level) {
		throw new MethodNotImplementedException();
	}

	@Override
	public ResponseEntity<OperationResult> invokeOperation(String resourceId, String idShortPath, OperationRequest body) {
		throw new MethodNotImplementedException();
	}

	@Override
	public ResponseEntity<Resource> getFileByPath(String resourceId, String idShortPath) {
		throw new MethodNotImplementedException();
	}

	@Override
	public ResponseEntity<Void> putFileByPath(String resourceId, String idShortPath, String fileName, MultipartFile file) {
		throw new MethodNotImplementedException();
	}

	@Override
	public ResponseEntity<Void> deleteFileByPath(String resourceId, String idShortPath) {
		throw new MethodNotImplementedException();
	}

	private String getEncodedCursorFromCursorResult(CursorResult<?> cursorResult) {
		if (cursorResult == null || cursorResult.getCursor() == null) {
			return null;
		}

		return Base64UrlEncodedCursor.encodeCursor(cursorResult.getCursor());
	}

}
