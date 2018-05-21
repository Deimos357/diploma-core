package ua.nure.tanasiuk.routing;

import java.util.Objects;

public final class TransactionContextHolder {
    private static final ThreadLocal<Transaction> CONTEXT_HOLDER = new ThreadLocal<>();

    private TransactionContextHolder() {
    }

    public static Transaction getTransactionType() {
        return CONTEXT_HOLDER.get();
    }

    public static void setTransactionType(Transaction transaction) {
        Objects.requireNonNull(transaction);
        CONTEXT_HOLDER.set(transaction);
    }

    public static void clearTransactionType() {
        CONTEXT_HOLDER.remove();
    }
}
