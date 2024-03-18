package com.example.resttemplateforquotersextendedapplication.quoterextend;

public record QuoterQuote(String type, QuoterQuoteValue value) {

    @Override
    public String toString() {
        return "Type: " + type + ", " + value.id().toString() + ": " + value.quote() + "\n";
    }

}