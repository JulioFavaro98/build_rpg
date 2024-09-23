package com.rpg.build_rpg.infra.exceptions;

public class ItemAlreadyExistsException extends RuntimeException{

    public ItemAlreadyExistsException(String message) {
        super(message);
    }

}
