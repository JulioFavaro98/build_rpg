package com.rpg.build_rpg.infra.exceptions;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String message) {
        super(message);
    }

}
