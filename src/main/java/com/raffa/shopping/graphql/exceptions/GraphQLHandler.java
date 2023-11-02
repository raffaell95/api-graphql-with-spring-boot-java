package com.raffa.shopping.graphql.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphQLHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {

        return graphQLError((DomainException) ex, env);

    }
    private GraphQLError graphQLError(DomainException ex,DataFetchingEnvironment env){

        return GraphqlErrorBuilder.newError()
                .message(ex.getMessage())
                .locations(null)
                .path(env.getExecutionStepInfo().getPath())
                .build();
    }

}