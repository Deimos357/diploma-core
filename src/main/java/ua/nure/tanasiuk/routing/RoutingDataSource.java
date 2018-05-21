package ua.nure.tanasiuk.routing;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class RoutingDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return Optional
            .ofNullable(TransactionContextHolder.getTransactionType())
            .orElse(Transaction.WRITE);
    }
}
