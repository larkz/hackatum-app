package com.github.hackatum.Optimo;

public class OptimoAction {

    String article;

    public OptimoAction(String articleInput) {
        article = articleInput;
    }

    @Override
    public String toString() {
        return article;
    }
}
