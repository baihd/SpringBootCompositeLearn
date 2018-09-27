package com.composite.exception;


import com.composite.result.CodeMsg;

public class IException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public IException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

}
