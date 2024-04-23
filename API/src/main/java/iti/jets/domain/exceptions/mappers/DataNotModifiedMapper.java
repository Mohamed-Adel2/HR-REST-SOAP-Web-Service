package iti.jets.domain.exceptions.mappers;

import iti.jets.domain.exceptions.models.DataNotCreatedException;
import iti.jets.domain.exceptions.models.DataNotModifiedException;
import iti.jets.domain.exceptions.models.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DataNotModifiedMapper implements ExceptionMapper<DataNotModifiedException> {
    @Override
    public Response toResponse(DataNotModifiedException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), Response.Status.NOT_ACCEPTABLE.getStatusCode());
        return Response.status(Response.Status.NOT_ACCEPTABLE).entity(errorMessage).build();
    }
}
