package com.example.Vet_db.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException (String mensaje){
        super(mensaje);
    }
}
