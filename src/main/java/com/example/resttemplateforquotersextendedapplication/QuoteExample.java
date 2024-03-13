package com.example.resttemplateforquotersextendedapplication;

public record QuoteExample(String type, QuoteValue value) {

    @Override
    public String toString() {
        return "Type: " + type + ", " + value.id().toString() + ": " + value.quote() + "\n";
    }
}