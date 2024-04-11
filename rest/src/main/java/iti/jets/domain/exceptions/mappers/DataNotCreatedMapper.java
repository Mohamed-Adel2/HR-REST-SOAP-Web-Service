package iti.jets.domain.exceptions.mappers;

import iti.jets.domain.exceptions.models.DataNotCreatedException;
import iti.jets.domain.exceptions.models.ErrorMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DataNotCreatedMapper implements ExceptionMapper<DataNotCreatedException> {
    @Override
    public Response toResponse(DataNotCreatedException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }
}
