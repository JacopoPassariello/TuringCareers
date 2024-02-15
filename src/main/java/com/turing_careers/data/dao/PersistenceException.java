package com.turing_careers.data.dao;

import lombok.experimental.StandardException;

/**
 * Eccezione lanciata da tutti i metodi che possono causare errori nella gestione della persistenza
 */
@StandardException
public class PersistenceException extends Exception {
}
